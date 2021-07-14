package br.com.zup.mercadolivre.user;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Email
    @NotBlank
    @Column(unique = true)
    private String email;
    @NotBlank
    @Size(min = 6)
    private String password;
    @NotNull
    @PastOrPresent
    private LocalDateTime registrationTime;

    public User() {
    }

    public User(@NotBlank @Email String email,
                @NotBlank @Size(min = 6) String password) {
        this.email = email;
        this.password = password;
        this.registrationTime = LocalDateTime.now();
    }
}
