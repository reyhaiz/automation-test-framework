package com.automation.api.clients;

import io.restassured.response.Response;
import java.util.Map;

public class UserApiClient extends BaseApiClient {

    private static final String USERS_ENDPOINT = "/user";

    public UserApiClient() {
        super();
    }

    public Response getUserById(String userId) {
        return get(USERS_ENDPOINT + "/" + userId);
    }

    public Response createUser(Map<String, String> userData) {
        return post(USERS_ENDPOINT + "/create", userData);
    }

    public Response updateUser(String userId, Map<String, String> userData) {
        return put(USERS_ENDPOINT + "/" + userId, userData);
    }

    public Response deleteUser(String userId) {
        return delete(USERS_ENDPOINT + "/" + userId);
    }

    public Response getTags() {
        return get("/tag");
    }
}