<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>new jsp</title>

<style type="text/css"> 
    body { 
     background: #fa7b8a;
    } 
    
    .container4{
              
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

<div class="container4">
<h2>${requestScope.message}</h2>
<a href="FileDownloadServlet">Download your decrypted file here</a>
</div>

</body>
</html>