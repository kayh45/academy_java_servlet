<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신규 등록 :: 도서관리시스템</title>
<style type="text/css">
table, tr, th, td {
	border : 1px solid black;
}
</style>
</head>
<body>

<h3>도서 신규 등록</h3>
<hr />
<form action="${contextPath}/main/insert" method="post">

<table>
	<tr>
		<th colspan="2">도서 정보 등록하기</th>
	</tr>
	<tr>
		<th>도서 번호</th>
		<td>
			<input name="bookId" type="text" required="required"/>
		</td>
	</tr>
	<tr>
		<th>도서 이름</th>
		<td>
			<input name="title" type="text" />
		</td>
	</tr>
	<tr>
		<th>저자</th>
		<td>
			<input name="author" type="text" />
		</td>
	</tr>
	<tr>
		<th>가격</th>
		<td>
			<input name="price" type="number" />
		</td>
	</tr>
	<tr>
		<th>ISBN</th>
		<td>
			<input name="isbn" type="number" />
		</td>
	</tr>
	<tr>
		<th>출판사</th>
		<td>
			<input name="publish" type="text" />
		</td>
	</tr>
	<tr>
		<th colspan="2">
			<input type="submit" value="등록하기" />
			<input type="reset" value="초기화" />
		</th>
	</tr>
</table>
</form>
<br />
<a href="menu">메뉴로 돌아가기</a>
</body>
</html>