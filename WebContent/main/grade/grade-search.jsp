
<%-- 成績参照 共通検索フォーム部品 --%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
 このJSPをインポートする側は、以下の変数をリクエストスコープにセットする必要があります。
 - entYears, classNums, subjects: ドロップダウンリストのデータ
 - f1, f2, f3, f4: 検索条件の保持用
 - errors: エラーメッセージのマップ
--%>

<c:if test="${not empty errors.condition_error}">
	<div class="error-message">${errors.condition_error}</div>
</c:if>


<div class="p-3 mb-4 rounded border">
<form action="" method="post">

	<%-- 科目情報による検索セクション --%>
	<div class="row align-items-center ">

	<%-- 科目情報ラベル --%>
	<div class="col-md-2 fw-bold">科目情報</div>

	<div class="col-md-10">

	<div class="row g-3 align-items-end">

		<%-- 入学年度 --%>
		<div class="col-md">
		<label for="f1-select" class="form-label">入学年度</label>
			<select name="f1" id="f1-select" class="form-select">
				<option value="">-------</option>
				<c:forEach var="year" items="${entYears}">
					<option value="${year.entYear}" <c:if test="${f1 == year.entYear}">selected</c:if>>${year.entYear}</option>
				</c:forEach>
			</select>
		</div>

		<%-- クラス --%>
		<div class="col-md">
		<label for="f2-select" class="form-label">クラス</label>
			<select name="f2" id="f2-select" class="form-select">
				<option value="">-------</option>
				<c:forEach var="classItem" items="${classNums}">
					<option value="${classItem.class_num}" <c:if test="${f2 == classItem.class_num}">selected</c:if>>${classItem.class_num}</option>
				</c:forEach>
			</select>
		</div>

		<%-- 科目 --%>
		<div class="col-md">
		<label for="f3-select" class="form-label">科目</label>
			<select name="f3" id="f3-select" class="form-select">
				<option value="">-------</option>
				<c:forEach var="subjectItem" items="${subjects}">
					<option value="${subjectItem.cd}" <c:if test="${f3 == subjectItem.cd}">selected</c:if>>${subjectItem.name}</option>
				</c:forEach>
			</select>
		</div>

	<%-- 検索ボタン (科目) --%>
	<div class="col-md-auto">
		<button type="submit" class="btn btn-secondary">検索</button>
	</div>

	<c:if test="${not empty errors.subject_search_error}">
		<div class="text-warning">
			<span>${errors.subject_search_error}</span>

		</div>
	</c:if>


	</div>
	</div>

	<hr class="my-2">

	<%-- 学生情報による検索セクション --%>
	<div class="row align-items-center">

		<%-- 学生情報ラベル --%>
		<div class="col-md-2 fw-bold">学生情報</div>
		<div class="col-md-10">

		<%--  学生番号ヘッダー --%>
		<label for="f4-input" class="form-label">学生番号</label>

		<%-- 学生番号入力テキスト --%>
		<div class="row g-2">
			<%-- ⑫ 学生番号入力欄 --%>
			<div class="col">
				<input type="text" name="f4" id="f4-input" value="${f4}" placeholder="学生番号を入力してください" maxlength="10"
                 class="form-control ${not empty errors.f4 ? 'is-invalid' : ''} required">
				<c:if test="${not empty errors.f4}">

					<div class="invalid-feedback d-block">${errors.f4}</div>
				</c:if>
			</div>

			<%-- 検索ボタン (学生) --%>
			<div class="col-md-auto">
				<button type="submit" class="btn btn-secondary">検索</button>
			</div>
		</div>
	</div>
</div>
</div>

</form>
</div>

