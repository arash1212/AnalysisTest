package com.example.salehi.model.repository;

import com.example.salehi.model.entity.OrderEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

    @Query(value = "SELECT entity from OrderEntity AS entity WHERE entity.customer.id=:customerId AND entity.product.id=:productId")
    Optional<OrderEntity> findByCustomerIdAndProductId(int customerId, int productId);

    @Query(value = "SELECT entity from OrderEntity AS entity WHERE entity.customer.id=:customerId")
    List<OrderEntity> findAllByCustomerId(int customerId, PageRequest pageRequest);
}
