<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
<%@ include file="../include/head.jsp" %>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>구해줘 홈즈 : 회원 목록</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
        <link rel="stylesheet" href="${ root }/assets/css/common.css" />
        <link rel="stylesheet" href="${ root }/assets/css/user/userList.css" />
    </head>
<body>
<%@ include file="../include/nav.jsp" %>
<script>
const users = [];

const User = (id, pass, name, email, phone) => {
	let user ={id, pass, name, email, phone};
	return user;
};

</script>
    <main class="container">
        <h2>회원 목록</h2>
        <table class="list-container">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>이름</th>
                    <th>이메일</th>
                    <th>연락처</th>
                    <th>관리</th>
                </tr>
            </thead>
            <tbody id="user-list">
            <form name="root" action="${root}/user"></form>
            <c:forEach var="user" items="${ users }" varStatus="status">
            <script>
            users.push(User("${user.id}", "${user.pass}", "${user.name}", "${user.email}", "${user.phone}"));
            </script>
                <tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.email}</td>
                    <td>${user.phone}</td>
                    <td>
                        <button onClick="openEditBox(${status.index})">수정</button>
                        <button type="submit" onClick="onClickDel(${status.index})" form="edit-form" name="edit" value="delete">삭제</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </main>
    <aside id="edit-box" class="modify-container">
        <form name="edit-form" action="${root}/user/modify">
            <table>
                <thead>
                        <h4>회원정보 수정</h4>
                        <hr>
                </thead>
                <tr>
                    <th>id</th>
                    <td><input type="text" name="id" id="id" value="admin" readonly></td>
                </tr>
                <tr>
                    <th>비밀번호</th>
                    <td><input type="password" name="pass" id="pass" value="password"></td>
                </tr>
                <tr>
                    <th>이름</th>
                    <td><input type="text" name="name" id="name" value="관리자"></td>
                </tr>
                <tr>
                    <th>연락처</th>
                    <td><input type="tel" name="phone" id="phone" value="01012341234"></td>
                </tr>
                <tr>
                    <th>이메일</th>
                    <td><input type="text" name="email" id="email" value="test@email.com"></td>
                </tr>
            </table>
            <div class="submit-btn-container">
                <button type="button" name="edit" onclick="onEdit(this)">수정</button>
                <button type="button" name="edit" onclick="closeEditBox()">취소</button>
            </div>
        </form>
    </aside>
    <%@ include file="../include/foot.jsp" %>
    <script type="text/javascript" src="${root}/assets/js/userList.js"></script>
</body>
</html>