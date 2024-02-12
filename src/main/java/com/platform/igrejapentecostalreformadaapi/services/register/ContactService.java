package com.platform.igrejapentecostalreformadaapi.services.register;

import com.platform.igrejapentecostalreformadaapi.data.vo.register.ContactVO;

import java.util.List;
import java.util.Optional;

public interface ContactService {

    List<ContactVO> findAll() throws Exception;

    ContactVO findById(Long id);

    ContactVO findByUserId(Long id);

    ContactVO create(ContactVO contactVO, Long userId);

    ContactVO update(ContactVO contactVO);

}
