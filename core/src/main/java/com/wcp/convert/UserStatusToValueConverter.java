package com.wcp.convert;

import com.wcp.security.Role;
import com.wcp.user.UserSataus;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;

@Convert
public class UserStatusToValueConverter implements AttributeConverter<UserSataus, String> {

    @Override
    public String convertToDatabaseColumn(UserSataus status) {
        if(status == null){ return null; }
        else { return status.getValue(); }

    }

    @Override
    public UserSataus convertToEntityAttribute(String status) {
        if(status == null){ return null; }
        else { return UserSataus.enumOf(status); }
    }
}
