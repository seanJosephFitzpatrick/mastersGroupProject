// JavaScript Document

var rootUrlBaseData = "http://localhost:8080/mase2-project/rest/basedatas";

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
var findAllBaseData = function() {
	$.ajax({
		type : 'GET',
		url : rootUrlBaseData,
		dataType : "json",
		success : renderListBaseData
	});
};

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
