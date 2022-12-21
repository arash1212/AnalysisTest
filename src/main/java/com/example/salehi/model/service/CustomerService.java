package com.example.salehi.model.service;

import com.example.salehi.dto.customer.CustomerInput;
import com.example.salehi.dto.customer.CustomerOutput;
import com.example.salehi.model.entity.CustomerEntity;
import com.example.salehi.model.repository.CustomerRepository;
import com.example.salehi.utils.PaginationFilter;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Integer save(CustomerInput input) {
        CustomerEntity entity = new CustomerEntity();
        input.fillEntity(entity);
        return this.customerRepository.save(entity).getId();
    }

    public void update(int id, CustomerInput input) {
        Optional<CustomerEntity> entity = this.customerRepository.findById(id);
        if (entity.isEmpty())
            throw new EntityNotFoundException();

        input.fillEntity(entity.get());
        this.customerRepository.save(entity.get());
    }

    public void delete(int id) {
        Optional<CustomerEntity> entity = this.customerRepository.findById(id);
        if (entity.isEmpty())
            throw new EntityNotFoundException();

        this.customerRepository.delete(entity.get());
    }

    public CustomerOutput getById(int id) {
        Optional<CustomerEntity> optional = this.customerRepository.findById(id);
        if (optional.isEmpty())
            throw new EntityNotFoundException();

        return new CustomerOutput(optional.get());
    }

    public List<CustomerOutput> getAll(PaginationFilter filter) {
        PageRequest page = PageRequest.of(filter.getFrom(), filter.getSize());
        return this.customerRepository.findAll(page).stream().filter(Objects::nonNull).map(CustomerOutput::new).collect(Collectors.toList());
    }
}
