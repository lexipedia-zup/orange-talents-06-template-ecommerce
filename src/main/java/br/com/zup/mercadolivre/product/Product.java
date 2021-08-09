package br.com.zup.mercadolivre.product;

import br.com.zup.mercadolivre.category.Category;
import br.com.zup.mercadolivre.product.image.ProductImage;
import br.com.zup.mercadolivre.product.particular.Particular;
import br.com.zup.mercadolivre.question.Question;
import br.com.zup.mercadolivre.user.User;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private BigDecimal price;
    private Integer avaliableQuantity;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "product")
    private Set<Particular> particulars = new HashSet<>();
    private String description;
    @ManyToOne
    private Category category;
    @CreationTimestamp
    private LocalDateTime registrationTime = LocalDateTime.now();
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "product")
    private Set<ProductImage> images = new HashSet<>();
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "product")
    private List<Question> quesitons;

    @Deprecated
    public Product() {

    }

    public Product(@NotBlank String name,
                   @NotNull @Positive BigDecimal price,
                   @NotNull @PositiveOrZero Integer avaliableQuantity,
                   @Size(min = 3)List<Particular> particulars,
                   @NotBlank @Size(max = 1000) String description,
                   @NotNull Category category,
                   @NotNull User user) {
        this.name = name;
        this.price = price;
        this.avaliableQuantity = avaliableQuantity;
        this.particulars.addAll(particulars.stream().map(particular -> particular.convert(this)).collect(Collectors.toSet()));
        this.description = description;
        this.category = category;
        this.registrationTime = LocalDateTime.now();
        this.user = user;
    }

    public Integer getUserId(){
        return this.user.getId();
    }

    public void updateImage(List<ProductImage> images) {
        this.images.addAll(images);
    }

    public void updateQuestions(Question quesiton){
        this.quesitons.add(quesiton);
    }
}
