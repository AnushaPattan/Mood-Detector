<%-- 
    Document   : index
    Created on : Aug 1, 2017, 3:18:17 PM
    Author     : Prateek
--%>

<%@page import="pkgLogic.info"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta name="description" content="" />
		<meta name="keywords" content="" />
		<title>File Upload/Download</title>
		<link href="http://fonts.googleapis.com/css?family=Abel" rel="stylesheet" type="text/css" />
		<link href="http://fonts.googleapis.com/css?family=Lobster" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="style.css" />
	</head>
	<body>
            
            <%
                String projName = info.proj_name;
            %>
            
            
		<div id="wrapper">
			<div id="header">
				<div id="logo">
					<h1><a href="#">FILE</a></h1>
					<span><%=projName%></span>
				</div>
				<div id="menu">
					<ul>
                                                
					</ul>
					<br class="clearfix" />
				</div>
			</div>
			<div id="page">			
                            <form action="./Login" method="post">
                                
                                <label>Admin ID : </label>
                                <input type="text" name="txtEmail"/><br/><br/>
                                
                                <label>Password : </label>
                                <input type="password" name="txtPass"/><br/><br/>
                                
                                <input type="submit" value="Login"/>
                                
                            </form>
			</div>
			
		</div>
		
	</body>
</html>
