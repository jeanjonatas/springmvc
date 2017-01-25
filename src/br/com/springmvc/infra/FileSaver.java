package br.com.springmvc.infra;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileSaver {
	@Autowired 
	private HttpServletRequest request;
	 
	
	public String write(String baseFolder, MultipartFile file){
		
		
		try{
			
			
			System.out.println("baseFolder "+baseFolder);
			System.out.println("" +file);
			
			String realPath = request.getServletContext().getRealPath(""+baseFolder);
			System.out.println("realPath " +realPath);
			
			String path = realPath+"\\"+file.getOriginalFilename();
			
			System.out.println("path "+path);
			
			
			
			
			file.transferTo(new File(path));
			return baseFolder+"/"+file.getOriginalFilename();
		}catch(IOException e){
			throw new RuntimeException(e);
		}
		
	}
}
