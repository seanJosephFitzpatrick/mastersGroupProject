var rootUrlMcc_Mnc = "http://localhost:8080/mase2-project/rest/mcc_mnc";
var rootUrlFailureClass = "http://localhost:8080/mase2-project/rest/failureclass";
var rootUrlUe = "http://localhost:8080/mase2-project/rest/ue";
var rootUrlEventCause = "http://localhost:8080/mase2-project/rest/eventcauses";



function showTableEventcauses() {
	showEventcausesTable();
	$.ajax({
		type : 'GET',
		url : rootUrlEventCause,
		dataType : "json",
		headers : {
			'Authorization' : 'Basic '+ sessionStorage.getItem("email")+":"+sessionStorage.getItem("password")
		},
		success : function(data) {
			$('#eventcauses_table').DataTable({
				data : data,
				columns : [ {
					data : "id.eventCode"
				}, {
					data : "id.eventId"
				} , {
					data : "description"
				}]
			});
		}
	});
}

function showEventcausesTable() {
	$('#wrapper').html(	''
			+ '	<i class="fa fa-table"></i> <span id="tableTitle">All Ue Table</span>'
			+ '	<div class="table-responsive ">'
			+ '		<table id="eventcauses_table" class="table table-bordered display" cellspacing="0" width="100%">'
			+ '			<thead>'
			+ '				<th>Event Code</th><th>Event ID</th><th>Description</th>'     
			+ '			</thead>'
			+ '			<tfoot>'
			+ '				<th>Event Code</th><th>Event ID</th><th>Description</th>'     
			+ '			</tfoot>'
			+ '		</table>' 
			+ '	<div>');
}

function showTabelUe() {
	showUeTable();
	$.ajax({
		type : 'GET',
		url : rootUrlUe,
		dataType : "json",
		headers : {
			'Authorization' : 'Basic '+ sessionStorage.getItem("email")+":"+sessionStorage.getItem("password")
		},
		success : function(data) {
			$('#ue_table').DataTable({
				data : data,
				columns : [ {
					data : "tac"
				}, {
					data : "marketingName"
				} , {
					data : "manufacturer"
				}, {
					data : "accessCapability"
				}, {
					data : "model"
				}, {
					data : "vendorName"
				}, {
					data : "ueType"
				}, {
					data : "os"
				},{
					data : "inputType"
				} ]
			});
		}
	});
}
// ue.tac + '</td><td>' + ue.marketingName + '</td><td>' + ue.manufacturer + '</td><td>' + ue.accessCapability + '</td><td>' + ue.model + '</td><td>' + ue.vendorName
// + '</td><td>' + ue.ueType + '</td><td>' + ue.os + '</td><td>' + ue.inputType

function showUeTable() {
	$('#wrapper').html(	''
			+ '	<i class="fa fa-table"></i> <span id="tableTitle">All Ue Table</span>'
			+ '	<div class="table-responsive ">'
			+ '		<table id="ue_table" class="table table-bordered display" cellspacing="0" width="100%">'
			+ '			<thead>'
			+ '				<th>TAC</th><th>Marketing Name</th><th>Manufacturer</th><th>Access Capability</th><th>Model</th><th>Vendor Name</th><th>UE Type</th><th>OS</th><th>Input Type</th>'
			+ '			</thead>'
			+ '			<tfoot>'
			+ '				<th>TAC</th><th>Marketing Name</th><th>Manufacturer</th><th>Access Capability</th><th>Model</th><th>Vendor Name</th><th>UE Type</th><th>OS</th><th>Input Type</th>'
			+ '			</tfoot>'
			+ '		</table>' 
			+ '	<div>');
}

function showTabelFailureClass() {
	showTableFailureClass();
	$.ajax({
		type : 'GET',
		url : rootUrlFailureClass,
		dataType : "json",
		headers : {
			'Authorization' : 'Basic '+ sessionStorage.getItem("email")+":"+sessionStorage.getItem("password")
		},
		success : function(data) {
			$('#failure_table').DataTable({
				data : data,
				columns : [ {
					data : "failureClass"
				}, {
					data : "description"
				} ]
			});
			// {"failureClass":"0","description":"EMERGENCY"}
		}
	});
}

function showTableFailureClass() {
	$('#wrapper').html(	''
			+ '	<i class="fa fa-table"></i> <span id="tableTitle" >Failure Class Table</span>'
			+ '	<div class="table-responsive ">'
			+ '		<table id="failure_table" class="table table-bordered display" cellspacing="0" width="100%">'
			+ '			<thead id="tableHeader"><th>Failure Class</th><th>Description</th></thead>'
			+ '			<tfoot id="tableFooter"><th>Failure Class</th><th>Description</th></tfoot>'
			+ '		</table>' 
			+ '	<div>');
}

function showTabelMcc_Mnc() {
	showTableMccMnc();
	$.ajax({
		type : 'GET',
		url : rootUrlMcc_Mnc,
		dataType : "json",
		headers : {
			'Authorization' : 'Basic '+ sessionStorage.getItem("email")+":"+sessionStorage.getItem("password")
		},
		success : function(data) {
			$('#mccmnc_table').DataTable({
				data : data,
				columns : [ {
					data : "id.mcc"
				}, {
					data : "id.mnc"
				}, {
					data : "country"
				}, {
					data : "operator"
				} ]
			});
			// {id: {mcc: "238", mnc: "2"}, country: "Denmark", operator:
			// "Sonofon DK "}
		}
	});
}

function showTableMccMnc() {
	$('#wrapper').html(	''
			+ '	<i class="fa fa-table"></i> <span id="tableTitle" >Mcc Mnc Table</span>'
			+ '	<div class="table-responsive ">'
			+ '		<table id="mccmnc_table" class="table table-bordered display" cellspacing="0" width="100%">'
			+ '			<thead id="tableHeader"><th>Mcc</th><th>Mnc</th><th>Country</th><th>Operator</th></thead>'
			+ '			<tfoot id="tableFooter"><th>Mcc</th><th>Mnc</th><th>Country</th><th>Operator</th></tfoot>'
			+ '		</table>' 
			+ '	<div>');
}