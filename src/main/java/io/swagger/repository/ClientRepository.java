package io.swagger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.swagger.model.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, String> {

}
