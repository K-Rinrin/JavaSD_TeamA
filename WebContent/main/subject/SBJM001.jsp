
<%-- 科目管理一覧画面 --%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../base.jsp">
<c:param name="title" value="得点管理システム" />
<c:param name="body">

<div class="SBJM001">
<h2>科目管理</h2>
      <a href="subjectRegister.jsp">新規登録</a>

      <c:choose>
        <c:when test="${not empty subjectList}">
          <table border="1" cellpadding="8" cellspacing="0">
            <thead>
              <tr>
                <th>科目コード</th>
                <th>科目名</th>
                <th>編集</th>
                <th>削除</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="subject" items="${subjectList}">
                <tr>
                  <td>${subject.code}</td>
                  <td>${subject.name}</td>
                  <td><a href="subjectEdit.jsp?code=${subject.code}">変更</a></td>
                  <td><a href="subjectDelete.jsp?code=${subject.code}">削除</a></td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </c:when>
        <c:otherwise>
          <p>科目は登録されていません。</p>
        </c:otherwise>
      </c:choose>
    </div>
    ]]>










</div>
</c:param>
</c:import>