
# Overview

* In this, we will use client side load balancing using Netflix Ribbon

* To invoke other microservice, we will be using *FEIGN CLIENT* instead of RestTemplate

* *Hystrix* framework library will be used to control the interaction between services by providing fault tolerance and latency tolerance. 
* Ribbon is inter process communication ( RPC ) library with built in software load balancer.

* If one microservice needs to communicate with another microservice, then generally, it looks up the service registry using discovery client and Eureka server returns all the instances of that target microservice to the caller service. Then it is the responsibility of the caller service to choose which instance to send request. We implemented this in [Eureka](../eureka)

* Eureka Discovery Server
    * service registry and discovery server.
* Employee Service
    * microservice, eureka client
    * talks with eureka server to register itself in the service registry.
    * This will lookup for reward service details in eureka service registry using netfilx ribbon client. This will choose one reward service to communicate via *Feign Client*
* Reward Service
    * microservice, eureka client
    * talks with eureka server to register itself in the service registry.
    * Hystrix will be used for fallback in case of any errors

* Circuit Breaker
    * Due to some reason the rewards service throws an exception. We defines fallback method using Hystrix. In case of exception in the exposed service the fallback method returned some default value.
    * In [RewardServiceImpl](reward-service/src/main/java/com/pankiba/rewardservice/service/impl/RewardServiceImpl.java), if getRewards() method throws any exception then getRewardsFallback will be called by Hystrix. 
    * Flow will be **Controller -> Service::getRewards -> Excception -> Service::getRewardsFallback**
    * If exception keeps occruing in getRewards method then thn the Hystrix circuit will break and the  reward-service will skip the getRewards method all together and directly call the fallback method.
    * Flow will be **Controller -> Service::getRewardsFallback**

* Commands
* to run rewards endpoint multiple times - it will run 100 times and 5 request at time
   - xargs -I % -P 5 curl -I "http://<XX.XX.XX.XXX>:8000/employee-service/api/employees-with-rewards" < <(printf '%s\n' {1..100})
   - http://localhost:8100/reward-service/hystrix/monitor 
