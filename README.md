# Project X

This project uses Keycloak for authentication. The Keycloak client secret should never be committed to source control.

All modules expect the secret via the `KEYCLOAK_SECRET` environment variable. You can export it locally or provide it through your secrets manager.

Example run:
```bash
export KEYCLOAK_SECRET=your-secret
./mvnw spring-boot:run -pl Root
```

When using Docker Compose or another orchestrator, configure this variable using an `.env` file or the platform's secret facilities.

