package com.rishabh.dynamodb;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.rishabh.dynamodb.model.ProductInfo;
import com.rishabh.dynamodb.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.web.WebAppConfiguration;

//@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DynamodbApplication.class)
@WebAppConfiguration
@ActiveProfiles("local")
@TestPropertySource(properties = {
        "amazon.dynamodb.endpoint=http://localhost:8000/",
        "aws.accessKeyId=abc",
        "aws.secretKey=def",
        "amazon.aws.region=ap-south-1"})
public class ProductInfoRepositoryIntegrationTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

//    private AmazonDynamoDB dynamoDB;
//    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private DynamoDBMapper dynamoDBMapper;


    private static final String EXPECTED_COST = "20";
    private static final String EXPECTED_PRICE = "50";

    @BeforeEach
    public void setup() throws Exception {

//        AmazonDynamoDB dynamoDB = AmazonDynamoDBClientBuilder.standard()
//                .withEndpointConfiguration(new AwsClientBuilder
//                        .EndpointConfiguration("http://localhost:8000/", "ap-south-1"))
//                .build();
//
//        dynamoDBMapper = new DynamoDBMapper(dynamoDB);
//
        CreateTableRequest tableRequest = dynamoDBMapper
                .generateCreateTableRequest(ProductInfo.class);
        tableRequest.setProvisionedThroughput(
                new ProvisionedThroughput(1L, 1L));
        amazonDynamoDB.createTable(tableRequest);


    }

    @Test
    public void givenItemWithExpectedCost_whenRunFindAll_thenItemIsFound() {
//        ProductInfo productInfo = new ProductInfo(EXPECTED_PRICE, EXPECTED_COST);
//        ProductInfo product = productService.createProduct(productInfo);
//        List<ProductInfo> result = (List<ProductInfo>) repository.findAll();
//
//        Assertions.assertNotNull(product);
//        Assertions.assertEquals(EXPECTED_COST, product.getCost());
//        Assertions.assertThat(result.size(), Matchers.is(Matchers.greaterThan(0)));
//        Assertions.assertThat(result.get(0).getCost(), is(CoreMatchers.equalTo(EXPECTED_COST)));
    }
}