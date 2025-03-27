package org.example.controller;

import org.example.model.Root;
import org.example.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;

// port 8081

@RestController
@RequestMapping
public class WeatherController
{
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Repository repository;

    @Value("${appid}")
    private String appId;

    private LocalDateTime alloReqestTime = LocalDateTime.now();

    @GetMapping("/weather")
    public Root getWeather(@RequestParam double lat, @RequestParam double lon)
    {
        if(LocalDateTime.now().isBefore(alloReqestTime))
        {
            return repository.findAll().iterator().next();
        }

       try
       {
           String url =  UriComponentsBuilder
                   .fromHttpUrl("https://api.openweathermap.org/data/2.5/weather")
                   .queryParam("lat", lat) // 54.1838 - Saransk
                   .queryParam("lon", lon) // 45.1749 - Saransk
                   .queryParam("units", "metric")
                   .queryParam("appid", appId)
                   .encode()
                   .toUriString();

           Root answer = restTemplate.getForObject(url, Root.class);
           alloReqestTime = LocalDateTime.now().plusMinutes(1);

           assert answer != null;
           repository.save(answer);

           return answer;
       }
       catch (Exception exception)
       {
           return new Root(exception.getMessage());
       }
    }
}