$(document).ready(function () {

    //Вызываем форму создания темы
    $('.nBtn').on('click', function () {
        event.preventDefault();
        var href =$(this).attr('href');
        $('.newThemeForm #newThemeModal').modal();
    });

    //Обработка регистрации пользователя
    $('#registrationForm').on('submit', function(event) {
        console.log("in registrateUser handler");
        event.preventDefault();
        var errorMessage = $('#errorMessage')[0];
        var surname = $("#registrationForm #surname").val();
        var username = $("#registrationForm #name").val();
        var login = $("#registrationForm #login").val();
        var password = $("#registrationForm #password").val();
        var password_confirm = $("#registrationForm #password_confirm").val();
        if (password !== password_confirm) {
            showErrorMessage(errorMessage, "Пароли не совпадают");
            return;
        }

        if (!surname || !username || !login || !password) {
            showErrorMessage(errorMessage, "Заполните все необходимые поля");
            return;
        }
        var digitPattern = /\d/;
        var upperCasePattern = /[A-Z]/;
        var lowerCasePattern = /[a-z]/;
        var specialCharacters = /[!@#$%]/;
        if(!(password.match(digitPattern) && password.match(upperCasePattern) && (password.match(lowerCasePattern)) && (password.match(specialCharacters)) && (password.length>7))) {
            showErrorMessage(errorMessage, "Пароль не соответствует требованиям. Пароль должен быть не менее 8 символов, должен содержать хотя бы одну цифру, строчную букву, заглавную букву и любой символ из !@#$%");
            return;
        }
        var formData = {
        	surname : surname,
        	username :  username,
            login: login,
            password: password,
            enabled: true,
        };

        $.ajax({
            type : "POST",
            contentType: "application/json; charset=utf-8",
            url : window.location.origin + "/registration/save",
            data : JSON.stringify(formData),
            dataType : 'json',
            success : function(result) {
                window.location = '/login';
        		},
            error: function() {
        		    showErrorMessage(errorMessage, "Данный логин занят");
        		},
        	complete: function() {
        	    }
        }).done(function() {
            window.location = "/login";
        });
    });
});

// показывает ошибку на форме регистрации
function showErrorMessage(messageElement, message) {
    messageElement.style.display = 'block';
    messageElement.innerText = message;
    messageElement.style.color = 'pink';
}

// Удаляем сообщение
function deleteItem(id) {
    if (confirm("Удалить это сообщение?")) {
        $.ajax({
            url: '/messages/delete?id=' + id,
            type : "POST",
            success : function(result) {

            },
            error: function() {
			   window.location = "/error";
			   console.log('error', arguments);
            },
            complete: function() {
                console.log('complete', arguments);
            }
        }).done(function() {
            window.location = window.location.href;
            console.log('done', arguments);
        });
    }
}

// Удаляем тему
function deleteTheme(id) {
    if (confirm("Удалить эту тему?")) {
        $.ajax({
            url: '/themes/delete?id=' + id,
            type : "POST",
            success : function(result) {

            },
            error: function() {
                window.location = "/error";
                console.log('error', arguments);
            },
            complete: function() {
                console.log('complete', arguments);
            }
        }).done(function() {
            window.location = window.location.href;
            console.log('done', arguments);
        });
    }
}

//ADD DOCUMENT


