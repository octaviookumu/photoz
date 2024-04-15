package com.octaviookumu.photoz.repository;

import com.octaviookumu.photoz.model.Photo;
import org.springframework.data.repository.CrudRepository;

public interface PhotozRepository extends CrudRepository<Photo, Integer> {
}
