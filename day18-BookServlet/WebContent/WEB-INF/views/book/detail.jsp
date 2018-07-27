<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="book.vo.Book" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세 조회 :: 도서관리시스템</title>
<style type="text/css">
table, tr, th, td {
	border : 1px solid black;
}
</style>
</head>
<body>
<h3>도서 상세 조회</h3>
<hr />
<table>
	<tr>
		<th>도서 번호</th>
		<td>${book.bookId}</td>
	</tr>
	<tr>
		<th>도서 이름</th>
		<td>${book.title}</td>
	</tr>
	<tr>
		<th>저자</th>
		<td>${book.author}</td>
	</tr>
	<tr>
		<th>가격</th>
		<td>${book.price}</td>
	</tr>
	<tr>
		<th>ISBN</th>
		<td>${book.isbn}</td>
	</tr>
	<tr>
		<th>출판사</th>
		<td>${book.publish}</td>
	</tr>
	<tr>
		<td colspan="2" style="text-align:center;">
			<a href="${contextPath}/main/list">목록보기</a>
			<a href="${contextPath}/main/update?bookId=${book.bookId}">수정</a>
			<a href="${contextPath}/main/delete?bookId=${book.bookId}">삭제</a>
		</td>
	</tr>
</table>
<br />
<a href="menu">메뉴로 돌아가기</a>
</body>
</html>