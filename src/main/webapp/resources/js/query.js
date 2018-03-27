//JavaScript Document


var rootUrlFailuresWithinTimePeriodQuery = "http://localhost:8080/mase2-project/rest/basedatas/se/QueryDates?";
var rootUrlNumFailuresForModel = "http://localhost:8080/mase2-project/rest/basedatas/se/";
var rootUrlIMSIFailuresWithinTimePeriod = "http://localhost:8080/mase2-project/rest/basedatas/fc/";
var rootUrlSumDurationAndCountFailures = "http://localhost:8080/mase2-project/rest/basedatas/nme/query?StartDate=";
var rootUrlTop10Failures = "http://localhost:8080/mase2-project/rest/basedatas/nme/querytopten?StartDate=";
var rootUrlUniqueIdAndCauseCodeForModel = "http://localhost:8080/mase2-project/rest/basedatas/nme/";
var rootUrlUniqueCauseCodeForIMSI = "http://localhost:8080/mase2-project/rest/basedatas/csr/unique/";
$('document').ready(function() {
	$('.card-header').html("Network Data Analytics");

});
// ///////////////////// Dashboard /////////////////////
function showDashboard() {
	cleenAllElements();
	$('.card-header').html("Network Data Analytics");

}
function showLoading(){
	$('#wrapper')
	.html('<img src="./resources/images/ajax-loader.gif" id="loading-indicator" style="display:none" />');
	$('#loading-indicator').show();
}

