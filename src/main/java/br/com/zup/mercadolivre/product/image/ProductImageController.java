package br.com.zup.mercadolivre.product.image;

import br.com.zup.mercadolivre.product.Product;
import br.com.zup.mercadolivre.product.ProductRepository;
import br.com.zup.mercadolivre.user.User;
import io.jsonwebtoken.lang.Assert;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/image")
public class ProductImageController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductImageRepository productImageRepository;

    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Valid ProductImageRequest request,
                                    @AuthenticationPrincipal User user){
/*        Assert.isTrue(request.getLinks().size() == 0, "Deve passar ao menos uma imagem");*/
        Product product = productRepository.findById(request.getProductId()).get();
        if(user.getId() != product.getUserId()){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Você não pode inserir imagens em produtos de outros usuários");
        }
        List<ProductImage> images = request.toModelList(product);

        product.updateImage(images);
        productImageRepository.saveAll(images);
        productRepository.save(product);
        return ResponseEntity.ok().build();

    }
}
