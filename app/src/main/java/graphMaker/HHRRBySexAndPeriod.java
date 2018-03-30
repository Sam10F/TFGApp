package graphMaker;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by samuel10 on 3/3/18.
 */

public class HHRRBySexAndPeriod extends AppCompatActivity {

    JSONArray jsonArray;

    public HHRRBySexAndPeriod(JSONArray jsonArray){
        this.jsonArray = jsonArray;
    }


    public void createLineGraph(GraphView lineGraph, String title, boolean extras) throws JSONException {

        int s = 0;
        while(jsonArray==null){
            s++;
        }

        List<Double> womanSpain = new ArrayList<>();
        List<Double> womanEU = new ArrayList<>();
        List<Double> manSpain = new ArrayList<>();
        List<Double> manEU = new ArrayList<>();



        for(int i = 0;i < jsonArray.length();i++){

            if(jsonArray.getJSONObject(i).has("Sex")
                    && jsonArray.getJSONObject(i).getString("Sex").equals("woman")){

                womanSpain.add(jsonArray.getJSONObject(i).getDouble("Spain"));
                womanEU.add(jsonArray.getJSONObject(i).getDouble("EU-28"));
            }
            if(jsonArray.getJSONObject(i).has("Sex")
                    && jsonArray.getJSONObject(i).getString("Sex").equals("man")){
                manSpain.add(jsonArray.getJSONObject(i).getDouble("Spain"));
                manEU.add(jsonArray.getJSONObject(i).getDouble("EU-28"));
            }
        }


        lineGraph.setTitle(title);

        LineGraphSeries<DataPoint> seriesWS = new LineGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(2012, womanSpain.get(4)),
                new DataPoint(2013, womanSpain.get(3)),
                new DataPoint(2014, womanSpain.get(2)),
                new DataPoint(2015, womanSpain.get(1)),
                new DataPoint(2016, womanSpain.get(0))
        });
        seriesWS.setColor(Color.MAGENTA);
        seriesWS.setTitle("Woman in Spain");

        LineGraphSeries<DataPoint> seriesWE = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(2012, womanEU.get(4)),
                new DataPoint(2013, womanEU.get(3)),
                new DataPoint(2014, womanEU.get(2)),
                new DataPoint(2015, womanEU.get(1)),
                new DataPoint(2016, womanEU.get(0))
        });
        seriesWE.setColor(Color.GREEN);
        seriesWE.setTitle("Woman in EU");

        LineGraphSeries<DataPoint> seriesMS = new LineGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(2012, manSpain.get(4)),
                new DataPoint(2013, manSpain.get(3)),
                new DataPoint(2014, manSpain.get(2)),
                new DataPoint(2015, manSpain.get(1)),
                new DataPoint(2016, manSpain.get(0))
        });
        seriesMS.setColor(Color.BLUE);
        seriesMS.setTitle("Man in Spain");

        LineGraphSeries<DataPoint> seriesME = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(2012, manEU.get(4)),
                new DataPoint(2013, manEU.get(3)),
                new DataPoint(2014, manEU.get(2)),
                new DataPoint(2015, manEU.get(1)),
                new DataPoint(2016, manEU.get(0))
        });
        seriesME.setColor(Color.RED);
        seriesME.setTitle("Man in EU");

        lineGraph.addSeries(seriesME);
        lineGraph.addSeries(seriesMS);
        lineGraph.addSeries(seriesWE);
        lineGraph.addSeries(seriesWS);

        if(extras){
            lineGraph.getLegendRenderer().setVisible(true);
            lineGraph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
            lineGraph.getViewport().setScalableY(true);
        }
    }

    public void createBarGraph(GraphView graph, String title, boolean extras) throws JSONException{
        List<Double> womanSpain = new ArrayList<>();
        List<Double> womanEU = new ArrayList<>();
        List<Double> manSpain = new ArrayList<>();
        List<Double> manEU = new ArrayList<>();



        for(int i = 0;i < jsonArray.length();i++){
            if(jsonArray.getJSONObject(i).has("Sex")
                && jsonArray.getJSONObject(i).getString("Sex").equals("woman")){

                womanSpain.add(jsonArray.getJSONObject(i).getDouble("Spain"));
                womanEU.add(jsonArray.getJSONObject(i).getDouble("EU-28"));
            }
            if(jsonArray.getJSONObject(i).has("Sex")
                    && jsonArray.getJSONObject(i).getString("Sex").equals("man")){

                manSpain.add(jsonArray.getJSONObject(i).getDouble("Spain"));
                manEU.add(jsonArray.getJSONObject(i).getDouble("EU-28"));
            }
        }


        graph.setTitle(title);

        BarGraphSeries<DataPoint> seriesWS = new BarGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(2012, womanSpain.get(4)),
                new DataPoint(2013, womanSpain.get(3)),
                new DataPoint(2014, womanSpain.get(2)),
                new DataPoint(2015, womanSpain.get(1)),
                new DataPoint(2016, womanSpain.get(0))
        });
        seriesWS.setColor(Color.MAGENTA);
        seriesWS.setTitle("Woman in Spain");

        BarGraphSeries<DataPoint> seriesWE = new BarGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(2012, womanEU.get(4)),
                new DataPoint(2013, womanEU.get(3)),
                new DataPoint(2014, womanEU.get(2)),
                new DataPoint(2015, womanEU.get(1)),
                new DataPoint(2016, womanEU.get(0))
        });
        seriesWE.setColor(Color.GREEN);
        seriesWE.setTitle("Woman in EU");

        BarGraphSeries<DataPoint> seriesMS = new BarGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(2012, manSpain.get(4)),
                new DataPoint(2013, manSpain.get(3)),
                new DataPoint(2014, manSpain.get(2)),
                new DataPoint(2015, manSpain.get(1)),
                new DataPoint(2016, manSpain.get(0))
        });
        seriesMS.setColor(Color.BLUE);
        seriesMS.setTitle("Man in Spain");

        BarGraphSeries<DataPoint> seriesME = new BarGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(2012, manEU.get(4)),
                new DataPoint(2013, manEU.get(3)),
                new DataPoint(2014, manEU.get(2)),
                new DataPoint(2015, manEU.get(1)),
                new DataPoint(2016, manEU.get(0))
        });
        seriesME.setColor(Color.RED);
        seriesME.setTitle("Man in EU");

        graph.addSeries(seriesME);
        graph.addSeries(seriesMS);
        graph.addSeries(seriesWE);
        graph.addSeries(seriesWS);

        if(extras){
            graph.getLegendRenderer().setVisible(true);
            graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
            graph.getViewport().setScalableY(true);
        }

    }


    public void createPointGraph(GraphView graph, String title, boolean extras) throws JSONException{
        List<Double> womanSpain = new ArrayList<>();
        List<Double> womanEU = new ArrayList<>();
        List<Double> manSpain = new ArrayList<>();
        List<Double> manEU = new ArrayList<>();



        for(int i = 0;i < jsonArray.length();i++){
            if(jsonArray.getJSONObject(i).has("Sex")
                    && jsonArray.getJSONObject(i).getString("Sex").equals("woman")){

                womanSpain.add(jsonArray.getJSONObject(i).getDouble("Spain"));
                womanEU.add(jsonArray.getJSONObject(i).getDouble("EU-28"));
            }
            if(jsonArray.getJSONObject(i).has("Sex")
                    && jsonArray.getJSONObject(i).getString("Sex").equals("man")){

                manSpain.add(jsonArray.getJSONObject(i).getDouble("Spain"));
                manEU.add(jsonArray.getJSONObject(i).getDouble("EU-28"));
            }
        }

        graph.setTitle(title);

        PointsGraphSeries<DataPoint> seriesWS = new PointsGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(2012, womanSpain.get(4)),
                new DataPoint(2013, womanSpain.get(3)),
                new DataPoint(2014, womanSpain.get(2)),
                new DataPoint(2015, womanSpain.get(1)),
                new DataPoint(2016, womanSpain.get(0))
        });
        seriesWS.setColor(Color.MAGENTA);
        seriesWS.setShape(PointsGraphSeries.Shape.POINT);
        seriesWS.setTitle("Woman in Spain");

        PointsGraphSeries<DataPoint> seriesWE = new PointsGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(2012, womanEU.get(4)),
                new DataPoint(2013, womanEU.get(3)),
                new DataPoint(2014, womanEU.get(2)),
                new DataPoint(2015, womanEU.get(1)),
                new DataPoint(2016, womanEU.get(0))
        });
        seriesWE.setColor(Color.GREEN);
        seriesWE.setShape(PointsGraphSeries.Shape.RECTANGLE);
        seriesWE.setTitle("Woman in EU");

        PointsGraphSeries<DataPoint> seriesMS = new PointsGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(2012, manSpain.get(4)),
                new DataPoint(2013, manSpain.get(3)),
                new DataPoint(2014, manSpain.get(2)),
                new DataPoint(2015, manSpain.get(1)),
                new DataPoint(2016, manSpain.get(0))
        });
        seriesMS.setColor(Color.BLUE);
        seriesWS.setShape(PointsGraphSeries.Shape.TRIANGLE);
        seriesMS.setTitle("Man in Spain");

        PointsGraphSeries<DataPoint> seriesME = new PointsGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(2012, manEU.get(4)),
                new DataPoint(2013, manEU.get(3)),
                new DataPoint(2014, manEU.get(2)),
                new DataPoint(2015, manEU.get(1)),
                new DataPoint(2016, manEU.get(0))
        });
        seriesME.setColor(Color.RED);
        seriesME.setTitle("Man in EU");

        graph.addSeries(seriesME);
        graph.addSeries(seriesMS);
        graph.addSeries(seriesWE);
        graph.addSeries(seriesWS);

        if(extras){
            graph.getLegendRenderer().setVisible(true);
            graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
            graph.getViewport().setScalableY(true);
        }

    }
}
