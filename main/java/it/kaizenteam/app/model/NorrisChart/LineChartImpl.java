/*
* Name: LineChartImpl.java
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
* v0.04 2015-06-02  Rubin Marco  Verify
* =================================================================
* v0.03 2015-05-30  Bigarella Chiara Edit
* =================================================================
* v0.02 2015-05-26  Moretto Alessandro   Verify
* =================================================================
* v0.01 2015-05-22  Dal Bianco Davide  Creation
* =================================================================
*
*/

package it.kaizenteam.app.model.NorrisChart;

/**
 * This class represents a line chart. It contains within it the data (LineChartDataObject) and settings (LineChartSettingsObject) relative to the chart. It contains also classes LineChartInPlaceUpdater and LineChartStreamUpdater, which respectively implement the update type in place and stream for the graph. LineChartImpl instance of the class is created by the class factory LineChartFactory.
 */
class LineChartImpl extends ChartImpl {

    static {
        //registro il tipo di grafico (DI)
        registerFactory("linechart", LineChartFactory.getInstance());
        registerUpdater("linechart:inplace", LineChartInPlaceUpdater.getInstance());
        registerUpdater("linechart:stream", LineChartStreamUpdater.getInstance());
    }

    /**
     * This method is the constructor. It is private because it can not be created an instance except from a request of his inner class factory
     * @param chartId id of the chart
     */
    private LineChartImpl(String chartId) {
        super("linechart", chartId);
    }


    /**
     * factory class for a LineChartImp
     * This class is responsible for creating a LineChartImpl. In particular, it deals with the data and configure the settings of the chart.
     */
    protected static class LineChartFactory implements ChartFactory{
        private static LineChartFactory instance;

        /**
         * This method is responsible for returning the unique instance of this class and creating it if it not exists.
         * @return the unique instance of the class
         */
        public static ChartFactory getInstance(){
            if(instance!=null)
                return instance;
            return new LineChartFactory();
        }

        /**
         * This method is the constructor of the class. It is private because only 
getInstance() method request can create an instance.
         */
        private LineChartFactory(){
            instance=this;
        }

        /**
         * This method has the task of creating the relative specialization of ChartImpl. It can access its constructor because this factory class is internal to the relevant class LineChartImpl.
         * @param chartId id of the chart
         * @return ChartImpl
         */
        @Override
        public ChartImpl createChart(String chartId) {
            return new LineChartImpl(chartId);
        }
    }
}
