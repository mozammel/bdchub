<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    

<h3>Authorized users only</h3>
	<div class="col-md-6 col-md-offset-3">
		<table class="table table-striped">
			<tr>
				<th>Full Name</th>
				<th>Username</th>
				<th>Email</th>
			</tr>
			<c:forEach var="user" items="${users}">
				<tr>
					<td><a href="<c:url value='/userdetails?username=${user.username}' />"><c:out value="${user.fullname}"></c:out></a></td>
					<td><a href="<c:url value='/userdetails?username=${user.username}' />"><c:out value="${user.username}"></c:out></a></td>
					<td><c:out value="${user.email}"></c:out></td>
				</tr>
			</c:forEach>
		</table>
	</div>

