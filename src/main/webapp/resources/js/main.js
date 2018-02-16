// JavaScript Document
var rootUrl="http://localhost:8080/mase2-project/rest/mcc_mnc";
var findAllMcc_Mnc=function(){
	$.ajax({
		   type:'GET',
		   url: rootUrl,
		   dataType:"json",
		   success:renderList
		   });
};

var renderList=function(data){
	$.each(data, function(index,mcc_mnc){
	$('#mcc_mncList').append('<li>'+ mcc_mnc.id.mcc + mcc_mnc.id.mnc +
						   mcc_mnc.country + mcc_mnc.operator +'</li>');
						  });
}

$(document).ready(function(){
	
});

$(document).ready(function() {
	findAllMcc_Mnc();
	$(document).ready(function(){
	    $('#mcc_mncList').DataTable();
	});
} );

