package com.rishabh.dynamodb.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamoDBConfig {

    @Value("${amazon.dynamodb.endpoint}")
    private String amazonDynamoDBEndPoint;

    @Value("${aws.accessKeyId}")
    private String amazonAWSAccessKey;

    @Value("${aws.secretKey}")
    private String amazonAWSSecretKey;

    @Value("${amazon.aws.region}")
    private String amazonAWSRegion;


//    private AWSCredentialsProvider awsCredentialsProvider() {
//        return new AWSStaticCredentialsProvider(awsCredentials());
//    }
//
//    @Bean
//    public AmazonDynamoDB amazonDynamoDB(){
//                return AmazonDynamoDBClientBuilder.standard()
//                        .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(amazonDynamoDBEndPoint, Regions.AP_SOUTH_1.getName()))

    /// /                .withCredentials(awsCredentialsProvider())
//                .build();
//    }
//
//    @Bean
//    public DynamoDbClient dynamoDbClient() {
//        AmazonDynamoDB amazonDynamoDB = new AmazonDynamoDBClient(awsCredentials());
//
//        if (!StringUtils.isNullOrEmpty(amazonDynamoDBEndPoint)) {
//            amazonDynamoDB.setEndpoint(amazonDynamoDBEndPoint);
//        }
//
//        return amazonDynamoDB;

//        return AmazonDynamoDBClientBuilder.standard()
//                .withCredentials(awsCredentialsProvider())
//                .withRegion(Regions.AP_SOUTH_1)
//                .build();

//        return DynamoDbClient.builder()
//                .endpointOverride(URI.create(amazonDynamoDBEndPoint))
//                .region(Region.of(amazonAWSRegion))
//                .credentialsProvider(
//                        StaticCredentialsProvider.create(AwsBasicCredentials.create(amazonAWSAccessKey, amazonAWSSecretKey))
//                )
//                .build();
//    }


//    private AWSCredentials awsCredentials() {
//        return new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
//    }
//
//    @Bean
//    public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB) {
//        return new DynamoDBMapper(AmazonDynamoDBClientBuilder.standard()
//                .withEndpointConfiguration(new AwsClientBuilder
//                        .EndpointConfiguration("http://localhost:8000/", "ap-south-1"))
//                .build());
//
//
//    }
//
//    @Bean
//    public DynamoDbEnhancedClient enhancedClient(DynamoDbClient client) {
//        return DynamoDbEnhancedClient.builder()
//                .dynamoDbClient(client)
//                .build();
//    }


//    DynamoDbClient dynamoDb() {
//        final String endpoint = System.getenv("ENDPOINT_OVERRIDE");
//
//        DynamoDbClientBuilder builder = DynamoDbClient.builder();
//        builder.httpClient(ApacheHttpClient.builder().build());
//        if (endpoint != null && !endpoint.isEmpty()) {
//            builder.endpointOverride(URI.create(endpoint));
//        }
//
//        return builder.build();
//    }
    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        return AmazonDynamoDBClientBuilder.standard()
//            .withEndpointConfiguration(
//                    new AwsClientBuilder.EndpointConfiguration(amazonDynamoDBEndPoint, amazonAWSRegion)
//            )
                .withCredentials(
                        new AWSStaticCredentialsProvider(new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey))
                )
                .withRegion(Regions.EU_NORTH_1)
                .build();
    }

    @Bean
    public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB) {
        return new DynamoDBMapper(amazonDynamoDB);
    }
}
