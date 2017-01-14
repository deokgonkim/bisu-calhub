<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title id="page-title">Tester</title>
    <link rel="stylesheet" type="text/css" href="lib/jasmine-1.2.0/jasmine.css">
    <script type="text/javascript" src="${initParam.extjs_url}/ext-debug.js"></script>
    
    <script type="text/javascript" src="lib/jasmine-1.2.0/jasmine.js"></script>
    <script type="text/javascript" src="lib/jasmine-1.2.0/jasmine-html.js"></script>
    
    <!-- include specs here -->
    <script type="text/javascript" src="app-test/specs/application.js"></script>
    <script type="text/javascript" src="app-test/specs/store/Users.js"></script>
    <script type="text/javascript" src="app-test/specs/store/CalendarEvents.js"></script>
    
    <!-- test launcher -->
    <script type="text/javascript" src="app-test.js"></script>

</head>
<body>
</body>
</html>