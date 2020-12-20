package com.lab_04.ui;

public enum FridgeMenuOp {
    UM_FRIDGE_TURN_ON         (0),
    UM_FRIDGE_TURN_OFF        (1),
    UM_FRIDGE_INC_TEMPERATURE (2),
    UM_FRIDGE_DEC_TEMPERATURE (3),

    UM_FRIDGE_NOT_FOUND       (4);

    private final int _cmd;

    FridgeMenuOp(int cmd) {
        _cmd = cmd;
    }

    public int getCmd() {
        return _cmd;
    }

    public static FridgeMenuOp fromCmd(int cmd) {
        for (FridgeMenuOp action : values()) {
            if (action._cmd == cmd) {
                return action;
            }
        }

        return UM_FRIDGE_NOT_FOUND;
    }
}
