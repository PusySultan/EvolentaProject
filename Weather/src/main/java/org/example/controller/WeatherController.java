package org.example.controller;

import jakarta.transaction.Transactional;
import org.example.model.Weather;
import org.example.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/weather")
public class WeatherController
{
    @Autowired
    private WeatherRepository weatherRepository;

    @GetMapping
    public Iterable<Weather> findAll()
    {
        return weatherRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Weather> findById(@PathVariable int id)
    {
        return weatherRepository.findById(id);
    }

    @GetMapping("/coordinate")
    public ResponseEntity<Weather> getWeatherByCoordinates(
            @RequestParam double lat,
            @RequestParam double lon
    )
    {
        Weather weather = null;

        for(Weather weather_: weatherRepository.findAll())
        {
            if(weather_.getLatitude() == lat && weather_.getLongitude() == lon)
            {
                weather = weather_;
            }
        }

        return weather != null ? new ResponseEntity<>(weather, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping
    @Transactional
    public Weather save(@RequestBody Weather weather)
    {
       return weatherRepository.save(weather);
    }

    @DeleteMapping()
    public void deleteAll()
    {
        weatherRepository.deleteAll();
    }
}
