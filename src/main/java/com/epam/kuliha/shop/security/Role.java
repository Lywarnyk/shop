package com.epam.kuliha.shop.security;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;
import java.util.Arrays;

@XmlType(name = "role")
@XmlEnum
public enum Role {

    @XmlEnumValue("admin")
    ADMIN,
    @XmlEnumValue("client")
    CLIENT,
    @XmlEnumValue("unregistered")
    UNREGISTERED;

}
