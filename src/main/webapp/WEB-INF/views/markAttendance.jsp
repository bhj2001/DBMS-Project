<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>

<html>
<head>
<title>Mark Attendance</title>
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
</style>
</head>
<body>
	<h2>Mark Attendance</h2>
	<h4>To be marked only once a day</h4>
	<form:form method="post" modelAttribute="attendance" action="attendanceMarked">
	<table>
	  <tr>
	    <th>Name</th>
	    <th>Present</th>
	    <th>Absent</th>
	   </tr>
		<c:forEach var="fulllist" items="${employeesnamelist}">
		<tr>
			<td>
		    ${fulllist}
		    </td>
		    <td>
		    <form:radiobutton path="ma[${fulllist}]" value="Present" />Present</td>
		    <td><form:radiobutton path="ma[${fulllist}]" value="Absent" />Absent </td>
			<form:errors path="ma[${fulllist}]" />
		</tr>
		</c:forEach>
		
		<tr>
			<td></td>
			<td><input type="submit" value="Submit" /></td>
			<td></td>
			</tr>
	</table>
	</form:form>
</body>
</html>