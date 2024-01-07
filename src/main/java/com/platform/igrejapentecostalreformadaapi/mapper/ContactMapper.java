package com.platform.igrejapentecostalreformadaapi.mapper;

import com.platform.igrejapentecostalreformadaapi.data.vo.ContactVO;
import com.platform.igrejapentecostalreformadaapi.entities.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContactMapper {

    ContactMapper INSTANCE = Mappers.getMapper(ContactMapper.class);

    ContactVO convertEntityToVO(Contact contact);

    List<ContactVO> convertEntitiesToVOs(List<Contact> contacts);

    Contact convertVOToEntity(ContactVO contact);

    List<Contact> convertVOsToEntities(List<ContactVO> contactsVos);
}
