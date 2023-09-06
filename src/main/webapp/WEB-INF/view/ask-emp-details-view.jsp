<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Please enter your details</h2>
<br>
<br>

<form:form action="showDetails" modelAttribute="employee">

    Name <form:input path="name"/>
    <br>
    Surname <form:input path="surname"/>
    <br>
    Salary <form:input path="salary"/>
    <br>
    Department <form:select path="department">
<%--    <form:option value="Information Technology" label="IT"/>--%>
<%--    <form:option value="Human Resources" label="HR"/>--%>
<%--    <form:option value="Sales" label="Sales"/>--%>
    <form:options items="${employee.departments}"/>
    </form:select>
    <br>
    Which car do you drive?
<%--    BMW <form:radiobutton path="carBrand" value="BMW"/>--%>
<%--    Toyota <form:radiobutton path="carBrand" value="Toyota"/>--%>
<%--    Merc <form:radiobutton path="carBrand" value="Mercedes"/>--%>
    <form:radiobuttons path="carBrand" items="${employee.carBrands}"/>
    <br>
    <input type="submit" value="OK">
</form:form>

</body>
</html>
