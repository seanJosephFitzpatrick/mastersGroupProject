var rootUrlDurationFailures = "http://localhost:8080/mase2-project/rest/basedatas/graph/";

var showSumAndCountGraph = function(data) {

	var data_p = [];
	var totalCount = 0;
	var range1_data = [];
	var range2_data = [];
	var range3_data = [];
	var range4_data = [];
	var range5_data = [];
	var range6_data = [];
	var range7_data = [];
	var range8_data = [];
	var range9_data = [];
	var range10_data = [];

	
			var maxOfDuration = 0;
			for (var i = 0; i < data.length; i++) {
				if (data[i].sum / data[i].count > maxOfDuration) {
					maxOfDuration = data[i].sum / data[i].count;
				}
			}

			var range_length = Math.ceil(maxOfDuration / 10);
			console.log("maxOfDuration " + maxOfDuration);
			console.log("range_length " + range_length);
			var range1_imsi_no = 0;
			var range2_imsi_no = 0;
			var range3_imsi_no = 0;
			var range4_imsi_no = 0;
			var range5_imsi_no = 0;
			var range6_imsi_no = 0;
			var range7_imsi_no = 0;
			var range8_imsi_no = 0;
			var range9_imsi_no = 0;
			var range10_imsi_no = 0;
			var range1_imsi_counter = 0;
			var range2_imsi_counter = 0;
			var range3_imsi_counter = 0;
			var range4_imsi_counter = 0;
			var range5_imsi_counter = 0;
			var range6_imsi_counter = 0;
			var range7_imsi_counter = 0;
			var range8_imsi_counter = 0;
			var range9_imsi_counter = 0;
			var range10_imsi_counter = 0;

			for (var i = 0; i < data.length; i++) {
				if (data[i].sum / data[i].count <= range_length) {
					range1_imsi_no++;
					range1_data.push({
						y : data[i].count,
						label : ++range1_imsi_counter,
						label2 : data[i].imsi
					})

				} else if (data[i].sum / data[i].count <= range_length * 2) {
					range2_imsi_no++;
					range2_data.push({
						y : data[i].count,
						label : ++ range3_imsi_counter,
						label2 : data[i].imsi
					})

				} else if (data[i].sum / data[i].count <= range_length * 3) {
					range3_imsi_no++;
					range3_data.push({
						y : data[i].count,
						label : ++ range3_imsi_counter,
						label2 : data[i].imsi
					})

				} else if (data[i].sum / data[i].count <= range_length * 4) {
					range4_imsi_no++;
					range4_data.push({
						y : data[i].count,
						label : ++ range4_imsi_counter,
						label2 : data[i].imsi
					})

				} else if (data[i].sum / data[i].count <= range_length * 5) {
					range5_imsi_no++;
					range5_data.push({
						y : data[i].count,
						label : ++ range5_imsi_counter,
						label2 : data[i].imsi
					})

				} else if (data[i].sum / data[i].count <= range_length * 6) {
					range6_imsi_no++;
					range6_data.push({
						y : data[i].count,
						label : ++ range6_imsi_counter,
						label2 : data[i].imsi
					})

				} else if (data[i].sum / data[i].count <= range_length * 7) {
					range7_imsi_no++;
					range7_data.push({
						y : data[i].count,
						label : ++ range7_imsi_counter,
						label2 : data[i].imsi
					})

				} else if (data[i].sum / data[i].count <= range_length * 8) {
					range8_imsi_no++;
					range8_data.push({
						y : data[i].count,
						label : ++ range8_imsi_counter,
						label2 : data[i].imsi
					})

				} else if (data[i].sum / data[i].count <= range_length * 9) {
					range9_imsi_no++;
					range9_data.push({
						y : data[i].count,
						label : ++ range9_imsi_counter,
						label2 : data[i].imsi
					})

				} else if (data[i].sum / data[i].count <= range_length * 10) {
					range10_imsi_no++;
					range10_data.push({
						y : data[i].count,
						label : ++ range10_imsi_counter,
						label2 : data[i].imsi
					})

				}

			}
			data_p.push({
				name : "Range 1: 1 to " + range_length,
				y : range1_imsi_no
			});
			data_p.push({
				name : 'Range 2: ' + (range_length + 1) + ' - ' + (range_length * 2),
				y : range2_imsi_no
			});
			data_p.push({
				name : 'Range 3: ' + (range_length * 2 + 1) + ' - ' + (range_length * 3),
				y : range3_imsi_no
			});
			data_p.push({
				name : 'Range 4: ' + (range_length * 3 + 1) + ' - ' + (range_length * 4),
				y : range4_imsi_no
			});
			data_p.push({
				name : 'Range 5: ' + (range_length * 4 + 1) + ' - ' + (range_length * 5),
				y : range5_imsi_no
			});
			data_p.push({
				name : 'Range 6: ' + (range_length * 5 + 1) + ' - ' + (range_length * 6),
				y : range6_imsi_no
			});
			data_p.push({
				name : 'Range 7: ' + (range_length * 6 + 1) + ' - ' + (range_length * 7),
				y : range7_imsi_no
			});
			data_p.push({
				name : 'Range 8: ' + (range_length * 7 + 1) + ' - ' + (range_length * 8),
				y : range8_imsi_no
			});
			data_p.push({
				name : 'Range 9: ' + (range_length * 8 + 1) + ' - ' + (range_length * 9),
				y : range9_imsi_no
			});

			data_p.push({
				name : 'Range 10: ' + (range_length * 9 + 1) + ' - ' + (range_length * 10),
				y : range10_imsi_no
			});

			
			
			var imsiFailureData = [ {
				click : durationChartDrilldownHandler,
				cursor : "pointer",
				fontColor : '#00275E',
				explodeOnClick : false,
				innerRadius : "35%",
				legendMarkerType : "square",
				name : "Grouped IMSI by failure duration",
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
						fontColor : '#00275E',
						text : "IMSIs grouped by failure duration"
					},
					legend : {
						fontFamily : "calibri",
						fontSize : 14,
						itemTextFormatter : function(e) {
							return e.dataPoint.name + ": " + e.dataPoint.y;
						}
					},
					data : []
				};
			
			var chart = new CanvasJS.Chart("chartContainer", imsiFailureChartOptions);
			chart.options.data = imsiFailureData;
			chart.render();

			
			$("#backButton").click(function() {
				$(this).toggleClass("invisible");
				chart = new CanvasJS.Chart("chartContainer", imsiFailureChartOptions);
				chart.options.data = imsiFailureData;
				chart.render();
			});
			
			function durationChartDrilldownHandler(e) {
				$("#backButton").toggleClass("invisible");
				//console.log("durationChartDrilldownHandler");
				var str = e.dataPoint.name;
				//console.log('label length: ' + str.length);
				
				var rangeStr = str.substring(0,8);
				//console.log('label range: ' + rangeStr+ ' '+ rangeStr.length);
				
				var drildown_data = [];
				var str = e.dataPoint.name;
				
				console.log("rangeStr ---" + rangeStr+'---');
//				console.log("rangeStr.localeCompare(Range 1: )" + rangeStr.localeCompare("Range 1:"));
//				console.log("rangeStr == (Range 1: )" + "Range 1: " == "Range 1: ");
//				console.log("rangeStr === (Range 1: )" + "Range 1: "  === "Range 1: ");
				
				if (rangeStr.localeCompare("Range 1:") == 0){
					console.log("Range 1 range1_data" +range1_data.length );
					drildown_data = range1_data;
				} else if (rangeStr === "Range 2:") {
					console.log("Range 2 range2_data" +range2_data.length );
					drildown_data =  range2_data;
				} else if (rangeStr === "Range 3:") {
					console.log("Range 3 range2_data" +range3_data.length );
					drildown_data = range3_data;
				} else if (rangeStr === "Range 4:") {
					console.log("Range 4 range2_data" +range4_data.length );
					drildown_data = range4_data;
				} else if (rangeStr === "Range 5:") {
					console.log("Range 5 range2_data" +range5_data.length );
					drildown_data = range5_data;
				} else if (rangeStr === "Range 6:") {
					console.log("Range 6 range2_data" +range6_data.length );
					drildown_data = range6_data;
				} else if (rangeStr === "Range 7:") {
					console.log("Range 7 range2_data" +range7_data.length );
					drildown_data = range7_data;
				} else if (rangeStr === "Range 8:") {
					console.log("Range 8 range2_data" +range8_data.length );
					drildown_data = range8_data;
				} else if (rangeStr === "Range 9:") {
					console.log("Range 9 range2_data" +range9_data.length );
					drildown_data = range9_data;
				} else if (rangeStr === "Range 10") {
					console.log("Range 10 range2_data" +range10_data.length );
					drildown_data = range10_data;
				}
				
				var durationDrilldownedChartOptions = {
					animationEnabled : true,
					theme : "light2",
					axisX : {
						labelFontColor : "#00275E",
						titleFontColor : "#00275E",
						title: "IMSIs in the range",
						lineColor : "#00275E",
						tickColor : "#00275E"
					},
					axisY : {
						gridThickness : 0,
						includeZero : false,
						labelFontColor : "#00275E",
						titleFontColor : "#00275E",
						title: "Number of occurrences",
						lineColor : "#00275E",
						tickColor : "#00275E",
						lineThickness : 1
					},
					data : []
				}

				var durationFailureData = [ {
					color : "#546BC1",
					fontColor : "#00275E",
					name : "Drill Down",
					type : "column",
					dataPoints : drildown_data 
				} ];

				chart = new CanvasJS.Chart("chartContainer", durationDrilldownedChartOptions);
				chart.options.data = durationFailureData;
				chart.options.toolTip = {
					content : "IMSI {label2}: {y}",
				}; 
				chart.options.title = {
					text : e.dataPoint.name,
					fontColor : "#00275E",
					fontSize : 24,
				};
				chart.render();
				
			}
		
}


