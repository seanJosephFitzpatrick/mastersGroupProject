//JavaScript Document

var rootUrlIMSIQuery = "http://localhost:8080/mase2-project/rest/basedatas/csr/";
var rootUrlFailuresWithinTimePeriodQuery = "http://localhost:8080/mase2-project/rest/basedatas/se/QueryDates?";
var rootUrlNumFailuresForModel = "http://localhost:8080/mase2-project/rest/basedatas/se/";
var rootUrlIMSIFailuresWithinTimePeriod = "http://localhost:8080/mase2-project/rest/basedatas/fc/";
var rootUrlSumDurationAndCountFailures = "http://localhost:8080/mase2-project/rest/basedatas/nme/query?StartDate=";
var rootUrlTop10Failures = "http://localhost:8080/mase2-project/rest/basedatas/nme/querytopten?StartDate=";
var rootUrlTop10IMSIs = "http://localhost:8080/mase2-project/rest/basedatas/nme/querytoptenimsi?StartDate=";
var rootUrlUniqueIdAndCauseCodeForModel = "http://localhost:8080/mase2-project/rest/basedatas/nme/";
var rootUrlUniqueCauseCodeForIMSI = "http://localhost:8080/mase2-project/rest/basedatas/csr/unique/";
var rootCountFailures = "http://localhost:8080/mase2-project/rest/basedatas/nme/countfailures"

var numberOfFailures=0;
var rootUrlIMSIForGivenFailureCauseClass = "http://localhost:8080/mase2-project/rest/basedatas/nme/querygivenfailurecauseclass/";

$('document').ready(function() {
	$('.card-header').html("Network Data Analytics");

});
// ///////////////////// Dashboard /////////////////////

// ///////////////////Dropdown modal Button//////////////////
$(function() {

	$(".dropdown-menu a").click(function() {

		$("#dropdownMenuButton:first-child").text($(this).text());
		$("#dropdownMenuButton:first-child").val($(this).text());
	});

});

var countFailures = function(data2) {

	$.ajax({
		type : 'GET',
		url : rootCountFailures,
		dataType : "json",
		headers : {
			'Authorization' : 'Basic ' + sessionStorage.getItem("email") + ":"
					+ sessionStorage.getItem("password")
		},
		success : function(data) {
			numberOfFailures=data[0];
		},
		complete : function(){
			drawGraph(data2);
		}
	});

};

