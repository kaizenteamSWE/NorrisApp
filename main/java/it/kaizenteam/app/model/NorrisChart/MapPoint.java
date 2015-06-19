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
* v0.04 2015-06-02  Moretto Alessandro  Verify
* =================================================================
* v0.03 2015-05-30  Dal Bianco Davide Edit
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
public class MapPoint {
    /**
     * This attribute is the latitude of a point on the map chart.
     */
    private double latitude;

    /**
     * This attribute represents the longitude of a point on the map chart.
     */
    private double longitude;

    /**
     * This attribute represent the id of a point on the map chart.
     */
    private String id;

    /**
     * This method is the constructor of MapPoint. It has as parameters the values of a geographical coordinate, and the id of this one.
     * @param latitude of point
     * @param longitude of point
     */
    public MapPoint(double latitude, double longitude){
        this.latitude=latitude;
        this.longitude=longitude;
    }

    /**
     * This method assign an id to the point.
     * @param id it's the id to assign to the object
     */
    public void setId(String id){
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
     * This method has the task of returning the latitude of the point.
     * @return the latitude of the point
     */
    public double getLatitude(){
        return latitude;
    }

    /**
     * This method has the task of returning the longitude of the point.
     * @return the longitude of the point
     */
    public double getLongitude(){
        return longitude;
    }

    /**
     * This method has the task to set the latitude of the point.
     * @param latitude
     */
    public void setLatitude(double latitude){
        this.latitude= latitude;
    }

    /**
     * This method has the task to set the longitude of the point.
     * @param longitude
     */
    public void setLongitude(double longitude){
        this.longitude= longitude;
    }
}
