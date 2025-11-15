package net.engineeringdigest.journalApp.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.*;

@Getter
@Setter
@Component
public class WeatherResponse {
    public Current current;

    @Getter
    @Setter
    public class Current{
        @JsonProperty("observation_time")
        private String observationTime;

        private int temperature;


        @JsonProperty("weather_icons")
        private List<String> weatherIcons;

        @JsonProperty("weather_descriptions")
        private List<String> weatherDescriptions;

        private int feelslike;
    }


}

//public class AirQuality{
//    public String co;
//    public String no2;
//    public String o3;
//    public String so2;
//    public String pm2_5;
//    public String pm10;
//    public String us-epa-index;
//    public String gb-defra-index;
//}
//
//public class Astro{
//    public String sunrise;
//    public String sunset;
//    public String moonrise;
//    public String moonset;
//    public String moon_phase;
//    public int moon_illumination;
//}




