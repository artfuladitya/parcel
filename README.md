# Parcel

Parcel is a multi-module Maven project consisting of configuration and cost modules. The Configuration application uses spring cloud config to manage configurations, whereas the Cost application uses spring boot to calculate the delivery costs of parcels using their weight and dimensions. It is integrated with another service that applies coupons to parcels and provides discounts. A factory design pattern is used to make cost strategy conditions extensible and maintainable.

## Installation

Use Maven to build the applications.

```bash
 mvn clean compile install
```
The above command will build the following applications-

![12_BuildSuccess](https://user-images.githubusercontent.com/82138263/210727857-24913901-4f91-46db-ad12-b3264572e379.jpg)


## Configuration Module

A dedicated “config server” is brought online from which each microservice can download its configuration data. This dramatically simplifies the management of many microservices by centralizing their configuration in one location and provides the ability to “live” refresh a microservice’s configuration without redeploying the service. As a bonus, Spring Cloud Config provides out-of-the-box support for storing/reading configuration from Git repositories, giving you a full audit history of changes in one location.

**For Simplicity Git Repository for Configuration server is made public** https://github.com/artfuladitya/parcel-config

![config_diagram](https://user-images.githubusercontent.com/82138263/210726069-77bb5828-5cc2-467b-8961-7ebd5601b167.png)

### Run

To test the main application we are not required to run the config server as cost application will use default values.

### API to refresh the values

During runtime when some changes are made in the config server properties, then we can use the below api in cost application to fetch the latest values.

```bash
curl --location --request POST 'http://localhost:8083/actuator/refresh'
```

## Cost Module

Cost module is used to get the cost of delivery of the parcel based on the weight and dimensions.

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

### Test Coverage

![13_TestCoverCoverage](https://user-images.githubusercontent.com/82138263/210728915-0717a1c8-449f-4755-8066-1efe62fa7c5a.jpg)
