package com.mongo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;

@Configuration
public class MongoConfig extends AbstractMongoConfiguration{
	private static final Logger log = LoggerFactory.getLogger(MongoConfig.class);	
	@Value("${mongo.ip}")
	private String ip;
	@Value("${mongo.port}")
	private Integer port;
	@Value("${mongo.db}")
	private String db;

	@Override
	public MongoClient mongoClient() {	
		log.info(String.format("Se intenta crear una conexion a la ip: %s y puerto: %s", ip, port));
		return new MongoClient(ip, port);
	}

	@Override
	protected String getDatabaseName() {		
		return db;
	}
	
	@Bean(name = "MiMongoTemplate")
	public MongoTemplate mongoTemplate() {
		return new MongoTemplate(mongoClient(), getDatabaseName());
	}
}
