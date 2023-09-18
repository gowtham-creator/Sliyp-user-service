package com.slip.user.service.impl;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.GridFSUploadStream;
import org.bson.types.ObjectId;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageServiceImpl {
    public void uploadImage() throws IOException {
            try {

                MongoClient client = MongoClients.create("mongodb://localhost:27017");

                // Get the database and GridFSBucket
                GridFSBucket gridFSBucket = GridFSBuckets.create(client.getDatabase("slipUserImages"));

                // Specify the path to your image file
                Path imagePath = Paths.get("/Users/shashikanth/Downloads/64588.jpg");

                // Open the image file
                FileInputStream imageStream = new FileInputStream(imagePath.toFile());

                // Upload the image to GridFS
                GridFSUploadStream uploadStream = gridFSBucket.openUploadStream("my_image.jpg");

                int bytesRead;
                byte[] buffer = new byte[1024];
                while ((bytesRead = imageStream.read(buffer)) != -1) {
                    uploadStream.write(buffer, 0, bytesRead);
                }
                uploadStream.close();

                // Get the ID of the stored image

                ObjectId storedFileId = uploadStream.getObjectId();
                System.out.println("Image stored with ID: " + storedFileId);

                client.close();
            }catch (Exception ex){
                System.out.println(ex.getMessage());
            }

    }
}
