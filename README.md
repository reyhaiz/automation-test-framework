# Automation Test Framework

Automation test framework untuk Web UI dan API testing menggunakan Java, Cucumber, Selenium, dan RestAssured.

## Tech Stack

| Tool | Version | Kegunaan |
|------|---------|----------|
| Java | 11 | Programming Language |
| Gradle | 8.x | Build Tool |
| Cucumber | 7.14.0 | BDD Framework |
| Selenium WebDriver | 4.15.0 | Web UI Testing |
| RestAssured | 5.3.2 | API Testing |
| JUnit 5 | 5.10.0 | Test Runner |
| WebDriverManager | 5.6.3 | Auto ChromeDriver Setup |

## Target Applications

- **Web UI**: [DemoBlaze](https://www.demoblaze.com)
- **API**: [DummyAPI](https://dummyapi.io)

## Project Structure
```
src/
├── main/java/com/automation/
│   ├── api/clients/         # API Client classes
│   ├── web/
│   │   ├── driver/          # WebDriver Manager
│   │   └── pages/           # Page Object Model
│   └── utils/               # Utilities
└── test/
    ├── java/com/automation/
    │   ├── api/
    │   │   ├── steps/       # API Step Definitions
    │   │   └── runners/     # API Test Runner
    │   └── web/
    │       ├── steps/       # Web Step Definitions
    │       └── runners/     # Web Test Runner
    └── resources/
        ├── features/
        │   ├── api/         # API Feature Files
        │   └── web/         # Web Feature Files
        └── config/
            └── config.properties
```

## ▶Cara Menjalankan Test

### Prerequisites
- Java JDK 11+
- Chrome Browser
- Git

### Clone Repository
```bash
git clone https://github.com/USERNAME/automation-test-framework.git
cd automation-test-framework
```

### Run API Tests (`@api` tag)
```bash
./gradlew apiTest
```

### Run Web UI Tests (`@web` tag)
```bash
./gradlew webTest
```

### Run Semua Tests
```bash
./gradlew test
```

### Run Headless (tanpa browser window)
```bash
WEB_HEADLESS=true ./gradlew webTest
```

## Test Reports

Setelah menjalankan test, laporan tersedia di:
- **HTML Report**: `build/reports/cucumber/{api|web}/report.html`
- **JSON Report**: `build/reports/cucumber/{api|web}/report.json`

## GitHub Actions

Workflow tersedia di `.github/workflows/test.yml`:
- **Manual Trigger**: Pilih jenis test (all/api/web) via GitHub Actions UI
- **Pull Request**: Otomatis berjalan saat ada PR ke branch `main` atau `develop`

## Test Tags

| Tag | Deskripsi |
|-----|-----------|
| `@web` | Semua Web UI tests |
| `@api` | Semua API tests |
| `@smoke` | Smoke tests |
| `@login` | Login-related tests |
| `@product` | Product-related tests |
| `@cart` | Cart-related tests |
| `@get-user` | Get user API tests |
| `@create-user` | Create user API tests |
| `@update-user` | Update user API tests |
| `@delete-user` | Delete user API tests |