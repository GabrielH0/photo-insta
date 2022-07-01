package com.social.domain.adapter;

import com.social.domain.model.ImageFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;

public interface ObjectStorage {

    void putObject(ImageFile object) throws NoSuchAlgorithmException, IOException;

    InputStream getObject(String key);
}
