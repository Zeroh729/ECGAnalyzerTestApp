package sometestapp;

import ecganal.Callback.ECGAnalysisCallback;
import ecganal.ECGAnalyzer;
import ecganal.DSPFunction;
import ecganal.DSPFunction.Type;
import ecganal.Model.ECGSummary;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SomeTestApp {
    private static String filedata;
    private static double[][] data;
    
    public static void main(String[] args) {
        filedata = DummyData.getData();
        System.out.println("Processing...");
        ECGAnalyzer.getInstance().analyzeData(filedata, new ECGAnalysisCallback(){
            @Override
            public void onSuccess(ECGSummary summary) {
                System.out.println("BPM is " + summary.getBPM());
                System.out.println("RR Intervals are " + summary.getRRIntervals());
                System.out.println("Deviating RRI count " + summary.getDeviatingRRIcount());
                HashMap<String, int[]> labels = new HashMap();
                labels.put("R peaks", toIntArr(summary.getrIndices()));
                
//                Dataviz.drawData("Original", "ECG", "Time", summary.getOriginalData(), labels);
            }
            
            @Override
            public void onFail(String message) {    
                System.out.println("Fail! " + message);
            }
        });
    }
    
    private static int[] toIntArr(ArrayList<Integer> alist){
        int[] arr = new int[alist.size()];
        for(int i = 0; i < alist.size(); i ++){
            arr[i] = alist.get(i) - 16;
        }
        return arr;
    }

}
