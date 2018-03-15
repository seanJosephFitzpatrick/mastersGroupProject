// JavaScript Document

$('document').ready(function() {
	$('#wrapper').css({backgroundColor:"white"});
	sessionStorage.setItem("role", "none");
	showManiPage();
	$('[data-toggle="modal"]').tooltip()
});

function showLoading(){
	console.log("gsfgdsgs");
	$('#wrapper').html(''
			+ '	<div calss="text-center" id="loding">'
			+ '		<img src="./resources/css/loading_icon.gif" alt="loading" id="loading_gif">'
			+ '	</div>'
			);
}

function showManiPage() {
	console.log("Current rolle " + sessionStorage.getItem("role"));
	if (sessionStorage.getItem("role") == "none") {
		showLoginForm();
	}
	showNavigation();

}

function showDashboard() {
	$('#wrapper').html("Network Data Analytics");

}



