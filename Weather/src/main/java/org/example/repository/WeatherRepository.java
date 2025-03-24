package org.example.repository;

import org.example.model.Weather;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface WeatherRepository extends CrudRepository<Weather, Integer>
{

}
