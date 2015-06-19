/*
* Name: MapChartElementInPlaceUpdate.java
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
* v0.04 2015-06-02  Bigarella Chiara  Verify
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

/**
 * This class represents an element of the update package in place of a map chart.
 */
public class MapChartElementStreamUpdate {
    /**
     * This attribute represents the values of the given date.
     */
    private MapPoint value;

    /**
     * This attribute is the series in the map of the data to be replaced.
     */
    private int series;


    /**
     * This method is a constructor to create the update package.
     * @param value new data of update package
     * @param series series of the chart to add the point
     */
    public MapChartElementStreamUpdate(int series, MapPoint value){
        this.value=value;
        this.series=series;
    }

    /**
     * This method is responsible for returning the new data of the update package.
     * @return new data of the update package
     */
    public MapPoint getData(){
        return value;
    }

    /**
     * This method is responsible for returning the series of the chart to add the point
     * @return series of point to edit
     * @throws Exception
     */
    public int getSeries() throws Exception {
        return series;
    }
}
