package br.com.zup.mercadolivre.question;

import br.com.zup.mercadolivre.product.Product;
import br.com.zup.mercadolivre.user.User;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;

public class QuestionRequest {

    @NotBlank

    private String title;

    public QuestionRequest() {
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public QuestionRequest(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public Question toModel(Product product, User user){
        return new Question(this.title, user, product);
    }

}
