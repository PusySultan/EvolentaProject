package org.example.controller;

import jakarta.transaction.Transactional;
import org.example.model.Location;
import org.example.model.Weather;
import org.example.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/location")
public class LocationControler
{
    @Autowired
    private LocationRepository locationRepository;

    @GetMapping
    public Iterable<Location> findAll()
    {
        return locationRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Location> findById(@PathVariable int id)
    {
        return locationRepository.findById(id);
    }

    @GetMapping("/weather")
    public Weather getWeatherByLocationName(@RequestParam String name)
    {
        return new Weather(10);
    }

    @PostMapping
    public ResponseEntity<Location> save(@RequestBody Location Location)
    {
        return locationRepository.findById(Location.getId()).isPresent()
                ? new ResponseEntity(locationRepository.findById(Location.getId()), HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(locationRepository.save(Location), HttpStatus.CREATED);
    }

    @DeleteMapping
    @Transactional
    public void deleteAll()
    {
        locationRepository.deleteAll();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteLocationById(@PathVariable int id)
    {
        locationRepository.deleteById(id);
    }
}
