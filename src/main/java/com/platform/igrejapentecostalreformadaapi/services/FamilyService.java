package com.platform.igrejapentecostalreformadaapi.services;

import com.platform.igrejapentecostalreformadaapi.data.vo.FamilyVO;
import com.platform.igrejapentecostalreformadaapi.entities.Family;
import com.platform.igrejapentecostalreformadaapi.entities.User;
import com.platform.igrejapentecostalreformadaapi.exceptions.ResourceAlreadyExistsException;
import com.platform.igrejapentecostalreformadaapi.exceptions.ResourceNotFoundException;
import com.platform.igrejapentecostalreformadaapi.mapper.FamilyMapper;
import com.platform.igrejapentecostalreformadaapi.repositories.FamilyRepository;
import com.platform.igrejapentecostalreformadaapi.repositories.UserRepository;
import com.platform.igrejapentecostalreformadaapi.utils.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class FamilyService {

    private final Logger logger = Logger.getLogger(FamilyService.class.getName());

    @Autowired
    private FamilyRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FamilyMapper mapper;

    public List<FamilyVO> findAll() throws Exception {

        logger.info("Finding all family!");

        List<Family> familyList = repository.findAll();

        return this.mapper.convertEntitiesToVOs(familyList);

    }

    public FamilyVO findById(Long id) {
        logger.info("Finding a family by Id");

        Family entity = repository
                .findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Família", "id", id)
                );

        return this.mapper.convertEntityToVO(entity);

    }

    public FamilyVO findByUserId(Long id) {
        logger.info("Finding a family by Id");

        Optional<Family> optionalEntity = repository.findByUserId(id);

        if (optionalEntity.isEmpty()) {
            throw new ResourceNotFoundException("Família", "id", id);
        }

        Family entity = optionalEntity.get();

        return this.mapper.convertEntityToVO(entity);

    }

    public FamilyVO create(FamilyVO familyVO, Long userId) {

        logger.info("Creating a family user data");

        Optional<User> user = this.userRepository.findById(userId);

        if (user.isEmpty()) {
            throw new ResourceNotFoundException("Usuário", "id", userId);
        }

        Optional<Family> optionalFamily = this.repository.findByUserId(userId);

        if (optionalFamily.isPresent()) {
            throw new ResourceAlreadyExistsException(Messages.FAMILY_IS_PRESENT_MESSAGE);
        }

        familyVO.setUser(user.get());

        Family family = this.mapper.convertVOToEntity(familyVO);

        Family newFamily = this.repository.save(family);

        return this.mapper.convertEntityToVO(newFamily);
    }

    public FamilyVO update(FamilyVO contactVO) {

        logger.info("Updating a family user data");

        var entity = this.repository.findById(contactVO.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Família", "id", contactVO.getId())
        );

        entity.setCivilStatus(contactVO.getCivilStatus());
        entity.setSpouseName(contactVO.getSpouseName());
        entity.setWeddingDate(contactVO.getWeddingDate());
        entity.setFatherName(contactVO.getFatherName());
        entity.setMotherName(contactVO.getMotherName());

        Family updatedContact = this.repository.save(entity);

        return this.mapper.convertEntityToVO(updatedContact);
    }
}
