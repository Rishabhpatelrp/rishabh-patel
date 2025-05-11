package com.rishabh.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.rishabh.dynamodb.model.ProductInfo;
import com.rishabh.dynamodb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ProductInfo createProduct(@RequestBody ProductInfo productInfo){
        return productService.createProduct(productInfo);
    }

    @GetMapping("{id}")
    public ProductInfo getProduct(
            @PathVariable(name = "id") String id,
            @RequestParam(name = "orderDate") Long orderDate){
        return productService.getProductById(id, orderDate);
    }

    @GetMapping("/scan")
    public PaginatedScanList<ProductInfo> scanProductInfo(
            @RequestParam(name = "lastKey", required = false) String lastKey) {

        Map<String, AttributeValue> map = null;

        if(Objects.nonNull(lastKey)){
            map = Map.of("id", new AttributeValue().withS(lastKey));
        }

        return productService.scanProductInfo(map, 10);
    }

    @GetMapping("/query")
    public PaginatedQueryList<ProductInfo> queryProductInfo(
            @RequestParam(name = "order") Long order,
            @RequestParam(name = "pageSize") int pageSize) {
        return productService.queryProductInfo(order, pageSize);
    }

}
