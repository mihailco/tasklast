package com.example.weatherservice.service;


import com.example.weatherservice.model.Geodata;
import com.example.weatherservice.model.Main;
import com.example.weatherservice.model.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    @Autowired
    RestTemplate restTemplate;

    RestTemplate externalRestTemplate = new RestTemplate();

    @Value("${appid}")
    String appId;

    @Value("${url.weather}")
    String weatherUrl;

    @Cacheable(value = "weathers", key = "#lon + ';' + #lat")
    public Main getWeatherByCord(String lon, String lat){

        String url = String.format("%s?lat=%s&lon=%s&units=metric&appid=%s", weatherUrl, lat, lon, appId);
        Root t = null;
        try {
            t = externalRestTemplate.getForObject(url, Root.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return t.getMain();
    }

    @Cacheable(value = "weathers", key = "#cityName")
    public Main getWeatherByCityName(String cityName) {
        String urlLocatorService = String.format("%s?city=%s", ServicesUrl.locationService, cityName);
       var t = restTemplate.getForObject(urlLocatorService, Geodata.class);

       return getWeatherByCord(t.getLon(), t.getLat());
    }

}
