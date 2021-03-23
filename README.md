

<h1 align="center">An online collaborative text editor</h1>

built upon SpringBoot, Mybatis, Websocket and Vue.js

## Deployment

run `docker-compose up` and configure Nginx to be like [nginx.conf](nginx.conf)

To run the backend and frontend separately (with maven, jre8 and npm): run `mvn package -DskipTests; java -jar ./target/docs-0.0.1-SNAPSHOT.jar` for the backend, and `npm install; npm run serve`

## Customization
* MySQL default data location: `/var/lib/mysql`. You can change it in [docker-compose.yml](docker-compose.yml) if you wish.
* Swagger2 UI: `localhost:8082/v2/api-docs`

## Acknowledgement
The Vue.js frontend UI is adapted from [mortenterhart/collaborative-markdown-editor][https://github.com/mortenterhart/collaborative-markdown-editor/tree/master/frontend], but the backend is based on traditional Java EE
