<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>

<html>
<title>List of Orders</title>
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
	<h2>All orders</h2>
	<table>
	  <tr>
	    <th>Product Id</th>
        <th>Name of Produt</th>
	    <th>Manufactured By</th>
        <th>Chemicals Contined</th>
	    <th>Cost</th>
	  </tr>
		<c:forEach var="fulllist" items="${productlist}">
			<tr>
		    	<td id="${fulllist[0]}">${fulllist[0]}</td>
		    	<td>${fulllist[1]}</td>
		    	<td>${fulllist[2]}</td>
		    	<td>${fulllist[3]}</td>
		    	<td>${fulllist[4]}</td>
		    </tr>
		</c:forEach>
	</table>
</body>
</html>