// /////////////////////Tables /////////////////////
var imsiDataRequest = function(imsi) {
	$.ajax({
		type : 'GET',
		url : rootUrlIMSIQuery + imsi,
		dataType : "json",
		headers : {
			'Authorization' : 'Basic ' + sessionStorage.getItem("email") + ":"
					+ sessionStorage.getItem("password")
		},
		success : function(data) {
			userTable = $('#ImsiDataTable').DataTable({
				responsive: true,
				fixedHeader: true,
				dom: 'Bfrtlip',
				buttons:{
					buttons: [
					            {extend: 'copy',className: 'btn btn-primary'},
					            {extend: 'excel',className: 'btn btn-primary'},
					            {extend: 'pdf',className: 'btn btn-primary'},
					            {extend: 'print',className: 'btn btn-primary'}
					            
					            ]},
				"columnDefs": [
				               { "width": "50%","targets": [0,1] }

				             ],
				data : data,
				columns : [ {
					data : "id.eventId"
				}, {
					data : "id.eventCode"
				}, ]
			});
			
		}
	});
};
var uniqueImsiDataRequest = function(imsi) {
	$.ajax({
		type : 'GET',
		url : rootUrlUniqueCauseCodeForIMSI + imsi,
		dataType : "json",
		headers : {
			'Authorization' : 'Basic ' + sessionStorage.getItem("email") + ":"
					+ sessionStorage.getItem("password")
		},
		success : function(data) {
			userTable = $('#uniqueImsiDataTable').DataTable({
				responsive: true,
				fixedHeader: true,
				dom: 'Bfrtlip',
				buttons:{
					buttons: [
					            {extend: 'copy',className: 'btn btn-primary'},
					            {extend: 'excel',className: 'btn btn-primary'},
					            {extend: 'pdf',className: 'btn btn-primary'},
					            {extend: 'print',className: 'btn btn-primary'}
					            
					            ]},
				data : data,
				columns : [ {
					data : "imsi"
				} ]
			});
		}
	});
};
var DateDataRequest = function(date1, date2) {
	$.ajax({
		type : 'GET',
		url : rootUrlFailuresWithinTimePeriodQuery + "StartDate=" + date1
				+ "&EndDate=" + date2,
		dataType : "json",
		headers : {
			'Authorization' : 'Basic ' + sessionStorage.getItem("email") + ":"
					+ sessionStorage.getItem("password")
		},
		success : function(data) {

			userTable = $('#DateDataTable').DataTable({
				responsive: true,
				fixedHeader: true,
				dom: 'Bfrtlip',
				buttons:{
					buttons: [
					            {extend: 'copy',className: 'btn btn-primary'},
					            {extend: 'excel',className: 'btn btn-primary'},
					            {extend: 'pdf',className: 'btn btn-primary'},
					            {extend: 'print',className: 'btn btn-primary'}
					            
					            ]},
				data : data,
				columns : [ {
					data : "imsi"
				} ]
			});

		}
	});
};
var countFailuresDataRequest = function(model, date1, date2) {
	$.ajax({
		type : 'GET',
		url : rootUrlNumFailuresForModel + model + "?StartDate=" + date1
				+ "&EndDate=" + date2,
		dataType : "json",
		headers : {
			'Authorization' : 'Basic ' + sessionStorage.getItem("email") + ":"
					+ sessionStorage.getItem("password")
		},
		success : function(data) {

			userTable = $('#CountFailuresDataTable').DataTable({
				responsive: true,
				fixedHeader: true,
				dom: 'Bfrtlip',
				buttons:{
					buttons: [
					            {extend: 'copy',className: 'btn btn-primary'},
					            {extend: 'excel',className: 'btn btn-primary'},
					            {extend: 'pdf',className: 'btn btn-primary'},
					            {extend: 'print',className: 'btn btn-primary'}
					            
					            ]},
				data : data,
				columns : [ {
					data : "failureCount"
				} ]

			});

		}
	});
};
var countAndSumDataRequest = function(data1, data2) { /////////////////////////////////////////////////
	$.ajax({
		type : 'GET',
		url : rootUrlSumDurationAndCountFailures + data1 + "&EndDate=" + data2,
		dataType : "json",
		headers : {
			'Authorization' : 'Basic ' + sessionStorage.getItem("email") + ":" + sessionStorage.getItem("password")
		},
		success : function(data) {


				$('#SumAndCountDataTable').DataTable({
				responsive: true,
				fixedHeader: true,
				dom: 'Bfrtlip',
				buttons:{
					buttons: [
					            {extend: 'copy',className: 'btn btn-primary'},
					            {extend: 'excel',className: 'btn btn-primary'},
					            {extend: 'pdf',className: 'btn btn-primary'},
					            {extend: 'print',className: 'btn btn-primary'}
					            
					            ]},
				"columnDefs": [
				               { "width": "33%","targets": [0,1,2] }

				],
				"columnDefs" : [ {
					"width" : "33%",
					"targets" : [ 0, 1, 2 ]
				}

				],
				data : data,
				columns : [ {
					data : "imsi"
				}, {
					data : "count"
				}, {
					data : "sum"
				} ]
			});

		}
	});
};
var TopTenDataRequest = function(data1, data2) {
	$.ajax({
		type : 'GET',
		url : rootUrlTop10Failures + data1 + "&EndDate=" + data2,
		dataType : "json",
		headers : {
			'Authorization' : 'Basic ' + sessionStorage.getItem("email") + ":"
					+ sessionStorage.getItem("password")
		},
		success : function(data) {
			

			userTable = $('#TopTenDataTable').DataTable({
				responsive: true,
				fixedHeader: true,
				dom: 'Bfrtlip',
				buttons:{
					buttons: [
					            {extend: 'copy',className: 'btn btn-primary'},
					            {extend: 'excel',className: 'btn btn-primary'},
					            {extend: 'pdf',className: 'btn btn-primary'},
					            {extend: 'print',className: 'btn btn-primary'}
					            
					            ]},
				"columnDefs": [
				               { "width": "25%","targets": [0,1,2,3] }

				             ],
				data : data,
				columns : [ {
					data : "country"
				}, {
					data : "operator"
				}, {
					data : "cellId"
				}, {
					data : "count"
				} ],
				"order" : [ [ 3, "desc" ] ]
			});
			countFailures(data);
			
		}
	});
};
var TopTenIMSIsDataRequest = function(data1, data2) {
	$.ajax({
		type : 'GET',
		url : rootUrlTop10IMSIs + data1 + "&EndDate=" + data2,
		dataType : "json",
		headers : {
			'Authorization' : 'Basic ' + sessionStorage.getItem("email") + ":"
					+ sessionStorage.getItem("password")
		},
		success : function(data) {

			userTable = $('#TopTenIMSIDataTable').DataTable({
				responsive: true,
				fixedHeader: true,
				dom: 'Bfrtlip',
				buttons:{
					buttons: [
					            {extend: 'copy',className: 'btn btn-primary'},
					            {extend: 'excel',className: 'btn btn-primary'},
					            {extend: 'pdf',className: 'btn btn-primary'},
					            {extend: 'print',className: 'btn btn-primary'}
					            
					            ]},
				
				data : data,
				columns : [ {
					data : "imsi"
				}, {
					data : "count"
				} ],
				"order" : [ [ 1, "count" ] ]
			});
		}
	});
};
var countFailuresForIMSIDataRequest = function(imsi, date1, date2) {
	$.ajax({
		type : 'GET',
		url : rootUrlIMSIFailuresWithinTimePeriod + imsi + "?StartDate="
				+ date1 + "&EndDate=" + date2,
		dataType : "json",
		headers : {
			'Authorization' : 'Basic ' + sessionStorage.getItem("email") + ":"
					+ sessionStorage.getItem("password")
		},
		success : function(data) {

			userTable = $('#CountFailuresDataTable').DataTable({
				responsive: true,
				fixedHeader: true,
				dom: 'Bfrtlip',
				buttons:{
					buttons: [
					            {extend: 'copy',className: 'btn btn-primary'},
					            {extend: 'excel',className: 'btn btn-primary'},
					            {extend: 'pdf',className: 'btn btn-primary'},
					            {extend: 'print',className: 'btn btn-primary'}
					            
					            ]},
				data : data,
				columns : [ {
					data : "failureCount"
				} ]

			});

		}
	});
};
var uniqueEventAndCauseDataRequest = function(model) {
	$.ajax({
		type : 'GET',
		url : rootUrlUniqueIdAndCauseCodeForModel + model,
		dataType : "json",
		headers : {
			'Authorization' : 'Basic ' + sessionStorage.getItem("email") + ":"
					+ sessionStorage.getItem("password")
		},
		success : function(data) {

			userTable = $('#UniqueEventAndCauseTable').DataTable({
				responsive: true,
				fixedHeader: true,
				dom: 'Bfrtlip',
				buttons:{
					buttons: [
					            {extend: 'copy',className: 'btn btn-primary'},
					            {extend: 'excel',className: 'btn btn-primary'},
					            {extend: 'pdf',className: 'btn btn-primary'},
					            {extend: 'print',className: 'btn btn-primary'}
					            
					            ]},
				"columnDefs": [
				               { "width": "33%","targets": [0,1,2] }

				             ],
				data : data,
				columns : [ {
					data : "eventCause.id.eventId"
				}, {
					data : "eventCause.id.eventCode"
				}, {
					data : "count"
				}, ],
				"order" : [ [ 2, "desc" ] ]

			});

		}
	});
};
var imsiForFailureClassRequest = function(failure) {
	$.ajax({
		type : 'GET',
		url : rootUrlIMSIForGivenFailureCauseClass + failure,
		dataType : "json",
		headers : {
			'Authorization' : 'Basic ' + sessionStorage.getItem("email") + ":"
					+ sessionStorage.getItem("password")
		},
		success : function(data) {
			console.log(data);
			userTable = $('#ImsibyFailureClassDataTable').DataTable({
				responsive: true,
				fixedHeader: true,
				dom: 'Bfrtlip',
				buttons:{
					buttons: [
					            {extend: 'copy',className: 'btn btn-primary'},
					            {extend: 'excel',className: 'btn btn-primary'},
					            {extend: 'pdf',className: 'btn btn-primary'},
					            {extend: 'print',className: 'btn btn-primary'}
					            
					            ]},
				data : data,
				columns : [ {
					data : "imsi"
				}, ]
			});
		}
	});
};
function retrieveIMSI() {
	// findAllIMSIData(document.getElementById('imsi').value);
	showLoading();
	imsi = document.getElementById('imsi').value;
	imsiDataRequest(document.getElementById('imsi').value);
	showImsiDataTable(imsi);
//	showImsiGraph();
	imsiDataRequestGraph();
	
}
function retrieveUniqueIMSI() {
	
	showLoading();
	uniqueImsiDataRequest(document.getElementById('imsi').value);
	showUniqueImsiDataTable(document.getElementById('imsi').value);
}
function retrieveDates() {
	showLoading();
	DateDataRequest(document.getElementById('date_timepicker_start').value,
			document.getElementById('date_timepicker_end').value);
	showDateDataTable(document.getElementById('date_timepicker_start').value,
			document.getElementById('date_timepicker_end').value);
}
function retrieveDatesNME() { ///////////////
	showLoading();

	var date1 = document.getElementById('date_timepicker_start').value;
	var date2 = document.getElementById('date_timepicker_end').value;
	countAndSumDataRequest(date1,date2);
	showSumAndCountDataTable(date1,date2);
	showSumAndCountGraph(date1, date2);

}
function retrieveDatesTopTen() {
	showLoading();
	TopTenDataRequest(document.getElementById('date_timepicker_start').value,
			document.getElementById('date_timepicker_end').value);
	showTopTenDataTable(document.getElementById('date_timepicker_start').value,
			document.getElementById('date_timepicker_end').value);
}
function retrieveModelAndDates() {
	showLoading();
	countFailuresDataRequest(document.getElementById('model').value, document
			.getElementById('date_timepicker_start').value, document
			.getElementById('date_timepicker_end').value);
	showCountFailuresModelDataTable(document.getElementById('model').value, document
			.getElementById('date_timepicker_start').value, document
			.getElementById('date_timepicker_end').value);
}

