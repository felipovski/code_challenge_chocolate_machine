package com.chococompany.control.mapper;

import com.chococompany.control.dto.ChocolateTypeDtoV1;
import com.chococompany.control.dto.ChocolatePercentageDto;
import com.chococompany.entity.Chocolate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "cdi")
public interface ChocolateMapper {

    @Mappings({
            @Mapping(target = "chocolateType", source = "dto.type")
    })
    Chocolate toChocolate(ChocolateTypeDtoV1 dto);

    @Mappings({
            @Mapping(target = "percentage", source = "chocolateType.percentage"),
            @Mapping(target = "type", source = "chocolateType"),
            @Mapping(target = "description", source = "description")
    })
    ChocolatePercentageDto toChocolatePercentageDto(Chocolate entity);
}
