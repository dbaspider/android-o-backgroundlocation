package com.amap.androidobackgroundlocation;

import android.util.Log;

import com.amap.api.maps2d.model.LatLng;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvParser {

    private static final String TAG = "CsvParser";
    private static final String COMMA_DELIMITER = ",";

    // 2022-09-06 16:33:31,118.73979307845654,31.992202852344132,30.0
    public static List<LatLng> parseFile(String csvPath) {
        Log.i(TAG, "parseFile csvPath = " + csvPath);

        List<LatLng> result = new ArrayList<>(128);
        //List<List<String>> records = new ArrayList<>(64);
        try {
            try (BufferedReader br = new BufferedReader(new FileReader(csvPath))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(COMMA_DELIMITER);
                    //records.add(Arrays.asList(values));
                    // 注意参数顺序：纬度,经度
                    LatLng latLng = new LatLng(Double.parseDouble(values[2]), Double.parseDouble(values[1]));
                    result.add(latLng);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Log.i(TAG, "parseFile result = " + result.size());
        return result;
    }
}
