package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.api.response.WeatherResponse;
import net.engineeringdigest.journalApp.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpMethod;

@Service
public class WeatherService {

    @Value("${weather_api_key}")
    private String apiKey;

//    @Autowired
//    private AppCache appCache;

    private static final String API="http://api.weatherstack.com/current?access_key=<apiKey>&query=<city>";

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city){
        String finalAPI = API.replace("<city>",city).replace("<apiKey>",apiKey);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI,HttpMethod.GET,null,WeatherResponse.class);
        WeatherResponse body = response.getBody();
        return body;
    }
}
