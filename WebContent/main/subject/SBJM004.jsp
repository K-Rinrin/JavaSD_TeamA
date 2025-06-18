
<%-- 科目変更画面 (SBJM004) --%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../base.jsp">
<c:param name="title" value="得点管理システム" />
<c:param name="body">

<div class="SBJM004">

    <%-- ① 画面タイトル --%>
    <h2>科目情報変更</h2>

    <%-- 変更処理を実行するためのフォーム --%>
    <form action="${pageContext.request.contextPath}/main/subject/SBJM004Execute" method="post">
        <%-- 科目コード表示エリア --%>
        <div class="form-group">
            <%-- ② 項目タイトル（科目コード） --%>
            <label for="subject-cd">科目コード</label>
            <%--
                ③ 科目コード (編集不可)
                readonly属性で編集を禁止します。
                更新対象を特定するため、name="cd"で値をサーバーに送信します。
            --%>
			<input type="text" id="subject-cd" name="cd" value="<c:out value='${subject.cd}'/>" readonly>
        </div>

        <%-- 科目名入力エリア --%>
        <div class="form-group">
            <%-- ④ 項目タイトル（科目名） --%>
            <label for="subject-name">科目名</label>
            <%--
                ⑤ 科目名入力テキスト
                初期値として選択された科目の名前を表示します。
                エラーで再表示された場合も入力内容が保持されます。
            --%>
            <input type="text" id="subject-name" name="name" value="<c:out value="${subject.name}"/>">

            <%-- バリデーションエラーメッセージの表示領域（例） --%>
            <c:if test="${not empty errors.name}">
                <div class="error-message"><c:out value="${errors.name}" /></div>
            </c:if>
        </div>

        <%-- ⑥ 変更ボタン --%>
        <input type="submit" value="変更">
    </form>

    <%-- ⑦ 戻るリンク --%>
    <a href="${pageContext.request.contextPath}/main/subject/SBJM001">戻る</a>

</div>
</c:param>
</c:import>
