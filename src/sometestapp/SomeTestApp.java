package sometestapp;

import ecganal.ECGAnalyzer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SomeTestApp {
    private static String data;
    
    public static void main(String[] args) {
        data = DummyData.getData();
        ECGAnalyzer.setData(data);
        DataVisualizationHelper.drawData("Original Data", "milliseconds", "volts", ECGAnalyzer.getData());
    }

}
