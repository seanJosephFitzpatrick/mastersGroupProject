
var rootUrlImportData = "http://localhost:8080/mase2-project/rest/importdata/all";
var rootUrlImportBaseData = "http://localhost:8080/mase2-project/rest/importdata/basedata";

function importTables() {
	showLoading();
	importData();
}

var importData = function() {
	$.ajax({
		type : 'GET',
		url : rootUrlImportData,
		dataType : "json",
		headers : {
			'Authorization' : 'Basic '+ sessionStorage.getItem("email")+":"+sessionStorage.getItem("password")
		},
		success : renderPopUp
	});
};

var renderPopUp = function(data) {

	$('#wrapper').html('<div class="alert alert-success alert-dismissable">' + '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>' + '<strong>Success!</strong> ' + data[0]
					+ ' rows were imported. ' + data[1] + ' rows were excluded.' + ' See log file for details</div> ');

};


