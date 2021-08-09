package br.com.zup.mercadolivre.product;

import br.com.zup.mercadolivre.category.Category;
import br.com.zup.mercadolivre.category.CategoryRepository;
import br.com.zup.mercadolivre.question.Question;
import br.com.zup.mercadolivre.question.QuestionRequest;
import br.com.zup.mercadolivre.question.QuestionWarning;
import br.com.zup.mercadolivre.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

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

    @PostMapping("/{productId}/ask")
    @Transactional
    public ResponseEntity<?> ask(@AuthenticationPrincipal User user, @PathVariable("productId") Integer productId,@RequestBody @Valid QuestionRequest request){

        Optional<Product> productOptional = productRepository.findById(productId);

        if(productOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Question question = request.toModel(productOptional.get(), user);
        productOptional.get().updateQuestions(question);
        QuestionWarning.enviarEmail("alexsander.viana@zup.com.br", "contato@zup.com.br");

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Integer id){
        return ResponseEntity.ok().body(productRepository.findById(id).get());
    }
}
