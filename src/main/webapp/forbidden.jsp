<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html id="home" lang="ko">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>권한 없음</title>
        <style type="text/css">
        #content-container {
            width: 50%;
            margin-top: 3em;
            display: block;
            margin-left: auto;
            margin-right: auto;
        }
        
        /* username input field */
        #login-container #id_username {
            background: url('../img/accounts/users.png') no-repeat;
            background-position: 2%;
            padding-left: 3.5em;
        }
        /* password input field */
        #login-container #id_password {
            background: url('../img/accounts/keyring.png') no-repeat;
            background-position: 2%;
            padding-left: 3.5em;
        }
        
        </style>
    </head>
    <body>
    <script type="text/javascript">
        var principal = '${pageContext.request.userPrincipal.name}';
        var requestedPage = '${requestScope["javax.servlet.forward.servlet_path"]}';
        if ( confirm(principal + '님은 해당 리소스' + requestedPage + '에 접근할 권한이 없습니다.\n다시 로그아웃후 다시 이용하시겠습니까?') ) {
            document.location.href = '${pageContext.request.contextPath}/logout_process.jsp?Return_Url=' + requestedPage;
        }
    </script>
    </body>
</html>