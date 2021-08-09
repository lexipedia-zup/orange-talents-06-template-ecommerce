package br.com.zup.mercadolivre.product.image;

import br.com.zup.mercadolivre.product.Product;
import br.com.zup.mercadolivre.validation.Exists;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class ProductImageRequest {

    @Size(min = 1)
    @NotNull
    private List<MultipartFile> images;
    @Exists(domainClass = Product.class, fieldName = "id", message = "O produto cadastrado não existe.")
    private Integer productId;


    public Integer getProductId() {
        return productId;
    }

    public List<MultipartFile> getImages() {
        return images;
    }

    public void setImages(List<MultipartFile> images) {
        this.images = images;
    }

    /* public ProductImageRequest() {
    }

    public ProductImageRequest(List<String> links, Integer productId) {
        this.links = links;
        this.productId = productId;
    }

    public List<String> getLinks() {
        return links;
    }

    public Integer getProductId() {
        return productId;
    }

    public ProductImage toModel(String link, Product product){
        return new ProductImage(link, product);
    }

    public List<ProductImage> toModelList(Product product){
       List<ProductImage> productImageList = new ArrayList<>();
       for (String link:this.links){
           productImageList.add(new ProductImage(link, product));
       }
       return productImageList;
    }*/

}
