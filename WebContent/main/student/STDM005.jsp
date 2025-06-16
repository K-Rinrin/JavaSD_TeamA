<%-- 学生情報変更完了画面 --%>


<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../base.jsp">


<%-- ページタイトルの指定 --%>
<c:param name="title">得点管理システム</c:param>


 	<%-- ページ本文の内容 --%>
 	<c:param name="body">
		<div class="card border-0">
			<div class="card-header bg-light border-0">
				<%-- 画面タイトルを表示 --%>
				<h2 class="h5 mb-0">学生情報変更</h2>
			</div>
			<div class="card-body">
				<%-- 完了メッセージの表示 --%>
				<div class="alert alert-success text-center" role="alert">
					変更が完了しました
				</div>

				<div class="mt-4">
					<%-- 学生一覧画面に遷移する --%>
					<a href="${pageContext.request.contextPath}/main/student/STDM001">学生一覧</a>
				</div>
			</div>
		</div>

	</c:param>
</c:import>