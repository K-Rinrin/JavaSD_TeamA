
<%-- 科目別成績一覧画面 --%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../base.jsp">
<c:param name="title" value="得点管理システム" />
<c:param name="body">

<div class="GRMR002">

	<h2 class="p-2 mb-4 bg-body-secondary border fw-bold">成績一覧（科目）</h2>

	<%-- 共通検索フォームをインポート --%>
	<c:import url="grade-search.jsp" />

		<%-- 結果表示エリア --%>
		<div class="mt-4">

			<c:if test="${not empty subject}">
				<div class="mb-3 fw-bold">
					科目：${subject.name}
				</div>
			</c:if>

		<c:choose>

		<%-- 結果が存在する場合 --%>
		<c:when test="${not empty results}">

		<%-- 成績一覧テーブル --%>
		<table class="table table-borderd aligin-meddle">

		<thead class="table-light">
		<tr>
			<%-- ヘッダー --%>
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
				<td>${test.entYear}</td>
				<td>${param.f2}</td>
				<td>${test.studentNo}</td>
				<td>${test.studentName}</td>
				<td>

					 ${test.points['1']}

				</td>
				<td>

					 ${test.points['2']}

				</td>
			</tr>
		</c:forEach>
		</tbody>
		<%-- // ResultSet rs からデータを取得していると仮定

Map<String, Integer> points = new HashMap<>();

while (rs.next()) {

    // ... 他の学生情報などを取得 ...

    int testNo = rs.getInt("test_no"); // DBから取得したテスト回が数値の場合

    int point = rs.getInt("point");

    // ★★★ 数値のキーを文字列に変換してMapに入れる ★★★

    points.put(String.valueOf(testNo), point);

}

test.setPoints(points);
  --%>

		</table>
		</c:when>

		<%-- 結果が存在しない場合 --%>
		<c:otherwise>
			<p class="mt-3">学生情報が存在しませんでした</p>
		</c:otherwise>
		</c:choose>
		</div>
</div>
</c:param>
</c:import>