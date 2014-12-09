<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.aalvarez.model.Person" %>
<%@ page import="java.util.List"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title></title>
	<link rel="stylesheet" type="text/css" href="css/mystyle.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery-ui.js"></script>

	<script>
		function init(){
			getEmployees();
			resize();
		}
		
		function resize(){
			var wh = $(window).height();
			var ht = $('.perHead').height();
			var st = $('.headTitle').height();
			$("#inner-div").height(wh-ht-st-50);
		}
		
		function getEmployees(){
			var request = $.ajax({
				url : "/CounterWebApp/getEmployees.htm",
				type : "get",
			});
			request.done(function(persons){ 
				var innerdiv = $('#inner-div');
				innerdiv.html("");
				
				
				for(var i=0; i<persons.length; i++){
					var person = persons[i];
					var row = document.getElementById("req-div").children[0].cloneNode(true);
					if(i%2 == 0)
						row.style.backgroundColor = "#fff";
					else
						row.style.backgroundColor = "beige";
					row.id="person-"+person.id;
					row.children[0].children[0].innerHTML = person.id;
					row.children[0].children[1].children[0].value = person.id;
					
					row.children[1].children[0].innerHTML = person.firstName;
					row.children[1].children[1].children[0].value = person.firstName;
					
					row.children[2].children[0].innerHTML = person.middleName;
					row.children[2].children[1].children[0].value = person.middleName;
					
					row.children[3].children[0].innerHTML = person.lastName;
					row.children[3].children[1].children[0].value = person.lastName;
					
					row.children[4].children[0].innerHTML = person.address;
					row.children[4].children[1].children[0].value = person.address;
					
					row.children[5].children[0].innerHTML = person.city;
					row.children[5].children[1].children[0].value = person.city;
					
					row.children[6].children[0].innerHTML = person.state;
					row.children[6].children[1].children[0].value = person.state;
					
					row.children[7].children[0].innerHTML = person.zipCode;
					row.children[7].children[1].children[0].value = person.zipCode;
					
					row.children[8].children[0].children[0].setAttribute("onclick","edit("+person.id+")");
					row.children[8].children[1].children[0].setAttribute("onclick","deletePerson("+person.id+")");
					innerdiv.append(row);
				}
			});
		}
		
		
		function update(id){
			var row = document.getElementById('person-'+id);
			
			var serialized = "id="+id+"&firstName="+row.children[1].children[1].children[0].value+"&middleName="+row.children[2].children[1].children[0].value+"&lastName="+row.children[3].children[1].children[0].value;
				serialized += "&address="+row.children[4].children[1].children[0].value+"&city="+row.children[5].children[1].children[0].value;
				serialized += "&state="+row.children[6].children[1].children[0].value+"&zipCode="+row.children[7].children[1].children[0].value;
				
			var request = $.ajax({
				url : "/CounterWebApp/updateEmployee.htm",
				type : "post",
				data :serialized
			});
			request.done(function(person){ 
				var row = document.getElementById('person-'+person.id);
				row.children[0].children[0].innerHTML = person.id;
				row.children[1].children[0].innerHTML = person.firstName;
				row.children[2].children[0].innerHTML = person.middleName;
				row.children[3].children[0].innerHTML = person.lastName;
				row.children[4].children[0].innerHTML = person.address;
				row.children[5].children[0].innerHTML = person.city;
				row.children[6].children[0].innerHTML = person.state;
				row.children[7].children[0].innerHTML = person.zipCode;
				for(var i = 0; i < row.childElementCount; i++){
					if(i == 8){
						row.children[i].children[0].children[0].setAttribute('onclick',"edit("+id+")");
						row.children[i].children[0].children[0].innerHTML = "Edit";
					}else{
						row.children[i].children[0].style.display = "block";
						row.children[i].children[1].style.display = "none";
					}
				}
			});	
		}
		function edit(id){
			var row = document.getElementById('person-'+id);
			
			for(var i = 0; i < row.childElementCount; i++){
				if(i == 8){
					row.children[i].children[0].children[0].setAttribute('onclick',"update("+id+")");
					row.children[i].children[0].children[0].innerHTML = "Update";
				}else{
					row.children[i].children[0].style.display = "none";
					row.children[i].children[1].style.display = "block";
				}
			}
			
			
				
		}
		
		function insertNew(){
			var innerdiv = $('#inner-div');
			var row = document.getElementById("req-div").children[0].cloneNode(true);
			for(var i = 0; i < row.childElementCount; i++){
				if(i == 8){
					row.children[i].children[0].children[0].setAttribute('onclick',"createEmployees(this)");
					row.children[i].children[0].children[0].innerHTML = "Create";
				}else{
					row.children[i].children[0].style.display = "none";
					row.children[i].children[1].style.display = "block";
				}
			}
			innerdiv.append(row);
		}
		
		function createEmployees(div){
			var row = div.parentElement.parentElement.parentElement;
			var serializedData = "firstName="+row.children[1].children[1].children[0].value+"&middleName="+row.children[2].children[1].children[0].value+"&lastName="+row.children[3].children[1].children[0].value;
			serializedData += "&address="+row.children[4].children[1].children[0].value+"&city="+row.children[5].children[1].children[0].value;
			serializedData += "&state="+row.children[6].children[1].children[0].value+"&zipCode="+row.children[7].children[1].children[0].value;
			
			var request = $.ajax({
				url : "/CounterWebApp/createEmployee.htm",
				type : "post",
				data :serializedData
			});
			request.done(function(person){ 
				var row = document.getElementById("personnew");
				row.id = "person-"+person.id;
				row.children[0].children[0].innerHTML = person.id;
				row.children[0].children[1].children[0].value = person.id
				row.children[1].children[0].innerHTML = person.firstName;
				row.children[2].children[0].innerHTML = person.middleName;
				row.children[3].children[0].innerHTML = person.lastName;
				row.children[4].children[0].innerHTML = person.address;
				row.children[5].children[0].innerHTML = person.city;
				row.children[6].children[0].innerHTML = person.state;
				row.children[7].children[0].innerHTML = person.zipCode;
				
				for(var i = 0; i < row.childElementCount; i++){
					if(i == 8){
						row.children[i].children[0].children[0].setAttribute('onclick',"edit("+person.id+")");
						row.children[i].children[0].children[0].innerHTML = "Edit";
					}else{
						row.children[i].children[0].style.display = "block";
						row.children[i].children[1].style.display = "none";
					}
				}
				row.children[8].children[1].children[0].setAttribute("onclick","deletePerson("+person.id+")");
				var innerdiv = document.getElementById('inner-div');
				if(innerdiv.childElementCount%2 == 0)
					row.style.backgroundColor = "#fff";
				else
					row.style.backgroundColor = "beige";
			});	
		}
		
		function deletePerson(id){
			var row = document.getElementById('person-'+id);
			
			var serialized = "id="+id;
			
			var request = $.ajax({
				url : "/CounterWebApp/deleteEmployee.htm",
				type : "post",
				data :serialized
			});
			request.done(function(person){ 
				$('#person-'+id).remove();
			});	
		}
