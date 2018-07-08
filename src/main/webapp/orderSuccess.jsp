<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<jsp:include page="/WEB-INF/jspf/header.jsp" />
<link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/cart-ajax.js"></script>

<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
<div class="container">
  <h3>Order was successfuly added</h3>
  <p>${requestScope.order.status}</p>
  <p>${requestScope.order.statusDescription}</p>
  <p>${requestScope.order.payType}</p>
  <p>${requestScope.order.deliveryInfo}</p>
  <p>Items:</p>
  <c:forEach var='item' items='${requestScope.order.items}'>
    <p>${item.transport.model}</p>
  </c:forEach>
  <p>Total Price: $${requestScope.order.totalPrice}</p>
</div>
<jsp:include page="/WEB-INF/jspf/footer.jspf" />
