package com.andrzej.pe.helpers;

import com.andrzej.pe.security.groups.Groups;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class GrantedAuthorityHelper implements GrantedAuthority {

    private Groups groups;

    @Autowired
    public GrantedAuthorityHelper(Groups groups) {
        this.groups = groups;
    }

    @Override
    public String getAuthority() {
        return groups.ADMIN;
    }
}