// ///////////////////Dropdown modal Button//////////////////
$(function() {

	$(".dropdown-menu a").click(function() {

		$("#dropdownMenuButton:first-child").text($(this).text());
		$("#dropdownMenuButton:first-child").val($(this).text());
	});

});

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
				buttons: [
				            'copy','excel','pdf','print'
				            
				            ],
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
				buttons: [
				            'copy','excel','pdf','print'
				            
				            ],
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
				buttons: [
				            'copy','excel','pdf','print'
				            
				            ],
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
				buttons: [
				            'copy','excel','pdf','print'
				            
				            ],
				data : data,
				columns : [ {
					data : "failureCount"
				} ]

			});

		}
	});
};
var countAndSumDataRequest = function(data1, data2) {
	$.ajax({
		type : 'GET',
		url : rootUrlSumDurationAndCountFailures + data1 + "&EndDate=" + data2,
		dataType : "json",
		headers : {
			'Authorization' : 'Basic ' + sessionStorage.getItem("email") + ":"
					+ sessionStorage.getItem("password")
		},
		success : function(data) {

				$('#SumAndCountDataTable').DataTable({
				responsive: true,
				fixedHeader: true,
				dom: 'Bfrtlip',
				buttons: [
				            'copy','excel','pdf','print'
				            
				            ],
				"columnDefs": [
				               { "width": "33%","targets": [0,1,2] }

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
				buttons: [
				            'copy','excel','pdf','print'
				            
				            ],
				"columnDefs": [
				               { "width": "25%","targets": [0,1,2,3] }

				             ],
				data : data,
				columns : [ {
					data : "mcc"
				}, {
					data : "mnc"
				}, {
					data : "cellId"
				}, {
					data : "count"
				} ],
				"order" : [ [ 3, "desc" ] ]
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
				buttons: [
				            'copy','excel','pdf','print'
				            
				            ],
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
				buttons: [
				            'copy','excel','pdf','print'
				            
				            ],
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
function retrieveIMSI() {
	// findAllIMSIData(document.getElementById('imsi').value);
	showLoading();
	imsiDataRequest(document.getElementById('imsi').value);
	showImsiDataTable();
}
function retrieveUniqueIMSI() {
	
	showLoading();
	uniqueImsiDataRequest(document.getElementById('imsi').value);
	showUniqueImsiDataTable();
}
function retrieveDates() {
	showLoading();
	DateDataRequest(document.getElementById('date_timepicker_start').value,
			document.getElementById('date_timepicker_end').value);
	showDateDataTable();
}
function retrieveDatesNME() {
	showLoading();
	countAndSumDataRequest(
			document.getElementById('date_timepicker_start').value, document
					.getElementById('date_timepicker_end').value);
	showSumAndCountDataTable();
}
function retrieveDatesTopTen() {
	showLoading();
	TopTenDataRequest(document.getElementById('date_timepicker_start').value,
			document.getElementById('date_timepicker_end').value);
	showTopTenDataTable();
}
function retrieveModelAndDates() {
	showLoading();
	countFailuresDataRequest(document.getElementById('model').value, document
			.getElementById('date_timepicker_start').value, document
			.getElementById('date_timepicker_end').value);
	showCountFailuresDataTable();
}

function retrieveIMSIAndDates() {
	showLoading();
	countFailuresForIMSIDataRequest(document.getElementById('imsi').value,
			document.getElementById('date_timepicker_start').value, document
					.getElementById('date_timepicker_end').value);
	showCountFailuresDataTable();
}
function retrieveModel() {
	showLoading();
	uniqueEventAndCauseDataRequest(document.getElementById('model').value);
	showUniqueEventAndCauseTable();
}

function showModelModal(){
	$("#exampleModalLongTitle").text("Failures for Model");
	$('#csrIMSIQueryModal').find('.modal-body').html('<div class="dropdown">'
			+'<div class="form-group centermargin">'
			+ '<label for="model">Model:</label>'
			+ '<input type="text" class="form-control" id="model" placeholder="Model">'
			+ '<label for="date_timepicker_start">Start Date:</label>'
			+ '<input type="text" class="form-control" id="date_timepicker_start" placeholder="Start Date">'
			+ '<label for="date_timepicker_end">End Date:</label>'
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
	$('#csrIMSIQueryModal').find('.modal-body').html('<div class="dropdown">'
			+'<div class="form-group centermargin">'
			+ '<label for="model">Model:</label>'
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
function showIMSIModal(){
	$("#exampleModalLongTitle").text("Event IDs - Cause Codes for IMSI");
	$('#csrIMSIQueryModal').find('.modal-body').html('<div class="dropdown">'
		+'<div class="form-group centermargin">'
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
	$('#csrIMSIQueryModal').find('.modal-body').html('<div class="dropdown">'
		+'<div class="form-group centermargin">'
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
	$("#exampleModalLongTitle").text("Sum Failure duration for IMSI");
	$('#csrIMSIQueryModal').find('.modal-body').html('<div class="dropdown">'
			+'<div class="form-group centermargin">'
			+ '<label for="date_timepicker_start">Start Date:</label>'
			+ '<input type="text" class="form-control" id="date_timepicker_start" placeholder="Start Date">'
			+ '<label for="date_timepicker_end">End Date:</label>'
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
	$('#csrIMSIQueryModal').find('.modal-body').html('<div class="dropdown">'
			+'<div class="form-group centermargin">'
			+ '<label for="date_timepicker_start">Start Date:</label>'
			+ '<input type="text" class="form-control" id="date_timepicker_start" placeholder="Start Date">'
			+ '<label for="date_timepicker_end">End Date:</label>'
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
	$('#csrIMSIQueryModal').find('.modal-body').html('<div class="dropdown">'
	    +'<div class="form-group centermargin">'
        +'<label for="date_timepicker_start">Start Date:</label>'
        +'<input type="text" class="form-control" id="date_timepicker_start" placeholder="Start Date">'
        +'<label for="date_timepicker_end">End Date:</label>'
        +'<input type="text" class="form-control" id="date_timepicker_end" placeholder="End Date">'
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
	$('#csrIMSIQueryModal').find('.modal-body').html('<div class="dropdown">'		
		+'<div class="form-group centermargin">'
		+ '<label for="imsi">IMSI</label>'
		+ '<input type="text" class="form-control" id="imsi" placeholder="IMSI">'
		+ '<label for="date_timepicker_start">Start Date:</label>'
		+ '<input type="text" class="form-control" id="date_timepicker_start" placeholder="Start Date">'
		+ '<label for="date_timepicker_end">End Date:</label>'
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
function showUniqueImsiDataTable() {
	$('#wrapper')
			.html(
					'<div class="card-body"><div class="table-responsive">'
							+ '	<table id="uniqueImsiDataTable" class="table table-bordered display" cellspacing="0" width="100%">'
							+ '		<thead>' + '			<tr>'
							+ ' 				<th>Cause Code</th>' + ' 			</tr>'
							+ ' 		</thead>'
							// +' <tbody> </tbody>'
							+ '		<tfoot>' + '			<tr>'
							+ ' 				<th>Cause Code</th>' + '			</tr>'
							+ '		</tfoot>' + '	</table>' + '</div></div>');
}
function showImsiDataTable() {
	$('#wrapper')
			.html(
					'<div class="card-body"><div class="table-responsive">'
							+ '	<table id="ImsiDataTable" class="table table-bordered display" cellspacing="0" width="100%">'
							+ '		<thead>' + '			<tr>'
							+ ' 				<th>Event Id</th>'
							+ ' 				<th>Cause Code</th>' + ' 			</tr>'
							+ ' 		</thead>'
							// +' <tbody> </tbody>'
							+ '		<tfoot>' + '			<tr>'
							+ ' 				<th>Event Id</th>'
							+ ' 				<th>Cause Code</th>' + '			</tr>'
							+ '		</tfoot>' + '	</table>' + '</div></div>');
}
function showUniqueEventAndCauseTable() {
	$('#wrapper')
			.html(
					'<div class="card-body"><div class="table-responsive">'
							+ '	<table id="UniqueEventAndCauseTable" class="table table-bordered display" cellspacing="0" width="100%">'
							+ '		<thead>' + '			<tr>'
							+ ' 				<th>Event Id</th>'
							+ ' 				<th>Cause Code</th>'
							+ ' 				<th>Number of Occurences</th>'
							+ ' 			</tr>' + ' 		</thead>'
							// +' <tbody> </tbody>'
							+ '		<tfoot>' + '			<tr>'
							+ ' 				<th>Event Id</th>'
							+ ' 				<th>Cause Code</th>'
							+ ' 				<th>Number of Occurences</th>' + '			</tr>'
							+ '		</tfoot>' + '	</table>' + '</div></div>');
}
function showTopTenDataTable() {
	$('#wrapper')
			.html(
					'<div class="card-body"><div class="table-responsive">'
							+ '	<table id="TopTenDataTable" class="table table-bordered display" cellspacing="0" width="100%">'
							+ '		<thead>' + '			<tr>' + ' 				<th>Market</th>'
							+ ' 				<th>Operator</th>'
							+ ' 				<th>Cell ID</th>'
							+ ' 				<th>Number of Failures</th>' + ' 			</tr>'
							+ ' 		</thead>'
							// +' <tbody> </tbody>'
							+ '		<tfoot>' + '			<tr>' + ' 				<th>Market</th>'
							+ ' 				<th>Operator</th>'
							+ ' 				<th>Cell ID</th>'
							+ ' 				<th>Number of Failures</th>' + '			</tr>'
							+ '		</tfoot>' + '	</table>' + '</div></div>');
}
function showSumAndCountDataTable() {
	$('#wrapper')
			.html(
					'<div class="card-body"><div class="table-responsive">'
							+ '	<table id="SumAndCountDataTable" class="table table-bordered display" cellspacing="0" width="100%">'
							+ '		<thead>' + '			<tr>' + ' 				<th>IMSI</th>'
							+ ' 				<th>Number of Failures</th>'
							+ ' 				<th>Sum Duration</th>' + ' 			</tr>'
							+ ' 		</thead>'
							// +' <tbody> </tbody>'
							+ '		<tfoot>' + '			<tr>' + ' 				<th>IMSI</th>'
							+ ' 				<th>Number of Failures</th>'
							+ ' 				<th>Sum Duration</th>' + '			</tr>'
							+ '		</tfoot>' + '	</table>' + '</div></div>');
}
function showDateDataTable() {
	$('#wrapper')
			.html(
					'<div class="card-body"><div class="table-responsive">'
							+ '	<table id="DateDataTable" class="table table-bordered display" cellspacing="0" width="100%">'
							+ '		<thead>' + '			<tr>' + ' 				<th>IMSI</th>'
							+ ' 			</tr>' + ' 		</thead>'
							// +' <tbody> </tbody>'
							+ '		<tfoot>' + '			<tr>' + ' 				<th>IMSI</th>'
							+ '			</tr>' + '		</tfoot>' + '	</table>'
							+ '</div></div>');
}
function showCountFailuresDataTable() {
	$('#wrapper')
			.html(
					'<div class="card-body"><div class="table-responsive">'
							+ '	<table id="CountFailuresDataTable" class="table table-bordered display" cellspacing="0" width="100%">'
							+ '		<thead>' + '			<tr>'
							+ ' 				<th>Number of Failures</th>' + ' 			</tr>'
							+ ' 		</thead>'
							// +' <tbody> </tbody>'
							+ '		<tfoot>' + '			<tr>'
							+ ' 				<th>Number of Failures</th>' + '			</tr>'
							+ '		</tfoot>' + '	</table>' + '</div></div>');
}