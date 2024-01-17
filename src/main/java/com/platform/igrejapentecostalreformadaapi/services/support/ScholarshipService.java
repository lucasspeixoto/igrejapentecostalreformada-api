package com.platform.igrejapentecostalreformadaapi.services.support;

import com.platform.igrejapentecostalreformadaapi.data.vo.support.ScholarshipVO;
import com.platform.igrejapentecostalreformadaapi.entities.support.Scholarship;
import com.platform.igrejapentecostalreformadaapi.exceptions.ResourceNotFoundException;
import com.platform.igrejapentecostalreformadaapi.mappers.support.ScholarshipMapper;
import com.platform.igrejapentecostalreformadaapi.repositories.support.ScholarshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class ScholarshipService {

    private final Logger logger = Logger.getLogger(ScholarshipService.class.getName());

    @Autowired
    private ScholarshipRepository repository;

    @Autowired
    private ScholarshipMapper mapper;

    public List<ScholarshipVO> findAll() throws Exception {

        logger.info("Finding all scholarship!");

        List<Scholarship> entities = repository.findAll();

        return this.mapper.convertEntitiesToVOs(entities);

    }

    public ScholarshipVO findById(Long id) {
        logger.info("Finding a scholarship by Id");

        Scholarship entity = repository
                .findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Escolaridade", "id", id)
                );

        return this.mapper.convertEntityToVO(entity);

    }

    public ScholarshipVO create(ScholarshipVO scholarshipVO) {

        logger.info("Creating a scholarship");

        Scholarship entity = this.mapper.convertVOToEntity(scholarshipVO);

        Scholarship savedEntity = this.repository.save(entity);

        return this.mapper.convertEntityToVO(savedEntity);
    }

    public ScholarshipVO update(ScholarshipVO scholarshipVO) {

        logger.info("Updating a scholarship");

        var entity = this.repository.findById(scholarshipVO.getId()).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Escolaridade",
                        "id",
                        scholarshipVO.getId()
                )
        );

        entity.setScholarship(scholarshipVO.getScholarship());

        Scholarship updatedEntity = this.repository.save(entity);

        return this.mapper.convertEntityToVO(updatedEntity);
    }

    public ScholarshipVO delete(Long id) {

        logger.info("Deleting a scholarship");

        var entity = this.repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Escolaridade", "id", id)
        );

        this.repository.delete(entity);

        return this.mapper.convertEntityToVO(entity);
    }
}
