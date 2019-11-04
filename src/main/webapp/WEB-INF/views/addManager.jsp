<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
<title>Add Manager</title>
</head>
<body>
<h2>Add Manager Of an Employee</h2>
	<form:form method="post" modelAttribute="manages" action="managerAdded">
		<table>
        <tr><td>
		    Manager Id</td><td>
            <form:input path="managerid" type="text" /></td><!-- bind to user.name-->
            <form:errors path="managerid" /></tr>
        
        <tr><td>
            Employee Id</td><td>
            <form:input path="empid" type="text" /></td><!-- bind to user.name-->
            <form:errors path="empid" /></tr>
        
        <tr>
            <td></td>
            <td><input type="submit" value="Submit" /></td>
            </tr>
		${error}
		</table>
	</form:form>
</body>