function retrieveIMSIAndDates() {
	showLoading();
	countFailuresForIMSIDataRequest(document.getElementById('imsi').value,
			document.getElementById('date_timepicker_start').value, document
					.getElementById('date_timepicker_end').value);
	showCountFailuresDataTable(document.getElementById('imsi').value,
			document.getElementById('date_timepicker_start').value, document
			.getElementById('date_timepicker_end').value);
}
function retrieveModel() {
	showLoading();
	uniqueEventAndCauseDataRequest(document.getElementById('model').value);
	showUniqueEventAndCauseTable(document.getElementById('model').value);
}
function retrieveDatesTopTenIMSIs() {
	showLoading();
	//need to change this back after testing 
	var date1 = document.getElementById('date_timepicker_start').value;
	var date2 = document.getElementById('date_timepicker_end').value;
	TopTenIMSIsDataRequest(date1,date2);
	showTopTenIMSIsDataTable(date1.date2);
	showTop10IMSIsGraph(date1, date2);

}
function retrieveIMSIbyFailureClass() {
	showLoading();
	imsiForFailureClassRequest(document.getElementById('failure').value);
	showImsibyFailureClassTable(document.getElementById('failure').value);
}

function showModelModal(){
	$("#exampleModalLongTitle").text("Failures for Model");
	$('#csrIMSIQueryModal').find('.modal-body').html('<div class="row">'
			+'<div class="form-group centermargin col-lg-12">'
			+ '<label for="model">Model</label>'
			+ '<input type="text" class="form-control" id="model" placeholder="Model">'
			+ '</div>'
			+ '</div>'
			+ '<div class="row">'
			+'<div class="form-group centermargin col-md-6">'
			+ '<label class="labelclass" for="date_timepicker_start">Start Date</label>'
			+ '<input type="text" class="form-control" id="date_timepicker_start" placeholder="Start Date">'
			+'</div>'
			+'<div class="form-group centermargin col-md-6">'
			+ '<label class="labelclass" for="date_timepicker_end">End Date</label>'
			+ '<input type="text" class="form-control" id="date_timepicker_end" placeholder="End Date">'
			+ '</div>'
		+'</div>');
	$('#csrIMSIQueryModal').find('.modal-footer').html('<button type="button" class="btn btn-secondary"'
		+'data-dismiss="modal">Close</button>'
		+'<button type="button" class="btn btn-primary"'
		+'onclick="retrieveModelAndDates()" id="submitquery" data-dismiss="modal">Submit</button>');
	initializeDatePicker();
	$('#csrIMSIQueryModal').modal('show'); 
	modelautocomplete();
}
function showUniqueModelModal(){
	$("#exampleModalLongTitle").text("Unique Event IDs - Cause Codes for model");
	$('#csrIMSIQueryModal').find('.modal-body').html('<div class="row">'
			+'<div class="form-group centermargin col-lg-12">'
			+ '<label for="model">Model</label>'
			+ '<input type="text" class="form-control" id="model" placeholder="Model">'
			+ '</div>'
			+ '</div>');
	$('#csrIMSIQueryModal').find('.modal-footer').html('<button type="button" class="btn btn-secondary"'
		+'data-dismiss="modal">Close</button>'
		+'<button type="button" class="btn btn-primary"'
		+'onclick="retrieveModel()" id="submitquery" data-dismiss="modal">Submit</button>');
	$('#csrIMSIQueryModal').modal('show'); 
	modelautocomplete();
}
function showTopTenIMSIsModal(){
	$("#exampleModalLongTitle").text("Top 10 IMSIs - Failures for time period");
	$('#csrIMSIQueryModal').find('.modal-body').html('<div class="row">'
			+'<div class="form-group centermargin col-md-6">'
			+ '<label class="labelclass" for="date_timepicker_start">Start Date</label>'
			+ '<input type="text" class="form-control" id="date_timepicker_start" value="2012-01-01" placeholder="Start Date">'
			+'</div>'
			+'<div class="form-group centermargin col-md-6">'
			+ '<label class="labelclass" for="date_timepicker_end">End Date</label>'
			+ '<input type="text" class="form-control" id="date_timepicker_end" value="2018-01-01" placeholder="End Date">'
			+ '</div>'
		+ '</div>');
	$('#csrIMSIQueryModal').find('.modal-footer').html('<button type="button" class="btn btn-secondary"'
		+'data-dismiss="modal">Close</button>'
		+'<button type="button" class="btn btn-primary"'
		+'onclick="retrieveDatesTopTenIMSIs()" id="submitquery" data-dismiss="modal">Submit</button>');
	initializeDatePicker();
	$('#csrIMSIQueryModal').modal('show'); 	
}
function showIMSIModal(){
	$("#exampleModalLongTitle").text("Event IDs - Cause Codes for IMSI");
	$('#csrIMSIQueryModal').find('.modal-body').html('<div class="row">'
		+'<div class="form-group centermargin col-lg-12">'
		+ '<label for="imsi">IMSI:</label>'
		+ '<input type="text" class="form-control" id="imsi" placeholder="IMSI">'
		+ '</div>'
		+'</div>');
	$('#csrIMSIQueryModal').find('.modal-footer').html('<button type="button" class="btn btn-secondary"'
		+'data-dismiss="modal">Close</button>'
		+'<button type="button" class="btn btn-primary"'
		+'onclick="retrieveIMSI()" id="submitquery" data-dismiss="modal">Submit</button>');
	$('#csrIMSIQueryModal').modal('show'); 
	imsiautocomplete();
}
function showUniqueIMSIModal(){
	$("#exampleModalLongTitle").text("Unique Cause Codes for IMSI");
	$('#csrIMSIQueryModal').find('.modal-body').html('<div class="row">'
		+'<div class="form-group centermargin col-lg-12">'
		+ '<label for="imsi">IMSI:</label>'
		+ '<input type="text" class="form-control" id="imsi" placeholder="IMSI">'
		+ '</div>'
		+'</div>');
	$('#csrIMSIQueryModal').find('.modal-footer').html('<button type="button" class="btn btn-secondary"'
		+'data-dismiss="modal">Close</button>'
		+'<button type="button" class="btn btn-primary"'
		+'onclick="retrieveUniqueIMSI()" id="submitquery" data-dismiss="modal">Submit</button>');
	$('#csrIMSIQueryModal').modal('show'); 
	imsiautocomplete();
}
function showNMEModal(){

	$("#exampleModalLongTitle").text("Number of Failures and duration for IMSI");
	$('#csrIMSIQueryModal').find('.modal-body').html('<div class="row">'
			+'<div class="form-group centermargin col-md-6">'
			+ '<label class="labelclass" for="date_timepicker_start">Start Date</label>'
			+ '<input type="text" class="form-control" id="date_timepicker_start" placeholder="Start Date">'
			+'</div>'
			+'<div class="form-group centermargin col-md-6">'
			+ '<label class="labelclass" for="date_timepicker_end">End Date</label>'
			+ '<input type="text" class="form-control" id="date_timepicker_end" placeholder="End Date">'
			+ '</div>'
		+ '</div>');
	$('#csrIMSIQueryModal').find('.modal-footer').html('<button type="button" class="btn btn-secondary"'
		+'data-dismiss="modal">Close</button>'
		+'<button type="button" class="btn btn-primary"'
		+'onclick="retrieveDatesNME()" id="submitquery" data-dismiss="modal">Submit</button>');
	initializeDatePicker();
	$('#csrIMSIQueryModal').modal('show'); 
	
}

