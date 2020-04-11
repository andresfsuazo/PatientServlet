<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Home</title>
	<link rel="stylesheet" type="text/css" href="css/home.css">
</head>
<body>
<h1>Patient Manager</h1>
<section class="top_container">
	<div class = "one">
	 	<div class="tbl-header">
	    	<table cellpadding="0" cellspacing="0" border="0">
		      <thead>
		        <tr>
		          <th>ID</th>
		          <th>Prediction</th>
		          <th>Result</th>
		          <th>Protein 1</th>
		          <th>Protein 2</th>
		        </tr>
		      </thead>
		    </table>
	  	</div>
		<div class="tbl-content">
			<table cellpadding="0" cellspacing="0" border="0">
		    	<tbody>
			    	<%
		        	String data = (String)request.getAttribute("patientData");
			        out.print(data);
			   		 %>
			      </tbody>
			    </table>
			</div>
	</div>
	<div class = "two">
		<p class="selectedTitle" align="center">Current Selection</p>
		<p class="selecetd">ID:</p>
		<p class="selecetd">Prediction</p>
		<p class="selecetd">Result</p>
		<p class="selecetd">Protein 1</p>
		<p class="selecetd">Protein 2</p>
		<div class = "field">
			<label class="selecetdinline" >Search ID</label>
			<input type="number" MIN="0" MAX="10" STEP="1" VALUE="1">
			<input type="Submit" value="Search" name="Submit">
		</div>
	</div>
</section>
</body>
</html>