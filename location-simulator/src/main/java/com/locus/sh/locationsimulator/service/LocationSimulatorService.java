package com.locus.sh.locationsimulator.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.locus.sh.locationsimulator.bean.LocationLagBean;
import com.locus.sh.locationsimulator.bean.LocationPointBean;
import com.locus.sh.locationsimulator.bean.LocationSimulatorBean;
import com.locus.sh.locationsimulator.dao.LocationSimulatorDAO;
import com.locus.sh.locationsimulator.model.LocationPointModel;
import com.locus.sh.locationsimulator.utils.LocationUtils;

@Component
public class LocationSimulatorService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private LocationSimulatorDAO locationSimulatorDAO;

	@Value("${polyline.overview.interval}")
	private Integer interval;

	/**
	 * Get latitude and longitude, decode and collect all polyline point, adjust
	 * interval between points
	 * 
	 * @param origin
	 * @param destination
	 * @return
	 */
	public List<String> getLocationPoints(LocationPointModel originLocationPoint,
			LocationPointModel destinationLocationPoint) {
		logger.info("Inside service getLocationPoints method, parameters latitude [{}] longitude [{}]",
				originLocationPoint, destinationLocationPoint);
		LocationPointBean origin = new LocationPointBean(originLocationPoint.getLatitude(),
				originLocationPoint.getLongitude());
		LocationPointBean destination = new LocationPointBean(destinationLocationPoint.getLatitude(),
				destinationLocationPoint.getLongitude());

		// final response string list
		List<String> locationPoints = new ArrayList<>();
		// containing all polyline points from all steps
		List<LocationPointBean> AllLocationPoints = new LinkedList<>();

		LocationSimulatorBean locationSimulatorBean = locationSimulatorDAO.getLocationPoints(origin, destination);
		LocationLagBean lag = getLag(locationSimulatorBean);

		if (lag != null && lag.getSteps() != null && !lag.getSteps().isEmpty()) {
			logger.info("Service getLocationPoints method, # of steps [{}] for latitude [{}] longitude [{}]",
					lag.getSteps().size(), origin, destination);
			lag.getSteps().forEach(step -> {
				List<LocationPointBean> mergedLocationPoints = LocationUtils.decodePoly(step.getPolyline().getPoints());
				AllLocationPoints.addAll(mergedLocationPoints);
			});

			logger.info("Service getLocationPoints method, # of polyline from google api [{}] for latitude [{}] longitude [{}]",
					AllLocationPoints.size(), origin, destination);
			locationPoints = LocationUtils.getIntervalsInBetween(AllLocationPoints, interval).stream()
					.map(locationPoint -> locationPoint.getLat() + ", " + locationPoint.getLng())
					.collect(Collectors.toList());
			logger.info("Service getLocationPoints method, # of polyline after processing [{}] for latitude [{}] longitude [{}]",
					locationPoints.size(), origin, destination);
		} else {
			logger.error("Service getLocationPoints method, lags are empty latitude [{}] longitude [{}]", origin,
					destination);
		}

		logger.info("Exist service getLocationPoints method, parameters latitude [{}] longitude [{}]", origin,
				destination);

		return locationPoints;
	}

	/**
	 * Get lag list from data received from google api
	 * 
	 * @param locationPoints
	 * @return
	 */
	private LocationLagBean getLag(LocationSimulatorBean locationPoints) {

		LocationLagBean lag = new LocationLagBean();
		if (locationPoints != null && locationPoints.getRoutes() != null && locationPoints.getRoutes().size() > 0
				&& locationPoints.getRoutes().get(0) != null && locationPoints.getRoutes().get(0).getLegs() != null
				&& locationPoints.getRoutes().get(0).getLegs().get(0) != null) {
			lag = locationPoints.getRoutes().get(0).getLegs().get(0);
		}

		return lag;
	}
}
