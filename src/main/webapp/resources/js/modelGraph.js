var rootUrlGraph = "http://localhost:8080/mase2-project/rest/basedatas/mg/";

var modelDataGraph = function() {
  $.getJSON(rootUrlGraph + model, function(data) {
    console.log(data);
	if(!Object.keys(data).length > 0){
		//$("#graph").replaceWith("<h1>No Data Available</h1>");
	}
	
    Highcharts.chart('panel_graph', {
      chart: {
        type: 'column'
      },
      title: {
        text: 'Model Codes'
      },
      subtitle: {
        text: 'Unique Cause Code and Event Id Failures'
      },
      xAxis: {
        type: 'category',
        categories: data.map(function(x) {
          return x.eventCause.description;
        })
      },
      yAxis: {
        title: {
          text: 'Number of Occurrences'
        }
      },
      legend: {
        enabled: false
      },
      plotOptions: {
        series: {
          borderWidth: 0,
          dataLabels: {
            enabled: true,
            format: '{point.y:.0f}'
          }
        }
      },
      tooltip: {
        //headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
        pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.0f}</b> Occurrences<br/>'
      },
      series: [{
        colorByPoint: true,
        data: data.map(function(x) {
          return x.count * 1;
        })
		}],
    });
  });
}