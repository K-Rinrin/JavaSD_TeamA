
<%-- 科目変更完了/エラー画面 --%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../base.jsp">
<c:param name="title" value="得点管理システム" />
<c:param name="body">

    <%-- 元のコンテナを活かし、Bootstrapのクラスで余白を追加 --%>
    <div class="SBJM005">

        <%-- ① 画面タイトル (元のスタイルをそのまま使用) --%>
        <h2 class="p-2 mb-4 bg-body-secondary border fw-bold">科目情報変更</h2>

        <%-- JSTLのchooseタグで、成功/エラーメッセージの表示を切り替え --%>
        <c:choose>
            <%-- 【エラー時】コントローラーから 'error' が渡された場合 --%>
            <c:when test="${not empty error}">
                <%-- pタグを使い、クラスを alert-danger (赤色) に変更 --%>
                <p class="alert alert-danger" role="alert">
                    <c:out value="${error}" />
                </p>
            </c:when>

            <%-- 【成功時】'error' がない場合 (デフォルト) --%>
            <c:otherwise>
                <%-- ② 元の完了メッセージのコードをそのまま使用 --%>
                <p class="alert alert-success" role="alert">
                    変更が完了しました
                </p>
            </c:otherwise>
        </c:choose>

        <%-- ③ 科目一覧へリンク (余白調整のためmt-4を追加) --%>
        <div class="mt-4">
             <a href="${pageContext.request.contextPath}/main/subject/SBJM001">科目一覧へ</a>
        </div>

    </div>
</c:param>
</c:import>