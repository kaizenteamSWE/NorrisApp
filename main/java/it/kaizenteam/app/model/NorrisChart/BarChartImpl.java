/*
* Name: BarChartImpl.java
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
* v0.04 2015-06-02  Pavanello Fabio Matteo  Verify
* =================================================================
* v0.03 2015-05-30  Rubin Marco Edit
* =================================================================
* v0.02 2015-05-26  Moretto Alessandro   Verify
* =================================================================
* v0.01 2015-05-22  Dal Bianco Davide  Creation
* =================================================================
*
*/

package it.kaizenteam.app.model.NorrisChart;

/**
* This class is a bar chart. It contains within it the data (BarChartDataObject) and settings (BarChartSettingsObject) relative to the chart. 
* It also contains BarChartInPlaceUpdater class, which implements the update type in place for the plot. BarChartImpl instance of the class is created by the
* class factory BarChartFactory.
*/
class BarChartImpl extends ChartImpl{
    static {
        //registro il tipo di grafico (DI)
        registerFactory("barchart", BarChartFactory.getInstance());
        registerUpdater("barchart:inplace", BarChartInPlaceUpdater.getInstance());
    }

    /**
    * This method is the constructor of the class.
    * @param chartId id of the chart
    */
    private BarChartImpl(String chartId) {
        super("barchart", chartId);
    }


    /**
    * This class is responsible for creating a BarChartImpl. In particular, it deals with the data and configure the settings of the chart.
    */
    protected static class BarChartFactory implements ChartFactory{
        private static BarChartFactory instance;

        /**
         * This method allows to get the unique existing instance of the class.
         * @return ChartFactory instance of the class
         */
        public static ChartFactory getInstance(){
            if(instance!=null)
                return instance;
            return new BarChartFactory();
        }

        /**
         * This method is the constructor of the class.
         */
        private BarChartFactory(){
            instance=this;
        }

        /**
         * This method allows to build an instance of BarChartImpl.
         * @param chartId Id of the chart
         * @return ChartModel BarChartImpl instance
         */
        @Override
        public ChartModel createChart(String chartId) {
            return new BarChartImpl(chartId);
        }
    }
}
