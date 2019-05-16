<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
    <table id="versionsTable" border = 1>
        <thead>
            <tr>
                <td>Name</td>
                <td>Version</td>
                <td>Release Date</td>
                <td>DPD<br><c:out value="${dpds.getDpdPath()}"></c:out></td>
                <td>Last</td>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="record" items="${all}">
                <tr>
                    <td><c:out value="${record.getName()}"></c:out></td>
                    <td><c:out value="${record.getVersion()}"></c:out></td>
                    <td><c:out value="${record.getDate()}"></c:out></td>
                    <td><c:out value="${dpds.getLastVersion(record)}"></c:out></td>
                    <td>
                        <a href="/all/${record.getName()}/save/${record.getVersion()}">
                            <c:out value="${keeper.getSavedVersion(record)}"></c:out>
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>