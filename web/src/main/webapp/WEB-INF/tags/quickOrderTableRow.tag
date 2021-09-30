<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ attribute name="quickOrderForm" type="com.es.core.dto.cart.QuickOrderFormDto" required="true" %>
<%@ attribute name="i" type="java.lang.Integer" required="true" %>

<%--@elvariable id="quickOrderForm" type="com.es.core.dto.cart.QuickOrderFormDto"--%>

<tr>
    <td>
        <form:input path="cartAdditions[${i}].model" placeholder="Model" cssClass="form-control"/>
        <c:if test="${not empty quickOrderForm.cartAdditions.get(i).model or not empty quickOrderForm.cartAdditions.get(i).quantity}">
            <form:errors path="cartAdditions[${i}].model"/>
        </c:if>
    </td>
    <td>
        <form:input path="cartAdditions[${i}].quantity" placeholder="Quantity" cssClass="form-control"/>
        <c:if test="${not empty quickOrderForm.cartAdditions.get(i).model or not empty quickOrderForm.cartAdditions.get(i).quantity}">
            <form:errors path="cartAdditions[${i}].quantity"/>
        </c:if>
    </td>
</tr>