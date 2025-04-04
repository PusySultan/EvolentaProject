package org.example.controller;

import jakarta.transaction.Transactional;
import org.example.model.Geodata;
import org.example.model.Weather;
import org.example.repository.GeodataRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

// Port 8083
@RestController()
@RequestMapping("/location")
public class LocationController
{
    @Autowired
    private GeodataRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${weather.url}")
    String weatherUrl;


    // GET /location?name={name} - Получить Location по name
    @GetMapping(params = "name")
    public Optional<Geodata> getLocationByName(@RequestParam String name)
    {
        return repository.findByName(name);
    }

    // GET /location - Получить все List<Location>
    @GetMapping
    public Iterable<Geodata> getAllLocations()
    {
        return repository.findAll();
    }

    @GetMapping("/weather")
    public Weather redirectRequestWeather(@RequestParam String name)
    {
        if(repository.existsByName(name))
        {
            Geodata geodata = repository.findByName(name).get();

            String url =  UriComponentsBuilder
                    .fromHttpUrl(String.format("http://%s/weather", weatherUrl))
                    .queryParam("lat", geodata.getLatitude()) // 54.1838 - Saransk
                    .queryParam("lon", geodata.getLongitude()) // 45.1749 - Saransk
                    .toUriString();

            String answer = restTemplate.getForObject(url, String.class);
            return new Weather(new JSONObject(answer).getJSONObject("main"));
        }

        return null;
    }

    //POST /location - Добавить новый Location
    @PostMapping
    public Geodata save(@RequestBody Geodata geodata)
    {
        return repository.save(geodata);
    }

    // PUT /location?name={name} - Изменить Location по name
    @PutMapping(params = "name")
    @Transactional
    public Geodata refactorGeodata(@RequestParam String name, @RequestBody Geodata geoData)
    {
        if(repository.existsByName(name))
        {
            repository.deleteByName(name);
            return repository.save(geoData);
        }

        return null;
    }

    // DELETE /location?name={name} - Удалить Location по name
    @DeleteMapping(params = "name")
    @Transactional
    public void DeleteByName(@RequestParam String name)
    {
        if(repository.existsByName(name))
        {
            repository.deleteByName(name);
        }
    }
}