

<h1 align="center">An online collaborative text editor</h1>

built upon SpringBoot, Mybatis, Websocket and Vue.js

## Deployment

### Docker

To deploy the application with Docker, run `docker-compose up` and configure Nginx to be like [nginx.conf](nginx.conf)

### Separately

To run the backend and frontend separately (with maven, jre8 and npm): 

run `mvn package -DskipTests; java -jar ./target/docs-0.0.1-SNAPSHOT.jar` for a production build of the backend, and `npm install; npm run serve` for frontend development (`npm run build` for a production build, needs a http server to host static files)

Default MySQL config: (initial file: [sql.sql](sql.sql) )
```
Host: localhost
Port: 3306
Username: mysqluser
Password: mysqlpass
Database: cmd
```


## Customization
* MySQL default data location: `/var/lib/mysql`. You can change it in [docker-compose.yml](docker-compose.yml) if you wish.
* Swagger2 UI: `localhost:8082/v2/api-docs`

## Acknowledgement
* The Vue.js frontend UI is adapted from [mortenterhart/collaborative-markdown-editor](https://github.com/mortenterhart/collaborative-markdown-editor/tree/master/frontend) (based on Java EE)
* [Springboot + websocket and vue.js integration](https://blog.csdn.net/BADAO_LIUMANG_QIZHI/article/details/114392573)
* [JWT and inteceptors implementation demo][https://programmersought.com/article/73635537032/] 
* ...