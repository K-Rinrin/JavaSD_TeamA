
<%-- 科目登録画面  --%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- base.jspをテンプレートとして読み込む --%>
<c:import url="../base.jsp">
    <c:param name="title" value="得点管理システム" />
    <c:param name="body">

        <%-- この画面専用のコンテナ。Bootstrapのクラスで余白を追加 --%>
        <div class="SBJM002">

            <%-- ① 画面タイトル。Bootstrapのクラスで装飾 --%>

                <h2 class="p-2 mb-4 bg-body-secondary border fw-bold">科目情報登録</h2>


            <%-- 登録処理を実行するためのフォーム --%>
            <form action="${pageContext.request.contextPath}/main/subject/SBJM002Execute" method="post">
                <%-- 科目コード入力エリア。元のdiv.form-groupを水平フォームのrowに置き換え --%>
                <div class="row mb-3">
                    <%-- ② 科目コード ラベル。Bootstrapのクラスを追加 --%>
                    <label for="subject-cd" class="col-md-2 col-form-label">科目コード</label>
                    <div class="col-md-10">
                        <%-- ③ 科目コード入力テキスト。Bootstrapのクラスを追加 --%>
                        <input type="text" id="subject-cd" name="cd" class="form-control" maxlength="3" required
                               value="<c:out value='${cd}'/>" placeholder="科目コードを入力してください">
                    </div>
                </div>

                <%-- 科目名入力エリア。元のdiv.form-groupを水平フォームのrowに置き換え --%>
                <div class="row mb-4">
                    <%-- ④ 科目名 ラベル。Bootstrapのクラスを追加 --%>
                    <label for="subject-name" class="col-md-2 col-form-label">科目名</label>
                    <div class="col-md-10">
                        <%-- ⑤ 科目名入力テキスト。Bootstrapのクラスを追加 --%>
                        <input type="text" id="subject-name" name="name" class="form-control" required
                               value="<c:out value='${name}'/>" placeholder="科目名を入力してください">
                    </div>
                </div>

                <%-- ボタンとリンクの配置を調整するためのrow --%>
                <div class="row">
                    <div class="col-md-10 offset-md-2">
                        <%-- ⑥ 登録ボタン。Bootstrapのクラスを追加 --%>
                        <input type="submit" value="登録" class="btn btn-primary">
                    </div>
                </div>
            </form>

            <%-- ⑦ 戻るリンク。ボタンと位置を合わせるためrowで囲む --%>
            <div class="row mt-3">
                <div class="col-md-10 offset-md-2">
                    <a href="${pageContext.request.contextPath}/main/subject/SBJM001">戻る</a>
                </div>
            </div>

        </div>
    </c:param>
</c:import>