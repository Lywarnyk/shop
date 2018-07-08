package com.epam.kuliha.shop.security.xml.entity;

import com.epam.kuliha.shop.security.Role;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class Constraint {

    String urlPattern;
    List<Role> roles;

    public String getUrlPattern() {
        return urlPattern;
    }

    @XmlElement(name = "url-pattern")
    public void setUrlPattern(String urlPattern) {
        this.urlPattern = urlPattern;
    }

    public List<Role> getRoles() {
        return roles;
    }

    @XmlElement(name = "role")
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("urlPattern", urlPattern)
                .append("roles", roles)
                .toString();
    }
}
