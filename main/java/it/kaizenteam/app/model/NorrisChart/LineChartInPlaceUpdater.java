/*
* Name: LineChartInPlaceUpdater.java
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
* v0.04 2015-06-02  Bucco Riccardo  Verify
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

import com.github.mikephil.charting.data.LineData;

import java.util.ArrayList;

/**
 * This class is responsible for defining the in place update method for a line chart. It can access to the LineChartImpl private data fields because it is an inner class of LineChartImpl. In particular, it can access to DataObject contained in LineChartImpl and change its values.
 */
public class LineChartInPlaceUpdater implements ChartUpdater {
    /**
     * The static attribute is the unique instance of that class.
     */
    private static LineChartInPlaceUpdater instance;

    /**
     * This method is responsible for returning the unique instance of this class and creating it if it not exists
     * @return the unique instance of the class
     */
    public static ChartUpdater getInstance(){
        if(instance!=null)
            return instance;
        return new LineChartInPlaceUpdater();
    }

    /**
     * Constructor
     */
    private LineChartInPlaceUpdater(){instance=this;}

    /**
     * This method has the task of updating the chart as a parameter (chart) by using the update package (UpdateData).
     * @param chart chart to update
     * @param updateData update package
     */
    @Override
    public void update(ChartImpl chart, ChartUpdate updateData) {
        LineData data=((LineChartDataImpl)chart.getData()).getData();
        ArrayList<LineChartElementInPlaceUpdate> elements=((LineChartInPlaceUpdate)updateData).getData();
        for(int i =0;i<elements.size();i++){
            data.getDataSetByIndex(elements.get(i).getX()).getEntryForXIndex(elements.get(i).getY()).setVal(elements.get(i).getData());
        }
    }
}
