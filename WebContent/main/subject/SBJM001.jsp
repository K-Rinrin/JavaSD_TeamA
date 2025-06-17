
<%-- 科目管理一覧画面 --%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../base.jsp">
<c:param name="title" value="得点管理システム" />
<c:param name="body">

<div class="SBJM001">

    <%-- 画面上部のヘッダー部分 (タイトルと新規登録リンク) --%>
    <div class="main-header">
        <%-- ① 画面タイトル --%>
        <h2>科目管理</h2>
        <%-- ② 新規登録リンク --%>
        <a href="${pageContext.request.contextPath}/main/subject/SBJM002">新規登録</a>
    </div>

    <%-- ③ 科目一覧テーブル --%>
    <table class="subject-table">
        <thead>
            <tr>
                <%-- ④ ヘッダ（科目コード） --%>
                <th>科目コード</th>
                <%-- ⑤ ヘッダ（科目名） --%>
                <th>科目名</th>
                <%-- 変更・削除リンク用のヘッダ --%>
                <th colspan="2"></th>
            </tr>
        </thead>
        <tbody>
            <%-- コントローラーから渡された科目リスト(subjects)をループ表示 --%>
            <c:forEach var="subject" items="${subjects}">
                <tr>
                    <%-- ⑥ 科目情報（科目コード） --%>
                    <td><c:out value="${subject.cd}" /></td>
                    <%-- ⑦ 科目情報（科目名） --%>
                    <td><c:out value="${subject.name}" /></td>
                    <%-- ⑧ 科目情報変更リンク --%>
                    <td>
                        <a href="${pageContext.request.contextPath}/main/subject/SBJM004?cd=${subject.cd}">変更</a>
                    </td>
                    <%-- ⑨ 科目情報削除リンク --%>
                    <td>
                        <a href="${pageContext.request.contextPath}/main/subject/SBJM006?cd=${subject.cd}">削除</a>
                    </td>
                </tr>
            </c:forEach>

            <%-- 科目が一件も登録されていない場合の表示 --%>
            <c:if test="${empty subjects}">
                <tr>
                    <td colspan="4" class="no-data">科目が登録されていません。</td>
                </tr>
            </c:if>
        </tbody>
    </table>

</div>
</c:param>
</c:import>