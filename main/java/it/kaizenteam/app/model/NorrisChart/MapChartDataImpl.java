/*
* Name: MapChartDataImpl.java
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

import java.util.ArrayList;

/**
 * This class represents the data of a map chart.
 */
public class MapChartDataImpl implements ChartData {
    /**
     * This attribute represents the values of the chart.
     */
    private ArrayList<MapSet> values;

    /**
     * This method is the constructor of MapChartDataImpl. It has values of chart as parameter.
     * @param values of chart
     */
    public MapChartDataImpl(ArrayList<MapSet> values){
        this.values=values;
    }

    /**
     * This method has the task of returning the data of the chart.
     * @return data of the chart
     */
    public ArrayList<MapSet> getData(){
        return values;
    }

    /**
     * This method has the task to set the data of the chart.
     * @param values data of the chart to set
     */
    public void setData(ArrayList<MapSet> values){
        this.values=values;
    }
}
