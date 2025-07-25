
<%-- 成績参照検索画面 --%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../base.jsp">
<c:param name="title" value="得点管理システム" />
<c:param name="body">

<div class="GRMR001">

	<%-- 画面タイトル --%>
	<h2 class="p-2 mb-4 bg-body-secondary border fw-bold">成績参照</h2>

	<%-- 共通検索フォームをインポート --%>
	<c:import url="grade-search.jsp" />


	<p class="class=mt-4 mb-3 text-info">科目情報を選択または学生情報を入力して検索ボタンをクリックしてください</p>

</div>
</c:param>
</c:import>