<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



	<div class="col-md-6 col-md-offset-3">
		<sf:form method="post" class="form-horizontal"
			action="${pageContext.request.contextPath}/doresetpassword"
			commandName="user">
			<fieldset>

			<sf:input type="hidden" name="id" path="id" />
			<sf:input type="hidden" name="username" path="username" />
			<sf:input type="hidden" name="email" path="email" />
			<sf:input type="hidden" name="enabled" path="enabled" />
			<sf:input type="hidden" name="authority" path="authority" />
			<sf:input type="hidden" name="fullname" path="fullname" />
			<sf:input type="hidden" name="userProfile.id" path="userProfile.id" />
			<sf:input type="hidden" name="userProfile.birthDate" path="userProfile.birthDate" />
			<sf:input type="hidden" name="userProfile.sex" path="userProfile.sex" />
			<sf:input type="hidden" name="userProfile.mobileNo" path="userProfile.mobileNo" />
			<sf:input type="hidden" name="userProfile.bloodGroup" path="userProfile.bloodGroup" />
			<sf:input type="hidden" name="userProfile.address" path="userProfile.address" />
			<sf:input type="hidden" name="userProfile.emergency" path="userProfile.emergency" />
			<sf:input type="hidden" name="userProfile.facebookProfile" path="userProfile.facebookProfile" />

				<!-- Form Name -->
				<legend>Change Password</legend>


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

				<!-- Button -->
				<div class="control-group">
					<label class="control-label" for="submit"></label>
					<div class="controls">
						<button id="submit" name="submit" class="btn btn-primary">Create
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
				password1.setCustomValidity("<fmt:message key='UnmatchedPasswords.user.password' />");
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
