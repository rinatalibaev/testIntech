<%@ page language="java" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Documents</title>
    <link rel="stylesheet" href="webjars/bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <form id="target">
            <div><label> Title : <input type="text" name="title" id="title"/> </label></div>
            <div><label> Author : <input type="text" name="author" id="author"/> </label></div>
            <div><label> Source file : <input type="text" name="src" id="src"/> </label></div>
            <div><input type="submit" value="Add document"/></div>
    </form>
<!--    
	Из-за того, что скрипт bootstrap шел раньше скрипта jquery в консоли браузера
    появлялась ошибка Type error: i is undefined
-->
    <script src="webjars/jquery/3.3.1-1/jquery.min.js"></script>
    <script src="webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    <script src="js/scriptAdd.js"></script>
</body>
</html>