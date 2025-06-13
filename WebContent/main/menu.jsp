
<%-- サイドバー --%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="menu">

	<nav>
	<ul>
		<%-- メイン画面リンク --%>
		<li><a href="${pageContext.request.contextPath}/main/MMNU001">メニュー</a></li>
		<%-- 学生管理リンク --%>
		<li><a href="${pageContext.request.contextPath}/main/student/SBJM001">学生管理</a></li>


		<%-- 成績管理 --%>
		<li class="has-submenu">
		<a>成績管理</a>
		<label>
			<%-- 成績登録リンク --%>
			<a href="${pageContext.request.contextPath}/main/grade/GRMU001">成績登録</a>
			<%-- 成績参照リンク --%>
			<a href="${pageContext.request.contextPath}/main/grade/GRMR001">成績参照</a>
		</label>
		</li>

		<%-- 科目管理リンク --%>
		<li><a href="${pageContext.request.contextPath}/main/subject/SBJM001">科目管理</a></li>

	</ul>
	</nav>
</div>