package com.lab_04.operation;

import com.lab_04.device.Lamp;
import com.lab_04.device.Fridge;

public interface DeviceOperation {
    void visitLamp(Lamp lamp);
    void visitFridge(Fridge fridge);
}
