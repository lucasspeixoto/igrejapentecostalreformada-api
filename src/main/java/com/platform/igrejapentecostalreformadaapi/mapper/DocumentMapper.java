package com.platform.igrejapentecostalreformadaapi.mapper;

import com.platform.igrejapentecostalreformadaapi.data.vo.DocumentVO;
import com.platform.igrejapentecostalreformadaapi.entities.Document;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DocumentMapper {

    DocumentMapper INSTANCE = Mappers.getMapper(DocumentMapper.class);

    DocumentVO convertEntityToVO(Document document);

    List<DocumentVO> convertEntitiesToVOs(List<Document> documents);

    Document convertVOToEntity(DocumentVO documentVo);

    List<Document> convertVOsToEntities(List<DocumentVO> documentVos);
}
