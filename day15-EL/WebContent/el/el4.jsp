<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL (4) 내장객체의 사용</title>
</head>
<body>

<%
	// 이 페이지에 진입했을 때 리스트를 하나 생성
	java.util.List<String> stars = new java.util.ArrayList<>();

	stars.add("화사");
	stars.add("비");
	stars.add("정우성");
	stars.add("정유미");
	stars.add("아이유");
	
	// 생성된 목록을 request 에 attribute 로 추가
	request.setAttribute("stars", stars);
%>

<h3>EL 에서 내장객체인 request에 추가된 객체를 추출</h3>

<pre>
	JSP 내장객체 : request | EL 내장객체 : requestScope
	목록객체에 대해서 [] (브래킷 연산자)로 값을 추출
	
	1. \${requestScope.stars[0]} = ${requestScope.stars[0]}
	2. \${requestScope["stars"][1]} = ${requestScope["stars"][1]}
	3. \${stars[2]} = ${stars[2]} 
	   requestScope을 생략 가능. 다른 내장 객체에 동일한 이름(stars)가 attribute로 추가된 것이 없을 때만
	4. \${stars["3"]} = ${stars["3"]}
	5. \${requestScope["stars"]["4"]} = ${requestScope["stars"]["4"]}
</pre>

<%
	// 맵 객체를 생성
	java.util.Map<String, String> starMap = new java.util.HashMap<>();
	starMap.put("S1", "화사");
	starMap.put("S2", "비");
	starMap.put("S3", "정우성");
	starMap.put("S4", "정유미");
	starMap.put("S5", "아이유");
	
	// 맵 객체를 request에 attribute 로 추가
	request.setAttribute("starMap", starMap);
%>

<h3>EL 에서 내장 객체인 request에 추가된 맵 객체를 추출</h3>
<pre>
	맵 객체에 대하여 [] (브래킷 연산자)로 값을 추출
	
	1. \${requestScope.starMap["S1"]} = ${requestScope.starMap["S1"]} 
	2. \${requestScope.starMap["S2"]} = ${requestScope.starMap["S2"]} 
	3. \${starMap["S3"]} = ${starMap["S3"]} 
	4. \${starMap["S4"]} = ${starMap["S4"]} 
	5. \${starMap["S5"]} = ${starMap["S5"]} 
</pre>

<hr />
<h3>scriptlet, expression Tag로 목록 추출 시</h3>
<pre>
<%

	// request 객체에서 attribute를 추출(getAttribute() 메소드 사용)
	java.util.List<String> starNames = (java.util.List<String>)request.getAttribute("stars");
%>

1. &lt;%= starNames.get(0) %&gt; = <%= starNames.get(0) %> <br />
2. &lt;%= starNames.get(1) %&gt; = <%= starNames.get(1) %> <br />
3. &lt;%= starNames.get(2) %&gt; = <%= starNames.get(2) %> <br />
4. &lt;%= starNames.get(3) %&gt; = <%= starNames.get(3) %> <br />
5. &lt;%= starNames.get(4) %&gt; = <%= starNames.get(4) %> <br />
</pre>

<h3>scriptlet, expression Tag로 맵 추출 시</h3>
<pre>
<%
	//request 객체에서 attribute를 추출
	java.util.Map<String, String> starNameMap = (java.util.Map<String, String>)request.getAttribute("starMap");

%>

1. &lt;%= starNameMap.get("S1") %&gt; = <%= starNameMap.get("S1") %> <br />
2. &lt;%= starNameMap.get("S2") %&gt; = <%= starNameMap.get("S2") %> <br />
3. &lt;%= starNameMap.get("S3") %&gt; = <%= starNameMap.get("S3") %> <br />
4. &lt;%= starNameMap.get("S4") %&gt; = <%= starNameMap.get("S4") %> <br />
5. &lt;%= starNameMap.get("S5") %&gt; = <%= starNameMap.get("S5") %> <br />

</pre>
</body>
</html>