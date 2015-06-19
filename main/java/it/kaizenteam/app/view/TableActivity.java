/*
* Name: TableActivity.java
* Package: it.kaizenteam.app.view
* Location: Sources/Applicazione/main/java/it/kaizenteam/app/view
* Date: 2015-05-23
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
* v0.02 2015-05-26  Moretto Alessandro   Verify
* =================================================================
* v0.01 2015-05-23  Dal Bianco Davide  Creation
* =================================================================
*
*/

package it.kaizenteam.app.view;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

import it.kaizenteam.app.R;
import it.kaizenteam.app.model.NorrisChart.ChartData;
import it.kaizenteam.app.model.NorrisChart.TableCell;
import it.kaizenteam.app.model.NorrisChart.TableDataImpl;
import it.kaizenteam.app.presenter.PresenterImpl;
import it.kaizenteam.app.presenter.TablePresenter;


/**
 * TableActivity specializes ChartActivity and constitutes an Activity for table charts. It provides static constants that represent the possible values to be passed to methods to change the view.
 */
public class TableActivity extends ChartActivity implements TableView{

    TableRow.LayoutParams tableRowParams = new TableRow.LayoutParams();

    /**
     * This method is performed by android at the creation of the Activity. It will be tasked to initializing its presenter.
     * @param savedInstanceState instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        presenter= PresenterImpl.create(PresenterImpl.ChartType.TABLE_TYPE, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((TablePresenter)presenter).setChart(getIntent().getStringExtra("id"));
        tableRowParams.weight = 1;
    }

    @Override
    protected void onPause() {
        super.onPause();
        ((TablePresenter)presenter).onPause();
    }

    /**
     * This method will display correctly the chart passed as a parameter.
     * @param data chart
     */
    @Override
    public void renderChart(ChartData data) {
        TableLayout tableLayout = (TableLayout) findViewById(R.id.table);
        tableLayout.removeAllViews();
        ArrayList<String> labels = ((TableDataImpl) data).getLabels();

        TableLayout.LayoutParams tableLayoutParams = new TableLayout.LayoutParams();

        TableRow headerRow = new TableRow(this);
        for (int j = 0; j < labels.size(); j++) {
            // 4) create textView
            TextView textView = new TextView(this);
            //  textView.setText(String.valueOf(j));
            textView.setBackgroundColor(Color.LTGRAY);
            textView.setGravity(Gravity.CENTER);
            textView.setText(labels.get(j));

            // 5) add textView to tableRow
            headerRow.addView(textView, tableRowParams);
        }
        tableLayout.addView(headerRow, tableLayoutParams);

        ArrayList<it.kaizenteam.app.model.NorrisChart.TableRow> datas = ((TableDataImpl) data).getData();
        for (int i = 0; i < datas.size(); i++) {//righe
            ArrayList<TableCell> row = datas.get(i).getData();

            // 3) create tableRow
            TableRow tableRow = new TableRow(this);

            for (int j = 0; j < row.size(); j++) {//colonne
                // 4) create textView
                TextView textView = new TextView(this);

                textView.setBackgroundColor(Color.parseColor(row.get(j).getBackgroundColor()));

                textView.setTextColor(Color.parseColor(row.get(j).getFontColor()));

                textView.setGravity(Gravity.CENTER);
                textView.setText(row.get(j).getData());

                // 5) add textView to tableRow
                tableRow.addView(textView, tableRowParams);
            }

            // 6) add tableRow to tableLayout
            tableLayout.addView(tableRow, tableLayoutParams);
        }
    }

    /**
     * This method provides the ability to view or not border lines of the cells of the table.
     * @param border view / hide border lines of the cells of the table
     */
    @Override
    public void showCellBorderLine(boolean border) {
        if(!border)
            tableRowParams.setMargins(0, 0, 0, 0);
        else
            tableRowParams.setMargins(1, 1, 1, 1);
    }
}
