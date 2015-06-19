/*
* Name: TableStreamUpdater.java
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
* v0.04 2015-06-03  Bucco Riccardo  Verify
* =================================================================
* v0.03 2015-05-29  Moretto Alessandro Edit
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
 * This class is responsible for defining the stream update method of a table. It can access it has turned to the TableImpl private data fields because 
 * TableStreamUpdater is an inner class of TableImpl. In particular, it can access to DataObject container in TableImpl and change its values.
 */
public class TableStreamUpdater implements ChartUpdater {
    /**
     * The static attribute is the unique instance of that class.
     */
    private static TableStreamUpdater instance;

    /**
     * This method has the task of returning the unique instance of that class, and creating it if it not exists.
     * @return the unique instance of the class
     */
    public static ChartUpdater getInstance(){

        if(instance!=null)
            return instance;
        return new TableStreamUpdater();
    }

    /**
     * Constructor
     */
    private TableStreamUpdater(){instance=this;}

    /**
     * This method has the task of updating the chart as a parameter (chart) by using the update package (UpdateData).
     * @param chart
     * @param updateData
     */
    @Override
    public void update(ChartImpl chart, ChartUpdate updateData) {
        ArrayList<TableRow> chartdata=((TableDataImpl)chart.getData()).getData();
        ArrayList<TableRow> updaterow=((TableStreamUpdate)updateData).getData();
        //add new row to chart data
        for(int i =0;i<updaterow.size();i++){
            if(((TableSettingsImpl)chart.getSettings()).getNewLinePosition().equals("top"))
                chartdata.add(0,updaterow.get(i));
            else
                chartdata.add(chartdata.size(),updaterow.get(i));
            //if the new data size is over chart max value, remove the older item
            if(chartdata.size()>((TableSettingsImpl)chart.getSettings()).getMaxValue())
                chartdata.remove(0);

        }
    }
}
