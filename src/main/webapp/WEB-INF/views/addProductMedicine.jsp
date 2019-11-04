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
<title>Add Product</title>
</head>
<body>
<h2>Add Product</h2>
	<form:form method="post" modelAttribute="product" action="productAdded">
		<table><tr><td>
		Product ID</td><td>
		<form:input path="productid" type="text" /></td><!-- bind to user.name-->
        <form:errors path="productid" /></tr>
        
        <tr><td>
            Product Name</td><td>
            <form:input path="name" type="text" /></td><!-- bind to user.name-->
            <form:errors path="name" /></tr>

        <tr><td>
            Cost</td><td>
            <form:input path="cost" type="Number" min="0" /></td><!-- bind to user.name-->
            <form:errors path="cost" /></tr>
        
        <tr><td>
            Companies that manufacture this product</td><td>
            <form:input path="companies" type="text" /></td><!-- bind to user.name-->
            <form:errors path="companies" /></tr>
        
            
        <tr><td>
            Chemicals containes in this product</td><td>
            <form:input path="chemicals" type="text" /></td><!-- bind to user.name-->
            <form:errors path="chemicals" /></tr>
		<tr>
			<td></td>
			<td><input type="submit" value="Submit" /></td>
			</tr>
		${error}
		</table>
	</form:form>
</body>