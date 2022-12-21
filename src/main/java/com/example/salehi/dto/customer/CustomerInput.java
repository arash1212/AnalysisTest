package com.example.salehi.dto.customer;

import com.example.salehi.model.entity.CustomerEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CustomerInput {
    @Size(max = 500)
    @Schema(example = "توضیحات")
    private String description;
    @NotBlank
    @Email
    @Size(max = 100)
    @Schema(example = "arashsalehi849@yahoo.com")
    private String email;
    @Size(max = 100)
    @Schema(example = "arash")
    private String firstName;
    @Size(max = 100)
    @Schema(example = "salehi")
    private String lastName;

    public void fillEntity(CustomerEntity entity) {
        entity.setDescription(this.description);
        entity.setEmail(this.email);
        entity.setFirstName(this.firstName);
        entity.setLastName(this.lastName);
    }
}
