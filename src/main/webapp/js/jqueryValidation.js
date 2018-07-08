$(document).ready(function () {
    $("#register-submit").submit(validate);
});

function validate(){
  $('.erros-message').remove();
 var firstName = validateName($("#first-name").val());
 var lastName = validateName($("#last-name").val());
  var email = validateEmail();
  var pass =  validatePassword();
  return firstName && lastName && email && pass;
}

function validateLogin(name){
    var usernameRegex = /^[A-Z][a-zA-Z0-9]+$/;
    var result = name.match(usernameRegex);
    if (result == null){
        writeMessage("User name is incorrect", name);
        return false;
        }
        return true;
}

function validateEmail(){
    var email = $("#email").val();
    var emailRegex = /^[a-zA-Z0-9]+@rich.com$/;
    var result = email.match(emailRegex);
        if (result == null){
            writeMessage("Email is incorrect ", "email");
            return false;
        }
        return true;
}

function validatePassword(){
    var pass = $("#password").val();
    var secondPass = $("#re-password").val();
        if (pass !== secondPass){
            writeMessage("Passwords are not equals", "password");
            return false;
        }
    var passRegex = /^[a-zA-Z0-9!\-\&\?\.]{4,20}$/;
    var result = pass.match(passRegex);
        if (result == null){
            writeMessage("Password is incorrect ", "password");
            return false;
        }
        return true;
}

function writeMessage(message, fieldId){
    var error = $('<p />', {"class":'erros-message', text: message});
    $("#" + fieldId).after(error);
}
