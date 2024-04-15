package com.octaviookumu.photoz.controller;

import com.octaviookumu.photoz.model.Photo;
import com.octaviookumu.photoz.service.PhotozService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class DownloadController {

    private final PhotozService photozService;

    public DownloadController(PhotozService photozService) {
        this.photozService = photozService;
    }

    @GetMapping("/api/download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable Integer id){

        Photo photo = photozService.getPhoto(id);

        if(photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);


        byte[] data = photo.getData();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(photo.getContentType()));

        // send a header with filename
        // whether to display photo in browser or download it
        ContentDisposition build = ContentDisposition.builder("attachment")
                .filename(photo.getFileName())
                .build();
        headers.setContentDisposition(build);

        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }
}
