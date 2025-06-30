
<%-- 学生別成績一覧画面 --%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../base.jsp">
<c:param name="title" value="得点管理システム" />
<c:param name="body">

<div class="GRMR003">

	<h2 class="p-2 mb-4 bg-body-secondary border fw-bold">成績一覧（学生）</h2>

	<%-- 共通検索フォームをインポート --%>
	<c:import url="grade-search.jsp" />

	<hr>

	<%-- 結果表示エリア --%>
	<div class="mt-4">

		<c:if test="${not empty subject }">
			<div class="mb-3 fw-bold">
			氏名：${student.name}（${student.no}）
			</div>
		</c:if>

		<c:choose>

		<%-- 結果が存在する場合 --%>
		<c:when test="${not empty results}">

		<%-- 成績一覧テーブル --%>
		<table class="result-table">

		<thead class="table-light">
		<tr>
			<%-- ヘッダー --%>
			<th>科目名</th>
			<th>科目コード</th>
			<th>回数</th>
			<th>点数</th>
		</tr>
		</thead>

		<tbody>

			<c:forEach var="test" items="${results}">
			<tr>
				<%-- 成績情報 --%>
				<td>${test.subject.name}</td>
				<td>${test.subject.cd}</td>
				<td>${test.num}</td>
				<td>${test.point}</td>
			</tr>
			</c:forEach>
		</tbody>
		</table>

		</c:when>

		<%-- 結果が存在しない場合 --%>
		<c:otherwise>
			<p class="no-data-message">成績情報が存在しませんでした</p>
		</c:otherwise>
		</c:choose>
	</div>
</div>
</c:param>
</c:import>