package org.itstep.msk.app.repository;

import org.itstep.msk.app.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Optional;


@Repository
public interface CustomRepository extends CrudRepository<Customer,Integer> {

    Optional<Customer> findById(Integer id);

    void deleteById(Integer id);




}
