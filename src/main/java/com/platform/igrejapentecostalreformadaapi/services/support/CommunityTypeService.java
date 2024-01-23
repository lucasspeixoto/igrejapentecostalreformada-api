package com.platform.igrejapentecostalreformadaapi.services.support;

import com.platform.igrejapentecostalreformadaapi.data.vo.support.CommunityTypeVO;

import com.platform.igrejapentecostalreformadaapi.entities.support.CommunityType;
import com.platform.igrejapentecostalreformadaapi.exceptions.ResourceNotFoundException;
import com.platform.igrejapentecostalreformadaapi.mappers.support.CommunityTypeMapper;
import com.platform.igrejapentecostalreformadaapi.repositories.support.CommunityTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class CommunityTypeService {

    private final Logger logger = Logger.getLogger(CommunityTypeService.class.getName());

    @Autowired
    private CommunityTypeRepository repository;

    @Autowired
    private CommunityTypeMapper mapper;

    public List<CommunityTypeVO> findAll() throws Exception {

        logger.info("Finding all Community Type!");

        List<CommunityType> entities = repository.findAll();

        return this.mapper.convertEntitiesToVOs(entities);

    }

    public CommunityTypeVO findById(Long id) {
        logger.info("Finding a Community Type by Id");

        CommunityType entity = repository
                .findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Comunidade", "id", id)
                );

        return this.mapper.convertEntityToVO(entity);

    }

    public CommunityTypeVO create(CommunityTypeVO communityTypeVO) {

        logger.info("Creating a Community Type");

        CommunityType entity = this.mapper.convertVOToEntity(communityTypeVO);

        CommunityType savedEntity = this.repository.save(entity);

        return this.mapper.convertEntityToVO(savedEntity);
    }

    public CommunityTypeVO update(CommunityTypeVO communityTypeVO) {

        logger.info("Updating a Community Type");

        var entity = this.repository.findById(communityTypeVO.getId())
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Comunidade",
                                "id",
                                communityTypeVO.getId()
                        )
                );

        entity.setCommunityType(communityTypeVO.getCommunityType());

        CommunityType updatedEntity = this.repository.save(entity);

        return this.mapper.convertEntityToVO(updatedEntity);
    }

    public CommunityTypeVO delete(Long id) {

        logger.info("Deleting a Community Type");

        var entity = this.repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Comunidade", "id", id)
        );

        this.repository.delete(entity);

        return this.mapper.convertEntityToVO(entity);
    }

}
