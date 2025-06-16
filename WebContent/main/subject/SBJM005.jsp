<%-- 科目変更完了画面 --%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../base.jsp">
<c:param name="title" value="得点管理システム" />
<c:param name="body">

<div class="SBJM005">


 <%-- ① 画面タイトル --%>
            <h2 class="h3 mb-3 fw-normal bg-light py-2 px-4">科目情報変更</h2>

            <%-- ② 完了メッセージ --%>
            <p class="alert alert-success" role="alert">
                変更が完了しました
            </p>

            <%-- ③ 科目一覧へリンク --%>
            <a href="SubjectList.action">科目一覧へ</a>

</div>
</c:param>
</c:import>
