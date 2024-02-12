package com.platform.igrejapentecostalreformadaapi.services;

import com.platform.igrejapentecostalreformadaapi.data.vo.indicators.UserProcessVO;
import com.platform.igrejapentecostalreformadaapi.entities.User;
import com.platform.igrejapentecostalreformadaapi.entities.indicators.UserProcess;
import com.platform.igrejapentecostalreformadaapi.exceptions.ResourceAlreadyExistsException;
import com.platform.igrejapentecostalreformadaapi.exceptions.ResourceNotFoundException;
import com.platform.igrejapentecostalreformadaapi.mappers.indicators.UserProcessMapper;
import com.platform.igrejapentecostalreformadaapi.repositories.indicators.UserProcessRepository;
import com.platform.igrejapentecostalreformadaapi.repositories.UserRepository;
import com.platform.igrejapentecostalreformadaapi.utils.constants.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class UserProcessService {

    private final Logger logger = Logger.getLogger(UserProcessService.class.getName());

    @Autowired
    private UserProcessRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProcessMapper mapper;

    public List<UserProcessVO> findAll() {

        logger.info("Finding all user process data!");

        List<UserProcess> userProcessList = repository.findAll();

        return this.mapper.convertEntitiesToVOs(userProcessList);
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
                () -> new ResourceNotFoundException("Dados de Processo", "id", id)
        );

        return this.mapper.convertEntityToVO(entity);
    }

    public UserProcessVO create(UserProcessVO userProcessVO, Long userId) {

        logger.info("Creating a user process data");

        Optional<User> user = this.userRepository.findById(userId);

        if (user.isEmpty()) {
            throw new ResourceNotFoundException("Usuário", "id", userId);
        }

        Optional<UserProcess> optionalUserProcess = this.repository.findByUserId(userId);

        if (optionalUserProcess.isPresent()) {
            throw new ResourceAlreadyExistsException(Messages.PROCESS_IS_PRESENT_MESSAGE);
        }

        userProcessVO.setUser(user.get());

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

        UserProcess updatedUserProcess = this.repository.save(entity);

        return this.mapper.convertEntityToVO(updatedUserProcess);
    }

    /**
     * O @Transaction é necessário, pois esta operação é
     * customizada, sem gerenciamento do Spring Data, o que
     * pode causar eventuais inconsistências no banco de dados

     @Transactional public UserProcessVO setHasContact(Long id, Long userId) {
     logger.info("Set user has contact info");

     this.repository.setHasContact(userId);

     UserProcess entity = this.repository.findById(id).orElseThrow(
     () -> new ResourceNotFoundException("User Process", "id", id)
     );

     return this.mapper.convertEntityToVO(entity);

     }
     */
}
