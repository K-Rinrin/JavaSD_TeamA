
<%-- 成績登録完了画面 --%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../base.jsp">
<c:param name="title" value="得点管理システム" />
<c:param name="body">

<div class="GRMU001">

	<h2>成績管理</h2>

	<label >
		<p>登録が完了しました</p>
	</label>

	<hr>
	<a href="${pageContext.request.contextPath}/main/grade/GRMU001">戻る</a>
	<a href="${pageContext.request.contextPath}/main/grade/GRMR001">成績参照</a>

</div>
</c:param>
</c:import>