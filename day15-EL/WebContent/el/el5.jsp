<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL (5) 내장 객체의 레벨</title>
</head>
<body>
	<h3>내장 객체의 레벨</h3>
	<pre>
	1. page : 현재 페이지에서만 사용 가능한 범위를 가빔
	
	2. request : 응답(response) 이 일어나기 전까지 유지되는 객체
	             응답이 일어나기 전가지는 동일한 request가 유지됨.
	             지속적으로 여러 페이지를 통과하며 전달될 수 있음.
	             응답이 일어나기 전까지는 해당 request 설정(추가)된
	             attribute가 유지된다.
	             모든 요청(request)는 응답(response)가 발생하면 소멸.
	             
	3. session : 명시적으로 생성하는 객체. 명시적으로 제거할 때까지 유지
	   	         세션은 요청에 연셜되어있어서 요청으로 부터 추출하여 생성.
	   	         세션은 요청에 대한 응답이 일어나도 자동으로 소멸하지 않음
	   	         명시적으로 invalidate() 를 시켜야 소멸.
	   	         보통은 로그인 정보 등을 유지할 때 사용
	   	         
	4. application : 하나의 웹 어플리케이션당 1개씩만 생성되는 객체
					 웹 컨테이너에 하나의 서비스가 배포되어서 유지되는 동안
					 계속하여 유지된다.
					 서버가 종료될 때까지 계속해서 하나의 객체가 유지됨.
					 즉 유지 범위가 가장 길다.
					 
	* JSP 내장 객체의 유지 범위
	page => request => session => application
	
	EL 의 내장 객체
	pageScope => requestScope => sessionScope => applicationScope
	</pre>
	
<%

	// 1. jsp 내장 객체 중에서 page 에 attribute 로 id를 추가
	pageContext.setAttribute("id", "gildong-page");

	// 2. jsp 내장 객체 중에서 request 에 attribute로 id를 추가
	request.setAttribute("id", "gildong-req");
	request.setAttribute("name", "길동이-req");
	
	// 3. jsp 내장 객체 중에서 session 에 attribute로 id를 추가
	session.setAttribute("id", "gildong-session");
	session.setAttribute("name", "길동이-session");
	
	// 4. jsp 내장 객체 중에서 application 에 attribute로 id를 추가
	application.setAttribute("id", "gildong-app");

%>

<h3>EL 의 내장 객체에 설정된 속성(attribute) 추출</h3>
<pre>
1. pageScope : \${pageScope.id} = ${pageScope.id}
2. requestScope : \${requestScope.id} = ${requestScope.id}
3. sessionScope : \${sessionScope.id} = ${sessionScope.id}
4. applicationScope : \${applicationScope.id} = ${applicationScope.id}

5. 범위를 명시하지 않고 속성 값만 명시 할 때 어떤 범위가 선택되는가
	=> 가장 작은 범위의 것이 자동 선택됨
	\${id} = ${id}
	\${name} = ${name}
</pre>

<hr />
<h3>scriptlet, expression Tag를 사용하여 속성 추출</h3>

<% 
	String pageId = (String) pageContext.getAttribute("id");
	String reqId  = (String) request.getAttribute("id");
	String sessId = (String) session.getAttribute("id");
	String appId  = (String) application.getAttribute("id");
	
	String reqName = (String) request.getAttribute("name");
	String sessName = (String) session.getAttribute("name");
%>

1. page Id = <%= pageId %> <br />
2. req Id  = <%= reqId %> <br />
3. sess Id = <%= sessId %> <br />
4. app Id  = <%= appId %> <br />

5. req Name   = <%= reqName %> <br />
6. sess Name  = <%= sessName %> <br />

</body>
</html>