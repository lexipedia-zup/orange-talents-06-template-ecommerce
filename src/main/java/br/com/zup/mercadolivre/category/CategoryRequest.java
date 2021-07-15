package br.com.zup.mercadolivre.category;

import br.com.zup.mercadolivre.validation.ExistsIfNotNull;
import br.com.zup.mercadolivre.validation.Unique;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;

public class CategoryRequest {

    @NotBlank
    @Unique(domainClass = Category.class, fieldName = "name", message = "Essa categoria já foi cadastrada")
    private String name;
    @ExistsIfNotNull(fieldName = "id", domainClass = Category.class, message = "Essa categoria nao existe")
    private Integer categoryId;

    public CategoryRequest() {
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CategoryRequest(@NotBlank String name) {
        this.name = name;
    }

    public CategoryRequest(@NotBlank String name, Integer categoryId) {
        this.name = name;
        this.categoryId = categoryId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public boolean haveMotherCategory(){
        return this.categoryId != null;
    }

    public Category toModel(Category category){
        return new Category(this.name, category);
    }

    public Category toModel(){
        return new Category(this.name, null);
    }
}
