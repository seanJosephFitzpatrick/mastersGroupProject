var showTop10IMSIsGraph = function(data1, data2) {

	var data_p = [];
	var totalCount = 0;

	$.ajax({
		type : 'GET',
		url : rootUrlTop10IMSIs + data1 + "&EndDate=" + data2,
		dataType : "json",
		headers : {
			'Authorization' : 'Basic ' + sessionStorage.getItem("email") + ":" + sessionStorage.getItem("password")
		},
		success : function(data) {
			console.log("showTop10IMSIsGraph getJSON" + data[0]);
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

			var totalVisitors = 883000;
			var visitorsData = {
				"New vs Returning Visitors" : [ {
					click : visitorsChartDrilldownHandler,
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
				} ],
				"New Visitors" : [ {
					color : "#E7823A",
					name : "New Visitors",
					type : "column",
					dataPoints : [ {
						x : new Date("1 Jan 2015"),
						y : 33000
					}, {
						x : new Date("1 Feb 2015"),
						y : 35960
					}, {
						x : new Date("1 Mar 2015"),
						y : 42160
					}, {
						x : new Date("1 Apr 2015"),
						y : 42240
					}, {
						x : new Date("1 May 2015"),
						y : 43200
					}, {
						x : new Date("1 Jun 2015"),
						y : 40600
					}, {
						x : new Date("1 Jul 2015"),
						y : 42560
					}, {
						x : new Date("1 Aug 2015"),
						y : 44280
					}, {
						x : new Date("1 Sep 2015"),
						y : 44800
					}, {
						x : new Date("1 Oct 2015"),
						y : 48720
					}, {
						x : new Date("1 Nov 2015"),
						y : 50840
					}, {
						x : new Date("1 Dec 2015"),
						y : 51600
					} ]
				} ],
				"Returning Visitors" : [ {
					color : "#546BC1",
					name : "Returning Visitors",
					type : "column",
					dataPoints : [ {
						x : new Date("1 Jan 2015"),
						y : 22000
					}, {
						x : new Date("1 Feb 2015"),
						y : 26040
					}, {
						x : new Date("1 Mar 2015"),
						y : 25840
					}, {
						x : new Date("1 Apr 2015"),
						y : 23760
					}, {
						x : new Date("1 May 2015"),
						y : 28800
					}, {
						x : new Date("1 Jun 2015"),
						y : 29400
					}, {
						x : new Date("1 Jul 2015"),
						y : 33440
					}, {
						x : new Date("1 Aug 2015"),
						y : 37720
					}, {
						x : new Date("1 Sep 2015"),
						y : 35200
					}, {
						x : new Date("1 Oct 2015"),
						y : 35280
					}, {
						x : new Date("1 Nov 2015"),
						y : 31160
					}, {
						x : new Date("1 Dec 2015"),
						y : 34400
					} ]
				} ]
			};

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
						return e.dataPoint.name + ": "+ e.dataPoint.y;
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

			var chart = new CanvasJS.Chart("chartContainer", newVSReturningVisitorsOptions);
			chart.options.data = visitorsData["New vs Returning Visitors"];
			chart.render();

			function visitorsChartDrilldownHandler(e) {
				chart = new CanvasJS.Chart("chartContainer", visitorsDrilldownedChartOptions);
				chart.options.data = visitorsData[e.dataPoint.name];
				chart.options.title = {
					text : e.dataPoint.name
				}
				chart.render();
				$("#backButton").toggleClass("invisible");
			}

			$("#backButton").click(function() {
				$(this).toggleClass("invisible");
				chart = new CanvasJS.Chart("chartContainer", newVSReturningVisitorsOptions);
				chart.options.data = visitorsData["New vs Returning Visitors"];
				chart.render();
			});
		}
	});
}

