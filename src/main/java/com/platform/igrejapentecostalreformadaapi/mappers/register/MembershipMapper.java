package com.platform.igrejapentecostalreformadaapi.mappers.register;

import com.platform.igrejapentecostalreformadaapi.data.vo.register.MembershipVO;
import com.platform.igrejapentecostalreformadaapi.entities.register.Membership;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MembershipMapper {

    MembershipMapper INSTANCE = Mappers.getMapper(MembershipMapper.class);

    MembershipVO convertEntityToVO(Membership membership);

    List<MembershipVO> convertEntitiesToVOs(List<Membership> memberships);

    Membership convertVOToEntity(MembershipVO membershipVO);

    List<Membership> convertVOsToEntities(List<MembershipVO> membershipVos);
}
