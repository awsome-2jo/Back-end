<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<header>
    <div class="header-mini">
        <div class="container">
            <div class="header-mini-logo">
                <img src="${root}/assets/img/logo.png" alt="ssafy logo" height="30px">
            </div>
            <div class="header-mini-nav">
            
            <c:if test="${ empty loginUser }">
                <a href="${root}/user/login">로그인</a>
                <a href="${root}/user/register">회원가입</a>
            </c:if>
            <c:if test="${ !empty loginUser }">
            	<a href="${root}/user/usercheck">마이페이지</a>
                <a href="${root}/user/logout">로그아웃</a>
            </c:if>
            
            </div>
        </div>
    </div>
    <div class="header-main">
        <div class="container">
            <h1>구해줘 홈즈</h1>
        </div>       
    </div>
</header>
<nav class="nav-topic">
    <div class="container">
        <ul>
            <li><a href="${root}/">홈</a></li>
            <li><a href="${root}/apt">실거래가</a></li>
            <li><a href="${root}/notice/list">공지사항</a></li>
            <c:if test="${ loginUser.id eq 'admin' }">
            	<li><a href="${root}/user/userList">회원관리</a></li>
            </c:if>
        </ul>
    </div>
</nav>