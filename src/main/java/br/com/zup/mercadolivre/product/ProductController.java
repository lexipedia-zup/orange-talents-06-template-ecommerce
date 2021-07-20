package br.com.zup.mercadolivre.product;

import br.com.zup.mercadolivre.category.Category;
import br.com.zup.mercadolivre.category.CategoryRepository;
import br.com.zup.mercadolivre.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> insert(@RequestBody @Valid ProductRequest request,
                                    @AuthenticationPrincipal User user){

        Category category = categoryRepository.findById(request.getCategoryId()).get();

        Product product = request.toModel(category, user);

        productRepository.save(product);

        return ResponseEntity.ok().build();
    }
}
