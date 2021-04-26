package company.parking.offense;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
@Builder
public class CameraDto {
	
	@NotNull
	public Date dateTime;
	
	@Size(min = 7, max=8)
	public String carNumber;
	
	@NotNull
	public double longitude;
	
	@NotNull
	public double latitude;
	
	@Min(0)
	public long photoId;
	
	@Min(0)
	public long cameraId;

}
