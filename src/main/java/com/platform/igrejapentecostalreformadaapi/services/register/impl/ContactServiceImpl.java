package com.platform.igrejapentecostalreformadaapi.services.register.impl;

import com.platform.igrejapentecostalreformadaapi.data.vo.register.ContactVO;
import com.platform.igrejapentecostalreformadaapi.entities.User;
import com.platform.igrejapentecostalreformadaapi.entities.register.Contact;
import com.platform.igrejapentecostalreformadaapi.exceptions.ResourceAlreadyExistsException;
import com.platform.igrejapentecostalreformadaapi.exceptions.ResourceNotFoundException;
import com.platform.igrejapentecostalreformadaapi.mappers.register.ContactMapper;
import com.platform.igrejapentecostalreformadaapi.repositories.UserRepository;
import com.platform.igrejapentecostalreformadaapi.repositories.register.ContactRepository;
import com.platform.igrejapentecostalreformadaapi.services.register.ContactService;
import com.platform.igrejapentecostalreformadaapi.utils.constants.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ContactServiceImpl implements ContactService {

    private final Logger logger = Logger.getLogger(ContactServiceImpl.class.getName());

    @Autowired
    private ContactRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactMapper mapper;

    /**
     * Creates a new contact for the given user.
     * Is Tested: true
     * @param contactVO the contact data to be created
     * @param userId    the id of the user to which the contact belongs
     * @return the created contact data
     * @throws ResourceNotFoundException if the user with the given id cannot be found
     * @throws ResourceAlreadyExistsException if a contact for the given user already exists
     * @author Lucas Peixoto
     */
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

    /**
     * Returns a list of all contacts in the system.
     * Is Tested: true
     * @return a list of all contacts in the system
     * @throws Exception if there is an error retrieving the contacts
     */
    public List<ContactVO> findAll() throws Exception {

        logger.info("Finding all contacts!");

        List<Contact> contactsList = repository.findAll();

        return this.mapper.convertEntitiesToVOs(contactsList);

    }

    /**
     * Returns a contact with the given ID.
     * Is Tested: true
     * @param id the ID of the contact to retrieve
     * @return the contact with the given ID
     * @throws ResourceNotFoundException if no contact with the given ID is found
     */
    public ContactVO findById(Long id) {
        logger.info("Finding a contact by Id");

        Optional<Contact> optionalExistingContact = repository.findById(id);

        if (optionalExistingContact.isEmpty()) {
            throw new ResourceNotFoundException("Contato", "id", id);
        }

        return this.mapper.convertEntityToVO(optionalExistingContact.get());

    }

    /**
     * Returns a contact with the given ID.
     * Is Teste: true
     * @param id the ID of the contact to retrieve
     * @return the contact with the given ID
     * @throws ResourceNotFoundException if no contact with the given ID is found
     */
    public ContactVO findByUserId(Long id) {
        logger.info("Finding a contact by Id");

        Optional<Contact> optionalEntity = repository.findByUserId(id);

        if (optionalEntity.isEmpty()) {
            throw new ResourceNotFoundException("Contato", "id", id);
        }

        Contact entity = optionalEntity.get();

        return this.mapper.convertEntityToVO(entity);

    }

    /**
     * Updates a contact user data
     * Is Tested: true
     * @param contactVO the contact data to be updated
     * @return the updated contact data
     * @throws ResourceNotFoundException if the contact with the given id cannot be found
     * @author: Lucas
     */
    public ContactVO update(ContactVO contactVO) {

        logger.info("Updating a contact user data");

        ContactVO updatedContactVo = null;

        try {
            updatedContactVo = findById(contactVO.getId());

            updatedContactVo.setBirthday(contactVO.getBirthday());
            updatedContactVo.setCellphone(contactVO.getCellphone());
            updatedContactVo.setTelephone(contactVO.getTelephone());
            updatedContactVo.setSex(contactVO.getSex());


        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Contato", "id", contactVO.getId());
        }

        Contact updatedContact = this.mapper.convertVOToEntity(updatedContactVo);

        Contact savedContact = this.repository.save(updatedContact);

        return this.mapper.convertEntityToVO(savedContact);
    }
}
