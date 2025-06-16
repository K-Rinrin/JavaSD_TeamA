
<%-- 科目登録画面 (SB-JM002) --%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- base.jspをテンプレートとして読み込む --%>
<c:import url="../base.jsp">
    <c:param name="title" value="得点管理システム" />
    <c:param name="body">
        <%--
            エラーメッセージのスタイルを定義する想定です。
            例: .error-message { color: red; font-size: 0.9em; margin-top: 5px; }
                 .input-error { border-color: red; }
        --%>
        <%-- この画面専用のコンテナ --%>
        <div class="SBJM002">

            <%-- ① 画面タイトル --%>
            <h2>科目情報登録</h2>

<<<<<<< Upstream, based on branch 'master' of https://github.com/K-Rinrin/JavaSD_TeamA.git
            <%-- 登録フォーム --%>
            <%-- action属性には登録処理を行うサーバー側プログラムのURLを指定します --%>
            <form action="SBJM002" method="post">

                <%-- 科目コードの入力エリア --%>
=======
            <%--
                コントローラーからエラー情報が格納されたMap(errors)が
                リクエストスコープにセットされていることを想定しています。
                例:
                Map<String, String> errors = new HashMap<>();
                errors.put("cd", "科目コードを入力してください");
                request.setAttribute("errors", errors);
            --%>

            <form action="SubjectCreateExecute.action" method="post">
                <%-- 科目コード入力エリア --%>
>>>>>>> 85d3f04 mod
                <div class="form-group">
                    <label for="subject-cd">科目コード</label>
                    <%-- errors.cdが存在すれば、入力欄にエラー用のCSSクラスを適用 --%>
                    <input type="text" id="subject-cd" name="cd" value="<c:out value="${cd}"/>" placeholder="科目コードを入力してください" class="${not empty errors.cd ? 'input-error' : ''}">

                    <%-- 項目1: 科目コードのエラーメッセージ --%>
                    <c:if test="${not empty errors.cd}">
                        <div class="error-message">
                            <c:out value="${errors.cd}" />
                        </div>
                    </c:if>
<<<<<<< Upstream, based on branch 'master' of https://github.com/K-Rinrin/JavaSD_TeamA.git

                    <%-- ③ 科目コード入力テキスト --%>
                    <%-- value="${cd}" でエラー時にサーバーから返された値を再表示 --%>
                    <input type="text" id="subject-cd" name="cd" class="form-control"
                           placeholder="科目コードを入力してください" value="${cd}" maxlength="3">
=======
>>>>>>> 85d3f04 mod
                </div>

                <%-- 科目名入力エリア --%>
                <div class="form-group">
                    <label for="subject-name">科目名</label>
                    <%-- errors.nameが存在すれば、入力欄にエラー用のCSSクラスを適用 --%>
                    <input type="text" id="subject-name" name="name" value="<c:out value="${name}"/>" placeholder="科目名を入力してください" class="${not empty errors.name ? 'input-error' : ''}">

                    <%-- 項目2: 科目名のエラーメッセージ --%>
                    <c:if test="${not empty errors.name}">
                        <div class="error-message">
                            <c:out value="${errors.name}" />
                        </div>
                    </c:if>
                </div>

<<<<<<< Upstream, based on branch 'master' of https://github.com/K-Rinrin/JavaSD_TeamA.git
                <%-- 操作ボタンエリア --%>
                <div class="actions">
                    <%-- ⑥ 登録ボタン --%>
                    <button type="submit" class="btn btn-primary">登録</button>

                    <%-- ⑦ 戻るリンク --%>
                    <a href="${pageContext.request.contextPath}/main/subject/SBJM001">戻る</a>
                </div>

=======
                <input type="submit" value="登録">
>>>>>>> 85d3f04 mod
            </form>

            <a href="SubjectList.action">戻る</a>

        </div>
    </c:param>
</c:import>