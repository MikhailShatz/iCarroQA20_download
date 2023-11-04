package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class AddCarDTO {
    String about;
    String carClass;
    String city;
    String fuel;
    String manufacture;
    String model;
    String pricePerDay;
    String seats;
    String serialNumber;
    String year;
}
