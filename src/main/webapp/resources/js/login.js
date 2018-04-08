/**
 * Login and logout functionality
 */

var loginURL = "http://localhost:8080/mase2-project/rest/users/login";


function loginRequest(emailValue, passworsValue){
	$.ajax({
		type : 'GET',
		url : loginURL,
		success : postLoginAction,
		error : function (){
			console.log("Wrong pass");
			$('#inputPass').addClass("login_error");
			$('#login_error').show();
		},
		dataType : "json",
		headers : {
			'Authorization' : 'Basic '+ emailValue+":"+passworsValue
		},
	});
};

function loginFunction() {
	var passworsValue = document.getElementById("inputPass").value;
	var emailValue = document.getElementById("inputEmail").value;
	
	passworsValue = md5("passworsValue");
	
	console.log(passworsValue + " " + emailValue);
	loginRequest(emailValue, passworsValue);
	sessionStorage.setItem("email", emailValue);
	sessionStorage.setItem("password", passworsValue);
}

var postLoginAction = function(data) {
	console.log("Back from server - -- " + data.role);
	sessionStorage.setItem("role", data.role);
	$('#wrapper').html("");
	showManiPage();
};

function logoutAction() {
	$('#graphtabs').hide();
	sessionStorage.setItem("role", "none");
	showManiPage();
}

function showLoginForm() {
	$('#wrapper').html(''
			+ '	<div id="loginForm" class="card card-login mx-auto mt-5">' 
			+ '		<div class="card-header labelclass">Network Data Analytics</div>' 
			+ '		<div class="card-body">'
			+ '			<div class="form-group">' 
			+ '      		<label for="inputEmail" class="labelclass">Email address</label>'
			+ '      		<input class="form-control" id="inputEmail" type="email" aria-describedby="emailHelp" placeholder="Enter email">' 
			+ '			</div>' 
			+ '			<div class="form-group">'
			+ '				<label for="inputPass" class="labelclass">Password</label>' 
			+ '      		<input class="form-control" id="inputPass" type="password" placeholder="Password">' 
			+ '			</div>'
			+ '			<div id="login_error" class="alert alert-danger">'
			+ '				<strong>Error!</strong> Incorrect Credentials.'
			+ '			</div>' 
			+ '			<button type="button" class="btn btn-primary btn-block" id="loginbutton" onclick="loginFunction()">Login</button>'
			+ ' 	</div>' 
			+ '</div>');
	$('#login_error').hide();
}