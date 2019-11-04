<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>

<html>
<title>People whome you manage</title>
<style>
table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 100%;
}
td, th {
    border: 1px solid #dddddd;
    text-align: left;
    padding: 8px;
}
tr:nth-child(even) {
    background-color: #dddddd;
}
</style>
<body>
	<h2>People whome you manage</h2>
	<table>
	  <tr>
	    <th>Employee Id</th>
	  </tr>
		<c:forEach var="fulllist" items="${subordinates}">
		    <tr>
		    	<td id="${fulllist}">${fulllist}</td>
		    </tr>
		</c:forEach>
	</table>
</body>
</html>