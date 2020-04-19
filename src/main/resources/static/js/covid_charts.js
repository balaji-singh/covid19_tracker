function chartUpdate(id){
	var nameArr = id.split(',');
	
	var data;
	
	if(nameArr[1].length>0){
		
		data=pontos.find(ponto => ponto.province_state === nameArr[1] );
	}
	else{
		data=pontos.find(ponto => ponto.country_region === nameArr[0] );
	}
	
	var dates = pontos[0].timelineConfirmed;
	var confirmed=data.timelineConfirmed;
	var deaths=data.timelineDeath;
	var recovered=data.timelineRecovered;

	var chart = new CanvasJS.Chart("chartContainer", {
		animationEnabled: true,
		theme: "light2",
		title:{
			text: "India Covid19 Live Data"
		},
		axisX:{
			valueFormatString: "DD/MM/YY",
			crosshair: {
				enabled: true,
				snapToDataPoint: true
			}
		},
		axisY: {
			
			title: "Number of Cases",
			crosshair: {
				enabled: true
			}
		},
		toolTip:{
			shared:true
		},  
		legend:{
			cursor:"pointer",
			verticalAlign: "bottom",
			horizontalAlign: "left",
			dockInsidePlotArea: true,
			//itemclick: toogleDataSeries
		},
		data: [{
			type: "spline",
			yValueFormatString: "0",
			axisYIndex: 0,
			showInLegend: true,
			name: "Daily Cases",
			markerType: "square",
			xValueFormatString: "DD/MMM/YY",
			color: "#F08080",
			dataPoints: []
		},
		{
			type: "spline",
			yValueFormatString: "0",
			axisYIndex: 0,
			showInLegend: true,
			name: "Fatal",
			markerType: "square",
			xValueFormatString: "DD/MMM/YY",
			color: "#CC0000",
			dataPoints: []
		},
		{
			type: "spline",
			yValueFormatString: "0",
			axisYIndex: 0,
			showInLegend: true,
			name: "Recovered",
			markerType: "square",
			xValueFormatString: "DD/MMM/YY",
			color: "#007E33",
			dataPoints: []
		}]
	});
	dates.forEach(myFunction);
	function myFunction(value, index, array) {
		
		xValue = new Date(value);
		chart.options.title.text = id+" Covid19 Live Data";
			try {
				chart.options.data[0].dataPoints.push({ x: xValue, y: parseInt(confirmed[index])});
			}
			catch(err) {
				chart.options.data[0].dataPoints.push({ x: xValue, y: parseInt(0)});
			}
			try {
			chart.options.data[1].dataPoints.push({ x: xValue, y: parseInt(deaths[index])});
			
			}
			catch(err) {
			 
				chart.options.data[1].dataPoints.push({ x: xValue, y: parseInt(0)});
			}
			try {
				chart.options.data[2].dataPoints.push({ x: xValue, y: parseInt(recovered[index])});
			}
			catch(err) {
			 
				chart.options.data[2].dataPoints.push({ x: xValue, y: parseInt(0)});
			}
		
		
	}
	chart.render();

	
	 function toogleDataSeries(e){
		if (typeof(e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
			e.dataSeries.visible = false;
		} else{
			e.dataSeries.visible = true;
		}
		chart.render();
	} 

}