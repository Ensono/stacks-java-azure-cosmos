package com.amido.stacks.cosmosdb.config;

import com.azure.core.credential.AzureKeyCredential;
import com.azure.cosmos.CosmosClientBuilder;
import com.azure.cosmos.DirectConnectionConfig;
import com.azure.cosmos.GatewayConnectionConfig;
import com.azure.spring.data.cosmos.config.AbstractCosmosConfiguration;
import com.azure.spring.data.cosmos.config.CosmosConfig;
import com.azure.spring.data.cosmos.repository.config.EnableCosmosRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnExpression(value = "${cosmos.enabled:true}")
@EnableCosmosRepositories(basePackages = "com.amido.stacks.workloads.menu.repository")
public class CosmosDbConfig extends AbstractCosmosConfiguration {

  @Value(value = "${spring.data.cosmos.uri}")
  private String uri;

  @Value(value = "${spring.data.cosmos.key}")
  private String key;

  @Value(value = "${spring.data.cosmos.databaseName}")
  private String databaseName;

  @Bean
  public CosmosClientBuilder cosmosClientBuilder() {
    return new CosmosClientBuilder()
        .endpoint(uri)
        .credential(new AzureKeyCredential(key))
        .directMode(new DirectConnectionConfig(), new GatewayConnectionConfig());
  }

  @Override
  public CosmosConfig cosmosConfig() {
    return CosmosConfig.builder()
        .enableQueryMetrics(false)
        .build();
  }

  @Override
  protected String getDatabaseName() {
    return databaseName;
  }
}
