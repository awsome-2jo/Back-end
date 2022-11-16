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
    <link rel="stylesheet" href="${ pageContext.request.contextPath }/assets/css/user/user.css" />
  </head>
  <body>
    <header>
      <div class="header-item">
        <label class="header-logo" onclick="location.replace('${ pageContext.request.contextPath }/index.jsp')">구해줘 홈즈</label>
        <div class="header-menu">회원 정보</div>
      </div>
    </header>
    <%@ include file="../include/head.jsp" %>
    <main class="container">
      <div class="user-page">
        <div class="form">
          <!-- START : user form -->
          <form name="user-form" class="register-form" method="post">
            <input id="id" name="id" type="text" placeholder="아이디" value="${ loginUser.id }" readonly />
            <input id="pass" name="pass" type="password" placeholder="비밀번호" required/>
            <input id="passcheck" name="passcheck" type="password" placeholder="비밀번호 재확인" required/>
            <input
            id="email"
            name="email"
            type="email"
            placeholder="이메일"
            value="${ loginUser.email }"
            />
            <input id="name" name="name" type="text" placeholder="이름" value="${ loginUser.name }" />
            <input id="phone" name="phone" type="tel" placeholder="전화번호" value="${ loginUser.phone }" />
            <button type="button" value="modify" onclick="submitUser('${root}/user')">정보 수정</button>
            <button type="button" value="delete" onclick="submitUser('${root}/user')">회원 탈퇴</button>
          </form>
          <!-- END : user form -->	
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
</c:if>
<c:if test="${ empty loginUser }">
<script>
document.location = "${root}/user/login.jsp";
</script>
</c:if>