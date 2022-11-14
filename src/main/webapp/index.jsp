<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String filePath = request.getParameter("filePath");
if (filePath == null) {
	filePath = "home";
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index.jsp</title>
<link href="css/design.css" rel="stylesheet"></link>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="mainContent">
		<jsp:include page='<%=filePath + ".jsp"%>' />
	</div>

	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>