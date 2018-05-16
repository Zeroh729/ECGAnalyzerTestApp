package sometestapp;

import ecganal.Callback.ECGAnalysisCallback;
import ecganal.ECGAnalyzer;
import ecganal.DSPFunction;
import ecganal.DSPFunction.Type;
import ecganal.Model.ECGSummary;
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
            }
            
            @Override
            public void onFail(String message) {    
                System.out.println("Fail! " + message);
            }
        });
    }

}
