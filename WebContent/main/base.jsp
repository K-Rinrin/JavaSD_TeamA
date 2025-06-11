
<%-- ベース --%>


<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>

		<meta charset="UTF-8">
		<title>${ param.title }</title>

	</head>
	<body>

	<c:import url="/main/header.jsp"/>
	<c:import url="/main/menu.jsp"/>
	${ param.body }
	<c:import url="/main/footer.jsp"/>

	</body>
</html>
