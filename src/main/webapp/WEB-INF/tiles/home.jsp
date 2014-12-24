<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>



	<sec:authorize access="!isAuthenticated()">
		<div class="col-md-6 col-md-offset-3">
			<br /> <br /> Welcome to BDCHub registration portal. Please click the
			top right 'Log in' link to login to your account. If you don't know
			your password, but know the email you used to register earlier, use
			that email with 'Forgot Password' link.
			
			<br /><br />You can also create a new
			account from 'Create New Account' link. After you login, please fill up
			the profile details so that we can contact you in case of emergency.
			<br /> <br />
			<p>
				Registered earlier? Use <a href="<c:url value='/forgotpassword' />">Forgot Password link</a> to get your password.
			</p>
			<p>
				Never Registered? <a href="<c:url value='/newaccount' />">Create New Account</a> to register then.
			</p>
			<p>
				Have account already? <a href="<c:url value='/login' />">Log in!</a>
			</p>
		</div>
	</sec:authorize>

	<sec:authorize access="isAuthenticated()">
		<div class="col-md-8 col-md-offset-2">
			<div class="row">
			  <div class="col-sm-2">
			  	<br />
			  	<img src="http://graph.facebook.com/<c:out value='${facebookUserName}' />/picture?&width=90" />
			  
			  </div>
			  <div class="col-sm-10">
						  <br /> <br /> 
					Please fill up your profile details from the following link if you haven't
					done so already.
					<br /> <br />
					<p>
						<a href="<c:url value='/profile' />">User Profile</a>
					</p>
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
			</div>				
		</div>
	
		



