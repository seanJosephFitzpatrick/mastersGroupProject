function drilldown(data, eId){
	$( document ).ready(function() {
		$("#panel_graph").append("<button class='ui-button ui-widget ui-corner-all' onclick='goBack()'>< back</button>");
	});
	Highcharts.chart('panel_graph', {
      chart: {
        type: 'column'
      },
      title: {
        text: 'Unique Cause Codes for Event ID ' + eId
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
          formatter: function () {
              return 'Cause Code: <b>' + this.x + ',' +
                  '</b> Number of Occurrences: <b>' + this.y + '</b>';
          }
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