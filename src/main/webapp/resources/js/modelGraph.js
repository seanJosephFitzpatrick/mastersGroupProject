var rootUrlGraph = "http://localhost:8080/mase2-project/rest/basedatas/mg/";
var rootUrlDrilldown = "http://localhost:8080/mase2-project/rest/basedatas/mgd/";

var modelDataGraph = function() {
	$.ajaxSetup({
		  headers : {
				'Authorization' : 'Basic ' + sessionStorage.getItem("email") + ":"
				+ sessionStorage.getItem("password")
		  }
	});
	$.getJSON(rootUrlGraph + model, function(data) {
		var chartTitle = 'Unique Event ID';
		if(!Object.keys(data).length > 0){
			chartTitle = 'No Data Available';
		}
		
	    Highcharts.chart('panel_graph', {
	      chart: {
	        type: 'column'
	      },
	      title: {
	          style: {
	              color: '#00275E',
	              fontWeight: 'bold',
	              fontSize:'24px'
	          },
	          text: chartTitle
	      },
	      xAxis: {
	    	gridLineWidth: 0,
	        type: 'category',
            labels: {
                style: {
                    color: '#00275E',
                    fontSize:'15px'
                }
            },
            lineColor: '#00275E',
            lineWidth: 1,
	        title: {
	            style: {
	                color: '#00275E',
	                fontSize:'24px'
	            },
		        text: "Event ID"
		    },
	        categories: data.map(function(x) {
	          return x.eventCause.id.eventId;
	        })
	      },
	      yAxis: {
	    	  gridLineWidth: 0,
	    	  labels: {
			    style: {
			        color: '#00275E',
			        fontSize:'15px'
			    }
			},
            lineColor: '#00275E',
            lineWidth: 1,
	        title: {
	            style: {
	                color: '#00275E',
	                fontSize:'24px'
	            },
	            text: 'Occurrences'
	        },
	      },
	      legend: {
	        enabled: false
	      },
	      plotOptions: {
	        series: {
	          borderWidth: 0,
	          cursor: 'pointer',
	          point: {
	              events: {
	                  click: function () {
	                	  eId = this.category;
							$.getJSON(rootUrlDrilldown + model + '/' + this.category, function(data) {
								drilldown(data, eId);
							});	
	                  }
	              }
	          },
	          dataLabels: {
	            enabled: true,
	            format: '{point.y:.0f}'
	          }
	        }
	      },
	      tooltip: {
		          formatter: function () {
		              return 'Event ID: <b>' + this.x + ',' +
		                  '</b> Number of Occurrences: <b>' + this.y + '</b>';
		          }
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
