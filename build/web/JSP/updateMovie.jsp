<%-- 
    Document   : updateMovie
    Created on : 31-Oct-2015, 13:30:30
    Author     : mosza16
--%>

<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <a href="/MovieWebApp/home
           "> Back </a><br>
        <a href="/MovieWebApp/newMovies?active=a
           "> check For update </a><br>
        <a href="/MovieWebApp/newMovies?index=all
           "> ADD all </a>
        <h1>new Movie!</h1>



        <c:choose>
            <c:when test="${sessionScope.movies!=null}">
                <c:forEach items="${sessionScope.movies}" var="n" varStatus="Mycount">
                    <a href="/MovieWebApp/newMovies?index=${Mycount.index}"><img src="${n.image_url}" width="320" height="240"/></a>
                    <h2>${n.movie_name}</h2>
                </c:forEach>
            </c:when>

        </c:choose>
        <c:if test="${paths!=null}">
            <c:forEach items="${paths}" var="p" varStatus="Mycount">
                Path image:${p}
            </c:forEach>
        </c:if>

    </body>
</html>
