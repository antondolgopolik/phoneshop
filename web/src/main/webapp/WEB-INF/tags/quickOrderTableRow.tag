<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ attribute name="i" type="java.lang.Integer" required="true" %>

<tr>
    <td>
        <form:input path="cartAdditions[${i}].model" placeholder="Model"
                    cssErrorClass="form-control is-invalid" cssClass="form-control"/>
        <form:errors path="cartAdditions[${i}].model"/>
    </td>
    <td>
        <form:input path="cartAdditions[${i}].quantity" placeholder="Quantity"
                    cssErrorClass="form-control is-invalid" cssClass="form-control"/>
        <form:errors path="cartAdditions[${i}].quantity"/>
    </td>
</tr>