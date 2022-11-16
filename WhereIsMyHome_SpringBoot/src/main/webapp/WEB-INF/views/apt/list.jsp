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
    <link rel="stylesheet" href="${ pageContext.request.contextPath }/assets/css/home/homeList.css" />
  </head>
  <body>
    <%@ include file="../include/head.jsp" %>
    <%@ include file="../include/nav.jsp" %>
<nav class="nav-type">
  <div class="container">
    <ul id="category">
        <li id="BK9" data-order="0"> 
            <span class="category_bg bank"></span>
            은행
        </li>       
        <li id="MT1" data-order="1"> 
            <span class="category_bg mart"></span>
            마트
        </li>  
        <li id="PM9" data-order="2"> 
            <span class="category_bg pharmacy"></span>
            약국
        </li>  
        <li id="OL7" data-order="3"> 
            <span class="category_bg oil"></span>
            주유소
        </li>  
        <li id="CE7" data-order="4"> 
            <span class="category_bg cafe"></span>
            카페
        </li>  
        <li id="CS2" data-order="5"> 
            <span class="category_bg store"></span>
            편의점
        </li>      
    </ul>
  </div>
</div>
</nav>
<main class="map-section">
  <div id="map"></div>

  <form class="search-form" name="search-form">
    <div class="text-form">
      <input type="text" name="inputText" id="inputText" placeholder="아파트, 주택명을 입력하세요.">
      <button type="button" id="btn-submit">
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
          <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
        </svg>
      </button>
    </div>
    <div class="address-form">
      <h3>지역</h3>

      <div>
          <select class="form-control" name="sido" id="sido">
              <option value="">도/광역시</option>
          </select>
          <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chevron-down" viewBox="0 0 16 16">
              <path fill-rule="evenodd" d="M1.646 4.646a.5.5 0 0 1 .708 0L8 10.293l5.646-5.647a.5.5 0 0 1 .708.708l-6 6a.5.5 0 0 1-.708 0l-6-6a.5.5 0 0 1 0-.708z"/>
          </svg>
      </div>
      
      <div>
          <select class="form-control" name="gugun" id="gugun">
              <option value="">시/구/군</option>
          </select>
          <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chevron-down" viewBox="0 0 16 16">
              <path fill-rule="evenodd" d="M1.646 4.646a.5.5 0 0 1 .708 0L8 10.293l5.646-5.647a.5.5 0 0 1 .708.708l-6 6a.5.5 0 0 1-.708 0l-6-6a.5.5 0 0 1 0-.708z"/>
          </svg>
      </div>
      
      <div>
          <select class="form-control" name="dong" id="dong">
              <option value="">동/읍/면</option>
          </select>
          <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chevron-down" viewBox="0 0 16 16">
              <path fill-rule="evenodd" d="M1.646 4.646a.5.5 0 0 1 .708 0L8 10.293l5.646-5.647a.5.5 0 0 1 .708.708l-6 6a.5.5 0 0 1-.708 0l-6-6a.5.5 0 0 1 0-.708z"/>
          </svg>
      </div>
    </div>
  </form>
  
  <section class="section-data-list">
    <div class="data-address">
      <h3 id="select-address">전체</h3>
      <span class="btn-like" id="btn-like">
      </span>
    </div>
    <ul class="data-list" id="data-list">
    </ul>
  </section>
  
  <script type="text/javascript" src="${ pageContext.request.contextPath }/assets/js/utils/apiKeys.js"></script>
  <script type="text/javascript" src="${ pageContext.request.contextPath }/assets/js/utils/const.js"></script>
  <script type="text/javascript" src="${ pageContext.request.contextPath }/assets/js/utils/search.js"></script>
  <script type="text/javascript" src="${ pageContext.request.contextPath }/assets/js/utils/map.js"></script>
  <script type="text/javascript" src="${ pageContext.request.contextPath }/assets/js/homeList.js"></script>
  </main>
</body>
</html>
