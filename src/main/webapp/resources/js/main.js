// JavaScript Document

$('document').ready(function() {
	$('#wrapper').css({backgroundColor:"white"});
	sessionStorage.setItem("role", "none");
	showManiPage();
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

//mcc_mnc table
//var rootUrlMcc_Mnc = "http://localhost:8080/mase2-project/rest/mcc_mnc";
//var rootUrlFailureClass = "http://localhost:8080/mase2-project/rest/failureclass";
//var rootUrlUe = "http://localhost:8080/mase2-project/rest/ue";
//var rootUrlBaseData = "http://localhost:8080/mase2-project/rest/basedatas";
//var rootUrlEventCause = "http://localhost:8080/mase2-project/rest/eventcauses";
//var rootUrlImportData = "http://localhost:8080/mase2-project/rest/importdata/all";
//var rootUrlImportBaseData = "http://localhost:8080/mase2-project/rest/importdata/basedata";

//var findAllMcc_Mnc = function() {
//	$.ajax({
//		type : 'GET',
//		url : rootUrlMcc_Mnc,
//		dataType : "json",
//		success : renderListMcc_Mnc
//	});
//};

//function showTabelMcc_Mnc() {
//	$.ajax({
//		type : 'GET',
//		url : rootUrlMcc_Mnc,
//		dataType : "json",
//		success : function(data) {
//			
//			//cleenAllElements();
//			tableId = "mccmnc_table";
//			showTable(tableId, "Mcc Mnc Table");
////			$('#wrapper').html('<i class="fa fa-table"></i> <span id="tableTitle" >Mcc Mnn Table</span>');
//
//			$('#tableHeader').html("<th>Mcc</th>" + "<th>Mnc</th>" + "<th>Country</th>" + "<th>Operator</th>");
//			$('#tableFooter').html("<th>Mcc</th>" + "<th>Mnc</th>" + "<th>Country</th>" + "<th>Operator</th>");
//
////			$.each(data, function(index, mcc_mnc) {
////				$('#tableBody').append('<tr><td>' + mcc_mnc.id.mcc + '</td><td>' + mcc_mnc.id.mnc + '</td><td>' + mcc_mnc.country + '</td><td>' + mcc_mnc.operator + '</td></tr>');
////			});
//			console.log(data);
//			$(tableId).DataTable({
//				search: true,
//				"data" : data,
//				"columns": [
//					{"data": "id.mcc"},
//					{"data": "id.mnc"},
//					{"data": "county"},
//					{"data": "operator"}
//				]
//			});
//			// {id: {mcc: "238", mnc: "2"}, country: "Denmark", operator: "Sonofon DK "}
//		}
//	});
//}
//
//var renderListMcc_Mnc = function(data) {
//	//cleenAllElements();
//	tableId = "mccmnc_table";
//	showTable(tableId, "Mcc Mnc Table");
////	$('#wrapper').html('<i class="fa fa-table"></i> <span id="tableTitle" >Mcc Mnn Table</span>');
//
//	$('#tableHeader').html("<th>Mcc</th>" + "<th>Mnc</th>" + "<th>Country</th>" + "<th>Operator</th>");
//	$('#tableFooter').html("<th>Mcc</th>" + "<th>Mnc</th>" + "<th>Country</th>" + "<th>Operator</th>");
//
////	$.each(data, function(index, mcc_mnc) {
////		$('#tableBody').append('<tr><td>' + mcc_mnc.id.mcc + '</td><td>' + mcc_mnc.id.mnc + '</td><td>' + mcc_mnc.country + '</td><td>' + mcc_mnc.operator + '</td></tr>');
////	});
//	console.log(data[0].county);
//	$(tableId).DataTable({
//		data : data,
//		columumns: [
//			{data: "id.mcc"},
//			{data: "id.mnc"},
//			{data: "county"},
//			{data: "operator"}
//		]
//	});
//	// {id: {mcc: "238", mnc: "2"}, country: "Denmark", operator: "Sonofon DK "}
//};
//
//function showTable(table_id, title) {
//	$('#wrapper').html (''
//		+'<i class="fa fa-table"></i> <span id="tableTitle" >'+title+'</span>'
//		+'<div class="table-responsive">'
//		+'	<table id="'+table_id+'" class="table table-bordered" cellspacing="0" width="100%">'
//		+'		<thead id="tableHeader"></thead>'
//		+ '		<tfoot id="tableFooter"></tfoot>'
//		+'	</table>'
//		+'<div>'
//		);
//}


//function showLoading(){
//	$('#wrapper')
//	.html('<img src="./resources/images/ajax-loader.gif" id="loading-indicator" style="display:none" />');
//	$('#loading-indicator').show();
//}

// ///////////////////// Dashboard /////////////////////

// ///////////////////// Import Data to Database /////////////////////

//var renderPopUp = function(data) {
//
//	$('#wrapper').html('<div class="alert alert-success alert-dismissable">' + '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>' + '<strong>Success!</strong> ' + data[0]
//					+ ' rows were imported. ' + data[1] + ' rows were excluded.' + ' See log file for details</div> ');
//
//};

//function importTables() {
//	showLoading();
//	importData();
//}
//
//function importBaseDataTable() {
//	showLoading();
//	importBaseData();
//}

// /////////////////////Tables /////////////////////
//var findAllFailureClass = function() {
//	$.ajax({
//		type : 'GET',
//		url : rootUrlFailureClass,
//		dataType : "json",
//		success : renderListFailureClass
//	});
//};

//var findAllUe = function() {
//	$.ajax({
//		type : 'GET',
//		url : rootUrlUe,
//		dataType : "json",
//		success : renderListUe
//	});
//};

//var findAllEventCause = function() {
//	$.ajax({
//		type : 'GET',
//		url : rootUrlEventCause,
//		dataType : "json",
//		success : renderListEventCause
//	});
//};
//var findAllBaseData = function() {
//	$.ajax({
//		type : 'GET',
//		url : rootUrlBaseData,
//		dataType : "json",
//		success : renderListBaseData
//	});
//};



//function showTabelFailureClass() {
//	findAllFailureClass();
//}

//function showTabelUe() {
//	findAllUe();
//}

//function showTableEventcauses() {
//	findAllEventCause();
//}

//function showTableBasedatas() {
//	findAllBaseData();
//}
//function cleenAllElements() {
//	clearElement('tableHeader');
//	clearElement('tableFooter');
//	clearElement('tableBody');
//
//}
//function clearElement(id) {
////	$('.content-wrapper').css("background", "rgb(255,255,255)");
//	console.log(id);
//	if (id !== null) {
//		document.getElementById(id).innerHTML = "";
//	}
//}



//var renderListMcc_Mnc = function(data) {
//	//cleenAllElements();
//
//	$('.card-header').html('<i class="fa fa-table"></i> <span id="tableTitle" >Mcc Mnn Table</span>');
//
//	$('#tableHeader').append("<th>Mcc</th>" + "<th>Mnc</th>" + "<th>Country</th>" + "<th>Operator</th>");
//	$('#tableFooter').append("<th>Mcc</th>" + "<th>Mnc</th>" + "<th>Country</th>" + "<th>Operator</th>");
//
//	$.each(data, function(index, mcc_mnc) {
//		$('#tableBody').append('<tr><td>' + mcc_mnc.id.mcc + '</td><td>' + mcc_mnc.id.mnc + '</td><td>' + mcc_mnc.country + '</td><td>' + mcc_mnc.operator + '</td></tr>');
//	});
//
//	$('#example').DataTable({
//		destroy : true,
//		paging : false,
//		searching : false
//	});
//	document.getElementById('example_info').setAttribute("style", "display:none");
//};

//var renderListUe = function(data) {
//	$('.card-header').html('<i class="fa fa-table"></i> <span id="tableTitle">Ue Table</span>');
//
//	cleenAllElements();
//	$('#tableHeader').append(
//			"<th>TAC</th>" + "<th>Marketing Name</th>" + "<th>Manufacturer</th>" + "<th>Access Capability</th>" + "<th>Model</th>" + "<th>Vendor Name</th>" + "<th>UE Type</th>" + "<th>OS</th>"
//					+ "<th>Input Type</th>");
//
//	$('#tableFooter').append(
//			"<th>TAC</th>" + "<th>Marketing Name</th>" + "<th>Manufacturer</th>" + "<th>Access Capability</th>" + "<th>Model</th>" + "<th>Vendor Name</th>" + "<th>UE Type</th>" + "<th>OS</th>"
//					+ "<th>Input Type</th>");
//
//	$.each(data, function(index, ue) {
//		$('#tableBody').append(
//				'<tr><td>' + ue.tac + '</td><td>' + ue.marketingName + '</td><td>' + ue.manufacturer + '</td><td>' + ue.accessCapability + '</td><td>' + ue.model + '</td><td>' + ue.vendorName
//						+ '</td><td>' + ue.ueType + '</td><td>' + ue.os + '</td><td>' + ue.inputType + '</td></tr>');
//
//	});
//	$('#example').DataTable({
//		destroy : true,
//		paging : false,
//		searching : false
//	});
//	document.getElementById('example_info').setAttribute("style", "display:none");
//
//};
//var renderListFailureClass = function(data) {
//	$('.card-header').html('<i class="fa fa-table"></i> <span id="tableTitle">Failure Class Table</span>');
//
//	cleenAllElements();
//
//	$('#tableHeader').append("<th>Failure Class</th>" + "<th>Description</th>");
//	$('#tableFooter').append("<th>Failure Class</th>" + "<th>Description</th>");
//
//	$.each(data, function(index, failure) {
//		$('#tableBody').append('<tr><td>' + failure.failureClass + '</td><td>' + failure.description + '</td></tr>');
//	});
//
//	$('#example').DataTable({
//		destroy : true,
//		paging : false,
//		searching : false
//	});
//	document.getElementById('example_info').setAttribute("style", "display:none");
//}

//var renderListEventCause = function(data) {
//	$('.card-header').html('<i class="fa fa-table"></i> <span id="tableTitle">Event Cause Table</span>');
//
//	cleenAllElements();
//	;
//
//	$('#tableHeader').append("<th>Event Code</th>" + "<th>Event ID</th>" + "<th>Description</th>");
//	$('#tableFooter').append("<th>Event Code</th>" + "<th>Event ID</th>" + "<th>Description</th>");
//
//	$.each(data, function(index, event_cause) {
//		$('#tableBody').append('<tr><td>' + event_cause.id.eventCode + '</td><td>' + event_cause.id.eventId + '</td><td>' + event_cause.description + '</td></tr>');
//	});
//
//	$('#example').DataTable({
//		destroy : true,
//		paging : false,
//		searching : false
//	});
//	document.getElementById('example_info').setAttribute("style", "display:none");
//}

// //////////////////////////
//var renderListBaseData = function(data) {
//	$('.card-header').html('<i class="fa fa-table"></i> <span id="tableTitle">Base Data Table</span>');
//
//	cleenAllElements();
//
//	$('#tableHeader').append("<th>Id</th>" + "<th>Date-Time</th>" + "<th>Event Id</th>"
//
//	+ "<th>Failure Class</th>" + "<th>Ue type</th>" + "<th>Market</th>"
//
//	+ "<th>Operator</th>" + "<th>Cell Id</th>" + "<th>Duration</th>"
//
//	+ "<th>Cause Code</th>" + "<th>neVersion</th>" + "<th>imsi</th>"
//
//	+ "<th>hier3Id</th>" + "<th>hier32Id</th>" + "<th>hier321Id</th>");
//
//	$('#tableFooter').append("<th>Id</th>" + "<th>Date-Time</th>" + "<th>Event Id</th>"
//
//	+ "<th>Failure Class</th>" + "<th>Ue type</th>" + "<th>Market</th>"
//
//	+ "<th>Operator</th>" + "<th>Cell Id</th>" + "<th>Duration</th>"
//
//	+ "<th>Cause Code</th>" + "<th>neVersion</th>" + "<th>imsi</th>"
//
//	+ "<th>hier3Id</th>" + "<th>hier32Id</th>" + "<th>hier321Id</th>");
//
//	$.each(data, function(index, base_data) {
//		$('#tableBody').append('<tr><td>' + base_data.baseDataId + '</td><td>' + base_data.dateTime + '</td><td>' + base_data.eventCause.id.eventId + '</td><td>'
//
//		+ base_data.failureClassBean.failureClass + '</td><td>' + base_data.ue.tac + '</td><td>' + base_data.mccMnc.id.mcc + '</td><td>'// market
//																																		// mcc
//
//		+ base_data.mccMnc.id.mnc + '</td><td>'// operator mnc
//				+ base_data.cellId + '</td><td>' + base_data.duration + '</td><td>'
//
//				+ base_data.eventCause.id.eventCode + '</td><td>' + base_data.neVersion + '</td><td>' + base_data.imsi + '</td><td>'
//
//				+ base_data.hier3Id + '</td><td>' + base_data.hier32Id + '</td><td>' + base_data.hier321Id + '</td></tr>');
//	});
//	$('#example').DataTable({
//		destroy : true,
//		paging : false,
//		searching : false
//	});
//	document.getElementById('example_info').setAttribute("style", "display:none");
//}
