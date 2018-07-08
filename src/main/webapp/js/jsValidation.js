window.onload = () => document.getElementById("register-submit").onsubmit = function() { return validateJS();};
function validateJS(){
var firstName = validateName(document.getElementById("first-name").value);
var lastName = validateName(document.getElementById("last-name").value);
var email = validateEmail();
var pass =  validatePassword();
return firstName && lastName && email && pass;
}

function validateName(name){
    console.log("valid name");
    var usernameRegex = /^[A-Z][a-zA-Z0-9]+$/;
    var result = name.match(usernameRegex);
    if (result == null){
        writeMessage("User name is incorrect", name);
        return false;
        }
        return true;
}

function validateEmail(){
    console.log("valid mail");
    var email = document.getElementById("email").value;
    var emailRegex = /^[a-zA-Z0-9]+@rich.com$/;
    var result = email.match(emailRegex);
        if (result == null){
            writeMessage("Email is incorrect ", "email");
            return false;
        }
        return true;
}

function validatePassword(){
    console.log("valid pass");
    var pass = document.getElementById("password").value;
    var secondPass = document.getElementById("re-password").value;
    if (pass !== secondPass){
        writeMessage("Passwords are not equals", "re-password");
        return false;
    }
    var passRegex = /^[a-zA-Z0-9!\-\&\?\.]{4,20}+$/;
    var result = pass.match(passRegex);
        if (result == null){
            writeMessage("Password is incorrect ", "password");
            return false;
        }
        return true;
}

function writeMessage(message, fieldId){
    var field = document.getElementById(fieldId);
    var error = document.createElement('p');
    error.classList.add("erros-message");
    var node = document.createTextNode(message);
    error.appendChild(node);
    insertAfter(error, field);
}

function insertAfter(elem, refElem) {
  return refElem.parentNode.insertBefore(elem, refElem.nextSibling);
}

function removeElementsByClass(className){
    var elements = document.getElementsByClassName(className);
    while(elements.length > 0){
        elements[0].parentNode.removeChild(elements[0]);
    }
}
