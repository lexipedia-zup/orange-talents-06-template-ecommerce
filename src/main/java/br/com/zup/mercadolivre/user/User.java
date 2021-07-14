package br.com.zup.mercadolivre.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String email;
    private String password;
    private LocalDateTime registrationTime;

    public User() {
    }

    public User(@NotBlank String email,
                String password,
                @NotNull @PastOrPresent LocalDateTime registrationTime) {
        this.email = email;
        this.password = password;
        this.registrationTime = registrationTime;
    }
}
