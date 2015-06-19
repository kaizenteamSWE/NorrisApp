/*
* Name: BarChartElementInPlaceUpdate.java
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

/**
* This class represents an element of the in place update package of a bar chart.
*/
public class BarChartElementInPlaceUpdate {
    private int value;
    private int xpos;
    private int ypos;

    /**
    * This method is the constructor to create the update package.
    * @param xpos value of abscissa
    * @param ypos value of ordinate
    * @param value value of the chart
    */
    public BarChartElementInPlaceUpdate(int xpos, int ypos, int value){
        this.xpos=xpos;
        this.ypos=ypos;
        this.value=value;
    }

    /**
    * This method is responsible for returning the new data of the update package.
    * @return getData data of the update package
    */
    public int getData(){
        return value;
    }

    /**
    * This method has the task of returning the abscissa of the data to be modified.
    * @return double value of abscissa to be modified
    */
    public int getX(){
        return xpos;
    }

    /**
    * This method has the task of returning the ordinate of data to be modified.
    * @return double value of ordinate to be modified
    */
    public int getY(){
        return ypos;
    }
}
