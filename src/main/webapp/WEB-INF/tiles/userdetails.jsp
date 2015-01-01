<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



		<table class="table table-striped">
			<tr>
				<th>Username</th>
				<th>Full Name</th>
				<th>Email</th>
				<th>Mobile</th>
				<th>Bloodgroup</th>
				<th>Sex</th>
				<th>Birthdate</th>
				<th>Emergency</th>
				<th>Address</th>
				<th>Facebook link</th>
			</tr>
				<tr>
					<td><c:out value="${user.username}"></c:out></td>
					<td><c:out value="${user.fullname}"></c:out></td>
					<td><c:out value="${user.email}"></c:out></td>
					<td><c:out value="${user.userProfile.mobileNo}"></c:out></td>
					<td><c:out value="${user.userProfile.bloodGroup}"></c:out></td>
					<td><c:out value="${user.userProfile.sex}"></c:out></td>
					<td><c:out value="${user.userProfile.birthDate}"></c:out></td>
					<td><c:out value="${user.userProfile.emergency}"></c:out></td>
					<td><c:out value="${user.userProfile.address}"></c:out></td>
					<td><c:out value="${user.userProfile.facebookProfile}"></c:out></td>
				</tr>
		</table>
</div>

