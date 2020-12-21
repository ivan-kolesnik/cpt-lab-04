package com.lab_04.ui;

public enum StoveMenuOp {
    UM_STOVE_TURN_ON         (0),
    UM_STOVE_TURN_OFF        (1),
    UM_STOVE_TURN_ON_BURNER  (2),
    UM_STOVE_TURN_OFF_BURNER (3),

    UM_STOVE_NOT_FOUND       (4);

    private final int _cmd;

    StoveMenuOp(int cmd) {
        _cmd = cmd;
    }

    public int getCmd() {
        return _cmd;
    }

    public static StoveMenuOp fromCmd(int cmd) {
        for (StoveMenuOp action : values()) {
            if (action._cmd == cmd) {
                return action;
            }
        }

        return UM_STOVE_NOT_FOUND;
    }
}
