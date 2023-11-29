package com.platform.igrejapentecostalreformadaapi.services;

import com.platform.igrejapentecostalreformadaapi.data.vo.ContactVO;
import com.platform.igrejapentecostalreformadaapi.data.vo.UserProcessVO;
import com.platform.igrejapentecostalreformadaapi.entities.Contact;
import com.platform.igrejapentecostalreformadaapi.entities.UserProcess;
import com.platform.igrejapentecostalreformadaapi.exceptions.ResourceNotFoundException;
import com.platform.igrejapentecostalreformadaapi.mapper.PlatformMapper;
import com.platform.igrejapentecostalreformadaapi.repositories.UserProcessRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class UserProcessService {

    private final Logger logger = Logger.getLogger(UserProcessService.class.getName());

    @Autowired
    private UserProcessRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public List<UserProcess> findAll() throws Exception {

        logger.info("Finding all user process data!");

        return repository.findAll();

    }

     public UserProcessVO findById(Long id) {
         logger.info("Finding a user process by Id");

         UserProcess entity = repository
            .findById(id)
            .orElseThrow(
         () -> new ResourceNotFoundException("User Process", "id", id)
         );

         return this.convertEntityToDTO(entity);

     }

    public UserProcessVO create(UserProcessVO userProcessVO) {

        logger.info("Creating a contact user data");

        // Create Contact data
        UserProcess userProcess = this.convertDTOToEntity(userProcessVO);

        UserProcess newUserProcess = this.repository.save(userProcess);

        return this.convertEntityToDTO(newUserProcess);
    }

    public UserProcessVO update(UserProcessVO userProcessVO) {

        logger.info("Updating a contact user data");


        var entity = this.repository.findById(userProcessVO.getId()).orElseThrow(
                () -> new ResourceNotFoundException("User Process", "id", userProcessVO.getId())
        );

        entity.setHasAddress(userProcessVO.getHasAddress());
        entity.setHasBaptism(userProcessVO.getHasBaptism());
        entity.setHasContact(userProcessVO.getHasContact());
        entity.setHasDocument(userProcessVO.getHasDocument());
        entity.setHasEducation(userProcessVO.getHasEducation());
        entity.setHasFamily(userProcessVO.getHasFamily());
        entity.setHasMember(userProcessVO.getHasMember());
        entity.setUserId(userProcessVO.getUserId());

        UserProcess updatedUserProcess = this.repository.save(entity);

        return this.convertEntityToDTO(updatedUserProcess);

    }

    public UserProcessVO findByUserId(Long id) {
        logger.info("Finding a user process by Id");

        UserProcess entity = repository.findByUserId(id).orElseThrow(
                () -> new ResourceNotFoundException("User Procecss", "id", id)
        );

        return this.convertEntityToDTO(entity);

    }


    //! Mapper methods ---------------------------------------------------------------------------
    private UserProcessVO convertEntityToDTO(UserProcess entity) {
        return PlatformMapper.parseObject(entity, UserProcessVO.class, modelMapper);
    }

    private UserProcess convertDTOToEntity(UserProcessVO userProcessVO) {
        return PlatformMapper.parseObject(userProcessVO, UserProcess.class, modelMapper);
    }

    private List<UserProcessVO> convertEntitiesToDTOs(List<UserProcess> entities) {
        return PlatformMapper.parseListObjects(entities, UserProcessVO.class, modelMapper);
    }

    private List<Contact> convertDTOsToEntities(List<ContactVO> products) {
        return PlatformMapper.parseListObjects(products, Contact.class, modelMapper);
    }
    //! --------------------------------------------------------------------------- Mapper methods
}
