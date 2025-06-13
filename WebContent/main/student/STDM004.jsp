<%-- 学生情報変更画面 --%>


<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../base.jsp">


<%-- ページタイトルの指定 --%>
<c:param name="title">得点管理システム</c:param>


 	<%-- ページ本文の内容 --%>
 	<c:param name="body">

 	<%-- 画面タイトルを表示 --%>
	<h2>学生情報変更</h2>


	<form action="${pageContext.request.contextPath}/main/student/STDM004" method="post">


	<%-- 入学年度の表示（読み取り） --%>
    <label>入学年度</label>
    <input type="text" value="${ent_year}" readonly>

    <%-- 学生番号の表示（読み取り） --%>
    <label>学生番号</label>
    <input type="text" value="${no}" readonly>

    <%-- 氏名の入力 --%>
    <label>氏名</label>
    <input type="text" name="name"  value="${name}">


     <%-- クラスの選択 --%>
     <%-- ログインユーザの学校コードに該当する「クラス番号テーブル」の「クラス番号」を表示 --%>
     <label>クラス</label><br>
     <select name="class_num" required>
       <option value="">----</option>

	       <c:forEach var="classNum" items="${classNum}">
	         <option value="${classNum.classNum}">${classNum.classNum}</option>
        </c:forEach>
     </select>


    <%-- 在学中の有無について判定チェック --%>
    <label>
        在学中<input type="checkbox" id="si_attend">
    </label>


    <!-- 学生変更完了画面に遷移する -->
    <button type="submit">変更</button>


    <%-- 学生一覧画面に遷移する --%>
	<a href="${pageContext.request.contextPath}/main/student/STDM001">戻る</a>






     </form>






	</c:param>
</c:import>