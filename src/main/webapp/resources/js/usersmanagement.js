/**
 * User Management
 * http://legacy.datatables.net/usage/callbacks#fnServerData help <-----
 */
var apiUrlUsersList = "http://localhost:8080/mase2-project/rest/users";

$(document).ready(function() {
    
} );

function showUsers() {
	userListRequest();
	showUsersTable();
}

var userListRequest = function (){
	$.ajax({
		type : 'GET',
		url : apiUrlUsersList,
		dataType : "json",
		headers : {
			'Authorization' : 'Basic '+ sessionStorage.getItem("email")+":"+sessionStorage.getItem("password")
		},
		success : function(data){
			$('#userTable').DataTable({
				data: data,
				columns: [
		            { data: "id" },
		            { data: "email" },
		            { data: "password" },
		            { data: "role" },
		            {
		                data: null,
		                className: "center",
		                defaultContent: '<a href="#" class="editor_edit"><button value="Edit">Edit</button></a> '
		            }
		         ]
			})
		}
	});
};

function showUsersTable() {
	$('#wrapper').html(
		'<div class="card-body"><div class="table-responsive">'
		+'<table id="userTable" "table table-bordered" cellspacing="0" width="100%">'
		+'<thead>'
		+'<tr>'
		+' <th>Id</th>'
		+' <th>Email</th>'
		+' <th>Pasword</th>'
		+' <th>Role</th>'
		+' <th>Edit</th>'
		+' </tr>'
		+' </thead>'
		+' <tbody>	</tbody>'
		+' <tfoot>'
		+' <tr>'
		+' <th>Id</th>'
		+' <th>Email</th>'
		+' <th>Pasword</th>'
		+' <th>Role</th>'
		+' <th>Edit</th>'
		+'</tr>'
		+'</tfoot>'
		+'</table>'
		+'</div></div>');
}