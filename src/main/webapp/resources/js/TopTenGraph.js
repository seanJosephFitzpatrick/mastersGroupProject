function drawGraph(topTen){
var definitionVisualization = {};

(function (visualization) {

    visualization.donut = {};

    var topLevelItem = {label: "% Failures Per Node"};

/*    var subSubData = [
        {colorIndex: 0, value: 3075, label: "Label 1"},
        {colorIndex: 1, value: 6150, label: "Label 2"},
        {colorIndex: 2, value: 6832, label: "Label 3"},
        {colorIndex: 3, value: 7516, label: "Label 4"},
        {colorIndex: 4, value: 6291, label: "Label 5"}
    ];*/

 /*   var subData = [
        {colorIndex: 0, value: 1000, label: "Region 1"},
        {colorIndex: 1, value: 1000, label: "Region 2"},
        {colorIndex: 2, value: 1000, label: "Region 3"},
        {colorIndex: 3, value: 1000, label: "Region 4"},
        {colorIndex: 4, value: 1200, childData: subSubData, label: "Region 5"}
    ];*/
    var subSubData =[];
	var subData=[];
    var data = [];
    data.push({ 
		colorIndex: 0, 
        value: numberOfFailures,
        label: "Other Failures"});
    var found=false;
    var countColour=1;
    for(var i=0;i<topTen.length;i++){
    	found=false;
    	for(var j=0;j<data.length;j++){
    	if(data[j].label===topTen[i].cellId){
    		data[j].value+=topTen[i].count;
    		found=true;
    	}
    	}
    	if(!found){
    		data.push({ 
        		colorIndex: countColour, 
                value: topTen[i].count,
                childData: subData,
                label: topTen[i].cellId});
    		countColour++;
    	}
    }
    var countTotal=0;
    for(var i=1;i<data.length;i++){
    	countTotal+=parseInt(data[i].value);
//    	data[i].value=parseFloat(data[i].value)/numberOfFailures*100;
//    	data[i].value=data[i].value.toFixed(2);
//    	console.log(data[i].value);
    	if(i===data.length-1){
    		console.log(countTotal);
    		data[0].value=(numberOfFailures-countTotal);
    	}
    }
    
    for(var i=0;i<data.length;i++){
    	countColour=0;
    	var tempArray=[];
    	
    	tempArray.push({ 
    		colorIndex: countColour, 
            value: 15000,
            label: "Other Failures"});
    	countColour++;
    	
    	
    	for(var j=0;j<topTen.length;j++){
    		if(topTen[j].cellId===data[i].label){

    			tempArray.push({ 
        		colorIndex: countColour, 
                value: topTen[j].count,
                childData: subSubData,
                label: topTen[j].country,
    			cellId: topTen[j].cellId});
    			countColour++;

    		}
        	}
    	console.log(data[i].label);
    	if(data[i].label !== "Other Failures"){
    		console.log("test");
    	subData.push(tempArray);
    	data[i].childData=subData[i-1];
    	}

    }
    
    for(var i=0;i<subData.length;i++){
    	countTotal=0;
    	for(var j=1;j<subData[i].length;j++){
    	countTotal+=parseInt(subData[i][j].value);
//    	subData[i][j].value=parseFloat(subData[i][j].value)/numberOfFailures*100;
//    	subData[i][j].value=subData[i][j].value.toFixed(2);
//    	console.log(data[i].value);
    	if(j===subData[i].length-1){
    		console.log(countTotal);
    		subData[i][0].value=numberOfFailures-countTotal;
//    		subData[i][0].value=subData[i][0].value.toFixed(2);
    	}
    	}
    }
    for(var i=0;i<subData.length;i++){
    	var tempArray=[];
    	for(var j=0;j<subData[i].length;j++){
    		var tempArray2 = [];
    		
    		countColour=0;  	      	
        	tempArray2.push({ 
        		colorIndex: countColour, 
                value: 5000,
                label: "Other Failures"});
        	countColour++;
        	 
    		for(var x=0;x<topTen.length;x++){
        		if(topTen[x].country===subData[i][j].label&&topTen[x].cellId===subData[i][j].cellId){
        			if(tempArray2.length===0){
        			tempArray2.push({ 
            		colorIndex: countColour, 
                    value: topTen[x].count,
                    label: topTen[x].operator});
        			countColour++;
        			}else{
        				var flag=false;
        				for(var z=0;z<tempArray2.length;z++){
        					if(topTen[x].operator===tempArray2[z].label){
        						
        						flag=true;
        						break;
        					}
        				}
        				if(!flag){
        					tempArray2.push({ 
        	            		colorIndex: countColour, 
        	                    value: topTen[x].count,
        	                    label: topTen[x].operator});
        	        			countColour++;
        				}
        			}
        		
            	}
        	 }
    		if(subData[i][j].label !== "Other Failures"){
        		console.log("test");
        		tempArray.push(tempArray2);
        	}
    }
    	subSubData.push(tempArray);
    	
    }
    for(var i=0;i<subSubData.length;i++){
    	countTotal=0;
    	for(var j=0;j<subSubData[i].length;j++){
    		for(var z=1;z<subSubData[i][j].length;z++){
    	countTotal+=parseInt(subSubData[i][j][z].value);
//    	subSubData[i][j][z].value=parseFloat(subSubData[i][j][z].value)/numberOfFailures*100;
//    	subSubData[i][j][z].value=subSubData[i][j][z].value.toFixed(2);
    	if(z===subSubData[i][j].length-1){
    		console.log(countTotal);
    		subSubData[i][j][0].value=numberOfFailures-countTotal;
//    		subSubData[i][j][0].value=subSubData[i][j][0].value.toFixed(2);
    	}
    		}
    	}
    }
    console.log(subSubData);
    for(var i=0;i<subData.length;i++){
    	for(var j=0;j<subData[i].length;j++){
    		if(subData[i][j].label !== "Other Failures"){
    			subData[i][j].childData=subSubData[i][j-1];
    			console.log(subData[i][j].label+", "+subData[i][j].value+" : ");
    			console.log(subData[i][j].childData);}
    	}
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

    var margin = {top: 150, right: 150, bottom: 0, left: 500};
    var width = '100%';
    var height = 600;

    var radius = 300;
    var tooltip = d3.select("body")
	.append("div")
	.style("position", "absolute")
	.style("z-index", "10")
	.style("visibility", "hidden")
	.text("a simple tooltip");

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
        console.log(sel[0][0]);
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
                return radius - 30
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
            console.log(currentItem);
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
        }).on("mouseover", function (d) {
            //return false;
            d3.select(this).transition()
                .ease("in")
                .duration(100)
                .attr("d", arcOver);
            tooltip.text(d.value);
            return tooltip.style("visibility", "visible");
        }).on("mousemove", function(){return tooltip.style("top",(d3.event.pageY-10)+"px").style("left",(d3.event.pageX+10)+"px");})
        .on("mouseout", function () {
            //return false;
            d3.select(this).transition()
                .ease("bounce")
                .duration(500)
                .attr("d", arc);
            return tooltip.style("visibility", "hidden");
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
                            return radius - 30;
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
                        .innerRadius(radius - 30);

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
        var origInnerRadius = radius - 30;

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
        if(startIndex==1){
        	for (var i = startIndex; i < selectedPath.length; i++) {
        		console.log(selectedPath);
            	currentItem = currentItem[selectedPath[i-1]].childData;
        	}
        	
        }else{
        	for (var i = startIndex; i < selectedPath.length; i++) {
        		console.log(selectedPath);
            	currentItem = currentItem[selectedPath[i]].childData;
        	}
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
    	var outerWidth  = 1600,
        outerHeight = 900;
        var svgContainer = d3.select("#panel_graph");
        svgContainer.html("");
        data = dataOriginal.slice(0);
        selectedPath = [];

        // Primary Chart
        chart = svgContainer
            .append("svg")
            .attr("id", "svg-container")
            .attr("width", width)
            .attr("height", height)
            .attr("viewBox", "0 0 " + outerWidth + " " + outerHeight)
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
            .innerRadius(radius - 30);

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
            .innerRadius(radius - 30);
        
        

        path.on("mouseover", function (d) {
            //return false;
            d3.select(this).transition()
                .ease("in")
                .duration(100)
                .attr("d", arcOver);
            tooltip.text(d.value);
            return tooltip.style("visibility", "visible");
        }).on("mousemove", function(){return tooltip.style("top",(d3.event.pageY-10)+"px").style("left",(d3.event.pageX+10)+"px");})
        .on("mouseout", function () {
            //return false;
            d3.select(this).transition()
                .ease("bounce")
                .duration(500)
                .attr("d", arc);
            return tooltip.style("visibility", "hidden");
        }).on("click",zoomIn
        );
        

    };

    return visualization;

}(definitionVisualization));

definitionVisualization.donut.show();
}