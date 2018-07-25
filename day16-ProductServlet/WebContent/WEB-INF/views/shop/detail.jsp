<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="shop.vo.Product" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제품 상세 조회</title>
<style type="text/css">
table, tr, th, td {
	border : 1px solid black;
}
</style>
</head>
<body>
<h3>제품 상세 조회</h3>
<hr />
<table>
	<tr>
		<th>제품 코드</th>
		<td>
			<input name="prodCode" type="text" required="required" value = "${product.prodCode}" readonly/>
		</td>
	</tr>
	<tr>
		<th>제품 이름</th>
		<td>
			<input name="prodName" type="text"  value = "${product.prodName}" readonly/>
		</td>
	</tr>
	<tr>
		<th>가격</th>
		<td>
			<input name="price" type="number"  value = "${product.price}" readonly/>
		</td>
	</tr>
	<tr>
		<th>재고</th>
		<td>
			<input name="quantity" type="number" value = "${product.quantity}" readonly/>
		</td>
	</tr>
</table>
<br />
<a href="menu">메뉴로 돌아가기</a>
</body>
</html>