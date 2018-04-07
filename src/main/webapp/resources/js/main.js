//JavaScript Document


// mcc_mnc table
var rootUrlMcc_Mnc = "http://localhost:8080/mase2-project/rest/mcc_mnc";
var rootUrlFailureClass = "http://localhost:8080/mase2-project/rest/failureclass";
var rootUrlUe = "http://localhost:8080/mase2-project/rest/ue";
var rootUrlBaseData = "http://localhost:8080/mase2-project/rest/basedatas";
var rootUrlEventCause = "http://localhost:8080/mase2-project/rest/eventcauses";
var rootUrlImportData = "http://localhost:8080/mase2-project/rest/importdata/all";
var rootUrlImportBaseData = "http://localhost:8080/mase2-project/rest/importdata/basedata";
var rootUrlFileNames="http://localhost:8080/mase2-project/rest/importdata/filenames";
var rootUrlManualImport="http://localhost:8080/mase2-project/rest/importdata";

$('document').ready(function() {
	sessionStorage.setItem("role", "none");
	showManiPage();
	$('[data-toggle="modal"]').tooltip();
	
});

jQuery(function($){
	$('#nav_Tables').click(function(){
		console.log("booo");
		checkQueriesNav();
		checkFilesNav();
		checkUsersNav();
	});
	$('#nav_Queries').click(function(){
		checkTablesNav();
		checkFilesNav();
		checkUsersNav();
	});
	$('#nav_Files').click(function(){
		checkUsersNav();
		checkQueriesNav();
		checkTablesNav();
	});
	$('#nav_userMgmt').click(function(){
		checkQueriesNav();
		checkFilesNav();
		checkTablesNav();
	});
});
function checkTablesNav(){
	if (!$('#nav_Tables > a').hasClass('collapsed')) {	
		$('#nav_Tables > a').addClass('collapsed');
		$('#nav_Tables > ul').removeClass('show');
	}
}
function checkQueriesNav(){
	if (!$('#nav_Queries > a').hasClass('collapsed')) {	
		$('#nav_Queries > a').addClass('collapsed');
		$('#nav_Queries > ul').removeClass('show');
	}
}
function checkFilesNav(){
	if (!$('#nav_Files > a').hasClass('collapsed')) {	
		$('#nav_Files > a').addClass('collapsed');
		$('#nav_Files > ul').removeClass('show');
	}
}
function checkUsersNav(){
	if (!$('#nav_userMgmt > a').hasClass('collapsed')) {	
		$('#nav_userMgmt > a').addClass('collapsed');
		$('#nav_userMgmt > ul').removeClass('show');
	}
}
// ///////////////////// Import Tables /////////////////////
function manualImportTables(id) {
	$('#graphtabs').hide();
	showLoading();
	manualImport(id);
}

var manualImport = function(filename) {

	$.ajax({
		type : 'POST',
		contentType: 'application/json',
		url : rootUrlManualImport,
		dataType : "json",
		data :  JSON.stringify(filename),
		success : renderPopUp 
	});
};
var getFileNames = function() {
	$.ajax({
		type : 'GET',
		url : rootUrlFileNames,
		dataType : "json",
		success: addFileNames
	});
};

function addFileNames(fileNames){
	$('#collapseFiles')
	.html('');
	for (index = 0; index < fileNames.length; ++index) {
		$('#collapseFiles')
		.append('<li><a href="baseDataModal.html" class="filelist" id="'+fileNames[index]+'" data-toggle="modal"'
				+'onclick="manualImportTables(this.id)">'+fileNames[index]+'</a></li>');

	}

}




function showLoading(){
	$('#tabletitle').html(''
			+ '	<div class="text-center" id="loding">'
			+ '		<img src="./resources/css/loading_icon.gif" alt="loading" id="loading_gif">'
			+ '	</div>'
	);
}

function showManiPage() {
	console.log("Current role " + sessionStorage.getItem("role"));
	if (sessionStorage.getItem("role") == "none") {
		showLoginForm();
	}
	showNavigation();

}

function showDashboard() {
	$('#graphtabs').hide();
	$('#wrapper').html('<img src="./resources/css/blahblahblah.jpg"  id="EricssonLogo">');

}



