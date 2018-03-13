//JavaScript Document

var rootUrlIMSIQuery = "http://localhost:8080/mase2-project/rest/basedatas/csr/";
var rootUrlFailuresWithinTimePeriodQuery = "http://localhost:8080/mase2-project/rest/basedatas/se/QueryDates?";
var rootUrlNumFailuresForModel = "http://localhost:8080/mase2-project/rest/basedatas/se/";
var rootUrlIMSIFailuresWithinTimePeriod = "http://localhost:8080/mase2-project/rest/basedatas/fc/";
var rootUrlSumDurationAndCountFailures = "http://localhost:8080/mase2-project/rest/basedatas/nme/query?StartDate=";
var rootUrlTop10Failures = "http://localhost:8080/mase2-project/rest/basedatas/nme/querytopten?StartDate=";
var rootUrlUniqueIdAndCauseCodeForModel = "http://localhost:8080/mase2-project/rest/basedatas/nme/";
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

			userTable = $('#SumAndCountDataTable').DataTable({
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
function showModelModal() {
	$('#csrIMSIQueryModal')
			.find('.modal-body')
			.html(
					'<div class="dropdown">'
							+ 'Enter Phone Model: <input type="text" name="model" id="model">'
							+ '<div>'
							+ 'Start'
							+ '<input id="date_timepicker_start" type="text" />'
							+ 'End'
							+ '<input id="date_timepicker_end" type="text" />'
							+ '</div>' + '</div>');
	$('#csrIMSIQueryModal')
			.find('.modal-footer')
			.html(
					'<button type="button" class="btn btn-secondary"'
							+ 'data-dismiss="modal">Close</button>'
							+ '<button type="button" class="btn btn-primary"'
							+ 'onclick="retrieveModelAndDates()" data-dismiss="modal">Submit'
							+ ' Query</button>');
	initializeDatePicker();
	$('#csrIMSIQueryModal').modal('show');
}
function showUniqueModelModal() {
	$('#csrIMSIQueryModal')
			.find('.modal-body')
			.html(
					'<div class="dropdown">'
							+ 'Enter Model: <input type="text" name="model" id="model">'
							+ '</div>');
	$('#csrIMSIQueryModal').find('.modal-footer').html(
			'<button type="button" class="btn btn-secondary"'
					+ 'data-dismiss="modal">Close</button>'
					+ '<button type="button" class="btn btn-primary"'
					+ 'onclick="retrieveModel()" data-dismiss="modal">Submit'
					+ ' Query</button>');
	$('#csrIMSIQueryModal').modal('show');
}
function showIMSIModal() {
	$('#csrIMSIQueryModal').find('.modal-body').html(
			'<div class="dropdown">'
					+ 'Enter IMSI: <input type="text" name="imsi" id="imsi">'
					+ '</div>');
	$('#csrIMSIQueryModal').find('.modal-footer').html(
			'<button type="button" class="btn btn-secondary"'
					+ 'data-dismiss="modal">Close</button>'
					+ '<button type="button" class="btn btn-primary"'
					+ 'onclick="retrieveIMSI()" data-dismiss="modal">Submit'
					+ ' Query</button>');
	$('#csrIMSIQueryModal').modal('show');
}
function showNMEModal() {
	$('#csrIMSIQueryModal').find('.modal-body').html(
			'<div class="dropdown">' + '<div>' + 'Start'
					+ '<input id="date_timepicker_start" type="text" />'
					+ 'End' + '<input id="date_timepicker_end" type="text" />'
					+ '</div>' + '</div>');
	$('#csrIMSIQueryModal')
			.find('.modal-footer')
			.html(
					'<button type="button" class="btn btn-secondary"'
							+ 'data-dismiss="modal">Close</button>'
							+ '<button type="button" class="btn btn-primary"'
							+ 'onclick="retrieveDatesNME()" data-dismiss="modal">Submit'
							+ ' Query</button>');
	initializeDatePicker();

	$('#csrIMSIQueryModal').modal('show');

}
function showTopTenModal() {
	$('#csrIMSIQueryModal').find('.modal-body').html(
			'<div class="dropdown">' + '<div>' + 'Start'
					+ '<input id="date_timepicker_start" type="text" />'
					+ 'End' + '<input id="date_timepicker_end" type="text" />'
					+ '</div>' + '</div>');
	$('#csrIMSIQueryModal')
			.find('.modal-footer')
			.html(
					'<button type="button" class="btn btn-secondary"'
							+ 'data-dismiss="modal">Close</button>'
							+ '<button type="button" class="btn btn-primary"'
							+ 'onclick="retrieveDatesTopTen()" data-dismiss="modal">Submit'
							+ ' Query</button>');
	initializeDatePicker();

	$('#csrIMSIQueryModal').modal('show');

}
function showDateModal() {
	$('#csrIMSIQueryModal').find('.modal-body').html(
			'<div class="dropdown">' + '<div>' + 'Start'
					+ '<input id="date_timepicker_start" type="text" />'
					+ 'End' + '<input id="date_timepicker_end" type="text" />'
					+ '</div>' + '</div>');
	$('#csrIMSIQueryModal').find('.modal-footer').html(
			'<button type="button" class="btn btn-secondary"'
					+ 'data-dismiss="modal">Close</button>'
					+ '<button type="button" class="btn btn-primary"'
					+ 'onclick="retrieveDates()" data-dismiss="modal">Submit'
					+ ' Query</button>');
	initializeDatePicker();

	$('#csrIMSIQueryModal').modal('show');
}
function showIMSIFailureModalGivenTimePeriod() {
	$('#csrIMSIQueryModal')
			.find('.modal-body')
			.html(
					'<div class="dropdown">'
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
							+ '</tr>' + '</table>' + '</div>');
	$('#csrIMSIQueryModal')
			.find('.modal-footer')
			.html(
					'<button type="button" class="btn btn-secondary"'
							+ 'data-dismiss="modal">Close</button>'
							+ '<button type="button" class="btn btn-primary"'
							+ 'onclick="retrieveIMSIAndDates()" data-dismiss="modal">Submit'
							+ ' Query</button>');
	initializeDatePicker();
	$('#csrIMSIQueryModal').modal('show');
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
