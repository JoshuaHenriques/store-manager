package org.jayhenri.ecommerce.repository;

import org.jayhenri.ecommerce.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    @Query("select case when count(c)> 0 then true else false end from Customer c where lower(c.phoneNumber) like lower(:phoneNumber)")
    boolean existsPhoneNumber(@Param("phoneNumber") String phoneNumber);

    @Query("select case when count(c)> 0 then true else false end from Customer c where lower(c.email) like lower(:email)")
    boolean existsEmail(@Param("email") String email);
}