<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
session.invalidate();
%>
<!DOCTYPE html>
<html id="home" lang="ko">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>로그아웃 처리</title>
        <style type="text/css">
        </style>
    </head>
    <body>
    <script type="text/javascript">
    var identity = '${pageContext.request.userPrincipal.name}';
    var contextPath = '${pageContext.request.contextPath}/';
    if ( document.execCommand('ClearAuthenticationCache') == false ) {
        var guest = 'nobody' + Math.ceil(Math.random() * 100);
        document.location.href = document.location.protocol + '//' + guest + '@' + document.location.host + contextPath;
    } else {
        document.location.href = document.location.protocol + '//' + document.location.host + contextPath;
    }
    </script>
    </body>
</html>