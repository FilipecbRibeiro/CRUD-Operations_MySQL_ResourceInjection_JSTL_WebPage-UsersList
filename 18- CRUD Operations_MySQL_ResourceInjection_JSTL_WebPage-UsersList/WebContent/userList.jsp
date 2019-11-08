<%@ include file="include/header.jsp"%>
<%@ page import="java.util.List"%>
<%@ page import="org.light.entity.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>





<div class="container mtb">
	<div class="row">
		<div class="col-lg-6">
			<strong>This is the list of users coming from
				filiperibeiro_db</strong>
			<hr />
			<table border="1">
				<tr>
					<th>Users_id</th>
					<th>Username</th>
					<th>Email</th>
					<th>Operation</th>
				</tr>
				<c:forEach items="${listUsers}" var="run">


					<c:url var="updateURL" value="operation">
						<%--Creating URL --%>
						<c:param name="page" value="updateUser"></c:param>
						<%--Adds parameter to a containing 'import' tag's URL --%>
						<c:param name="usersId" value="${run.users_id}"></c:param>
						<c:param name="username" value="${run.username}"></c:param>
						<c:param name="email" value="${run.email}"></c:param>
					</c:url>


					<c:url var="deleteURL" value="operation">
						<%--Creating URL --%>
						<c:param name="page" value="deleteUser"></c:param>
						<%--Adds parameter to a containing 'import' tag's URL --%>
						<c:param name="usersId" value="${run.users_id}"></c:param>
						<c:param name="username" value="${run.username}"></c:param>
						<c:param name="email" value="${run.email}"></c:param>
					</c:url>


					<tr>
						<td>${run.users_id}</td>
						<td>${run.username}</td>
						<td>${run.email}</td>
						<td><a href="${updateURL}">Update ||</a> <a
							href="${deleteURL}"
							onclick="if(!confirm('Are you sure you want to delete this user?')) return false">Delete</a>


						</td>


					</tr>

				</c:forEach>


			</table>
		</div>
	</div>
</div>


<%@ include file="include/footer.jsp"%>













