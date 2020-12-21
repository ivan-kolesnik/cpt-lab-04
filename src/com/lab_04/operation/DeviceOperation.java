package com.lab_04.operation;

import com.lab_04.device.Lamp;
import com.lab_04.device.Fridge;
import com.lab_04.device.Stove;

public interface DeviceOperation {
    void visitLamp(Lamp lamp);
    void visitFridge(Fridge fridge);
    void visitStove(Stove stove);
}
