<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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
</style>
<title>Edit Profile</title>
<body>
<h1>Edit profile</h1>
	<form:form method="post" modelAttribute="client" action="saveClientProfile">
		<table>
        
        <tr><td>Shop Name</td><td>
        <form:input path="name" type="text" /> </td><!-- bind to user.name-->
        <form:errors path="name" /></tr>

		<tr><td>
        Phone Number</td><td>
        <form:input path="phone" type="number" min="0" /> </td><!-- bind to user.name-->
        <form:errors path="phone" /></tr>
		
		<tr><td>Email</td><td>
		<form:input path="email" type="text" /> </td><!-- bind to user.name-->
        <form:errors path="email" /></tr>
        
        <tr><td>Shop Address</td><td>
        <form:input path="shopaddress" type="text" /> </td><!-- bind to user.name-->
        <form:errors path="shopaddress" /></tr>
        
        <tr><td>Owners Name</td><td>
        <form:input path="ownersname" type="text" /> </td><!-- bind to user.name-->
        <form:errors path="ownersname" /></tr>

		<tr>
			<td></td>
			<td><input type="submit" value="Submit" /></td>
			</tr>
		${error}
		</table>
	</form:form>
</body>