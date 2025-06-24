<%-- 科目情報登録画面 (Bootstrap 5 使用) - 縦並びレイアウト --%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../base.jsp">
<c:param name="title" value="得点管理システム" />
<c:param name="body">

	<div class="container-fluid p-4">

	    <h2 class="p-2 mb-4 bg-body-secondary border fw-bold">科目情報登録</h2>


	    <form action="${pageContext.request.contextPath}/main/subject/SBJM002Execute" method="post">



	        <%-- 科目コード入力欄 --%>
	        <div class="mb-3">
	            <label for="subject-cd" class="form-label">科目コード</label>
	            <input type="text" class="form-control" id="subject-cd" name="cd" value="${cd}" placeholder="科目コードを入力してください" required>
		            <%-- エラーメッセージを表示 --%>
		            <c:if test="${not empty errors.cd}">
		               <p class="text-warning mt-1">${errors.cd}</p>
		            </c:if>
	        </div>



	        <%-- 科目名入力欄 --%>
	        <div class="mb-3">
	            <label for="subject-name" class="form-label">科目名</label>
	            <input type="text" class="form-control" id="subject-name" name="name" placeholder="科目名を入力してください" required>
	        </div>


			<%-- 登録ボタン --%>
	        <div class="mt-4">
	            <button type="submit" class="btn btn-primary">登録</button>
	            <div class="mt-2">
	                <a href="${pageContext.request.contextPath}/main/subject/SBJM001">戻る</a>
	            </div>
	        </div>


	    </form>
	</div>

</c:param>
</c:import>