<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/jspf/header.jsp" />
<div>
    <h2 class="error-message">${requestScope.errorMessage}</h2>
    <img class="error-img" src="images/error/error.png" align="center">
</div>
<jsp:include page="/WEB-INF/jspf/footer.jspf" />
