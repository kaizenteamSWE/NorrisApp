/*
* Name: MapChartMovieUpdater.java
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
import java.util.Collections;
import java.util.Comparator;

/**
 * This class is responsible for defining the movie update method of a map chart. It can access the MapChartImpl private data fields because it is an inner class.  
 * In particular it can access in DataObject contained in MapChartImpl and change its values.
 */
public class MapChartMovieUpdater implements ChartUpdater {
    /**
     * The static attribute is the unique instance of the class.
     */
    private static MapChartMovieUpdater instance;

    /**
     * This method has the task of returning the unique instance of that class, and creating it if not exists.
     * @return the unique instance of the class
     */
    public static ChartUpdater getInstance(){

        if(instance!=null)
            return instance;
        return new MapChartMovieUpdater();
    }

    /**
     * Constructor
     */
    private MapChartMovieUpdater(){instance=this;}

    /**
     * This method has the task of updating the chart passed as a parameter (chart) by using the update package (UpdateData).
     * @param chart
     * @param updateData
     */
    @Override
    public void update(ChartImpl chart, ChartUpdate updateData) {
        ArrayList<MapSet> chartdata=((MapChartDataImpl)chart.getData()).getData();
        MapChartInPlaceUpdate inplaceupdate=((MapChartMovieUpdate)updateData).getInPlaceData();
        if(inplaceupdate!=null)
            chart.update("mapchart:inplace", inplaceupdate);

        MapChartStreamUpdate streamupdate=((MapChartMovieUpdate)updateData).getStreamData();
        if(streamupdate!=null){
            ArrayList<MapChartElementStreamUpdate> streamUpdates=streamupdate.getData();
            for(int i=0;i<streamUpdates.size();i++){
                try {
                    //aggiungo infondo alla serie dell'elemento dell'aggiornamento il dato aggiornato
                    chartdata.get(streamUpdates.get(i).getSeries()).getData().add(chartdata.get(streamUpdates.get(i).getSeries()).getData().size(),streamUpdates.get(i).getData());
                    if(chartdata.get(streamUpdates.get(i).getSeries()).getData().size()>((MapChartSettingsImpl)chart.getSettings()).getMaxValue())
                        chartdata.get(streamUpdates.get(i).getSeries()).getData().remove(0);
                } catch (Exception e) {}
            }
        }

        MapChartDeleteUpdate deleteupdate=((MapChartMovieUpdate)updateData).getDeleteData();
        if(deleteupdate!=null){
            ArrayList<MapChartElementDeleteUpdate> deleteUpdates= deleteupdate.getData();
            //sort by index the element

            Collections.sort(deleteUpdates, new Comparator<MapChartElementDeleteUpdate>() {
                @Override
                public int compare(MapChartElementDeleteUpdate elem1, MapChartElementDeleteUpdate elem2) {
                    if (elem1.getIndex() < elem2.getIndex())
                        return -1;
                    if (elem1.getIndex() == elem2.getIndex())
                        return 0;
                    return 1;
                }
            });
            //delete the elements from higher index to lower
            for(int i = deleteUpdates.size()-1 ; i >=0 ;i--)
                chartdata.get(deleteUpdates.get(i).getSeries()).getData().remove(deleteUpdates.get(i).getIndex());
        }
    }
}
