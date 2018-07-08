package com.epam.kuliha.shop.security;

import com.epam.kuliha.shop.security.xml.SecurityMappingParser;
import com.epam.kuliha.shop.security.xml.entity.Constraint;
import com.epam.kuliha.shop.security.xml.entity.SecurityMapping;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccessController {

    private SecurityMapping securityMapping;

    public AccessController(String securityFile) {
        securityMapping = SecurityMappingParser.parse(securityFile);
    }

    public Optional<Constraint> getConstraintByUrl(String requestURI) {
        return securityMapping.getConstraints()
                .stream()
                .filter(c -> {
                    Pattern p = Pattern.compile(c.getUrlPattern());
                    Matcher m = p.matcher(requestURI);
                    return m.find();
                })
                .findFirst();
    }
}
