package com.lab_04.operation;

import com.lab_04.device.Lamp;
import com.lab_04.device.Fridge;
import com.lab_04.device.Stove;

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

    @Override
    public void visitFridge(Fridge fridge) {
        String str =
                "\nFridge (id = " + fridge.getId() + ").\n" +
                "Manufacturer:        " + fridge.getManufacturer()      + "\n" +
                "Model:               " + fridge.getModel()             + "\n" +
                "Min temperature:     " + fridge.getMinTemperature()    + "\n" +
                "Max temperature:     " + fridge.getMaxTemperature()    + "\n" +
                "Is turn on?:         " + fridge.isTurnOn()             + "\n" +
                "Current temperature: " + fridge.getTemperature();

        System.out.println(str);
    }

    @Override
    public void visitStove(Stove stove) {
        String str =
                "\nStove (id = " + stove.getId() + ").\n" +
                "Manufacturer:        " + stove.getManufacturer()      + "\n" +
                "Model:               " + stove.getModel()             + "\n" +
                "Is turn on?:         " + stove.isTurnOn()             + "\n";

        int burnersCount = stove.getBurnersCount();
        for (int i = 0; i < burnersCount; i += 1) {
            str += "Is burner " + i + " on?:     " + stove.isBurnerTurnOn(i);
            if (i != burnersCount - 1) {
                str += "\n";
            }
        }

        System.out.println(str);
    }
}
