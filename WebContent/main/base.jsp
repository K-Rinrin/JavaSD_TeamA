
<%-- ベース --%>


<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>

		<meta charset="UTF-8">
		<title>${ param.title }</title>

		<%-- メイン画面のcssを定義 --%>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mmnu.css">

	</head>
	<body>

	<c:import url="/main/header.jsp"/>

	<div class="page-container">
		<c:import url="/main/menu.jsp"/>
		<main class="main-content">
			${ param.body }
		</main>
	</div>

	<c:import url="/main/footer.jsp"/>

	</body>
</html>
