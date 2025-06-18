<%-- 学生管理一覧画面 --%>


<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../base.jsp">


<%-- ページタイトルの指定 --%>
<c:param name="title">得点管理システム</c:param>


 	<%-- ページ本文の内容 --%>
 	<c:param name="body">

	<div class="container mt-4">
		<%-- 画面タイトルと検索フォームをカードで囲む --%>
		<div class="card border-0">
			<div class="card-header bg-light border-0">
                    <h2 class="h5 mb-0">学生管理</h2>
            </div>

			<div class="card-body">
				<div class="text-end mb-3">
					<%-- 学生登録画面に遷移する --%>
					<a href="${pageContext.request.contextPath}/main/student/STDM002">新規登録</a>
				</div>


			<div class="border p-3 rounded">
				<form action="${pageContext.request.contextPath}/main/student/StudentListController" method="get" class="d-flex align-items-end flex-wrap gap-3">

					<%-- 入学年度の選択 --%>
					<div>
						<label class="form-label">入学年度</label>
						<select name="entYear" class="form-select" style="width: 180px;">
							<option value="">----</option>
							<c:forEach var="stu" items="${student}">
								<option value="${stu.entYear}">${stu.entYear}</option>
							</c:forEach>
						</select>

						<%-- 未選択の場合エラーが表示される --%>
						<c:if test="${not empty error.entYear}">
                			<div class="invalid-feedback d-block">
                    			${error.entYear}
							</div>
						</c:if>
					</div>



					<%-- クラスの選択 --%>
					<div>
						<label class="form-label">クラス</label>
						<select name="classNum" class="form-select" style="width: 180px;">
							<option value="">----</option>
							<c:forEach var="stu" items="${student}">
								<option value="${stu.classNum}">${stu.classNum}</option>
							</c:forEach>
						</select>
					</div>


					<%-- 在学中のON/OFF --%>
					<div class="form-check mb-2">
						<input type="checkbox" name="isAttend" class="form-check-input" id="is-attend-check">
						<label class="form-check-label" for="is-attend-check">在学中</label>
					</div>


					<%-- 絞り込みボタン --%>
					<div>
						<button type="submit" class="btn btn-secondary">絞込み</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>





	<%-- DBから学生一覧表を表示する --%>
	<c:choose>
		<%-- studentリストが空でない場合のみテーブルを表示 --%>
		<c:when test="${not empty student}">

			<%-- 検索結果の件数を表示 --%>
			<div id="検索結果">検索結果: ${件数}件</div>

				<table class="table table-striped table-hover mt-4">
					<thead><tr>
						<th>入学年度</th>
						<th>学生番号</th>
						<th>氏名</th>
						<th>クラス</th>
						<th>在学中</th>
						<th></th></tr>
					</thead>
					<tbody>
					<c:forEach var="stu" items="${student}">
						<tr>
							<td>${stu.entYear}</td>
							<td>${stu.no}</td>
							<td>${stu.name}</td>
							<td>${stu.classNum}</td>
							<td>${stu.isAttend}</td>
							<%-- 学生変更画面に遷移する --%>
							<td>${stu.isAttend ? '在学' : ''}</td>
							<td><a href="${pageContext.request.contextPath}/main/student/STDM004? no=${stu.no}">変更</a></td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
		</c:when>

		<%-- studentリストが空の場合にメッセージを表示 --%>
		<c:otherwise>
			<p>学生情報が存在しませんでした。</p>
		</c:otherwise>

	</c:choose>



	</c:param>
</c:import>
