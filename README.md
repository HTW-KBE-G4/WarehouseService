# WarehouseService

Backend-Storage Service for Online Shop

#Running without docker

Start development server  inside root directory with

```
nvm spring-boot:run
```

Spring Depends on an MySQL database which should run on port ```3306```. 

##Configure Spring for local database

Configuration changes are beeing made at ``application.properties``-file.

- Change JDBC String to connect to ```localhost``` rather than ```db```. 
- might need to change user credentials
- default database should be named ```db```

#Running with docker

To start the docker service including db image run 

```
docker-compose up
``` 

inside the root directory. With default configuration docker-compose will build the image with the ```dockerfile```. 

Uncomment
```
#image: profanta3/htwb-kbe-g4:main
```
inside docker-compose to pull the image from docker repository rather than building it. And comment out:
```
build: .
```

#API

Server Port: ```3002```

API Endpoint: ````component/````

##Supported CURL Operations

```
# Get all products
GET /components

# Get specific product
GET /component/{id}
```