package cartify.shop.shopcartify.services.implementations;

import cartify.shop.shopcartify.dto.reqests.NewProductRequest;
import cartify.shop.shopcartify.dto.responses.NewProductResponse;
import cartify.shop.shopcartify.factory.QrCodeGenerator;
import cartify.shop.shopcartify.models.Product;
import cartify.shop.shopcartify.repositories.ProductRepository;
import cartify.shop.shopcartify.services.interfaces.ProductService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ShopCartifyProductService implements ProductService {

    private final ProductRepository productRepository;
    private final String imageQrcodePath ="C:\\Users\\USER\\Desktop\\ShopCartify\\ShopCartifyBackend\\src\\main\\java\\cartify\\shop\\shopcartify\\assets\\images\\";
    private final ModelMapper mapper = new ModelMapper();
    @Override
    public NewProductResponse addNewProduct(NewProductRequest newProductRequest) {

//        validator.validate(newProductRequest.getName());

        Product product = mapper.map(newProductRequest, Product.class);

        String text = createQrCodeText(product);

        QrCodeGenerator.generateImageQRCode(text, 300, 300,imageQrcodePath+product.getProductName());

        Product savedProduct = productRepository.save(product);

        NewProductResponse newProductResponse = new NewProductResponse();
        BeanUtils.copyProperties( savedProduct, newProductResponse);
        newProductResponse.setMessage("Product added successfully");

        return  newProductResponse;
    }

    private String createQrCodeText(Product product) {
        return "{\"name\": " + "\"" + product.getProductName() + "\"}";
    }

}
