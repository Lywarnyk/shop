<%@ taglib prefix="m" uri="http://tomcat.apache.org/example-taglib" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${pageContext.request.locale}"/>
<%-- load the bundle (by locale) --%>
<fmt:setBundle basename="resource"/>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Car Trade | Home :: w3layouts</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Car Trade Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template,
	SmartPhone Compatible web template, free WebDesigns for Nokia, Samsung, LG, Sony Ericsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- Custom Theme files -->
<link href="css/bootstrap.css" type="text/css" rel="stylesheet" media="all">
<%-- <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css"> --%>
<link href="css/style.css" type="text/css" rel="stylesheet" media="all">
<link href="css/font-awesome.css" rel="stylesheet">	<!-- font-awesome icons -->
<link rel="stylesheet" href="css/flexslider.css" type="text/css" media="screen" />  <!-- flexslider-CSS -->
<link rel="stylesheet" href="css/chocolat.css" type="text/css" media="screen">
<link rel="stylesheet" href="css/transport.css" type="text/css" media="screen">
<link rel="stylesheet" href="css/web-shop.css" type="text/css" media="screen">
<!-- //Custom Theme files -->
<!-- js -->
<script src="js/jquery-2.2.3.min.js"></script>
<%-- <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script> --%>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/url-rewrite.js"></script>
<!-- //js -->
<!-- web-fonts -->
<link href="//fonts.googleapis.com/css?family=Lato:100,100i,300,300i,400,400i,700,700i,900,900i" rel="stylesheet">
<link href="//fonts.googleapis.com/css?family=Aguafina+Script" rel="stylesheet">
<link href="//fonts.googleapis.com/css?family=Petit+Formal+Script" rel="stylesheet">
<!-- //web-fonts -->
</head>
<body id="page-top" data-spy="scroll" data-target=".navbar-fixed-top">

	<!-- banner -->
	<div id="home" class="w3ls-banner ">
		<!-- header -->
		<div class="header-w3layouts">
			<!-- Navigation -->
			<nav class="navbar navbar-default navbar-fixed-top">
				<div class="container">
					<div class="navbar-header page-scroll">
						<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
							<span class="sr-only">Car Trade</span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
						</button>
						<h1 class="w3lslogo"><a href="index.html">CT <span>Car Trade</span></a></h1>
					</div>
					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse navbar-collapse navbar-ex1-collapse">
						<ul class="nav navbar-nav navbar-right cl-effect-15">
							<!-- Hidden li included to remove active class from about link when scrolled up past about section -->
							<li><a class="page-scroll" href="index" data-hover=<fmt:message key="button.home"/>><fmt:message key="button.home"/></a></li>
							<li><a class="page-scroll" href="allOffers" data-hover=<fmt:message key="button.offers"/>><fmt:message key="button.offers"/></a></li>
							<li><a class="page-scroll" href="register" data-hover=<fmt:message key="button.register"/>><fmt:message key="button.register"/></a></li>
							<m:sign-in/>

						</ul>
						<div id="cart-container">
							<a href="cart" class="btn btn-info btn-lg pull-right cart">
								<i class="fa fa-shopping-cart fa-x" aria-hidden="false"> <fmt:message key="label.cart"/></i>
								<span id="itemCount">${sessionScope.cart.getProductsAmount()}</span>
							</a>
						</div>

					</div>
					<!-- /.navbar-collapse -->
				</div>
				<!-- /.container -->
			</nav>
		</div>
		<!-- //header -->
		<!-- banner-text -->
		<div class="banner-text">
			<div class="container">
				<div class="flexslider">
					<ul class="slides">
						<li>
							<div class="banner-w3lstext">
								<h2><fmt:message key="label.car-sale"/></h2>
								<p>The best transport in the entire world</p>
							</div>
						</li>
						<li>
							<div class="banner-w3lstext">
								<h3><fmt:message key="label.shop"/></h3>
								<p>The best transport in the entire world</p>
							</div>
						</li>
						<li>
							<div class="banner-w3lstext">
								<h3><fmt:message key="label.l3"/></h3>
								<p>The best transport in the entire world</p>
							</div>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<!-- //banner-text -->
	</div>
	<my:language  locales="${applicationScope.locales}" current="${pageContext.request.locale}"/>
	
