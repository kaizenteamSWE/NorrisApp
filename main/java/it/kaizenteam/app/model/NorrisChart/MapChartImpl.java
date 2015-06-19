/*
* Name: MapChartImpl.java
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
* v0.04 2015-06-01  Dal Bianco Davide  Verify
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

/**
 * This class represent a map chart. It contains within it data (MapChartDataObject) and settings (MapChartSettingsObject) related to the graph.
 * It contains also the classes MapChartInPlaceUpdater and MapChartMovieUpdater, which respectively implement in place and movie update for the plot.
 * MapChartImpl instance of the class is created by the factory class MapChartFactory.
 */
class MapChartImpl extends ChartImpl {

    static {
        //registro il tipo di grafico (DI)
        registerFactory("mapchart", MapChartFactory.getInstance());
        registerUpdater("mapchart:inplace", MapChartInPlaceUpdater.getInstance());
        registerUpdater("mapchart:movie", MapChartMovieUpdater.getInstance());
    }

    /**
     * This method is the constructor. It is private because only the factory class can create the instance.
     * @param chartId id of the chart
     */
    private MapChartImpl(String chartId) {
        super("mapchart", chartId);
    }

    /**
     * This class is responsible for creating a MapChartImpl. In particular, it configure the data and the settings of the chart.
     */
    protected static class MapChartFactory implements ChartFactory{
        /**
         * This static attribute represent the unique instance of the class
         */        
	private static MapChartFactory instance;

        /**
         * This method has the task of returning the unique instance of the class, and creating it if it not exists.
         * @return the instance of the class
         */
        public static ChartFactory getInstance(){
            if(instance!=null)
                return instance;
            return new MapChartFactory();
        }

        /**
         * This method is the constructor of the class. It is private because anyone creation of the instance is not wanted except through getInstance() method.
         */
        private MapChartFactory(){
            instance=this;
        }

        /**
         * This method has the task of creating the specialization of ChartImpl. It can access its manufacturer because this factory class is internal to the related class MapChartImpl.
         * @param chartId 
         * @return the specialization of ChartImpl
         */
        @Override
        public ChartImpl createChart(String chartId) {
            return new MapChartImpl(chartId);
        }
    }
}
