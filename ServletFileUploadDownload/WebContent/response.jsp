<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "https://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Upload File Response</title>



<style type="text/css"> 
body { 
 background: #fa7b8a;
} 

.container3{
          
          max-width: 700px;
          margin: auto;
          text-align: center;
          padding: 4rem 4rem;
          background-color: rgb(241, 234, 234);
          border-radius: 1rem;
          text-align: center;
          
}
</style>
</head>
<body>
	<%-- Using JSP EL to get message attribute value from request scope --%>

    <div class="container3">
    <h2>${requestScope.message}</h2>
    <a href="index2.html">DECRYPT A FILE</a>
    </div>
  
    
</body>
</html>