// JavaScript Document

//mcc_mnc table
var rootUrlMcc_Mnc = "http://localhost:8080/mase2-project/rest/mcc_mnc";
var rootUrlFailureClass = "http://localhost:8080/mase2-project/rest/failureclass";
var rootUrlUe = "http://localhost:8080/mase2-project/rest/ue";
var rootUrlBaseData = "http://localhost:8080/mase2-project/rest/basedatas";
var rootUrlEventCause = "http://localhost:8080/mase2-project/rest/eventcauses";
var rootUrlImportData = "http://localhost:8080/mase2-project/rest/importdata/all";

/////////////////////// Import Tables /////////////////////
var importData = function() {
	$.ajax({
		type : 'GET',
		url : rootUrlFailureClass,
		dataType : "json",
		success : renderPopUp
	});
};
var renderPopUp = function(data) {
	$('.container-fluid').html('<div class="alert alert-success alert-dismissable">'
	  + '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>'
	  + '<strong>Success!</strong> Indicates a successful or positive action.'
	  + '</div> ');
	
	alert('<div class="alert alert-success alert-dismissable">'
			  + '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>'
			  + '<strong>Success!</strong> Indicates a successful or positive action.'
			  + '</div> ');
	
	console.log("import");
	
}

function importTables() {
	renderPopUp();
}

///////////////////////Tables /////////////////////
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

function clearElement(id) {
	document.getElementById(id).innerHTML = "";
}

var renderListMcc_Mnc = function(data) {
	$('.card-header').html('<i class="fa fa-table"></i> <span id="tableTitle" >Mcc Mnn Table</span>');

	clearElement('tableHeader');
	clearElement('tableFooter');
	clearElement('tableBody');
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
		searching: false
	});
	document.getElementById('example_info').setAttribute("style", "display:none");
}

var renderListUe = function(data) {
	$('.card-header').html('<i class="fa fa-table"></i> <span id="tableTitle">Ue Table</span>');

	clearElement('tableHeader');
	clearElement('tableFooter');
	clearElement('tableBody');
	$('#tableHeader').append(
			"<th>TAC</th>" + "<th>Access Capability</th>"
					+ "<th>Manufacturer</th>" + "<th>Marketing Name</th>");
	$('#tableFooter').append(
			"<th>TAC</th>" + "<th>Access Capability</th>"
					+ "<th>Manufacturer</th>" + "<th>Marketing Name</th>");

	$.each(data, function(index, ue) {
		$('#tableBody').append(
				'<tr><td>' + ue.tac + '</td><td>' + ue.accessCapability
						+ '</td><td>' + ue.manufacturer + '</td><td>'
						+ ue.marketingName + '</td></tr>');

	});
	$('#example').DataTable({
		destroy : true,
		paging : false,
		searching: false
	});
	document.getElementById('example_info').setAttribute("style", "display:none");

}
var renderListFailureClass = function(data) {
	$('.card-header').html('<i class="fa fa-table"></i> <span id="tableTitle">Failure Class Table</span>');

	clearElement('tableHeader');
	clearElement('tableFooter');
	clearElement('tableBody');

	$('#tableHeader').append(
			"<th>Failure Class</th>" + "<th>Description</th>"
					+ "<th>Base Data</th>");
	$('#tableFooter').append(
			"<th>Failure Class</th>" + "<th>Description</th>"
					+ "<th>Base Data</th>");
	
	$.each(data, function(index, failure) {
		$('#tableBody').append(
				  '<tr><td>' + failure.failureClass 
				+ '</td><td>' + failure.description 
				+ '</td><td>' + failure.baseData
				+ '</td></tr>');
	});
	
	$('#example').DataTable({
		destroy : true,
		paging : false,
		searching: false
	});
	document.getElementById('example_info').setAttribute("style", "display:none");
}

var renderListEventCause = function(data) {
	$('.card-header').html('<i class="fa fa-table"></i> <span id="tableTitle">Event Cause Table</span>');

	clearElement('tableHeader');
	clearElement('tableFooter');
	clearElement('tableBody');

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
		searching: false
	});
	document.getElementById('example_info').setAttribute("style", "display:none");
}

// //////////////////////////
var renderListBaseData = function(data) {
	$('.card-header').html('<i class="fa fa-table"></i> <span id="tableTitle">Base Data Table</span>');

	clearElement('tableHeader');
	clearElement('tableFooter');
	clearElement('tableBody');

	$('#tableHeader').append(
			 "<th>Id</th>" 
			+ "<th>Date-Time</th>"
			+ "<th>Event Id</th>"
			
			+ "<th>Failure Class</th>"
			+ "<th>Ue type</th>"
			+ "<th>Market</th>"
			
			+ "<th>Operator</th>"
			+ "<th>Cell Id</th>"
			+ "<th>Duration</th>"
			
			+ "<th>Cause Code</th>"
			+ "<th>neVersion</th>"
			+ "<th>imsi</th>"
			
			+ "<th>hier3Id</th>"
			+ "<th>hier32Id</th>"
			+ "<th>hier321Id</th>");
	
	$('#tableFooter').append(
			  "<th>Id</th>" 
			+ "<th>Date-Time</th>"
			+ "<th>Event Id</th>"
			
			+ "<th>Failure Class</th>"
			+ "<th>Ue type</th>"
			+ "<th>Market</th>"
			
			+ "<th>Operator</th>"
			+ "<th>Cell Id</th>"
			+ "<th>Duration</th>"
			
			+ "<th>Cause Code</th>"
			+ "<th>neVersion</th>"
			+ "<th>imsi</th>"
			
			+ "<th>hier3Id</th>"
			+ "<th>hier32Id</th>"
			+ "<th>hier321Id</th>");
	
	$.each(data, function(index, base_data) {
		$('#tableBody').append(
				'<tr><td>' 
						+ base_data.baseDataId + '</td><td>'
						+ base_data.dateTime + '</td><td>'
						+ base_data.eventCause.id.eventId + '</td><td>' 
						
						+ base_data.failureClassBean.failureClass + '</td><td>' 
						+ base_data.ue.tac + '</td><td>' 
						+ base_data.mccMnc.id.mcc + '</td><td>'//market mcc

						+ base_data.mccMnc.id.mnc + '</td><td>'//operator mnc
						+ base_data.cellId + '</td><td>'
						+ base_data.duration + '</td><td>' 

						+ base_data.eventCause.id.eventCode + '</td><td>' 
						+ base_data.neVersion + '</td><td>'
						+ base_data.imsi + '</td><td>' 
						
						+ base_data.hier3Id + '</td><td>'
						+ base_data.hier32Id + '</td><td>'
						+ base_data.hier321Id  
						+ '</td></tr>');
	});
	$('#example').DataTable({
		destroy : true,
		paging : false,
		searching: false
	});
	document.getElementById('example_info').setAttribute("style", "display:none");
}


