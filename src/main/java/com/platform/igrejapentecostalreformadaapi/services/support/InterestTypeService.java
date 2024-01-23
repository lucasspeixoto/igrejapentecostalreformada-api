package com.platform.igrejapentecostalreformadaapi.services.support;

import com.platform.igrejapentecostalreformadaapi.data.vo.support.InterestTypeVO;
import com.platform.igrejapentecostalreformadaapi.entities.support.InterestType;
import com.platform.igrejapentecostalreformadaapi.exceptions.ResourceNotFoundException;
import com.platform.igrejapentecostalreformadaapi.mappers.support.InterestTypeMapper;
import com.platform.igrejapentecostalreformadaapi.repositories.support.InterestTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class InterestTypeService {

    private final Logger logger = Logger.getLogger(InterestTypeService.class.getName());

    @Autowired
    private InterestTypeRepository repository;

    @Autowired
    private InterestTypeMapper mapper;

    public List<InterestTypeVO> findAll() throws Exception {

        logger.info("Finding all Interest Type!");

        List<InterestType> entities = repository.findAll();

        return this.mapper.convertEntitiesToVOs(entities);

    }

    public InterestTypeVO findById(Long id) {
        logger.info("Finding a Interest Type by Id");

        InterestType entity = repository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Principal interesse", "id", id)
                );

        return this.mapper.convertEntityToVO(entity);

    }

    public InterestTypeVO create(InterestTypeVO interestTypeVO) {

        logger.info("Creating a Interest Type");

        InterestType entity = this.mapper.convertVOToEntity(interestTypeVO);

        InterestType savedEntity = this.repository.save(entity);

        return this.mapper.convertEntityToVO(savedEntity);
    }

    public InterestTypeVO update(InterestTypeVO craftType) {

        logger.info("Updating a Interest Type");

        var entity = this.repository.findById(craftType.getId())
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Principal interesse",
                                "id",
                                craftType.getId()
                        )
                );

        entity.setInterestType(craftType.getInterestType());

        InterestType updatedEntity = this.repository.save(entity);

        return this.mapper.convertEntityToVO(updatedEntity);
    }

    public InterestTypeVO delete(Long id) {

        logger.info("Deleting a Interest Type");

        var entity = this.repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Principal interesse", "id", id)
        );

        this.repository.delete(entity);

        return this.mapper.convertEntityToVO(entity);
    }

}
