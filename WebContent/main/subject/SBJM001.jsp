<%-- 科目管理一覧画面 (Bootstrap 5 使用) --%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../base.jsp">
<c:param name="title" value="得点管理システム" />
<c:param name="body">

<div class="container-fluid p-4">

    <%-- ① 画面タイトルと ② 新規登録リンク --%>

        <h2 class="p-2 mb-4 bg-body-secondary border fw-bold">科目管理</h2>
        <%-- [修正] ② 画像に合わせてボタンではなく通常のリンクにするため、class="btn btn-primary" を削除 --%>
        <div class="text-end">
        <a href="${pageContext.request.contextPath}/main/subject/SBJM002">新規登録</a>
        </div>




    <%-- ③ 科目一覧リストのコンテナ --%>
  <%-- [修正] list-group-flushクラスを追加して、左右の枠線を削除し横線のみ表示する --%>
<div class="list-group list-group-flush">

    <%-- ヘッダー行 (④科目コード, ⑤科目名) --%>
    <%-- この部分は画像の見た目と一致しているため変更しません --%>
    <div class="list-group-item  fw-bold">
        <div class="row align-items-center">
            <div class="col-2">科目コード</div>
            <div class="col">科目名</div>
            <div class="col-3"></div> <%-- 変更・削除リンク用のヘッダースペース --%>
        </div>
    </div>

    <%-- コントローラーから渡された科目リスト(subjects)をループ表示 --%>
    <c:forEach var="subject" items="${subjects}">
        <%-- 各科目のデータ行 --%>
        <div class="list-group-item">
            <div class="row align-items-center">
                <%-- ⑥ 科目コード --%>
                <div class="col-2">
                    <c:out value="${subject.cd}" />
                </div>
                <%-- ⑦ 科目名 --%>
                <div class="col">
                    <c:out value="${subject.name}" />
                </div>
                <%-- ⑧ 変更, ⑨ 削除リンク --%>
                <div class="col-3 text-end">
                    <a href="${pageContext.request.contextPath}/main/subject/SBJM004?cd=${subject.cd}" class="btn btn-link btn-sm text-decoration-none me-3">変更</a>
                    <a href="${pageContext.request.contextPath}/main/subject/SBJM006?cd=${subject.cd}" class="btn btn-link btn-sm text-decoration-none">削除</a>
                </div>
            </div>
        </div>
    </c:forEach>

    <%-- 科目が一件も登録されていない場合の表示 --%>
    <c:if test="${empty subjects}">
        <div class="list-group-item text-center text-muted">

        </div>
    </c:if>

</div> <%-- .list-group .list-group-flush --%>

</div> <%-- .container-fluid --%>
</c:param>
</c:import>