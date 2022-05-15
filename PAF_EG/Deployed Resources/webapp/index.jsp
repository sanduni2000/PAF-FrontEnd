<%@page import="model.Cunsumption"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
if (request.getParameter("itemCode") != null) 
{ 
	Cunsumption consumpObj = new Cunsumption(); 

 String stsMsg = ""; 
//Insert--------------------------
if (request.getParameter("hidItemIDSave") == "") 
 { 
 stsMsg = consumpObj.insertconsumption( 
 request.getParameter("Dname"), 
 request.getParameter("Month"), 
 request.getParameter("Note")); 
 } 
else//Update----------------------
 { 
 stsMsg = consumpObj.updateCunsumption(request.getParameter("hidItemIDSave"), 
 request.getParameter("Dname"), 
 request.getParameter("Month"),  
 request.getParameter("Note")); 
 } 
 session.setAttribute("statusMsg", stsMsg); 
} 
//Delete-----------------------------
if (request.getParameter("hidItemIDDelete") != null) 
{ 
	Cunsumption consumpObj = new Cunsumption(); 
 String stsMsg = 
		 consumpObj.deleteCunsumption(request.getParameter("hidIDDelete")); 
 session.setAttribute("statusMsg", stsMsg); 
}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Consumption Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/items.js"></script>
</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> 
<h1>Items Management V10.1</h1>
<form id="formConsumption" name="formConsumption">
 

 <br> Distric Name: 
 <input id="Dname" name="Dname" type="text" 
 class="form-control form-control-sm">
 <br> Month: 
 <input id="Month" name="Month" type="text" 
 class="form-control form-control-sm">
 <br> Note: 
 <input id="Note" name="Note" type="text" 
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidIDSave" 
 name="hidIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divItemsGrid">
 <%

 %>
</div>
</div> </div> </div> 
</body>
</html>
