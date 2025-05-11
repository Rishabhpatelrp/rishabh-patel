package com.rishabh.dynamodb.service;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.rishabh.dynamodb.model.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public ProductInfo createProduct(ProductInfo productInfo) {
        log.info("productInfo [{}]", productInfo);
        dynamoDBMapper.save(productInfo);
        return getProductById(productInfo.getId(), productInfo.getOrder());
    }

    public ProductInfo getProductById(String id, Long orderDate ) {
        return dynamoDBMapper.load(ProductInfo.class, id, orderDate);
    }

    public PaginatedScanList<ProductInfo> scanProductInfo(Map<String, AttributeValue> exclusiveStartKey, int pageSize) {

        DynamoDBScanExpression dynamoDBScanExpression = new DynamoDBScanExpression()
                .withLimit(pageSize);

        if (Objects.nonNull(exclusiveStartKey)){
            dynamoDBScanExpression
                    .withExclusiveStartKey(exclusiveStartKey);
        }


        PaginatedScanList<ProductInfo> productInfos = dynamoDBMapper.scan(ProductInfo.class, dynamoDBScanExpression);
        productInfos.loadAllResults();

        return productInfos;
//        productInfos.

    }

    public PaginatedQueryList<ProductInfo> queryProductInfo(Long order,int pageSize) {
        Map<String, String> ean = new HashMap<>();
        ean.put("#ord", "order"); //

        Map<String, AttributeValue> eav = new HashMap<>();
//        eav.put(":userId", new AttributeValue().withS("user123"));

        eav.put(":val", new AttributeValue().withS(order.toString()));

        DynamoDBQueryExpression<ProductInfo> productInfoDynamoDBQueryExpression = new DynamoDBQueryExpression<ProductInfo>()
                .withKeyConditionExpression("#ord >= :val")
                .withLimit(pageSize)
                .withExpressionAttributeValues(eav)
                .withExpressionAttributeNames(ean);

        return dynamoDBMapper.query(ProductInfo.class, productInfoDynamoDBQueryExpression);
    }


    }
