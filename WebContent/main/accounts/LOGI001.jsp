
<%-- ログイン画面 --%>


<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../header.jsp"/>




<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>得点管理システム</title>

  <meta name="viewport" content="width=device-width, initial-scale=1">

  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>



<body class="text-center">


<main class="form-signin w-100 m-auto" style="max-width: 330px; padding: 1rem;">
  <form action="${pageContext.request.contextPath}/main/accounts/LOGI001exe" method="get">


    <%-- 画面タイトルの表示 --%>
    <h2 class="h3 mb-3 fw-normal">ログイン</h2>

    <%-- ログインID（最大20文字・必須） --%>
    <div class="form-floating mb-2">
      <input type="text" class="form-control" id="id" name="id" value="${id}" maxlength="20" required>
      <label for="id">ID</label>
    </div>


    <%-- パスワード（最大20文字・必須） --%>
    <div class="form-floating mb-2">
      <input type="password" class="form-control" id="password" name="password" value="${password}" maxlength="20" required>
      <label for="password">パスワード</label>
    </div>


    <!-- パスワード表示チェックボックス -->
    <div class="checkbox mb-3 text-start">
      <label>
        <input type="checkbox" id="chk_d_ps"> パスワードを表示
      </label>
    </div>


    <!-- ログインボタン -->
    <button class="w-100 btn btn-lg btn-primary" type="submit">ログイン</button>

  </form>
</main>



	<!-- パスワード表示切替JS -->
	<script>
	  document.getElementById("chk_d_ps").addEventListener("change", function () {
	    const pwd = document.getElementById("password");
	    pwd.type = this.checked ? "text" : "password";
	  });
	</script>


</body>
</html>



<c:import url="../footer.jsp"/>
