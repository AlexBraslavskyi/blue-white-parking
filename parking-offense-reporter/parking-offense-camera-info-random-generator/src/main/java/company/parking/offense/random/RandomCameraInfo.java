package company.parking.offense.random;

import java.time.Instant;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import company.parking.offense.CameraDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Component
@NoArgsConstructor
@Getter
@EnableConfigurationProperties
@PropertySource("classpath:random.properties")
@Log4j2
public class RandomCameraInfo {
	
	private static final int INITIAL_VALUE_CAMERA_IP_RANGE = 100;
	private static final int END_VALUE_CAMERA_IP_RANGE = 999;
	private static final double INITIAL_VALUE_LATITUDE = 32.02000;
	private static final double END_VALUE_LATITUDE = 32.15000;
	private static final double INITIAL_VALUE_LONGITUDE = 34.75000;
	private static final double END_VALUE_LONGITUDE = 34.80000;
	private static final int MIN_NUMBERS_NUMBERPLATE = 7;
	private static final int MAX_NUMBERS_NUMBERPLATE = 8;
//	private static final int INITIAL_VALUE_CAMERA_IP_RANGE = 100;
//	private static final int END_VALUE_CAMERA_IP_RANGE = 999;
//	private static final double INITIAL_VALUE_LATITUDE = 32.02000;
//	private static final double END_VALUE_LATITUDE = 32.15000;
//	private static final double INITIAL_VALUE_LONGITUDE = 34.75000;
//	private static final double END_VALUE_LONGITUDE = 34.80000;
//	private static final int MIN_NUMBERS_NUMBERPLATE = 7;
//	private static final int MAX_NUMBERS_NUMBERPLATE = 8;

	public CameraDto createRandomCameraDto() {
		return new CameraDto(new Date(), getCarNumber(), getLongitude(), getLatitude(), getPhotoId(), getCameraId());
	}

	private long getCameraId() {
		return randomNumber(INITIAL_VALUE_CAMERA_IP_RANGE, END_VALUE_CAMERA_IP_RANGE);
	}

	private long getPhotoId() {
		return Instant.now().getEpochSecond();
	}

	private double getLatitude() {
		return randomDoubleNumber(INITIAL_VALUE_LATITUDE, END_VALUE_LATITUDE);
	}

	private double getLongitude() {
		return randomDoubleNumber(INITIAL_VALUE_LONGITUDE, END_VALUE_LONGITUDE);
	}

	private String getCarNumber() {
		StringBuilder res = new StringBuilder();
		for(int i = 0; i < randomNumber(MIN_NUMBERS_NUMBERPLATE, MAX_NUMBERS_NUMBERPLATE); i++) { // number plate can include 7 or 8 numbers
			res.append(randomNumber(0, 9));			
		}
		return res.toString();
	}
	
	private long randomNumber(long from, long to) {
		return ThreadLocalRandom.current().nextLong(from, to+1);
	}

	private double randomDoubleNumber(double from, double to) {
		return ThreadLocalRandom.current().nextDouble(from, to);
	}
}
