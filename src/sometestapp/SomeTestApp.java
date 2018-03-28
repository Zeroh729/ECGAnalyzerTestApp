package sometestapp;

import ecganal.ECGAnalyzer;
import ecganal.DSPFunction;
import ecganal.DSPFunction.Type;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SomeTestApp {
    private static String data;
    
    public static void main(String[] args) {
        data = DummyData.getData();
        ECGAnalyzer.setData(data);
        DataVisualizationHelper.drawData("Original Data", "milliseconds", "volts", ECGAnalyzer.getData());
        DataVisualizationHelper.drawData("Low Pass Filter", "milliseconds", "volts", 
                DSPFunction.execute(Type.LOWPASSFILTER, ECGAnalyzer.getData()));
        
    }

}
