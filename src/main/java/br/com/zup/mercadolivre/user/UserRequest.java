package br.com.zup.mercadolivre.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

public class UserRequest {

    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Size(min = 6)
    private String password;

    public UserRequest(@NotBlank @Email String email,
                       @NotBlank @Size(min = 6) String password) {
        this.email = email;
        this.password = password;
    }

    public String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        byte hash[] = algorithm.digest(password.getBytes(StandardCharsets.UTF_8));
        StringBuilder hashedPassword = new StringBuilder();
        for (byte b : hash) {
            hashedPassword.append(String.format("%02X", 0xFF & b));
        }
        return hashedPassword.toString();
    }

    public User toModel() throws NoSuchAlgorithmException {
        return new User(this.email, hashPassword(this.password), LocalDateTime.now());
    }
}