function showTopTenModal(){
	$("#exampleModalLongTitle").text("Top 10 Market/Operator/Cell ID combinations with failures");
	$('#csrIMSIQueryModal').find('.modal-body').html('<div class="row">'
			+'<div class="form-group centermargin col-md-6">'
			+ '<label class="labelclass" for="date_timepicker_start">Start Date</label>'
			+ '<input type="text" class="form-control" id="date_timepicker_start" placeholder="Start Date">'
			+'</div>'
			+'<div class="form-group centermargin col-md-6">'
			+ '<label class="labelclass" for="date_timepicker_end">End Date</label>'
			+ '<input type="text" class="form-control" id="date_timepicker_end" placeholder="End Date">'
			+ '</div>'
		+ '</div>');
	$('#csrIMSIQueryModal').find('.modal-footer').html('<button type="button" class="btn btn-secondary"'
		+'data-dismiss="modal">Close</button>'
		+'<button type="button" class="btn btn-primary"'
		+'onclick="retrieveDatesTopTen()" id="submitquery" data-dismiss="modal">Submit</button>');
	initializeDatePicker();
	$('#csrIMSIQueryModal').modal('show'); 	
}

function showDateModal(){
	$("#exampleModalLongTitle").text("IMSI failures during given period");
	$('#csrIMSIQueryModal').find('.modal-body').html('<div class="row">'
			+'<div class="form-group centermargin col-md-6">'
			+ '<label class="labelclass" for="date_timepicker_start">Start Date</label>'
			+ '<input type="text" class="form-control" id="date_timepicker_start" placeholder="Start Date">'
			+'</div>'
			+'<div class="form-group centermargin col-md-6">'
			+ '<label class="labelclass" for="date_timepicker_end">End Date</label>'
			+ '<input type="text" class="form-control" id="date_timepicker_end" placeholder="End Date">'
			+ '</div>'
		+ '</div>');
	$('#csrIMSIQueryModal').find('.modal-footer').html('<button type="button" class="btn btn-secondary"'
		+ 'data-dismiss="modal">Close</button>'
		+ '<button type="button" class="btn btn-primary"'
		+ 'onclick="retrieveDates()" id="submitquery" data-dismiss="modal">Submit</button>');
	initializeDatePicker();
	$('#csrIMSIQueryModal').modal('show');

}

