<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<div class="col-md-6 col-md-offset-3">
		<table class="table table-striped">
			<tr>
				<th>id</th>
				<th>Name</th>
				<th>Email</th>
				<th>Status</th>
			</tr>
			<c:forEach var="status" items="${statuses}">
				<tr>
					<td><c:out value="${status.id}"></c:out></td>
					<td><c:out value="${status.user.username}"></c:out></td>
					<td><c:out value="${status.user.email}"></c:out></td>
					<td><c:out value="${status.text}"></c:out></td>
				</tr>


			</c:forEach>
		</table>
	</div>
