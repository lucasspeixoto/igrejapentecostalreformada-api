package com.platform.igrejapentecostalreformadaapi.services;

import com.platform.igrejapentecostalreformadaapi.data.vo.ImageVO;
import com.platform.igrejapentecostalreformadaapi.entities.Image;
import com.platform.igrejapentecostalreformadaapi.entities.User;
import com.platform.igrejapentecostalreformadaapi.exceptions.PlatformException;
import com.platform.igrejapentecostalreformadaapi.exceptions.ResourceAlreadyExistsException;
import com.platform.igrejapentecostalreformadaapi.exceptions.ResourceNotFoundException;
import com.platform.igrejapentecostalreformadaapi.mappers.ImageMapper;
import com.platform.igrejapentecostalreformadaapi.repositories.ImageRepository;
import com.platform.igrejapentecostalreformadaapi.repositories.UserRepository;
import com.platform.igrejapentecostalreformadaapi.utils.constants.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Autowired
    private ImageMapper mapper;

    public List<ImageVO> findAll() throws Exception {

        logger.info("Finding all images!");

        List<Image> images = this.imageRepository.findAll();

        return this.mapper.convertEntitiesToVOs(images);

    }

    public ImageVO createProfilePhoto(MultipartFile profilePhoto, Long userId) throws IOException {

        if (profilePhoto.isEmpty()) {
            throw new PlatformException(HttpStatus.BAD_REQUEST, Messages.PROFILE_PHOTO_IS_MISSING_MESSAGE);
        }

        Optional<User> user = this.userRepository.findById(userId);

        if (user.isEmpty()) {
            //! Usuário não existente na base
            throw new ResourceNotFoundException("Usuário", "id", userId);
        }

        Image image = new Image();

        image.setUser(user.get());

        Optional<Image> userSavedImage = this.imageRepository.findByUserId(userId);

        if (userSavedImage.isPresent()) {
            throw new ResourceAlreadyExistsException(Messages.PROFILE_PHOTO_IS_PRESENT_MESSAGE);
        } else {
            //! Obter Bytes da Imagem MultipartFile enviada e setar na foto de perfil da Imagem nova
            image.setProfilePhoto(profilePhoto.getBytes());

            Image savedImage = this.imageRepository.save(image);

            return this.mapper.convertEntityToVO(savedImage);
        }
    }

    public ImageVO updateProfilePhoto(MultipartFile profilePhoto, Long userId, Long imageId) throws IOException {

        if (profilePhoto.isEmpty()) {
            throw new PlatformException(HttpStatus.BAD_REQUEST, Messages.PROFILE_PHOTO_IS_MISSING_MESSAGE);
        }

        Image image = this.imageRepository.findById(imageId).orElseThrow(
                () -> new ResourceNotFoundException("Imagem", "id", imageId)
        );

        Optional<User> user = this.userRepository.findById(userId);

        if (user.isEmpty()) {
            //! Usuário não existente na base
            throw new ResourceNotFoundException("Usuário", "id", userId);
        }

        image.setProfilePhoto(profilePhoto.getBytes());

        Image updatedImage = this.imageRepository.save(image);

        return this.mapper.convertEntityToVO(updatedImage);
    }

    public ImageVO findById(Long id) {
        logger.info("Finding a image by Id");

        Image entity = this.imageRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Image", "id", id)
                );

        return this.mapper.convertEntityToVO(entity);

    }

    public ImageVO findByUserId(Long id) {
        logger.info("Finding a image by user id");

        Optional<Image> optionalEntity = this.imageRepository.findByUserId(id);

        if (optionalEntity.isEmpty()) {
            throw new ResourceNotFoundException("Imagem", "user id", id);
        }

        Image entity = optionalEntity.get();

        return this.mapper.convertEntityToVO(entity);

    }
}
