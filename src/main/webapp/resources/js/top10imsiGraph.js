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
			'Authorization' : 'Basic ' + sessionStorage.getItem("email") + ":"
					+ sessionStorage.getItem("password")
		},
		success : function(data) {
			console.log("showSumAndCountGraph getJSON" + data[0]);

			var subModel1 = [];
			var subModel2 = [];
			var subModel3 = [];
			var subModel4 = [];
			var subModel5 = [];
			var subModel6 = [];
			var subModel7 = [];
			var subModel8 = [];
			var subModel9 = [];
			var subModel10 = [];

			for (var i = 0; i < data.length; i++) {
				totalCount += data[i].count
			}

			average = totalCount / data.length;
			rangeLength = Math.ceil(average / data.length);
			console.log("average " + average);
			console.log("range length " + Math.ceil(rangeLength));
			console.log('total_count --' + totalCount);

			var label1 = 'Range 1: 1 - ' + rangeLength;
			console.log(label1);
			var label2 = 'Range 2: '+(rangeLength + 1 + '-' + rangeLength * 2);
			console.log(label2);
			var label3 = 'Range 3: '+(rangeLength * 2 + 1 + '-' + rangeLength * 3);
			console.log(label3);
			var label4 = 'Range 4: '+(rangeLength * 3 + 1 + '-' + rangeLength * 4);
			console.log(label4);
			var label5 = 'Range 5: '+(rangeLength * 4 + 1 + '-' + rangeLength * 5);
			console.log(label5);
			var label6 = 'Range 6: '+(rangeLength * 5 + 1 + '-' + rangeLength * 6);
			console.log(label6);
			var label7 = 'Range 7: '+(rangeLength * 6 + 1 + '-' + rangeLength * 7);
			console.log(label7);
			var label8 = 'Range 8: '+(rangeLength * 7 + 1 + '-' + rangeLength * 8);
			console.log(label8);
			var label9 = 'Range 9: '+(rangeLength * 8 + 1 + '-' + rangeLength * 9);
			console.log(label9);
			var label10 = 'Range 10: '+(rangeLength * 9 + 1 + '-' + rangeLength * 10);
			console.log(label10);
			
//			var label1 = '1 - ' + rangeLength;
//			console.log(label1);
//			var label2 = rangeLength + 1 + '-' + rangeLength * 2;
//			console.log(label2);
//			var label3 = rangeLength * 2 + 1 + '-' + rangeLength * 3;
//			var label4 = rangeLength * 3 + 1 + '-' + rangeLength * 4;
//			var label5 = rangeLength * 4 + 1 + '-' + rangeLength * 5;
//			var label6 = rangeLength * 5 + 1 + '-' + rangeLength * 6;
//			var label7 = rangeLength * 6 + 1 + '-' + rangeLength * 7;
//			var label8 = rangeLength * 7 + 1 + '-' + rangeLength * 8;
//			var label9 = rangeLength * 8 + 1 + '-' + rangeLength * 9;
//			var label10 = rangeLength * 9 + 1 + '-' + rangeLength * 10;
			
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
				if(data[i].count == 0){
					
				}
				else if (data[i].count <= rangeLength) {
					console.log('in loop - range <= ' + rangeLength);
					++yValue1;
					subModel1.push({
						//range : 'Range 1: ',
						label : data[i].imsi,
						y : data[i].count
					});

				} else if (data[i].count <= rangeLength * 2) {
					console.log('in loop - range <= ' + rangeLength * 2)
					++yValue2;
					subModel2.push({
						//range : 'Range 2: ',
						label : data[i].imsi,
						y : data[i].count
					});

				} else if (data[i].count <= rangeLength * 3) {
					console.log('in loop - range <= ' + rangeLength * 3)
					++yValue3;
					subModel3.push({
						//range : 'Range 3: ',
						label : data[i].imsi,
						y : data[i].count
					});

				} else if (data[i].count <= rangeLength * 4) {
					console.log('in loop - range <= ' + rangeLength * 4)
					++yValue4;
					subModel4.push({
						//range : 'Range 4: ',
						label : data[i].imsi,
						y : data[i].count
					});

				} else if (data[i].count <= rangeLength * 5) {
					console.log('in loop - range <= ' + rangeLength * 5)
					++yValue5;
					subModel5.push({
						//range : 'Range 5: ',
						label : data[i].imsi,
						y : data[i].count
					});

				} else if (data[i].count <= rangeLength * 6) {
					console.log('in loop - range <= ' + rangeLength * 6)
					++yValue6;
					subModel6.push({
						//range : 'Range 6: ',
						label : data[i].imsi,
						y : data[i].count
					});

				} else if (data[i].count <= rangeLength * 7) {
					console.log('in loop - range <= ' + rangeLength * 7)
					++yValue7;
					subModel7.push({
						//range : 'Range 7: ',
						label : data[i].imsi,
						y : data[i].count
					});

				} else if (data[i].count <= rangeLength * 8) {
					console.log('in loop - range <= ' + rangeLength * 8)
					++yValue8;
					subModel8.push({
						//range : 'Range 8: ',
						label : data[i].imsi,
						y : data[i].count
					});

				} else if (data[i].count <= rangeLength * 9) {
					console.log('in loop - range <= ' + rangeLength * 9)
					++yValue9;
					subModel9.push({
						//range : 'Range 9: ',
						label : data[i].imsi,
						y : data[i].count
					});

				} else if (data[i].count <= rangeLength * 10) {
					console.log('in loop - range <= ' + rangeLength * 10)
					++yValue10;
					subModel10.push({
						//range : 'Range 10: ',
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

			// ten of these
			var failureDrillDownData1 = [ {
				color : "#E7823A",
				name : "New Visitors",
				type : "column",
				dataPoints : subModel1

			} ]

			var failureDrillDownData2 = [ {
				color : "#E7823A",
				name : "New Visitors",
				type : "column",
				dataPoints : subModel2
			} ]
			
			var failureDrillDownData3 = [ {
				color : "#E7823A",
				name : "New Visitors",
				type : "column",
				dataPoints : subModel3
			} ]
			
			var failureDrillDownData4 = [ {
				color : "#E7823A",
				name : "New Visitors",
				type : "column",
				dataPoints : subModel4
			} ]
			
			var failureDrillDownData5 = [ {
				color : "#E7823A",
				name : "New Visitors",
				type : "column",
				dataPoints : subModel5
			} ]
			
			var failureDrillDownData6 = [ {
				color : "#E7823A",
				name : "New Visitors",
				type : "column",
				dataPoints : subModel6
			} ]
			
			var failureDrillDownData7 = [ {
				color : "#E7823A",
				name : "New Visitors",
				type : "column",
				dataPoints : subModel7
			} ]
			
			var failureDrillDownData8 = [ {
				color : "#E7823A",
				name : "New Visitors",
				type : "column",
				dataPoints : subModel8
			} ]
			
			var failureDrillDownData9 = [ {
				color : "#E7823A",
				name : "New Visitors",
				type : "column",
				dataPoints : subModel9
			} ]
			
			var failureDrillDownData10 = [ {
				color : "#E7823A",
				name : "New Visitors",
				type : "column",
				dataPoints : subModel10
			} ]
			
			

			var failureData = [ {

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

			} ];

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
				
				var str = e.dataPoint.name;
				console.log('label length: ' + str.length);
				
				
				
				var rangeStr = str.substring(0,8);
				console.log('label range: ' + rangeStr);

					
				
				
				chart = new CanvasJS.Chart("chartContainer",
						visitorsDrilldownedChartOptions);
				for (i = 0; i < data.length; i++) {
						if (rangeStr == "Range 1:") {
						console.log(e.dataPoint.name);
						console.log(rangeStr);
						console.log(" in faildrilldown1 - " + data[i].count);
						chart.options.data = failureDrillDownData1;

					} else if (rangeStr == "Range 2:") {
						console.log(e.dataPoint.name);
						console.log(rangeStr);
						console.log(" in faildrilldown2 - " + data[i].count);
						chart.options.data = failureDrillDownData2;

					} else if (rangeStr === "Range 3:") {
						console.log(e.dataPoint.name);
						console.log(rangeStr);
						console.log(" in faildrilldown3 - " + data[i].count);
						chart.options.data = failureDrillDownData3;

					} else if (rangeStr === "Range 4:") {
						console.log(e.dataPoint.name);
						console.log(rangeStr);
						console.log(" in faildrilldown4 - " + data[i].count);
						chart.options.data = failureDrillDownData4;

					} else if (rangeStr === "Range 5:") {
						console.log(e.dataPoint.name);
						console.log(rangeStr);
						console.log(" in faildrilldown5 - " + data[i].count);
						chart.options.data = failureDrillDownData5;

					} else if (rangeStr === "Range 6:") {
						console.log(e.dataPoint.name);
						console.log(rangeStr);
						console.log(" in faildrilldown6 - " + data[i].count);
						chart.options.data = failureDrillDownData6;

					} else if (rangeStr === "Range 7:") {
						console.log(e.dataPoint.name);
						console.log(rangeStr);
						console.log(" in faildrilldown7 - " + data[i].count);
						chart.options.data = failureDrillDownData7;

					} else if (rangeStr == "Range 8:") {
						console.log(e.dataPoint.name);
						console.log(rangeStr);
						console.log(" in faildrilldown8 - " + data[i].count);
						chart.options.data = failureDrillDownData8;

					} else if (rangeStr === "Range 9:") {
						console.log(e.dataPoint.name);
						console.log(rangeStr);
						console.log(" in faildrilldown9 - " + data[i].count);
						chart.options.data = failureDrillDownData9;

					} else if (rangeStr === "Range 10") {
						console.log(e.dataPoint.name);
						console.log(rangeStr);
						console.log(" in faildrilldown10 - " + data[i].count);
						chart.options.data = failureDrillDownData10;
					}
				}

				chart.options.title = {
					text : e.dataPoint.name
				}
				chart.render();
				$("#backButton").toggleClass("invisible");

			}

			$("#backButton").click(
					function() {
						$(this).toggleClass("invisible");
						chart = new CanvasJS.Chart("chartContainer",
								newVSReturningVisitorsOptions);
						chart.options.data = failureData;
						chart.render();
					});
		}
	});
}
