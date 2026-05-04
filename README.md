# java-springboot

Minimal Spring Boot sample app created to generate a few **intentional** Sonar findings.

## Run

```bash
mvn spring-boot:run
```

## Endpoints

- `GET /api/hello?name=Satya`
- `GET /api/run?cmd=whoami` (intentionally unsafe; for Sonar to flag)

