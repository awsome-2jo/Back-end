<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>구해줘 홈즈</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx"
      crossorigin="anonymous"
    />
    <link rel="stylesheet" href="${ pageContext.request.contextPath }/assets/css/common.css" />
    <link rel="stylesheet" href="${ pageContext.request.contextPath }/assets/css/noticeDetail.css" />
  </head>
  <body>
    <%@ include file="../include/head.jsp" %>
    <%@ include file="../include/nav.jsp" %>
    <main class="container">
      <div class="notice-title">
        <strong>공지사항 작성</strong>
      </div>
      <form class="notice-container notice-form" method="post" action="${ root }/notice">
      <c:if test="${ empty notice }">
	      <input type="hidden" name="action" value="regist">
      </c:if>
      <c:if test="${ !empty notice }">
	      <input type="hidden" name="action" value="modify">
	      <input type="hidden" name="no" value="${ notice.no }">
      </c:if>
        <div class="notice-head">
          <label for="title">제목 </label>
          <c:if test="${ empty notice }">
	        <input id="title" name="title" type="text">
	      </c:if>
	      <c:if test="${ !empty notice }">
	        <input id="title" name="title" type="text" value="${notice.title}">
	      </c:if>
        </div>
        <hr>
        <div class="notice-body">
          <c:if test="${ empty notice }">
          <textarea name="content" id="content" rows="10"></textarea>
	      </c:if>
	      <c:if test="${ !empty notice }">
          <textarea name="content" id="content" rows="10"}>${ notice.content }</textarea>
	      </c:if>
        </div>
        <button class="btn-list">완료</button>
      </form>
    </main>
    <%@ include file="../include/foot.jsp" %>
  </body>
</html>
