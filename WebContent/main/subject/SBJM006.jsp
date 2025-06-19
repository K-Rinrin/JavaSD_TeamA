
<%-- 科目削除画面  --%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../base.jsp">
<c:param name="title" value="得点管理システム" />
<c:param name="body">

    <%-- 元のコンテナを活かし、Bootstrapのクラスで余白を追加 --%>
    <div class="SBJM006">

        <%-- ① 画面タイトル (イメージ図に合わせてボックススタイルに) --%>
        <div class="p-3 mb-4 bg-light border">
            <h2 class="p-2 mb-4 bg-body-secondary border fw-bold">科目情報削除</h2>
        </div>

        <%-- 削除処理を実行するためのフォーム --%>
        <form action="${pageContext.request.contextPath}/main/subject/SBJM006Execute" method="post">

            <%-- ② 確認メッセージ (上下に余白を追加) --%>
            <p class="my-4">
                「<c:out value="${subject.name}"/>(<c:out value="${subject.cd}"/>)」を削除してもよろしいですか。
            </p>

            <%-- 削除対象の科目コードをサーバーに送信するための隠しフィールド --%>
            <input type="hidden" name="cd" value="<c:out value='${subject.cd}'/>">

            <%-- ③ 削除ボタン (Bootstrapのdangerボタンに) --%>
            <input type="submit" value="削除" class="btn btn-danger">
        </form>

        <%-- ④ 戻るリンク (上のフォームとの間に余白を追加) --%>
        <div class="mt-3">
            <a href="${pageContext.request.contextPath}/main/subject/SBJM001">戻る</a>
        </div>

    </div>
</c:param>
</c:import>