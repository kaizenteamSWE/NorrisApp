/*
* Name: BarChartDataImpl.java
* Package: it.kaizenteam.app.model.NorrisChart
* Location: Sources/Applicazione/main/java/it/kaizenteam/app/model/NorrisChart
* Date: 2015-05-22
* Version: v1.00
*
* History:
* =================================================================
* Version	Date	Programmer	Changes
* =================================================================
* v1.00 2015-06-15  Carlon Chiara  Approved
* =================================================================
* v0.04 2015-06-02  Moretto Alessandro  Verify
* =================================================================
* v0.03 2015-05-30  Dal Bianco Davide Edit
* =================================================================
* v0.02 2015-05-26  Moretto Alessandro   Verify
* =================================================================
* v0.01 2015-05-22  Dal Bianco Davide  Creation
* =================================================================
*
*/

package it.kaizenteam.app.model.NorrisChart;

import com.github.mikephil.charting.data.BarData;

/**
* This class represents the data in a bar chart
*/
public class BarChartDataImpl implements ChartData {
    private BarData values;

    /**
    * This method is the constructor of BarChartDataImpl. It has as a parameter data values in the chart.
    * @param values values of the chart
    */
    public BarChartDataImpl(BarData values){
        this.values=values;
    }

    /**
    * This method has the task of returning the data of the chart.
    * @return data of the chart
    */
    public BarData getData(){
        return values;
    }

    /**
    * This method has the task to set the data of the chart.
    * @param values data of the chart
    */
    public void setData(BarData values){
        this.values=values;
    }
}
