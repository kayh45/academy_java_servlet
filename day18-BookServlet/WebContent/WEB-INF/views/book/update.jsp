<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정보 수정 :: 도서관리시스템</title>
<style type="text/css">
table, tr, th, td {
	border : 1px solid black;
}
</style>
</head>
<body>
<h3>제품 정보 수정</h3>
<hr />
<form action="update" method="post">
<table>
	<tr>
		<th>도서 번호</th>
		<td>
			<input name="bookId" type="text" value="${book.bookId}" readonly/>
		</td>
	</tr>
	<tr>
		<th>도서 이름</th>
		<td>
			<input name="title" type="text" value="${book.title}" />
		</td>
	</tr>
	<tr>
		<th>저자</th>
		<td>
			<input name="author" type="text" value="${book.author}"/>
		</td>
	</tr>
	<tr>
		<th>가격</th>
		<td>
			<input name="price" type="number" value="${book.price}" />
		</td>
	</tr>
	<tr>
		<th>ISBN</th>
		<td>
			<input name="isbn" type="number" value="${book.isbn}" />
		</td>
	</tr>
	<tr>
		<th>출판사</th>
		<td>
			<input name="publish" type="text" value="${book.publish}" />
		</td>
	</tr>
	<tr>
		<td colspan="2" style="text-align:center;">
			<a href="${contextPath}/main/list">목록보기</a>
			<a href="${contextPath}/main/detail?bookId=${book.bookId}">수정 취소</a>
			<input type="submit" value="저장" />
			<input type="reset" value="초기화" />
		</td>
	</tr>
</table>
</form>
<br />
<a href="menu">메뉴로 돌아가기</a>
</body>
</html>