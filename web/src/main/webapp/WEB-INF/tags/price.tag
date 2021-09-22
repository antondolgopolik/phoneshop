<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="price" type="java.math.BigDecimal" required="true" %>

<%--@elvariable id="price" type="java.math.BigDecimal"--%>

<c:if test="${empty price}">
    Unknown
</c:if>
<c:if test="${not empty price}">
    ${price}$
</c:if>