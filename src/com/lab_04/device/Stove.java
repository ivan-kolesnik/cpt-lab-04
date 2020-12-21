package com.lab_04.device;

import com.lab_04.operation.DeviceOperation;
import java.util.Arrays;

public class Stove extends Device {
    private final int _burnersCount;
    private final boolean[] _burnersIsOn;

    public static class Builder extends Device.Builder<Builder> {
        private int _burnersCount;

        public Builder setBurnersCount(int v) {
            _burnersCount = v;
            return this;
        }

        @Override
        public Stove build() {
            return new Stove(this);
        }
    }

    public Stove(Builder builder) {
        super(builder);

        if (builder._burnersCount < 1) {
            throw new IllegalArgumentException("Burners count of stove must be less than 1");
        }

        _burnersCount = builder._burnersCount;
        _burnersIsOn = new boolean[_burnersCount];

        Arrays.fill(_burnersIsOn, false);
    }

    public int getBurnersCount() {
        return _burnersCount;
    }

    public boolean isBurnerTurnOn(int index) {
        return _burnersIsOn[index];
    }

    public void turnOnBurner(int index) {
        _burnersIsOn[index] = true;
    }

    public void turnOffBurner(int index) {
        _burnersIsOn[index] = false;
    }

    @Override
    public void accept(DeviceOperation visitor) {
        visitor.visitStove(this);
    }
}
