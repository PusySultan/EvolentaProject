package org.example.controller;

import jakarta.transaction.Transactional;
import org.example.model.Person;
import org.example.model.Weather;
import org.example.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController
{

    @Autowired
    private PersonRepository repository;
    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping
    public Iterable<Person> findAll()
    {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Person> findById(@PathVariable int id)
    {
        return repository.findById(id);
    }

    @GetMapping("{id}/weather")
    public ResponseEntity<Weather> getWeather(@PathVariable int id)
    {
        if (repository.existsById(id))
        {
            String location = repository.findById(id).get().getLocation();
            Weather weather = restTemplate.getForObject("http://localhost:8082/location/weather?name=" + location, Weather.class);
            return new ResponseEntity<>(weather, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Person> save(@RequestBody Person person)
    {
        return new ResponseEntity<>(repository.save(person), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Person> refactorPerson(@RequestBody Person person, @PathVariable int id)
    {
        if(repository.existsById(id))
        {
            repository.deleteById(id);
            return new ResponseEntity<>(repository.save(person), HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping
    @Transactional
    public void deleteAllPersons()
    {
        repository.deleteAll();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<NullPointerException> deletePersonsById(@PathVariable int id)
    {
        if(repository.existsById(id))
        {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}