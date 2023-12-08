package com.platform.igrejapentecostalreformadaapi.services;

import com.platform.igrejapentecostalreformadaapi.data.vo.ContactVO;
import com.platform.igrejapentecostalreformadaapi.entities.Contact;
import com.platform.igrejapentecostalreformadaapi.exceptions.ResourceNotFoundException;
import com.platform.igrejapentecostalreformadaapi.mapper.PlatformMapper;
import com.platform.igrejapentecostalreformadaapi.repositories.ContactRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.logging.Logger;

@Service
public class ContactService {

    private final Logger logger = Logger.getLogger(ContactService.class.getName());

    @Autowired
    private ContactRepository repository;

    @Autowired
    private ModelMapper modelMapper;

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

         return this.convertEntityToDTO(entity);

     }

    public ContactVO create(ContactVO contactVO) {

        logger.info("Creating a contact user data");

        // Create Contact data
        Contact contact = this.convertDTOToEntity(contactVO);

        Contact newContact = this.repository.save(contact);

        return this.convertEntityToDTO(newContact);
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

        return this.convertEntityToDTO(updatedContact);


    }

    public ContactVO findByUserId(Long id) {
        logger.info("Finding a contact by Id");

        Contact entity = repository.findByUserId(id).orElseThrow(
                () -> new ResourceNotFoundException("Contact", "id", id)
        );

        return this.convertEntityToDTO(entity);

    }


    //! Mapper methods ---------------------------------------------------------------------------
    private ContactVO convertEntityToDTO(Contact entity) {
        return PlatformMapper.parseObject(entity, ContactVO.class, modelMapper);
    }

    private Contact convertDTOToEntity(ContactVO postDTO) {
        return PlatformMapper.parseObject(postDTO, Contact.class, modelMapper);
    }

    private List<ContactVO> convertEntitiesToDTOs(List<Contact> entities) {
        return PlatformMapper.parseListObjects(entities, ContactVO.class, modelMapper);
    }

    private List<Contact> convertDTOsToEntities(List<ContactVO> products) {
        return PlatformMapper.parseListObjects(products, Contact.class, modelMapper);
    }
    //! --------------------------------------------------------------------------- Mapper methods
}
