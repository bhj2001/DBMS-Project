<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>

<html>
<title>Your Managers</title>
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
	<h2>Your Managers</h2>
	<table>
	  <tr>
	    <th>Mnager Id</th>
	  </tr>
		<c:forEach var="fulllist" items="${managers}">
		    <tr>
		    	<td id="${fulllist}">${fulllist}</td>
		    </tr>
		</c:forEach>
	</table>
</body>
</html>