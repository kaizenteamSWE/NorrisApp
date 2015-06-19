/*
* Name: MapChartDeleteUpdate.java
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
* v0.03 2015-05-30  Moretto Alessandro Edit
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
 * This class represents a removal package of a map chart.
 */
public class MapChartDeleteUpdate {
    /**
     * This attribute represents the values of the given date.
     */
    private ArrayList<MapChartElementDeleteUpdate> values;

    /**
     * This method is the constructor to create the update package.
     * @param values of the update package
     */
    public MapChartDeleteUpdate(ArrayList<MapChartElementDeleteUpdate> values){
        this.values=values;
    }

    /**
     * This method is responsible for returning the new data of the update package.
     * @return data of the update package
     */
    public ArrayList<MapChartElementDeleteUpdate> getData(){
        return values;
    }
}
