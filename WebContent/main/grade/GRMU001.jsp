
<%-- 成績管理一覧画面 --%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../base.jsp">
<c:param name="title" value="得点管理システム" />
<c:param name="body">

<div class="container">

	<%-- 画面タイトル --%>
	<h2 class="p-2 mb-4 bg-body-secondary border fw-bold">成績管理</h2>


	<%-- 検索 --%>
	<div class="p-3 mb-4 rounded border">
	<form action="${pageContext.request.contextPath}/main/grade/GRMU001" method="get">

		<div class="row g-3 align-items-end">

			<%-- 入学年度セレクトボックス --%>
			<div class="col-md">
				<label for="f1_select" class="form-label">入学年度</label>
				<select name="f1" id="f1_select" class="form-select">
					<option value="">--------</option>
					<c:forEach var="stu" items="${student}">
						<option value="${stu.entYear}">${stu.entYear}</option>
					</c:forEach>
				</select>
			</div>

			<%-- クラスセレクトボックス  --%>
			<div class="col-md">
				<label for="f2_select" class="form-label">クラス</label>
				<select name="f2" id="f2_select" class="form-select">
					<option value="">--------</option>
					<c:forEach var="classItem" items="${classNums}">
						<option value="${classItem.class_num}">${classItem.class_num}</option>
					</c:forEach>
				</select>
			</div>

			<%-- 科目セレクトボックス --%>
			<div class="col-md">
				<label for="f3_select" class="form-label">科目</label>
				<select name="f3" id="f3_select" class="form-select">
					<option value="">--------</option>
					<c:forEach var="subject" items="${subjects}">
						<option value="${subject.cd}">${subject.name}</option>
					</c:forEach>
				</select>
			</div>

			<%-- 回数セレクトボックス --%>
			<div class="col-md">
				<label for="f4_select" class="form-label">回数</label>
				<select name="f4" id="f4_select" class="form-select">
					<option value="">--------</option>
					<option value="1">1</option>
					<option value="2">2</option>
				</select>
			</div>

			<%-- 検索ボタン --%>
			<div class="col-md-auto">
				<button type="submit" class="btn btn-secondary">検索</button>
			</div>
		</div>

	</form>
	</div>



	<%-- 検索結果・点数登録 --%>
	<c:if test="${not empty scorelist}">

	<%--科目と回数の表示 --%>
	<p class="mt-4 mb-3 ">科目：${subjectName}(${param.f4}回)</p>

	<%-- エラーメッセージある場合 --%>
	<c:if test="${not empty errorMsg }">
		<p>${errorMsg}</p>
	</c:if>

	<form action="${pageContext.request.contextPath}/main/grade/GRMU001Execute" method="post">

		<%-- 検索条件を保持 --%>
		<input type="hidden" name="f1" value="${param.f1}">
		<input type="hidden" name="f2" value="${param.f2}">
		<input type="hidden" name="f3" value="${param.f3}">
		<input type="hidden" name="f4" value="${param.f4}">

		<table class="table aligin-middle">

		<thead class="table-light">
			<tr>
				<th>入学年度</th>
				<th>クラス</th>
				<th>学生番号</th>
				<th>氏名</th>
				<th>得点</th>
				<th></th>
			</tr>
		</thead>

		<c:forEach var="score" items="${scorelist}">
			<tr>
				<%-- 入学年度 --%>
				<td>${score.student.entYear}</td>

				<%-- クラス --%>
				<td>${score.student.classNum}</td>

				<%-- 学生番号 --%>
				<td>${score.student.no}</td>

				<%-- 氏名 --%>
				<td>${score.student.name}</td>

				<%-- 点数入力欄 --%>
				<td>
				 <input type="number" name="point_${score.student.no}"
				  value="${score.point}" min="0" max="100" required
				  class="from-control" style="width: 120px;"/>
				</td>

				<%-- 削除チェックボックス --%>
				<td>
					<input type="checkbox" name="check_${score.student.no}"
					value="on">
				</td>

			</tr>
		</c:forEach>
		</table>


		<button type="submit" class="btn btn-secondary mt-3">登録して終了</button>

	</form>
	</c:if>

</div>
</c:param>
</c:import>