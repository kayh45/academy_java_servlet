<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL (2) Core Tag : if</title>
</head>
<body>


<h4>&lt;c:if&gt;</h4>
<pre>
&lt;c:if&gt; : if 구문과 같이 논리 결과에 따라
            선택을 분기 시키는 기능
            test 속성 값이 true / false 인지에 따라 분기
            test 속성 값에 EL을 사용할 수 있다.
</pre>

<hr />
<%-- 1. request 에 name 속성을 추가 --%>
<c:set var="name" value="길동" scope="request" />

<h4>&lt;c:if&gt;의 활용</h4>
<pre>
1. request 에 name 이라는 속성 값이 있는가?
   EL : \${not empty requestScope.name}
   
   &lt;c:if&gt; 와 EL을 조합
<c:if test="${not empty requestScope.name}">
	request 에 name 값이 있습니다.
</c:if>
<c:if test="${empty requestScope.name}">
	request 에 name 값이 없습니다.	
</c:if>

<% // <c:if> 와 동일한 일을 하는 스크릿트릿 코드 
	if (request.getAttribute("name") != null) {
%>
	request 에 name 값이 있습니다.
<%
	} else {
%>
	request 에 name 값이 없습니다.
<%
	}
%>

</pre>

<hr />

<pre>
2. name 이 길동입니까?
   EL : \${requestScope.name eq '길동'}
   
<c:if test="${requestScope.name eq '길동'}">
   이름이 '${name}'입니다.
</c:if>
<c:if test="${name ne '길동'}">
   이름이 '길동'이가 아닙니다.
</c:if>
</pre>

<hr />

<ol>
	<li>http://localhost:8088/day16-JSTL/jstl/jstl2.jsp?id=gildong</li>
	<li>http://localhost:8088/day16-JSTL/jstl/jstl2.jsp?id=</li>
	<li>http://localhost:8088/day16-JSTL/jstl/jstl2.jsp</li>
</ol>

<pre>
3. id 라는 값이 파라미터에 있습니가?
   EL : \${not empty param.id}
   
<c:if test="${not empty param.id}">
	파라미터에 id 값이 존재합니다.
</c:if>
<c:if test="${empty param.id}">
	파라미터에 id 값이 없습니다.
</c:if>
</pre>

<hr />

<ol>
	<li>?id=gildong&size=L&hobby=movie</li>
	<li>?id=&size=S</li>
	<li>?size=M</li>
	<li>?size=M&hobby=soccer</li>
</ol>
<pre>
4. 안녕하세요.
   
<c:if test="${not empty param.id}">
	당신은 ${param.id} 이군요. 반갑습니다.
</c:if>
<c:if test="${empty param.id}">
	무명씨, 어서오세요.
</c:if>

	선택한 사이즈는
<c:if test="${param.size eq 'L'}">
	라지 입니다.
</c:if>
<c:if test="${param.size eq 'M'}">
	보통 입니다.
</c:if>
<c:if test="${param.size eq 'S'}">
	스몰 입니다.
</c:if>
<c:if test="${empty param.size}">
	없습니다.
</c:if>

	당신의 취미는
<c:if test="${not empty param.hobby}">
	<c:if test="${param.hobby eq 'movie'}">
	영화보기 입니다.
	</c:if>
	<c:if test="${param.hobby ne 'movie'}">
	영화보기는 아니네요.
	</c:if>
</c:if>
<c:if test="${empty param.hobby}">
	입력되지 않았습니다.
</c:if>

</pre>
</body>
</html>