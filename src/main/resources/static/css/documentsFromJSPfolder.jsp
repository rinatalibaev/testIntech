<%@ page language="java" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Documents</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<!--     <link rel="stylesheet" href="webjars/bootstrap/4.1.3/css/bootstrap.min.css"> -->
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <div class="container">
    <div id="documents_list">
     <h3>СПИСОК ДОКУМЕНТОВ</h3>
     <table id="docTable">
      <thead>
       <tr>
        <th>№</th>
        <th>Название</th>
        <th>Автор</th>
        <th colspan="3">Действия</th>
       </tr>
      </thead>
      <tbody>
      
      </tbody>
     </table>  
    </div>
    <div id = "buttonsDiv">
      <div class = "actionBtns">
         <img id="plusPict" src="/pict/plus.png"/>
         <input id="addDocBtn" type="label" value="Добавить документ"/>
      </div>
      <div class = "actionBtns">
         <img id="lookPict" src="/pict/look.png"/>
         <input id="lookBtn" type="label" value="Просмотреть документ"/>
      </div>
      <div class = "actionBtns">
         <img id="editPict" src="/pict/edit.png"/>
         <input id="editBtn" type="label" value="Редактировать запись"/>
      </div>
      <div class = "actionBtns">
         <img id="deletePict" src="/pict/delete.png"/>
         <input id="deleteBtn" type="label" value="Удалить документ"/>
      </div>
    </div>
    </div>
<!--    
	Из-за того, что скрипт bootstrap шел раньше скрипта jquery в консоли браузера
    появлялась ошибка Type error: i is undefined
-->
    <script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    <script src="js/script.js"></script>
</body>
</html>