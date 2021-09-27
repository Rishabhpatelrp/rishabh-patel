package com.locus.sh.locationsimulator.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.locus.sh.locationsimulator.constants.ExceptionConstants;
import com.locus.sh.locationsimulator.exception.BadRequestException;
import com.locus.sh.locationsimulator.model.LocationPointModel;
import com.locus.sh.locationsimulator.service.LocationSimulatorService;
import com.locus.sh.locationsimulator.utils.LocationUtils;

@RestController
public class LocationSimulatorResource {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private LocationSimulatorService locationSimulatorService;

	@GetMapping(value = "points/v0/origin/{origin}/destination/{destination}")
	public List<String> getlocation(@PathVariable String origin, @PathVariable String destination) {

		logger.info("Inside resource getlocation method, parameters latitude [{}] longitude [{}]",origin,destination);
		// Validate origin longitude and latitude
		if (origin == null || origin.split(",").length != 2) {
			throw new BadRequestException(ExceptionConstants.ORIGIN_FORMAT);
		}

		String[] originSplit = origin.split(",");
		LocationPointModel originLocationPoint = new LocationPointModel();
		originLocationPoint.setLatitude(Double.valueOf(originSplit[0].trim()));
		originLocationPoint.setLongitude(Double.valueOf(originSplit[1].trim()));

		// check if latitude is in range
		if (!LocationUtils.isInLatitudeRange(originLocationPoint.getLatitude())) {
			throw new BadRequestException(ExceptionConstants.LATITUDE_RANGE);
		}

		// check if Logitude is in range
		if (!LocationUtils.isInLongitudeRange(originLocationPoint.getLongitude())) {
			throw new BadRequestException(ExceptionConstants.LONGITUDE_RANGE);
		}

		// Validate destination longitude and latitude
		if (origin == null || origin.split(",").length != 2) {
			throw new BadRequestException(ExceptionConstants.DESTINATION_FORMAT);
		}

		String[] dstinationSplit = destination.split(",");
		LocationPointModel destinationLocationPoint = new LocationPointModel();
		destinationLocationPoint.setLatitude(Double.valueOf(dstinationSplit[0].trim()));
		destinationLocationPoint.setLongitude(Double.valueOf(dstinationSplit[1].trim()));
		// check if latitude is in range
		if (!LocationUtils.isInLatitudeRange(destinationLocationPoint.getLatitude())) {
			throw new BadRequestException(ExceptionConstants.LATITUDE_RANGE);
		}

		// check if Logitude is in range
		if (!LocationUtils.isInLongitudeRange(destinationLocationPoint.getLongitude())) {
			throw new BadRequestException(ExceptionConstants.LONGITUDE_RANGE);
		}
		List<String> locationPoints = locationSimulatorService.getLocationPoints(originLocationPoint,
				destinationLocationPoint);
		logger.info("Exist resource getlocation method, parameters latitude [{}] longitude [{}]",origin,destination);
		return locationPoints;
	}
}