function showIMSIFailureModalGivenTimePeriod(){
	$("#exampleModalLongTitle").text("Count of failures for IMSI");
	$('#csrIMSIQueryModal').find('.modal-body').html('<div class="row">'		
			+'<div class="form-group centermargin col-lg-12">'
			+ '<label for="model">IMSI</label>'
			+ '<input type="text" class="form-control" id="imsi" placeholder="IMSI">'
			+ '</div>'
			+ '</div>'
			+ '<div class="row">'
			+'<div class="form-group centermargin col-md-6">'
			+ '<label class="labelclass" for="date_timepicker_start">Start Date</label>'
			+ '<input type="text" class="form-control" id="date_timepicker_start" placeholder="Start Date">'
			+'</div>'
			+'<div class="form-group centermargin col-md-6">'
			+ '<label class="labelclass" for="date_timepicker_end">End Date</label>'
			+ '<input type="text" class="form-control" id="date_timepicker_end" placeholder="End Date">'
			+ '</div>'
		+ '</div>');
	$('#csrIMSIQueryModal').find('.modal-footer').html('<button type="button" class="btn btn-secondary"'
		+ 'data-dismiss="modal">Close</button>'
		+ '<button type="button" class="btn btn-primary"'
		+ 'onclick="retrieveIMSIAndDates()" id="submitquery" data-dismiss="modal">Submit</button>');
	initializeDatePicker();
	$('#csrIMSIQueryModal').modal('show');
	imsiautocomplete();
}

