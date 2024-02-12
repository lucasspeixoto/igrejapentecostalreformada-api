package com.platform.igrejapentecostalreformadaapi.unittests.mapper.mocks;

import com.platform.igrejapentecostalreformadaapi.data.vo.register.ContactVO;
import com.platform.igrejapentecostalreformadaapi.entities.User;
import com.platform.igrejapentecostalreformadaapi.entities.register.Contact;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MockContact {

    public Contact mockEntity() {
        return mockEntity(0);
    }

    public ContactVO mockVO() {
        return mockVO(0);
    }

    public List<Contact> mockEntityList() {
        List<Contact> users = new ArrayList<Contact>();

        for (int i = 0; i < 14; i++) {
            users.add(mockEntity(i));
        }
        return users;
    }

    public List<ContactVO> mockVOList() {
        List<ContactVO> contacts = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            contacts.add(mockVO(i));
        }
        return contacts;
    }

    public Contact mockEntity(Integer number) {
        Contact contact = new Contact();
        contact.setId(number.longValue());
        contact.setBirthday(new Date());
        contact.setCellphone("19982621117");
        contact.setTelephone("19982621117");
        contact.setCreatedAt(new Date());
        contact.setUpdatedAt(new Date());
        contact.setSex("Masculino");

        User user = new User();
        user.setId(number.longValue());
        user.setName("Lucas Peixoto");
        user.setEmail("lspeixotodev@gmail.com");
        user.setUsername("lspeixotodev");

        contact.setUser(user);

        return contact;
    }

    public ContactVO mockVO(Integer number) {
        ContactVO contactVo = new ContactVO();
        contactVo.setId(number.longValue());
        contactVo.setBirthday(new Date());
        contactVo.setCellphone("19982621117");
        contactVo.setTelephone("19982621117");
        contactVo.setCreatedAt(new Date());
        contactVo.setUpdatedAt(new Date());
        contactVo.setSex("Masculino");

        User user = new User();
        user.setId(number.longValue());
        user.setName("Lucas Peixoto");
        user.setEmail("lspeixotodev@gmail.com");
        user.setUsername("lspeixotodev");

        contactVo.setUser(user);

        return contactVo;
    }

}

