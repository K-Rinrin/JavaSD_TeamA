
<%-- ヘッダー --%>

<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="header">

	<%-- 画面タイトル --%>
	<h1>得点管理システム</h1>
	<%-- ログインユーザ名 --%>
	<span>${session_user.username}様 </span>
	<%-- ログアウトリンク --%>
	<a href="${pageContext.request.contextPath}/main/accounts/LOGO001">ログアウト</a>

</div>
