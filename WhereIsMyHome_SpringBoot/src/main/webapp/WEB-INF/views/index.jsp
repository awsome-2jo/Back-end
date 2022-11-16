<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>구해줘 홈즈</title>
    <!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
<link rel="stylesheet" href="${ root }/assets/css/common.css">
<link rel="stylesheet" href="${ root }/assets/css/main.css">
</head>
<body>
	<%@ include file="include/head.jsp" %>
	<%@ include file="include/nav.jsp" %>
    <main>
        <nav class="nav-type container">
            <div class="row">
                <div class="col-md-3">
                    <div class="div-type">
                        <div class="img-box">
                            <img src="${root}/assets/img/apartment-1.jpg" alt="img">
                        </div>
                        <div>
                            <div>
                                <h2>아파트/다세대</h2>
                                <span>전 · 월세</span>
                            </div>
                            <button id="btn-apt-month">
                                정보조회
                            </button>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="div-type col-md-3">
                        <div class="img-box">
                            <img src="${root}/assets/img/house-1.jpg" alt="img">
                        </div>
                        <div>
                            <div>
                                <h2>주택</h2>
                                <span>전 · 월세</span>
                            </div>
                            <button id="btn-house-month">
                                정보조회
                            </button>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="div-type col-md-3">
                        <div class="img-box">
                            <img src="${root}/assets/img/apartment-2.jpg" alt="img">
                        </div>
                        <div>
                            <div>
                                <h2>아파트/다세대</h2>
                                <span>매매</span>
                            </div>
                            <button id="btn-apt-deal">
                                정보조회
                            </button>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="div-type col-md-3">
                        <div class="img-box">
                            <img src="${root}/assets/img/house-2.jpg" alt="img">
                        </div>
                        <div>
                            <div>
                                <h2>주택</h2>
                                <span>매매</span>
                            </div>
                            <button id="btn-house-deal">
                                정보조회
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </nav>

        <section class="container padding-none">
            <form class="address-form">
                <h2>지역을 선택해 주세요</h2>
                <div>
                    <select class="form-control" name="sido" id="sido" onchange="setReg(this)">
                        <option value="">도/광역시</option>
                    </select>
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chevron-down" viewBox="0 0 16 16">
                        <path fill-rule="evenodd" d="M1.646 4.646a.5.5 0 0 1 .708 0L8 10.293l5.646-5.647a.5.5 0 0 1 .708.708l-6 6a.5.5 0 0 1-.708 0l-6-6a.5.5 0 0 1 0-.708z"/>
                    </svg>
                </div>
                
                <div>
                    <select class="form-control" name="gugun" id="gugun" onchange="setReg(this)">
                        <option value="">시/구/군</option>
                    </select>
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chevron-down" viewBox="0 0 16 16">
                        <path fill-rule="evenodd" d="M1.646 4.646a.5.5 0 0 1 .708 0L8 10.293l5.646-5.647a.5.5 0 0 1 .708.708l-6 6a.5.5 0 0 1-.708 0l-6-6a.5.5 0 0 1 0-.708z"/>
                    </svg>
                </div>
                
                <div>
                    <select class="form-control" name="dong" id="dong" onchange="setReg(this)">
                        <option value="">동/읍/면</option>
                    </select>
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chevron-down" viewBox="0 0 16 16">
                        <path fill-rule="evenodd" d="M1.646 4.646a.5.5 0 0 1 .708 0L8 10.293l5.646-5.647a.5.5 0 0 1 .708.708l-6 6a.5.5 0 0 1-.708 0l-6-6a.5.5 0 0 1 0-.708z"/>
                    </svg>
                </div>
            </form>

            <div class="data-container">
                <div class="apartment" id="data-list-apartment">
                    <h2>아파트 거래정보</h2>
                    <a id="a-more-apt" href="#">전체보기</a>
                </div>
                <div class="house" id="data-list-house">
                    <h2>주택 거래정보</h2>
                    <a id="a-more-house" href="#">전체보기</a>
                </div>
            </div>
        </section>
    </main>
	<%@ include file="include/foot.jsp" %>
	<script type="text/javascript" src="${ pageContext.request.contextPath }/assets/js/utils/apiKeys.js"></script>
    <script type="text/javascript" src="${ pageContext.request.contextPath }/assets/js/utils/const.js"></script>
    <script type="text/javascript" src="${ pageContext.request.contextPath }/assets/js/utils/search.js"></script>
    <script type="text/javascript" src="${ pageContext.request.contextPath }/assets/js/index.js"></script>
</body>

</html>
