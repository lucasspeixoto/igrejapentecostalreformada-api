package com.platform.igrejapentecostalreformadaapi.services;

import com.platform.igrejapentecostalreformadaapi.data.vo.UserProcessVO;
import com.platform.igrejapentecostalreformadaapi.entities.UserProcess;
import com.platform.igrejapentecostalreformadaapi.exceptions.ResourceAlreadyExistsException;
import com.platform.igrejapentecostalreformadaapi.exceptions.ResourceNotFoundException;
import com.platform.igrejapentecostalreformadaapi.mapper.UserProcessMapper;
import com.platform.igrejapentecostalreformadaapi.repositories.UserProcessRepository;
import jakarta.transaction.Transactional;
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
    private UserProcessMapper mapper;

    public List<UserProcess> findAll() {

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

         return this.mapper.convertEntityToVO(entity);
     }

    public UserProcessVO findByUserId(Long id) {
        logger.info("Finding a user process by Id");

        UserProcess entity = repository.findByUserId(id).orElseThrow(
                () -> new ResourceNotFoundException("User Process", "id", id)
        );

        return this.mapper.convertEntityToVO(entity);
    }

    public UserProcessVO create(UserProcessVO userProcessVO) {

        logger.info("Creating a user process data");

        this.repository.findByUserId(userProcessVO.getUserId()).orElseThrow(
                () -> new ResourceAlreadyExistsException("User already has a process registered")
        );


        UserProcess userProcess = this.mapper.convertVOToEntity(userProcessVO);

        UserProcess newUserProcess = this.repository.save(userProcess);

        return this.mapper.convertEntityToVO(newUserProcess);
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

        return this.mapper.convertEntityToVO(updatedUserProcess);
    }

    /**
     * O @Transaction é necessário, pois esta operação é
     * customizada, sem gerenciamento do Spring Data, o que
     * pode causar eventuais inconsistências no banco de dados
     */
    @Transactional
    public UserProcessVO setHasContact(Long id, Long userId) {
        logger.info("Set user has contact info");

        this.repository.setHasContact(userId);

        UserProcess entity = this.repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User Process", "id", id)
        );

        return this.mapper.convertEntityToVO(entity);

    }
}
