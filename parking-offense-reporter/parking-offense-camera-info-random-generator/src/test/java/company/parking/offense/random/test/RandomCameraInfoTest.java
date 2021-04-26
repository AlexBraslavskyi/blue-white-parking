package company.parking.offense.random.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import company.parking.offense.CameraDto;
import company.parking.offense.random.RandomCameraInfo;
import lombok.extern.log4j.Log4j2;

@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration
@ContextConfiguration(classes = RandomCameraInfo.class)
@Log4j2
@PropertySource("classpath:random.properties")
class RandomCameraInfoTest {
	
	@Autowired
	RandomCameraInfo random;
	
	@Value("${properties.test}")
	String properties;

	@Test
	void aplContext() {
		assertNotNull(random);
		assertEquals("test-pass", properties);
	}
	@Test
	void testCreateRandomCameraDto() {
		for(int i = 0; i < 100; i++) {
			CameraDto randomDto = random.createRandomCameraDto();
			assertTrue(randomDto.dateTime.compareTo(new Date()) <= 0);
//			assertTrue(randomDto.dateTime.toInstant().toEpochMilli() <= System.currentTimeMillis());
			testCarNumber(randomDto.carNumber);
			testLatitude(randomDto.latitude);
			testLongitude(randomDto.longitude);
			testPhotoId(randomDto.photoId);
			testCameraId(randomDto.cameraId);
		}
	}
	
	private void testCarNumber(String randomCarNumber) {
		assertTrue(randomCarNumber.length() >= 7 && randomCarNumber.length() <= 8);
		assertTrue(Integer.parseInt(randomCarNumber) > 0 && Integer.parseInt(randomCarNumber) < 100000000);
	}
	
	private void testLatitude(double randomLatitude) {
		log.debug(">>> RandomCameraInfoTest >> testLatitude: {}", randomLatitude);
		assertTrue(randomLatitude >= 32.02 && randomLatitude <= 32.150);
	}
	
	private void testLongitude(double randomLongitude) {
		assertTrue(randomLongitude >= 34.75 && randomLongitude <= 34.8);
	}
	
	private void testPhotoId(long randomPhotoId) {
		long nowSec = Instant.now().getEpochSecond();
		assertTrue(randomPhotoId >= nowSec);
	}
	
	private void testCameraId(long randomCamersId) {
		assertTrue(randomCamersId >= 100 && randomCamersId <= 999);
	}
	
//	@Test
//	void testGetCameraId() {
//		for(int i = 0; i < 100; i++) {
//			long randomCamersId = random.getCameraId();
//			testCameraId(randomCamersId);
//		}
//	}
//
//	@Test
//	void testGetPhotoId() {
//		
//		for(int i = 0; i < 100; i++) {
//			long randomPhotoId = random.getPhotoId();
//			testPhotoId(randomPhotoId);
//		}
//	}
//
//	@Test
//	void testGetLatitude() {
//		for(int i = 0; i < 100; i++) {
//			double randomLatitude = random.getLatitude();
//			testLatitude(randomLatitude);
//		}
//	}
//	
//	@Test
//	void testGetLongitude() {
//		for(int i = 0; i < 100; i++) {
//			double randomLongitude = random.getLongitude();
//			testLongitude(randomLongitude);
//		}
//	}
//	
//	@Test
//	void testGetCarNumber() {
//		for(int i = 0; i < 100; i++) {
//			String randomCarNumber = random.getCarNumber();
//			testCarNumber(randomCarNumber);
//		}
//	}
}
