function drilldown(data){
	$( document ).ready(function() {
		$("#panel_graph").append("<button class='ui-button ui-widget ui-corner-all' onclick='goBack()'>< back</button>");
	});
	Highcharts.chart('panel_graph', {
      chart: {
        type: 'column'
      },
      title: {
        text: 'Unique Cause Codes'
      },
      xAxis: {
        type: 'category',
        categories: data.map(function(x) {
          return x.eventCause.id.eventCode;
        }),
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
        pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.0f}</b> Occurrences<br/>'
      },
      series: [{
        colorByPoint: true,
        data: data.map(function(x, i) {
          return x.count * 1;
        })
		}],
    });
}

function goBack() {
	modelDataGraph();
}