function showIMSIsForFailureClassModal(){
	$("#exampleModalLongTitle").text("IMSI Affected by given Failure Class");
	$('#csrIMSIQueryModal').find('.modal-body').html('<div class="row">'
		+'<div class="form-group centermargin col-lg-12">'
		+ '<label for="failure">Failure Cause:</label>'
		+ '<input type="number" class="form-control" id="failure" min="0" max="4" placeholder="0-4">'
		+ '</div>'
		+'</div>');
	$('#csrIMSIQueryModal').find('.modal-footer').html('<button type="button" class="btn btn-secondary"'
		+'data-dismiss="modal">Close</button>'
		+'<button type="button" class="btn btn-primary"'
		+'onclick="retrieveIMSIbyFailureClass()" id="submitquery" data-dismiss="modal">Submit</button>');
	$('#csrIMSIQueryModal').modal('show'); 
	failureclassautocomplete();
	
}
function initializeDatePicker() {
	$.datetimepicker.setLocale('en');
	jQuery(function() {
		jQuery('#date_timepicker_start').datetimepicker(
				{
					format : 'Y-m-d',
					onShow : function(ct) {
						this
								.setOptions({
									maxDate : jQuery('#date_timepicker_end')
											.val() ? jQuery(
											'#date_timepicker_end').val()
											: false
								});
					},
					timepicker : false
				});
		jQuery('#date_timepicker_end').datetimepicker(
				{
					format : 'Y-m-d',
					onShow : function(ct) {
						this
								.setOptions({
									minDate : jQuery('#date_timepicker_start')
											.val() ? jQuery(
											'#date_timepicker_start').val()
											: false
								});
					},
					timepicker : false
				});
	});
}

function cleenAllElements() {
	clearElement('tableHeader');
	clearElement('tableFooter');
	clearElement('tableBody');

}

function clearElement(id) {

	console.log(id);
	if (id !== null) {
		document.getElementById(id).innerHTML = "";
	}
}

