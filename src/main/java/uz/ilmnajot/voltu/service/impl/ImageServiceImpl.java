package uz.ilmnajot.voltu.service.impl;

import org.springframework.stereotype.Service;
import uz.ilmnajot.voltu.repository.ImageRepository;
import uz.ilmnajot.voltu.service.ImageService;
@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }
}
