package com.platform.igrejapentecostalreformadaapi.services.indicators;

import com.platform.igrejapentecostalreformadaapi.data.vo.indicators.Statistics;
import com.platform.igrejapentecostalreformadaapi.entities.Role;
import com.platform.igrejapentecostalreformadaapi.entities.User;
import com.platform.igrejapentecostalreformadaapi.entities.UserProcess;
import com.platform.igrejapentecostalreformadaapi.entities.register.Address;
import com.platform.igrejapentecostalreformadaapi.entities.register.Contact;
import com.platform.igrejapentecostalreformadaapi.repositories.RoleRepository;
import com.platform.igrejapentecostalreformadaapi.repositories.UserProcessRepository;
import com.platform.igrejapentecostalreformadaapi.repositories.UserRepository;
import com.platform.igrejapentecostalreformadaapi.repositories.register.AddressRepository;
import com.platform.igrejapentecostalreformadaapi.repositories.register.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

@Service
public class StatisticsService {

    private final Logger logger = Logger.getLogger(StatisticsService.class.getName());

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserProcessRepository userProcessRepository;

    public Statistics findStatisticsData() throws Exception {

        logger.info("Finding statistics data!");

        int totalMembers = this.userProcessRepository.findAll().size();

        AtomicInteger totalMembersWithRegisterCompleted = new AtomicInteger();

        AtomicInteger totalMembersFemale = new AtomicInteger();

        AtomicInteger totalMembersMale = new AtomicInteger();

        AtomicInteger totalMembersInCampinas = new AtomicInteger();

        AtomicInteger totalMembersAdmin = new AtomicInteger();

        List<User> users = this.userRepository.findAll();

        for (User user : users) {

            for (Role role : user.getRoles()) {
                if (Objects.equals(role.getName(), "ROLE_ADMIN")) {
                    totalMembersAdmin.addAndGet(1);
                }
            }

            Long userId = user.getId();

            Optional<UserProcess> userProcess = this.userProcessRepository.findByUserId(userId);

            if (userProcess.isPresent()) {
                UserProcess selectedUserProcess = userProcess.get();

                if (selectedUserProcess.isHasAddress() &
                        selectedUserProcess.isHasBaptism() &
                        selectedUserProcess.isHasContact() &
                        selectedUserProcess.isHasDocument() &
                        selectedUserProcess.isHasEducation() &
                        selectedUserProcess.isHasFamily() &
                        selectedUserProcess.isHasMember()
                ) {
                    totalMembersWithRegisterCompleted.addAndGet(1);
                }
            }

            Optional<Contact> contact = this.contactRepository.findByUserId(userId);

            if (contact.isPresent()) {
                if (Objects.equals(contact.get().getSex().toLowerCase(), "feminino")) {
                    totalMembersFemale.addAndGet(1);
                } else {
                    totalMembersMale.addAndGet(1);
                }
            }

            Optional<Address> address = this.addressRepository.findByUserId(userId);

            if (address.isPresent()) {
                if (Objects.equals(address.get().getCity().toLowerCase(), "campinas")) {
                    totalMembersInCampinas.addAndGet(1);
                }
            }

        }

        return getStatisticsDataHandler(
                totalMembers,
                totalMembersWithRegisterCompleted.get(),
                totalMembersFemale.get(),
                totalMembersMale.get(),
                totalMembersInCampinas.get(),
                totalMembersAdmin.get()
        );
    }

    private static Statistics getStatisticsDataHandler(
            int totalMembers,
            int totalMembersWithRegisterCompleted,
            int totalMembersFemale,
            int totalMembersMale,
            int totalMembersInCampinas,
            int totalMembersAdmin
    ) {
        Statistics statistics = new Statistics();

        statistics.setTotalMembers(totalMembers);
        statistics.setTotalMembersWithRegisterCompleted(totalMembersWithRegisterCompleted);
        statistics.setTotalMembersFemale(totalMembersFemale);
        statistics.setTotalMembersMale(totalMembersMale);
        statistics.setTotalMembersInCampinas(totalMembersInCampinas);
        statistics.setTotalMembersAdmin(totalMembersAdmin);

        return statistics;
    }
}
