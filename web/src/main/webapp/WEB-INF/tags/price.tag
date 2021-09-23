<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="phone" type="com.es.core.model.phone.Phone" required="true" %>

<%--@elvariable id="phone" type="com.es.core.model.phone.Phone"--%>

<c:if test="${empty phone.price}">
    Unknown
</c:if>
<c:if test="${not empty phone.price}">
    ${phone.price}$
</c:if>