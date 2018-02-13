// JavaScript Document
var rootUrl="http://localhost:8080/mase2-project/rest/mcc_mncs";
var findAll=function(){
	$.ajax({
		   type:'GET',
		   url: rootUrl,
		   dataType:"json",
		   success:renderList
		   });
};

var renderList=function(data){
	$.each(data, function(index,mcc_mnc){
	$('#mcc_mncList').append('<li><a href="#" mcc="'+mcc_mnc.mcc+
						  '">'+mcc_mnc.mnc+mcc_mnc.country+mcc_mnc.operator+'</a></li>');
						  });
}

$(document).ready(function(){
	findAllMccMncs();
});