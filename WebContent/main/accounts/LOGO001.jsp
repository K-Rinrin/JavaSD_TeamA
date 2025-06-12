
<%-- ログアウト画面 --%>


<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../header.jsp"/>




<title>得点管理システム</title>

	<%-- 画面タイトルの表示 --%>
	<h2>ログアウト</h2>


	<p>ログアウトしました</p>


	<%-- ログイン画面に遷移 --%>
	<a href="${pageContext.request.contextPath}/main/accounts/logi001">ログイン</a>





<c:import url="../footer.jsp"/>
