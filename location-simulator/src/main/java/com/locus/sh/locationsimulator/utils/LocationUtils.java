package com.locus.sh.locationsimulator.utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.math3.util.Precision;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.locus.sh.locationsimulator.bean.LocationPointBean;

public class LocationUtils {

	private static Logger logger = LoggerFactory.getLogger(LocationUtils.class);

	/**
	 * This method adds given distance (in km) to a given point
	 * 
	 * @see <a href=
	 *      "http://www.movable-type.co.uk/scripts/latlong.html">http://www.movable-type.co.uk/scripts/latlong.html</a>
	 *
	 * @param location the latitude of the starting point in degrees, must not be
	 *                 {@link Double#NaN}.
	 * @param angle    the course to be used for extrapolation in degrees, must not
	 *                 be {@link Double#NaN}.
	 * @param distance the distance to be added in ms, must not be
	 *                 {@link Double#NaN}.
	 *
	 * @return the extrapolated point.
	 */

	public static LocationPointBean addDistanceToLocationCoordinates(LocationPointBean location, final double angle,
			final double distance) {

		double radianLatitude = Math.toRadians(location.getLat());
		double radianLongitude = Math.toRadians(location.getLng());

		final double radianAngle = Math.toRadians(angle);
		final double radianAngularDistance = distance / 6371000.0;

		final double lat = Math.asin(Math.sin(radianLatitude) * Math.cos(radianAngularDistance)
				+ Math.cos(radianLatitude) * Math.sin(radianAngularDistance) * Math.cos(radianAngle));

		final double lon = radianLongitude
				+ Math.atan2(Math.sin(radianAngle) * Math.sin(radianAngularDistance) * Math.cos(radianLatitude),
						Math.cos(radianAngularDistance) - Math.sin(radianLatitude) * Math.sin(lat));

		return new LocationPointBean(Precision.round(Math.toDegrees(lat), 6), Precision.round(Math.toDegrees(lon), 6));
	}

	public static double getAngleBetweenTwoLocationCoordinates(LocationPointBean location1,
			LocationPointBean location2) {

		double radianLatitude1 = Math.toRadians(location1.getLat());
		double radianLongitude1 = Math.toRadians(location1.getLng());

		double radianLatitude2 = Math.toRadians(location2.getLat());
		double radianLongitude2 = Math.toRadians(location2.getLng());

		double y = Math.sin(radianLongitude2 - radianLongitude1) * Math.cos(radianLatitude2);
		double x = Math.cos(radianLatitude1) * Math.sin(radianLatitude2)
				- Math.sin(radianLatitude1) * Math.cos(radianLatitude2) * Math.cos(radianLongitude2 - radianLongitude1);

		double angle = Math.toDegrees(Math.atan2(y, x));

		return (angle + 360) % 360;
	}

	public static double distanceBetweenTwoLocations(LocationPointBean location1, LocationPointBean location2) {

		double radianLatitude1 = Math.toRadians(location1.getLat());
		double radianLongitude1 = Math.toRadians(location1.getLng());

		double radianLatitude2 = Math.toRadians(location2.getLat());
		double radianLongitude2 = Math.toRadians(location2.getLng());

		int earthRadius = 6371000; // in meter

		double deltaLatitude = radianLatitude2 - radianLatitude1;
		double deltaLongitude = radianLongitude2 - radianLongitude1;

		double a = Math.pow(Math.sin(deltaLatitude / 2), 2)
				+ Math.cos(radianLatitude1) * Math.cos(radianLatitude2) * Math.pow(Math.sin(deltaLongitude / 2), 2);

		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		double d = earthRadius * c;

		return d;
	}

