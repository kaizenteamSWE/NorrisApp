/*
 * Name: BarChartInPlaceUpdate
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
* v0.04 2015-06-01  Pavanello Fabio Matteo  Verify
* =================================================================
* v0.03 2015-05-31  Dal Bianco Davide Edit
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
 * This class represents an in place update package of a bar chart.
 */
public class BarChartInPlaceUpdate implements ChartUpdate {
    private ArrayList<BarChartElementInPlaceUpdate> values;

    /**
     * This method is the constructor to create the update package.
     * @param values BarChart elements
     */
    public BarChartInPlaceUpdate(ArrayList<BarChartElementInPlaceUpdate> values){
        this.values=values;
    }

    /**
     * This method has the task of returning the data of the update package
     * @return data of the update package
     */
    public ArrayList<BarChartElementInPlaceUpdate> getData(){
        return values;
    }
}