// //////////////////////////
function showUniqueImsiDataTable(imsi) {
	$('#wrapper')
			.html(  '<div class="tabletitle"><div><h2>All Unique Cause Codes for a given IMSI</h2></div>'
					+'<div><h3>IMSI: '+imsi+'</h3></div>'
					+'</div>'
					+'<div class="card-body"><div class="table-responsive">'
							+ '	<table id="uniqueImsiDataTable" class="table table-bordered display" cellspacing="0" width="100%">'
							+ '		<thead id="tableHeader">' + '			<tr>'
							+ ' 				<th>Cause Code</th>' + ' 			</tr>'
							+ ' 		</thead>'
							// +' <tbody> </tbody>'
							+ '		<tfoot id="tableFooter">' + '			<tr>'
							+ ' 				<th>Cause Code</th>' + '			</tr>'
							+ '		</tfoot>' + '	</table>' + '</div></div>');
}
function showImsiDataTable(imsi) {
	$('#wrapper')
			.html(
					'<div class="tabletitle"><div><h2>All Event IDs and Cause Codes for a given IMSI</h2></div>'
					+'<div><h3>IMSI: '+imsi+'</h3></div>'
					+'</div>'
					+'<div class="card-body"><div class="table-responsive">'
							+ '	<table id="ImsiDataTable" class="table table-bordered display" cellspacing="0" width="100%">'
							+ '		<thead id="tableHeader">' + '			<tr>'
							+ ' 				<th>Event Id</th>'
							+ ' 				<th>Cause Code</th>' + ' 			</tr>'
							+ ' 		</thead>'
							// +' <tbody> </tbody>'
							+ '		<tfoot id="tableFooter">' + '			<tr>'
							+ ' 				<th>Event Id</th>'
							+ ' 				<th>Cause Code</th>' + '			</tr>'
							+ '		</tfoot>' + '	</table>' + '</div></div>');
}
function showUniqueEventAndCauseTable(model) {
	$('#wrapper')
			.html('<div class="tabletitle"><div><h2>All Unique Event IDs and Cause Codes for a given Model</h2></div>'
					+'<div><h3>Model: '+model+'</h3></div>'
					+'</div>'
					+'<div class="card-body"><div class="table-responsive">'
							+ '	<table id="UniqueEventAndCauseTable" class="table table-bordered display" cellspacing="0" width="100%">'
							+ '		<thead id="tableHeader">' + '			<tr>'
							+ ' 				<th>Event Id</th>'
							+ ' 				<th>Cause Code</th>'
							+ ' 				<th>Number of Occurences</th>'
							+ ' 			</tr>' + ' 		</thead>'
							// +' <tbody> </tbody>'
							+ '		<tfoot id="tableFooter">' + '			<tr>'
							+ ' 				<th>Event Id</th>'
							+ ' 				<th>Cause Code</th>'
							+ ' 				<th>Number of Occurences</th>' + '			</tr>'
							+ '		</tfoot>' + '	</table>' + '</div></div>');
}
function showTopTenDataTable(startDate, endDate) {
	$('#wrapper')
			.html('<div class="tabletitle"><div><h2>Top 10 Market/Operator/Cell ID Combinations With Failures for a Given Time Period</h2></div>'
					+'<div><h3>'+startDate+' - '+endDate+'</h3></div>'
					+'</div>'
					+ tab_panel_start
					+'<div class="card-body"><div class="table-responsive">'
							+ '	<table id="TopTenDataTable" class="table table-bordered display" cellspacing="0" width="100%">'
							+ '		<thead id="tableHeader">' + '			<tr>' + ' 				<th>Market</th>'
							+ ' 				<th>Operator</th>'
							+ ' 				<th>Cell ID</th>'
							+ ' 				<th>Number of Failures</th>' + ' 			</tr>'
							+ ' 		</thead>'
							// +' <tbody> </tbody>'
							+ '		<tfoot id="tableFooter">' + '			<tr>' + ' 				<th>Market</th>'
							+ ' 				<th>Operator</th>'
							+ ' 				<th>Cell ID</th>'
							+ ' 				<th>Number of Failures</th>' + '			</tr>'
							+ '		</tfoot>' + '	</table>' + '</div></div>'
							+ tab_panel_end);
	$('#panel_graph')

	.html('<div id="graphdiv"></div>');
}
function showTopTenIMSIsDataTable(startDate, endDate) {
	$('#wrapper')

			.html(''
					+ tab_panel_start
					+ '<div class="card-body"><div class="table-responsive">'

			+'<div class="tabletitle"><div><h2>Top 10 IMSIs With Failures for a Given Time Period</h2></div>'
					+'<div><h3>'+startDate+' - '+endDate+'</h3></div>'
					+'</div>'
					+'<div class="card-body"><div class="table-responsive">'

							+ '	<table id="TopTenIMSIDataTable" class="table table-bordered display" cellspacing="0" width="100%">'
							+ '		<thead id="tableHeader">' + '			<tr>' + ' 				<th>IMSI</th>'
							+ ' 				<th>Number of Failures</th>'
                            + ' 			</tr>'
							+ ' 		</thead>'
							// +' <tbody> </tbody>'
							+ '		<tfoot id="tableFooter">' + '			<tr>' + ' 				<th>IMSI</th>'
							+ ' 				<th>Number of Failures</th>'
 + '			</tr>'
							+ '		</tfoot>' + '	</table>' + '</div></div>'
							+ tab_panel_end);
	
}

