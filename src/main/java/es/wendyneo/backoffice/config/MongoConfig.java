package es.wendyneo.backoffice.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Collection;
import java.util.Collections;

@Configuration
@EnableMongoRepositories(basePackages = "es.wendyneo.backoffice.repository")
public class MongoConfig extends AbstractMongoClientConfiguration {

    public static final String MONGO_URL_CONNECTION = "mongodb+srv://abogarill:0ZHw0VhUsd9Z6uiD@cluster0.etrsynj.mongodb.net/?retryWrites=true&w=majority";

    @Override
    protected String getDatabaseName() {
        return "wendyneo-admin";
    }

    @Override
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString(MONGO_URL_CONNECTION);
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Override
    public Collection getMappingBasePackages() {
        return Collections.singleton("es.wendyneo.backoffice.model");
    }
}