package com.epam.kuliha.shop.service.image;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

public class ImageService {
    private static final String EXTENSION = ".jpg";
    private String folder;

    public ImageService(String folder) {
        this.folder = folder;
    }

    public void save(Part image, String name) throws IOException {
        image.write(StringUtils.join(folder, name, EXTENSION));
    }

    public File get(String name){
       return new File(StringUtils.join(folder, name, EXTENSION));
    }
}
