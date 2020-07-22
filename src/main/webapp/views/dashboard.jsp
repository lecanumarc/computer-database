<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
    
<!DOCTYPE HTML>
<html>
<head>
<title><spring:message code="title"/></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/dashboard.js"></script>
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard"> <spring:message code="title"/> </a>
			<div class="pull-right">
            	<a class="navbar-brand" href="<spring:message code="lang.url"/>"><spring:message code="lang"/></a>
            </div>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<h1 id="homeTitle">
				${rowNumber} <spring:message code="dashboard.computer.found"/>
			</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="#" method="GET" class="form-inline">
						<input type="search" id="searchbox" name="search" class="form-control" placeholder="<spring:message code="dashboard.search.placeholder"/>" /> 
						<input type="submit" id="searchsubmit" value="<spring:message code="dashboard.filter.button"/>" class="btn btn-primary" href="dashboard?search=${search}"/>
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="addComputer"> <spring:message code="dashboard.addComputer.button"/></a> 
					<a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();"> <spring:message code="dashboard.edit.button"/> </a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="dashboard" method="POST">
			<input type="hidden" name="selection" value="">
		</form>

		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<!-- Variable declarations for passing labels as parameters -->
						<!-- Table header for Computer Name -->

						<th class="editMode" style="width: 60px; height: 22px;">
							<input type="checkbox" id="selectall" /> 
							<span style="vertical-align: top;"> 
								- <a href="#"id="deleteSelected" onclick="$.fn.deleteSelected();"> 
										<i class="fa fa-trash-o fa-lg"></i>
								  </a>
							</span>
						</th>
						<th>
							<a href="dashboard?columnOrder=computer.name">
								<spring:message code="dashboard.computerName"/>
							</a>
	                         	
						</th>
						<th>
							<a href="dashboard?columnOrder=introduced">
								<spring:message code="dashboard.introduced"/>
							</a>
						</th>
						<th>
							<a href="dashboard?columnOrder=discontinued">
								<spring:message code="dashboard.discontinued"/>
								</a>
						</th>
						<th>
							<a href="dashboard?columnOrder=company.name">
								<spring:message code="dashboard.company"/>
							</a>
						</th>

					</tr>
				</thead>
				
				<!-- Browse attribute computers -->
				<tbody id="results">
					<c:forEach items="${computerList}" var="computer">
						<tr>
						<td class="editMode">
                            <input type="checkbox" name="cb" class="cb" value="${computer.id}"></td>
							<td><a href="editComputer?id=${computer.id}" onclick="">${computer.name}</a></td>
							<td>${computer.introduced}</td>
							<td>${computer.discontinued}</td>
							<td>${computer.company.name}</td>
						</tr>
					</c:forEach>
				</tbody>
				
			</table>
			
		</div>
	</section>

	<footer class="navbar-fixed-bottom">
		<div class="container text-center">
			<c:if test="${currentPage != null}">
				<ul class="pagination">
					<li>
						<c:if test="${currentPage > 1}">
							<a href="dashboard?currentPage=${currentPage-1}&search=${filter}&columnOrder=${columnOrder}"
								aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
							</a>
						</c:if>
					</li>
					
					<c:forEach var="i" begin="0" end="2">
						<c:set var="decr" value="${2-i}"></c:set>
						<c:if test="${currentPage - decr > 1 }">
							<li>
							<a href="dashboard?currentPage=${currentPage - decr}&search=${filter}&columnOrder=${columnOrder}">
								<c:out value="${currentPage- decr}"></c:out>
							</a>
							</li>
						</c:if>
					</c:forEach>
					<c:forEach var="i" begin="1" end="3">
						<c:if test="${currentPage + i < maxPage }">
							<li>
							<a href="dashboard?currentPage=${currentPage + i}&search=${filter}&columnOrder=${columnOrder}">
								<c:out value="${currentPage+i}"></c:out>
							</a>
							</li>
						</c:if>
					</c:forEach> 
					<li>
						<c:if test="${currentPage + i < maxPage}">
							<a href="dashboard?currentPage=${currentPage+1}&search=${filter}&columnOrder=${columnOrder}" aria-label="Next"> <span aria-hidden="true">&raquo;</span></a>
						</c:if>
					</li>
				</ul>
			</c:if>


			<div class="btn-group btn-group-sm pull-right" role="group">
				<button type="button" class="btn btn-default">
					<a href="dashboard?queryRows=10">10</a>
				</button>
				<button type="button" class="btn btn-default">
					<a href="dashboard?queryRows=50">50</a>
				</button>
				<button type="button" class="btn btn-default">
					<a href="dashboard?queryRows=100">100</a>
				</button>
			</div>
		</div>
	</footer>
	
</body>
</html>