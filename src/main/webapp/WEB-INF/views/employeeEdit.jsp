<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<title>Edit profile</title>
<style>
body {
	padding: 20px;
}
table {
	/* align: center; */
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
<body>
<h1>Edit profile</h1>
	<form:form method="post" modelAttribute="employee" action="saveEmployeeProfile">
		<table>
		
		<tr><td>Name</td>
			<td>
			<form:input path="name" /> </td><!-- bind to user.name-->
			<form:errors path="name" /></tr>

		<tr><td>Email</td>
			<td>
			<form:input path="email" /> </td><!-- bind to user.name-->
			<form:errors path="email" /></tr>

		<tr><td>Phone</td>
			<td>
			<form:input path="phone" type="Number"  /> </td><!-- bind to user.name-->
			<form:errors path="phone" /></tr>
		
		<tr><td>Address</td>
			<td>
			<form:input path="address" /> </td><!-- bind to user.name-->
			<form:errors path="address" /></tr>

		
		
		<tr>
			<td></td>
			<td><input type="submit" value="Submit" /></td>
			</tr>
		${error}
		<!-- <form:hidden path="name" value="${employee.name}" /> -->
		</table>
	</form:form>
</body>