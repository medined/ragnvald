<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="breadcrumbs" style="margin-left: 10px">
<c:forEach items="${breadcrumbs}" var="breadcrumb">
    <c:if test="${breadcrumb.value.isEmpty()}">
        <c:out value="${breadcrumb.key}"/>
    </c:if>
    <c:if test="${breadcrumb.value.isEmpty() == false}">
        <a href='<c:out value="${breadcrumb.value}"/>'><c:out value="${breadcrumb.key}"/></a>
    </c:if>
</c:forEach>
</div>
