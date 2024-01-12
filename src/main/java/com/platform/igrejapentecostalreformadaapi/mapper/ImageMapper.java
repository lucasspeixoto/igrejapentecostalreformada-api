package com.platform.igrejapentecostalreformadaapi.mapper;

import com.platform.igrejapentecostalreformadaapi.data.vo.ImageVO;
import com.platform.igrejapentecostalreformadaapi.entities.Image;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ImageMapper {

    ImageMapper INSTANCE = Mappers.getMapper(ImageMapper.class);

    ImageVO convertEntityToVO(Image image);

    List<ImageVO> convertEntitiesToVOs(List<Image> images);

    Image convertVOToEntity(ImageVO imageVO);

    List<Image> convertVOsToEntities(List<ImageVO> imageVOs);
}
