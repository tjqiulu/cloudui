package com.gemalto;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gemalto.pojo.LocationReq;
import com.gemalto.pojo.Status;
import com.gemalto.service.ImageGenerater;
import com.gemalto.service.Validator;


@RestController  
@RequestMapping("/rest")  
	public class DownloadRestController {  
	
	private static final Logger log = LoggerFactory.getLogger(DownloadRestController.class);

		@Autowired
	    private ImageGenerater imager;
		
		@Autowired
		private Validator validaHanlder;

		/** 
	     * 下载 
	     *  
	     * @param systemInfo 
	     * @param ucBuilder 
	     * @return 
		 * @throws UnsupportedEncodingException 
	     */  
	    @RequestMapping(value = "/download/{id}", method = RequestMethod.GET, produces = MediaType.MULTIPART_FORM_DATA_VALUE)  
	    public @ResponseBody ResponseEntity downloadFile(@PathVariable("id") String id)  {  
	    	log.info("request id is {}",id);
	    	HttpHeaders headers = new HttpHeaders();
	    	InputStream image=null;
	    	try {
	    		String fileName = imager.getFilename();
	            image = imager.generateImage(fileName);  
	            Resource inputStreamResource = new InputStreamResource(image);

	            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
	            headers.add("Pragma", "no-cache");
	            headers.add("Expires", "0");
	            headers.add("charset", "utf-8");
	            //设置下载文件名
	            String filename = URLEncoder.encode(fileName, "UTF-8");
	            headers.add("Content-Disposition", "attachment;filename=\"" + filename + "\"");
	            log.info("filename download {}",filename);
	 
	            return ResponseEntity.ok()
	            		.headers(headers)
	            		.contentType(MediaType.IMAGE_PNG)
	            		.body(inputStreamResource);
	    	}catch(Exception e) {
	    		log.error("occur error!{}",e);
	    		return ResponseEntity.ok()
	    				.headers(headers)
   					 	.contentType(MediaType.APPLICATION_JSON)
   					 	.body("{'status':'KO'}");
	    	}
	 
	            
	    }
	    
	    @RequestMapping(value="/valid/{id}",method=RequestMethod.POST)
	    public Status valid(@PathVariable("id") String id,@RequestBody LocationReq index) {
	    	log.info("request pin id is {}",id);
	    	log.info("request location {}",index);
	    	if ( index == null) {  
	            return new Status("KO");  
	        } else {  
	            if(validaHanlder.valid(index.getIndex(),index.getFileName(),id))
	            	return new Status("OK");
	            else
	            	return new Status("KO");
	        }  
	}  
}
