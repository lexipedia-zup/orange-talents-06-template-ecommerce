package br.com.zup.mercadolivre.product.image;

import br.com.zup.mercadolivre.product.Product;

import javax.persistence.*;

@Entity
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String link;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Product product;

    public ProductImage() {
    }

    public ProductImage(String link, Product product) {
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
