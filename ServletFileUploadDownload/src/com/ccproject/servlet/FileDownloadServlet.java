package com.ccproject.servlet;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/FileDownloadServlet")
@MultipartConfig(fileSizeThreshold=1024*1024*10, 	// 10 MB 
                 maxFileSize=1024*1024*50,      	// 50 MB
                 maxRequestSize=1024*1024*100)   


public class FileDownloadServlet extends HttpServlet{

	private static final long serialVersionUID = -8340371175558509513L;
	FileUploadServlet fus=new FileUploadServlet();
	String applicationPath;
	String uploadFilePath;
	String fn;
	String fnpath;
	public void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
  
			response.setContentType("text/html");  
			PrintWriter out = response.getWriter();  
			String filename =  fn+"dec"; 
			String filepath = uploadFilePath+File.separator+fn+"dec";   
			response.setContentType("APPLICATION/OCTET-STREAM");   
			response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");   
  
			FileInputStream fileInputStream = new FileInputStream(filepath);  
            
			int i;   
			while ((i=fileInputStream.read()) != -1) {  
				out.write(i);   
			}   
			fileInputStream.close();   
			out.close();
			File file3=new File(filepath);
			if(file3.delete())
	    	{
	    		System.out.print("File d key file deleted from Project root directory\n");
	    	}
	    	else
	    	{
	    		System.out.print("Big error");
	    	}
			
			
			   
	} 

	protected void doPost(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException {

       
        applicationPath = request.getServletContext().getRealPath("");
        uploadFilePath= applicationPath+ File.separator + fus.UPLOAD_DIR;

        
        String kfileName = null;
       	for (Part part : request.getParts()) {
                kfileName = getFileName(part);
                if (kfileName != null && !"".equals(kfileName)) {                
                part.write(uploadFilePath + File.separator + kfileName);
                }
                            
            }
       	fn=request.getParameter("fileToDecrypt");
        System.out.print(fn);
        fnpath=uploadFilePath+File.separator+fn;
        System.out.print(fnpath);
        
        AES obj2=new AES();
        String op2=obj2.Result2(fnpath, kfileName);
        if (fn.endsWith("enc")) {
            fn = fn.substring(0, fn.length()-3);
        }
        
        File file =new File(uploadFilePath+File.separator+kfileName);
        String encpath2=uploadFilePath+File.separator+fn+"dec";
        File file2=new File(encpath2);
        if(!file2.exists()) {
            file2.createNewFile();
            FileWriter writer = new FileWriter(file2);
      	    writer.write(op2);
      	    writer.close();
        }
        else
        {
        	System.out.print("Not possible");
        }
        
        
    	if(file.delete())
    	{
    		System.out.print("File d key file deleted from Project root directory\n");
    	}
    	else
    	{
    		System.out.print("Big error");
    	}
        
        request.setAttribute("message", " File is decrypted successfully!");
        getServletContext().getRequestDispatcher("/response2.jsp").forward(
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
