// JavaScript Document

var rootUrlIMSIQuery = "http://localhost:8080/mase2-project/rest/basedatas/csr/";
var rootUrlFailuresWithinTimePeriodQuery = "http://localhost:8080/mase2-project/rest/basedatas/se/QueryDates?";
var rootUrlNumFailuresForModel = "http://localhost:8080/mase2-project/rest/basedatas/se/";
var rootUrlIMSIFailuresWithinTimePeriod = "http://localhost:8080/mase2-project/rest/basedatas/fc/";
var rootUrlSumDurationAndCountFailures= "http://localhost:8080/mase2-project/rest/basedatas/nme/query?StartDate=";
var rootUrlTop10Failures= "http://localhost:8080/mase2-project/rest/basedatas/nme/querytopten?StartDate=";
var rootUrlUniqueIdAndCauseCodeForModel= "http://localhost:8080/mase2-project/rest/basedatas/nme/";
$('document').ready(function(){
	$('.card-header').html("Network Data Analytics");
	$('.content-wrapper').css("background", "rgb(180,180,180)");
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

/////////////////////Dropdown modal Button//////////////////
$(function(){
	  
	  $(".dropdown-menu a").click(function(){
	    
	    $("#dropdownMenuButton:first-child").text($(this).text());
	     $("#dropdownMenuButton:first-child").val($(this).text());
	  });

	});

// /////////////////////Tables /////////////////////
var findAllIMSIData = function(data) {
	$.ajax({
		type : 'GET',
		url : rootUrlIMSIQuery+data,
		dataType : "json",
		success : renderListIMSIData
	});
};
var findAllDateData = function(data1,data2) {
	$.ajax({
		type : 'GET',
		url : rootUrlFailuresWithinTimePeriodQuery+"StartDate="+data1+"&EndDate="+data2,
		dataType : "json",
		success : renderListDateData
	});
};
var findAllDateDataNME = function(data1,data2) {
	$.ajax({
		type : 'GET',
		url : rootUrlSumDurationAndCountFailures+data1+"&EndDate="+data2,
		dataType : "json",
		success : renderListSumDurationAndCountFailures
	});
};
var findCountCallFailures = function(model,date1,date2){
	$.ajax({
		type : 'GET',
		url : rootUrlNumFailuresForModel+model+"?StartDate="+date1+"&EndDate="+date2,
		dataType : "json",
		success : renderCountFailures
	});
};
var findIMSICallFailuresGivenTimePeriod = function(imsi,date1,date2){
	$.ajax({
		type : 'GET',
		url : rootUrlIMSIFailuresWithinTimePeriod+imsi+"?StartDate="+date1+"&EndDate="+date2,
		dataType : "json",
		success : renderCountIMSIFailuresGivenTimePeriod
	});
};

var findUniqueIdCauseCodeCombinations = function(model){
	$.ajax({
		type : 'GET',
		url : rootUrlUniqueIdAndCauseCodeForModel+model,
		dataType : "json",
		success : renderListUniqueEventIdCauseCode
	});
};

var findAllTopTenDateDataFailures = function(data1,data2) {
	$.ajax({
		type : 'GET',
		url : rootUrlTop10Failures+data1+"&EndDate="+data2,
		dataType : "json",
		success : renderListTopTenFailuresTimePeriod
	});
};

function retrieveIMSI() {
	findAllIMSIData(document.getElementById('imsi').value);
}
function retrieveDates() {
	findAllDateData(document.getElementById('date_timepicker_start').value,document.getElementById('date_timepicker_end').value);
}
function retrieveDatesNME() {
	findAllDateDataNME(document.getElementById('date_timepicker_start').value,document.getElementById('date_timepicker_end').value);
}
function retrieveDatesTopTen() {
	findAllTopTenDateDataFailures(document.getElementById('date_timepicker_start').value,document.getElementById('date_timepicker_end').value);
}
function retrieveModelAndDates() {
	findCountCallFailures(document.getElementById('model').value,document.getElementById('date_timepicker_start').value,document.getElementById('date_timepicker_end').value);
}

function retrieveIMSIAndDates() {
	findIMSICallFailuresGivenTimePeriod(document.getElementById('imsi').value,document.getElementById('date_timepicker_start').value,document.getElementById('date_timepicker_end').value);
}
function retrieveModel() {
	findUniqueIdCauseCodeCombinations(document.getElementById('model').value);
}
function showModelModal(){
	$("#exampleModalLongTitle").text("Failures for Model");
	$('#csrIMSIQueryModal').find('.modal-body').html('<div class="dropdown">'
			+ '<table>'
			+ '<tr>'
			+ '<td>Enter Phone Model:</td>' 
			+ '<td><input type="text" name="model" id="model"></td>'
			+ '</tr>'
			+ '<tr>'
			+ '<td>Start Date: </td>'
			+ '<td><input id="date_timepicker_start" type="text" /></td>'
			+ '</tr>'
			+ '<tr>'
			+ '<td>End Date: </td>'
			+ '<td><input id="date_timepicker_end" type="text" /></td>'
			+ '</tr>'
			+ '</table>'
		+'</div>');
	$('#csrIMSIQueryModal').find('.modal-footer').html('<button type="button" class="btn btn-secondary"'
		+'data-dismiss="modal">Close</button>'
		+'<button type="button" class="btn btn-primary"'
		+'onclick="retrieveModelAndDates()" data-dismiss="modal">Submit</button>');
	initializeDatePicker();
	$('#csrIMSIQueryModal').modal('show'); 
}
function showUniqueModelModal(){
	$("#exampleModalLongTitle").text("Unique Event IDs - Cause Codes for model");
	$('#csrIMSIQueryModal').find('.modal-body').html('<div class="dropdown">'
			+ '<table>'
			+ '<tr>'
			+ '<td>Enter Model:</td>' 
			+ '<td><input type="text" name="model" id="model"></td>'
			+ '</tr>'
			+ '</table>'
			+ '</div>');
	$('#csrIMSIQueryModal').find('.modal-footer').html('<button type="button" class="btn btn-secondary"'
		+'data-dismiss="modal">Close</button>'
		+'<button type="button" class="btn btn-primary"'
		+'onclick="retrieveModel()" data-dismiss="modal">Submit</button>');
	$('#csrIMSIQueryModal').modal('show'); 
}
function showIMSIModal(){
	$("#exampleModalLongTitle").text("Event IDs - Cause Codes for IMSI");
	$('#csrIMSIQueryModal').find('.modal-body').html('<div class="dropdown">'
		+ '<table>'
		+ '<tr>'
		+ '<td>Enter IMSI:</td>' 
		+ '<td><input type="text" name="imsi" id="imsi"></td>'
		+ '</tr>'
		+ '</table>'
		+'</div>');
	$('#csrIMSIQueryModal').find('.modal-footer').html('<button type="button" class="btn btn-secondary"'
		+'data-dismiss="modal">Close</button>'
		+'<button type="button" class="btn btn-primary"'
		+'onclick="retrieveIMSI()" data-dismiss="modal">Submit</button>');
	$('#csrIMSIQueryModal').modal('show'); 
}
function showNMEModal(){
	$("#exampleModalLongTitle").text("Sum Failure duration for IMSI");
	$('#csrIMSIQueryModal').find('.modal-body').html('<div class="dropdown">'
		+ '<table>'
		+ '<tr>'
		+ '<td>Start Date: </td>'
		+ '<td><input id="date_timepicker_start" type="text" /></td>'
		+ '</tr>'
		+ '<tr>'
		+ '<td>End Date: </td>'
		+ '<td><input id="date_timepicker_end" type="text" /></td>'
		+ '</tr>'
		+ '</table>'
		+ '</div>');
	$('#csrIMSIQueryModal').find('.modal-footer').html('<button type="button" class="btn btn-secondary"'
		+'data-dismiss="modal">Close</button>'
		+'<button type="button" class="btn btn-primary"'
		+'onclick="retrieveDatesNME()" data-dismiss="modal">Submit</button>');
	initializeDatePicker();
	$('#csrIMSIQueryModal').modal('show'); 
	
}

function showTopTenModal(){
	$("#exampleModalLongTitle").text("Top 10 Market/Operator/Cell ID combinations with failures");
	$('#csrIMSIQueryModal').find('.modal-body').html('<div class="dropdown">'
		+ '<table>'
		+ '<tr>'
		+ '<td>Start Date: </td>'
		+ '<td><input id="date_timepicker_start" type="text" /></td>'
		+ '</tr>'
		+ '<tr>'
		+ '<td>End Date: </td>'
		+ '<td><input id="date_timepicker_end" type="text" /></td>'
		+ '</tr>'
		+ '</table>'
		+ '</div>');
	$('#csrIMSIQueryModal').find('.modal-footer').html('<button type="button" class="btn btn-secondary"'
		+'data-dismiss="modal">Close</button>'
		+'<button type="button" class="btn btn-primary"'
		+'onclick="retrieveDatesTopTen()" data-dismiss="modal">Submit</button>');
	initializeDatePicker();
	$('#csrIMSIQueryModal').modal('show'); 	
}

function showDateModal(){
	$("#exampleModalLongTitle").text("IMSI failures during given period");
	$('#csrIMSIQueryModal').find('.modal-body').html('<div class="dropdown">'
		+ '<table>'
		+ '<tr>'
		+ '<td>Start Date: </td>'
		+ '<td><input id="date_timepicker_start" type="text" /></td>'
		+ '</tr>'
		+ '<tr>'
		+ '<td>End Date: </td>'
		+ '<td><input id="date_timepicker_end" type="text" /></td>'
		+ '</tr>'
		+ '</table>'
		+ '</div>');
	$('#csrIMSIQueryModal').find('.modal-footer').html('<button type="button" class="btn btn-secondary"'
		+ 'data-dismiss="modal">Close</button>'
		+ '<button type="button" class="btn btn-primary"'
		+ 'onclick="retrieveDates()" data-dismiss="modal">Submit</button>');
	initializeDatePicker();
	$('#csrIMSIQueryModal').modal('show'); 
}

function showIMSIFailureModalGivenTimePeriod(){
	$("#exampleModalLongTitle").text("Count of failures for IMSI");
	$('#csrIMSIQueryModal').find('.modal-body').html('<div class="dropdown">'		
		+ '<table>'
		+ '<tr>'
		+ '<td>IMSI: </td>'
		+ '<td><input type="text" name="imsi" id="imsi"></td>'
		+ '</tr>'
		+ '<tr>'
		+ '<td>Start Date: </td>'
		+ '<td><input id="date_timepicker_start" type="text" /></td>'
		+ '</tr>'
		+ '<tr>'
		+ '<td>End Date: </td>'
		+ '<td><input id="date_timepicker_end" type="text" /></td>'
		+ '</tr>'
		+ '</table>'
		+ '</div>');
	$('#csrIMSIQueryModal').find('.modal-footer').html('<button type="button" class="btn btn-secondary"'
		+ 'data-dismiss="modal">Close</button>'
		+ '<button type="button" class="btn btn-primary"'
		+ 'onclick="retrieveIMSIAndDates()" data-dismiss="modal">Submit</button>');
	initializeDatePicker();
	$('#csrIMSIQueryModal').modal('show'); 
}

function initializeDatePicker(){
	$.datetimepicker.setLocale('en');
	jQuery(function(){
		 jQuery('#date_timepicker_start').datetimepicker({
		  format:'Y-m-d',
		  onShow:function( ct ){
		   this.setOptions({
		    maxDate:jQuery('#date_timepicker_end').val()?jQuery('#date_timepicker_end').val():false
		   });
		  },
		  timepicker:false
		 });
		 jQuery('#date_timepicker_end').datetimepicker({
		  format:'Y-m-d',
		  onShow:function( ct ){
		   this.setOptions({
		    minDate:jQuery('#date_timepicker_start').val()?jQuery('#date_timepicker_start').val():false
		   });
		  },
		  timepicker:false
		 });
		});
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



// //////////////////////////
var renderListIMSIData = function(data) {
	$('.card-header')
			.html(
					'<i class="fa fa-table"></i> <span id="tableTitle">Base Data Table</span>');

	cleenAllElements();

	$('#tableHeader').append(
			"<th>Event Id</th>" + "<th>Cause Code</th>");

	$('#tableFooter').append(
			"<th>Event Id</th>" + "<th>Cause Code</th>");

	$.each(data, function(index, base_data) {
		$('#tableBody').append(
				'<tr><td>'
						+ base_data.id.eventId + '</td><td>'
						+ base_data.id.eventCode + '</td></tr>');
	});
	$('#example').DataTable({
		destroy : true,
		paging : false,
		searching : false
	});
	document.getElementById('example_info').setAttribute("style",
			"display:none");
};
var renderListDateData = function(data) {
	$('.card-header')
			.html(
					'<i class="fa fa-table"></i> <span id="tableTitle">Base Data Table</span>');

	cleenAllElements();

	$('#tableHeader').append(
			"<th>IMSI</th>");

	$('#tableFooter').append(
			"<th>IMSI</th>");

	$.each(data, function(index, base_data) {
		$('#tableBody').append(
				'<tr><td>'
						+ base_data + '</td></tr>');
	});
	$('#example').DataTable({
		destroy : true,
		paging : false,
		searching : false
	});
	document.getElementById('example_info').setAttribute("style",
			"display:none");
};
var renderCountFailures = function(data) {
	$('.card-header')
			.html(
					'<i class="fa fa-table"></i> <span id="tableTitle">Base Data Table</span>');

	cleenAllElements();

	$('#tableHeader').append(
			"<th>Number of Call Failures</th>");

	$('#tableFooter').append(
			"<th>Number of Call Failures</th>");

	$.each(data, function(index, base_data) {
		$('#tableBody').append(
				'<tr><td>'
						+ base_data + '</td></tr>');
	});
	$('#example').DataTable({
		destroy : true,
		paging : false,
		searching : false
	});
	document.getElementById('example_info').setAttribute("style",
			"display:none");
};
var renderListSumDurationAndCountFailures = function(data) {
	$('.card-header')
			.html(
					'<i class="fa fa-table"></i> <span id="tableTitle">Base Data Table</span>');

	cleenAllElements();

	$('#tableHeader').append(
			"<th>IMSI</th>" + "<th>Number of Failures</th>"+"<th>Start period</th>"+"<th>end period</th>" );

	$('#tableFooter').append(
			"<th>IMSI</th>" + "<th>Number of Failures</th>"+"<th>Start period</th>"+"<th>end period</th>");

	$.each(data, function(index, base_data) {
		$('#tableBody').append(
				'<tr><td>'
						+ base_data[0] + '</td><td>'
						+ base_data[1]+'</td><td>'
						+ base_data[2]+'</td></tr>');
	});
	$('#example').DataTable({
		destroy : true,
		paging : false,
		searching : false
	});
	document.getElementById('example_info').setAttribute("style",
			"display:none");
};

var renderCountIMSIFailuresGivenTimePeriod = function(data) {
	$('.card-header')
			.html('<i class="fa fa-table"></i> <span id="tableTitle">Base Data Table</span>');

	cleenAllElements();

	$('#tableHeader').append(
			"<th>Number of Failures</th>");

	$('#tableFooter').append(
			"<th>Number of Failures</th>");

	$.each(data, function(index, base_data) {
		$('#tableBody').append('<tr><td>'+ base_data + '</td></tr>');
	});
	$('#example').DataTable({
		destroy : true,
		paging : false,
		searching : false
	});
	document.getElementById('example_info').setAttribute("style",
			"display:none");
	};
	
var renderListUniqueEventIdCauseCode = function(data) {
	$('.card-header')
			.html(
					'<i class="fa fa-table"></i> <span id="tableTitle">Base Data Table</span>');

	cleenAllElements();

	$('#tableHeader').append(
			"<th>Event Id</th>" + "<th>Cause Code</th>" + "<th>Number of Occurrences</th>");

	$('#tableFooter').append(
			"<th>Event Id</th>" + "<th>Cause Code</th>" + "<th>Number of Occurrences</th>");

	$.each(data, function(index, base_data) {
		$('#tableBody').append(
				'<tr><td>'
				+ base_data[0].id.eventId + '</td><td>'
				+ base_data[0].id.eventCode + '</td><td>'
				+ base_data[1] + '</td></tr>');
	});
	$('#example').DataTable({
		destroy : true,
		paging : false,
		searching : false
	});
	document.getElementById('example_info').setAttribute("style",
			"display:none");
};

var renderListTopTenFailuresTimePeriod = function(data) {
	var count=0;
	$('.card-header')
			.html(
					'<i class="fa fa-table"></i> <span id="tableTitle">Base Data Table</span>');

	cleenAllElements();

	$('#tableHeader').append(
			"<th></th>" + "<th>Market</th>" + "<th>Operator</th>"+"<th>Cell ID</th>"+"<th>Number of failures</th>");

	$('#tableFooter').append(
			"<th></th>" +"<th>Market</th>" + "<th>Operator</th>"+"<th>Cell ID</th>"+"<th>Number of failures</th>");

	$.each(data, function(index, base_data) {
		count++;
		$('#tableBody').append(
				'<tr><td>'
						+ count + '</td><td>'
						+ base_data[0] + '</td><td>'
						+ base_data[1]+'</td><td>'
						+ base_data[2]+'</td><td>'
						+ base_data[3]+'</td></tr>');
	});
	$('#example').DataTable({
		"order": [[4,"desc"]],
		destroy : true,
		paging : false,
		searching : false
	});
	document.getElementById('example_info').setAttribute("style",
			"display:none");
};
