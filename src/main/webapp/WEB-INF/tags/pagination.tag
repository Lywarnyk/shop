<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="js/url-rewrite.js"></script>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">

<br>
<a href="#" onclick="changePage(${requestScope.filter.page - 1})" <c:if test='${requestScope.filter.page == 1}'> class="disabled" </c:if>>previous</a>
<c:forEach var="page" begin='1' end="${requestScope.pagesAmount}">
  <a href="#" onclick="changePage(${page})" <c:if test='${requestScope.filter.page == page}'> class="disabled" </c:if>>${page}</a>
    </c:forEach>
<a href="#" onclick="changePage(${requestScope.filter.page + 1})" <c:if test='${requestScope.filter.page == requestScope.pagesAmount}'> class="disabled" </c:if>>next</a>
