
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



<form action="" method="post">

	<%-- 科目情報による検索セクション --%>
	<div class="search-section subject-search">

	<%-- 科目情報ラベル --%>
	<div class="section-label">科目情報</div>
	<div class="controls">

	<%-- 入学年度 --%>
	<label>入学年度</label>

	<select name="f1">
		<option value="">-------</option>
			<c:forEach var="year" items="${entYears}">
				<option value="${year}" <c:if test="${f1 == year}">selected</c:if>>${year}</option>
			</c:forEach>
	</select>

	<%-- クラス --%>
	<label>クラス</label>
	<select name="f2">
		<option value="">-------</option>
		<c:forEach var="classItem" items="${classNums}">
			<option value="${classItem.cd}" <c:if test="${f2 == classItem.cd}">selected</c:if>>${classItem.name}</option>
		</c:forEach>
	</select>

	<%--  科目 --%>
	<label>科目</label>
	<select name="f3">
		<option value="">-------</option>
		<c:forEach var="subjectItem" items="${subjects}">
			<option value="${subjectItem.cd}" <c:if test="${f3 == subjectItem.cd}">selected</c:if>>${subjectItem.name}</option>
		</c:forEach>
	</select>

	<%-- 検索ボタン (科目) --%>
	<button type="submit">検索</button>

	<c:if test="${not empty errors.subject_search_error}">
		<div class="error-message subject-error-area">
			<%-- コントローラーがセットしたメッセージがここに表示される --%>
			<span>${errors.subject_search_error}</span>
		</div>
	</c:if>

	</div>
	</div>

	<%-- 学生情報による検索セクション --%>
	<div class="search-section student-search">

		<%-- 学生情報ラベル --%>
		<div class="section-label">学生情報</div>
		<div class="controls">

		<%--  学生番号ヘッダー --%>
		<label>学生番号</label>

		<%-- 学生番号入力テキスト --%>
		<input type="text" name="f4" value="${f4}" placeholder="学生番号を入力してください" maxlength="10">
		<c:if test="${not empty errors.f4}">
			<span class="error-message-inline">${errors.f4}</span>
		</c:if>

		<%-- 検索ボタン (学生) --%>
		<button type="submit">検索</button>
		</div>
	</div>

</form>

