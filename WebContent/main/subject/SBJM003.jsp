
<%-- 科目登録完了画面 (SBJM003) --%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../base.jsp">
    <c:param name="title" value="得点管理システム" />
    <c:param name="body">

        <div class="SBJM003">
            <%-- ① 画面タイトル --%>
            <h2 class="h2">科目情報登録</h2>

            <%-- ② 完了メッセージ --%>
            <%-- 画像のスタイル（緑色の背景）はCSSで適用されることを想定し、クラス名を付与しています --%>
            <p class="message-success">登録が完了しました</p>

            <div class="actions">
                <%-- ③ 戻るリンク (イベントID: 41) --%>
                <%-- 設計書に基づき「科目登録画面」に遷移 --%>
                <a href="${pageContext.request.contextPath}/main/subject/SBJM002">戻る</a>

                <%-- ④ 科目一覧リンク (イベントID: 40) --%>
                <%-- 設計書に基づき「科目管理一覧画面」に遷移 --%>
                <a href="${pageContext.request.contextPath}/main/subject/SBJM001">科目一覧</a>
            </div>
        </div>
    </c:param>
</c:import>