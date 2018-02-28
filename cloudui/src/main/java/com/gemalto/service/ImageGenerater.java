package com.gemalto.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageGenerater {
	
	@Autowired
	private ConfigHandler configHandler;
	
	
	public InputStream generateImage(String fileName) throws FileNotFoundException {
		return new FileInputStream(new File(fileName));
	}

	public String getFilename() {
		Random random = new Random();
		int randNum = random.nextInt(3);
		return configHandler.getListProp()[randNum];
		
	}

}
