package com.platform.igrejapentecostalreformadaapi.services;

import com.platform.igrejapentecostalreformadaapi.data.vo.ContactVO;
import com.platform.igrejapentecostalreformadaapi.entities.Contact;
import com.platform.igrejapentecostalreformadaapi.exceptions.ResourceAlreadyExistsException;
import com.platform.igrejapentecostalreformadaapi.exceptions.ResourceNotFoundException;
import com.platform.igrejapentecostalreformadaapi.mapper.ContactMapper;
import com.platform.igrejapentecostalreformadaapi.repositories.ContactRepository;
import com.platform.igrejapentecostalreformadaapi.utils.Messages;
import org.mapstruct.ap.internal.util.Message;
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
    private ContactMapper mapper;

    public List<Contact> findAll() throws Exception {

        logger.info("Finding all contacts!");

        return repository.findAll();

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

    public ContactVO create(ContactVO contactVO) {

        logger.info("Creating a contact user data");

        Optional<Contact> optionalContact = this.repository.findByUserId(contactVO.getUserId());

        if (optionalContact.isPresent()) {
            throw new ResourceAlreadyExistsException(Messages.CONTACT_IS_PRESENT_MESSAGE);
        }

        // Create Contact data
        Contact contact = this.mapper.convertVOToEntity(contactVO);

        Contact newContact = this.repository.save(contact);

        return this.mapper.convertEntityToVO(newContact);
    }

    public ContactVO update(ContactVO contactVO) {

        logger.info("Updating a contact user data");


        var entity = this.repository.findById(contactVO.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Contact", "id", contactVO.getId())
        );

        entity.setBirthday(contactVO.getBirthday());
        entity.setCellphone(contactVO.getCellphone());
        entity.setTelephone(contactVO.getTelephone());
        entity.setSex(contactVO.getSex());
        entity.setUserId(contactVO.getUserId());

        Contact updatedContact = this.repository.save(entity);

        return this.mapper.convertEntityToVO(updatedContact);

    }

    public ContactVO findByUserId(Long id) {
        logger.info("Finding a contact by Id");

        Optional<Contact> optionalEntity = repository.findByUserId(id);

        if (optionalEntity.isEmpty()) {
            throw new ResourceNotFoundException("Contact", "id", id);
        }

        Contact entity = optionalEntity.get();

        return this.mapper.convertEntityToVO(entity);

    }
}
