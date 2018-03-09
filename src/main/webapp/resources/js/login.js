/**
 * Login and logout functionality
 */
var email;
var password;
var role = "none";
var loginURL = "http://localhost:8080/mase2-project/rest/users/login";

$('document').ready(function() {
	sessionStorage.setItem("role", "none");
	showPage();
});
 
function logoutAction() {
	sessionStorage.setItem("role", "none");
	showPage();
}
function showPage() {
	console.log("Current rolle " + sessionStorage.getItem("role"));
	if (sessionStorage.getItem("role") === "none") {
		showLoginForm();
	} else {
		$('.content-wrapper').html("<p>Welcome "+ role +"</p>");
	}
}
function loginRequest(emailValue, passworsValue){
	$.ajax({
		type : 'GET',
		url : loginURL,
		dataType : "json",
		success : postLoginAction,
		headers : {
			'Authorization' : 'Basic '+emailValue+":"+passworsValue
		},
	});
};

function loginFunction() {
	var passworsValue = document.getElementById("inputPass").value;
	var emailValue = document.getElementById("inputEmail").value;
	console.log(passworsValue + " " + emailValue);
	loginRequest(emailValue, passworsValue);

}

var postLoginAction = function(data) {
	console.log("Back from server - -- " + data.role);
	sessionStorage.setItem("role", data.role);
	showPage();
};

function showLoginForm() {
	$('.content-wrapper')
			.html(
					""
							+ '<div class="card card-login mx-auto mt-5">'
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
							+ '    <a class="btn btn-primary btn-block" onclick="loginFunction()">Login</a>'
//							+ '  </form>' 
							+ '<div class="text-center">'
							+ '</div>' + ' </div>' + '</div>);');
}