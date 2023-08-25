package com.slip.user;

import org.bson.types.ObjectId;
import org.springframework.boot.SpringApplication;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.GridFSUploadStream;
import org.bson.BsonValue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
