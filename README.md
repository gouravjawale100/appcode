# Development application repository

A small Java 17+ web application used as the deployable application.

## Build
`mvn clean package`

Output: `target/release-demo-app-1.0.0.jar`

## Run on the server
`java -jar target/release-demo-app-1.0.0.jar`

Default port: 8080. Override with the `PORT` environment variable.

- UI: `http://<server>:8080/`
- Health check: `http://<server>:8080/health` (returns `OK`)

This is a demonstration app, not a production-hardened service.
