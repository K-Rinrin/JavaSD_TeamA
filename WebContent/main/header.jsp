
<%-- ヘッダー --%>

<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%-- レイアウトの定義 --%>
<header class="border-bottom" style="background-color: #dbedff;">
    <div class="container py-3">
        <div class="d-flex justify-content-between align-items-center">



			<%-- 画面タイトル --%>
			<h1 class="h3 mb-0">得点管理システム</h1>

			<%-- ログインユーザ名 --%>
			<div>
				<%-- ログインしている場合（セッションにユーザー情報がある） --%>
			    <c:if test="${not empty session_user}">
			        <span class="me-3">${session_user.name} 様</span>
			        <a href="${pageContext.request.contextPath}/main/accounts/LOGO001">ログアウト</a>
			    </c:if>
			</div>

		</div>
	</div>
</header>
