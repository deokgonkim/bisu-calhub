<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html id="home" lang="ko">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>로그인 화면</title>
        <link rel="stylesheet" type="text/css" href="resources/css/screen.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="resources/css/print.css" media="print" />
        <link rel="stylesheet" type="text/css" href="resources/css/content.css" media="screen" />
        
        <link rel="stylesheet" type="text/css" href="${initParam.jqueryui_url}/css/smoothness/jquery-ui.css" media="screen" />
        
        <link rel="stylesheet" type="text/css" href="resources/css/login_form/style.css" />
        <style type="text/css">
        
        </style>
        <script type="text/javascript" src="${initParam.jquery_url}/jquery.min.js" charset="UTF-8"></script>
        <script type="text/javascript" src="${initParam.jqueryui_url}/js/jquery-ui.min.js" charset="UTF-8"></script>
        <script type="text/javascript" src="js/login_form.js" charset="UTF-8"></script>
    </head>
    <body>
    <div id="body">
        <table id="header" cellpadding="0" cellspacing="0">
            <tr>
                <td id="header-logo">
                    <a href="${pageContext.request.contextPath}">
                        CALHUB
                    </a>
                </td>
                <td id="header-auth">
                    <p><a href="${pageContext.request.contextPath}/login_form.jsp">로그인</a>?</p>
                </td>
                <td id="header-avatar">
                    
                </td>
            </tr>
        </table>
        <table id="layout-container" cellspacing="0" cellpadding="0">
            <tr>
                <td id="sidebar-container">
                    <div id="sidebar">
                    </div>
                </td>
                <td id="content-container">
                    <div id="content-header">
                        
                        
                    </div>
                    <div id="content">
                        <div id="login-container">
                            <h2>
                                <h1>DRP 로그인하기</h1>
                            </h2>
                            
                            <form method="POST" id="login-form" action="j_security_check">
                                <b><label for="j_username">로그인명</label></b>
                                <input id="j_username" type="text" name="j_username" maxlength="30" />
                                <br />
                                <b><label for="j_password">비밀번호</label></b>
                                <input type="password" name="j_password" id="j_password" />
                                <br />
                                <input type="submit" value="로그인">
                            </form>
                        </div>
                    </td>
                </tr>
            </table>
        </div>
        <div id="footer">
        </div>
    </body>
</html>