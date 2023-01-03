# Parcel

Parcel is a multi module maven project consisting of config which is a spring cloud config application for dealing with
configuration management and cost which is a spring boot application for calculating the delivery cost of a parcel based
on its weight and dimensions. The service is integrated with another service that applies coupon to a parcel and
provides discount. Application uses Factory design pattern which makes cost strategy conditions extensible and
maintainable.

## Installation

Use Maven to build the applications.

```bash
 mvn clean compile install
```

## Config Server

Config Application provides server and client-side support for externalized configuration in a distributed system. With
the Config Server we have a central place to manage external properties for applications across all environments.
Configuration Link: [github](https://github.com/artfuladitya/parcel-config)

### Run

Execute the jar and keep it running in the background so that other application can communicate with config server to
get the latest configuration values.

### API to refresh the values

```bash
curl --location --request POST 'http://localhost:8083/actuator/refresh'
```

## Cost Server

Cost Calculation server is used to get the cost of delivery of the parcel based on the weight and dimensions.

### API to get cost of the parcel

```bash
curl --location --request POST 'http://localhost:8083/parcel/cost' \
--header 'Content-Type: application/json' \
--data-raw '{
    "weight":12,
    "length":3,
    "width":2,
    "height":2
}'
```

### Swagger Documentation

```bash
http://localhost:8083/swagger-ui/index.html
```