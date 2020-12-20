package com.lab_04.ui;

public enum LampMenuOp {
    UM_LAMP_TURN_ON         (0),
    UM_LAMP_TURN_OFF        (1),
    UM_LAMP_NEXT_TEMP_COLOR (2),
    UM_LAMP_PREV_TEMP_COLOR (3),
    UM_LAMP_INC_BRIGHTNESS  (4),
    UM_LAMP_DEC_BRIGHTNESS  (5),
    UM_LAMP_SCHED_TURN_ON   (6),
    UM_LAMP_SCHED_TURN_OFF  (7),

    UM_LAMP_NOT_FOUND       (8);

    private final int _cmd;

    LampMenuOp(int cmd) {
        _cmd = cmd;
    }

    public int getCmd() {
        return _cmd;
    }

    public static LampMenuOp fromCmd(int cmd) {
        for (LampMenuOp action : values()) {
            if (action._cmd == cmd) {
                return action;
            }
        }

        return UM_LAMP_NOT_FOUND;
    }
}
