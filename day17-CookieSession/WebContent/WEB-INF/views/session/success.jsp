<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 성공(session)</title>
</head>
<body>
<h3>로그인 성공(session)</h3>
<hr />
안녕하세요, ${sessionScope.userId}님, 로그인되었습니다.<br />
안녕하세요, ${userId}님, 로그인되었습니다.<br />
<hr />

<a href="${pageContext.request.contextPath}/session/logout">로그아웃 EL</a>
<a href="<%= application.getContextPath() %>/session/logout">로그아웃 ET</a>
</body>
</html>