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

	<%-- ② 科目情報ラベル --%>
	<div class="section-label">科目情報</div>
	<div class="controls">

	<%-- ③ 入学年度 --%>
	<label>入学年度</label>

	<select name="f1">
		<option value="">-------</option>
			<c:forEach var="year" items="${entYears}">
				<option value="${year}" <c:if test="${f1 == year}">selected</c:if>>${year}</option>
			</c:forEach>
	</select>

	<%-- ④ クラス --%>
	<label>クラス</label>
	<select name="f2">
		<option value="">-------</option>
		<c:forEach var="classItem" items="${classNums}">
			<option value="${classItem.cd}" <c:if test="${f2 == classItem.cd}">selected</c:if>>${classItem.name}</option>
		</c:forEach>
	</select>

	<%-- ⑤ 科目 --%>
	<label>科目</label>
	<select name="f3">
		<option value="">-------</option>
		<c:forEach var="subjectItem" items="${subjects}">
			<option value="${subjectItem.cd}" <c:if test="${f3 == subjectItem.cd}">selected</c:if>>${subjectItem.name}</option>
		</c:forEach>
	</select>

	<%-- ⑨ 検索ボタン (科目) --%>
	<button type="submit" name="event" value="31">検索</button>

	</div>
	</div>

	<%-- 学生情報による検索セクション --%>
	<div class="search-section student-search">

		<%-- ⑩ 学生情報ラベル --%>
		<div class="section-label">学生情報</div>
		<div class="controls">

		<%-- ⑪ 学生番号ヘッダー --%>
		<label>学生番号</label>

		<%-- ⑫ 学生番号入力テキスト --%>
		<input type="text" name="f4" value="${f4}" placeholder="学生番号を入力してください" maxlength="10">
		<c:if test="${not empty errors.f4}">
			<span class="error-message-inline">${errors.f4}</span>
		</c:if>

		<%-- ⑬ 検索ボタン (学生) --%>
		<button type="submit" name="event" value="32">検索</button>
		</div>
	</div>

	<%-- ⑭ 利用方法案内メッセージ --%>
	<p class="guidance-message">科目情報を選択または学生情報を入力して検索ボタンをクリックしてください</p>
</form>

