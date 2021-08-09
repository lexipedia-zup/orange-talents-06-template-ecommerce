package br.com.zup.mercadolivre.product.image;

import br.com.zup.mercadolivre.product.Product;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @URL
    @NotBlank
    private String link;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @Valid
    @NotNull
    private Product product;

    public ProductImage() {
    }

    public ProductImage(@URL @NotBlank String link, @NotNull @Valid Product product) {
        this.link = link;
        this.product = product;
    }

    public String getLink() {
        return link;
    }

    public Product getProduct() {
        return product;
    }
}
