<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" type="text/css" href="css/mystyle.css">
</head>
<body>
 
<form:form method="post" action="create" id="signin">
    <fieldset>
        <legend>Welcome Please Login</legend>
    </fieldset>
    <c:if test="${not empty error}">
    	<div class="error">${error}</div>
    </c:if>
    <c:if test="${not empty error}">
    	<div class="msg">${msg}</div>
    </c:if>       
   <table>
   		<tr>
   			<td>User:</td>
   			<td><input type="text" name="username"></td>
   		</tr>
   		<tr>
   			<td>Password:</td>
   			<td><input type="password" name="password"></td>
   		</tr>
   		<tr>
   			<td colspan='2'><input type="submit" name="submit" value="submit"></td>
   		</tr>
   </table>
   
     <input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
   
 </form:form>
</body>
</html>