package br.com.zup.mercadolivre.user;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Profile implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String profile;

    @Override
    public String getAuthority() {
        return this.profile;
    }

    public Integer getId() {
        return id;
    }

    public String getProfile() {
        return profile;
    }
}
