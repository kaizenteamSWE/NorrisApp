/*
* Name: TableInPlaceUpdater.java
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
* v0.04 2015-06-02  Bigarella Chiara  Verify
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

import java.util.ArrayList;

/**
 * This class is responsible for defining the in place update method for a table. It can access to the private data fields of his related class TableImpl. In particular, it can access in DataObject container in TableImpl and change its values.
 */
public class TableInPlaceUpdater implements ChartUpdater {
    /**
     * This attribute is a reference to the unique instance of the class.
     */
    private static TableInPlaceUpdater instance;

    /**
     * This method allows to get the unique existing instance of the class.
     * @return unique existing instance
     */
    public static ChartUpdater getInstance(){

        if(instance!=null)
            return instance;
        return new TableInPlaceUpdater();
    }

    /**
     * This method is the constructor of the class.
     */
    private TableInPlaceUpdater(){
        instance=this;
    }

    /**
     * This method allows you to update the chart passed as the first parameter using the data passed as the second parameter.
     * @param chart
     * @param updateData
     */
    @Override
    public void update(ChartImpl chart, ChartUpdate updateData) {
        ArrayList<TableRow> chartdata=((TableDataImpl)chart.getData()).getData();
        ArrayList<TableCellInPlaceUpdate> updatecell=((TableInPlaceUpdate)updateData).getData();
        //for each update data in updatedata, substiture the object in that place
        for(int i =0;i<updatecell.size();i++){
            chartdata.get(updatecell.get(i).getX()).getData().set(updatecell.get(i).getY(),updatecell.get(i).getData());
        }
    }
}
