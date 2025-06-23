<%-- 科目管理一覧画面 (Bootstrap 5 使用) --%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../base.jsp">
<c:param name="title" value="得点管理システム" />
<c:param name="body">

<div class="container-fluid p-4">

    <%-- ① 画面タイトルと ② 新規登録リンク --%>
    <h2 class="p-2 mb-4 bg-body-secondary border fw-bold">科目管理</h2>
    <div class="text-end">
        <a href="${pageContext.request.contextPath}/main/subject/SBJM002">新規登録</a>
    </div>

    <%-- ③ 科目一覧リストのコンテナ --%>
    <div class="list-group list-group-flush">

        <%-- ヘッダー行 (④科目コード, ⑤科目名) --%>
        <div class="list-group-item fw-bold">
            <div class="row align-items-center">
                <%-- [修正] border-end を追加して右側に縦線を引く --%>
                <div class="col-2">科目　　コード  </div>


                <%-- [修正] スペーサー用の空カラムを追加して距離を確保 --%>
                <div class="col-4"></div>

                <%-- [修正] ps-3 を追加して縦線との間に余白を設ける --%>
                <div class="col ps-3">科目名</div>

                <div class="col-3"></div> <%-- 変更・削除リンク用のヘッダースペース --%>
            </div>
        </div>

        <%-- コントローラーから渡された科目リスト(subjects)をループ表示 --%>
        <c:forEach var="subject" items="${subjects}">
            <%-- 各科目のデータ行 --%>
            <div class="list-group-item">
                <div class="row align-items-center">
                    <%-- ⑥ 科目コード --%>
                    <%-- [修正] 縦線と揃えるため、データ行にも同様のクラスを追加 --%>
                    <div class="col-2 border-end">
                        <c:out value="${subject.cd}" />
                    </div>
                    <%-- ⑦ 科目名 --%>
                    <%-- [修正] ヘッダーと揃えるため、データ行にも同様のクラスを追加 --%>
                    <div class="col ps-3">
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
                 <%-- [修正] 空だった部分にメッセージを追加 --%>

            </div>
        </c:if>

    </div> <%-- .list-group .list-group-flush --%>

</div> <%-- .container-fluid --%>
</c:param>
</c:import>