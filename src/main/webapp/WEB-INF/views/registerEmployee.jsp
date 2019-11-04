<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<head>
<title>Add employee</title>
<style>
body {
	padding: 20px;
}
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
<h1>Register Employee</h1>
	<form:form method="post" modelAttribute="e" action="registerEmployee">
		<table><tr><td>
		Employee Identity</td><td>
		<form:input path="empid" type="text" /> </td><!-- bind to user.name-->
		<form:errors path="empid" /> </tr>
		
		<tr><td>
		Password</td><td>
		<form:input path="password" type="password" /> </td><!-- bind to user.name-->
		<form:errors path="password" /></tr>
		
		<tr><td>Confirm password </td>
		<td>
		<form:input path="mPassword" type="password" /> </td>
		<form:errors path="mPassword" /></tr>
		
		<tr><td>Name</td>
		<td>
		<form:input path="name" type="text" /> </td>
		<form:errors path="name" /></tr>
		
		<tr><td>Email</td>
		<td>
		<form:input path="email" type="text" /> </td><!-- bind to user.name-->
		<form:errors path="email" /></tr>
		
		<tr><td>Mobile Number</td>
		<td>
		<form:input path="phone" type="number" min="0"/> </td><!-- bind to user.name-->
		<form:errors path="phone" /></tr>
        
        <tr><td>Position</td>
            <td>
            <form:input path="position" type="text" /> </td><!-- bind to user.name-->
            <form:errors path="position" /></tr>

        <tr><td>Address</td>
            <td>
            <form:input path="address" type="text" /> </td><!-- bind to user.name-->
            <form:errors path="address" /></tr>
        
		<tr>
			<td></td>
			<td><input type="submit" value="Submit" /></td>
			</tr>
		${error}
		</table>
	</form:form>
</body>