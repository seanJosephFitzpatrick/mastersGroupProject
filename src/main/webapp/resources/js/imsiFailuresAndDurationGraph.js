var rootUrlDurationFailures = "http://localhost:8080/mase2-project/rest/basedatas/graph/";

var showSumAndCountGraph = function(data1, data2) {

	var data_p = [];
	var totalCount = 0;

	$.ajax({
		type : 'GET',
		url : rootUrlSumDurationAndCountFailures + data1 + "&EndDate=" + data2,
		dataType : "json",
		headers : {
			'Authorization' : 'Basic ' + sessionStorage.getItem("email") + ":" + sessionStorage.getItem("password")
		},
		success : function(data) {
			console.log("showSumAndCountGraph getJSON" + data[0]);
			for (var i = 0; i < data.length; i++) {
				console.log("huj" + data[i].imsi);
				data_p.push({
					name : data[i].imsi,
					y : parseInt(data[i].count)
				});
				totalCount += data[i].count
			}

			console.log('total_count --' + totalCount)
			console.log("data_p.length - " + data_p.length);

			var imsiFailureData = [ {

				click : durationChartDrilldownHandler,
				cursor : "pointer",
				explodeOnClick : false,
				innerRadius : "35%",
				legendMarkerType : "square",
				name : "New vs Returning Visitors",
				radius : "100%",
				showInLegend : true,
				startAngle : 90,
				type : "doughnut",
				dataPoints : data_p
			} ];

			var imsiFailureChartOptions = {
				animationEnabled : true,
				theme : "light2",
				title : {
					text : "IMSI Failures Number"
				},
				subtitles : [ {
					text : "Click on Any Segment to Drilldown",
					backgroundColor : "#2eacd1",
					fontSize : 16,
					fontColor : "white",
					padding : 5
				} ],
				legend : {
					fontFamily : "calibri",
					fontSize : 14,
					itemTextFormatter : function(e) {
						return e.dataPoint.name + ": " + e.dataPoint.y + ' (' + Math.round(e.dataPoint.y / totalCount * 100) + "%)";
					}
				},
				data : []
			};

			var durationDrilldownedChartOptions = {
				animationEnabled : true,
				theme : "light2",
				axisX : {
					labelFontColor : "#717171",
					lineColor : "#a2a2a2",
					tickColor : "#a2a2a2"
				},
				axisY : {
					gridThickness : 0,
					includeZero : false,
					labelFontColor : "#717171",
					lineColor : "#a2a2a2",
					tickColor : "#a2a2a2",
					lineThickness : 1
				},
				data : []

			};

			var chart = new CanvasJS.Chart("chartContainer", imsiFailureChartOptions);
			chart.options.data = imsiFailureData;
			chart.render();

			function durationChartDrilldownHandler(e) {
				console.log("durationChartDrilldownHandler");

				$.ajax({
					type : 'GET',
					url : rootUrlDurationFailures + e.dataPoint.name + "/" + data1 + "/" + data2,
					dataType : "json",
					headers : {
						'Authorization' : 'Basic ' + sessionStorage.getItem("email") + ":" + sessionStorage.getItem("password")
					},
					success : function(data) {
						console.log("hire with ");
						var dataDuration = [];
						for (var i = 0; i < data.length; i++) {
							dataDuration.push({
								y : parseInt(data[i].duration),
								label : i + 1,
								label2 : new Date(data[i].dateTime)
							});
						}
						console.log("hire with dataDuration " + dataDuration.length);

						var durationFailureData = [ {
							color : "#546BC1",
							name : "Returning Visitors",
							type : "column",
							dataPoints : dataDuration,
						} ];

						chart = new CanvasJS.Chart("chartContainer", durationDrilldownedChartOptions);
						chart.options.data = durationFailureData;
						chart.options.toolTip = {
							content : "My {label2}: {y}"
						}, chart.options.title = {
							text : e.dataPoint.name
						}
						chart.render();
						$("#backButton").toggleClass("invisible");
					},
				});
			}

			$("#backButton").click(function() {
				$(this).toggleClass("invisible");
				chart = new CanvasJS.Chart("chartContainer", imsiFailureChartOptions);
				chart.options.data = imsiFailureData;
				chart.render();
			});
		}
	});
}
