<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<div class="col-md-6 col-md-offset-3">
	<sf:form method="post" class="form-horizontal"
		action="${pageContext.request.contextPath}/docreate"
		commandName="status">
		<fieldset>

			<!-- Form Name -->
			<div class="form-group">
				<h1>Create Status</h1>
			</div>

			<sf:input type="hidden" name="id" path="id" />

			<!-- Textarea -->
			<div class="form-group">
				<label class="control-label" for="text">Status</label>
					<sf:textarea id="text" path="text" name="text" class="form-control"></sf:textarea>
					<sf:errors path="text" cssClass="alert-danger"></sf:errors>
			</div>

			<!-- Button -->
			<div class="form-group">
				<label class="control-label" for="submit"></label>
					<button id="submit" name="submit" class="btn btn-primary">Save
						Status</button>
			</div>

			<c:if test="${status.id != 0}">
				<!-- Button -->
				<div class="form-group">
					<label class="control-label" for="submit"></label>
						<button id="delete" name="delete" class="btn btn-primary">Delete</button>
				</div>
			</c:if>

		</fieldset>
	</sf:form>
</div>
