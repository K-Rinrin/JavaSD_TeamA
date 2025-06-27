<%-- 成績管理一覧画面 --%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../base.jsp">
<c:param name="title" value="得点管理システム" />
<c:param name="body">

<div class="container">
	<h2 class="p-2 mb-4 bg-body-secondary border fw-bold">成績管理</h2>



	<div class="p-3 mb-4 rounded border">
	<form action="${pageContext.request.contextPath}/main/grade/GRMU001" method="get">
		<div class="row g-3 align-items-end">
			<%-- 入学年度セレクトボックス --%>
			<div class="col-md">
				<label for="f1_select" class="form-label">入学年度</label>
				<select name="f1" id="f1_select" class="form-select">
					<option value="">--------</option>
					<c:forEach var="year" items="${student}">
						<option value="${year}" <c:if test="${year == f1}">selected</c:if>>
						${year}
						</option>
					</c:forEach>
				</select>
			</div>



			<%-- クラスセレクトボックス --%>
			<div class="col-md">
				<label for="f2_select" class="form-label">クラス</label>
				<select name="f2" id="f2_select" class="form-select">
					<option value="">--------</option>
					<c:forEach var="classNumStr" items="${classNums}">
						<option value="${classNumStr}" <c:if test="${classNumStr == f2}">selected</c:if>>
						${classNumStr}
						</option>
					</c:forEach>
				</select>
			</div>



			<%-- 科目セレクトボックス --%>
			<div class="col-md">
				<label for="f3_select" class="form-label">科目</label>
				<select name="f3" id="f3_select" class="form-select">
					<option value="">--------</option>
					<c:forEach var="subject" items="${subjects}">
						<option value="${subject.cd}" <c:if test="${subject.cd == f3}">selected</c:if>>
						${subject.name}
						</option>
					</c:forEach>
				</select>
			</div>



			<%-- 回数セレクトボックス --%>
			<div class="col-md">
				<label for="f4_select" class="form-label">回数</label>
				<select name="f4" id="f4_select" class="form-select">
					<option value="">--------</option>
					<option value="1" <c:if test="${f4 == '1'}">selected</c:if>>1</option>
					<option value="2" <c:if test="${f4 == '2'}">selected</c:if>>2</option>
				</select>
			</div>


			<div class="col-md-auto">
				<button type="submit" name="search" value="true" class="btn btn-secondary">検索</button>
			</div>
		</div>
	</form>
	</div>



	<%-- 全体エラーメッセージ --%>
	<c:if test="${not empty errorMsg}">
		<p class="text-warning mt-1">${errorMsg}</p>
	</c:if>

	<%-- 検索結果・点数登録 --%>
	<c:if test="${not empty scorelist}">
		<p class="mt-4 mb-3 ">科目：${subjectName}(${f4}回)</p>






	<form action="${pageContext.request.contextPath}/main/grade/GRMU001Execute" method="post">


		<input type="hidden" name="f1" value="${f1}">
		<input type="hidden" name="f2" value="${f2}">
		<input type="hidden" name="f3" value="${f3}">
		<input type="hidden" name="f4" value="${f4}">


		<table class="table aligin-middle">
		<thead class="table-light">
			<tr>
				<th>入学年度</th>
				<th>クラス</th>
				<th>学生番号</th>
				<th>氏名</th>
				<th>得点</th>
				<th>削除</th>
			</tr>
		</thead>
		<tbody>


		<c:forEach var="score" items="${scorelist}">
			<c:set var="pointKey" value="point_${score.student.no}" />
			<c:set var="checkKey" value="check_${score.student.no}" />

			<tr>
				<td>${score.student.entYear}</td>
				<td>${score.student.classNum}</td>
				<td>${score.student.no}</td>
				<td>${score.student.name}</td>
				<td>
				  <c:set var="pointValue">
					  <c:choose>
						  <c:when test="${not empty postedValues[pointKey]}">
							  <c:out value="${postedValues[pointKey][0]}" />
						  </c:when>
						  <c:otherwise>
							  <c:out value="${score.point}" default=""/>
						  </c:otherwise>
					  </c:choose>
				  </c:set>

				  <input type="text" name="point_${score.student.no}"
				  	value="${pointValue}" class="form-control" style="width: 120px;" />


				  <c:if test="${not empty inputErrors[score.student.no]}">
				    <p class="text-warning small mt-1">${inputErrors[score.student.no]}</p>
				</c:if>
				</td>
				<td>
					<input type="checkbox" name="check_${score.student.no}" value="on"
						<c:if test="${not empty postedValues[checkKey]}">checked</c:if> >
				</td>
			</tr>

		</c:forEach>
		</tbody>
		</table>


		<button type="submit" name="action" value="end" class="btn btn-secondary mt-3">登録して終了</button>
		<button type="submit" name="action" value="again" class="btn btn-secondary mt-3">登録して再度入力</button>
	</form>
	</c:if>
</div>


</c:param>
</c:import>