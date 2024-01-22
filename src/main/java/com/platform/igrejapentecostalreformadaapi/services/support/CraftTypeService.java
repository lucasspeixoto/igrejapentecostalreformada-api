package com.platform.igrejapentecostalreformadaapi.services.support;

import com.platform.igrejapentecostalreformadaapi.data.vo.support.CraftTypeVO;
import com.platform.igrejapentecostalreformadaapi.data.vo.support.MembershipTypeVO;
import com.platform.igrejapentecostalreformadaapi.entities.support.CraftType;
import com.platform.igrejapentecostalreformadaapi.entities.support.MembershipType;
import com.platform.igrejapentecostalreformadaapi.exceptions.ResourceNotFoundException;
import com.platform.igrejapentecostalreformadaapi.mappers.support.CraftTypeMapper;
import com.platform.igrejapentecostalreformadaapi.mappers.support.MembershipTypeMapper;
import com.platform.igrejapentecostalreformadaapi.repositories.support.CraftTypeRepository;
import com.platform.igrejapentecostalreformadaapi.repositories.support.MembershipTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class CraftTypeService {

    private final Logger logger = Logger.getLogger(CraftTypeService.class.getName());

    @Autowired
    private CraftTypeRepository repository;

    @Autowired
    private CraftTypeMapper mapper;

    public List<CraftTypeVO> findAll() throws Exception {

        logger.info("Finding all Craft Type!");

        List<CraftType> entities = repository.findAll();

        return this.mapper.convertEntitiesToVOs(entities);

    }

    public CraftTypeVO findById(Long id) {
        logger.info("Finding a Craft Type by Id");

        CraftType entity = repository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Ofício", "id", id)
                );

        return this.mapper.convertEntityToVO(entity);

    }

    public CraftTypeVO create(CraftTypeVO craftTypeVO) {

        logger.info("Creating a Craft Type");

        CraftType entity = this.mapper.convertVOToEntity(craftTypeVO);

        CraftType savedEntity = this.repository.save(entity);

        return this.mapper.convertEntityToVO(savedEntity);
    }

    public CraftTypeVO update(CraftTypeVO craftType) {

        logger.info("Updating a Craft Type");

        var entity = this.repository.findById(craftType.getId())
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Ofício",
                                "id",
                                craftType.getId()
                        )
                );

        entity.setCraftType(craftType.getCraftType());

        CraftType updatedEntity = this.repository.save(entity);

        return this.mapper.convertEntityToVO(updatedEntity);
    }

    public CraftTypeVO delete(Long id) {

        logger.info("Deleting a Craft Type");

        var entity = this.repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Ofício", "id", id)
        );

        this.repository.delete(entity);

        return this.mapper.convertEntityToVO(entity);
    }

}
