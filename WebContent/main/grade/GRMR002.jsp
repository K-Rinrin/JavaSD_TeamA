
<%-- 科目別成績一覧画面 --%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../base.jsp">
<c:param name="title" value="得点管理システム" />
<c:param name="body">

<div class="GRMR002">

<h2>成績一覧（科目）</h2>
<%-- 共通検索フォームをインポート --%>
<c:import url="grade-search.jsp" />
<hr>
<%-- 結果表示エリア --%>
<div class="result-section">

<div class="result-header">
<%-- ① 科目情報 --%>
                   科目：${subject.name}
</div>
<c:choose>
<%-- 結果が存在する場合 --%>
<c:when test="${not empty results}">
<%-- ② 成績一覧テーブル --%>
<table class="result-table">
<thead>
<tr>
<%-- ③～⑧ ヘッダー --%>
<th>入学年度</th>
<th>クラス</th>
<th>学生番号</th>
<th>氏名</th>
<th>1回</th>
<th>2回</th>
</tr>
</thead>
<tbody>
<c:forEach var="test" items="${results}">
<tr>
<%-- ⑨～⑫ 成績情報 --%>
<td>${test.student.entYear}</td>
<td>${test.student.classNum}</td>
<td>${test.student.no}</td>
<td>${test.student.name}</td>
<%-- ⑬,⑭ 点数 (Map<Integer, Integer>形式を想定) --%>
<td><c:out value="${test.points[1]}" default="-" /></td>
<td><c:out value="${test.points[2]}" default="-" /></td>
</tr>
</c:forEach>
</tbody>
</table>
</c:when>
<%-- 結果が存在しない場合 --%>
<c:otherwise>
<p class="no-data-message">学生情報が存在しませんでした</p>
</c:otherwise>
</c:choose>
</div>

</div>
</c:param>
</c:import>