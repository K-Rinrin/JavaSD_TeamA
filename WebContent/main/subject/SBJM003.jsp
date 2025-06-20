
<%-- 科目登録完了画面  --%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../base.jsp">
    <c:param name="title" value="得点管理システム" />
    <c:param name="body">

        <%-- 元のコンテナを活かし、Bootstrapのクラスで余白を追加 --%>
        <div class="SBJM003">

            <%-- ① 画面タイトル。Bootstrapのクラスで装飾 --%>
            <h2 class="p-2 mb-4 bg-body-secondary border fw-bold">科目情報登録</h2>

            <%-- ② 完了メッセージ --%>
            <%-- 元の p.message-success を、BootstrapのAlertコンポーネントに置き換え --%>
            <div class="alert alert-success text-center" role="alert">
                           登録が完了しました
            </div>

            <%-- 元の div.actions を活かし、余白を追加 --%>
            <div class="actions mt-4">
                <%-- ③ 戻るリンク --%>
                <%-- リンク間にスペースを設けるため me-3 クラスを追加 --%>
                <a href="${pageContext.request.contextPath}/main/subject/SBJM002" class="me-3">戻る</a>

                <%-- ④ 科目一覧リンク --%>
                <a href="${pageContext.request.contextPath}/main/subject/SBJM001">科目一覧</a>
            </div>
        </div>
    </c:param>
</c:import>