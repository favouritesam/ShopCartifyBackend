package cartify.shop.shopcartify.services.interfaces;

import cartify.shop.shopcartify.dto.reqests.NewProductRequest;
import cartify.shop.shopcartify.dto.responses.NewProductResponse;

public interface ProductService {
    NewProductResponse addNewProduct(NewProductRequest productRequest);
}
