package com.platform.igrejapentecostalreformadaapi.services.support;

import com.platform.igrejapentecostalreformadaapi.data.vo.support.MembershipTypeVO;
import com.platform.igrejapentecostalreformadaapi.entities.support.MembershipType;
import com.platform.igrejapentecostalreformadaapi.exceptions.ResourceNotFoundException;
import com.platform.igrejapentecostalreformadaapi.mappers.support.MembershipTypeMapper;
import com.platform.igrejapentecostalreformadaapi.repositories.support.MembershipTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class MembershipTypeService {

    private final Logger logger = Logger.getLogger(MembershipTypeService.class.getName());

    @Autowired
    private MembershipTypeRepository repository;

    @Autowired
    private MembershipTypeMapper mapper;

    public List<MembershipTypeVO> findAll() throws Exception {

        logger.info("Finding all Membership Type!");

        List<MembershipType> entities = repository.findAll();

        return this.mapper.convertEntitiesToVOs(entities);

    }

    public MembershipTypeVO findById(Long id) {
        logger.info("Finding a Membership Type by Id");

        MembershipType entity = repository
                .findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Tipo de membresia", "id", id)
                );

        return this.mapper.convertEntityToVO(entity);

    }

    public MembershipTypeVO create(MembershipTypeVO membershipTypeVO) {

        logger.info("Creating a Membership Type");

        MembershipType entity = this.mapper.convertVOToEntity(membershipTypeVO);

        MembershipType savedEntity = this.repository.save(entity);

        return this.mapper.convertEntityToVO(savedEntity);
    }

    public MembershipTypeVO update(MembershipTypeVO membershipTypeVO) {

        logger.info("Updating a Membership Type");

        var entity = this.repository.findById(membershipTypeVO.getId())
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Tipo de membresia",
                                "id",
                                membershipTypeVO.getId()
                        )
                );

        entity.setMembershipType(membershipTypeVO.getMembershipType());

        MembershipType updatedEntity = this.repository.save(entity);

        return this.mapper.convertEntityToVO(updatedEntity);
    }

    public MembershipTypeVO delete(Long id) {

        logger.info("Deleting a Membership Type");

        var entity = this.repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Tipo de membresia", "id", id)
        );

        this.repository.delete(entity);

        return this.mapper.convertEntityToVO(entity);
    }

}
