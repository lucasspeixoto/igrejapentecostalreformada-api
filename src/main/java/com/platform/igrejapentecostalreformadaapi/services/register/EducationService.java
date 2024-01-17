package com.platform.igrejapentecostalreformadaapi.services.register;

import com.platform.igrejapentecostalreformadaapi.data.vo.register.EducationVO;
import com.platform.igrejapentecostalreformadaapi.entities.User;
import com.platform.igrejapentecostalreformadaapi.entities.register.Education;
import com.platform.igrejapentecostalreformadaapi.exceptions.ResourceAlreadyExistsException;
import com.platform.igrejapentecostalreformadaapi.exceptions.ResourceNotFoundException;
import com.platform.igrejapentecostalreformadaapi.mappers.register.EducationMapper;
import com.platform.igrejapentecostalreformadaapi.repositories.UserRepository;
import com.platform.igrejapentecostalreformadaapi.repositories.register.EducationRepository;
import com.platform.igrejapentecostalreformadaapi.utils.constants.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class EducationService {

    private final Logger logger = Logger.getLogger(EducationService.class.getName());

    @Autowired
    private EducationRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EducationMapper mapper;

    public List<EducationVO> findAll() throws Exception {

        logger.info("Finding all education!");

        List<Education> familyList = repository.findAll();

        return this.mapper.convertEntitiesToVOs(familyList);

    }

    public EducationVO findById(Long id) {
        logger.info("Finding a Education by Id");

        Education entity = repository
                .findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Educação", "id", id)
                );

        return this.mapper.convertEntityToVO(entity);

    }

    public EducationVO findByUserId(Long id) {
        logger.info("Finding a education by Id");

        Optional<Education> optionalEntity = repository.findByUserId(id);

        if (optionalEntity.isEmpty()) {
            throw new ResourceNotFoundException("Educação", "id", id);
        }

        Education entity = optionalEntity.get();

        return this.mapper.convertEntityToVO(entity);

    }

    public EducationVO create(EducationVO educationVO, Long userId) {

        logger.info("Creating a education user data");

        Optional<User> user = this.userRepository.findById(userId);

        if (user.isEmpty()) {
            throw new ResourceNotFoundException("Usuário", "id", userId);
        }

        Optional<Education> optionalEducation = this.repository.findByUserId(userId);

        if (optionalEducation.isPresent()) {
            throw new ResourceAlreadyExistsException(Messages.EDUCATION_IS_PRESENT_MESSAGE);
        }

        educationVO.setUser(user.get());

        Education education = this.mapper.convertVOToEntity(educationVO);

        Education newEducation = this.repository.save(education);

        return this.mapper.convertEntityToVO(newEducation);
    }

    public EducationVO update(EducationVO educationVO) {

        logger.info("Updating a education user data");

        var entity = this.repository.findById(educationVO.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Educação", "id", educationVO.getId())
        );

        entity.setScholarship(educationVO.getScholarship());
        entity.setProfession(educationVO.getProfession());

        Education updatedContact = this.repository.save(entity);

        return this.mapper.convertEntityToVO(updatedContact);
    }
}
