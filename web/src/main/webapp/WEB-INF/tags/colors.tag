<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="colors" type="java.util.Set" required="true" %>

<%--@elvariable id="colors" type="java.util.Set"--%>

<c:forEach var="color" items="${colors}" varStatus="colorStatus">
    ${color}<c:if test="${not colorStatus.last}">, </c:if>
</c:forEach>