</script>
	
	
</head>
<body id="body" onload="init()">
<div class="headTitle">Welcome to a listing of people</div>
<div style="width:100%;">
	<div class="perHead" > 
		<div class="hiTitle" >ID</div>
		<div class="hTitle" >First Name</div>
		<div class="hTitle" >Middle Initial</div>
		<div class="hTitle">Last Name</div>
		<div class="haTitle" >Address</div>
		<div class="hTitle" >City</div>
		<div class="hTitle" style="width:7%;">State</div>
		<div class="hTitle" style="width:5%;">Zip</div>
		<div class="hNew">
		   <div><button onclick="insertNew()">New</button></div>
		</div>
	</div>
	<div id="inner-div" class="inner-div">	
	</div>

</div>

 <div id="req-div" style="display:none;">
 			<div id="personnew" class="personrow">
			<div class="id" >
				<div>100</div>
				<div style="display:none;"><input readonly type="textbox" value="" /></div>
			</div>
			<div class="first" >
				<div>Alexander</div>
				<div style="display:none;"><input type="textbox" value="" /></div>
			</div>
			<div class="middle">
				<div>A</div>
				<div style="display:none;"><input type="textbox" value="" /></div>
			</div>
			<div class="last">
				<div>Alvarez</div>
				<div style="display:none;"><input type="textbox" value="" /></div>
			</div>
			<div class="address">
				<div>10780 McNerney Av.</div>
				<div style="display:none;"><input type="textbox" value="" /></div>
			</div>
			<div class="city">
				<div>Lynwood</div>
				<div style="display:none;"><input type="textbox" value="" /></div>
			</div>
			<div class="state">
				<div>CA</div>
				<div style="display:none;"><input type="textbox" value="" /></div>
			</div>
			<div class="zip">
				<div>90262</div>
				<div style="display:none;"><input type="textbox" value="" /></div>
			</div>
			<div class="btnClass">
		        <div><button>Edit</button></div>
		        <div><button>Delete</button></div>
		    </div>
		</div>	
</div>
</body>
</html>