package com.platform.igrejapentecostalreformadaapi.services;

import com.platform.igrejapentecostalreformadaapi.data.vo.MemberVO;
import com.platform.igrejapentecostalreformadaapi.entities.User;
import com.platform.igrejapentecostalreformadaapi.entities.Membership;
import com.platform.igrejapentecostalreformadaapi.entities.UserProcess;
import com.platform.igrejapentecostalreformadaapi.repositories.MembershipRepository;
import com.platform.igrejapentecostalreformadaapi.repositories.UserProcessRepository;
import com.platform.igrejapentecostalreformadaapi.repositories.UserRepository;
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

    @Autowired
    private UserProcessRepository userProcessRepository;

    public List<MemberVO> findAll() throws Exception {

        logger.info("Finding all members data table!");

        List<MemberVO> members = new ArrayList<>();

        List<User> usersList = this.userRepository.findAll();

        for (User user : usersList) {
            MemberVO member = new MemberVO();

            Long userId = user.getId();

            Optional<Membership> membership = this.membershipRepository.findByUserId(userId);

            Optional<UserProcess> userProcess = this.userProcessRepository.findByUserId(userId);

            member.setId(userId);
            member.setName(user.getName().split(" ")[0]);

            if (membership.isPresent()) {
                member.setCraft(membership.get().getCraft());
                member.setMembership(membership.get().getMembership());
            }

            if (userProcess.isPresent()) {
                UserProcess selectedUserProcess = userProcess.get();

                member.setIsRegisterFinished(selectedUserProcess.isHasAddress() &
                        selectedUserProcess.isHasBaptism() &
                        selectedUserProcess.isHasContact() &
                        selectedUserProcess.isHasDocument() &
                        selectedUserProcess.isHasEducation() &
                        selectedUserProcess.isHasFamily() &
                        selectedUserProcess.isHasMember()
                );
            } else {
                member.setIsRegisterFinished(false);
            }

            members.add(member);
        }

        return members;

    }

}
