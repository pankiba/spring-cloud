### Overview

* In this, we will use Eureka server for building the service registry server and Eureka clients which will register themselves and discover other services to call REST APIs.

* Eureka Discovery Server
    * service registry and discovery server.
* Employee Service
    * microservice, eureka client
    * talks with eureka server to register itself in the service registry.
    * This will lookup for reward service details in eureka service registry using discovery client and communicates with it via Rest Template 
* Reward Service
    * microservice, eureka client
    * talks with eureka server to register itself in the service registry.

