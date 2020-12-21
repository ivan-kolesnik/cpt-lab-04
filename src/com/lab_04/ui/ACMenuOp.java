package com.lab_04.ui;

public enum ACMenuOp {
    UM_AC_TURN_ON         (0),
    UM_AC_TURN_OFF        (1),
    UM_AC_INC_TEMPERATURE (2),
    UM_AC_DEC_TEMPERATURE (3),
    UM_AC_INC_AIR_FLOW    (4),
    UM_AC_DEC_AIR_FLOW    (5),
    UM_AC_SCHED_TURN_ON   (6),
    UM_AC_SCHED_TURN_OFF  (7),

    UM_AC_NOT_FOUND       (8);

    private final int _cmd;

    ACMenuOp(int cmd) {
        _cmd = cmd;
    }

    public int getCmd() {
        return _cmd;
    }

    public static ACMenuOp fromCmd(int cmd) {
        for (ACMenuOp action : values()) {
            if (action._cmd == cmd) {
                return action;
            }
        }

        return UM_AC_NOT_FOUND;
    }
}
