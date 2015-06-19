/*
* Name: MapChartElementInPlaceUpdate.java
* Package: it.kaizenteam.app.model.NorrisChart
* Location: Sources/Applicazione/main/java/it/kaizenteam/app/model/NorrisChart
* Date: 2015-05-2
* Version: v1.00
*
* History:
* =================================================================
* Version	Date	Programmer	Changes
* =================================================================
* v1.00 2015-06-15  Carlon Chiara  Approved
* =================================================================
* v0.04 2015-06-01  Rubin Marco  Verify
* =================================================================
* v0.03 2015-05-31  Bucco Riccardo Edit
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
public class MapChartElementInPlaceUpdate {
    /**
     * This attribute represents the values of the given date.
     */
    private MapPoint value;

    /**
     * This attribute is the series in the map of the data to be replaced.
     */
    private int series;

    /**
     * This attribute is the index of the series in the map of the data to be replaced.
     */
    private int index;

    /**
     * This attribute is the id of the data to be replaced.
     */
    private String id;

    /**
     * This method is a constructor to create the update package.
     * @param value new data of update package
     * @param series position of point
     * @param index position of point
     */
    public MapChartElementInPlaceUpdate(MapPoint value, int series, int index){
        this.value=value;
        this.series=series;
        this.index=index;
    }

    /**
     * This method is responsible for returning the new data of the update package.
     * @return new data of the update package
     */
    public MapPoint getData(){
        return value;
    }

    /**
     * This method is responsible for returning the position in mapchart of the point to edit.
     * @return x-coordinate of point to edit
     */
    public int getSeries(){
        return series;
    }

    /**
     * This method is responsible for returning the position in mapchart of the point to edit.
     * @return y-coordinate of point to edit
     */
    public int getIndex() {
        return index;
    }

    /**
     * This method is responsible for returning the id of the point to edit.
     * @return id of the point to be point to edit
     */
    public String getId() {
        return id;
    }

    /**
     * This method is responsible for set the id of the point to edit.
     * @return id of the point to be point to edit
     */
    public void setId(String id) {
        this.id= id;
    }
}
