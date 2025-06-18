<%-- 科目管理一覧画面 (Bootstrap 5 使用) --%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../base.jsp">
<c:param name="title" value="得点管理システム" />
<c:param name="body">

<%--
  このJSPはBootstrap 5のクラスのみを使用してスタイリングしています。
  - d-flex, justify-content-between: ヘッダーのレイアウト
  - border-bottom, pb-*, mb-*: 区切り線と余白
  - btn, btn-primary: ボタン
  - list-group, list-group-item: 一覧表示のコンテナと各行
  - row, col-*: グリッドシステムによるカラムレイアウト
  - bg-light, fw-bold: ヘッダーの背景色と太字
  - text-end: テキストの右寄せ
--%>

<div class="SBJM001"> <%-- 画面全体のコンテナと余白 --%>

    <%-- 画面上部のヘッダー部分 (①タイトルと②新規登録リンク) --%>
    <div class="d-flex justify-content-between align-items-center pb-2 mb-3 border-bottom">
        <h2 class="p-2 mb-4 bg-body-secondary border fw-bold">科目管理</h2>
        <a href="${pageContext.request.contextPath}/main/subject/SBJM002" class="btn btn-primary">新規登録</a>
    </div>

    <%-- ③ 科目一覧リスト --%>
    <div class="list-group">

        <%-- ヘッダー行 (④科目コード, ⑤科目名) --%>
        <div class="list-group-item bg-light fw-bold">
            <div class="row align-items-center">
                <div class="col-2">科目コード</div>
                <div class="col">科目名</div>
                <div class="col-3 text-end"></div> <%-- 変更・削除リンク用のヘッダースペース --%>
            </div>
        </div>

        <%-- コントローラーから渡された科目リスト(subjects)をループ表示 --%>
        <c:forEach var="subject" items="${subjects}">
            <%-- 各科目のデータ行 (⑥, ⑦, ⑧, ⑨) --%>
            <div class="list-group-item">
                <div class="row align-items-center">
                    <%-- ⑥ 科目情報（科目コード） --%>
                    <div class="col-2">
                        <c:out value="${subject.cd}" />
                    </div>
                    <%-- ⑦ 科目情報（科目名） --%>
                    <div class="col">
                        <c:out value="${subject.name}" />
                    </div>
                    <%-- ⑧,⑨ 変更・削除リンク --%>
                    <div class="col-3 text-end">
                        <a href="${pageContext.request.contextPath}/main/subject/SBJM004?cd=${subject.cd}" class="btn btn-link btn-sm text-decoration-none">変更</a>
                        <a href="${pageContext.request.contextPath}/main/subject/SBJM006?cd=${subject.cd}" class="btn btn-link btn-sm text-decoration-none text-danger">削除</a>
                    </div>
                </div>
            </div>
        </c:forEach>

        <%-- 科目が一件も登録されていない場合の表示 --%>
        <c:if test="${empty subjects}">
            <div class="list-group-item text-center text-muted">
                科目が登録されていません。
            </div>
        </c:if>

    </div> <%-- .list-group --%>

</div> <%-- .container-fluid --%>
</c:param>
</c:import>