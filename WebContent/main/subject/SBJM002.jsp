
<%-- 科目登録画面 (SBJM002) --%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- base.jspをテンプレートとして読み込む --%>
<c:import url="../base.jsp">
    <c:param name="title" value="得点管理システム" />
    <c:param name="body">

        <%-- この画面専用のコンテナ --%>
        <div class="SBJM002">

            <%-- ① 画面タイトル --%>
            <h2 class="h2">科目情報登録</h2>

            <%-- 登録フォーム --%>
            <%-- action属性には登録処理を行うサーバー側プログラムのURLを指定します --%>
            <form action="SBJM002" method="post">

                <%-- 科目コードの入力エリア --%>
                <div class="form-group">
                    <%-- ② 科目コード ラベル --%>
                    <label for="subject-cd">科目コード</label>

                    <%-- 科目コードに関するエラーメッセージを表示 --%>
                    <%-- コントローラから "errors" というMapでエラー情報が渡されることを想定 --%>
                    <c:if test="${not empty errors.cd}">
                        <div class="error-message">${errors.cd}</div>
                    </c:if>

                    <%-- ③ 科目コード入力テキスト --%>
                    <%-- value="${cd}" でエラー時にサーバーから返された値を再表示 --%>
                    <input type="text" id="subject-cd" name="cd" class="form-control"
                           placeholder="科目コードを入力してください" value="${cd}" maxlength="3">
                </div>

                <%-- 科目名の入力エリア --%>
                <div class="form-group">
                    <%-- ④ 科目名 ラベル --%>
                    <label for="subject-name">科目名</label>

                    <%-- 科目名に関するエラーメッセージを表示 --%>
                    <c:if test="${not empty errors.name}">
                        <div class="error-message">${errors.name}</div>
                    </c:if>

                    <%-- ⑤ 科目名入力テキスト --%>
                    <input type="text" id="subject-name" name="name" class="form-control"
                           placeholder="科目名を入力してください" value="${name}">
                </div>

                <%-- 操作ボタンエリア --%>
                <div class="actions">
                    <%-- ⑥ 登録ボタン --%>
                    <button type="submit" class="btn btn-primary">登録</button>

                    <%-- ⑦ 戻るリンク --%>
                    <a href="${pageContext.request.contextPath}/main/subject/SBJM001">戻る</a>
                </div>

            </form>

        </div> <%-- / .SBJM002 --%>

    </c:param>
</c:import>