<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
<!-- CSS -->
        <link href="<c:url value="/resources/home/css/main.css"/>" rel="stylesheet">

        <!-- JS -->
        <script src="<c:url value="/resources/home/js/vendor/modernizr-2.8.3.min.js"/>"></script>
        <script src="<c:url value="/resources/home/js/vendor/jquery-1.12.0.min.js"/>"></script>
<style>
ul {
	list-style: none;
}
#footerlinks {
    /*background:#ccc;*/
    overflow:hidden;
    padding:5px;
    width:100%;
}
#footerlinks li {
	text-align: center;
    float:left;
    padding:5px 0;
    width:50%;
}
body {
	background-color: #90ee90;
}
a.logt {
	padding-right: 20px;
    color: #2da1c1;
    text-decoration: none;
    float: right;
}
a.logt:hover {
    color: #f90;
    text-decoration: underline;         
}
​
</style>
<title>Admin page</title>
</head>
<body>
    <h1 style="padding-left: 10px">Welcome to Admin area</h1>
    
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <h2 style="padding-left: 10px">
            Welcome : ${user} <a class="logt"
                href="<c:url value="/logout" />"> Logout</a>
        </h2>
    </c:if>
    
    <ul id="footerlinks">
    <li><div class="title__action"><a href="admin/registerEmployee" class="btn btn-primary">Add employee</a></div></li>
    <li><div class="title__action"><a href="admin/viewEmployees"  class="btn btn-primary">View Employees</a> </div></li>
    <li><div class="title__action"><a href="admin/addManager"  class="btn btn-primary">Add Manager of an employee</a> </div></li>
    <li><div class="title__action"><a href="admin/viewClients"  class="btn btn-primary">View Clients</a></div></li>
    <li><div class="title__action"><a href="admin/viewAllOrders" class="btn btn-primary">View All Orders</a></div></li>
    <li><div class="title__action"><a href="/admin/markAttendance" class="btn btn-primary">Mark Attendance</a></div></li>
    <li><div class="title__action"><a href="/admin/viewAllAttendance" class="btn btn-primary">View Attendance</a></div> </li>
    <li><div class="title__action"><a href="admin/viewFeedbacks" class="btn btn-primary">View Feedbacks</a> </div></li>
    <li><div class="title__action"><a href="admin/addProduct" class="btn btn-primary">Add Product</a></div></li>
    <li><div class="title__action"><a href="admin/alterPricings" class="btn btn-primary">Alter Pricings</a></div></li>
    <li><div class="title__action"><a href="admin/viewInvoices" class="btn btn-primary">View Invoices</a></div></li>
    <li><div class="title__action"><a href="admin/viewCompanyList" class="btn btn-primary">View Company List</a></div></li>
    <li><div class="title__action"><a href="admin/viewChemicalList" class="btn btn-primary">View Chemical List</a></div></li>
    <li><div class="title__action"><a href="admin/viewProducts" class="btn btn-primary">View Products</a></div></li>
    <li><div class="title__action"><a href="admin/inventory" class="btn btn-primary">Inventory</a></div></li>
    </ul>
</body>
</html>