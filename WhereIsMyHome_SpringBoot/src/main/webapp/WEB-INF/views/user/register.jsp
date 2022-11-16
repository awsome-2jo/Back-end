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
    <title>구해줘 홈즈 : 회원가입</title>
    <link rel="stylesheet" href="${root}/assets/css/user/register.css" />
  </head>
  <body>
  <%@ include file="../include/head.jsp" %>
    <header>
      <div class="header-logo">
        <label class="header-logo" onclick="location.replace('${root}/index.jsp')">구해줘 홈즈</label>
      </div>
    </header>
    <main class="container">
      <div class="register-page">
        <div class="form">

          <!-- START : regist form -->
          <form name="regist-form" class="register-form" action="${root}/user/register" method="post" onsubmit="return submitRegist()">
            <input type="hidden" name="action" value="regist" />
            <input type="text" id="id" name="id" placeholder="아이디" required/>
            <input type="password" id="pass" name="pass" placeholder="비밀번호" required/>
            <input type="password" id="passcheck" name="passcheck" placeholder="비밀번호 재확인" required/>
            <input type="email" id="email" name="email" placeholder="이메일" required/>
            <input type="text" id="name" name="name" placeholder="이름" required/>
            <input type="tel" id="phone" name="phone" placeholder="전화번호" required/>
            <button>회원 가입</button>
          </form>
          <!-- END : regist form -->
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
    <script type="text/javascript" src="${root}/assets/js/user.js"></script>
  </body>
</html>
