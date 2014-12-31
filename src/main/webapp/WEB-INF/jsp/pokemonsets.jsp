<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
    <head>
        <meta http-equiv="Content-type" content="text/html; charset=utf-8">
        <title>Ragnvald - Pokemon Inventory</title>
        <link rel="stylesheet" type="text/css" href="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css" />
        <link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.4/css/jquery.dataTables.min.css">
        <script type="text/javascript" language="javascript" src="//code.jquery.com/jquery-1.11.1.min.js"></script>
        <script type="text/javascript" language="javascript" src="//cdn.datatables.net/1.10.4/js/jquery.dataTables.min.js"></script>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <script type="text/javascript" class="init">
            $(document).ready(function () {
                $('#example').DataTable({
                    "scrollY": "400px",
                    "scrollCollapse": true,
                    "paging": false
                });
            });
        </script>
        
        <h1>Pokemon Set Management</h1>

        <table id="example" class="display" width="100%" cellspacing="0">
            <thead>
                <tr>
                    <th>Number</th>
                    <th>Name</th>
                    <th>Count</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${pokomonSetRepository.findAll()}" var="entry">
                    <tr>
                        <td>
                            <c:if test="${entry.number != -1}">
                                <c:out value="${entry.number}" />
                            </c:if>
                        </td>
                        <td><a href="/inventory/<c:out value="${entry.rootName}" />" target="_blank"><c:out value="${entry.name}" /></a></td>
                        <td><c:out value="${entry.count}" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
