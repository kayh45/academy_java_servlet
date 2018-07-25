<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- JSTL 을 사용하려면 페이지 상단에 태그 라이브러리 사용선언이 필요 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL (1) Core Tag : set, remove</title>
</head>
<body>

	<h4>&lt;c:set&gt;, &lt;c:remove&gt;</h4>
	<pre>
	&lt;c:set&gt; : setAttribute() 메소드와 같은 역할
                page | request | session | application 범위객체에
                속성값을 추가하는 기능
	                
	&lt;c:remove&gt; : removeAttribute() 메소드와 같은 역할
                   page | request | session | application 범위객체에
                   속성값을 삭제하는 기능
	</pre>

<%
	// attribute 를 request 에 추가하려면 스크립트릿에서
	// setAttribute() 메소드로 추가
	
	// 1. 스크립트릿에서 name 속성에 홍길동 값을 추가
	request.setAttribute("name", "길동");

%>

<%-- 2. <c:set> 태그로 속성 추가 --%>
<c:set var="surname" value="홍" scope="request" />


<%-- 3. expression Tag, EL 로 추출 --%>
<pre>
- EL 
     성  : \${requestScope.surname} = ${requestScope.surname}
    이름 : \${name} = ${name}
    
- expressionTag
     성  : <%= request.getAttribute("surname") %>
    이름 : <%= request.getAttribute("name") %>
</pre>
<hr />

<%-- 4. <c:remove>, removeAttribute()로 삭제 --%>
<c:remove var="name" scope="request" />
<% request.removeAttribute("surname"); %>

<%-- 5. expression Tag, EL 로 추출 --%>
<pre>
== name, surname 삭제 후 출력 == 

- EL 
     성  : \${requestScope.surname} = ${requestScope.surname}
    이름 : \${name} = ${name}
    
- expressionTag
     성  : <%= request.getAttribute("surname") %>
    이름 : <%= request.getAttribute("name") %>
</pre>

</body>
</html>