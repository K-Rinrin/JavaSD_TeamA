
<%-- 科目削除完了画面 --%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../base.jsp">
<c:param name="title" value="得点管理システム" />
<c:param name="body">

<div class="SBJM007">

    <%-- ① 画面タイトル (h2) --%>
    <h2>科目情報削除</h2>

    <%--
        ② 完了メッセージ (p)
        画面イメージの緑色の背景を表現するため、
        共通CSSで定義されているクラス(例: .completion-message)を付与する想定です。
    --%>
    <p class="completion-message">
        削除が完了しました
    </p>

    <%-- ③ 科目一覧リンク (a) --%>
    <a href="SubjectList.action">科目一覧</a>





</div>
</c:param>
</c:import>