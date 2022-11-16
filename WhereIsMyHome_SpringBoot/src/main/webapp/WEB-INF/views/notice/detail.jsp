<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<link rel="stylesheet" href="${ root }/assets/css/notice/noticeDetail.css">
  </head>
  <body>
    <%@ include file="../include/head.jsp" %>
    <%@ include file="../include/nav.jsp" %>
    <main class="container">
      <nav class="nav-notice">
        <!-- <a id="next-notice">
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chevron-up" viewBox="0 0 16 16">
		  <path fill-rule="evenodd" d="M7.646 4.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1-.708.708L8 5.707l-5.646 5.647a.5.5 0 0 1-.708-.708l6-6z"/>
		</svg>윗글</a>
        <a id="pre-notice">
   			<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chevron-down" viewBox="0 0 16 16">
		  	<path fill-rule="evenodd" d="M1.646 4.646a.5.5 0 0 1 .708 0L8 10.293l5.646-5.647a.5.5 0 0 1 .708.708l-6 6a.5.5 0 0 1-.708 0l-6-6a.5.5 0 0 1 0-.708z"/>
			</svg>아랫글</a> -->
      </nav>
      <section class="notice-container">
        <div class="notice-head">
          <h3>${ notice.title }</h3>
          <div>
            <div>
              <span>관리자</span> |
              <span>${ notice.reg_date }</span>
            </div>
            <div>
              <span>
              <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-eye" viewBox="0 0 16 16">
  <path d="M16 8s-3-5.5-8-5.5S0 8 0 8s3 5.5 8 5.5S16 8 16 8zM1.173 8a13.133 13.133 0 0 1 1.66-2.043C4.12 4.668 5.88 3.5 8 3.5c2.12 0 3.879 1.168 5.168 2.457A13.133 13.133 0 0 1 14.828 8c-.058.087-.122.183-.195.288-.335.48-.83 1.12-1.465 1.755C11.879 11.332 10.119 12.5 8 12.5c-2.12 0-3.879-1.168-5.168-2.457A13.134 13.134 0 0 1 1.172 8z"/>
  <path d="M8 5.5a2.5 2.5 0 1 0 0 5 2.5 2.5 0 0 0 0-5zM4.5 8a3.5 3.5 0 1 1 7 0 3.5 3.5 0 0 1-7 0z"/>
</svg>
              ${ notice.hit }</span>
            </div>
          </div>
        </div>
        <hr>
        <c:if test="${ loginUser.id eq 'admin'}">
	        <div class="nav-modify">
	          <a class="modify-btn" href="modify?no=${notice.no}">수정</a>
	          <a class="delete-btn" href="#" onClick="onClickDel()">삭제</a>
	        </div>
          </c:if>
        <article class="notice-body">
          ${ notice.content }
        </article>
      </section>
      <!-- <section class="comment-container">
        <h5>3개의 댓글</h5>
        <form class="comment-form">
          <textarea name="content" rows="3"></textarea>
          <button>등록</button>
        </form>
        <article class="comment">
          <div class="comment-head">
            <span>user-name</span> | 
            <span>reg-date</span>
          </div>
          <div class="comment-body">
            content
          </div>
        </article>
      </section> -->
      <a class="btn-list" href="${ root }/notice/list">목록</a>
    </main>
    <%@ include file="../include/foot.jsp" %>
    <script>
    function onClickDel() {
    	if(confirm("정말 삭제하시겠습니까?")){
    		location.href = "delete?no=${notice.no}";
    	}
    }
    </script>
  </body>
</html>
