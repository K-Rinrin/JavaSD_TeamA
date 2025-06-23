
<%-- 学生登録画面 --%>


<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../base.jsp">


<%-- ページタイトルの指定 --%>
<c:param name="title">得点管理システム</c:param>



	<%-- ページ本文の内容 --%>
 	<c:param name="body">



	<div class="container mt-4" style="max-width: 700px;">
		<div class="card border-0">
			<div class="card-header bg-light border-0">
				<h2 class="h5 mb-0">学生情報登録</h2>
			</div>
			<div class="card-body">
				<form action="${pageContext.request.contextPath}/main/student/studentcreateexecute" method="post">




					<%-- 入学年度の選択 --%>
					<div class="mb-3">
						<label for="ent-year-select" class="form-label">入学年度</label>
						<select name="ent_year" id="ent-year-select" class="form-select">
							<option value="">--------</option>
							<c:forEach var="ent" items="${entYearList}">
									<option value="${ent}" ${ent == ent_year ? 'selected' : ''}>${ent}</option>
							</c:forEach>
						</select>
						<%-- 入学年度未選択の場合エラーメッセージを表示させる --%>
						<c:if test="${not empty errors.ent_year}">
							<p class="text-warning mt-1">${errors.ent_year}</p>
						</c:if>
					</div>




					<%-- 学生番号の入力 --%>
					<div class="mb-3">
						<label for="no-input" class="form-label">学生番号</label>
						<input type="text" name="no" id="no-input" value="${no}" placeholder="学生番号を入力してください" maxlength="10" required class="form-control">
						<%-- 学生番号重複の場合エラーメッセージを表示させる --%>
						<c:if test="${not empty errors.no}">
							<p class="text-warning mt-1">${errors.no}</p>
						</c:if>
					</div>




					<%-- 氏名の入力 --%>
					<div class="mb-3">
						<label class="form-label">氏名</label>
						<input type="text" name="name" value="${name}" placeholder="氏名を入力してください"  maxlength="30" required class="form-control">
					</div>




					<%-- クラスの選択 --%>
					<div class="mb-4">
						<label class="form-label">クラス</label>
						<select name="class_num" class="form-select" required>
							<option value="">--------</option>
							<c:forEach var="classNum" items="${classnum}">
							<option value="${classNum}" <c:if test="${classNum == class_num}">selected</c:if>>
								${classNum}
							</option>
							</c:forEach>
						</select>
					</div>


					<%-- すべての入力が完了したら登録ボタンを押下する --%>
					<button type="submit" name="end" class="btn btn-primary">登録</button>

				</form>


				<div class="mt-3">
					<%-- 学生管理一覧に遷移する --%>
					<a href="${pageContext.request.contextPath}/main/student/STDM001">戻る</a>
				</div>


			</div>
		</div>
	</div>

	</c:param>
</c:import>