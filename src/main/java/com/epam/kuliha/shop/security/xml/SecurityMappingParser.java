package com.epam.kuliha.shop.security.xml;

import com.epam.kuliha.shop.security.xml.entity.SecurityMapping;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class SecurityMappingParser {

    private static final Logger LOG = Logger.getLogger(SecurityMappingParser.class);

    public static SecurityMapping parse(String inputFile){
        try{
            File file = new File(inputFile);
            JAXBContext jaxbContext = JAXBContext.newInstance(SecurityMapping.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (SecurityMapping) jaxbUnmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            LOG.error(e);
        }
        return null;
    }
}
