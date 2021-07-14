package br.com.zup.mercadolivre.user;

import br.com.zup.mercadolivre.validation.Unique;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserRequest {

    @NotBlank
    @Email
    @Unique(domainClass = User.class, fieldName = "email", message = "Esse email já foi cadastrado")
    private String email;
    @NotBlank
    @Size(min = 6)
    private String password;

    public UserRequest(@NotBlank @Email String email,
                       @NotBlank @Size(min = 6) String password) {
        this.email = email;
        this.password = password;
    }

    public String hashPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    public User toModel(){
        return new User(this.email, hashPassword(this.password));
    }
}
