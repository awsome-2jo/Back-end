<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
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
<link rel="stylesheet" href="${ root }/assets/css/common.css">
<link rel="stylesheet" href="${ root }/assets/css/notice/notice.css">
  </head>
  <body>
    <%@ include file="../include/head.jsp" %>
    <%@ include file="../include/nav.jsp" %>
    <main class="container">
      <div>
        <div class="notice-title">
          <strong>공지사항</strong>
          <c:if test="${ loginUser.id eq 'admin'}">
	          <a class="btn-add" href="modify">추가</a>
          </c:if>
        </div>
        <div class="notice-list" style="margin-top: 10px">
          <table class="notice-post">
            <thead>
              <tr>
                <th style="width: 10%">번호</th>
                <th style="width: 60%">제목</th>
                <th style="width: 20%">작성일</th>
                <th style="width: 10%">조회수</th>
              </tr>
            </thead>
            <tbody>
            
            <c:if test="${ !empty noticelist }">
	            <c:forEach var="notice" items="${ noticelist }">
	              <tr onClick="location.href = '${root}/notice/detail?no=${notice.no}'">
	                <td>${ notice.no }</td>
	                <td>${ notice.title }</td>
	                <td>${ fn:split(notice.reg_date, ' ')[0] }</td>
	                <td>${ notice.hit }</td>
	              </tr>            	
	            </c:forEach>
            </c:if>
            
            <c:if test="${ empty noticelist }">
            <tr>
	            <td colspan="4">
	           	올라온 게시물이 없습니다.
	            </td>
            </tr>
            </c:if>
            
            </tbody>
          </table>
        </div>
        <div class="search">
          <input type="text" title="검색어 입력" class="search-text" value="" />
          <button class="search-btn">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
              <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
            </svg>
          </button>
        </div>
      </div>
    </main>
    <%@ include file="../include/foot.jsp" %>
  </body>
</html>
