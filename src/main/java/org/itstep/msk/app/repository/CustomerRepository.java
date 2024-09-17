package org.itstep.msk.app.repository;

import org.itstep.msk.app.entity.Customer;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Dao interface of customer entity model
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    /**
     * Find a customer by customer's name
     * @param name
     * @return
     */
    @Query(value = "SELECT c from Customer c where c.name = :name")
    Customer findByName(@Param("name") String name);

    /**
     * Find customer by customer's email sorting
     * @param email
     * @return
     */
    @Query(value = "SELECT c from Customer c where c.email= :email ORDER BY c.email")
    Customer getCustomerByEmail(@Param("email") String email);

    /**
     * Find all emails from customer table
     * where <p>email</p> is not null
     * @param email
     * @return
     */
    @Query(value = "select c.email from Customer  c  where c.email= :email and c.email is not null order by c.email ")
    @EntityGraph(value = "customer-entity-graph", type = EntityGraph.EntityGraphType.FETCH)
    List<String> customerEmails(@Param("email") String email);


    @Modifying(clearAutomatically = true)
    void deleteById(@Param("id") @NotNull Integer id);


}
