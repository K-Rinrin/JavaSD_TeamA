
<%-- メイン画面 --%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="./base.jsp">
<c:param name="title" value="得点管理システム" />
<c:param name="body">



<div class="container mt-4">

	<%-- 画面タイトル --%>
	<header class="mb-4">
		<h2>メニュー</h2>
	</header>



	<div class="row g-4">

		<%-- 学生管理リンク --%>
		<div class="col-md-4">
			<a href="${pageContext.request.contextPath}/student/SBJM001"  class="text-decoration-none">
				<div class="card h-100 bg-danger-subtle shadow-sm">
					<div class="card-body d-flex align-items-center justify-content-center">
						<h5 class="card-title text-primary mb-0 text-decoration-underline">学生管理</h5>
					</div>
				</div>
			</a>
		</div>




		<%-- 成績管理 --%>
		<div class="col-md-4">
			<div class="card h-100 bg-success-subtle shadow-sm">
				<div class="card-body  text-center">
					<h5 class="card-title">成績管理</h5>
					<%-- リンクのリスト --%>
					<ul class="list-unstyled mt-3">
						<li><a href="${pageContext.request.contextPath}/grade/GRMU001">成績登録</a></li>
						<li><a href="${pageContext.request.contextPath}/grade/GRMR001">成績参照</a></li>
					</ul>
				</div>
			</div>
		</div>




		<%-- 科目管理リンク --%>
		<div class="col-md-4">
			<a href="${pageContext.request.contextPath}/subject/SBJM001" class="text-decoration-none">
				<div class="card h-100 bg-primary-subtle shadow-sm">
					<div class="card-body d-flex align-items-center justify-content-center">
						<h5 class="card-title text-primary mb-0 text-decoration-underline">科目管理</h5>
					</div>
				</div>
			</a>
		</div>


	</div>
</div>



</c:param>
</c:import>