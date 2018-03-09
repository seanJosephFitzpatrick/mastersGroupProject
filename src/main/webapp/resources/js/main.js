// JavaScript Document

//mcc_mnc table
var rootUrlMcc_Mnc = "http://localhost:8080/mase2-project/rest/mcc_mnc";
var rootUrlFailureClass = "http://localhost:8080/mase2-project/rest/failureclass";
var rootUrlUe = "http://localhost:8080/mase2-project/rest/ue";
var rootUrlBaseData = "http://localhost:8080/mase2-project/rest/basedatas";
var rootUrlEventCause = "http://localhost:8080/mase2-project/rest/eventcauses";
var rootUrlImportData = "http://localhost:8080/mase2-project/rest/importdata/all";
var rootUrlImportBaseData = "http://localhost:8080/mase2-project/rest/importdata/basedata";

$('document').ready(function(){
	
	$('.card-header').html("Network Data AnalyticsSS");
	$('.content-wrapper').css("background", "rgb(0,0,0)");
//	{
//		  overflow-x: hidden;
//		  background: rgb(180,180,180);
//		}
});
// ///////////////////// Dashboard /////////////////////
function showDashboard() {
	cleenAllElements();
	$('.card-header').html("Network Data Analytics");
	$('.content-wrapper').css("background", "rgb(180,180,180)");

}
// ///////////////////// Import Tables /////////////////////
var importData = function() {
	$.ajax({
		type : 'GET',
		url : rootUrlImportData,
		dataType : "json",
		success : renderPopUp
	});
};
var importBaseData = function() {
	$.ajax({
		type : 'GET',
		url : rootUrlImportBaseData,
		dataType : "json",
		success : renderPopUp
	});
};
var renderPopUp = function(data) {
		
		console.log(data[0]);
		console.log(data[1]);
	

	$('.card-header') 
			.html(
					'<div class="alert alert-success alert-dismissable">'
							+ '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>'
							+ '<strong>Success!</strong> '+data[0]+ ' rows were imported. '
							+ data[1]+ ' rows were excluded.'
							+ ' See log file for details</div> ');
	cleenAllElements();

};

function importTables() {
	importData();
}

function importBaseDataTable() {
	importBaseData();
}

// /////////////////////Tables /////////////////////
var findAllFailureClass = function() {
	$.ajax({
		type : 'GET',
		url : rootUrlFailureClass,
		dataType : "json",
		success : renderListFailureClass
	});
};

var findAllUe = function() {
	$.ajax({
		type : 'GET',
		url : rootUrlUe,
		dataType : "json",
		success : renderListUe
	});
};

var findAllEventCause = function() {
	$.ajax({
		type : 'GET',
		url : rootUrlEventCause,
		dataType : "json",
		success : renderListEventCause
	});
};
var findAllBaseData = function() {
	$.ajax({
		type : 'GET',
		url : rootUrlBaseData,
		dataType : "json",
		success : renderListBaseData
	});
};

var findAllMcc_Mnc = function() {
	$.ajax({
		type : 'GET',
		url : rootUrlMcc_Mnc,
		dataType : "json",
		success : renderListMcc_Mnc
	});
};

function showTabelMcc_Mnc() {
	findAllMcc_Mnc();
}

function showTabelFailureClass() {
	findAllFailureClass();
}

function showTabelUe() {
	findAllUe();
}

function showTableEventcauses() {
	findAllEventCause();
}

function showTableBasedatas() {
	findAllBaseData();
}
function cleenAllElements(){
	clearElement('tableHeader');
	clearElement('tableFooter');
	clearElement('tableBody');
	
}
function clearElement(id) {
	$('.content-wrapper').css("background", "rgb(255,255,255)");
	console.log(id);
	if (id !== null) {
		document.getElementById(id).innerHTML = "";
	}
}

var renderListMcc_Mnc = function(data) {
	cleenAllElements();

	$('.card-header')
			.html(
					'<i class="fa fa-table"></i> <span id="tableTitle" >Mcc Mnn Table</span>');

	$('#tableHeader').append(
			"<th>Mcc</th>" + "<th>Mnc</th>" + "<th>Country</th>"
					+ "<th>Operator</th>");
	$('#tableFooter').append(
			"<th>Mcc</th>" + "<th>Mnc</th>" + "<th>Country</th>"
					+ "<th>Operator</th>");

	$.each(data, function(index, mcc_mnc) {
		$('#tableBody').append(
				'<tr><td>' + mcc_mnc.id.mcc + '</td><td>' + mcc_mnc.id.mnc
						+ '</td><td>' + mcc_mnc.country + '</td><td>'
						+ mcc_mnc.operator + '</td></tr>');
	});

	$('#example').DataTable({
		destroy : true,
		paging : false,
		searching : false
	});
	document.getElementById('example_info').setAttribute("style",
			"display:none");
};

