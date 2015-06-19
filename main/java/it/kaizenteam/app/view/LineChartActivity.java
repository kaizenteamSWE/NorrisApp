/*
* Name: LineChartActivity.java
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
* v0.04 2015-06-02  Rubin Marco  Verify
* =================================================================
* v0.03 2015-05-30  Dal Bianco Davide Edit
* =================================================================
* v0.01	2015-05-19	Dal Bianco Davide   Verify
* =================================================================
* v0.01 2015-05-19  Moretto Alessandro  Creation
* =================================================================
*
*/

package it.kaizenteam.app.view;

import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

import it.kaizenteam.app.R;
import it.kaizenteam.app.model.NorrisChart.ChartData;
import it.kaizenteam.app.model.NorrisChart.LineChartDataImpl;
import it.kaizenteam.app.presenter.LineChartPresenter;
import it.kaizenteam.app.presenter.PresenterImpl;

/**
 * LineChartActivity specializes ChartActivity and constitutes an Activity for line charts. It provides static constants that represent the possible values to pass to methods to change the view.
 */
public class LineChartActivity extends ChartActivity implements LineChartView {
    private LineChart chart;

    /**
     * This method is performed by android at the creation of the Activity. It will be tasked to initializing its presenter.
     * @param savedInstanceState instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);
        chart=(LineChart)findViewById(R.id.chart);
        presenter= PresenterImpl.create(PresenterImpl.ChartType.LINECHART_TYPE, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((LineChartPresenter)presenter).setChart(getIntent().getStringExtra("id"));
        chart.setDescription("");
        chart.invalidate();
    }

    @Override
    protected void onPause() {
        super.onPause();
        ((LineChartPresenter)presenter).onPause();
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
        chart.setData(((LineChartDataImpl) data).getData());
        chart.getXAxis().setValues(((LineChartDataImpl) data).getData().getXVals());

        float max=-324234234;
        float min=434243424;
        for(int i =0;i<chart.getData().getDataSets().size();i++)
            for(int j =0;j<chart.getData().getDataSets().get(i).getYVals().size();j++) {
                if (chart.getData().getDataSets().get(i).getYVals().get(j).getVal() > max)
                    max = chart.getData().getDataSets().get(i).getYVals().get(j).getVal();
                else if (chart.getData().getDataSets().get(i).getYVals().get(j).getVal() < min)
                    min = chart.getData().getDataSets().get(i).getYVals().get(j).getVal();
            }
        chart.getAxisLeft().setAxisMaxValue(max + 1);
        chart.getAxisRight().setAxisMaxValue(max + 1);
        if(min<0) {
            chart.getAxisLeft().setStartAtZero(false);
            chart.getAxisRight().setStartAtZero(false);
        }
        chart.invalidate();
    }

    /**
     * This method provides the ability to display in the view the name of axes of the chart.
     * @param x name of x-axis
     * @param y name of y-axis
     */
    @Override
    public void setAxisName(String x, String y) {
        ((TextView) findViewById(R.id.xlabelline)).setText(x);
        ((TextView) findViewById(R.id.ylabelline)).setText(y.replace("", "\n"));
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
     * This method allows to display the lines of the line chart cubic or not (depending on the boolean parameter).
     * @param cubics disply / not display the lines of the line chart cubic
     */
    @Override
    public void setCubicLines(boolean cubics) {
        ArrayList<LineDataSet> sets = chart.getData().getDataSets();
        for (LineDataSet set : sets)
            set.setDrawCubic(cubics);
        chart.invalidate();
    }

    /**
     * this methods set the radius of the dot of the chart
     * @param dotRadius dot radius value
     */
    @Override
    public void setDotRadius(int dotRadius) {
        ArrayList<LineDataSet> sets = chart.getData().getDataSets();
        for (LineDataSet set : sets) {
            if(dotRadius==0)
                set.setDrawCircles(false);
            else {
                set.setDrawCircles(true);
                set.setCircleSize(dotRadius);
            }

        }
        chart.invalidate();
    }
}
