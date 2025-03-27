package org.example.repository;

import org.example.model.Root;
import org.springframework.data.repository.CrudRepository;

public interface Repository extends CrudRepository<Root, Integer>
{

}
