/*
* Name: BarChartActivity.java
* Package: it.kaizenteam.app.view
* Location: Sources/Applicazione/main/java/it/kaizenteam/app/view
* Date: 2015-05-19
* Version: v1.00
*
* History:
* =================================================================
* Version	Date	Programmer	Changes
* =================================================================
* v1.00 2015-06-15  Carlon Chiara  Approved
* =================================================================
* v0.04 2015-06-01  Moretto Alessandro  Verify
* =================================================================
* v0.03 2015-05-28  Rubin Marco Edit
* =================================================================
* v0.02 2015-05-22  Dal Bianco Davide   Verify
* =================================================================
* v0.01 2015-05-19  Moretto Alessandro  Creation
* =================================================================
*
*/

package it.kaizenteam.app.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;

import java.util.ArrayList;

import it.kaizenteam.app.R;
import it.kaizenteam.app.model.NorrisChart.BarChartDataImpl;
import it.kaizenteam.app.model.NorrisChart.ChartData;
import it.kaizenteam.app.presenter.BarChartPresenter;
import it.kaizenteam.app.presenter.PresenterImpl;


/**
 * BarChartActivity specializes ChartActivity and constitutes an Activity for bar charts. It provides static constants that represent the possible values to be passed to methods to change the view.
 */
public class BarChartActivity extends ChartActivity implements BarChartView{
    private BarChart chart;

    /**
     * This method is performed by android at the creation of the Activity. It will be tasked to initializing its presenter.
     * @param savedInstanceState instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);
        chart=(BarChart)findViewById(R.id.chartv);
        presenter= PresenterImpl.create(PresenterImpl.ChartType.BARCHART_TYPE,this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((BarChartPresenter)presenter).setChart(getIntent().getStringExtra("id"));
        chart.setDescription("");
        chart.invalidate();
    }

    @Override
    protected void onPause() {
        super.onPause();
        ((BarChartPresenter)presenter).onPause();
        chart.setDescription("");
        chart.invalidate();
    }

    /**
     * This method will display correctly the chart passed as a parameter.
     * @param data chart
     */
    @Override
    public void renderChart(ChartData data) {
        chart.clear();
        BarData datanew =((BarChartDataImpl) data).getData();
        float max=-1212121;
        float min=+1212121;
        for(int i =0;i<datanew.getDataSets().size();i++){
            for(int j =0;j<datanew.getDataSets().get(i).getYVals().size();j++){
                if(datanew.getDataSets().get(i).getYVals().get(j).getVal()>max)
                    max=datanew.getDataSets().get(i).getYVals().get(j).getVal();
                else
                    if(datanew.getDataSets().get(i).getYVals().get(j).getVal()<min)
                        min=datanew.getDataSets().get(i).getYVals().get(j).getVal();
            }
        }
        chart.setData(datanew);
        chart.getAxisLeft().setAxisMaxValue(max + 1);
        chart.getAxisRight().setAxisMaxValue(max + 1);
        if(min<0) {
            chart.getAxisLeft().setStartAtZero(false);
            chart.getAxisRight().setStartAtZero(false);
        }
        chart.getAxisLeft().setAxisMinValue(min - 1);
        chart.getAxisRight().setAxisMinValue(min - 1);
        chart.invalidate();
    }

    /**
     * This method provides the ability to display in the view the name of axes of the chart.
     * @param x name of x-axis
     * @param y name of y-axis
     */
    @Override
    public void setAxisName(String x, String y) {
        if (chart instanceof HorizontalBarChart) {
            ((TextView) findViewById(R.id.xlabelbar)).setText(y);
            ((TextView) findViewById(R.id.ylabelbar)).setText(x.replace("", "\n"));
        }
        else{
            ((TextView) findViewById(R.id.xlabelbar)).setText(x);
            ((TextView) findViewById(R.id.ylabelbar)).setText(y.replace("", "\n"));
        }
    }

    /**
     * This method provides the ability to view or not the grid in the chart. If the parameter is true appears and hidden otherwise.
     * @param show boolean show / hidden the chart grid
     */
    @Override
    public void showGrid(boolean show) {
        chart.getXAxis().setDrawGridLines(show);
        chart.getAxisRight().setDrawGridLines(show);
        chart.getAxisLeft().setDrawGridLines(show);
        chart.invalidate();
    }

    /**
     * This method provides the ability to set the position of the legend. The positions available are eligible as static variables of the class.
     * @param position position of the legend
     */
    @Override
    public void setLegendPosition(int position) {
        chart.getLegend().setEnabled(true);
        if(position==0){
            chart.getLegend().setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
            return;
        }
        if(position==1){
            chart.getLegend().setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
            return;
        }
        if(position==2){
            chart.getLegend().setPosition(Legend.LegendPosition.BELOW_CHART_RIGHT);
            return;
        }
        if(position==3){
            chart.getLegend().setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
            return;
        }
        if(position==4){
            chart.getLegend().setPosition(Legend.LegendPosition.RIGHT_OF_CHART_INSIDE);
            return;
        }
        if(position==5){
            chart.getLegend().setEnabled(false);
            return;
        }
        chart.invalidate();
    }

    /**
     * This method provides the ability to change the orientation of the chart based on the parameter.
     * The parameters allowed are available in static constants in the class.
     * @param orientation orientation of the chart
     */
    @Override
    public void setOrientation(String orientation) {
        BarData data=chart.getBarData();
        if(orientation.equals("horizontal")){
            chart=(HorizontalBarChart)findViewById(R.id.charth);
            (findViewById(R.id.chartv)).setVisibility(View.INVISIBLE);
            (findViewById(R.id.charth)).setVisibility(View.VISIBLE);
        }
        else{
            chart=(BarChart)findViewById(R.id.chartv);
            (findViewById(R.id.charth)).setVisibility(View.INVISIBLE);
            (findViewById(R.id.chartv)).setVisibility(View.VISIBLE);
        }
        chart.setDescription("");
        renderChart(new BarChartDataImpl(data));
        chart.invalidate();
    }

    /**
     * Sets the stace between two bar
     * @param barValueSpacing space between two bar
     */
    public void setBarValueSpacing(int barValueSpacing) {
        ArrayList<BarDataSet> set =chart.getBarData().getDataSets();
        for(int i=0;i<set.size();i++)
            set.get(i).setBarSpacePercent(barValueSpacing);
        chart.invalidate();
    }

    /**
     * Sets the stace between two sets
     * @param barDataSetSpacing space between two sets
     */
    public void setBarDataSetSpacing(int barDataSetSpacing) {
        Log.e("ERRRRRR", ""+barDataSetSpacing);
        BarData data =chart.getBarData();
        data.setGroupSpace(barDataSetSpacing);
        chart.invalidate();
    }
}
