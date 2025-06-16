
<%-- サイドバー --%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="d-flex flex-column flex-shrink-0 p-3 bg-white border-end" style="width: 240px;">
	<ul class="list-unstyled">


		<%-- メイン画面リンク --%>
		<li class="mb-2"><a href="${pageContext.request.contextPath}/main/MMNU001">メニュー</a></li>

		<%-- 学生管理リンク --%>
		<li class="mb-2"><a href="${pageContext.request.contextPath}/main/student/SBJM001">学生管理</a></li>


		<%-- 成績管理 --%>
		<li class="mb-2">
			<h6 class="text-dark fw-bold mb-1">成績管理</h6>
			<div class="ps-3">
				<%-- 成績登録リンク --%>
				<a href="${pageContext.request.contextPath}/main/grade/GRMU001">成績登録</a><br>
				<%-- 成績参照リンク --%>
				<a href="${pageContext.request.contextPath}/main/grade/GRMR001">成績参照</a>
			</div>
		</li>


		<%-- 科目管理リンク --%>
		<li class="mb-2"><a href="${pageContext.request.contextPath}/main/subject/SBJM001">科目管理</a></li>

	</ul>
</div>