// JavaScript Document

//mcc_mnc table
var rootUrlMcc_Mnc ="http://localhost:8080/mase2-project/rest/mcc_mnc";
var findAllMcc_Mnc=function(){
	$.ajax({
		   type:'GET',
		   url: rootUrlMcc_Mnc,
		   dataType:"json",
		   success:renderListMcc_Mnc
		   });
};

var renderListMcc_Mnc=function(data){
	$.each(data, function(index,mcc_mnc){
	$('#mcc_mncList').append('<li>'+ mcc_mnc.id.mcc + mcc_mnc.id.mnc +
						   mcc_mnc.country + mcc_mnc.operator +'</li>');
						  });
}

$(document).ready(function(){
	findAllMcc_Mnc();
});

//ue table
var rootUrlUe="http://localhost:8080/mase2-project/rest/ue";
var findAllUe=function(){
	$.ajax({
		   type:'GET',
		   url: rootUrlUe,
		   dataType:"json",
		   success:renderListUe
		   });
};

var renderListUe=function(data){
	$.each(data, function(index,ue){
	$('#ueList').append('<li>'+ ue.tac + ue.marketingName +
						   ue.manufacturer + ue.accessCapability +'</li>');
						  });
}

$(document).ready(function(){
	findAllUe();
});

//failure class table
var rootUrlFailureClass="http://localhost:8080/mase2-project/rest/failureclass";
var findAllFailureClass=function(){
	$.ajax({
		   type:'GET',
		   url: rootUrlFailureClass,
		   dataType:"json",
		   success:renderListFailureClass
		   });
};

var renderListFailureClass=function(data){
	$.each(data, function(index,failure_class){
	$('#failureClassList').append('<li>'+ failure_class.failureClass + failure_class.description +
						  '</li>');
						  });
}

$(document).ready(function(){
	findAllFailureClass();
});

//event cause table
var rootUrlEventCause="http://localhost:8080/mase2-project/rest/eventcauses";
var findAllEventCause=function(){
	$.ajax({
		   type:'GET',
		   url: rootUrlEventCause,
		   dataType:"json",
		   success:renderListEventCause
		   });
};

var renderListEventCause=function(data){
	$.each(data, function(index,event_cause){
	$('#eventCauseList').append('<li>'+ event_cause.id.eventCode + event_cause.id.eventId + event_cause.description +
						  '</li>');
						  });
}

$(document).ready(function(){
	findAllEventCause();
});

//base_data
var rootUrlBaseData="http://localhost:8080/mase2-project/rest/basedatas";
var findAllBaseData=function(){
	$.ajax({
		   type:'GET',
		   url: rootUrlBaseData,
		   dataType:"json",
		   success:renderListBaseData
		   });
};

var renderListBaseData=function(data){
	$.each(data, function(index,base_data){
	$('#baseDataList').append('<li>'+ base_data.id.eventId + base_data.id.causeCode + base_data.cellId + base_data.duration + base_data.hier3Id + 
			base_data.hier32Id + base_data.hier321Id + base_data.imsi + base_data.neVersion + base_data.eventCause + base_data.ue +  base_data.failureClassBean + '</li>');
						  });
}

$(document).ready(function(){
	findAllBaseData();
});




