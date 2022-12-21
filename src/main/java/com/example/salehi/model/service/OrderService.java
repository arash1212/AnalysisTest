package com.example.salehi.model.service;

import com.example.salehi.dto.order.OrderInput;
import com.example.salehi.dto.order.OrderOutput;
import com.example.salehi.model.entity.CustomerEntity;
import com.example.salehi.model.entity.OrderEntity;
import com.example.salehi.model.entity.ProductEntity;
import com.example.salehi.model.repository.CustomerRepository;
import com.example.salehi.model.repository.OrderRepository;
import com.example.salehi.model.repository.ProductRepository;
import com.example.salehi.utils.PaginationFilter;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    public void save(OrderInput input) {
        Optional<CustomerEntity> customerEntity = this.customerRepository.findById(input.getCustomerId());
        if (customerEntity.isEmpty())
            throw new EntityNotFoundException();
        Optional<ProductEntity> productEntity = this.productRepository.findById(input.getProductId());
        if (productEntity.isEmpty())
            throw new EntityNotFoundException();

        Optional<OrderEntity> oldEntity = this.orderRepository.findByCustomerIdAndProductId(input.getCustomerId(), input.getProductId());

        OrderEntity entity = new OrderEntity();
        input.fillEntity(entity);
       // if (oldEntity.isEmpty()) {
            entity.setCustomer(customerEntity.get());
            entity.setProduct(productEntity.get());
     //   } else entity.setId(oldEntity.get().getId());

        this.orderRepository.save(entity);
    }

    public void update(int customerId, int productId, OrderInput input) {
        Optional<OrderEntity> entity = this.orderRepository.findByCustomerIdAndProductId(customerId, productId);
        if (entity.isEmpty())
            throw new EntityNotFoundException();
        Optional<CustomerEntity> customerEntity = this.customerRepository.findById(input.getCustomerId());
        if (customerEntity.isEmpty())
            throw new EntityNotFoundException();
        Optional<ProductEntity> productEntity = this.productRepository.findById(input.getProductId());
        if (productEntity.isEmpty())
            throw new EntityNotFoundException();

        input.fillEntity(entity.get());
        entity.get().setCustomer(customerEntity.get());
        entity.get().setProduct(productEntity.get());

        this.orderRepository.save(entity.get());
    }

    public void delete(int customerId, int productId) {
        Optional<OrderEntity> entity = this.orderRepository.findByCustomerIdAndProductId(customerId, productId);
        if (entity.isEmpty())
            throw new EntityNotFoundException();

        this.orderRepository.delete(entity.get());
    }

    public List<OrderOutput> getAll(PaginationFilter filter) {
        PageRequest page = PageRequest.of(filter.getFrom(), filter.getSize());
        return this.orderRepository.findAll(page).stream().filter(Objects::nonNull).map(OrderOutput::new).collect(Collectors.toList());
    }

    public List<OrderOutput> getAllByCustomerId(int customerId, PaginationFilter filter) {
        PageRequest page = PageRequest.of(filter.getFrom(), filter.getSize());
        return this.orderRepository.findAllByCustomerId(customerId, page).stream().map(OrderOutput::new).collect(Collectors.toList());
    }
}
