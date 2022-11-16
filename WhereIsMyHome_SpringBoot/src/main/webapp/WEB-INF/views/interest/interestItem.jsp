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
    <link rel="stylesheet" href="${ pageContext.request.contextPath }/assets/css/interestItem.css" />
  </head>
  <body>
    <%@ include file="../include/head.jsp" %>
    <%@ include file="../include/nav.jsp" %>

    <section class="map-section">
      <div id="map"></div>
      <ul class="data-list">
        <div class="data-menu">
          <button onclick="location.href='${ pageContext.request.contextPath }/interest/interestArea.jsp'">관심지역</button>
          <button>관심매물</button>
        </div>
        <div class="data-sort">
          <a href="#">등록일순</a>
          |
          <a href="#">최신순</a>
          |
          <a href="#">가격순</a>
          |
          <a href="#">면적순</a>
        </div>
        <li class="data-item">
          <div class="img-box">
            <img src="${ pageContext.request.contextPath}/assets/img/house-2.jpg" alt="apartment" />
          </div>
          <div>
            <p>반포자이</p>
            <p>서초구 반포동 ・ 59.97㎡</p>
            <p>315,000만원</p>
            <p>2018.02.17</p>
          </div>
        </li>
        <li class="data-item">
          <div class="img-box">
            <img src="${ pageContext.request.contextPath}/assets/img/house-1.jpg" alt="apartment" />
          </div>
          <div>
            <p>반포자이</p>
            <p>서초구 반포동 ・ 59.97㎡</p>
            <p>315,000만원</p>
            <p>2018.02.17</p>
          </div>
        </li>
      </ul>
    </section>
    <script type="text/javascript" src="${ pageContext.request.contextPath }/assets/js/utils/apiKeys.js"></script>
    <script type="text/javascript" src="${ pageContext.request.contextPath }/assets/js/utils/map.js"></script>
  </body>
</html>
