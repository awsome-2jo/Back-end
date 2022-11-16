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
    <link rel="stylesheet" href="${ pageContext.request.contextPath }/assets/css/interest/interestArea.css" />
  </head>
  <body>
    <%@ include file="../include/head.jsp" %>
    <%@ include file="../include/nav.jsp" %>
    <section class="map-section">
      <div id="map"></div>
      <div class="data-list">
        <div class="data-menu">
          <button>관심지역</button>
          <button onclick="location.href='${ pageContext.request.contextPath }/interest/interestItem.jsp'">관심매물</button>
        </div>
        <div class="data-add">
          <button id="show">+ 관심 지역 등록</button>
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
        <ul class="data-item-list" id="data-item-list">
        </ul>
      </div>
    </section>
    <div class="background">
      <div class="window">
        <div class="popup">
          <label>관심 지역</label>
          <form class="address-form2">
            <div>
              <select class="form-control" name="sido" id="sido">
                <option value="all">도/광역시</option>
              </select>
              <svg
                xmlns="http://www.w3.org/2000/svg"
                width="16"
                height="16"
                fill="currentColor"
                class="bi bi-chevron-down"
                viewBox="0 0 16 16"
              >
                <path
                  fill-rule="evenodd"
                  d="M1.646 4.646a.5.5 0 0 1 .708 0L8 10.293l5.646-5.647a.5.5 0 0 1 .708.708l-6 6a.5.5 0 0 1-.708 0l-6-6a.5.5 0 0 1 0-.708z"
                />
              </svg>
            </div>
            <div>
              <select class="form-control" name="gugun" id="gugun">
                <option value="all">시/구/군</option>
              </select>
              <svg
                xmlns="http://www.w3.org/2000/svg"
                width="16"
                height="16"
                fill="currentColor"
                class="bi bi-chevron-down"
                viewBox="0 0 16 16"
              >
                <path
                  fill-rule="evenodd"
                  d="M1.646 4.646a.5.5 0 0 1 .708 0L8 10.293l5.646-5.647a.5.5 0 0 1 .708.708l-6 6a.5.5 0 0 1-.708 0l-6-6a.5.5 0 0 1 0-.708z"
                />
              </svg>
            </div>
            <section>
              <button id="close">닫기</button>
              <button id="add" onclick="regist()">등록</button>
            </section>
          </form>
        </div>
      <div>
    <div>
    <script type="text/javascript" src="${ pageContext.request.contextPath }/assets/js/utils/getInterest.js"></script>
    <script type="text/javascript" src="${ pageContext.request.contextPath }/assets/js/utils/apiKeys.js"></script>
    <script type="text/javascript" src="${ pageContext.request.contextPath }/assets/js/utils/search.js"></script>
    <script type="text/javascript" src="${ pageContext.request.contextPath }/assets/js/utils/map.js"></script>
    <script type="text/javascript" src="${ pageContext.request.contextPath }/assets/js/interestArea.js"></script>
  </body>
</html>
