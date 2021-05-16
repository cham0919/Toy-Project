package com.wcp.convert;

import com.wcp.security.Role;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class UserRoleToValueConverter implements AttributeConverter<Role, String> {
    @Override
    public String convertToDatabaseColumn(Role role) {
        if(role == null){ return null; }
        else { return role.getValue(); }
    }

    @Override
    public Role convertToEntityAttribute(String role) {
        if(role == null){ return null; }
        else { return Role.enumOf(role); }
    }
}
