/*
* Name: MapPoint.java
* Package: it.kaizenteam.app.model.NorrisChart
* Location: Sources/Applicazione/main/java/it/kaizenteam/app/model/NorrisChart
* Date: 2015-05-18
* Version: v1.00
*
* History:
* =================================================================
* Version	Date	Programmer	Changes
* =================================================================
* v1.00 2015-06-15  Carlon Chiara  Approved
* =================================================================
* v0.04 2015-06-03  Moretto Alessandro  Verify
* =================================================================
* v0.03 2015-05-31  Bucco Riccardo Edit
* =================================================================
* v0.02 2015-05-24  Dal Bianco Davide   Verify
* =================================================================
* v0.01 2015-05-18  Moretto Alessandro  Creation
* =================================================================
*
*/

package it.kaizenteam.app.model.NorrisChart;

/**
 * This class represents the data of a point of a map chart.
 */
public class MapChartElementDeleteUpdate {
    /**
     * This attribute is the latitude of a point on the map chart.
     */
    private int series;

    /**
     * This attribute represents the longitude of a point on the map chart.
     */
    private int index;

    /**
     * This attribute represent the id of a point on the map chart.
     */
    private String id;

    /**
     * This method is the constructor of MapPoint. It has as parameters the values of index and series, and the id of this one.
     * @param series of point
     * @param index of point
     */
    public MapChartElementDeleteUpdate(int series, int index){
        this.series=series;
        this.index=index;
    }

    /**
     * This method is the constructor of MapPoint. It has as parameters the values of a geographical coordinate, and the id of this one.
     * @param id of point
     */
    public MapChartElementDeleteUpdate(String id){
        this.id=id;
    }

    /**
     * This method has the task of returning the id of the point.
     * @return the id of the point
     */
    public String getId(){
        return id;
    }

    /**
     * This method has the task of returning the series of the point.
     * @return the series of the point
     */
    public int getSeries(){
        return series;
    }

    /**
     * This method has the task of returning the index of the point.
     * @return the Index of the point
     */
    public int getIndex(){
        return index;
    }

    /**
     * This method has the task to set the series of the point.
     * @param series
     */
    public void setSeries(int series){
        this.series= series;
    }

    /**
     * This method has the task to set the index of the point.
     * @param index
     */
    public void setIndex(int index){
        this.index= index;
    }
}
