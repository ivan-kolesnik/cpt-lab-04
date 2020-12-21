package com.lab_04.ui;

public enum SelectDeviceTypeOp {
    SDT_LAMP      (0),
    SDT_FRIDGE    (1),
    SDT_STOVE     (2),
    SDT_TV        (3),
    SDT_AC        (4),

    SDT_NOT_FOUND (5);

    private final int _cmd;

    SelectDeviceTypeOp(int cmd) {
        _cmd = cmd;
    }

    public int getCmd() {
        return _cmd;
    }

    public static SelectDeviceTypeOp fromCmd(int cmd) {
        for (SelectDeviceTypeOp action : values()) {
            if (action._cmd == cmd) {
                return action;
            }
        }

        return SDT_NOT_FOUND;
    }
}
