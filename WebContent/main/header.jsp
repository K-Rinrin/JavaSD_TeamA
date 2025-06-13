
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

	<%-- ログインしている場合（セッションにユーザー情報がある） --%>
    <c:if test="${not empty session_user}">
        <span>${session_user.name} 様</span>
        <a href="${pageContext.request.contextPath}/main/accounts/LOGO001">ログアウト</a>
    </c:if>



	</div>

</div>
