# Chat application
This application provides the functionality to chat with different users. It's based on a Quasar frontend. The data and logic is handled by a Spring Boot application. To get messages from a different user, Apache Kafka is used as a messaging system (access via the Spring Boot application).

Uses following frameworks/products
* Quasar.js
* Spring Boot
* Apache Kafka (Docker)
* MongoDB (Currently not included)


## Screens

![image](https://user-images.githubusercontent.com/6909859/113509952-8078e780-9558-11eb-858b-50c0a0e0a293.png)

![image](https://user-images.githubusercontent.com/6909859/113509998-bc13b180-9558-11eb-81d2-d3e14c835463.png)


## How to start

### Docker

```bash
docker-compose -f ./docker-compose.yml up -d
```


### Backend
Start `BackendApplication` from VSCode with Spring Boot Dashboard.

### Frontend
Navigate to /frontend.

#### Web
```bash
quasar dev
```

#### Electron
```bash
quasar dev -m electron
```

#### Android (Emulator)
First you need to start a new android emulator instance from AVD.
You need also to setup your Android enviroment (ANDROID_HOME/JAVA_HOME)
```bash
quasar dev -m cordova -T android
```



## Todo
* Not yet 100% responsive
* Add MongoDB docker
* Add unit tests
