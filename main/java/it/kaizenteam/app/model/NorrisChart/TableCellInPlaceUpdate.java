/*
* Name: TableCellInPlaceUpdate.java
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
* v0.03 2015-05-30  Pavanello Fabio Matteo Edit
* =================================================================
* v0.02 2015-05-24  Dal Bianco Davide   Verify
* =================================================================
* v0.01 2015-05-18  Moretto Alessandro  Creation
* =================================================================
*
*/

package it.kaizenteam.app.model.NorrisChart;

/**
 * This class represents an element of the in place update package of a table.
 */
public class TableCellInPlaceUpdate {
    /**
     * This attribute represents the abscissa of the data to replace.
     */
    private int xpos;

    /**
     * This attribute represents the ordinate of the data to replace.
     */
    private int ypos;

    /**
     * This attribute stores the new value to be included in the chart.
     */
    private TableCell value;

    /**
     * Constructor
     * @param xpos
     * @param ypos
     * @param value
     */
    public TableCellInPlaceUpdate(int xpos, int ypos, TableCell value){
        this.xpos=xpos;
        this.ypos=ypos;
        this.value=value;
    }

    /**
     * This method has the task of returning the abscissa of the data to be modified.
     * @return the abscissa of the data to be modified
     */
    public int getX(){
        return xpos;
    }

    /**
     * This method has the task of returning the ordinate of data to be modified.
     * @return the ordinate of data to be modified
     */
    public int getY(){
        return ypos;
    }

    /**
     * This method is responsible for returning the new data of the update package.
     * @return the new data of the update package
     */
    public TableCell getData(){
        return value;
    }
}
