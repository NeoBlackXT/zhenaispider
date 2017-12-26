package com.neoblack.common.service;

import com.neoblack.common.exetend.PropertyConfig;
import org.springframework.stereotype.Service;

@Service
public class PropertieService {

    @PropertyConfig
    public String REPOSITORY_PATH;

    @PropertyConfig
    public String IMAGE_BASE_URL;

}
