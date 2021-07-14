package com.ccproject.servlet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.util.ArrayList;



@WebServlet("/FileUploadServlet")
@MultipartConfig(fileSizeThreshold=1024*1024*10, 	// 10 MB 
                 maxFileSize=1024*1024*50,      	// 50 MB
                 maxRequestSize=1024*1024*100)   

// 100 MB
public class FileUploadServlet extends HttpServlet {
 
    private static final long serialVersionUID = 205242440643911308L;
	
    public static final String UPLOAD_DIR = "uploads";
    
	

     
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
    			
    	ArrayList<String> scripts = new ArrayList<String>();
    	String applicationPath = request.getServletContext().getRealPath("");
    	
    	String uploadFilePath =applicationPath+File.separator + UPLOAD_DIR;
    	
  
         
        File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        System.out.println("Upload File Directory="+fileSaveDir.getAbsolutePath());
        
        String fileName = null;
        
        for (Part part : request.getParts()) {
            fileName = getFileName(part);
            part.write(uploadFilePath + File.separator + fileName);
            scripts.add(fileName);            
        }
        String df=scripts.get(0);
        String kf=scripts.get(1);
        String dff=df;
        
        AES obj=new AES();
        String op=obj.Result(df, kf);
        if (df.endsWith(".txt")) {
            df = df.substring(0, df.length()-4);
        }
        String encpath=uploadFilePath+File.separator+df+"enc";//dataenc
        File file=new File(encpath);
        if(!file.exists()) {
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
      	    writer.write(op);
      	    writer.close();
        }
        else
        {
        	System.out.print("Not possible");
        }
 	    
       
        	file =new File(uploadFilePath+File.separator+dff);
        	if(file.delete())
        	{
        		System.out.print("File deleted from Project root directory");
        	}
        	else
        	{
        		System.out.print("Big error");
        	}
        	file =new File(uploadFilePath+File.separator+kf);
        	if(file.delete())
        	{
        		System.out.print("File deleted from Project root directory");
        	}
        	else
        	{
        		System.out.print("Big error");
        	}
    
    
     
        request.setAttribute("message", " File is encrypted successfully!");
        getServletContext().getRequestDispatcher("/response.jsp").forward(
                request, response);
        
    }
 
    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        System.out.println("content-disposition header= "+contentDisp);
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length()-1);
            }
        }
        return "";
    }
}