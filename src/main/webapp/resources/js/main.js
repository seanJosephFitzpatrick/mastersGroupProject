// JavaScript Document
var rootUrl="http://localhost:8080/mase2-project/rest/mcc_mnc";
var findAllMccMncs=function(){
	$.ajax({
		   type:'GET',
		   url: rootUrl,
		   dataType:"json",
		   success:renderList
		   });
};

var renderList=function(data){
	$.each(data, function(index,mcc_mnc){
	$('#mcc_mncList').append('<li><a href="#" id="'+mcc_mnc.id.mnc+mcc_mnc.id.mcc+
						  '">'+mcc_mnc.country+mcc_mnc.operator+'</a></li>');
						  });
}

$(document).ready(function(){
	findAllMccMncs();
});

