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
                String msg = "";
                
                if(session.getAttribute("msg") != null){
                    msg = session.getAttribute("msg").toString();
                }
                
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
                            
                            <h2><%=msg%></h2>
                            
                            <form action="./upload_file" method="post" enctype="multipart/form-data">
                                
                                <label>File : </label>
                                <input type="file" name="file"/><br/><br/>
                                       <label>Category : </label>
                                       <select name="catg">
                                           <option value="Select">Select</option>
                                           <option value="Happy">Happy</option>
                                           <option value="Sad">Sad</option>
                                           <option value="Angry">Angry</option>
                                           <option value="depression">Depression</option>
                                           <option value="surprise">Surprise</option>
                                       </select>
                                <input type="submit" value="Upload"/>
                                
                            </form>
			</div>
			
		</div>
		
	</body>
</html>
