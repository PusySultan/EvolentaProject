package org.example.repository;

import org.example.model.Location;
import org.springframework.data.repository.CrudRepository;

public interface LocationRepository extends CrudRepository <Location, Integer>
{

}
