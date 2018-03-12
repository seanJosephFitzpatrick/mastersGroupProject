/**
 * Navigation
 */
function showNavigation() {
	role = sessionStorage.getItem("role");
	// hide all elements
	$('#nav_logout').hide();
	$('#nav_Tables').hide();
	$('#nav_NDA').hide();
	$('#nav_Queries').hide();
	$('#nav_ImportAll').hide();
	$('#nav_ImportAllData').hide();
	$('#nav_userMgmt').hide();
	
	if(role == "admin" || role == "manager" || role == "support" || role == "customer") {
		$('#nav_NDA').show();
		$('#nav_logout').show();
		
		$('#nav_Queries').show();
		$('#nav_Tables').show();
	}
	
	if(role == "admin" || role == "manager" || role == "support") {
		
	}	
	
	if(role == "admin" || role == "manager") {
	}	
	if(sessionStorage.getItem("role") == "admin") {
		$('#nav_userMgmt').show();
		$('#nav_ImportAll').show();
		$('#nav_ImportAllData').show();
		
	}	
}