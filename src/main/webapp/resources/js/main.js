//JavaScript Document


// mcc_mnc table
var rootUrlMcc_Mnc = "http://localhost:8080/mase2-project/rest/mcc_mnc";
var rootUrlFailureClass = "http://localhost:8080/mase2-project/rest/failureclass";
var rootUrlUe = "http://localhost:8080/mase2-project/rest/ue";
var rootUrlBaseData = "http://localhost:8080/mase2-project/rest/basedatas";
var rootUrlEventCause = "http://localhost:8080/mase2-project/rest/eventcauses";
var rootUrlImportData = "http://localhost:8080/mase2-project/rest/importdata/all";
var rootUrlImportBaseData = "http://localhost:8080/mase2-project/rest/importdata/basedata";
var rootUrlFileNames="http://localhost:8080/mase2-project/rest/importdata/filenames"
var rootUrlManualImport="http://localhost:8080/mase2-project/rest/importdata"

$('document').ready(function() {
	$('#wrapper').css({backgroundColor:"white"});
	sessionStorage.setItem("role", "none");
	showManiPage();
	$('[data-toggle="modal"]').tooltip()
});


// ///////////////////// Import Tables /////////////////////
function manualImportTables(id) {
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



