<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>구해줘 홈즈 : 로그인</title>
    <link rel="stylesheet" href="${root}/assets/css/user/login.css" />
  </head>
  <body>
  <%@ include file="../include/head.jsp" %>
    <header>
      <label class="header-logo" onclick="location.replace('${root}/')">구해줘 홈즈</label>
    </header>
    <main class="container">
      <div class="login-page">
        <div class="form">

          <!-- START : Login Form -->
          <form name="login-form" class="login-form" action="${root}/user/login" method="post" onsubmit="return submitLogin()">
            
            <input type="hidden" name="action" value="login" />
            <input type="text" id="id" name="id" placeholder="ID" value="${ cookie.userid.value }" required autofocus />
            <input type="password" id="pass" name="pass" placeholder="password" required />

            <div class="remember">
              <input name="rem" id="rem" type="checkbox" value="rem" />
              <label for="rem">아이디 저장</label>
            </div>

            <button class="login-btn">로그인</button>

          </form>
          <!-- END : Login Form -->

        </div>
        <div class="search">
          <a href="${root}/user/findPass">계정 찾기</a>
          |
          <a href="${root}/user/register">회원가입</a>
        </div>
      </div>
      <div class="blank">&nbsp;</div>
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

    <script type="text/javascript" src="${root}/assets/js/user.js"></script>
  </body>
</html>
