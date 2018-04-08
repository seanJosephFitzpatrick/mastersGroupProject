/**
 * Navigation
 */
function showNavigation() {
	
	role = sessionStorage.getItem("role");
	// hide all elements
	$('.bg-dark').css("cssText","background-color: #FFFFFF !important;");
	$('footer').hide();
	$('#mainNav').hide();
	$('#nav_logout').hide();
	$('#nav_Tables').hide();
	$('#nav_NDA').hide();
	$('#nav_nmeQueries').hide();
	$('#nav_seQueries').hide();
	$('#nav_ImportAll').hide();
	$('#nav_csrQueries').hide();
	$('#nav_ImportAllData').hide();
	$('#nav_userMgmt').hide();
	$('#dateQuery').hide();
	$('#countFailuresQuery').hide();
	$('#sumDurationQuery').hide();
	$('#topTenQuery').hide();
	$('#uniqueIdAndCodeQuery').hide();
	$('#Filestoggle').hide();
	$('.filelist').hide();
	$('#IMSIsforGivenFailureClass').hide();
	$('#topTenIMSIQuery').hide();

	
	if(role == "admin" || role == "manager" || role == "support" || role == "customer") {
		$('.bg-dark').css("cssText","background-color: #00275E !important;");
		$('footer').show();
		$('#mainNav').show();
		showDashboard();
		$('#nav_NDA').show();
		$('#nav_logout').show();
		$('#nav_csrQueries').show();
	}
	
	if(role == "admin" || role == "manager" || role == "support") {
		$('#dateQuery').show();
		$('#countFailuresQuery').show();
		$('#IMSIsforGivenFailureClass').show();
		$('#nav_seQueries').show();
	}	
	
	if(role == "admin" || role == "manager") {
		$('#uniqueIdAndCodeQuery').show();
		$('#sumDurationQuery').show();
		$('#topTenQuery').show();
		$('#topTenIMSIQuery').show();
		$('#nav_nmeQueries').show();
		
	}	
	if(sessionStorage.getItem("role") == "admin") {
		$('#nav_userMgmt').show();
		$('#nav_Tables').show();
		$('#Filestoggle').show();
		
	}	
}