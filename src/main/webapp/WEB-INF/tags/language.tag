<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@attribute name="current" type="java.util.Locale" required="true" %>
<%@attribute name="locales" type="java.util.Collection" required="true" %>

<form class="" action="">
  <label> Change language</label>
    <select id="language" name="lang" class="custom-select" onchange="submit()">
        <c:forEach var="locale" items="${locales}">
            <option value="${locale.language}" ${locale.language eq current.language ? 'selected' : ''}>${locale.language}</option>
        </c:forEach>
    </select>
 </form>
</div>
