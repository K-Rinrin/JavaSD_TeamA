
<%-- 科目変更画面 (SBJM004) --%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../base.jsp">
<c:param name="title" value="得点管理システム" />
<c:param name="body">

<div class="SBJM004">

    <%-- ① 画面タイトル --%>
    <h2 class="h2">科目情報変更</h2>

    <%-- 変更フォーム --%>
    <form action="SubjectUpdateExecute.action" method="post">

        <%-- 科目コード（変更不可） --%>
        <div class="form-group">
            <%-- ② 科目コード ラベル --%>
            <label for="subject-cd">科目コード</label>

            <%-- ③ 科目コード表示テキスト --%>
            <input type="text" id="subject-cd" name="cd" class="form-control" value="${code}" readonly>
        </div>

        <%-- (追加) 対象科目削除済みエラーの表示エリア --%>
        <%-- コントローラから "errors" Map の "global" キーでメッセージが渡された場合に表示 --%>
        <c:if test="${not empty errors.global}">
            <div class="error-message">${errors.global}</div>
        </c:if>

        <%-- 科目名（変更可能） --%>
        <div class="form-group">
            <%-- ④ 科目名 ラベル --%>
            <label for="subject-name">科目名</label>

            <%-- 科目名未入力エラーの表示エリア --%>
            <%-- コントローラから "errors" Map の "name" キーでメッセージが渡された場合に表示 --%>
            <c:if test="${not empty errors.name}">
                <div class="error-message">${errors.name}</div>
            </c:if>

            <%-- ⑤ 科目名入力テキスト --%>
            <input type="text" id="subject-name" name="name" class="form-control" value="${name}" maxlength="80">
        </div>

        <%-- 操作ボタンエリア --%>
        <div class="actions">
            <%-- ⑥ 変更ボタン --%>
            <button type="submit" class="btn btn-primary">変更</button>

            <%-- ⑦ 戻るリンク --%>
            <a href="SubjectList.action">戻る</a>
        </div>
    </form>

</div>
</c:param>
</c:import>