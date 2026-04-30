package com.example.template.domain.auth.mapper;

import com.example.template.domain.auth.dto.RegisterRequest;
import com.example.template.domain.auth.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for Auth-related conversions using MapStruct.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthMapper {

    @Mapping(target = "password", ignore = true) // Password will be encoded manually in service
    User toUser(RegisterRequest request);
}
