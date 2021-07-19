package br.com.zup.mercadolivre.product;

import javax.persistence.*;

@Entity
public class Particular {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Product product;
    private String name;
    private String description;

    public Particular() {

    }

    public Particular(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Particular(String name, String description, Product product) {
        this.product = product;
        this.name = name;
        this.description = description;
    }

    public Particular convert(Product product) {
        return new Particular(this.name, this.description, product);
    }
}