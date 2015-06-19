/*
* Name: MapChartInPlaceUpdater.java
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
* v0.04 2015-06-02  Bucco Riccardo  Verify
* =================================================================
* v0.03 2015-05-30  Dal Bianco Davide Edit
* =================================================================
* v0.02 2015-05-26  Rubin Marco Verify
* =================================================================
* v0.01 2015-05-25  Moretto Alessandro  Creation
* =================================================================
*
*/

package it.kaizenteam.app.model.NorrisChart;

import java.util.ArrayList;

/**
 * That class is responsible for defining in place updating method for a map chart. It can access to the private data fields of MapChartImpl because it is an inner class. In particular it can access to DataObject contained in MapChartImpl and change its values.
 */
public class MapChartInPlaceUpdater implements ChartUpdater {
    /**
      * The static attribute represent the unique instance of the class. 
      */
    private static MapChartInPlaceUpdater instance;

    /**
     * This method has the task of returning the unique instance of class, and creating it if it not exists.
     * @return instance of the class
     */
    public static ChartUpdater getInstance(){
        if(instance!=null)
            return instance;
        return new MapChartInPlaceUpdater();
    }

    /**
     * Constructor of the class
     */
    private MapChartInPlaceUpdater(){instance=this;}


    /**
     * This method has the task of updating the chart passed as a parameter (chart) by using the update package (UpdateData).
     * @param chart the chart to be update
     * @param updateData the update package
     */
    @Override
    public void update(ChartImpl chart, ChartUpdate updateData) {
        ArrayList<MapSet> chartdata=((MapChartDataImpl)chart.getData()).getData();
        ArrayList<MapChartElementInPlaceUpdate> updatepoints=((MapChartInPlaceUpdate)updateData).getData();
        for(int i =0;i<updatepoints.size();i++){
            chartdata.get(updatepoints.get(i).getSeries()).getData().set(updatepoints.get(i).getIndex(),updatepoints.get(i).getData());
        }
    }
}
