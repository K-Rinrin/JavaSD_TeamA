<%-- ログアウト画面 --%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../base.jsp">


    <c:param name="title">得点管理システム</c:param>

	    <c:param name="body">

			<%-- レイアウトの定義 --%>
    		<div class="container mt-0">
        		<div class="row justify-content-center">
            		<div class="col-md-10 col-lg-8">


						<%-- 画面タイトルを表示する --%>
		                <header class="bg-light p-3 rounded mb-4 py-2">
		                    <h2 class="h3 card-title mb-0">ログアウト</h2>
		                </header>

		                <%-- 完了メッセージを表示する --%>
		                <div class="alert alert-success text-center py-1" role="alert">
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
		    </div>

		</c:param>

</c:import>