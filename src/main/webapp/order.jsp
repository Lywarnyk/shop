<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<jsp:include page="/WEB-INF/jspf/header.jsp" />
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<fmt:setLocale value="${pageContext.request.locale}"/>
<%-- load the bundle (by locale) --%>
<fmt:setBundle basename="resource"/>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/cart-ajax.js"></script>

<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
<div class="container">
  <c:if test='${empty requestScope.orderItems}'><h3>The cart is empty</h3></c:if>
  <c:if test='${not empty requestScope.orderItems}'>
	<table id="cart" class="table table-hover table-condensed">
    				<thead>
						<tr>
							<th style="width:50%"><fmt:message key="column.product"/></th>
							<th style="width:10%"><fmt:message key="column.price"/></th>
							<th style="width:8%"><fmt:message key="column.quantity"/></th>
							<th style="width:10%"></th>
						</tr>
					</thead>
					<tbody>
            <c:if test='${empty requestScope.transports}'><tr><td>The cart is empty</td></tr></c:if>
            <c:forEach var='orderItem' items='${requestScope.orderItems}'>
						<tr id='orderline${orderItem.transport.id}'>
							<td data-th="Product">
								<div class="row">
									<div class="col-sm-2 hidden-xs"><img src="images/ng${orderItem.transport.id}.jpg" alt="..." class="img-responsive"/></div>
									<div class="col-sm-10">
										<h4 class="nomargin">${orderItem.transport.model}</h4>
										<p>${orderItem.transport.description}</p>
									</div>
								</div>
							</td>
							<td data-th="Price">$${orderItem.currentPrice}</td>
							<td data-th="Quantity">
								<input type="number" min='1' max='20' id='${orderItem.transport.id}' onChange="updateAmount(${orderItem.transport.id})" class="form-control text-center quantity" value="${orderItem.amount}">
							</td>
							<td class="actions" data-th="">
								<button class="btn btn-danger btn-sm" onClick='remove(${orderItem.transport.id})'><i class="fa fa-trash-o"></i></button>
							</td>
						</tr>
          </c:forEach>
					</tbody>
          <form action='order' id='buy' method="post">
					<tfoot>
						<tr class="">
							<td class="text-right" ><strong id='totalPrice'><fmt:message key="label.total"/>: $${requestScope.totalPrice}</strong></td>
						</tr>
              <tr>
                <td colspan="3">
                  <label style="margin-right:0;"><fmt:message key="label.pay"/>:</label>
              <select id="payType" onchange="showCardBlock()" name="payType">
                <option value="card"><fmt:message key="label.card"/></option>
                <option value="cash"><fmt:message key="label.cash"/></option>
              </select>
              <label class="filter-col" style="margin-right:0;"><fmt:message key="label.delivery"/>:</label>
          <select  name="delivery">
              <option value="pickup"><fmt:message key="label.pickup"/></option>
              <option value="express"><fmt:message key="label.express"/></option>
          </select>
        </td>
      </tr>
              <tr>
              <td>
                  <label class="filter-col" style="margin-right:0;">Delivery info: </label>
                  <input type="text" name="description" class="form-control"/>
                </td>
                <td id="cardNumber">
                  <label class="filter-col" style="margin-right:0;">Card number</label>
                  <input type="text" name="cardNumber" class="form-control" />
                </td>
              </tr>
						<tr><td></td>	<td></td>
								<td><button type="submit" form="buy" class="${empty sessionScope.user ? 'btn btn-success btn-block disabled' : 'btn btn-success btn-block'}" >Buy <i class="fa fa-angle-right"></i></button></td>
						</tr>
            <c:if test='${empty sessionScope.user}'>
              <tr><td></td>
                <td colspan="2">
                  <p>You need to login or register</p>
                </td>
              </tr>
            </c:if>
					</tfoot>
        </form>
				</table>
        </c:if>
</div>
<jsp:include page="/WEB-INF/jspf/footer.jspf" />
