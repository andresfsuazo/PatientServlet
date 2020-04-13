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
<script>
function switchDiv(id){
	div1 = document.getElementById('1');
	div2 = document.getElementById('2');
	div3 = document.getElementById('3');
    div1.style.display = "none";
    div2.style.display = "none";
    div3.style.display = "none";
    
    div = document.getElementById(id);
    div.style.display = "block";
    
    
}

function searchTable() {
    var input, filter, found, table, tr, td, i, j;
    input = document.getElementById("myInput");
    filter = input.value
    table = document.getElementById("myTable");
    tr = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td");
        if (td[0].innerHTML == filter) {
            found = true;
            break;
        }else{
        	found = false;
        }
    }
    if (found) {
        document.getElementById('currentID').innerHTML = "" + td[0].innerHTML;
        document.getElementById('currentP').innerHTML =  "" + td[1].innerHTML;
        document.getElementById('currentR').innerHTML =  "" + td[2].innerHTML;
        document.getElementById('currentP1').innerHTML = "" + td[3].innerHTML;
        document.getElementById('currentP2').innerHTML = "" + td[4].innerHTML;
        document.getElementById('currentComplete').innerHTML = "" + td[0].innerHTML + " " + td[1].innerHTML + " " + td[2].innerHTML + " " + td[3].innerHTML + " " + td[4].innerHTML;
        found = false;
    } else {
        alert("not found");
    }
    
}

</script>
	<form class="login" action="HelloServlet" method="get">
		<div style="padding-left: 15px; padding-top: 15px;">
			<input class = "optionBots2" type="Submit" value="LOGOUT" name=""/>
		</div>
	</form>
	<h1>Patient Manager</h1>
	<form class="login" action="HelloServlet" method="get">
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
			<div class="tbl-content" id='myTable'>
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
			<section class="top_container2">
			<div class = "one1" style="padding-top: 0px;" >
				<p id="1currentID" class="selecetd" style="padding-left: 100px;">ID:</p>
				<p id="1currentP" class="selecetd" style="padding-left: 100px;">Prediction</p>
				<p id="1currentR" class="selecetd" style="padding-left: 100px;">Result</p>
				<p id="1currentP1" class="selecetd" style="padding-left: 100px;" >Protein 1</p>
				<p id="1currentP2" class="selecetd" style="padding-left: 100px;">Protein 2</p>
			</div>
			<div class = "two2" style="padding-top: 0px;">
				<p id="currentID" class="selecetd" style="padding-right: 0px;"></p>
				<p id="currentP" class="selecetd" style="padding-right: 0px;"></p>
				<p id="currentR" class="selecetd" style="padding-right: 0px;"></p>
				<p id="currentP1" class="selecetd" style="padding-right: 0px;"></p>
				<p id="currentP2" class="selecetd" style="padding-right: 0px;"></p>
			</div>
			</section>
			<div class = "field" style="padding-top: 35px; padding-left: 100px;" align="center">
				<label class="selecetdinline" >Search ID</label>
				<input type="number" MIN="0" STEP="1" VALUE="0" name="patientID" id='myInput'/>
				<input id='search' type="button" value="Search" name="Search" onclick="searchTable()"/>
			</div>
		</div>
	</section>
	<section class="top_container">
		<div class = "bottom-one" align="center">
			<div class = "field" align="center" style="padding-left: 100px;" >
					<input class = "optionBots" type="button" value="Add" name="Add" onclick="switchDiv('1')"/>
					<input class = "optionBots" type="button" value="Edit" name="Edit" onclick="switchDiv('2')"/>
					<input class = "optionBots" type="button" value="Remove" name="Remove" onclick="switchDiv('3')"/>
			</div>
			<div id="1" class = "field" style="display:none; padding-top: 15px; padding-left: 100px;" align="center">	
    			<input type="file" value="Select File" id="Select_File" accept=".csv" name="Select_File">
    			<input class = "optionBots2" type="Submit" value="Submit" name="Add" />
			</div>
			<div id="2" class = "field" style="padding-top: 15px; padding-left: 100px;" align="center">
		    	<input type="radio" id="CR" name="result" value="CR">
		    	<label for="CR" class="selecetd" style="font-size : 18px; padding-right: 30px; padding-left: 0px;">CR</label>
		    	<input type="radio" id="DP" name="result" value="DP">
		    	<label for="DP" class="selecetd" style="font-size : 18px; padding-right: 30px; padding-left: 0px;">DP</label>
				<input class = "optionBots2" type="Submit" value="Submit" name="Edit" />
			</div>
			<div id="3" class = "field" style="display:none; padding-top: 15px; padding-left: 100px;" align="center">
		    	<label id="currentComplete" style="font-size : 18px; padding-right: 30px; padding-left: 0px;" class="selecetd">Select Patient</label>
				<input class = "optionBots2" type="Submit" value="Submit" name="Remove" />
			</div>
		</div>
	</section>
	</form>
</body>
</html>