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



	<form action="${pageContext.request.contextPath}/main/student/STDM003" method="post">


	<%-- 入学年度の選択 --%>
      <label>入学年度</label><br>
      <select name="ent_year" required>
        <option value="">----</option>

	        <c:forEach var="stu" items="${student}">
	          <option value="${stu.ent_year}">${stu.ent_year}</option>
        	</c:forEach>
      </select>
      <br>



      <%-- 学生番号の入力（最大文字数10・必須） --%>
      <label>学生番号</label><br>
      <input type="text" name="no"  value="${no}" placeholder="学生番号を入力してください" maxlength="10" required>
       <br>


      <%-- 氏名の入力（最大文字数30・必須） --%>
      <label>氏名</label><br>
      <input type="text" name="name"  value="${name}" placeholder="氏名を入力してください"  maxlength="30" required>
       <br>



       <%-- クラスの選択 --%>
      <label>クラス</label><br>
      <select name="class_num" required>
        <option value="">----</option>

	        <c:forEach var="classNum" items="${classNum}">
	          <option value="${classNum.classNum}">${classNum.classNum}</option>
        	</c:forEach>
      </select>
      <br>


      <%-- 登録完了画面に遷移 --%>
	  <button type="submit" name="end">登録して終了 </button>


	  </form>



	  <%-- 学生一覧画面に遷移する --%>
	  <a href="${pageContext.request.contextPath}/main/student/STDM001">戻る</a>




	</c:param>
</c:import>