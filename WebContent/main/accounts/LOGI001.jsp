<%-- ログイン画面 --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../base.jsp">

    <%-- ページタイトルをbase.jspに渡す --%>
    <c:param name="title"> 得点管理システム</c:param>

    <%-- ページ本文の内容 --%>
    <c:param name="body">


	    <div class="container mt-1">
            <%-- justify-content-center を追加して、カードを水平中央に配置 --%>
	        <div class="col-md-8 col-lg-6 mx-auto">

	            <div class="card shadow-sm">

	                <%-- タイトルの表示 --%>
	                <div class="card-header text-center bg-light">
	                    <h2 class="h3 mb-0">ログイン</h2>
	                </div>

	                <div class="card-body p-4">
	                    <form action="${pageContext.request.contextPath}/main/accounts/loginExecute" method="post">

	                        <%-- システムエラーが発生した時のエラーメッセージを表示 --%>
	                        <c:if test="${not empty error}">

	                                <c:out value="${error}" />

	                        </c:if>

	                        <%-- ログインIDを入力してもらう（最大20文字・必須） --%>
	                        <div class="form-floating mb-3">
	                            <input type="text" class="form-control" id="id" name="id" placeholder="ID" value="${id}" maxlength="20" required>
	                            <label for="id">ID</label>
	                        </div>

	                        <%-- パスワードを入力してもらう（最大20文字・必須） --%>
	                        <div class="form-floating mb-3">
	                            <input type="password" class="form-control" id="password" name="password" placeholder="パスワード" maxlength="20" required>
	                            <label for="password">パスワード</label>
	                        </div>

	                        <%-- パスワード表示チェックボックスを設定 --%>
	                        <div class="d-flex justify-content-center my-3">
    							<div class="form-check">
       					 			<input class="form-check-input" type="checkbox" id="show-password-check">
        							<label class="form-check-label" for="show-password-check">
            							パスワードを表示
        							</label>
    							</div>
							</div>

							<%-- ログインボタンを表示する --%>
	                        <div class="text-center">
  								<button class="w-50 btn btn-lg btn-primary" type="submit">ログイン</button>
							</div>
	                    </form>
	                </div>
	            </div>
	        </div>
	    </div>

    <%-- パスワード表示切替のJSを定義 --%>
    <script>
        // DOMが読み込まれてからスクリプトを実行
        document.addEventListener('DOMContentLoaded', (event) => {
            const passCheckbox = document.getElementById('show-password-check');
            if (passCheckbox) {
                passCheckbox.addEventListener('change', function () {
                    const passwordField = document.getElementById('password');
                    // チェックボックスがチェックされていれば typeを'text'に、そうでなければ'password'に
                    passwordField.type = this.checked ? 'text' : 'password';
                });
            }
        });
    </script>

    </c:param>
</c:import>