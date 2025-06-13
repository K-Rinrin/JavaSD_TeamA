
<%-- ヘッダー --%>

<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mmnu.css">
</head>


<div class="header">

	<%-- 画面タイトル --%>
	<h1 class="header-title">得点管理システム</h1>

	<%-- ログインユーザ名 --%>
	<div class="header-user">
		<span class="user-name">${session_user.username}様 </span>
		<%-- ログアウトリンク --%>
		<a href="${pageContext.request.contextPath}/main/accounts/LOGO001">ログアウト</a>
	</div>

</div>
