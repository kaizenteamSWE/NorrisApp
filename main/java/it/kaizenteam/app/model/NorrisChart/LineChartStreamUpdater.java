/*
* Name: LineChartStreamUpdater.java
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
* v0.04 2015-06-01  Marco Rubin  Verify
* =================================================================
* v0.03 2015-05-29  Pavanello Fabio Matteo Edit
* =================================================================
* v0.02 2015-05-26  Moretto Alessandro   Verify
* =================================================================
* v0.01 2015-05-22  Dal Bianco Davide  Creation
* =================================================================
*
*/

package it.kaizenteam.app.model.NorrisChart;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;

import java.util.ArrayList;

/**
 * This class is responsible for defining the updating stream method for a line chart. In particular it change the DataObject contained in LineChartImpl through the appropriate method.
 */
public class LineChartStreamUpdater implements ChartUpdater {
    /**
     * This attribute is a reference to the unique instance of the class
     */    
    private static LineChartStreamUpdater instance;

    /**
     * This method allows to get the unique existing instance of the class.
     * @return instance of the class
     */
    public static ChartUpdater getInstance(){
        if(instance!=null)
            return instance;
        return new LineChartStreamUpdater();
    }

    /**
     * This method is the constructor of the class.
     */
    private LineChartStreamUpdater(){instance=this;}

    @Override
    /**
     * This method allows to update the chart passed as the first parameter using the data passed as the second parameter.
     * @param chart
     * @param updateData
     */
    public void update(ChartImpl chart, ChartUpdate updateData) {
        LineData data=((LineChartDataImpl)chart.getData()).getData();
        ArrayList<LineChartElementStreamUpdate> elements=((LineChartStreamUpdate)updateData).getData();
        for(int i =0;i<elements.size();i++){
            data.getXVals().add(data.getXVals().size(), elements.get(i).getLabel());
            int dim=data.getDataSets().size();
            for(int j =0;j<dim;j++)
                data.getDataSetByIndex(j).addEntry(new Entry(elements.get(i).getData().get(j).intValue(), data.getXVals().size()-1));
        }
        if(data.getXVals().size()>((LineChartSettingsImpl)chart.getSettings()).getMaxValue()){
            data.removeXValue(0);
            for(int i = 0 ; i < data.getDataSets().size();i++) {
                data.removeEntry(0, i);
                for (int j=0;j<data.getDataSets().get(i).getYVals().size();j++)
                data.getDataSets().get(i).getYVals().get(j).setXIndex(data.getDataSets().get(i).getYVals().get(j).getXIndex()-1);
            }
        }
    }
}
