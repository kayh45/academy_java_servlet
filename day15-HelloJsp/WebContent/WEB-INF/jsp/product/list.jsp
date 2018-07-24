<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="shop.vo.Product" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제품 목록</title>
<style type="text/css">
	table, tr, td, th {
		border : solid 1px black;
	}
</style>
</head>
<body>

<%
	// 사용자 정의 클래스 타입인 Product 객체 생성
	Product adidas = new Product("S001", "슈퍼스타", 87200, 5);
	Product reebok = new Product("S002", "리복 로얄 테크큐 티", 42000, 20);
	Product nike = new Product("S003", "나이키 탄준 샌들", 41300, 30);
	Product crocs = new Product("S004", "라이트라이드 샌들 우먼", 40200, 7);
	Product birkenstock = new Product("S005", "지제 에바", 29000, 15);
	
	// 생성된 Product 객체를 목록에 추가
	List<Product> products = new ArrayList<>();
	products.add(adidas);
	products.add(reebok);
	products.add(nike);
	products.add(crocs);
	products.add(birkenstock);
	
	
%>
	<!-- 브라우저 출력을 위한 HTML table 시작 -->
	<table>
		<tr>
			<th>제품 코드</th>
			<th>제품 명</th>
			<th>가격</th>
			<th>재고</th>
		</tr>
	<!-- 반복 출력을 위하여 for 를 시작 -->
<% for (Product product : products) { %>	
	<!-- HTML 코드 tr>(td*4) -->
		<tr>
			<td><%= product.getProdCode() %></td>
			<td><%= product.getProdName() %></td>
			<td><%= product.getPrice() %></td>
			<td><%= product.getQuantity() %></td>
		</tr>
<% } // end for %>
	</table>

</body>
</html>