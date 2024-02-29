package com.yadchenko.botspectehnika.repository;

import com.yadchenko.botspectehnika.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "select o from Order o join fetch o.machine and left join fetch o.attachment where o.id = :id")
    Order getById(Long id);
}
