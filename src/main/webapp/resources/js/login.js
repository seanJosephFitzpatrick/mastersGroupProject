/**
 * Login and logout functionality
 */

var loginURL = "http://localhost:8080/mase2-project/rest/users/login";


function loginRequest(emailValue, passworsValue){
	$.ajax({
		type : 'GET',
		url : loginURL,
		success : postLoginAction,
		dataType : "json",
		headers : {
			'Authorization' : 'Basic '+ emailValue+":"+passworsValue
		},
	});
};

function loginFunction() {
	var passworsValue = document.getElementById("inputPass").value;
	var emailValue = document.getElementById("inputEmail").value;
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
	sessionStorage.setItem("role", "none");
	showManiPage();
}

function showLoginForm() {
	$('#wrapper')
			.html(
					""
							+ '<div id="loginForm" class="card card-login mx-auto mt-5">'
							+ '<div class="card-header">Login</div>'
							+ '<div class="card-body">'
//							+ '  <form id="loginForm">'
							+ '    <div class="form-group">'
							+ '      <label for="inputEmail">Email address</label>'
							+ '      <input class="form-control" id="inputEmail" type="text" aria-describedby="emailHelp" placeholder="Enter email">'
							+ '   </div>'
							+ '   <div class="form-group">'
							+ '      <label for="inputPass">Password</label>'
							+ '      <input class="form-control" id="inputPass" type="password" placeholder="Password">'
							+ '    </div>'
							+ '    <div class="form-group">'
							+ '      <div class="form-check">'
							+ '        <label class="form-check-label">'
							+ '      </div>'
							+ '    </div>'
							+ '    <a class="btn btn-primary btn-block" id="loginbutton" onclick="loginFunction()">Login</a>'
//							+ '  </form>' 
							+ '<div class="text-center">'
							+ '</div>' + ' </div>' + '</div>');
}