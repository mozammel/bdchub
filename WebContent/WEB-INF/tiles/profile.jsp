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
			<legend>Update Profile</legend>

			<sf:input type="hidden" name="id" path="id" />
			<sf:input type="hidden" name="enabled" path="enabled" />
			<sf:input type="hidden" name="authority" path="authority" />
			<sf:input type="hidden" name="userProfile.id" path="userProfile.id" />
			
			


			<!-- Text input-->
			<div class="control-group">
				<label class="control-label" for="fullname">Full Name</label>
				<div class="controls">
					<sf:input id="fullname" path="fullname" name="fullname"
						placeholder="enter full name" class="input-xlarge" type="text" />
					<sf:errors path="fullname" cssClass="alert-danger"></sf:errors>
				</div>

			</div>


			<!-- Text input-->
			<div class="control-group">
				<label class="control-label" for="name">Username</label>
				<div class="controls">
					<sf:input id="username" path="username" name="username"
						placeholder="enter name" class="input-xlarge" type="text" />
					<sf:errors path="username" cssClass="alert-danger"></sf:errors>
				</div>

			</div>

			<!-- Text input-->
			<div class="control-group">
				<label class="control-label" for="email">Email</label>
				<div class="controls">
					<sf:input id="email" path="email" name="email"
						placeholder="enter valid email" class="input-xlarge" type="text" />
					<sf:errors path="email" cssClass="alert-danger"></sf:errors>

				</div>
			</div>

			<!-- Password input-->
			<div class="control-group">
				<label class="control-label" for="password">Password</label>
				<div class="controls">
					<sf:input id="password" path="password" name="password"
						class="input-xlarge" type="password" />
					<sf:errors path="password" cssClass="alert-danger"></sf:errors>

				</div>
			</div>

			<!-- Confirm Password input-->
			<div class="control-group">
				<label class="control-label" for="confirmpassword">Confirm
					Password</label>
				<div class="controls">
					<input id="confirmpassword" name="confirmpassword"
						class="input-xlarge" type="password" />
				</div>
			</div>



			<hr />



			<!-- Text input-->
			<div class="control-group">
				<label class="control-label" for="userProfile.birthDate">Mobile</label>
				<div class="controls">
					<sf:input id="userProfile.mobileNo" path="userProfile.mobileNo"
						name="userProfile.mobileNo" placeholder="Mobile no"
						class="input-xlarge" type="text" />
					<sf:errors path="userProfile.mobileNo" cssClass="alert-danger"></sf:errors>

				</div>
			</div>

			<!-- Text input-->
			<div class="control-group">
				<label class="control-label" for="userProfile.bloodGroup">Blood
					Group</label>
				<div class="controls">
					<sf:input id="userProfile.bloodGroup" path="userProfile.bloodGroup"
						name="userProfile.bloodGroup" placeholder="bloodGroup"
						class="input-xlarge" type="text" />
					<sf:errors path="userProfile.bloodGroup" cssClass="alert-danger"></sf:errors>

				</div>
			</div>
			
					<!-- Textarea -->
			<div class="control-group">
				<label class="control-label" for="userProfile.address">Address</label>
				<div class="controls">
					<sf:textarea id="userProfile.address" path="userProfile.address" name="userProfile.address"></sf:textarea>
					<sf:errors path="userProfile.address" cssClass="alert-danger"></sf:errors>

				</div>
			</div>

					<!-- Textarea -->
			<div class="control-group">
				<label class="control-label" for="userProfile.emergency">Emergency</label>
				<div class="controls">
					<sf:textarea id="userProfile.emergency" path="userProfile.emergency" name="userProfile.emergency"></sf:textarea>
					<sf:errors path="userProfile.emergency" cssClass="alert-danger"></sf:errors>

				</div>
			</div>
			
					<!-- Text input-->
			<div class="control-group">
				<label class="control-label" for="userProfile.bloodGroup">Facebook Profile Link</label>
				<div class="controls">
					<sf:input id="userProfile.facebookProfile" path="userProfile.facebookProfile"
						name="userProfile.facebookProfile" placeholder="facebookProfile"
						class="input-xlarge" type="text" />
					<sf:errors path="userProfile.facebookProfile" cssClass="alert-danger"></sf:errors>

				</div>
			</div>
			
						<!-- Button -->
			<div class="control-group">
				<label class="control-label" for="submit"></label>
				<div class="controls">
					<button id="submit" name="submit" class="btn btn-primary">Update
						User</button>
				</div>
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
