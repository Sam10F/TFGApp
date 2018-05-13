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

public class FemaleResearch extends AppCompatActivity {

    JSONArray jsonArray;

    public FemaleResearch(JSONArray jsonArray){
        this.jsonArray = jsonArray;
    }

    public void createLineGraph(GraphView lineGraph, String title, boolean extras) throws JSONException {

        int s = 0;
        while(jsonArray==null){
            s++;
        }

        List<Double> wCompanies         = new ArrayList<>();
        List<Double> wHEducation        = new ArrayList<>();
        List<Double> wPAdministration   = new ArrayList<>();
        List<Double> wPNPInstitution    = new ArrayList<>();



        for(int i = 0;i < jsonArray.length();i++){

            wCompanies.add(jsonArray.getJSONObject(i).getDouble("Companies"));
            wHEducation.add(jsonArray.getJSONObject(i).getDouble("Higher education"));
            wPAdministration.add(jsonArray.getJSONObject(i).getDouble("Public administration"));
            wPNPInstitution.add(jsonArray.getJSONObject(i).getDouble("Private non-profit institutions"));

        }


        lineGraph.setTitle(title);

        LineGraphSeries<DataPoint> seriesWS = new LineGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(2012, wCompanies.get(4)),
                new DataPoint(2013, wCompanies.get(3)),
                new DataPoint(2014, wCompanies.get(2)),
                new DataPoint(2015, wCompanies.get(1)),
                new DataPoint(2016, wCompanies.get(0))
        });
        seriesWS.setColor(Color.MAGENTA);
        seriesWS.setTitle("Woman in companies");

        LineGraphSeries<DataPoint> seriesWE = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(2012, wHEducation.get(4)),
                new DataPoint(2013, wHEducation.get(3)),
                new DataPoint(2014, wHEducation.get(2)),
                new DataPoint(2015, wHEducation.get(1)),
                new DataPoint(2016, wHEducation.get(0))
        });
        seriesWE.setColor(Color.GREEN);
        seriesWE.setTitle("Woman in education");

        LineGraphSeries<DataPoint> seriesMS = new LineGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(2012, wPAdministration.get(4)),
                new DataPoint(2013, wPAdministration.get(3)),
                new DataPoint(2014, wPAdministration.get(2)),
                new DataPoint(2015, wPAdministration.get(1)),
                new DataPoint(2016, wPAdministration.get(0))
        });
        seriesMS.setColor(Color.BLUE);
        seriesMS.setTitle("Woman in public administration");

        LineGraphSeries<DataPoint> seriesME = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(2012, wPNPInstitution.get(4)),
                new DataPoint(2013, wPNPInstitution.get(3)),
                new DataPoint(2014, wPNPInstitution.get(2)),
                new DataPoint(2015, wPNPInstitution.get(1)),
                new DataPoint(2016, wPNPInstitution.get(0))
        });
        seriesME.setColor(Color.RED);
        seriesME.setTitle("Woman in private non-profit institutions");

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

    public void createBarGraph(GraphView lineGraph, String title, boolean extras) throws JSONException {

        int s = 0;
        while(jsonArray==null){
            s++;
        }

        List<Double> wCompanies         = new ArrayList<>();
        List<Double> wHEducation        = new ArrayList<>();
        List<Double> wPAdministration   = new ArrayList<>();
        List<Double> wPNPInstitution    = new ArrayList<>();



        for(int i = 0;i < jsonArray.length();i++){

            wCompanies.add(jsonArray.getJSONObject(i).getDouble("Companies"));
            wHEducation.add(jsonArray.getJSONObject(i).getDouble("Higher education"));
            wPAdministration.add(jsonArray.getJSONObject(i).getDouble("Public administration"));
            wPNPInstitution.add(jsonArray.getJSONObject(i).getDouble("Private non-profit institutions"));

        }


        lineGraph.setTitle(title);

        BarGraphSeries<DataPoint> seriesWS = new BarGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(2012, wCompanies.get(4)),
                new DataPoint(2013, wCompanies.get(3)),
                new DataPoint(2014, wCompanies.get(2)),
                new DataPoint(2015, wCompanies.get(1)),
                new DataPoint(2016, wCompanies.get(0))
        });
        seriesWS.setColor(Color.MAGENTA);
        seriesWS.setTitle("Woman in companies");

        BarGraphSeries<DataPoint> seriesWE = new BarGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(2012, wHEducation.get(4)),
                new DataPoint(2013, wHEducation.get(3)),
                new DataPoint(2014, wHEducation.get(2)),
                new DataPoint(2015, wHEducation.get(1)),
                new DataPoint(2016, wHEducation.get(0))
        });
        seriesWE.setColor(Color.GREEN);
        seriesWE.setTitle("Woman in education");

        BarGraphSeries<DataPoint> seriesMS = new BarGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(2012, wPAdministration.get(4)),
                new DataPoint(2013, wPAdministration.get(3)),
                new DataPoint(2014, wPAdministration.get(2)),
                new DataPoint(2015, wPAdministration.get(1)),
                new DataPoint(2016, wPAdministration.get(0))
        });
        seriesMS.setColor(Color.BLUE);
        seriesMS.setTitle("Woman in public administration");

        BarGraphSeries<DataPoint> seriesME = new BarGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(2012, wPNPInstitution.get(4)),
                new DataPoint(2013, wPNPInstitution.get(3)),
                new DataPoint(2014, wPNPInstitution.get(2)),
                new DataPoint(2015, wPNPInstitution.get(1)),
                new DataPoint(2016, wPNPInstitution.get(0))
        });
        seriesME.setColor(Color.RED);
        seriesME.setTitle("Woman in private non-profit institutions");

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

    public void createPointGraph(GraphView lineGraph, String title, boolean extras) throws JSONException {

        int s = 0;
        while(jsonArray==null){
            s++;
        }

        List<Double> wCompanies         = new ArrayList<>();
        List<Double> wHEducation        = new ArrayList<>();
        List<Double> wPAdministration   = new ArrayList<>();
        List<Double> wPNPInstitution    = new ArrayList<>();



        for(int i = 0;i < jsonArray.length();i++){

            wCompanies.add(jsonArray.getJSONObject(i).getDouble("Companies"));
            wHEducation.add(jsonArray.getJSONObject(i).getDouble("Higher education"));
            wPAdministration.add(jsonArray.getJSONObject(i).getDouble("Public administration"));
            wPNPInstitution.add(jsonArray.getJSONObject(i).getDouble("Private non-profit institutions"));

        }


        lineGraph.setTitle(title);

        PointsGraphSeries<DataPoint> seriesWS = new PointsGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(2012, wCompanies.get(4)),
                new DataPoint(2013, wCompanies.get(3)),
                new DataPoint(2014, wCompanies.get(2)),
                new DataPoint(2015, wCompanies.get(1)),
                new DataPoint(2016, wCompanies.get(0))
        });
        seriesWS.setColor(Color.MAGENTA);
        seriesWS.setTitle("Woman in companies");

        PointsGraphSeries<DataPoint> seriesWE = new PointsGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(2012, wHEducation.get(4)),
                new DataPoint(2013, wHEducation.get(3)),
                new DataPoint(2014, wHEducation.get(2)),
                new DataPoint(2015, wHEducation.get(1)),
                new DataPoint(2016, wHEducation.get(0))
        });
        seriesWE.setColor(Color.GREEN);
        seriesWE.setTitle("Woman in education");

        PointsGraphSeries<DataPoint> seriesMS = new PointsGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(2012, wPAdministration.get(4)),
                new DataPoint(2013, wPAdministration.get(3)),
                new DataPoint(2014, wPAdministration.get(2)),
                new DataPoint(2015, wPAdministration.get(1)),
                new DataPoint(2016, wPAdministration.get(0))
        });
        seriesMS.setColor(Color.BLUE);
        seriesMS.setTitle("Woman in public administration");

        PointsGraphSeries<DataPoint> seriesME = new PointsGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(2012, wPNPInstitution.get(4)),
                new DataPoint(2013, wPNPInstitution.get(3)),
                new DataPoint(2014, wPNPInstitution.get(2)),
                new DataPoint(2015, wPNPInstitution.get(1)),
                new DataPoint(2016, wPNPInstitution.get(0))
        });
        seriesME.setColor(Color.RED);
        seriesME.setTitle("Woman in private non-profit institutions");

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

}
