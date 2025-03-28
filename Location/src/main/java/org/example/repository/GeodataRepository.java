package org.example.repository;

import org.example.model.Geodata;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GeodataRepository extends CrudRepository<Geodata, Integer>
{
    boolean existsByName(String name);
    void deleteByName(String name);
    Optional<Geodata> findByName(String name);
}
