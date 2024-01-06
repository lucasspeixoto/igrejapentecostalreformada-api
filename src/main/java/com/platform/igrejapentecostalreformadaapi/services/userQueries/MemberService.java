package com.platform.igrejapentecostalreformadaapi.services.userQueries;

import com.platform.igrejapentecostalreformadaapi.data.vo.userForms.ContactVO;
import com.platform.igrejapentecostalreformadaapi.data.vo.userForms.MembershipVO;
import com.platform.igrejapentecostalreformadaapi.data.vo.userQueries.MemberVO;
import com.platform.igrejapentecostalreformadaapi.entities.User;
import com.platform.igrejapentecostalreformadaapi.entities.userForms.Contact;
import com.platform.igrejapentecostalreformadaapi.entities.userForms.Membership;
import com.platform.igrejapentecostalreformadaapi.exceptions.ResourceAlreadyExistsException;
import com.platform.igrejapentecostalreformadaapi.exceptions.ResourceNotFoundException;
import com.platform.igrejapentecostalreformadaapi.mapper.ContactMapper;
import com.platform.igrejapentecostalreformadaapi.repositories.ContactRepository;
import com.platform.igrejapentecostalreformadaapi.repositories.MembershipRepository;
import com.platform.igrejapentecostalreformadaapi.repositories.UserRepository;
import com.platform.igrejapentecostalreformadaapi.utils.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class MemberService {

    private final Logger logger = Logger.getLogger(MemberService.class.getName());

    @Autowired
    private MembershipRepository membershipRepository;

    @Autowired
    private UserRepository userRepository;

    public List<MemberVO> findAll() throws Exception {

        logger.info("Finding all members data table!");

        List<MemberVO> members = new ArrayList<>();

        List<User> usersList = this.userRepository.findAll();

        for (User user : usersList) {
            MemberVO member = new MemberVO();

            Long userId = user.getId();

            Optional<Membership> membership = this.membershipRepository.findByUserId(userId);

            if (membership.isEmpty()) {
                continue;
            }

            member.setId(user.getId());
            member.setName(user.getName());
            member.setCraft(membership.get().getCraft());
            member.setMembership(membership.get().getMembership());

            // ! Developing compute register status
            member.setRegisterFinished(false);

            members.add(member);

        }

        return members;

    }

}
