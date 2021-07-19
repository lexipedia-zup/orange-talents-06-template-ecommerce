package br.com.zup.mercadolivre.product;

import br.com.zup.mercadolivre.category.Category;
import br.com.zup.mercadolivre.user.User;
import br.com.zup.mercadolivre.validation.Exists;
import br.com.zup.mercadolivre.validation.NoDuplicity;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductRequest {

    @NotBlank
    private String name;
    @NotNull
    @Positive
    private BigDecimal price;
    @NotNull
    @PositiveOrZero
    private Integer avaliableQuantity;
    @Valid
    @Size(min = 3, message = "Devem haver ao menos 3 características")
    @NoDuplicity(message = "Não podem haver características com nome duplicado.")
    private List<ParticularRequest> particularsRequest = new ArrayList<>();
    @NotBlank
    @Size(max = 1000, message = "Descrição muito longa. Deve conter no máximo 1000 caracteres.")
    private String description;
    @NotNull
    @Exists(domainClass = Category.class, fieldName = "id", message = "A categoria cadastrada não não existe")
    private Integer categoryId;

    public ProductRequest(@NotBlank String name,
                          @NotNull @Positive BigDecimal price,
                          @NotNull @PositiveOrZero Integer avaliableQuantity,
                          @Size(min = 3) @Valid List<ParticularRequest> particularsRequest,
                          @NotBlank @Size(max = 1000) String description,
                          @NotNull Integer categoryId) {
        this.name = name;
        this.price = price;
        this.avaliableQuantity = avaliableQuantity;
        this.particularsRequest = particularsRequest;
        this.description = description;
        this.categoryId = categoryId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public Product toModel(Category category, User user) {
        return new Product(this.name,
                this.price,
                this.avaliableQuantity,
                ParticularRequest.toModelList(particularsRequest),
                this.description,
                category,
                user);
    }

    public List<ParticularRequest> getParticularsRequest() {
        return particularsRequest;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getAvaliableQuantity() {
        return avaliableQuantity;
    }

    public String getDescription() {
        return description;
    }
}
