
<%-- メイン画面のレイアウト --%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="./base.jsp">
<c:param name="title">得点管理システム</c:param>

	<c:param name="body">



		<div class="container mt-0">


			<%-- 画面タイトル --%>
			<header class="mb-4 bg-light p-3 rounded">
				<h2 class="h5 mb-0">メニュー</h2>
			</header>


			<div class="row g-4">

				<%-- 学生管理リンク --%>

				<div class="col-md-4">
					<a href="${pageContext.request.contextPath}/main/student/STDM001"  class="text-decoration-none">
						<div class="card h-100 shadow-sm" style="background-color: #ffcccc;">
							<div class="card-body d-flex align-items-center justify-content-center">
								<h5 class="card-title text-primary mb-0 text-decoration-underline">学生管理</h5>
							</div>
						</div>
					</a>
				</div>




				<%-- 成績管理リンク --%>
				<div class="col-md-4">
					<div class="card h-100 bg-success-subtle shadow-sm">
						<div class="card-body  text-center">
							<h5 class="card-title">成績管理</h5>
							<%-- リンクのリスト --%>
							<ul class="list-unstyled mt-3">
								<li><a href="${pageContext.request.contextPath}/main/grade/GRMU001">成績登録</a></li>
								<li><a href="${pageContext.request.contextPath}/main/grade/GRMR001">成績参照</a></li>
							</ul>
						</div>
					</div>
				</div>




				<%-- 科目管理リンク --%>

				<div class="col-md-4">
					<a href="${pageContext.request.contextPath}/main/subject/SBJM001" class="text-decoration-none">
						<div class="card h-100 shadow-sm" style="background-color: #c1c1ff;">
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
