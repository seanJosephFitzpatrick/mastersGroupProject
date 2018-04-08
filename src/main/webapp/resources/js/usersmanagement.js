/**
 * User Management
 * http://legacy.datatables.net/usage/callbacks#fnServerData help <-----
 */
var apiUrlUsers = "http://localhost:8080/mase2-project/rest/users";
var apiUrlUserWithParam = "http://localhost:8080/mase2-project/rest/users/";
var userTable;

$(document).ready(function() {
} );


function editUser(userId, email, password , role) {
	showNewUserForm();
	$('#inputEmail').val(email);
	$('#span_reg').val("Update User");
	$('#selectRole').val(role);
	$('#userFormButton').html('    		<a class="btn btn-primary btn-block" href="#" onClick="updateUser('+userId+')"><span id="span_reg">Update User</span></a>');
	
}

function showUsers() {
	userListRequest();
	showUsersTable();
}

function updateUser(userId) {
	var emailValue = $('#inputEmail').val();
	var passworsValue = $('#inputPassword').val();
	var confirmPassworsValue = $('#confirmPassword').val();
	var roleValue = $('#selectRole option:selected').val();

	var message = "";
	
		if(emailValue == "") {
			message += "Email cannot be empty";
		}
		if(passworsValue == "" || passworsValue != confirmPassworsValue) {
			message += "Passwords don't match";
		}
		
		if(message != "") {
			showNewUserForm();
			$('#userFormButton').html('    		<a class="btn btn-primary btn-block" href="#" onClick="updateUser('+userId+')"><span id="span_reg">Update User</span></a>');
			$('#errorMsg').html(
					'<div class="alert alert-danger alert-dismissable">' 
					+	'<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>'
					+	'<strong>Error</strong> '+ message +'</div> ');
		}
	if(message == "") {
		updateUserInDB(userId, emailValue, md5(passworsValue), roleValue);
	} else {
		$('#inputEmail').val(emailValue);
		$('#selectRole').val(roleValue);
	}

}
function updateUserInDB(id, email, password, role){
	var newUser = {
			"id": id,
			"email": email,
			"password":password,
			"role": role
	}
	
	$.ajax({
		type: "PUT",
		contentType: 'application/json',
		url: apiUrlUserWithParam+id,
		dataType : "json",
		data :  JSON.stringify(newUser),
		headers : {
			'Authorization' : 'Basic '+ sessionStorage.getItem("email")+":"+sessionStorage.getItem("password")
		},
		complete : function () {
			showUsers();
			$('#message').html(
				'<div class="alert alert-success alert-dismissable" role="alert">'
					+	'<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>'
					+'<strong>Success</strong> User updated'
				 +'</div>'
			);
		}
	});
	
	
}
var userListRequest = function (){
	$.ajax({
		type : 'GET',
		url : apiUrlUsers,
		dataType : "json",
		headers : {
			'Authorization' : 'Basic '+ sessionStorage.getItem("email")+":"+sessionStorage.getItem("password")
		},
		success : function(data){
			userTable = $('#userTable').DataTable({
				data: data,
				columns: [
		            { data: "id" },
		            { data: "email" },
//		            { data: "password" },
		            {
		            	render : function(data, type, row) {
		                	return "********"
		            	}
		            },
		            { data: "role" },
		            {
		                className: "center",
		                render: function(data, type, row) {
		                	return "<button class=\"btn btn-primary\" onClick=\"editUser(" +row.id+ ",'"+ row.email +"', '"+ row.password +"', '"+ row.role +"');\" value=\"Edit\">Edit</button>";
		                },
		            }
		         ]
			})
		}
	});
};

function registerNewUser() {

	var emailValue = $('#inputEmail').val();
	var passworsValue = $('#inputPassword').val();
	var confirmPassworsValue = $('#confirmPassword').val();
	var roleValue = $('#selectRole option:selected').val();

	var message = "";

	if(emailValue == "") {
		message += "Email cannot be empty. ";
	}
	if(passworsValue == "" || passworsValue != confirmPassworsValue) {
		message += "Passwords don't match.";
	}
	
	var userFromDBEmail = "";
	if(message != "") {
		showNewUserForm();
		$('#errorMsg').html(
				'<div class="alert alert-danger alert-dismissable">' 
				+	'<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>'
				+	'<strong>Error</strong> '+ message +'</div> ');
	} else {
		$.ajax({
			type : 'GET',
			url : apiUrlUserWithParam + emailValue,
			dataType : "json",
			headers : {
				'Authorization' : 'Basic '+ sessionStorage.getItem("email")+":"+sessionStorage.getItem("password")
			},
			success : function(data){
				userFromDBEmail = data[0].email;
				if(data[0].email == emailValue){
					message += "User already exist";
				}
				showNewUserForm();
				if(message != "") {
					$('#errorMsg').html(
							'<div class="alert alert-danger alert-dismissable">' 
							+	'<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>'
							+	'<strong>Error</strong> '+ message +'</div> ');
				}
			},
			error : function() {
				addUserToDB(emailValue, md5(passworsValue), roleValue);
				
			}
		});
	}
}

