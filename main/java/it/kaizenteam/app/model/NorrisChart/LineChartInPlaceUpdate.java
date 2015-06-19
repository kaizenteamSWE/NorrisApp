/*
* Name: LineChartInPlaceUpdate.java
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
* v0.04 2015-06-03  Pavanello Fabio Matteo  Verify
* =================================================================
* v0.03 2015-05-28  Bucco Riccardo Edit
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
 * This class represents an in place update package of a line chart.
 */
public class LineChartInPlaceUpdate implements ChartUpdate {
    /**
     * This attribute represents the values of the updated date.
     */    
    private ArrayList<LineChartElementInPlaceUpdate> values;

    /**
     * This method is the constructor to create the update package.
     * @param values udpate package
     */
    public LineChartInPlaceUpdate(ArrayList<LineChartElementInPlaceUpdate> values){
        this.values=values;
    }

    /**
     * This method is responsible for returning the data of the update package.
     * @return the data of the update package
     */
    public ArrayList<LineChartElementInPlaceUpdate> getData(){
        return values;
    }
}
