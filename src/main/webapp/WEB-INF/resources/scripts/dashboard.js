/**
 * Created by klohani on 12/12/13.
 */
window.onload = function () {
    var chart1 = new CanvasJS.Chart("chartContainer1",
        {
            title:{
                text: "oraTest"
            },
            legend:{
                verticalAlign: "bottom",
                horizontalAlign: "center"
            },
            data: [
                {
                    indexLabelFontSize: 20,
                    indexLabelFontFamily: "Monospace",
                    indexLabelFontColor: "darkgrey",
                    indexLabelLineColor: "darkgrey",
                    indexLabelPlacement: "outside",
                    type: "pie",
                    showInLegend: true,
                    dataPoints: [
                        {  y: 75, legendText:"Passed", indexLabel: "Passed" },
                        {  y: 5, legendText:"Failed", exploded: true,indexLabel: "Failed" },
                        {  y: 10, legendText:"Pending", indexLabel: "Pending" },
                        {  y: 10, legendText:"Skipped" , indexLabel: "Skipped"}
                    ]
                }
            ]
        }
    );
    chart1.render();


    var chart2 = new CanvasJS.Chart("chartContainer2",
        {
            title:{
                text: "campaignManagerTest"
            },
            legend:{
                verticalAlign: "bottom",
                horizontalAlign: "center"
            },
            data: [
                {
                    indexLabelFontSize: 20,
                    indexLabelFontFamily: "Monospace",
                    indexLabelFontColor: "darkgrey",
                    indexLabelLineColor: "darkgrey",
                    indexLabelPlacement: "outside",
                    type: "pie",
                    showInLegend: true,
                    dataPoints: [
                        {  y: 65, legendText:"Passed", indexLabel: "Passed" },
                        {  y: 10, legendText:"Failed", exploded: true,indexLabel: "Failed" },
                        {  y: 15, legendText:"Pending", indexLabel: "Pending" },
                        {  y: 10, legendText:"Skipped" , indexLabel: "Skipped"}
                    ]
                }
            ]
        }
    );

    chart2.render();


    var chart3 = new CanvasJS.Chart("chartContainer3",
        {
            title:{
                text: "alpTest"
            },
            legend:{
                verticalAlign: "bottom",
                horizontalAlign: "center"
            },
            data: [
                {
                    indexLabelFontSize: 20,
                    indexLabelFontFamily: "Monospace",
                    indexLabelFontColor: "darkgrey",
                    indexLabelLineColor: "darkgrey",
                    indexLabelPlacement: "outside",
                    type: "pie",
                    showInLegend: true,
                    dataPoints: [
                        {  y: 80, legendText:"Passed", indexLabel: "Passed" },
                        {  y: 5, legendText:"Failed", exploded: true,indexLabel: "Failed" },
                        {  y: 5, legendText:"Pending", indexLabel: "Pending" },
                        {  y: 10, legendText:"Skipped" , indexLabel: "Skipped"}
                    ]
                }
            ]
        }
    );

    chart3.render();


    var chart4 = new CanvasJS.Chart("chartContainer4",
        {
            title:{
                text: "ShopPayPalTest"
            },
            legend:{
                verticalAlign: "bottom",
                horizontalAlign: "center"
            },
            data: [
                {
                    indexLabelFontSize: 20,
                    indexLabelFontFamily: "Monospace",
                    indexLabelFontColor: "darkgrey",
                    indexLabelLineColor: "darkgrey",
                    indexLabelPlacement: "outside",
                    type: "pie",
                    showInLegend: true,
                    dataPoints: [
                        {  y: 30, legendText:"Passed", indexLabel: "Passed" },
                        {  y: 30, legendText:"Failed", exploded: true,indexLabel: "Failed" },
                        {  y: 30, legendText:"Pending", indexLabel: "Pending" },
                        {  y: 10, legendText:"Skipped" , indexLabel: "Skipped"}
                    ]
                }
            ]
        }
    );

    chart4.render();
}