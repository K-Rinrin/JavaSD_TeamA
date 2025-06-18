<%-- ログアウト画面 --%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../base.jsp">


    <c:param name="title">得点管理システム</c:param>

	    <c:param name="body">


		    <div class="container d-flex align-items-center justify-content-center" style="p-5;">
		    	<div class="card-body p-4 p-md-5">

		         	<%-- 画面タイトルの表示 --%>
		            <h2 class="h3 card-title mb-4">ログアウト</h2>

		            <%-- 完了メッセージの表示 --%>
		            <div class="alert alert-success text-center" role="alert">
		            	ログアウトしました
		            </div>


		            <%-- ログイン画面へ遷移する --%>
		            <div class="mt-4">
		            	<a href="${pageContext.request.contextPath}/main/accounts/LOGI001">
		                	ログイン
		                </a>
		            </div>


				</div>
			</div>


	    </c:param>
</c:import>