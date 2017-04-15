<%-- 
    Document   : movie
    Created on : 17-Oct-2015, 15:22:36
    Author     : mosza16
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
       
        <style>
            body{
                background-image: url('https://picjumbo.imgix.net/HNCK8487.jpg?q=40&w=1650&sharp=30');
                color:seashell;
            }
            .b1{
                background: rgba(0%,0%,0%,0.1);
                border-radius: 500px;
                transition: background 1s;
                border-style: dashed;
            }
            button:hover ,input:hover {
                background-color: red;

            }
            input{
                background: rgba(0%,0%,0%,0.1);
                border-radius: 500px;
                transition: background 1s;
                border-style: dashed;
                width: 300px;
                padding: 8px;
                height: 60px;
                font-size: 18px;
            }
            h1{
                color: darkslategray;
            }
            h2{
                color: darkolivegreen;
            }
            td{
                background: rgba(0%,0%,0%,0.5);
                border-radius: 20px;
                text-align: center;
                transition: background 1s;

            }
            a{
                color: aliceblue;
            }
            a:hover,td:hover{
                font-weight: bold;
                background: rgba(0%,0%,0%,0.9);
            }
        </style>
    </head>
    <body>
        <div style="border-radius: 30px; background-color: rgba(100%,100%,100%,0.5);padding: 20px;">
            <div class="progress"> <span class="blue" style="width:0%;"><span>0%</span></span> </div>
           
            
            <c:choose >
                <c:when test="${movie !=null}">
                    <h1>${movie.movie_name}</h1>
                    <h2>IMBD${movie.imdb==-1?"non":movie.imdb}</h2>
                    <h2>CATEGORY : : ${cate}</h2>
                    <h2>VIEW : : ${movie.viewer}</h2>
                    <img src="${movie.image}" width="350px" height="500px">
                    <iframe id='i1' src="${movie.trailer_url}" width="640px" height="480px"></iframe>
                    <div style="border-radius: 20px;background-color: rgba(0%,0%,0%,0.5);width: 400px;
                         " >
                        <c:forEach items="${movie.links}" var="l">
                            <c:out value="${l.type}" />:
                            <c:out value="${l.resolution}" />::
                            <button class='b1' onclick='
                                    document.getElementById("i1").src = "<c:out value='${l.link_url}'/>";
                                    '>Watch</button>
                        </c:forEach>
                    </div>
                </c:when>
               
               

            </c:choose>
        </div> 
        
        
</html>
