package br.com.zup.mercadolivre.question;

import br.com.zup.mercadolivre.product.Product;
import br.com.zup.mercadolivre.user.User;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;
    @CreationTimestamp
    private LocalDateTime registrationTime = LocalDateTime.now();
    @ManyToOne
    private User user;
    @ManyToOne
    private Product product;

    @Deprecated
    public Question() {
    }

    public Question(String title,
                    User user,
                    Product product) {
        this.title = title;
        this.user = user;
        this.product = product;
    }


}
