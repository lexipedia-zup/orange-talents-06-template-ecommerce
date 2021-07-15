package br.com.zup.mercadolivre.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody @Valid CategoryRequest request) {
        Category category = null;
        if (request.haveMotherCategory()) {
            Optional<Category> categoryMother = categoryRepository.findById(request.getCategoryId());
            if (categoryMother.isPresent()) {
                category = request.toModel(categoryMother.get());
            }
        } else {
            category = request.toModel();
        }

        categoryRepository.save(category);
        return ResponseEntity.ok().build();
    }
}
