package com.gemalto.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gemalto.pojo.Location;

@Service
public class Validator {
	
	@Autowired
	private ConfigHandler configHandler;
	
	private Logger log = LoggerFactory.getLogger(Validator.class);

	public boolean valid(Location[] index,String fileName,String id ) {
		
		String pw="";
		/*
		String sPw = configHandler.getListProp()
				.stream()
				.map (n->n.keySet()
					.map(p->p)
					.filter(key->key.equals(fileName))
					.findFirst()
					.orElse(""))
				;
		
		*/
		
		String sPw="";
		
		List<Map<String,String>> list =configHandler.getArrayProps();
		for(Map file: list) {
			if(file.get("id").equals(id)) {
				sPw=(String)file.get("pw");
				break;
			}
		}
		
		log.info("Standard pw is {}",sPw);
		
		try {
		for(int i=0;i<index.length;i++) {
			log.info("poion num.{}",i);
			int xPoint = index[i].getX();
			log.info("x={}",xPoint);
			int yPoint = index[i].getY();
			log.info("y={}",yPoint);
			log.info("fileName is {}",fileName);
			pw+=ParserPinUtil.parsePosition2Number (fileName, xPoint, yPoint);
		}
		
		
		log.info("Given pw is {}",pw);
		
		
		if(pw.equals(sPw)) {
			log.info("Match pw successful!");
			return true;
		}else {
			log.warn("Match pw fail!");
			return false;
		}			
		}catch(Exception e){
			log.error("Error in backend!",e);
			return false; 
		}
		
	}

}
