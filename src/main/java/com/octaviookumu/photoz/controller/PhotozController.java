package com.octaviookumu.photoz.controller;

import com.octaviookumu.photoz.model.Photo;
import com.octaviookumu.photoz.service.PhotozService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RestController
public class PhotozController {

    private final PhotozService photozService;

    public PhotozController(PhotozService photozService) {
        this.photozService = photozService;
    }

    @GetMapping("/")
    public String hello(){
        return "Hello";
    }

    @GetMapping("/api/photoz")
    Iterable<Photo> getPhotoz(){
        return photozService.getPhotoz();
    }

    @GetMapping("/api/photoz/{id}")
    public Photo getPhoto(@PathVariable Integer id){
        Photo photo = photozService.getPhoto(id);
        if(photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return photo;
    }

    @DeleteMapping("/api/photoz/{id}")
    public void deletePhoto(@PathVariable Integer id){
        photozService.removePhoto(id);
    }

    @PostMapping("/api/photoz")
    public Photo createPhoto(@RequestPart("data") MultipartFile file) throws IOException {
       return  photozService.savePhoto(file.getOriginalFilename(), file.getContentType(), file.getBytes());
    }
}
