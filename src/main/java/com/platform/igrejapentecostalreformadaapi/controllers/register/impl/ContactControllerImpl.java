package com.platform.igrejapentecostalreformadaapi.controllers.register.impl;

import com.platform.igrejapentecostalreformadaapi.controllers.register.ContactController;
import com.platform.igrejapentecostalreformadaapi.data.vo.register.ContactVO;
import com.platform.igrejapentecostalreformadaapi.entities.register.Contact;
import com.platform.igrejapentecostalreformadaapi.services.register.ContactService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/contacts")
public class ContactControllerImpl implements ContactController {

    @Autowired
    private ContactService service;

    /**
     * Creates a new contact for the specified user.
     * Is Teste: false
     * @param contactVO the contact information to be created
     * @param userId    the ID of the user to which the contact belongs
     * @return the created contact information
     *
     * @author Lucas Peixoto
     */
    @Override
    public ResponseEntity<ContactVO> create(
            @RequestBody @Valid ContactVO contactVO,
            @PathVariable Long userId)
    {
        ContactVO contact = service.create(contactVO, userId);

        return new ResponseEntity<>(contact, HttpStatus.CREATED);

    }

    /**
     * Returns a list of all contacts in the system.
     *
     * @return a list of all contacts in the system
     *
     * @author Lucas Peixoto
     */
    @Override
    public ResponseEntity<List<ContactVO>> findAll() throws Exception {

        List<ContactVO> contacts = service.findAll();

        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    /**
     * Returns the contact with the specified ID.
     *
     * @param id the ID of the contact to be retrieved
     * @return the contact with the specified ID, or an error response if the contact could not be found
     *
     * @author Lucas Peixoto
     */
    @Override
    public ResponseEntity<ContactVO> findById(@PathVariable Long id) {
        ContactVO selectedContact = service.findById(id);

        return new ResponseEntity<>(selectedContact, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<ContactVO> update(@RequestBody @Valid ContactVO contactVO) {
        ContactVO contact = service.update(contactVO);

        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ContactVO> findByUserId(@RequestParam(value = "userId") Long userId) throws Exception {

        return ResponseEntity.ok(service.findByUserId(userId));
    }
}
