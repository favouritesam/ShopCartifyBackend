package cartify.shop.shopcartify.services.implementations;

import cartify.shop.shopcartify.dto.reqests.NewProductRequest;
import cartify.shop.shopcartify.dto.reqests.UpdateProductDetailRequest;
import cartify.shop.shopcartify.dto.responses.NewProductResponse;
import cartify.shop.shopcartify.dto.responses.UpdateProductDetailResponse;
import cartify.shop.shopcartify.exceptions.ProductNotFoundException;
import cartify.shop.shopcartify.factory.QrCodeGeneratorFactory;
import cartify.shop.shopcartify.models.Product;
import cartify.shop.shopcartify.replica.ProductReplica;
import cartify.shop.shopcartify.repositories.ProductRepository;
import cartify.shop.shopcartify.services.interfaces.ProductService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@AllArgsConstructor
@Service
public class ShopCartifyProductService implements ProductService {

    private final ProductRepository productRepository;
//    private final CloudService cloudService;
    private final String imageQrcodePath ="C:\\Users\\USER\\Desktop\\ShopCartify\\ShopCartifyBackend\\src\\main\\java\\cartify\\shop\\shopcartify\\assets\\images\\";
    private final ModelMapper mapper = new ModelMapper();
    @Override
    public NewProductResponse addNewProduct(NewProductRequest newProductRequest) {

//        validator.validate(newProductRequest.getName());


        Product product = mapper.map(newProductRequest, Product.class);

        String text = createQrCodeText(product);

        QrCodeGeneratorFactory.generateImageQRCode(text, 300, 300,imageQrcodePath + "generalPicturePath");

        Product savedProduct = productRepository.save(product);

        NewProductResponse newProductResponse = new NewProductResponse();
        BeanUtils.copyProperties( savedProduct, newProductResponse);
        newProductResponse.setMessage("Product added successfully");

        return  newProductResponse;
    }

    @Override
    public UpdateProductDetailResponse updateProductDetail(UpdateProductDetailRequest updateProductDetailRequest) {
        Product foundProduct = findProductByProductName(updateProductDetailRequest.getProductName());

        foundProduct.setProductPrice(updateProductDetailRequest.getProductPrice());
        Product product = productRepository.save(foundProduct);

        UpdateProductDetailResponse updateProductDetailResponse = mapper.map(product, UpdateProductDetailResponse.class);
        return updateProductDetailResponse;
    }
    @Override
    public Product findProductByProductName(String productName) {
        Product foundProduct = productRepository.findProductByProductName(productName)
                .orElseThrow(() ->
                        new ProductNotFoundException( productName + " is not found"));

        return foundProduct;
    }
    @Override
    public ProductReplica findProductReplicaByProductName(String productName) {
        Product foundProduct = productRepository.findProductByProductName(productName)
                                                .orElseThrow(() ->
                                                    new ProductNotFoundException( productName + " is not found"));
        ProductReplica productReplica = mapper.map(foundProduct, ProductReplica.class);

        return productReplica;
    }

    private String upLoadQrCode(){
        String videoPath = "";

        Path testPath = Paths.get(videoPath);
        try(InputStream inputStream = Files.newInputStream(testPath)){
//            MultipartFile multipartFile = new MultipartFile("test",inputStream);
//            String url = cloudService.upload(multipartFile);
            System.out.println("test to be deleted");
        } catch (IOException exception){
            throw new RuntimeException("Media upload failed");
        }
        return null;
    }

    private String createQrCodeText(Product product) {
        return "{\"name\": " + "\"" + product.getProductName() + "\"}";
    }

}
