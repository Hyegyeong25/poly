<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="kopo.poly.util.CmmUtil" %>

<%
    String msg = (String)request.getAttribute("msg");
    String url = (String)request.getAttribute("url");
%>
<!DOCTYPE html>
<html>
<head>
    <script>
        alert("<%=msg%>")
        location.href="<%=url%>"
    </script>
    <<meta charset="utf-8"/>
    <title>웹페이지 제목z</title>>
</head>
<body>

</body>
</html>