package com.automation.api.clients;

import com.automation.utils.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public abstract class BaseApiClient {

    protected RequestSpecification requestSpec;

    public BaseApiClient() {
        ConfigManager config = ConfigManager.getInstance();
        RestAssured.baseURI = config.get("api.base.url");

        requestSpec = new RequestSpecBuilder()
            .setBaseUri(config.get("api.base.url"))
            .setContentType(ContentType.JSON)
            .addHeader("app-id", config.get("api.app.id"))
            .log(LogDetail.ALL)
            .build();
    }

    protected Response get(String path) {
        return RestAssured.given()
            .spec(requestSpec)
            .when()
            .get(path)
            .then()
            .log().all()
            .extract().response();
    }

    protected Response post(String path, Object body) {
        return RestAssured.given()
            .spec(requestSpec)
            .body(body)
            .when()
            .post(path)
            .then()
            .log().all()
            .extract().response();
    }

    protected Response put(String path, Object body) {
        return RestAssured.given()
            .spec(requestSpec)
            .body(body)
            .when()
            .put(path)
            .then()
            .log().all()
            .extract().response();
    }

    protected Response delete(String path) {
        return RestAssured.given()
            .spec(requestSpec)
            .when()
            .delete(path)
            .then()
            .log().all()
            .extract().response();
    }
}