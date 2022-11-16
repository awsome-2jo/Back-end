 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>구해줘 홈즈 : 비밀번호 찾기</title>
    <link rel="stylesheet" href="${ pageContext.request.contextPath }/assets/css/user/find.css" />
  </head>
  <body>
  <%@ include file="../include/head.jsp" %>
    <header>
      <label class="header-logo" onclick="location.replace('../index.jsp')">구해줘 홈즈</label>
    </header>
    <main class="container">
      <div class="login-page">
        <div class="form">
          <form name="find-pass-form" class="login-form">
            <div class="name">
              <input id="name" name="name" type="text" placeholder ="이름" required />
            </div>
            <div class="id">
                <input id="id" name="id" type="text" placeholder ="아이디" required />
              </div>
            <div>
              <input id="email" name="email" type="text" placeholder ="이메일" required />
              <div class="pass">
              <input id="pass" name="pass" type="password" placeholder ="비밀번호" required />
            </div>
            <div class="passcheck">
                <input id="passcheck" name="passcheck" type="password" placeholder ="비밀번호 재확인" required />
              </div>
            </div>

            <button class="next-btn" onclick="findPass()" type="button">다음</button>
          </form>
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
    
    <script type="text/javascript" src="${root}/assets/js/findPass.js"></script>
  </body>
</html>

