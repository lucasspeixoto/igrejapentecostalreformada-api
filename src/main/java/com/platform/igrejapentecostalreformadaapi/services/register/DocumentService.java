package com.platform.igrejapentecostalreformadaapi.services.register;

import com.platform.igrejapentecostalreformadaapi.data.vo.register.DocumentVO;
import com.platform.igrejapentecostalreformadaapi.entities.register.Document;
import com.platform.igrejapentecostalreformadaapi.entities.User;
import com.platform.igrejapentecostalreformadaapi.exceptions.ResourceAlreadyExistsException;
import com.platform.igrejapentecostalreformadaapi.exceptions.ResourceNotFoundException;
import com.platform.igrejapentecostalreformadaapi.mappers.register.DocumentMapper;
import com.platform.igrejapentecostalreformadaapi.repositories.register.DocumentRepository;
import com.platform.igrejapentecostalreformadaapi.repositories.UserRepository;
import com.platform.igrejapentecostalreformadaapi.utils.constants.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class DocumentService {

    private final Logger logger = Logger.getLogger(DocumentService.class.getName());

    @Autowired
    private DocumentRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DocumentMapper mapper;

    public List<DocumentVO> findAll() throws Exception {

        logger.info("Finding all documents!");

        List<Document> docsList = repository.findAll();

        return this.mapper.convertEntitiesToVOs(docsList);

    }

    public DocumentVO findById(Long id) {
        logger.info("Finding a contact by Id");

        Document entity = repository
                .findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Documento", "id", id)
                );

        return this.mapper.convertEntityToVO(entity);

    }

    public DocumentVO findByUserId(Long id) {
        logger.info("Finding a document by Id");

        Optional<Document> optionalEntity = repository.findByUserId(id);

        if (optionalEntity.isEmpty()) {
            throw new ResourceNotFoundException("Documento", "id", id);
        }

        Document entity = optionalEntity.get();

        return this.mapper.convertEntityToVO(entity);

    }

    public DocumentVO create(DocumentVO docVO, Long userId) {

        logger.info("Creating a document user data");

        Optional<User> user = this.userRepository.findById(userId);

        if (user.isEmpty()) {
            throw new ResourceNotFoundException("Usuário", "id", userId);
        }

        Optional<Document> optionalContact = this.repository.findByUserId(userId);

        if (optionalContact.isPresent()) {
            throw new ResourceAlreadyExistsException(Messages.DOC_IS_PRESENT_MESSAGE);
        }

        docVO.setUser(user.get());

        Document contact = this.mapper.convertVOToEntity(docVO);

        Document newContact = this.repository.save(contact);

        return this.mapper.convertEntityToVO(newContact);
    }

    public DocumentVO update(DocumentVO docVO) {

        logger.info("Updating a document user data");

        var entity = this.repository.findById(docVO.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Documento", "id", docVO.getId())
        );

        entity.setCpf(docVO.getCpf());
        entity.setRg(docVO.getRg());

        Document updatedContact = this.repository.save(entity);

        return this.mapper.convertEntityToVO(updatedContact);
    }
}