function showSumAndCountDataTable(startDate, endDate) {

	$('#wrapper')
			.html('<div class="tabletitle"><div><h2>Number of Failures and Their Duration for a Given Time Period</h2></div>'
					+'<div><h3>'+startDate+' - '+endDate+'</h3></div>'
					+'</div>'
				+ tab_panel_start
				+ '<div class="card-body"><div class="table-responsive">'
				+ '	<table id="SumAndCountDataTable" class="table table-bordered display" cellspacing="0" width="100%">'
				+ '		<thead id="tableHeader">' + '			<tr>' + ' 				<th>IMSI</th>'
				+ ' 				<th>Number of Failures</th>'
				+ ' 				<th>Sum Duration</th>' + ' 			</tr>'
				+ ' 		</thead>'
							// +' <tbody> </tbody>'
				+ '		<tfoot id="tableFooter">' + '			<tr>' + ' 				<th>IMSI</th>'
				+ ' 				<th>Number of Failures</th>'
				+ ' 				<th>Sum Duration</th>' + '			</tr>'
				+ '		</tfoot>' + '	</table>' + '</div></div>'
				+ tab_panel_end);
}
function showDateDataTable(startDate, endDate) {
	$('#wrapper')
			.html('<div class="tabletitle"><div><h2>All IMSIs with Failures for a Given Time Period</h2></div>'
					+'<div><h3>'+startDate+' - '+endDate+'</h3></div>'
					+'</div>'
					+'<div class="card-body"><div class="table-responsive">'
							+ '	<table id="DateDataTable" class="table table-bordered display" cellspacing="0" width="100%">'
							+ '		<thead id="tableHeader">' + '			<tr>' + ' 				<th>IMSI</th>'
							+ ' 			</tr>' + ' 		</thead>'
							// +' <tbody> </tbody>'
							+ '		<tfoot id="tableFooter">' + '			<tr>' + ' 				<th>IMSI</th>'
							+ '			</tr>' + '		</tfoot>' + '	</table>'
							+ '</div></div>');
}
function showCountFailuresDataTable(imsi,startDate, endDate) {
	$('#wrapper')
			.html('<div class="tabletitle"><div><h2>Number of Failures for a Given IMSI and Time Period</h2></div>'
					+'<div><h3>IMSI: '+imsi+'</h3></div>'
					+'<div><h4>'+startDate+' - '+endDate+'</h4></div>'
					+'</div>'
					+'<div class="card-body"><div class="table-responsive">'
							+ '	<table id="CountFailuresDataTable" class="table table-bordered display" cellspacing="0" width="100%">'
							+ '		<thead id="tableHeader">' + '			<tr>'
							+ ' 				<th>Number of Failures</th>' + ' 			</tr>'
							+ ' 		</thead>'
							// +' <tbody> </tbody>'
							+ '		<tfoot id="tableFooter">' + '			<tr>'
							+ ' 				<th>Number of Failures</th>' + '			</tr>'
							+ '		</tfoot>' + '	</table>' + '</div></div>');
}
function showCountFailuresModelDataTable(model,startDate, endDate) {
	$('#wrapper')
			.html('<div class="tabletitle"><div><h2>Number of Failures for a Given Model and Time Period</h2></div>'
					+'<div><h3>Model: '+model+'</h3></div>'
					+'<div><h4>'+startDate+' - '+endDate+'</h4></div>'
					+'</div>'
					+'<div class="card-body"><div class="table-responsive">'
							+ '	<table id="CountFailuresDataTable" class="table table-bordered display" cellspacing="0" width="100%">'
							+ '		<thead id="tableHeader">' + '			<tr>'
							+ ' 				<th>Number of Failures</th>' + ' 			</tr>'
							+ ' 		</thead>'
							// +' <tbody> </tbody>'
							+ '		<tfoot id="tableFooter">' + '			<tr>'
							+ ' 				<th>Number of Failures</th>' + '			</tr>'
							+ '		</tfoot>' + '	</table>' + '</div></div>');
}
function showImsibyFailureClassTable(failureClass) {
	$('#wrapper')
			.html('<div class="tabletitle"><div><h2>All IMSIs Affected by a Given Failure Cause Class</h2></div>'
					+'<div><h3>Failure Class: '+failureClass+'</h3></div>'
					+'</div>'
					+'<div class="card-body"><div class="table-responsive">'
							+ '	<table id="ImsibyFailureClassDataTable" class="table table-bordered display" cellspacing="0">'
							+ '		<thead id="tableHeader">' + '			<tr>'
							+ ' 				<th>IMSI</th>' + ' 			</tr>'
							+ ' 		</thead>'
							// +' <tbody> </tbody>'
							+ '		<tfoot id="tableFooter">' + '			<tr>'
							+ ' 				<th>IMSI</th>' + '			</tr>'
							+ '		</tfoot>' + '	</table>' + '</div></div>');
}

var tab_panel_start =''
	+ '	<ul class="nav nav-tabs nav-justified" role="tablist">'
	+ '		<li class="nav-item">'
	+ '			<a class="nav-link" data-toggle="tab" href="#panel_table" role="tab">Table</a>'
	+ '		</li>'
	+ '		<li class="nav-item">'
	+ '			<a class="nav-link active" data-toggle="tab" href="#panel_graph" role="tab">Graph</a>'
	+ '		</li>'
	+ '	</ul>'
	+ '		<div class="tab-content card">'
	+ '			<!--Panel Tab-->'
	+ '			<div class="tab-pane fade in show" id="panel_table" role="tabpanel">';
var tab_panel_end = ''
	+ '		<a class="scroll-to-top rounded" href="#page-top"><i class="fa fa-angle-up"></i></a>'
	+ '		</div>'
	+ '		<!--Panel Graph-->'
	+ '		<div class="tab-pane fade in show active" id="panel_graph" role="tabpane2">'
	+ '			<button class="btn invisible" id="backButton">< Back</button>'
	+ '			<div id="chartContainer" style="height: 600px; width: 100%;"></div>'
	+ '		<a class="scroll-to-top rounded" href="#page-top"><i class="fa fa-angle-up"></i></a>'
	+ '		</div>'
	+ '</div>';






















