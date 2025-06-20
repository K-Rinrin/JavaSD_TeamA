
<%-- 科目削除完了画面  --%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../base.jsp">
<c:param name="title" value="得点管理システム" />
<c:param name="body">

    <%-- 元のコンテナを活かし、Bootstrapのクラスで余白を追加 --%>
    <div class="SBJM007">


            <h2 class="p-2 mb-4 bg-body-secondary border fw-bold">科目情報削除</h2>


        <%-- ② 完了メッセージ --%>
        <%-- 元の p.completion-message のクラスを、BootstrapのAlertクラスに置き換え --%>
        <p class="alert alert-success text-center" role="alert">
            削除が完了しました
        </p>

        <%-- ③ 科目一覧リンク (元のaタグを活かし、divで囲んで余白を追加) --%>
        <div class="mt-4">
            <a href="${pageContext.request.contextPath}/main/subject/SBJM001">科目一覧</a>
        </div>

    </div>
</c:param>
</c:import>