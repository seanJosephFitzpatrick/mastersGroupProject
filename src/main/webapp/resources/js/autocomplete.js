var rootUrlIMSIAutoQuery = "http://localhost:8080/mase2-project/rest/basedatas/aci/";
var rootUrlModelAutoQuery = "http://localhost:8080/mase2-project/rest/basedatas/acm/";

function imsiautocomplete(){
	$("#imsi").autocomplete({
		source: rootUrlIMSIAutoQuery + document.getElementById('imsi').value
	})
}

function modelautocomplete(){
	$("#model").autocomplete({
		source: rootUrlModelAutoQuery + document.getElementById('model').value
	})
}