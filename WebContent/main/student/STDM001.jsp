
<%-- 学生管理一覧画面 --%>


<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../base.jsp">


    <%-- ページタイトルの指定 --%>
    <c:param name="title">得点管理システム</c:param>


    <%-- ページ本文の内容 --%>
    <c:param name="body">


    <div class="container mt-1">
        <%-- 画面タイトルと検索フォームをカードで囲む --%>
        <div class="card border-0">
            <div class="card-header bg-light border-0">
                <h2 class="h5 mb-0">学生管理</h2>
            </div>


            <div class="card-body">
                <div class="text-end mb-3">
                    <%-- 学生登録画面に遷移する --%>
                    <a href="${pageContext.request.contextPath}/main/student/STDM002">新規登録</a>
                </div>


                <div class="border p-2 rounded mb-4">
                    <form action="${pageContext.request.contextPath}/main/student/STDM001" method="get" class="d-flex align-items-end flex-wrap gap-3">
                        <input type="hidden" name="filter" value="true">



                        <%-- 入学年度の選択 --%>
                        <div class="position-relative">
                            <label for="entYear-select" class="form-label">入学年度</label>
                            <%-- エラーがあればis-invalidクラスを付与 --%>
                            <select name="entYear" id="entYear-select" class="form-select" style="width: 180px;">
                                <option value="">----</option>
                                <c:forEach var="ent" items="${allent}">
                                    <%-- 検索後の値保持 --%>
                                    <option value="${ent}" ${ent == entYear ? 'selected' : ''}>${ent}</option>
                                </c:forEach>
                            </select>
                            <%-- バリデーションエラーメッセージの表示 --%>
                            <c:if test="${not empty errors.entYear}">
                                <p class="text-warning mt-1">
                                    ${errors.entYear}
                                </p>
                            </c:if>
                        </div>



                        <%-- クラスの選択 --%>
                        <div>
                            <label for="classNum-select" class="form-label">クラス</label>
                            <select name="classNum" id="classNum-select" class="form-select" style="width: 180px;">
                                <option value="">----</option>
                                <c:forEach var="cls" items="${allclass}">
                                    <%-- 検索後の値保持 --%>
                                    <option value="${cls}" ${cls == classNum ? 'selected' : ''}>${cls}</option>
                                </c:forEach>
                            </select>
                        </div>



                        <%-- 在学中のON/OFF --%>
                        <div class="form-check mb-2">
                            <%-- 検索後の値保持 --%>
                            <input type="checkbox" name="isAttend" value="true" class="form-check-input" id="is-attend-check" ${isAttend ? 'checked' : ''}>
                            <label class="form-check-label" for="is-attend-check">在学中</label>
                        </div>



                        <%-- 絞り込みボタン --%>
                        <div>
                            <button type="submit" class="btn btn-secondary">絞込み</button>
                        </div>

                    </form>
                </div>
            </div>
        </div>



        <%-- 結果表示エリア --%>
        <div class="mt-1">
            <c:choose>
                <%-- バリデーションエラーがある場合 --%>
                <c:when test="${not empty errors}">
                </c:when>


                <%-- 検索結果が1件以上ある場合 --%>
                <c:when test="${not empty student}">
                    <div id="検索結果">検索結果: ${件数}件</div>
                    <table class="table mt-1">
                        <thead>
                            <tr>
                                <th>入学年度</th>
                                <th>学生番号</th>
                                <th>氏名</th>
                                <th>クラス</th>
                                <th>在学中</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="stu" items="${student}">
                                <tr>
                                    <td>${stu.entYear}</td>
                                    <td>${stu.no}</td>
                                    <td>${stu.name}</td>
                                    <td>${stu.classNum}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${stu.attend}">
                                                <span style="font-size: 1.5rem; color: green; font-weight: bold;">○</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span style="color: #999;">×</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td><a href="${pageContext.request.contextPath}/main/student/studentupdate?no=${stu.no}">変更</a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>


                <%-- エラーがなく、結果が0件の場合 --%>
                <c:otherwise>
                    <%-- 「絞り込み」ボタンが押された後でのみ「該当なし」メッセージを表示 --%>
                    <c:if test="${filtered}">
                        <p>対象の学生情報が存在しませんでした。</p>
                    </c:if>
                </c:otherwise>
            </c:choose>



            <%-- データベースエラーなど、予期せぬエラーメッセージの表示 --%>
            <c:if test="${not empty errorMessage}">
                <p class="text-danger">${errorMessage}</p>
            </c:if>



        </div>
    </div>
    </c:param>
</c:import>