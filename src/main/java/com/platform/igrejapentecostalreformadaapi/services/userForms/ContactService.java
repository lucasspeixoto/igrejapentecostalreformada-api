package com.platform.igrejapentecostalreformadaapi.services.userForms;

import com.platform.igrejapentecostalreformadaapi.data.vo.userForms.ContactVO;
import com.platform.igrejapentecostalreformadaapi.entities.userForms.Contact;
import com.platform.igrejapentecostalreformadaapi.entities.User;
import com.platform.igrejapentecostalreformadaapi.exceptions.ResourceAlreadyExistsException;
import com.platform.igrejapentecostalreformadaapi.exceptions.ResourceNotFoundException;
import com.platform.igrejapentecostalreformadaapi.mapper.ContactMapper;
import com.platform.igrejapentecostalreformadaapi.repositories.ContactRepository;
import com.platform.igrejapentecostalreformadaapi.repositories.UserRepository;
import com.platform.igrejapentecostalreformadaapi.utils.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ContactService {

    private final Logger logger = Logger.getLogger(ContactService.class.getName());

    @Autowired
    private ContactRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactMapper mapper;

    public List<ContactVO> findAll() throws Exception {

        logger.info("Finding all contacts!");

        List<Contact> contactsList = repository.findAll();

        return this.mapper.convertEntitiesToVOs(contactsList);

    }

    public ContactVO findById(Long id) {
        logger.info("Finding a contact by Id");

        Contact entity = repository
                .findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Contact", "id", id)
                );

        return this.mapper.convertEntityToVO(entity);

    }

    public ContactVO findByUserId(Long id) {
        logger.info("Finding a contact by Id");

        Optional<Contact> optionalEntity = repository.findByUserId(id);

        if (optionalEntity.isEmpty()) {
            throw new ResourceNotFoundException("Contato", "id", id);
        }

        Contact entity = optionalEntity.get();

        return this.mapper.convertEntityToVO(entity);

    }

    public ContactVO create(ContactVO contactVO, Long userId) {

        logger.info("Creating a contact user data");

        Optional<User> user = this.userRepository.findById(userId);

        if (user.isEmpty()) {
            throw new ResourceNotFoundException("Usuário", "id", userId);
        }

        Optional<Contact> optionalContact = this.repository.findByUserId(userId);

        if (optionalContact.isPresent()) {
            throw new ResourceAlreadyExistsException(Messages.CONTACT_IS_PRESENT_MESSAGE);
        }

        contactVO.setUser(user.get());

        Contact contact = this.mapper.convertVOToEntity(contactVO);


        Contact newContact = this.repository.save(contact);

        return this.mapper.convertEntityToVO(newContact);

    }

    public ContactVO update(ContactVO contactVO) {

        logger.info("Updating a contact user data");

        var entity = this.repository.findById(contactVO.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Contato", "id", contactVO.getId())
        );

        entity.setBirthday(contactVO.getBirthday());
        entity.setCellphone(contactVO.getCellphone());
        entity.setTelephone(contactVO.getTelephone());
        entity.setSex(contactVO.getSex());

        Contact updatedContact = this.repository.save(entity);

        return this.mapper.convertEntityToVO(updatedContact);
    }
}
