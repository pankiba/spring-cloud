
# Overview

* Eureka Discovery Server
    * service registry and discovery server.

* Zuul Api Gateway
    * talk with eureka server to register itself in service registry
    * intercepts all request from **employee-service -> reward-service**

* Employee Service
    * microservice, eureka client
    * talks with eureka server to register itself in the service registry.
    * This will lookup for reward service details in eureka service registry using netfilx ribbon client. This will choose one reward service to communicate via *Feign Client*

* Reward Service
    * microservice, eureka client
    * talks with eureka server to register itself in the service registry.
    * Hystrix will be used for fallback in case of any errors

* To access employee-service via zuul api gateway
    - **http://{zuul-api-gatway}}/{service-id}/{url}**
    - http://localhost:8765/employee-service/employee-service/api/employees-with-rewards
 
