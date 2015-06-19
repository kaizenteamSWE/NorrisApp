/*
* Name: MapChartStreamUpdate.java
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
* v0.04 2015-06-02  Pavanello Fabio Matteo  Verify
* =================================================================
* v0.03 2015-05-30  Bigarella Chiara Edit
* =================================================================
* v0.02 2015-05-24  Dal Bianco Davide   Verify
* =================================================================
* v0.01 2015-05-18  Moretto Alessandro  Creation
* =================================================================
*
*/

package it.kaizenteam.app.model.NorrisChart;

import java.util.ArrayList;

/**
 * This class represents an stream update package a map chart.
 */
public class MapChartStreamUpdate {
    /**
      * This attribute represents the values of the given date.
      */
    private ArrayList<MapChartElementStreamUpdate> values;
    /**
     * This method is the constructor to create the update package.
     * @param values of the update package
     */
    public MapChartStreamUpdate(ArrayList<MapChartElementStreamUpdate> values){
        this.values=values;
    }

    /**
     * This method is responsible for returning the new data of the update package.
     * @return new data of the update package
     */
    public ArrayList<MapChartElementStreamUpdate> getData(){
        return values;
    }
}
