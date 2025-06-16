
<%-- 科目削除画面 --%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../base.jsp">
<c:param name="title" value="得点管理システム" />
<c:param name="body">

<div class="SBJM006">


 <%-- ① 画面タイトル --%>
            <h2>科目情報削除</h2>

            <%-- 削除処理を実行するためのフォーム --%>
            <form action="SubjectDeleteExecute.action" method="post">

                <%-- ② 確認メッセージ --%>
                <%-- コントローラーから渡される科目オブジェクト(subject)の情報を表示 --%>
                <p>
                    「<c:out value="${subject.name}"/>(<c:out value="${subject.cd}"/>)」を削除してもよろしいですか。
                </p>

                <%-- 削除対象の科目コードをサーバーに送信するための隠しフィールド --%>
                <input type="hidden" name="cd" value="${subject.cd}">

                <%-- ③ 削除ボタン --%>
                <input type="submit" value="削除">
            </form>

            <%-- ④ 戻るリンク --%>
            <%-- 科目管理一覧画面へ遷移する想定 --%>
            <a href="SubjectList.action">戻る</a>







</div>
</c:param>
</c:import>