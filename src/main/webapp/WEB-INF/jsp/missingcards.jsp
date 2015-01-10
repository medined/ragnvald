<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
    <head>
        <meta http-equiv="Content-type" content="text/html; charset=utf-8">
        <title>Ragnvald - Missing Pokemon Cards</title>
        <style>
            pre {
                white-space: pre-wrap;
            }
            tr > td
            {
                padding-bottom: 1em;
            }
    </style>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <h1>Missing Pokemon Cards</h1>
        <table border="1">
            <tr>
                <th>Icon</th>
                <th width="100px">Set</th>
                <th>Missing Count</th>
                <th>Missing Cards</th>
            </tr>
            <c:forEach items="${pokemonSetRepository.findAll()}" var="set">
                    <c:if test="${set.number != -1}">
                <tr>
                    <td valign="top"><img src='images/<fmt:formatNumber pattern="000" value="${set.number}" />.png' height='20' width='20'/></td>
                    <td valign="top">
                        <fmt:formatNumber pattern="000" value="${set.number}" />: 
                        <c:if test="${set.master}">MASTER</c:if>
                        <c:out value="${set.name}" />
                    </td>
                    <td valign="top" style="font-size: 10px">
                        <c:out value="${missingCardCounter.countMissingCards(set.rootName)}"/>
                        /
                        <c:out value="${missingCardCounter.fullSetSize(set.rootName)}"/>
                    </td>
                    <td valign="top" style="font-size: 12px">${missingCardCounter.missingCards(set.rootName)}</td>
                </tr>
                </c:if>
            </c:forEach>
        </table>
    </table>
</body>
</html>
