package uz.ilmnajot.voltu.model.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserStatisticsDTO  {

    Double totalSpent;
    Double totalCharged;
    String totalSpentTime;
    List<Long> visitedStations;
    String totalParkingTime;
    Integer cyclesCounts;

}
