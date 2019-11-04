<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<style>
table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 50%;
}
td, th {
    border: 1px solid #dddddd;
    text-align: left;
    padding: 8px;
}
tr:nth-child(even) {
    background-color: #dddddd;
}
body {
	padding: 20px;
}
</style>
<title>
Inventory</title>
</head>
<body>
	<h2>Inventory</h2>


		<table>

		<tr>
			<td>Product Id</td>
			<td>Quantity Available</td>
			</tr>

 		<form:form method="post" modelAttribute="inventory" action="inventoryAltered">
		
		<c:forEach var="listitem" items="${prodlist}">
            <tr><td>
			${listitem}: </td><td>
			<form:input path="ma[${listitem}]" type="number"  min="0" /></td>
			<form:errors path="ma[${listitem}]" /></tr>
        </c:forEach>
		
		
		<tr>
			<td></td>
			<td><input type="submit" value="Submit" /></td>
			</tr>
		${error}
		</table>
	</form:form>

</body>
</html>