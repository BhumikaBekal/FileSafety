package com.ccproject.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;
import java.util.Stack;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AES {

	private static SecretKeySpec secretKey;
    private static byte[] key;
 
    public static void GenerateKey(String keyFilePath){
    	String s=null;
    	Stack<String> stack=new Stack<String>();
    try  
    {  
    File file=new File(keyFilePath);   
    Scanner input=new Scanner(file);
    int count=0;
    while(input.hasNextLine()) { 
    	stack.push(input.next());
    	count++;
    }

    int top=count-1;
    int tophash = 0;

    	while(top!=0)
    {

    	
    	String str1=stack.pop();
    	String str2=stack.pop();
    	String str3=str1+str2;
    	tophash=str3.hashCode();
    	s=Integer.toString(tophash);
    	stack.push(s);
    	top--;	
    }
    	key=s.getBytes();
    	key = Arrays.copyOf(key, 16); 
    	secretKey = new SecretKeySpec(key, "AES");
    
    }
    catch(Exception e)  
    {  
    e.printStackTrace();  
    }  
    }
 
    public static String encrypt(String strToEncrypt) 
    {
        try
        {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } 
        catch (Exception e) 
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }
 
    public static String decrypt(String strToDecrypt) 
    {
        try
        {
            
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } 
        catch (Exception e) 
        {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }
    public static String readFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            return sb.toString();
        } finally {
            br.close();
        }
    }
    public static String readFile2(String filePath) throws IOException {
    	File file = new File(filePath);
    	String scannedline = null;
    	//String scannedline1 = null;

    	
		try {
		Scanner scan = new Scanner(file);
		while (scan.hasNextLine()) {
			scannedline = scan.nextLine();
		}
		scan.close();
		return scannedline;
		
		} catch (Exception e) {
			return("one more error");
		}
    }
    
 
    
    
    public String Result(String dataFile,String keyFile) throws IOException 
    {
    	
    	 AES.GenerateKey(keyFile);
         
        String originalString = AES.readFile(dataFile);
        String encryptedString = AES.encrypt(originalString) ;
        String decryptedString = AES.decrypt(encryptedString) ;  
       
        
       return(encryptedString);

            
        
        
        
    }
    public String Result2(String dataFilePath,String keyFile) throws IOException 
    {
    	
    	
    	 AES.GenerateKey(keyFile);
     
        String encryptedString = AES.readFile2(dataFilePath);
        String decryptedString = AES.decrypt(encryptedString) ;  
       
        
       return(decryptedString);

            
        
        
        
    }
}