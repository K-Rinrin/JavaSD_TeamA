
<%-- 成績管理一覧画面 --%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../base.jsp">
<c:param name="title" value="得点管理システム" />
<c:param name="body">

<div class="GRMU001">

	<%-- 画面タイトル --%>
	<h2>成績管理</h2>


	<%-- 検索 --%>
	<form action="${pageContext.request.contextPath}/main/grade/testlist" method="get">
	<table>

		<tr>
			<th>入学年度</th>
			<th>クラス</th>
			<th>科目</th>
			<th>回数</th>
			<th></th>
		</tr>

		<tr>

			<%-- 入学年度セレクトボックス --%>
			<td>
			<select name="f1">
				<option value="">--------</option>
				<c:forEach var="stu" items="${student}">
					<option value="${stu.int_year}">${stu.int_year}</option>
				</c:forEach>
			</select>
			</td>

			<%-- クラスセレクトボックス --%>
			<td>
			<select name="f2">
				<option value="">--------</option>
				<c:forEach var="stu" items="${student}">
					<option value="${stu.class_num}">${stu.class_num}</option>
				</c:forEach>
			</select>
			</td>

			<%-- 科目セレクトボックス --%>
			<td>
			<select name="f3">
				<option value="">--------</option>
				<c:forEach var="subject" items="${subject}">
					<option value="${subject.cd}">${subject.name}</option>
				</c:forEach>
			</select>
			</td>

			<%-- 回数セレクトボックス --%>
			<td>
			<select name="f4">
				<option value="">--------</option>
				<c:forEach var="test" items="${test}">
					<option value="${test.no}">${test.no}</option>
				</c:forEach>
			</select>
			</td>

			<%-- 検索ボタン --%>
			<td>
				<button type="submit">検索</button>
			</td>
		</tr>

	</table>
	</form>

	<%-- 検索結果・点数登録 --%>
	<c:if test="{not empty TestList}">
	<%--科目と回数の表示 --%>
	<h2>科目：${subjectName}(${param.f4})</h2>

	<%-- エラーメッセージある場合 --%>
	<c:if test="${not empty errorMsg }">
		<p>${errorMsg}</p>
	</c:if>

	<form action="${pageContext.request.contextPath}/main/grade/testlist" method="post">

		<%-- 検索条件を保持 --%>
		<input type="hidden" name="f1" value="${param.f1}">
		<input type="hidden" name="f2" value="${param.f2}">
		<input type="hidden" name="f3" value="${param.f3}">
		<input type="hidden" name="f4" value="${param.f4}">

		<table>
		<tr>
			<th>入学年度</th>
			<th>クラス</th>
			<th>学生番号</th>
			<th>氏名</th>
			<th>得点</th>
		</tr>

		<c:forEach var="score" items="${scorelist}">
			<tr>
				<%-- 入学年度 --%>
				<td>
					<c:forEach var="stu" items="${student}">
					 <c:if test="${stu.no == score.studentNo}">
						${stu.intyear}
					 </c:if>
					</c:forEach>
				</td>

				<%-- クラス --%>
				<td>${score.classnum}</td>

				<%-- 学生番号 --%>
				<td>${score.studentno}</td>

				<%-- 氏名 --%>
				<td>
				 <c:forEach var="stu" items="${student}">
				 <c:if test="${stu.no == score.studentno}">
					${stu.name}
				 </c:if>
				 </c:forEach>
				</td>

				<%-- 点数入力欄 --%>
				<td>
				 <input type="number" name="point_${score.studentno}"
				  value="${score.point}" min="0" max="100" required />
				</td>

			</tr>
		</c:forEach>
		</table>

		<br>
		<button type="submit">登録して終了</button>

	</form>
	</c:if>

</div>
</c:param>
</c:import>