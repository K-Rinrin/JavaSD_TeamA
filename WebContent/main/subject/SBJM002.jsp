
 <%-- 科目登録画面 (SB-JM002) --%>


<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- base.jspをテンプレートとして読み込む --%>
<c:import url="../base.jsp">
    <c:param name="title" value="得点管理システム" />
    <c:param name="body">

        <%-- この画面専用のコンテナ --%>
        <div class="SBJM002">

            <%-- ① 画面タイトル --%>
            <h2>科目情報登録</h2>

            <%-- 登録処理を実行するためのフォーム --%>
            <form action="SBJM002Execute" method="post">
                <%-- 科目コード入力エリア --%>
                <div class="form-group">
                    <%-- ② 科目コード ラベル --%>
                    <label for="subject-cd">科目コード</label>
                    <%-- ③ 科目コード入力テキスト --%>
					<input type="text" id="subject-cd" name="cd" maxlength="3" required
					       value="<c:out value='${cd}'/>" placeholder="科目コードを入力してください">
                </div>

                <%-- 科目名入力エリア --%>
                <div class="form-group">
                    <%-- ④ 科目名 ラベル --%>
                    <label for="subject-name">科目名</label>
                    <%-- ⑤ 科目名入力テキスト --%>
                    <input type="text" id="subject-name" name="name" required
                    	value="<c:out value="${name}"/>" placeholder="科目名を入力してください">
                </div>

                <%-- ⑥ 登録ボタン --%>
                <input type="submit" value="登録">
            </form>

            <%-- ⑦ 戻るリンク --%>
            <a href="${pageContext.request.contextPath}/main/subject/SBJM001">戻る</a>

        </div>
    </c:param>
</c:import>