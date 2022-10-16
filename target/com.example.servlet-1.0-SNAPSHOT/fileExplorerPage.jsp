<%@ page import="java.io.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Files.com</title>
</head>
<body>
    <p>${date}</p>
    <header>
        <h1>${currDir}</h1>
    </header>
    <hr></hr>
    <c:url var="goBack" value="files">
        <c:param name="path" value="${prevDir}"/>
    </c:url>
    <p><a href="${goBack}">Вверх</a></p>

    <table>
        <tr>
            <th>Файл</th>
            <th>Размер</th>
            <th>Дата</th>
        </tr>
        <c:forEach var="item" items="${allItems}">

            <c:url var="openDir" value="files">
                <c:param name="path" value="${item.path}"/>
            </c:url>

            <tr>
                <td>
                    <c:if test="${item.directory == true}">
                        <a href="${openDir}">
                            <c:out value="${item.name}"/>
                        </a>
                    </c:if>
                    <c:if test="${item.directory == false}">
                        <a href="download?filename=${item.path}">
                            <c:out value="${item.name}"/>
                        </a>
                    </c:if>
                </td>
                <td><c:out value="${item.size}"/></td>
                <td><c:out value="${item.date}"/></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>