package org.example.controller;

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

    @PostMapping
    public ResponseEntity<Weather> save(@RequestBody Weather weather)
    {
        return weatherRepository.findById(weather.getId()).isPresent()
                ? new ResponseEntity(weatherRepository.findById(weather.getId()), HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(weatherRepository.save(weather), HttpStatus.CREATED);
    }
}
