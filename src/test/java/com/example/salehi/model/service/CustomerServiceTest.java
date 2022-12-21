package com.example.salehi.model.service;

import com.example.salehi.dto.customer.CustomerInput;
import com.example.salehi.dto.customer.CustomerOutput;
import com.example.salehi.model.entity.CustomerEntity;
import com.example.salehi.model.entity.OrderEntity;
import com.example.salehi.model.entity.ProductEntity;
import com.example.salehi.model.repository.CustomerRepository;
import com.example.salehi.utils.PaginationFilter;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {
    private final int ID = 1;
    private final int LENGTH = 5;
    private CustomerOutput customerOutput;
    private final CustomerInput customerInput = new CustomerInput();
    private final CustomerEntity customerEntity = new CustomerEntity();
    private final List<OrderEntity> orderEntities = new ArrayList<>(LENGTH);
    private final PaginationFilter paginationFilter = new PaginationFilter();
    private final List<CustomerEntity> customerEntityList = new ArrayList<>(LENGTH);
    private final List<CustomerOutput> customerOutputList = new ArrayList<>(LENGTH);

    @Mock
    private CustomerRepository customerRepository;
    @Spy
    @InjectMocks
    private CustomerService customerService;

    @Before
    public void setUp() {
        String LAST_NAME = "lastName";
        String FIRST_NAME = "firstName";
        String DESCRIPTION = "description";
        String EMAIL = "arashsalehi849@yahoo.com";
        //paginationFilter init
        paginationFilter.setFrom(0);
        paginationFilter.setSize(10);
        //productEntity init
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(ID);
        //customerEntity init
        customerEntity.setId(ID);
        //customerEntity init
        customerEntity.setId(ID);
        customerEntity.setEmail(EMAIL);
        customerEntity.setLastName(LAST_NAME);
        customerEntity.setFirstName(FIRST_NAME);
        customerEntity.setDescription(DESCRIPTION);
        customerEntity.setOrders(new HashSet<>(orderEntities));
        //orderEntities fill
        for (int i = 0; i < LENGTH; i++) {
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setCustomer(customerEntity);
            orderEntity.setProduct(productEntity);
            orderEntities.add(orderEntity);
        }
        //customerEntityList init
        for (int i = 0; i < LENGTH; i++) {
            CustomerEntity entity = new CustomerEntity();
            entity.setId(ID);
            entity.setEmail(EMAIL);
            entity.setLastName(LAST_NAME);
            entity.setFirstName(FIRST_NAME);
            entity.setDescription(DESCRIPTION);
            entity.setOrders(new HashSet<>(orderEntities));
            customerEntityList.add(entity);
            customerOutputList.add(new CustomerOutput(customerEntity));
        }
        //customerInput init
        customerInput.setEmail(EMAIL);
        customerInput.setLastName(LAST_NAME);
        customerInput.setFirstName(FIRST_NAME);
        customerInput.setDescription(DESCRIPTION);
        //
        customerOutput = new CustomerOutput(customerEntity);
    }

    @Test
    public void save() {
        int EXPECTED = customerEntity.getId();
        Mockito.doReturn(customerEntity).when(customerRepository).save(Mockito.any(CustomerEntity.class));
        int result = this.customerService.save(customerInput);
        Assertions.assertEquals(EXPECTED, result);
        Mockito.verify(customerService, Mockito.times(1)).save(customerInput);
        Mockito.verify(customerRepository, Mockito.times(1)).save(Mockito.any(CustomerEntity.class));
        Mockito.verifyNoMoreInteractions(customerRepository, customerService);
    }

    @Test
    public void update() {
        Mockito.doReturn(Optional.of(customerEntity)).when(customerRepository).findById(ID);
        Mockito.doReturn(customerEntity).when(customerRepository).save(Mockito.any(CustomerEntity.class));
        this.customerService.update(ID, customerInput);
        Mockito.verify(customerService, Mockito.times(1)).update(ID, customerInput);
        Mockito.verify(customerRepository, Mockito.times(1)).findById(ID);
        Mockito.verify(customerRepository, Mockito.times(1)).save(Mockito.any(CustomerEntity.class));
        Mockito.verifyNoMoreInteractions(customerRepository, customerService);
    }

    @Test
    public void delete() {
        Mockito.doReturn(Optional.of(customerEntity)).when(customerRepository).findById(ID);
        this.customerService.delete(ID);
        Mockito.verify(customerService, Mockito.times(1)).delete(ID);
        Mockito.verify(customerRepository, Mockito.times(1)).delete(Mockito.any(CustomerEntity.class));
        Mockito.verify(customerRepository, Mockito.times(1)).findById(ID);
        Mockito.verifyNoMoreInteractions(customerRepository, customerService);
    }

    @Test
    public void getById() {
        Mockito.doReturn(Optional.of(customerEntity)).when(customerRepository).findById(ID);
        CustomerOutput result = this.customerService.getById(ID);
        Assertions.assertEquals(customerOutput.getId(), result.getId());
        Assertions.assertEquals(customerOutput.getEmail(), result.getEmail());
        Assertions.assertEquals(customerOutput.getDescription(), result.getDescription());
        Assertions.assertEquals(customerOutput.getFirstName(), result.getFirstName());
        Mockito.verify(customerService, Mockito.times(1)).getById(ID);
        Mockito.verify(customerRepository, Mockito.times(1)).findById(ID);
        Mockito.verifyNoMoreInteractions(customerRepository, customerService);
    }

    @Test
    public void getAll() {
        Page<CustomerEntity> customerEntityPage = new PageImpl<CustomerEntity>(customerEntityList);
        Mockito.doReturn(customerEntityPage).when(customerRepository).findAll(Mockito.any(PageRequest.class));
        List<CustomerOutput> result = this.customerService.getAll(paginationFilter);
        for (int i = 0; i < LENGTH; i++) {
            Assertions.assertEquals(customerOutputList.get(i).getId(), result.get(i).getId());
            Assertions.assertEquals(customerOutputList.get(i).getEmail(), result.get(i).getEmail());
            Assertions.assertEquals(customerOutputList.get(i).getDescription(), result.get(i).getDescription());
            Assertions.assertEquals(customerOutputList.get(i).getFirstName(), result.get(i).getFirstName());
        }
        Mockito.verify(customerService, Mockito.times(1)).getAll(paginationFilter);
        Mockito.verify(customerRepository, Mockito.times(1)).findAll(Mockito.any(PageRequest.class));
        Mockito.verifyNoMoreInteractions(customerRepository, customerService);
    }
}