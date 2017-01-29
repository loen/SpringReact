package com.andrzej.pe.security.groups;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class Groups {

    public final String ADMIN;

    @Autowired
    public Groups(Environment env) {
        this.ADMIN = env.getProperty("stormpath.authorized.admin.group.href");
    }
}
