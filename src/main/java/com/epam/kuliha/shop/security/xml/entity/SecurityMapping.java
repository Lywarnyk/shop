package com.epam.kuliha.shop.security.xml.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "security")
public class SecurityMapping {


    List<Constraint> constraints;

    public List<Constraint> getConstraints() {
        return constraints;
    }

    @XmlElement(name = "constraint")
    public void setConstraints(List<Constraint> constraints) {
        this.constraints = constraints;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("constraints", constraints)
                .toString();
    }
}
