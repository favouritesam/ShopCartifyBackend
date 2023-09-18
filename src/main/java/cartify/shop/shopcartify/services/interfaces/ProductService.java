package cartify.shop.shopcartify.services.interfaces;

import cartify.shop.shopcartify.dto.reqests.NewProductRequest;
import cartify.shop.shopcartify.dto.reqests.UpdateProductDetailRequest;
import cartify.shop.shopcartify.dto.responses.NewProductResponse;
import cartify.shop.shopcartify.dto.responses.UpdateProductDetailResponse;
import cartify.shop.shopcartify.models.Product;
import cartify.shop.shopcartify.replica.ProductReplica;

public interface ProductService {
    NewProductResponse addNewProduct(NewProductRequest productRequest);

    UpdateProductDetailResponse updateProductDetail(UpdateProductDetailRequest updateProductDetailRequest);

    abstract Product findProductByProductName(String productName);

    ProductReplica findProductReplicaByProductName(String productName);
}
