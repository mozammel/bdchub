<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<div class="col-md-6 col-md-offset-3">
	<sf:form method="post" class="form-horizontal"
		action="${pageContext.request.contextPath}/doupdateprofile"
		commandName="user">
		<fieldset>

			<!-- Form Name -->
			<h1>Update Profile</h1>

			<sf:input type="hidden" name="id" path="id" />
			<sf:input type="hidden" name="enabled" path="enabled" />
			<sf:input type="hidden" name="authority" path="authority" />
			<sf:input type="hidden" name="userProfile.id" path="userProfile.id" />




			<!-- Text input-->
			<div class="form-group">
				<label class="control-label" for="fullname">Full Name</label>
				<sf:input id="fullname" path="fullname" name="fullname"
					placeholder="enter full name" class="form-control" type="text" />
				<sf:errors path="fullname" cssClass="alert-danger"></sf:errors>

			</div>


			<!-- Text input-->
			<div class="form-group">
				<label class="control-label" for="name">Username</label>
					<sf:input id="username" path="username" name="username"
						placeholder="enter name" class="form-control" type="text" />
					<sf:errors path="username" cssClass="alert-danger"></sf:errors>

			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="control-label" for="email">Email</label>
					<sf:input id="email" path="email" name="email"
						placeholder="enter valid email" class="form-control" type="text" />
					<sf:errors path="email" cssClass="alert-danger"></sf:errors>
			</div>


			<!-- Password input-->
			<div class="form-group">
				<label class="control-label" for="password">Password</label>
					<sf:input id="password" path="password" name="password"
						class="form-control" type="password" />
					<sf:errors path="password" cssClass="alert-danger"></sf:errors>
			</div>

			<!-- Confirm Password input-->
			<div class="form-group">
				<label class="control-label" for="confirmpassword">Confirm
					Password</label>
					<input id="confirmpassword" name="confirmpassword"
						class="form-control" type="password" />
			</div>




			<!-- Text input-->
			<div class="form-group">
				<label class="control-label" for="userProfile.birthDate">Birth
					Date</label>
					<sf:input id="userProfile.birthDate" path="userProfile.birthDate"
						name="userProfile.birthDate" placeholder="birthDate"
						class="form-control" type="date" />
					<sf:errors path="userProfile.birthDate" cssClass="alert-danger"></sf:errors>
			</div>


			<!-- Text input-->
			<div class="form-group">
				<label class="control-label" for="userProfile.birthDate">Mobile</label>
					<sf:input id="userProfile.mobileNo" path="userProfile.mobileNo"
						name="userProfile.mobileNo" placeholder="Mobile no"
						class="form-control" type="text" />
					<sf:errors path="userProfile.mobileNo" cssClass="alert-danger"></sf:errors>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="control-label" for="userProfile.bloodGroup">Blood
					Group</label>
					<sf:input id="userProfile.bloodGroup" path="userProfile.bloodGroup"
						name="userProfile.bloodGroup" placeholder="bloodGroup"
						class="form-control" type="text" />
					<sf:errors path="userProfile.bloodGroup" cssClass="alert-danger"></sf:errors>
			</div>

			<!-- Textarea -->
			<div class="form-group">
				<label class="control-label" for="userProfile.address">Address</label>
					<sf:textarea id="userProfile.address" path="userProfile.address"
						name="userProfile.address" class="form-control"></sf:textarea>
					<sf:errors path="userProfile.address" cssClass="alert-danger"></sf:errors>
			</div>

			<!-- Textarea -->
			<div class="form-group">
				<label class="control-label" for="userProfile.emergency">Emergency</label>
					<sf:textarea id="userProfile.emergency"
						path="userProfile.emergency" name="userProfile.emergency" class="form-control"></sf:textarea>
					<sf:errors path="userProfile.emergency" cssClass="alert-danger"></sf:errors>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="control-label" for="userProfile.bloodGroup">Facebook
					Profile Link</label>
					<sf:input id="userProfile.facebookProfile"
						path="userProfile.facebookProfile"
						name="userProfile.facebookProfile" placeholder="facebookProfile"
						class="form-control" type="text" />
					<sf:errors path="userProfile.facebookProfile"
						cssClass="alert-danger"></sf:errors>
			</div>

			<!-- Button -->
			<div class="form-group">
				<label class="control-label" for="submit"></label>
					<button id="submit" name="submit" class="btn btn-primary">Update
						User</button>
			</div>

		</fieldset>
	</sf:form>
</div>

<script>
	var password1 = document.getElementById('password');
	var password2 = document.getElementById('confirmpassword');

	var checkPasswordValidity = function() {
		if (password1.value != password2.value) {
			password1
					.setCustomValidity("<fmt:message key='UnmatchedPasswords.user.password' />");
		} else {
			password1.setCustomValidity('');
		}
	};

	password1.addEventListener('change', checkPasswordValidity, false);
	password2.addEventListener('change', checkPasswordValidity, false);

	var form = document.getElementById('user');
	form.addEventListener('submit', function() {
		checkPasswordValidity();
		if (!this.checkValidity()) {
			event.preventDefault();
			//Implement you own means of displaying error messages to the user here.
			password1.focus();
		}
	}, false);
</script>
