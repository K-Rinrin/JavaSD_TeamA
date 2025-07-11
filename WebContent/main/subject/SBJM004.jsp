
<%-- 科目変更画面  --%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../base.jsp">
    <c:param name="title" value="得点管理システム" />
    <c:param name="body">

        <%-- 元のコンテナを活かし、Bootstrapのクラスで余白を追加 --%>
        <div class="SBJM004">

            <%-- ① 画面タイトル --%>
            <div class="pb-2 mb-4 border-bottom">
                <h2 class="p-2 mb-4 bg-body-secondary border fw-bold">科目情報変更</h2>
            </div>

            <%-- 変更処理を実行するためのフォーム --%>
            <form action="${pageContext.request.contextPath}/main/subject/SBJM004Execute" method="post">

                <%-- ②,③ 科目コード表示エリア (変更不可) --%>
                <div class=" mb-3">
                    <label for="subject-cd" class="form-label">科目コード</label>
                    <div class="col-md-10">
                        <%-- form-control-plaintextで、枠線なしのテキストとして表示 --%>
                        <input type="text" id="subject-cd" class="form-control-plaintext" readonly
                               value="<c:out value='${subject.cd}'/>">
                        <%-- どの科目を変更するのかをサーバーに伝えるための隠しフィールド --%>
                        <input type="hidden" name="cd" value="<c:out value='${subject.cd}'/>">
                    </div>
                </div>

                <%-- ④,⑤ 科目名入力エリア (変更可能) --%>
                <div class="mb-4">
                    <label for="subject-name" class="form-label">科目名</label>
                    <div class="col-md-10">
                        <input type="text" id="subject-name" name="name" class="form-control" required
                               value="<c:out value='${subject.name}'/>">
                    </div>
                </div>



            <%-- ⑥ 変更ボタン (左揃え) --%>
            <div class="mt-4"> <%-- 上のフォーム項目との間に余白を設ける --%>
                <input type="submit" value="変更" class="btn btn-primary">
            </div>

            <%-- ⑦ 戻るリンク (左揃え) --%>
            <div class="mt-2"> <%-- 変更ボタンとの間に少し余白を設ける --%>
                <a href="${pageContext.request.contextPath}/main/subject/SBJM001">戻る</a>
            </div>

        </div>

    </c:param>
</c:import>
