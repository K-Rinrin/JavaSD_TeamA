<%-- 学生情報変更完了画面 --%>


<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../base.jsp">


<%-- ページタイトルの指定 --%>
<c:param name="title">得点管理システム</c:param>


 	<%-- ページ本文の内容 --%>
 	<c:param name="body">

 	<%-- 画面タイトルを表示 --%>
	<h2>学生情報変更</h2>

	<%-- 完了メッセージの表示 --%>
    <p><label>変更が完了しました</label></p>

    <%-- 学生一覧画面に遷移する --%>
	<a href="${pageContext.request.contextPath}/main/student/STDM001">学生一覧</a>




	</c:param>
</c:import>