	/**
	 * Set interval between points
	 * 
	 * @param poly
	 * @param interval
	 * @return
	 */
	public static List<LocationPointBean> getIntervalsInBetween(List<LocationPointBean> poly, Integer interval) {
		if (poly == null || poly.size() == 0) {
			return poly;
		}
		
		List<LocationPointBean> pointsWithInterval = new LinkedList<>();
		pointsWithInterval.add(poly.get(0));
		
		LocationPointBean preLocationPoint = poly.get(0);
		Double current_distance = 0.0;
		Double angle = 0.0;
		
		for (int i = 1; i < poly.size(); i++) {
			Double distance = distanceBetweenTwoLocations(preLocationPoint, poly.get(i));
			if(current_distance < distance) {
				
				
				angle = getAngleBetweenTwoLocationCoordinates(preLocationPoint, poly.get(i));
				preLocationPoint = poly.get(i);
				current_distance += distance;
				
			} else {
				while(current_distance > interval) {
					current_distance = current_distance - interval;
					Double rev_angle = (angle + 180) % 360;
					LocationPointBean locationPointBean = addDistanceToLocationCoordinates(preLocationPoint, rev_angle, current_distance);
					pointsWithInterval.add(locationPointBean);
				}
			}
		}
		
		
//		for (int i = 0; i < poly.size() - 1; i++) {
//			Double distance = distanceBetweenTwoLocations(poly.get(i), poly.get(i + 1));
//			if (distance > interval) {
//				Double angle = getAngleBetweenTwoLocationCoordinates(poly.get(i), poly.get(i + 1));
//				LocationPointBean locationPoint = addDistanceToLocationCoordinates(poly.get(i), angle, interval);
//				poly.add(i+1, locationPoint);
//			}
//		}
//
//		logger.info("Added interval [{}]", poly.toString());
//		System.out.println();
//
//		List<LocationPointBean> pointsWithInterval = new LinkedList<>();
//		pointsWithInterval.add(poly.get(0));
//		Double current_distance = 0.0;
//		for (int i = 0; i < poly.size() - 1; i++) {
//			if (current_distance < interval) {
//				Double distance = distanceBetweenTwoLocations(poly.get(i), poly.get(i + 1));
//				current_distance += distance;
//				logger.info("skip interval [{}] current_distance [{}]", poly.get(i), current_distance);
//			} else {
//				if (current_distance - interval == 0) {
//					pointsWithInterval.add(poly.get(i + 1));
//					current_distance = 0.0;
//					logger.info("adding interval [{}] current_distance [{}]", poly.get(i+1), current_distance);
//				} else {
//					current_distance = current_distance - interval;
//					Double angle = getAngleBetweenTwoLocationCoordinates(poly.get(i), poly.get(i + 1));
//					angle = (angle + 180) % 360;
//					LocationPointBean locationPoint = addDistanceToLocationCoordinates(poly.get(i + 1), angle,
//							current_distance);
//					logger.info("adding interval [{}] current_distance [{}]", locationPoint, current_distance);
//					pointsWithInterval.add(locationPoint);
//				}
//			}
//		}

		return pointsWithInterval;
	}

	/**
	 * Decode polyline points
	 * 
	 * @param encoded
	 * @return
	 */
	public static List<LocationPointBean> decodePoly(String encoded) {

		List<LocationPointBean> poly = new ArrayList<>();
		int index = 0, len = encoded.length();
		int lat = 0, lng = 0;

		while (index < len) {
			int b, shift = 0, result = 0;
			do {
				b = encoded.charAt(index++) - 63;
				result |= (b & 0x1f) << shift;
				shift += 5;
			} while (b >= 0x20);
			int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
			lat += dlat;

			shift = 0;
			result = 0;
			do {
				b = encoded.charAt(index++) - 63;
				result |= (b & 0x1f) << shift;
				shift += 5;
			} while (b >= 0x20);
			int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
			lng += dlng;

			LocationPointBean p = new LocationPointBean((((double) lat / 1E5)), (((double) lng / 1E5)));
			poly.add(p);
		}

		return poly;
	}

	/**
	 * Validate latitude range
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isInLatitudeRange(double value) {
		if (value < -90 || value > 90) {
			return false;
		}
		return true;
	}

	/**
	 * Validate Longitude range
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isInLongitudeRange(double value) {
		if (value < -180 || value > 180) {
			return false;
		}
		return true;
	}

}
