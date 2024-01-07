package com.platform.igrejapentecostalreformadaapi.services;

import com.platform.igrejapentecostalreformadaapi.entities.Image;
import com.platform.igrejapentecostalreformadaapi.entities.User;
import com.platform.igrejapentecostalreformadaapi.exceptions.ResourceNotFoundException;
import com.platform.igrejapentecostalreformadaapi.repositories.ImageRepository;
import com.platform.igrejapentecostalreformadaapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ImageService {

    private final Logger logger = Logger.getLogger(ImageService.class.getName());

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Image> findAll() throws Exception {

        logger.info("Finding all images!");

        return this.imageRepository.findAll();

    }

    public Image saveProfilePhoto(MultipartFile file, Long userId) throws IOException {
        Image image = new Image();

        image.setProfilePhoto(file.getBytes());

        Optional<User> user = this.userRepository.findById(userId);

        if (user.isEmpty()) {
            throw new ResourceNotFoundException("Usuário", "id", userId);
        }

        image.setUser(user.get());

        this.imageRepository.save(image);

        return image;
    }
}
