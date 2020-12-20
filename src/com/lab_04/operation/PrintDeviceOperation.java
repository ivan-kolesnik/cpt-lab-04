package com.lab_04.operation;

import com.lab_04.device.Lamp;
import java.util.Arrays;

public class PrintDeviceOperation implements DeviceOperation {
    @Override
    public void visitLamp(Lamp lamp) {
        int[] arr = lamp.getColorTemperatureArr();
        String colorTemperatureArrStr = Arrays.toString(arr);

        String str =
                "\nLamp (id = " + lamp.getId() + ").\n" +
                "Manufacturer:                 " + lamp.getManufacturer()       + "\n" +
                "Model:                        " + lamp.getModel()              + "\n" +
                "Min brightness:               " + lamp.getMinBrightness()      + "\n" +
                "Max brightness:               " + lamp.getMaxBrightness()      + "\n" +
                "Supported color temperatures: " + colorTemperatureArrStr       + "\n" +
                "Is turn on?:                  " + lamp.isTurnOn()              + "\n" +
                "Current brightness:           " + lamp.getBrightness()         + "\n" +
                "Current color temperature:    " + lamp.getColorTemperature();

        System.out.println(str);
    }
}
