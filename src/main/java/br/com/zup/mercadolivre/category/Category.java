package br.com.zup.mercadolivre.category;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    @ManyToOne
    private Category category;

    public Category(){
    }

    public Category(@NotBlank String name){
        this.name = name;
    }

    public Category(@NotBlank String name,
                    Category category){
        this.name = name;
        this.category = category;
    }
}
