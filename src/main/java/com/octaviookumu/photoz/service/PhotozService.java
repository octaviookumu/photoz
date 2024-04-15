package com.octaviookumu.photoz.service;

import com.octaviookumu.photoz.model.Photo;
import com.octaviookumu.photoz.repository.PhotozRepository;
import org.springframework.stereotype.Service;

@Service
public class PhotozService {

    private final PhotozRepository photozRepository;

    public PhotozService(PhotozRepository photozRepository) {
        this.photozRepository = photozRepository;
    }

    public Iterable<Photo> getPhotoz(){
        return photozRepository.findAll();
    }

    public Photo getPhoto(Integer id){
        return photozRepository.findById(id).orElse(null);
    }

    public void removePhoto(Integer id) {
        photozRepository.deleteById(id);
    }

    public Photo savePhoto(String fileName, String contentType, byte[] data) {
        Photo photo = new Photo();
        photo.setFileName(fileName);
        photo.setContentType(contentType);
        photo.setData(data);
        photozRepository.save(photo);
        return photo;
    }
}
