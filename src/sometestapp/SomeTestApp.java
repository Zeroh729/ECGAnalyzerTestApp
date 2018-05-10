package sometestapp;

import ecganal.ECGAnalyzer;
import ecganal.DSPFunction;
import ecganal.DSPFunction.Type;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SomeTestApp {
    private static String filedata;
    private static double[][] data;
    
    public static void main(String[] args) {
        filedata = DummyData.getData();
        ECGAnalyzer.setData(filedata);
        data = ECGAnalyzer.getData();
        
        Dataviz.drawData("Original Data", "milliseconds", "volts", data);
//        data = DSPFunction.execute(Type.LOWPASSFILTER, data);
//        Dataviz.drawData("Low Pass Filter", "milliseconds", "volts", data);
//        data = DSPFunction.execute(Type.HIGHPASSFILTER, data);
//        Dataviz.drawData("High Pass Filter", "milliseconds", "volts", data);
////        data = DSPFunction.execute(Type.DERIVATIVE, data);
////        Dataviz.drawData("Derivative", "milliseconds", "volts", data);
//        data = DSPFunction.execute(Type.SQUARING, data);
//        Dataviz.drawData("Squaring", "milliseconds", "volts", data);
        data = DSPFunction.execute(Type.ALL, data);
        Dataviz.drawData("All", "milliseconds", "volts", data);
        DSPFunction.detectRPeaks();
        int bpm = DSPFunction.calculateBPM();
        System.out.println("BPM is " + bpm);
    }

}
