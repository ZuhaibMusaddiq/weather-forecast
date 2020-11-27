# weather-forecast

Server

1- Create database 'weather-forecast'
2- clean compile install
3a- if debugging
    java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 -jar demo-0.0.1-SNAPSHOT.jar
3b- if not 
    mvn spring-boot:run
	

Client 

1- Install nodejs
2- npm install -g @vue/cli      
3- vue ui
4- load client folder on vue cli 
or npm run serve

Make sure server is running before client, as both try to avail port 8080.
Vue can find next available port but server can't.

