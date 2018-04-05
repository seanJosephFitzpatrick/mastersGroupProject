var showTop10IMSIsGraph = function(data1, data2) {

	var data_p = [];
	var imsiCount = 0;
	var totalCount = 0;
	var numRanges = 10;
	var rangeLength = 0;
	var numFailures = 0;
	var average = 0;
	var label = '';

	var value = 0;

	$.ajax({
				type : 'GET',
				url : rootUrlTop10IMSIs + data1 + "&EndDate=" + data2,
				dataType : "json",
				headers : {
					'Authorization' : 'Basic '
							+ sessionStorage.getItem("email") + ":"
							+ sessionStorage.getItem("password")
				},
				success : function(data) {
					console.log("showSumAndCountGraph getJSON" + data[0]);
					var subModel10 = [];
					for (var i = 0; i < data.length; i++) {
						//				
						//				
						//				
						//				
						// console.log("imsi - " + data[i].imsi);
						// console.log("imsi failures - " + data[i].count);
						//				
						// data_p.push({
						// name : data[i].imsi,
						// y : parseInt(data[i].count)
						// });
						totalCount += data[i].count
					}

					average = totalCount / numRanges;
					rangeLength = Math.ceil(average / data.length);
					console.log("average " + average);
					console.log("range length " + Math.ceil(rangeLength));
					console.log('total_count --' + totalCount);

					var label1 = '1-' + rangeLength;
					var label2 = rangeLength + 1 + '-' + rangeLength * 2;
					var label3 = rangeLength * 2 + 1 + '-' + rangeLength * 3;
					var label4 = rangeLength * 3 + 1 + '-' + rangeLength * 4;
					var label5 = rangeLength * 4 + 1 + '-' + rangeLength * 5;
					var label6 = rangeLength * 5 + 1 + '-' + rangeLength * 6;
					var label7 = rangeLength * 6 + 1 + '-' + rangeLength * 7;
					var label8 = rangeLength * 7 + 1 + '-' + rangeLength * 8;
					var label9 = rangeLength * 8 + 1 + '-' + rangeLength * 9;
					var label10 = rangeLength * 9 + 1 + '-' + rangeLength * 10;

					var yValue1 = 0;
					var yValue2 = 0;
					var yValue3 = 0;
					var yValue4 = 0;
					var yValue5 = 0;
					var yValue6 = 0;
					var yValue7 = 0;
					var yValue8 = 0;
					var yValue9 = 0;
					var yValue10 = 0;
					

					for (i = 0; i < data.length; i++) {
						if (data[i].count <= rangeLength) {

							// data_p.push({
							// name: label1,
							// y : ++yValue1
							// });
							++yValue1;

						} else if (data[i].count <= rangeLength * 2) {
							// data_p.push({
							// name: label2,
							// y : ++yValue2
							// });
							++yValue2;

						} else if (data[i].count <= rangeLength * 3) {
							// data_p.push({
							// name: label3,
							// y : ++yValue3
							// });
							++yValue3;

						} else if (data[i].count <= rangeLength * 4) {
							++yValue4;

						} else if (data[i].count <= rangeLength * 5) {
							++yValue5;

						} else if (data[i].count <= rangeLength * 6) {
							++yValue6;

						} else if (data[i].count <= rangeLength * 7) {
							++yValue7;

						} else if (data[i].count <= rangeLength * 8) {
							++yValue8;

						} else if (data[i].count <= rangeLength * 9) {
							++yValue9;

						} else if (data[i].count <= rangeLength * 10) {
							++yValue10;
							subModel10.push({
								label : data[i].imsi,
								y : data[i].count
							});

						}
					}

					data_p.push({
						name : label1,
						y : yValue1
					});

					data_p.push({
						name : label2,
						y : yValue2
					});

					data_p.push({
						name : label3,
						y : yValue3
					});

					data_p.push({
						name : label4,
						y : yValue4
					});

					data_p.push({
						name : label5,
						y : yValue5
					});

					data_p.push({
						name : label6,
						y : yValue6
					});

					data_p.push({
						name : label7,
						y : yValue7
					});

					data_p.push({
						name : label8,
						y : yValue8
					});

					data_p.push({
						name : label9,
						y : yValue9
					});

					data_p.push({
						name : label10,
						y : yValue10
					});

					var totalVisitors = 883000;
					//ten of there
					var failureDrillDownData = 
							[ {
								color : "#E7823A",
								name : "New Visitors",
								type : "column",
							    dataPoints : subModel10
							
				
					}]
					var failureData = [{
						 
							//click : visitorsChartDrilldownHandler,
							click : failureChartHandler,
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
						
					}];

					var newVSReturningVisitorsOptions = {
						animationEnabled : true,
						theme : "light2",
						title : {
							text : "New VS Returning Visitors"
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
								return e.dataPoint.name + ": " + e.dataPoint.y;
							}
						},
						data : []
					};

					var visitorsDrilldownedChartOptions = {
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

					var chart = new CanvasJS.Chart("chartContainer",
							newVSReturningVisitorsOptions);
					chart.options.data = failureData;
					chart.render();

					function failureChartHandler(e) {
						chart = new CanvasJS.Chart("chartContainer",
								visitorsDrilldownedChartOptions);
						chart.options.data = failureDrillDownData;
						chart.options.title = {
							text : e.dataPoint.name
						}
						chart.render();
						$("#backButton").toggleClass("invisible");
					}

					$("#backButton")
							.click(
									function() {
										$(this).toggleClass("invisible");
										chart = new CanvasJS.Chart(
												"chartContainer",
												newVSReturningVisitorsOptions);
										chart.options.data = failureData;
										chart.render();
									});
				}
			});
}
