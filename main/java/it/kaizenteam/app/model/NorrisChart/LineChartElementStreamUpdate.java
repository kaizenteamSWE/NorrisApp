/*
* Name: LineChartElementStreamUpdate.java
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

import java.util.ArrayList;

/**
 * This class represents an element of stream update package of a line chart.
 */
public class LineChartElementStreamUpdate {
    /**
     * This attribute is the value of the updated data.
    */
    private ArrayList<Integer> values;

    /**
     * This attribute is the value of the new label to be included in the chart.
    */
    private String label;

    /**
     * This method is the constructor to create update package.
     * @param label value of chart label
     * @param values updated value
     */
    public LineChartElementStreamUpdate(String label, ArrayList<Integer> values){
        this.label=label;
        this.values=values;
    }

    /**
     * This method is responsible for returning the new data of the update package.
     * @return the new data of the update package
     */
    public ArrayList<Integer> getData(){
        return values;
    }

    /**
     * This method has the task to return the value of new label.
     * @return the value of new label
     */
    public String getLabel(){
        return label;
    }
}
