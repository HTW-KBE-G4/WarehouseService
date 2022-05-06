# WarehouseService

Backend-Storage Service for Online Shop

# General Infos

This Spring Service is configured and build to run inside a docker-compose environment.
Furthermore this Service should be combined with the [Product-Backend-Service](https://github.com/HTW-KBE-G4/ProductService).

# How to run

Run this WarehouseService with given docker-compose configuration with
```shell
docker-compose up -d
```
inside the repo root directory. Docker-compose will do following:
- build and instantiate the WarehouseService docker container based on the `dockerfile`.
- Instantiate a mysqlServer container
- create a internal network needed by the ProductService to access this rest controller.


# API

## Endpoint

Server Port: ```3002```

```
# Product API

## Get all products
GET /products

# Component API

## Get all components
GET /components

```

## JSON  Body

Example for all components
```
GET /components 
```

```json5
[
  {  
  "component_id":1,
  "type":"CPU",
  "model":"i7-10700K",
  "description":"Die Intel Core i7 Prozessoren stellen dank schnellerer, intelligenter Multicochnik, die Rechenleistung ",
  "manufacturer":"Intel",
  "releaseDate":"2019",
  "uvp":374.0,
  "weight":10.0,
  "productName":"Intel® Core™ i7-10700K Prozessor",
  "ean":5032037188609,
  "imageURL":"https://pixabay.com/get/g480552bae51294d688aaae5fad719d5e7298799356734268fc625150f585b6bc35323a108180b1a667e3a29cb0e4d6320608a34cd071d5a57a973e14b09ab9de433befd4ae26cfb6007ce12a5306009b_1920.jpg"
  },
  {/*...*/},
  {/*...*/},
  {/*...*/},
  {/*...*/}
]
```