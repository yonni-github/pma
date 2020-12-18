// For a pie chart

var chartDataStr = decodeHtml(chartData);
var chartJson = JSON.parse(chartDataStr);

var numericData = [];
var labelData = [];

for (var i = 0; i < chartJson.length; i++) {
	numericData[i] = chartJson[i].value;
	labelData[i] = chartJson[i].label;
}

new Chart(document.getElementById('myPieChart'), {
	type: 'pie',
	// The data for our dataset
	data: {
		labels: labelData,
		datasets: [{
			label: 'My First dataset',
			backgroundColor: ['#3fd611', '#e8d71c', '#eb4034', '#3143e8'],
			borderColor: '',
			data: numericData
		}]
	},

	// Configuration options go here
	options: {
		title: {
			display: true,
			text: 'Project Status'
		}
	}
});


//decoder
function decodeHtml(html) {
	var txt = document.createElement("textarea");
	txt.innerHTML = html;
	return txt.value;
}
