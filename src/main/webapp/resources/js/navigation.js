/**
 * Navigation
 */
function showNavigation() {
	$('.content-wrapper').css({backgroundImage:"url(./resources/css/blahblahblah.jpg)"});
	$('.content-wrapper').css({backgroundSize:"cover"});
	$('.content-wrapper').css({backgroundRepeat:"no-repeat"});
	$('.content-wrapper').css({backgroundPosition:"center center"});
	role = sessionStorage.getItem("role");
	// hide all elements
	$('#nav_logout').hide();
	$('#nav_Tables').hide();
	$('#nav_NDA').hide();
	$('#nav_Queries').hide();
	$('#nav_ImportAll').hide();
	$('#nav_ImportAllData').hide();
	$('#nav_userMgmt').hide();
	$('#dateQuery').hide();
	$('#countFailuresQuery').hide();
	$('#sumDurationQuery').hide();
	$('#topTenQuery').hide();
	$('#uniqueIdAndCodeQuery').hide();
	$('#Filestoggle').hide();
	$('.filelist').hide();

	
	if(role == "admin" || role == "manager" || role == "support" || role == "customer") {
		$('#nav_NDA').show();
		$('#nav_logout').show();
		$('#nav_Queries').show();	
	}
	
	if(role == "admin" || role == "manager" || role == "support") {
		$('#dateQuery').show();
		$('#countFailuresQuery').show();	
	}	
	
	if(role == "admin" || role == "manager") {
		$('#uniqueIdAndCodeQuery').show();
		$('#sumDurationQuery').show();
		$('#topTenQuery').show();
		
	}	
	if(sessionStorage.getItem("role") == "admin") {
		$('#nav_userMgmt').show();
		$('#nav_Tables').show();
		$('#Filestoggle').show();
		
	}	
}