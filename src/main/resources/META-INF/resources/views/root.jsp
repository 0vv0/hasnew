<!DOCTYPE html>
<!--%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%-->
<%@ page errorPage ="error.jsp"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>root page</title>
    </head>
    <body>
    <p>View all at once <a href="${all}">here</a>
    <p>
        <table>
            <tbody>
                <c:forEach  var="record"  items="${names}" >
                           <tr>
                               <td><c:out value="${record}"></c:out></td>
                           </tr>
                </c:forEach>
            </tbody>
       </table>
    </body>
</html>