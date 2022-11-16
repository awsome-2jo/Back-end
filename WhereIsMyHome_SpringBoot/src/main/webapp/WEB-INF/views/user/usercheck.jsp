<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<c:if test="${ !empty loginUser }">
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>구해줘 홈즈 : 회원 정보</title>
    <link rel="stylesheet" href="${root}/assets/css/user/user.css" />
  </head>
  <body>
  <%@ include file="../include/head.jsp" %>
    <header>
      <div class="header-item">
        <div class="header-logo" onclick="location.replace('${root}/index.jsp')">구해줘 홈즈</div>
        <div class="header-menu">회원 정보</div>
      </div>
    </header>
    <main class="container">
      <div class="user-page">
        <div class="form">
          <form name="user-form" class="register-form" method="post" action="${root}/user/usercheck">
          	<input type="hidden" name="action" value="check" />
            <input type="password" name="pass" placeholder="비밀번호" />
            <button>비밀번호 확인</button>
          </form>
        </div>
      </div>
    </main>
    <footer>
      <ul class="footer-list">
        <li><a href="#">이용 약관</a></li>
        <li><a href="#">개인정보처리방침</a></li>
        <li><a href="#">책임의 한계와 법적고지</a></li>
        <li><a href="#">회원정보 고객센터</a></li>
      </ul>
      <div class="footer-copy">
        <span class="footer-logo">구해줘 홈즈</span>
        <span>&copy; SSAFY corp. All Rights Reserved.</span>
      </div>
    </footer>
  </body>
</html>
</c:if>
<c:if test="${ empty loginUser }">
<script>
document.location = "${root}/user/login.jsp";
</script>
</c:if>