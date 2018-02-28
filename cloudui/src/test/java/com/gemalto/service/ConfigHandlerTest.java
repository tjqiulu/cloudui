package com.gemalto.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConfigHandlerTest {
    @Autowired  
    private ConfigHandler myProps;
    
	private ObjectMapper objectMapper = new ObjectMapper();   
      
    @Test  
    public void propsTest() throws JsonProcessingException {  
        System.out.println("arrayProps: " + objectMapper.writeValueAsString(myProps.getArrayProps()));  
        System.out.println("listProp1: " + objectMapper.writeValueAsString(myProps.getListProp()));  
    }  
}
