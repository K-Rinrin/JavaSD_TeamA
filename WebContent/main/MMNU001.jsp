
<%-- メイン画面 --%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/base.jsp">
<c:param name="title" value="得点管理システム" />
<c:param name="body">

<div class="MMNU001">

	<%-- 画面タイトル --%>
	<h2>メニュー</h2>

	<%-- 学生管理リンク --%>
	<a href="${pageContext.request.contextPath}/student/SBJM001">学生管理</a>

	<%-- 成績管理 --%>
	<div class="seisekikannri">
		<a>成績管理</a>
		<%-- 成績登録リンク --%>
		<a href="${pageContext.request.contextPath}/grade/GRMU001">成績登録</a>
		<%-- 成績参照リンク --%>
		<a href="${pageContext.request.contextPath}/grade/GRMR001">成績参照</a>
	</div>

	<%-- 科目管理リンク --%>
	<a href="${pageContext.request.contextPath}/subject/SBJM001">科目管理</a>


</div>
</c:param>
</c:import>