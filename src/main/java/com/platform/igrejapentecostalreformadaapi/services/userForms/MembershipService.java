package com.platform.igrejapentecostalreformadaapi.services.userForms;

import com.platform.igrejapentecostalreformadaapi.data.vo.userForms.MembershipVO;
import com.platform.igrejapentecostalreformadaapi.entities.User;
import com.platform.igrejapentecostalreformadaapi.entities.userForms.Membership;
import com.platform.igrejapentecostalreformadaapi.exceptions.ResourceAlreadyExistsException;
import com.platform.igrejapentecostalreformadaapi.exceptions.ResourceNotFoundException;
import com.platform.igrejapentecostalreformadaapi.mapper.MembershipMapper;
import com.platform.igrejapentecostalreformadaapi.repositories.MembershipRepository;
import com.platform.igrejapentecostalreformadaapi.repositories.UserRepository;
import com.platform.igrejapentecostalreformadaapi.utils.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class MembershipService {

    private final Logger logger = Logger.getLogger(MembershipService.class.getName());

    @Autowired
    private MembershipRepository membershipRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MembershipMapper mapper;

    public List<MembershipVO> findAll() throws Exception {

        logger.info("Finding all contacts!");

        List<Membership> membershipList = membershipRepository.findAll();

        return this.mapper.convertEntitiesToVOs(membershipList);

    }

    public MembershipVO create(MembershipVO membershipVO, Long userId) {

        logger.info("Creating a Membership user data");

        Optional<User> user = this.userRepository.findById(userId);

        if (user.isEmpty()) {
            throw new ResourceNotFoundException("Usuário", "id", userId);
        }

        Optional<Membership> optionalMembership = this.membershipRepository.findByUserId(userId);

        if (optionalMembership.isPresent()) {
            throw new ResourceAlreadyExistsException(Messages.CONTACT_IS_PRESENT_MESSAGE);
        }

        membershipVO.setUser(user.get());

        Membership membership = this.mapper.convertVOToEntity(membershipVO);

        Membership newMembership = this.membershipRepository.save(membership);

        return this.mapper.convertEntityToVO(newMembership);

    }

    public MembershipVO findById(Long id) {
        logger.info("Finding a contact by Id");

        Membership entity = this.membershipRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Membresia", "id", id)
                );

        return this.mapper.convertEntityToVO(entity);

    }

    public MembershipVO findByUserId(Long id) {
        logger.info("Finding a contact by Id");

        Optional<Membership> optionalMembership = this.membershipRepository.findByUserId(id);

        if (optionalMembership.isEmpty()) {
            throw new ResourceNotFoundException("Membresia", "id", id);
        }

        Membership membership = optionalMembership.get();

        return this.mapper.convertEntityToVO(membership);

    }

    public MembershipVO update(MembershipVO membershipVO) {

        logger.info("Updating a Membership user data");

        var entity = this.membershipRepository.findById(membershipVO.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Membresia", "id", membershipVO.getId())
        );

        entity.setCommunity(membershipVO.getCommunity());
        entity.setMembership(membershipVO.getMembership());
        entity.setCraft(membershipVO.getCraft());
        entity.setInterest(membershipVO.getInterest());

        Membership updatedContact = this.membershipRepository.save(entity);

        return this.mapper.convertEntityToVO(updatedContact);
    }

}
