<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="US-ASCII"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/captchatag.tld" prefix="m" %>
<jsp:include page="/WEB-INF/jspf/header.jsp" />
<!-- features -->
<div id="features" class="features">
    <div class="container">
        <div class="w3layouts_skills_grids w3layouts-features-agileinfo">
            <div class="col-md-8 col-md-offset-2">
                <div id="register" class="login-form agileits-right">
                    <div class="agile-row">
                        <h5>Register Form</h5>
                        <div class="login-agileits-top">
                            <form action="register" method="post" id="register-submit" enctype="multipart/form-data">
                                <p>Download avatar</p>
                                <input type="file" name="avatar">
                                <p>First Name</p>
                                <input type="text" class="name" id="first-name" name="first-name" value='${requestScope.bean.firstName}' required=""/>
                                    <p class="errors-text">
                                        <c:out value="${requestScope.errors['first-name']}"/>
                                    </p>
                                <p>Last Name</p>
                                <input type="text" class="name" id="last-name" name="last-name" value="${requestScope.bean.lastName}" required=""/>
                                <p class="errors-text">
                                    <c:out value="${requestScope.errors['last-name']}"/>
                               </p>
                                <p>Email</p>
                                <input type="email" class="email" id="email" name="email" value="${requestScope.bean.email}" required=""/>
                                <p class="errors-text">
                                    <c:out value='${requestScope.errors["email"]}'/>
                                </p>
                                <p>Password</p>
                                <input type="password" class="password" id="password" name="password" required=""/>
                                <p class="errors-text">
                                    <c:out value='${requestScope.errors["password"]}'/>
                                </p>
                                <p>Retype password</p>
                                <input type="password" class="password" id="re-password" name="re-password" required=""/>
                                <p class="errors-text">
                                    <c:out value='${requestScope.errors["re-password"]}'/>
                                </p>
                                <m:captcha/>
                                  <p class="errors-text">
                                      <c:out value='${requestScope.errors["captcha"]}'/>
                                  </p>
                                <input type="checkbox" name="mailing" value="true"> I want to receive the latest news<br>
                                <input type="submit" value="Register" >
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Progressive-Effects-Animation-JavaScript -->
<script type="text/javascript" src="js/numscroller-1.0.js"></script>
<!-- //Progressive-Effects-Animation-JavaScript -->
</div>
</div>
<%-- <script type="text/javascript" src="js/jsValidation.js"></script> --%>
<%-- <script type="text/javascript" src="js/jqueryValidation.js"></script> --%>
<jsp:include page="/WEB-INF/jspf/footer.jspf" />
