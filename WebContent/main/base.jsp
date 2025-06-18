
<%-- ベース --%>


<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>

		<meta charset="UTF-8">
		<title>${ param.title }</title>


		<%-- bootstrapを読み込む --%>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
			rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
			crossorigin="anonymous">

		<%-- メイン画面のcssを定義 --%>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mmnu.css">


	</head>
	<body>

		<div class="container">
			<c:import url="/main/header.jsp"/>

			<div class="page-container">
				<c:import url="/main/menu.jsp"/>
					<main class="main-content">
						${ param.body }
					</main>
			</div>

			<c:import url="/main/footer.jsp"/>
		</div>

	</body>
</html>
