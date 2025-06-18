
<%-- 成績登録完了画面 --%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../base.jsp">
<c:param name="title" value="得点管理システム" />
<c:param name="body">

<div class="container">

	<h2 class="p-2 mb-4 bg-body-secondary border fw-bold">成績管理</h2>

	<div class="p-2 mb-4 bg-success-subtle border-success rounded text-center">
		登録が完了しました
	</div>

	<div class="mt-4">
		<a href="${pageContext.request.contextPath}/main/grade/GRMU001"
		 class="me-5">戻る</a>
		<a href="${pageContext.request.contextPath}/main/grade/GRMR001">成績参照</a>
	</div>
</div>
</c:param>
</c:import>