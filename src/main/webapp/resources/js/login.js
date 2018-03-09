/**
 * Login and logout functionality
 */
var email;
var password;
var role = "none";
var loginURL = "http://localhost:8080/mase2-project/rest/users/login";

$('document').ready(function(){
	console.log("tu sie dzieje role - " +role )
	if( role === "none") {
		showLoginForm();
	}
});

var importData = function() {
	$.ajax({
		type : 'POST',
		url : loginURL,
		dataType : "json",
		success : loginAction
	});
};

var loginAction = function(data) {
	
	$('.card-header').html();
};



function showLoginForm () {
	$('.content-wrapper').html(""
			+ '<div class="card card-login mx-auto mt-5">' 
		    + '<div class="card-header">Login</div>'
		    + ' <div class="card-body">'
		    + '   <form>'
		    + ' <div class="form-group">'
		    + '   <label for="exampleInputEmail1">Email address</label>'
		    + '   <input class="form-control" id="exampleInputEmail1" type="email" aria-describedby="emailHelp" placeholder="Enter email">'
		    + ' </div>'
		    + ' <div class="form-group">'
		    + '   <label for="exampleInputPassword1">Password</label>'
		    + '   <input class="form-control" id="exampleInputPassword1" type="password" placeholder="Password">'
		    + ' </div>'
		    + ' <div class="form-group">'
		    + '   <div class="form-check">'
		    + '     <label class="form-check-label">'
		    + '   </div>'
		    + ' </div>'
		    + ' <a class="btn btn-primary btn-block" href="index.html">Login</a>'
		    + '</form>'
		    + '<div class="text-center">'
		    + '</div>'
		    + ' </div>'
		    + '</div>);'		
		);
}