var renderListUe = function(data) {
	$('.card-header')
			.html(
					'<i class="fa fa-table"></i> <span id="tableTitle">Ue Table</span>');

	cleenAllElements();
	$('#tableHeader').append(
			"<th>TAC</th>" + "<th>Marketing Name</th>"
					+ "<th>Manufacturer</th>" + "<th>Access Capability</th>"
					+ "<th>Model</th>" + "<th>Vendor Name</th>"
					+ "<th>UE Type</th>" + "<th>OS</th>"
					+ "<th>Input Type</th>");

	$('#tableFooter').append(
			"<th>TAC</th>" + "<th>Marketing Name</th>"
					+ "<th>Manufacturer</th>" + "<th>Access Capability</th>"
					+ "<th>Model</th>" + "<th>Vendor Name</th>"
					+ "<th>UE Type</th>" + "<th>OS</th>"
					+ "<th>Input Type</th>");

	$.each(data, function(index, ue) {
		$('#tableBody').append(
				'<tr><td>' + ue.tac + '</td><td>' + ue.marketingName
						+ '</td><td>' + ue.manufacturer + '</td><td>'
						+ ue.accessCapability + '</td><td>' + ue.model
						+ '</td><td>' + ue.vendorName + '</td><td>' + ue.ueType
						+ '</td><td>' + ue.os + '</td><td>' + ue.inputType
						+ '</td></tr>');

	});
	$('#example').DataTable({
		destroy : true,
		paging : false,
		searching : false
	});
	document.getElementById('example_info').setAttribute("style",
			"display:none");

};
var renderListFailureClass = function(data) {
	$('.card-header')
			.html(
					'<i class="fa fa-table"></i> <span id="tableTitle">Failure Class Table</span>');

	cleenAllElements();

	$('#tableHeader').append("<th>Failure Class</th>" + "<th>Description</th>");
	$('#tableFooter').append("<th>Failure Class</th>" + "<th>Description</th>");

	$.each(data, function(index, failure) {
		$('#tableBody').append(
				'<tr><td>' + failure.failureClass + '</td><td>'
						+ failure.description + '</td></tr>');
	});

	$('#example').DataTable({
		destroy : true,
		paging : false,
		searching : false
	});
	document.getElementById('example_info').setAttribute("style",
			"display:none");
}

var renderListEventCause = function(data) {
	$('.card-header')
			.html(
					'<i class="fa fa-table"></i> <span id="tableTitle">Event Cause Table</span>');

	cleenAllElements();;

	$('#tableHeader').append(
			"<th>Event Code</th>" + "<th>Event ID</th>"
					+ "<th>Description</th>");
	$('#tableFooter').append(
			"<th>Event Code</th>" + "<th>Event ID</th>"
					+ "<th>Description</th>");

	$.each(data, function(index, event_cause) {
		$('#tableBody').append(
				'<tr><td>' + event_cause.id.eventCode + '</td><td>'
						+ event_cause.id.eventId + '</td><td>'
						+ event_cause.description + '</td></tr>');
	});

	$('#example').DataTable({
		destroy : true,
		paging : false,
		searching : false
	});
	document.getElementById('example_info').setAttribute("style",
			"display:none");
}

// //////////////////////////
var renderListBaseData = function(data) {
	$('.card-header')
			.html(
					'<i class="fa fa-table"></i> <span id="tableTitle">Base Data Table</span>');

	cleenAllElements();

	$('#tableHeader').append(
			"<th>Id</th>" + "<th>Date-Time</th>" + "<th>Event Id</th>"

			+ "<th>Failure Class</th>" + "<th>Ue type</th>" + "<th>Market</th>"

			+ "<th>Operator</th>" + "<th>Cell Id</th>" + "<th>Duration</th>"

			+ "<th>Cause Code</th>" + "<th>neVersion</th>" + "<th>imsi</th>"

			+ "<th>hier3Id</th>" + "<th>hier32Id</th>" + "<th>hier321Id</th>");

	$('#tableFooter').append(
			"<th>Id</th>" + "<th>Date-Time</th>" + "<th>Event Id</th>"

			+ "<th>Failure Class</th>" + "<th>Ue type</th>" + "<th>Market</th>"

			+ "<th>Operator</th>" + "<th>Cell Id</th>" + "<th>Duration</th>"

			+ "<th>Cause Code</th>" + "<th>neVersion</th>" + "<th>imsi</th>"

			+ "<th>hier3Id</th>" + "<th>hier32Id</th>" + "<th>hier321Id</th>");

	$.each(data, function(index, base_data) {
		$('#tableBody').append(
				'<tr><td>' + base_data.baseDataId + '</td><td>'
						+ base_data.dateTime + '</td><td>'
						+ base_data.eventCause.id.eventId + '</td><td>'

						+ base_data.failureClassBean.failureClass + '</td><td>'
						+ base_data.ue.tac + '</td><td>'
						+ base_data.mccMnc.id.mcc + '</td><td>'// market mcc

						+ base_data.mccMnc.id.mnc + '</td><td>'// operator mnc
						+ base_data.cellId + '</td><td>' + base_data.duration
						+ '</td><td>'

						+ base_data.eventCause.id.eventCode + '</td><td>'
						+ base_data.neVersion + '</td><td>' + base_data.imsi
						+ '</td><td>'

						+ base_data.hier3Id + '</td><td>' + base_data.hier32Id
						+ '</td><td>' + base_data.hier321Id + '</td></tr>');
	});
	$('#example').DataTable({
		destroy : true,
		paging : false,
		searching : false
	});
	document.getElementById('example_info').setAttribute("style",
			"display:none");
}
