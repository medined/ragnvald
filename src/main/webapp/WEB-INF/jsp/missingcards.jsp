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
                <tr>
                    <td valign="top"><img src='images/<fmt:formatNumber pattern="000" value="${set.number}" />.png' height='20' width='20'/></td>
                    <td valign="top"><fmt:formatNumber pattern="000" value="${set.number}" />: <c:out value="${set.name}" /></td>
                    <td valign="top"><c:out value="${inventoryRepository.countMissingCards(set.rootName)}"/></td>
                    <td valign="top"><c:forEach items="${inventoryRepository.findByPokemonSetRootName(set.rootName)}" var="card"><c:if test="${card.count < 1}"><c:out escapeXml="false" value="${displayAdapter.card(card)}" /> </c:if></c:forEach></td>
                </tr>
            </c:forEach>
        </table>
    </table>

    <!--
    <b>001</b> <b>002</b> <b>003</b> <b>004</b> <b>005</b> <b>006</b> <b>007</b> <b>008</b> <b>009</b> <b>010</b> <b>011</b> <b>012</b> <b>013</b> <b>014</b> <b>015</b> <b>016</b> <b>017</b> <b>018</b> <b>019</b> <b>021</b> <b>036</b> <b>037</b> <b>049</b> <b>055</b> <b>056</b> <b>063</b> <b>064</b> <b>070</b> <b>071</b> <b>072</b> <b>073</b> <b>074</b> <b>076</b> <b>077</b> <b>078</b> <b>079</b> <b>082</b> <b>089</b> <b>096</b> <b>100</b> <b>102</b>
    -->
</body>
</html>
