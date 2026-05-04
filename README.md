# java-springboot

Minimal Spring Boot sample app created to generate a few **intentional** Sonar findings.

## Run

```bash
mvn spring-boot:run
```

## Endpoints

- `GET /api/hello?name=Satya`
- `GET /api/run?cmd=whoami` (intentionally unsafe; for Sonar to flag)

Removed the hardcoded project key and the wrong working-directory
Uses GitHub secrets now:
SONAR_TOKEN
SONAR_ORG
SONAR_PROJECT_KEY
Runs on push to main (this is what your webhook/agent expects)
What you must do on GitHub (repo settings)
Add these Actions secrets:

SONAR_TOKEN = SonarCloud token
SONAR_ORG = satyam5821 (based on your org key)
SONAR_PROJECT_KEY = the SonarCloud project key for this repo
