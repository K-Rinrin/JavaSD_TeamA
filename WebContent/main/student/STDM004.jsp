<%-- 学生情報変更画面 --%>


<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../base.jsp">


<%-- ページタイトルの指定 --%>
<c:param name="title">得点管理システム</c:param>


 	<%-- ページ本文の内容 --%>
 	<c:param name="body">
	<div class="container mt-4" style="max-width: 700px;">
		<div class="card border-0">
			<div class="card-header bg-light border-0">
				<%-- 画面タイトルを表示 --%>
				<h2 class="h5 mb-0">学生情報変更</h2>
			</div>


			<div class="card-body">
				<form action="${pageContext.request.contextPath}/main/student/STDM004" method="post">

					<%-- 入学年度の表示（読み取り） --%>
					<div class="mb-3">
						<label class="form-label">入学年度</label>
						<input type="text" value="${ent_year}" readonly class="form-control">
					</div>

					<%-- 学生番号の表示（読み取り） --%>
					<div class="mb-3">
						<label class="form-label">学生番号</label>
						<input type="text" value="${no}" readonly class="form-control">
					</div>

					<%-- 氏名の入力 --%>
					<div class="mb-3">
						<label class="form-label">氏名</label>
						<input type="text" name="name"  value="${name}" required class="form-control">
					</div>

					<%-- クラスの選択 --%>
					<%-- ログインユーザの学校コードに該当する「クラス番号テーブル」の「クラス番号」を表示 --%>
					<div class="mb-3">
						<label class="form-label">クラス</label>
						<select name="class_num" class="form-select" required>
						<option value="">----</option>
							<c:forEach var="classNum" items="${classNum}">
								<%-- DBから取得した現在のクラスが選択された状態にする --%>
								<option value="${classNum.classNum}" ${classNum.classNum == student.classNum ? 'selected' : ''}>${classNum.classNum}</option>
							</c:forEach>
						</select>
					</div>

					<%-- 在学中の有無について判定チェック --%>
					<div class="form-check mb-4">
						<input type="checkbox" name="is_attend" value="true" class="form-check-input" id="is_attend_check" ${student.isAttend ? 'checked' : ''}>
						<label class="form-check-label" for="is_attend_check">在学中</label>
					</div>

					<!-- 学生変更完了画面に遷移する -->
					<button type="submit" class="btn btn-primary">変更</button>
				</form>

				<div class="mt-3">
					<%-- 学生一覧画面に遷移する --%>
					<a href="${pageContext.request.contextPath}/main/student/STDM001">戻る</a>
				</div>
			</div>
		</div>
	</div>
	</c:param>
</c:import>