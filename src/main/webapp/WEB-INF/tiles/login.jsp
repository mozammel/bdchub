<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<script type="text/javascript">
	$(document).ready(function() {
		$(j_username).focus();
	});
</script>

<div class="col-md-6 col-md-offset-3">
	<form class="form-horizontal"
		action="${pageContext.request.contextPath}/j_spring_security_check"
		method="post">
		<fieldset>

			<!-- Form Name -->
			<div class="form-group">
				<h1>Log In</h1>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="control-label" for="j_username">Username</label> <input
					id="j_username" name="j_username" placeholder=""
					class="form-control" type="text">
			</div>

			<!-- Password input-->
			<div class="form-group">
				<label class="control-label" for="j_password">Password</label> <input
					id="j_password" name="j_password" placeholder=""
					class="form-control" type="password">
			</div>

			<!-- Remember me checkbox-->
			<div class="form-group">
				<label class="control-label" for="j_password">Remember Me
					&nbsp;</label> <input id="_spring_security_remember_me"
					name="_spring_security_remember_me" checked="checked"
					type="checkbox">
			</div>

			<div class="alert-danger">
				<c:if test="${param.error != null}">
						Incorrect Username or Password provided
					</c:if>

			</div>

			<!-- Button -->
			<div class="form-group">
				<label class="control-label" for="submit"></label>
				<div class="controls">
					<button id="submit" name="submit" class="btn btn-primary">Submit</button>
				</div>
			</div>

		</fieldset>
	</form>

	<p>
		<a href="<c:url value='/newaccount' />">Create new account</a>
	</p>
	<p>
		<a href="<c:url value='/forgotpassword' />">Forgot password?</a>
	</p>
</div>
