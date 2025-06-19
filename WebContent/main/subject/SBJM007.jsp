
<%-- 科目削除完了画面  --%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../base.jsp">
<c:param name="title" value="得点管理システム" />
<c:param name="body">

    <%-- 元のコンテナを活かし、Bootstrapのクラスで余白を追加 --%>
    <div class="SBJM007">

        <%-- ① 画面タイトル (元のh2を活かし、divで囲んで装飾) --%>
        <div class="p-3 mb-4 bg-light border">
            <h2 class="p-2 mb-4 bg-body-secondary border fw-bold">科目情報削除</h2>
        </div>

        <%-- ② 完了メッセージ --%>
        <%-- 元の p.completion-message のクラスを、BootstrapのAlertクラスに置き換え --%>
        <p class="alert alert-success" role="alert">
            削除が完了しました
        </p>

        <%-- ③ 科目一覧リンク (元のaタグを活かし、divで囲んで余白を追加) --%>
        <div class="mt-4">
            <a href="${pageContext.request.contextPath}/main/subject/SBJM001">科目一覧</a>
        </div>

    </div>
</c:param>
</c:import>