function drawGraph(topTen){
	var definitionVisualization = {};

	(function (visualization) {

	    visualization.donut = {};

	    var topLevelItem = {label: "Top Ten Failures"};

	    var subSubData = [
	        {colorIndex: 0, value: 3075, label: "Label 1"},
	        {colorIndex: 1, value: 6150, label: "Label 2"},
	        {colorIndex: 2, value: 6832, label: "Label 3"},
	        {colorIndex: 3, value: 7516, label: "Label 4"},
	        {colorIndex: 4, value: 6291, label: "Label 5"}
	    ];

	    var subData = [
	        {colorIndex: 0, value: 1000, label: "Region 1"},
	        {colorIndex: 1, value: 1000, label: "Region 2"},
	        {colorIndex: 2, value: 1000, label: "Region 3"},
	        {colorIndex: 3, value: 1000, label: "Region 4"},
	        {colorIndex: 4, value: 1200, childData: subSubData, label: "Region 5"}
	    ];
	    var data = [];
	    for(var i=0;i<topTen.length;i++){
	    	data.push({ 
	    		colorIndex: i, 
	            value: topTen[i].count,
	            childData: subData,
	            label: topTen[i].country});
	    }

	    var dataOriginal = data.slice(0); //Keep a record around for book-keeping purposes

	    var selectedPath = [];

	    function endall(transition, callback) {
	        var n = 0;
	        transition
	            .each(function () {
	                ++n;
	            })
	            .each("end", function () {
	                if (!--n) callback.apply(this, arguments);
	            });
	    }

	    // Global Variables

	    var margin = {top: 100, right: 0, bottom: 10, left: 127.5};
	    var width = 1110;
	    var height = 1800;

	    var radius = 300;

	    var transformAttrValue = function (adjustLeft) {
	        var leftValue = margin.left + radius;
	        if (adjustLeft) {
	            leftValue = leftValue + adjustLeft;
	        }
	        return "translate(" + leftValue + "," + (margin.top + radius) + ")";
	    }
	    var colorRange = ["#ffb822","#00bf8c","#219ddb","#ad85cc","#f95275","#80B647","#11AEB4","#6791D4","#D36CA1","#FC803B","#52f0fe","#d02ae6","#096c9e","#6dfc99","#87d3eb"];
	  
	    var colors = d3.scale.ordinal().range(colorRange);

	    var chart, chartLabelsGroup, chartCenterLabelGroup, chartCenterLabel,
	        arc, arcSmall, pie, path, chartSelect, chartSelectTertiary, arcOver;

	    function zoomIn(d) {

	        if (!d.data.childData) {
	            //At deepest available level
	            return false;
	        }

	        var sel = d3.select(this);
	        console.log(sel);
	        //Search the current path to see the counter where it was selected. (Also update selected path)

	        for (var i = 0; i < path[0].length; i++) {
	            if (path[0][i] == sel[0][0]) {
	                selectedPath.push(i);
	                break;
	            }
	        }

	        sel.attr("d", arc);

	        var startAngle = d.startAngle;
	        var endAngle = d.endAngle;

	        var arcSelect = d3.svg.arc()
	            .startAngle(function (s) {
	                return startAngle;
	            })
	            .endAngle(function (s) {
	                return endAngle;
	            })
	            .innerRadius(function (i) {
	                return radius - 20
	            })
	            .outerRadius(function (o) {
	                return radius * 1.1
	            });

	        var newArc = chartSelect.append('path')
	            .style("fill", colors(d.data.colorIndex))
	            .attr("d", arcSelect)
	            .on("click", zoomOut(d));

	        newArc.transition()
	            .duration(1000)
	            .attrTween("d", function () {
	                var newAngle = d.startAngle + 2 * Math.PI;
	                var interpolate = d3.interpolate(d.endAngle, newAngle);
	                return function (tick) {
	                    endAngle = interpolate(tick);
	                    return arcSelect(d);
	                };
	            })
	            .each("end", zoomInSweepEnded(d.data.childData));

	    }

	    function zoomOut(clickedItem, selectedItem) {

	        return function () {

	            //Determine the current parent item while zooming.

	            var currentItem = getCurrentItemData(1);
	            selectedPath.pop(); //Update the path

	            //If we have another parent we need to show the zoom further out arc.

	            if (selectedPath.length > 0) {
	                chartSelectTertiary.attr("style", "");
	                chartSelectTertiary.on("click", function () {
	                    //TODO: Fix this!!!
	                  zoomOut({startAngle: 0, endAngle:0}, chartSelectTertiary.select("path"))();
	                });
	            }

	            //Proceed with animations

	            selectedItem = selectedItem || d3.select(this);

	            selectedItem.on("click", null);

	            path=chart.selectAll("path").data(pie(currentItem));
	            path.exit().remove();
	            path
	                .transition()
	                .ease("back-in-out")
	                .duration(750)
	                .attr("d", arc)
	                .attr("transform", "scale(.5), rotate(-90)")
	                .style("opacity", 0)
	                .call(endall, function () {

	                	path
	                        .attr("transform", "scale(1), rotate(0)")
	                        .style("opacity", 1);
	                        
	                	 
	                        path.enter().append("path") .style("fill", function (d) {
	                            return colors(d.data.colorIndex);
	                        })
	                        .attr("d", arc)
	                        .on("mouseover", function () {
	            //return false;
	            d3.select(this).transition()
	                .ease("in")
	                .duration(100)
	                .attr("d", arcOver);
	        }).on("mouseout", function () {
	            //return false;
	            d3.select(this).transition()
	                .ease("bounce")
	                .duration(500)
	                .attr("d", arc);
	        }).on("click", zoomIn)
	                        .each(function (d, counter) {
	                            this._current = d;
	                        }); // store the initial angles
	                       
	                    updatePieLabels();
	                    

	                });
	                zoomZeArc(selectedItem, true, function () {

	                    var startAngle = clickedItem.startAngle;
	                    var endAngle = clickedItem.startAngle + 2 * Math.PI;

	                    var arcSelect = d3.svg.arc()
	                        .startAngle(function (s) {
	                            return startAngle;
	                        })
	                        .endAngle(function (s) {
	                            return endAngle;
	                        })
	                        .innerRadius(function (i) {
	                            return radius - 20;
	                        })
	                        .outerRadius(function (o) {
	                            return radius * 1.1;
	                        });

	                    var arcFinal = d3.svg.arc()
	                        .startAngle(function (s) {
	                            return startAngle;
	                        })
	                        .endAngle(function (s) {
	                            return endAngle;
	                        })
	                        .outerRadius(radius)
	                        .innerRadius(radius - 20);

	                    selectedItem.transition()
	                        .duration(750)
	                        .attrTween("d", function () {
	                            var newAngle = clickedItem.startAngle + 2 * Math.PI;
	                            var interpolate = d3.interpolate(newAngle, clickedItem.endAngle);
	                            return function (tick) {
	                                endAngle = interpolate(tick);
	                                return arcSelect(clickedItem);
	                            };
	                        })
	                        .each("end", function () {
	                            selectedItem.transition()
	                                .ease("bounce")
	                                .duration(500)
	                                .attr("d", arcFinal)
	                                .each("end", function () {
	                                    //Finished animating and bouncing
	                                    d3.select(".secondary").html("");
	                                });
	                        });

	                });

	        }
	    }

	    function zoomInSweepEnded(childData) {
	    	console.log("before");
	        return function () {
	        	
	        	
	            chartSelectTertiary.attr("style", "display: none");

	            var selectedItem = d3.select(this);

	           

	        
	          
	         

	          path=chart.selectAll("path").data(pie(childData));
	          path.exit().remove();
	          path
	          .transition()
	          .attr("d", arc)
	          .ease("back-in-out")
	          .duration(750)
	          .attr("transform", "scale(1), rotate(0)")
	          .style("opacity", 1)
	          .call(function () {
	        	  
	          });
	         

	            updatePieLabels();
	 
	            

	            d3.selectAll(".zoom-out")
	                .transition()
	                .ease("back-in-out")
	                .duration(750)
	                .attr("transform", "scale(1.25)")
	                .style("opacity", 0)
	                .remove()
	                .call(function () {
	                    //Finished zooming out
	                });

	            setTimeout(function () {
	                var secondaryHtml = d3.select(".secondary").html();
	                d3.select(".tertiary").html(secondaryHtml);
	            }, 850);
	            
	            

	            zoomZeArc(selectedItem, false);

	        };
	    }

	    function zoomZeArc(selectedItem, reverse, callback) {

	        var zoomScale = 1.25;

	        var origOuterRadius = radius * 1.1;
	        var origInnerRadius = radius - 20;

	        var finalInnerRadius = radius * zoomScale;

	        var curInnerRadius = origInnerRadius;
	        var curOuterRadius = origOuterRadius;

	        var arcZoom = d3.svg.arc()
	            .startAngle(0)
	            .endAngle(2 * Math.PI)
	            .outerRadius(function () {
	                return (curOuterRadius);
	            })
	            .innerRadius(function () {
	                return (curInnerRadius);
	            });

	        if (!reverse) {
	            selectedItem.attr("class", "zoom-out");
	        }

	        selectedItem
	            .transition()
	            .ease("back-in-out")
	            .duration(750)
	            .attrTween("d", function () {
	                var iInner = reverse ? d3.interpolate(finalInnerRadius, origInnerRadius) : d3.interpolate(origInnerRadius, finalInnerRadius);
	                return function (tick) {
	                    curInnerRadius = iInner(tick);
	                    curOuterRadius = origOuterRadius + ((curInnerRadius - origInnerRadius) / 1.75);
	                    return arcZoom(selectedItem);
	                };
	            })
	            .each("end", function () {
	                if (callback) {
	                    callback();
	                }
	            });

	    }


	    function updatePieLabels() {
	    	console.log("After");
	        chartCenterLabel.text(getCurrentItem().label);

	        //Updates pie chart labels. Needs to be after path data gets set!

	        chartLabelsGroup.html("");
	        console.log(pie(path.data()));
	        var sliceLabels = chartLabelsGroup.selectAll("text").data(pie(path.data()));
	        var currentData = getCurrentItemData();
	        sliceLabels
	            .enter()
	            .append("text")
	            .style("opacity", 0)
	            .transition().duration(750)
	            .style("opacity", 1)
	            .attr("class","outer-label")
	            .attr("transform", function(d) {
	                return "translate(" + arcSmall.centroid(d) + ")";
	            })
	            .text(function(d ,i) { 
	                return currentData[i].label; 	
	            });

	    }

	    function getCurrentItem() {
	        if (selectedPath.length == 0) {
	            return topLevelItem;
	        }
	        var currentItem = data;
	        for (var i = 0; i < selectedPath.length; i++) {
	            if (i + 1 < selectedPath.length) {
	                currentItem = currentItem[selectedPath[i]].childData;
	            } else {
	                currentItem = currentItem[selectedPath[i]];
	            }
	        }
	        return currentItem;
	    }

	    function getCurrentItemAsBreadCrumbs() {
	        if (selectedPath.length == 0) {
	            return [topLevelItem.label];
	        }
	        var currentItem = data;
	        var returnList = [topLevelItem.label];
	        for (var i = 0; i < selectedPath.length; i++) {
	            returnList.push(currentItem[selectedPath[i]].label);
	            if (i + 1 < selectedPath.length) {
	                currentItem = currentItem[selectedPath[i]].childData;
	            } else {
	                currentItem = currentItem[selectedPath[i]];
	            }
	        }
	        return returnList;
	    }

	    function getCurrentItemData(startIndex) {
	        startIndex = startIndex | 0;
	        var currentItem = data;
	        for (var i = startIndex; i < selectedPath.length; i++) {
	            currentItem = currentItem[selectedPath[i]].childData;
	        }
	        return currentItem;
	    }

	    function getRandomNumberInRange(min, max) {
	        return Math.floor(Math.random() * (max - min + 1)) + min;
	    }

	    visualization.donut.getBreadCrumbs = function() {
	        return getCurrentItemAsBreadCrumbs();
	    };

	    visualization.donut.randomize = function() {

	        //Set some random dataz and animate it

	        var currentDataLevel = getCurrentItemData();
	        for (var i=0; i<currentDataLevel.length; i++) {
	            currentDataLevel[i].value = currentDataLevel[i].value * getRandomNumberInRange(75, 125) / 100.0;
	        }

	        path.data(pie(currentDataLevel));

	        path.transition().duration(750).attrTween("d", function(a) {
	            var i = d3.interpolate(this._current, a);
	            this._current = i(0);
	            return function(t) {
	                return arc(i(t));
	            };
	        });

	    };

	    visualization.donut.show = function () {

	        var svgContainer = d3.select("#graph");
	        svgContainer.html("");

	        data = dataOriginal.slice(0);
	        selectedPath = [];

	        // Primary Chart
	        chart = svgContainer
	            .append("svg")
	            .attr("id", "svg-container")
	            .attr("width", width)
	            .attr("height", height)
	            .append("g")
	            .attr("class", "primary")
	            .attr("transform", transformAttrValue());

	        chartLabelsGroup = d3.select("#svg-container")
	            .append("g")
	            .attr("class", "labelGroup")
	            .attr("transform", transformAttrValue(-20));

	        chartCenterLabelGroup = d3.select("#svg-container")
	            .append("g")
	            .attr("class", "labelCenterGroup")
	            .attr("transform", transformAttrValue());

	        chartCenterLabel = chartCenterLabelGroup
	            .append("text")
	            .attr("dy", ".35em")
	            .attr("class", "chartLabel center")
	            .attr("text-anchor", "middle");

	        arc = d3.svg.arc()
	            .outerRadius(radius)
	            .innerRadius(radius - 20);

	        arcSmall = d3.svg.arc()
	            .outerRadius(radius - 40)
	            .innerRadius(radius - 80);

	        pie = d3.layout.pie()
	            .value(function (d) {
	                return d.value;
	            })
	            .sort(null);

	        path = chart.selectAll("path")
	            .data(pie(data));
	            path.enter().append("path")
	            .style("fill", function (d) {
	                return colors(d.data.colorIndex);
	            })
	            .attr("d", arc)
	            .each(function (d) {
	                this._current = d;
	            }); // store the initial angles

	        updatePieLabels();

	        // Secondary Chart
	        chartSelect = d3.select("svg")
	            .append("g")
	            .attr("class", "secondary")
	            .attr("transform", transformAttrValue());

	        // Tertiary Chart
	        chartSelectTertiary = d3.select("svg")
	            .append("g")
	            .attr("class", "tertiary")
	            .attr("style", "display:none")
	            .attr("transform", transformAttrValue());

	        // Arc Interaction Sizing
	        arcOver = d3.svg.arc()
	            .outerRadius(radius * 1.1)
	            .innerRadius(radius - 20);

	        path.on("mouseover", function () {
	            //return false;
	            d3.select(this).transition()
	                .ease("in")
	                .duration(100)
	                .attr("d", arcOver);
	        }).on("mouseout", function () {
	            //return false;
	            d3.select(this).transition()
	                .ease("bounce")
	                .duration(500)
	                .attr("d", arc);
	        }).on("click", zoomIn);

	    };

	    return visualization;

	}(definitionVisualization));

	definitionVisualization.donut.show();
	}
