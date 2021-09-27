package com.locus.sh.locationsimulator.dao;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.locus.sh.locationsimulator.bean.LocationPointBean;
import com.locus.sh.locationsimulator.bean.LocationSimulatorBean;
import com.locus.sh.locationsimulator.constants.GoogleApiConstants;
import com.locus.sh.locationsimulator.exception.GoogleMapDirectionApiException;

@Component
@PropertySource("classpath:google-direction-api.properties")
public class LocationSimulatorDAO {
	@Value(value = "${google.map.api.key}")
	private String GOOGLE_MAP_API_KEY;

	@Value(value = "${google.map.api.uri}")
	private String GOOGLE_MAP_API_URI;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * build google map api uri and fetch response using RestTemplete
	 * 
	 * @param originLocationPoint
	 * @param destinationLocationPoint
	 * @return
	 */
	public LocationSimulatorBean getLocationPoints(LocationPointBean originLocationPoint,
			LocationPointBean destinationLocationPoint) {
		logger.info("Inside DAO getLocationPoints method, parameters latitude [{}] longitude [{}]", originLocationPoint,
				destinationLocationPoint);

		HashMap<String, String> uriVariable = new HashMap<>();
		uriVariable.put(GoogleApiConstants.ORIGIN, originLocationPoint.getLat() + "," + originLocationPoint.getLng());
		uriVariable.put(GoogleApiConstants.DESTINATION,
				destinationLocationPoint.getLat() + "," + destinationLocationPoint.getLng());
		uriVariable.put(GoogleApiConstants.KEY, GOOGLE_MAP_API_KEY);

		StringBuilder uriSb = new StringBuilder();
		uriSb.append(GOOGLE_MAP_API_URI).append("?origin={").append(GoogleApiConstants.ORIGIN).append("}&destination={")
				.append(GoogleApiConstants.DESTINATION).append("}&key={").append(GoogleApiConstants.KEY).append("}");

		ResponseEntity<LocationSimulatorBean> responseEntity = new RestTemplate().getForEntity(uriSb.toString(),
				LocationSimulatorBean.class, uriVariable);

		String errorMessage = responseEntity.getBody().getError_message();
		if (errorMessage != null && !errorMessage.trim().isEmpty()) {
			throw new GoogleMapDirectionApiException(errorMessage);
		}

		LocationSimulatorBean body = responseEntity.getBody();

		logger.info("Exit DAO getLocationPoints method, parameters latitude [{}] longitude [{}]", originLocationPoint,
				destinationLocationPoint);
		return body;
	}
}
