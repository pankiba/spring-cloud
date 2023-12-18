# Overview

* In this, we will use client side load balancing using Netflix Ribbon
* Ribbon is inter process communication ( RPC ) library with built in software load balancer.
* If one microservice needs to communicate with another microservice, then generally, it looks up the service registry using discovery client and Eureka server returns all the instances of that target microservice to the caller service. Then it is the responsibility of the caller service to choose which instance to send request. We implemented this in [Eureka](../eureka)
*  Here, client side load balancing comes into picture and handles the complexities around this situation and delegates to proper instance in load balanced fashion.

* Eureka Discovery Server
    * service registry and discovery server.
* Employee Service
    * microservice, eureka client
    * talks with eureka server to register itself in the service registry.
    * This will lookup for reward service details in eureka service registry using netfilx ribbon client. This will choose one reward service to communicate via RestTemplate
* Reward Service
    * microservice, eureka client
    * talks with eureka server to register itself in the service registry.
