<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div class="col-md-6 col-md-offset-3">


	<sec:authorize access="!isAuthenticated()">
		<br /> <br /> Welcome to BDCHub registration portal. Please click the
		top right 'Log in' link to login to your account. If you don't know
		your password, but know the email you used to register earlier, use
		that email with 'Forgot Password' link.
		
		<br /><br />You can also create a new
		account from 'Create New Account' link. After you login, please fill up
		the profile details so that we can contact you in case of emergency.
		<br /> <br />
		<p>
			<a href="<c:url value='/login' />">Log in</a>
		</p>
	</sec:authorize>

	<sec:authorize access="isAuthenticated()">
		<br /> <br /> 
		Please fill up your profile details from the following link if you haven't
		done so already.
		<br /> <br />
		<p>
			<a href="<c:url value='/profile' />">User Profile</a>
		</p>
	</sec:authorize>

	<sec:authorize access="isAuthenticated()">
		<p>
			<a href="<c:url value='/j_spring_security_logout' />">Log out</a>
		</p>
	</sec:authorize>

	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<p>
			<a href="<c:url value='/admin' />">Admin page</a>
		</p>
	</sec:authorize>



</div>