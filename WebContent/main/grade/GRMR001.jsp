<%-- 成績参照検索画面 --%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../base.jsp">
<c:param name="title" value="得点管理システム" />
<c:param name="body">
<div class="GRMR001">
<%-- ① 画面タイトル --%>
<h2>成績参照</h2>
<%-- 共通検索フォームをインポート --%>
<c:import url="grade-search.jsp" />
<%-- この画面では結果表示エリアは不要 --%>
</div>
</c:param>
</c:import>