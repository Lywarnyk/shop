<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>


<link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/url-rewrite.js"></script>
<jsp:include page="/WEB-INF/jspf/header.jsp" />
<!-- gallery -->
<div id="gallery" class="gallery">

    <div class="container">
        <h3 class="agileits-title">Offers<span class="w3lshr-line"> </span></h3>
        <!-- Search panel -->
        <div class="container">
        	<div class="row">
                <div id="filter-panel" class="collapse filter-panel">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <form class="form-inline" role="filter" id="filter-form">
                                <div class="form-group">
                                  <label class="filter-col" style="margin-right:0;"> Price from </label>
                                  <input type="text" name="min-price" class="form-control"/>
                                  <label class="filter-col" style="margin-right:0;"> to </label>
                                  <input type="text" name="max-price" class="form-control"/>
                                </div>
                                <div class="form-group">
                                </div>
                                <div class="form-group">
                                    <label class="filter-col" style="margin-right:0;">Manufacturer:</label>
                                    <select id="manufacturer" class="form-control" name="manufacturer">
                                      <option value=""></option>
                                      <c:forEach var='manufacturer' items='${requestScope.manufacturers}'>
                                        <option value="${manufacturer.id}" <c:if test='${requestScope.filter.manufacturer == manufacturer.id}'> selected </c:if>> ${manufacturer.name}</option>
                                      </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label class="filter-col" style="margin-right:0;" for="sort" for=>Name</label>
                                    <input type="text" name="name" class="form-control">
                                </div><!-- form group [order by] -->
                                <div class="form-group">
                                  <br>
                                    <div class="checkbox" style="margin-left:10px; margin-right:10px;">
                                      <c:forEach var='category' items='${requestScope.categories}'>
                                        <label><input type="checkbox" name="categories" value="${category.id}">${category.name}</label>
                                      </c:forEach>
                                    </div><br><br>
                                    <button type="submit" class="btn btn-default filter-col" formaction="allOffers">
                                        <span class="glyphicon glyphicon-record"></span> Apply
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#filter-panel">
                    <span class="glyphicon glyphicon-cog"></span> Advanced Search
                </button>
        	</div>
        </div>
      </div>
        <!-- Search panel -->

        <script type="text/javascript" src="js/url-rewrite.js"></script>
        <!-- gallery -->
        <div id="gallery" class="gallery">
            <div class="container">
                <div class="main container">
                  <ul class="transport-description col-md-12" style="margin-top: 20px;">
                    <li class="col-md-12">
                      <!-- sorter -->
                          <label class="" for="pref-perpage">Rows per page:</label>
                          <select id="pref-perpage" name="items" form="filter-form">
                              <option value="3" <c:if test='${requestScope.filter.itemsPerPage == 3}'> selected </c:if>>3</option>
                              <option value="5" <c:if test='${requestScope.filter.itemsPerPage == 5}'> selected </c:if>>5</option>
                              <option value="10" <c:if test='${requestScope.filter.itemsPerPage == 10}'> selected </c:if>>10</option>
                              <option value="15" <c:if test='${requestScope.filter.itemsPerPage == 15}'> selected </c:if>>15</option>
                              <option value="20" <c:if test='${requestScope.filter.itemsPerPage == 20}'> selected </c:if>>20</option>
                              <option value="30" <c:if test='${requestScope.filter.itemsPerPage == 30}'> selected </c:if>>30</option>
                              <option value="40" <c:if test='${requestScope.filter.itemsPerPage == 40}'> selected </c:if>>40</option>
                              <option value="50" <c:if test='${requestScope.filter.itemsPerPage == 50}'> selected </c:if>>50</option>
                          </select>
                          <label class="" style="margin-right:0;" for="sort">Sort by: </label>
                          <select id="sort" class="" name='sort' form="filter-form">
                            <option value='model' <c:if test='${requestScope.filter.sort == "model"}'> selected </c:if>>Model</option>
                            <option value='price' <c:if test='${requestScope.filter.sort == "price"}'> selected </c:if>>Price</option>
                          </select>
                          <label class="" style="margin-right:0;" for="order">Order by: </label>
                          <select id="order" class="" name='order' form="filter-form">
                            <option value='asc' <c:if test='${requestScope.filter.order == "asc"}'> selected </c:if>>Ascending</option>
                            <option value='desc' <c:if test='${requestScope.filter.order == "desc"}'> selected </c:if>>Descending</option>
                          </select>
                      <!-- sorter -->
                  </ul>
                  <c:if test='${  empty requestScope.transports}'>There are no transports with specified conditions</c:if>

                  <c:forEach var='transport' items='${requestScope.transports}'>
                    <ul class="transport-description col-md-12">
                        <li class="col-md-6"><img src="images/ng${transport.id}.jpg" class="transport-img" alt=""/></li>
                        <li class="col-md-4 text-left">
                            <h4>${transport.model}</h4>
                            <p>Price = $${transport.price}</p>
                            <p>Manufacturer: ${transport.manufacturer.name}</p>
                            <p>Category: ${transport.category.name}</p>
                            <p>Description: ${transport.description}</p>
                        </li>
                        <li class="col-md-2">
                          <button type="button" onClick="addProductToCart(${transport.id})" class="btn-primary">Add to cart</button>
                        </li>
                    </ul>
                  </c:forEach>

                  <my:pagination/>

                </div>
                <div class="clearfix"></div>
            </div>
        </div>
        </div>
        <!-- //gallery -->
        <!-- Progressive-Effects-Animation-JavaScript -->
        <script type="text/javascript" src="js/cart-ajax.js"></script>

        <script type="text/javascript" src="js/numscroller-1.0.js"></script>
        <!-- //Progressive-Effects-Animation-JavaScript -->
        <jsp:include page="/WEB-INF/jspf/footer.jspf" />
        <div class="clearfix"></div>