function addUserToDB(email, password, role){
	var newUser = {
			"email": email,
			"password":password,
			"role": role
	}
	console.log("Sending post to add user");
	
	$.ajax({
		type: "POST",
		contentType: 'application/json',
		url: apiUrlUsers,
		dataType : "json",
		data :  JSON.stringify(newUser),
		headers : {
			'Authorization' : 'Basic '+ sessionStorage.getItem("email")+":"+sessionStorage.getItem("password")
		},
		success : function(data) {
			
		},
		failure : function(data){
			console.log("WIELKI HUJ WIELKI HUJ");
		},
		complete : function () {
			showUsers();
			$('#message').html(
				'<div class="alert alert-success alert-dismissable" role="alert">'
					+	'<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>'
					+'<strong>Success</strong> User added'
				 +'</div>'
			);
		}
	});
}


function showNewUserForm() {
	$('#wrapper').html(
		'	<div class="card card-register mx-auto mt-5">'
			+'      <div class="card-header">Register an Account</div>'
			+'<div class="card-body">'
			+'<div id="errorMsg"></div>'
			+'  <form id="myForm2">'
			+'    <div class="form-group">'
			+'      <div class="form-row">'
			+'        <div class="col-md-6">'
			+'          <label for="inputRole">Select System Role</label>'
			+'        </div>'
			+'        <div class="col-md-6">'
			+'          <select class="form-control" id="selectRole">'
			+' 				<option value="customer">Customer Service Representative</option>'
			+' 				<option value="manager">Network Management Engineer</option>'
			+' 				<option value="support">Support Engineer</option>'
			+' 				<option value="admin">System Admin</option>'
			+' 			</select>'
			+'        </div>'
			+'      </div>'
			+'    </div>'
			+'    <div class="form-group">'
			+'      <label for="inputEmail">Email address</label>'
			+'      <input class="form-control" id="inputEmail" type="email" aria-describedby="emailHelp" placeholder="Enter email">'
			+'    </div>'
			+'    <div class="form-group">'
			+'      <div class="form-row">'
			+'        <div class="col-md-6">'
			+'          <label for="inputPassword">Password</label>'
			+'          <input class="form-control" id="inputPassword" type="password" placeholder="Password">'
			+'        </div>'
			+'        <div class="col-md-6">'
			+'          <label for="confirmPassword">Confirm password</label>'
			+'          <input class="form-control" id="confirmPassword" type="password" placeholder="Confirm password">'
			+'        </div>'
			+'      </div>'
			+'    </div>'
			+'		<div id="userFormButton">'
			+			'<a class="btn btn-primary btn-block" href="#" onClick="registerNewUser()"><span id="span_reg">Register</span></a>'
			+'		<div>'	
			+'  </form>'
			+'</div>'
			+'</div>'
			);


}
function showUsersTable() {
	$('#wrapper').html(
		'<div id="message"></div>'
		+'<div class="table-responsive">'
		+'	<table id="userTable" class="table table-bordered display" cellspacing="0" width="100%">'
		+'		<thead>'
		+'			<tr>'
		+' 				<th>Id</th>'
		+' 				<th>Email</th>'
		+' 				<th>Pasword</th>'
		+' 				<th>Role</th>'
		+'				<th>Edit</th>'
		+' 			</tr>'
		+' 		</thead>'
//		+' 		<tbody>	</tbody>'
		+'		<tfoot>'
		+'			<tr>'
		+'				<th>Id</th>'
		+'				<th>Email</th>'
		+'				<th>Pasword</th>'
		+'				<th>Role</th>'
		+'				<th>Edit</th>'
		+'			</tr>'
		+'		</tfoot>'
		+'	</table>'
		+'</div>');
}