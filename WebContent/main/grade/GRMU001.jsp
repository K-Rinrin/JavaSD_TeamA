
<%-- 成績管理一覧画面 --%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../base.jsp">
<c:param name="title" value="得点管理システム" />
<c:param name="body">

<div class="GRMU001">

	<%-- 画面タイトル --%>
	<h2>成績管理</h2>

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

			<!-- 科目セレクトボックス -->
			<td>
			<select name="f3">
				<option value="">--------</option>
				<c:forEach var="subject" items="${subject}">
					<option value="${subject.cd}">${subject.name}</option>
				</c:forEach>
			</select>
			</td>

			<!-- 回数セレクトボックス -->
			<td>
			<select name="f4">
				<option value="">--------</option>
				<c:forEach var="test" items="${test}">
					<option value="${test.no}">${test.no}</option>
				</c:forEach>
			</select>
			</td>

			<!-- 検索ボタン -->
			<td>
				<button type="submit">検索</button>
			</td>
		</tr>

	</table>

</div>
</c:param>
</c:import>