package com.example.salehi.model.service;

import com.example.salehi.dto.product.ProductInput;
import com.example.salehi.dto.product.ProductOutput;
import com.example.salehi.model.entity.ProductEntity;
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
public class ProductService {

    private final ProductRepository customerRepository;

    public ProductService(ProductRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Integer save(ProductInput input) {
        ProductEntity entity = new ProductEntity();
        input.fillEntity(entity);
        return this.customerRepository.save(entity).getId();
    }

    public void update(int id, ProductInput input) {
        Optional<ProductEntity> entity = this.customerRepository.findById(id);
        if (entity.isEmpty())
            throw new EntityNotFoundException();

        input.fillEntity(entity.get());
        this.customerRepository.save(entity.get());
    }

    public void delete(int id) {
        Optional<ProductEntity> entity = this.customerRepository.findById(id);
        if (entity.isEmpty())
            throw new EntityNotFoundException();

        this.customerRepository.delete(entity.get());
    }

    public ProductOutput getById(int id) {
        Optional<ProductEntity> optional = this.customerRepository.findById(id);
        if (optional.isEmpty())
            throw new EntityNotFoundException();

        return new ProductOutput(optional.get());
    }

    public List<ProductOutput> getAll(PaginationFilter filter) {
        PageRequest page = PageRequest.of(filter.getFrom(), filter.getSize());
        return this.customerRepository.findAll(page).stream().filter(Objects::nonNull).map(ProductOutput::new).collect(Collectors.toList());
    }
}
