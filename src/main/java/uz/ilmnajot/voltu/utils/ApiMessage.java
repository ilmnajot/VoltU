package uz.ilmnajot.voltu.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public final class ApiMessage {
    public static final String USER_NOT_FOUND = "User not found";
    public static final String VEHICLE_NOT_FOUND = "Vehicle not found";
    public static final String STATION_NOT_FOUND = "Station not found";
    public static final String PORT_NOT_FOUND = "Port not found";

}
