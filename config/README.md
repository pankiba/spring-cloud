### Overview

* The management of microservice/s by centralizing their configuration in one location and provides the ability to dynamically refresh a microservice/s configuration without redeploying the service

* Confing Server
    * providing configurations at runtime
    * connects to GIT for configurations
* Employee Service
    * microservice, config client
    * using the configuration exposed as config server

* http://127.0.0.1:8888/config-server/employee-service/dev to see the configurations

### Dynamic refresh without restart

* Update the central configuration of microservice and to reflect the new value, excute curl -X POST http://localhost:8000/employee-service/actuator/refresh
* After successful refresh, new value will be displayed
