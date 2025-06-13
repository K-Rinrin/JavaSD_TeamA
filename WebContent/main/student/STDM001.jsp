<%-- 学生管理一覧画面 --%>


<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../base.jsp">


<%-- ページタイトルの指定 --%>
<c:param name="title">得点管理システム</c:param>


 	<%-- ページ本文の内容 --%>
 	<c:param name="body">


	<%-- 画面タイトルを表示 --%>
	<h2>学生管理</h2>


	<%-- 入学年度の選択 --%>
      <label>入学年度</label>
      <select name="f1" required>
        <option value="">----</option>

	        <c:forEach var="stu" items="${student}">
	          <option value="${stu.ent_year}">${stu.ent_year}</option>
        	</c:forEach>
      </select>


	<%-- クラスの選択 --%>
	<label>クラス</label>
      <select name="f2" required>
        <option value="">----</option>

	        <c:forEach var="stu" items="${student}">
	          <option value="${stu.class_num}">${stu.class_num}</option>
        	</c:forEach>
      </select>


	<%-- 在学中のON/OFF --%>
	<label>在学中</label>
	<input type="checkbox" name="f3">


	<%-- 学生登録画面に遷移する --%>
	<a href="${pageContext.request.contextPath}/main/student/STDM002">新規登録</a>


	<%-- 絞り込みボタン --%>
	<input type="button" value="絞り込み">





	<%-- 検索結果の件数を表示 --%>
	<div id="検索結果">検索結果: ${件数}件</div>



	<%-- DBから学生一覧表を表示する --%>
	<table border="1">
		<thead><tr><th>入学年度</th><th>学生番号</th><th>氏名</th><th>クラス</th><th>在学中</th><th></th></tr></thead>
		<tbody>
		<c:forEach var="stu" items="${student}">
			<tr>
				<td>${stu.entYear}</td>
				<td>${stu.studentNo}</td>
				<td>${stu.Name}</td>
				<td>${stu.classNum}</td>
				<td>${stu.isAttend}</td>
				<%-- 学生変更画面に遷移する --%>
				<td><a href="${pageContext.request.contextPath}/main/student/STDM004">変更</a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>



	<%-- エラーメッセージ表示 --%>
	<%-- 学生情報が存在しない場合「学生情報が存在しませんでした」と表示される --%>
    <c:if test="${not empty error}">
      <p style="color: red">${error}</p>
    </c:if>




	</c:param>
</c:import>
