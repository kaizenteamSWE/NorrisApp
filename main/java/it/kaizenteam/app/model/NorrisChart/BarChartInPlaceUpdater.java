/*
 * Name: BarChartInPlaceUpdater
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
 * v0.04 2015-06-01  Bigarella Chiara  Verify
 * =================================================================
 * v0.03 2015-05-29  Pavanello Fabio Matteo Edit
 *  =================================================================
 * v0.02 2015-05-26  Moretto Alessandro   Verify
 * =================================================================
 * v0.01 2015-05-22  Dal Bianco Davide  Creation
 * =================================================================
 *
 */


package it.kaizenteam.app.model.NorrisChart;

import com.github.mikephil.charting.data.BarData;

import java.util.ArrayList;

/**
 * This class is responsible for defining the in place update method for a bar chart. It can access to the BarChartImpl private data fields because it is an inner class of BarChartImpl. In particular, it can access to DataObject contained in  BarChartImpl and change its values.
 */
public class BarChartInPlaceUpdater implements ChartUpdater {
    /*
     * The static attribute is the unique instance of that class.
     */
    private static BarChartInPlaceUpdater instance;

    /**
     * This method is responsible for returning the unique instance of this class and creating it if it not exists.
     * @return the unique instance of the class
     */
    public static ChartUpdater getInstance(){
        if(instance!=null)
            return instance;
        return new BarChartInPlaceUpdater();
    }

    /**
     * Constructor
     */
    private BarChartInPlaceUpdater(){
        instance=this;
    }

    /**
     * This method has the task of updating the chart as a parameter (chart) by using the update package (UpdateData).
     * @param chart
     * @param updateData update package
     */
    @Override
    public void update(ChartImpl chart, ChartUpdate updateData) {
        BarData data=((BarChartDataImpl)chart.getData()).getData();
        ArrayList<BarChartElementInPlaceUpdate> elements=((BarChartInPlaceUpdate)updateData).getData();
        for(int i =0;i<elements.size();i++){
            data.getDataSetByIndex(elements.get(i).getX()).getEntryForXIndex(elements.get(i).getY()).setVal(elements.get(i).getData());
        }
    }
}
