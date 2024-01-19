package com.platform.igrejapentecostalreformadaapi.services.register;

import com.platform.igrejapentecostalreformadaapi.data.vo.register.BaptismVO;
import com.platform.igrejapentecostalreformadaapi.entities.User;
import com.platform.igrejapentecostalreformadaapi.entities.register.Baptism;
import com.platform.igrejapentecostalreformadaapi.exceptions.ResourceAlreadyExistsException;
import com.platform.igrejapentecostalreformadaapi.exceptions.ResourceNotFoundException;
import com.platform.igrejapentecostalreformadaapi.mappers.register.BaptismMapper;
import com.platform.igrejapentecostalreformadaapi.repositories.UserRepository;
import com.platform.igrejapentecostalreformadaapi.repositories.register.BaptismRepository;
import com.platform.igrejapentecostalreformadaapi.utils.constants.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class BaptismService {

    private final Logger logger = Logger.getLogger(BaptismService.class.getName());

    @Autowired
    private BaptismRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BaptismMapper mapper;

    public List<BaptismVO> findAll() throws Exception {

        logger.info("Finding all baptism!");

        List<Baptism> familyList = repository.findAll();

        return this.mapper.convertEntitiesToVOs(familyList);

    }

    public BaptismVO findById(Long id) {
        logger.info("Finding a Baptism by Id");

        Baptism entity = repository
                .findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Batismo", "id", id)
                );

        return this.mapper.convertEntityToVO(entity);

    }

    public BaptismVO findByUserId(Long id) {
        logger.info("Finding a Baptism by Id");

        Optional<Baptism> optionalEntity = repository.findByUserId(id);

        if (optionalEntity.isEmpty()) {
            throw new ResourceNotFoundException("Batismo", "id", id);
        }

        Baptism entity = optionalEntity.get();

        return this.mapper.convertEntityToVO(entity);

    }

    public BaptismVO create(BaptismVO familyVO, Long userId) {

        logger.info("Creating a family user data");

        Optional<User> user = this.userRepository.findById(userId);

        if (user.isEmpty()) {
            throw new ResourceNotFoundException("Usuário", "id", userId);
        }

        Optional<Baptism> optionalFamily = this.repository.findByUserId(userId);

        if (optionalFamily.isPresent()) {
            throw new ResourceAlreadyExistsException(Messages.FAMILY_IS_PRESENT_MESSAGE);
        }

        familyVO.setUser(user.get());

        Baptism baptism = this.mapper.convertVOToEntity(familyVO);

        Baptism newBaptism = this.repository.save(baptism);

        return this.mapper.convertEntityToVO(newBaptism);
    }

    public BaptismVO update(BaptismVO baptismVO) {

        logger.info("Updating a Baptism user data");


        var entity = this.repository.findById(baptismVO.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Batismo", "id", baptismVO.getId())
        );

        entity.setBaptismDate(baptismVO.getBaptismDate());
        entity.setBaptismPastor(baptismVO.getBaptismPastor());
        entity.setBaptized(baptismVO.getBaptized());

        Baptism updatedContact = this.repository.save(entity);

        return this.mapper.convertEntityToVO(updatedContact);
    }
}
