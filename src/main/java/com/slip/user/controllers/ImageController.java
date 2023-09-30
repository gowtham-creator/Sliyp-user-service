package com.slip.user.controllers;

import com.slip.user.Models.mongodb.Image;
import com.slip.user.Models.mongodb.ImageType;
import com.slip.user.service.ImageService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin
@RequestMapping("/api/v1/image")
public class ImageController {
    public final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping()
       public Image uploadUserImage( @RequestParam("file")  MultipartFile image,
                                     @RequestParam(value = "img-type",required = false) ImageType imageType,
                                     @RequestParam(value = "post-ref",required = false) String postRef) throws IOException {
             return imageService.uploadImage(Image.builder()
                                .filename(image.getOriginalFilename())
                                .imageType(imageType)
                                 .postRef(postRef)
                                .contentType(image.getContentType())
                                .file(image.getBytes())
                     .build());
       }

    @GetMapping()
    public ResponseEntity<byte[]> downloadUserImage(@RequestParam String imageRefId){
        final Image image =imageService.downloadImage(imageRefId);

        return ResponseEntity.ok()
                .contentType(Optional.of(MediaType.valueOf(image.getContentType())).orElse(MediaType.ALL))
                .header(HttpHeaders.CONTENT_DISPOSITION)
                .body(image.getFile());
    }
    @GetMapping("/all")
    public List<Image> downloadUserImage(){
        return imageService.findAllObj();
    }
}
