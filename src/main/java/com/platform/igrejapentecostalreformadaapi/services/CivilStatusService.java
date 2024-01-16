package com.platform.igrejapentecostalreformadaapi.services;

import com.platform.igrejapentecostalreformadaapi.data.vo.CivilStatusVO;
import com.platform.igrejapentecostalreformadaapi.entities.CivilStatus;
import com.platform.igrejapentecostalreformadaapi.exceptions.ResourceNotFoundException;
import com.platform.igrejapentecostalreformadaapi.mapper.CivilStatusMapper;
import com.platform.igrejapentecostalreformadaapi.repositories.CivilStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class CivilStatusService {

    private final Logger logger = Logger.getLogger(CivilStatusService.class.getName());

    @Autowired
    private CivilStatusRepository repository;

    @Autowired
    private CivilStatusMapper mapper;

    public List<CivilStatusVO> findAll() throws Exception {

        logger.info("Finding all civil status!");

        List<CivilStatus> entities = repository.findAll();

        return this.mapper.convertEntitiesToVOs(entities);

    }

    public CivilStatusVO findById(Long id) {
        logger.info("Finding a civil status by Id");

        CivilStatus entity = repository
                .findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Estado civil", "id", id)
                );

        return this.mapper.convertEntityToVO(entity);

    }

    public CivilStatusVO create(CivilStatusVO civilStatusVO) {

        logger.info("Creating a civil status");

        CivilStatus entity = this.mapper.convertVOToEntity(civilStatusVO);

        CivilStatus savedEntity = this.repository.save(entity);

        return this.mapper.convertEntityToVO(savedEntity);
    }

    public CivilStatusVO update(CivilStatusVO civilStatusVO) {

        logger.info("Updating a civil status");

        var entity = this.repository.findById(civilStatusVO.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Estado Civil", "id", civilStatusVO.getId())
        );

        entity.setCivilStatus(civilStatusVO.getCivilStatus());

        CivilStatus updatedEntity = this.repository.save(entity);

        return this.mapper.convertEntityToVO(updatedEntity);
    }

    public CivilStatusVO delete(Long id) {

        logger.info("Deleting a civil status");

        var entity = this.repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Estado Civil", "id", id)
        );

        this.repository.delete(entity);

        return this.mapper.convertEntityToVO(entity);
    }

}
