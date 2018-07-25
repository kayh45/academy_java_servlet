<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL (4) Core Tag : forEach 2</title>
</head>
<body>


<% // List 를 생성하여 request 에 속성으로 추가
	List<String> fruits = new ArrayList<>();
	fruits.add("자두");
	fruits.add("수박");
	fruits.add("복숭아");
	fruits.add("참외");
	fruits.add("포도");
	
	request.setAttribute("fruits", fruits);
%>

<h4>&lt;c:forEach&gt; 자료구조와 함께 사용</h4>

<c:forEach items="${requestScope.fruits}" var="fruit">
	${fruit} <br />
</c:forEach>

<hr />

<h4>&lt;c:forEach&gt; 자료구조, varStatus를 함께 사용</h4>
<c:forEach items="${requestScope.fruits}" var="fruit" varStatus="fruitStatus">
	${fruit}, count=${fruitStatus.count}, index=${fruitStatus.index} <br />
</c:forEach>

</body>
</html>