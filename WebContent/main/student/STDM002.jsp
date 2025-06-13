<%-- 学生登録画面 --%>


<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../base.jsp">

<%-- ページタイトルの指定 --%>
<c:param name="title">得点管理システム</c:param>

	<%-- ページ本文の内容 --%>
 	<c:param name="body">


 	<%-- 画面タイトルを表示 --%>
	<h2>学生情報登録</h2>


	<%-- 入学年度の選択 --%>
      <label>入学年度</label>
      <select name="ent_year" required>
        <option value="">----</option>

	        <c:forEach var="stu" items="${student}">
	          <option value="${stu.ent_year}">${stu.ent_year}</option>
        	</c:forEach>
      </select>



      <%-- 学生番号の入力（最大文字数10・必須） --%>
      <label>学生番号</label>
      <input type="text" name="no"  value="${no}" maxlength="10" required>










	</c:param>
</c:import>