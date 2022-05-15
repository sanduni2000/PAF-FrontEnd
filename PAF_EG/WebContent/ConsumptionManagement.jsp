<%@page import="model.Cunsumption"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
if (request.getParameter("ID") != null) 
{ 
	Cunsumption consumpObj = new Cunsumption(); 
 String stsMsg = ""; 
//Insert--------------------------
if (request.getParameter("hidIDSave") == "") 
 { 
 stsMsg = consumpObj.insertconsumption(request.getParameter("ID"), 
 request.getParameter("Month"), 
 request.getParameter("Date")
); 
 } 
else//Update----------------------
 { 
	
	//String ID,String Dname, String Month,String Note) 
 stsMsg = consumpObj.updateCunsumption(request.getParameter("hidIDSave"), 
 request.getParameter("Dname"),
 request.getParameter("Month"),
 request.getParameter("Note")
); 
 } 
 session.setAttribute("statusMsg", stsMsg); 
} 
//Delete-----------------------------
if (request.getParameter("hidIDDelete") != null) 
{ 
	Cunsumption consumpObj = new Cunsumption(); 
 String stsMsg = consumpObj.deleteCunsumption(request.getParameter("hidIDDelete")); 
 session.setAttribute("statusMsg", stsMsg); 
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Consumption Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.4.1.min.js"></script>
<script src="Components/ConsumptionManagement.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Consumption Management</h1>

				<form id="formConsumptionManagement" name="formConsumptionManagement" method="post" action="ConsumptionManagement.jsp">


					<!-- DISTRIC NAME -->
					 <div class="input-group input-group-sm mb-3">
					 <div class="input-group-prepend">
					 <span class="input-group-text" id="lblName">Distric Name: </span>
					 </div>
					 <input type="text" id="Dname" name="Dname"></div>
										 
					 <!-- MONTH -->
					 <div class="input-group input-group-sm mb-3">
					 <div class="input-group-prepend">
					 <span class="input-group-text" id="lblName">MONTH: </span>
					 </div>
					 <input type="text" id="MONTH" name="MONTH"></div>
					 
					 <!-- NOTE -->
					 <div class="input-group input-group-sm mb-3">
					 <div class="input-group-prepend">
					 <span class="input-group-text" id="lblName">NOTE </span>
					 </div>
					 <input type="text" id="NOTE" name="NOTE">
					 </div>
					 
											
											
						<br> <input
						id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary"> <input type="hidden"
						id="hidIDSave" name="hidIDSave" value="Save">
				</form>

				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>

				<br>
				<div id="divProjectGrid">
					<%
					Cunsumption consumpObj = new Cunsumption();
						out.print(consumpObj.readCunsumption());
					%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
