1. download zip file and unzip it.
2. open cmd/terminal
3. go to ~/location-simulator location 
4. use 'mvn spring-boot:run' command to run the application (if maven not already installed, use 'https://maven.apache.org/install.html' link to install maven)
5. application runs on default port 8080
6. use uri 'http://localhost:8080/locationsimulator/points/v0/origin/{origin}/destination/{destination}' using postman or browser
	where origin and destination are comma sperated values of latitude and longitude
	Ex. 'http://localhost:8080/locationsimulator/points/v0/origin/12.93175,77.62872/destination/12.92662,77.63696'
	
Alternative:
1. import project in IDE (eclipse/intellij).
2. setup maven build to run spring boot application and run the application.
3. follow steps from 5 from above

Files:
1. com.locus.sh.locationsimulator.controller.LocationSimulatorResource.java: contains rest controller resource.
2. com.locus.sh.locationsimulator.serice.LocationSimulatorService.java: contains all post processing for polyline coordinates to get points in certain intervals
3. com.locus.sh.locationsimulator.dao.LocationSimulatorDAO.java: send reqest to google direction api and get JSON response
4. com.locus.sh.locationsimulator.exception.*: All generic and custom exception
5. com.locus.sh.locationsimulator.utils.LocationUtils.java: coordinates utility function to apply post processing on recieved data from google api
6. com.locus.sh.locationsimulator.model.LocationPointModel.java: store origin and destination data received from user
7. com.locus.sh.locationsimulator.bean.*: store data recieved from google api and user
8. com.locus.sh.locationsimulator.constants.*: application constants

	

