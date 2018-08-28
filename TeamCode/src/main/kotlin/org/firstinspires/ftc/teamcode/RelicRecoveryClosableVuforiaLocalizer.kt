package org.firstinspires.ftc.teamcode

import org.firstinspires.ftc.robotcore.internal.vuforia.VuforiaLocalizerImpl
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.Parameters

class RelicRecoveryClosableVuforiaLocalizer(parameters: Parameters): VuforiaLocalizerImpl(parameters) {
    var closed = false
    public override fun close() {
        if (!closed) super.close()
        closed = true
    }
}