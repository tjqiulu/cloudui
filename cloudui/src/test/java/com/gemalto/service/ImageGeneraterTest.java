package com.gemalto.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageGeneraterTest {
	
	@Autowired
	ImageGenerater imageGenerate;
	
	private Logger log = LoggerFactory.getLogger(ImageGeneraterTest.class);

	@Test
	public void test() {
		log .info(imageGenerate.getFilename());
	}

}
