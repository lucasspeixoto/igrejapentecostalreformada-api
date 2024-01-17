package com.platform.igrejapentecostalreformadaapi.services.register;

import com.platform.igrejapentecostalreformadaapi.data.vo.register.AddressVO;
import com.platform.igrejapentecostalreformadaapi.entities.register.Address;
import com.platform.igrejapentecostalreformadaapi.entities.User;
import com.platform.igrejapentecostalreformadaapi.exceptions.ResourceAlreadyExistsException;
import com.platform.igrejapentecostalreformadaapi.exceptions.ResourceNotFoundException;
import com.platform.igrejapentecostalreformadaapi.mappers.register.AddressMapper;
import com.platform.igrejapentecostalreformadaapi.repositories.register.AddressRepository;
import com.platform.igrejapentecostalreformadaapi.repositories.UserRepository;
import com.platform.igrejapentecostalreformadaapi.utils.constants.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class AddressService {

    private final Logger logger = Logger.getLogger(AddressService.class.getName());

    @Autowired
    private AddressRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressMapper mapper;

    public List<AddressVO> findAll() throws Exception {

        logger.info("Finding all address!");

        List<Address> entities = repository.findAll();

        return this.mapper.convertEntitiesToVOs(entities);

    }

    public AddressVO findById(Long id) {
        logger.info("Finding a address by Id");

        Address entity = repository
                .findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Endereço", "id", id)
                );

        return this.mapper.convertEntityToVO(entity);

    }

    public AddressVO findByUserId(Long id) {
        logger.info("Finding a address by Id");

        Optional<Address> optionalEntity = repository.findByUserId(id);

        if (optionalEntity.isEmpty()) {
            throw new ResourceNotFoundException("Endereço", "id", id);
        }

        Address entity = optionalEntity.get();

        return this.mapper.convertEntityToVO(entity);

    }

    public AddressVO create(AddressVO addressVO, Long userId) {

        logger.info("Creating a address user data");

        Optional<User> user = this.userRepository.findById(userId);

        if (user.isEmpty()) {
            throw new ResourceNotFoundException("Usuário", "id", userId);
        }

        Optional<Address> optionalEntity = this.repository.findByUserId(userId);

        if (optionalEntity.isPresent()) {
            throw new ResourceAlreadyExistsException(Messages.ADDRESS_IS_PRESENT_MESSAGE);
        }

        addressVO.setUser(user.get());

        Address address = this.mapper.convertVOToEntity(addressVO);

        Address newAddress = this.repository.save(address);

        return this.mapper.convertEntityToVO(newAddress);
    }

    public AddressVO update(AddressVO addressVO) {

        logger.info("Updating a address user data");

        var entity = this.repository.findById(addressVO.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Endereço", "id", addressVO.getId())
        );


        entity.setState(addressVO.getState());
        entity.setCep(addressVO.getCep());
        entity.setCity(addressVO.getCity());
        entity.setDistrict(addressVO.getDistrict());
        entity.setNumber(addressVO.getNumber());
        entity.setStreet(addressVO.getStreet());

        Address updated = this.repository.save(entity);

        System.out.println(this.mapper.convertEntityToVO(updated).getNumber());

        return this.mapper.convertEntityToVO(updated);
    }
}
