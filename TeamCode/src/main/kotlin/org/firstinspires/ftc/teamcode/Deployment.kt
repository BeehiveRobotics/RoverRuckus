package org.firstinspires.ftc.teamcode

import org.BeehiveRobotics.Library.Util.BROpMode
import org.BeehiveRobotics.Library.Systems.RobotSystem
import org.BeehiveRobotics.Library.Servos.Servo as BRServo
import com.qualcomm.robotcore.hardware.Servo
import org.BeehiveRobotics.Library.Sensors.REVColorSensor

class Deployment(private val opMode: BROpMode): RobotSystem(opMode), Runnable {
    val kickerServo = BRServo(opMode, "dks")
    private val rightFlipServo = BRServo(opMode, "drfs")
    private val leftFlipServo = BRServo(opMode, "dlfs")
    private val balanceServo = BRServo(opMode, "dbs")
    val cs = REVColorSensor(opMode, "dcs")
    private val REVEALED_POSITION = 0.0
    private val STOWED_POSITION = 1.0
    private val UP_POSITION = 0.8
    private val RIGHT_POSITION = 1.0
    private val MIDDLE_POSITION = 0.5
    private val LEFT_POSITION = 0.0
    private val A_LITTLE_OFF_FROM_THE_MIDDLE_POSITION = 0.55
    private val OUT_OF_THE_WAY_FOR_LANDING_POSITION = 0.7
    private var isOut = false
    private var kickerPosition = A_LITTLE_OFF_FROM_THE_MIDDLE_POSITION
    enum class Tasks {
        KICK_RIGHT, KICK_LEFT, REVEAL, NONE
    }
    private var task = Tasks.NONE

    override fun init() {
        rightFlipServo.direction = Servo.Direction.REVERSE
        balanceServo.speed = 0.7
        balanceServo.direction = Servo.Direction.REVERSE
    }
    
    fun reveal() {
        isOut = true
        task = Tasks.REVEAL
        val thread = Thread(this)
        thread.start()
    }

    fun stow() {
        isOut = false
        setFlipPosition(STOWED_POSITION)
    }

    fun toggleOut() {
        if(isOut) {
            stow()
        } else {
            reveal()
        }
    }
    fun knock() {
        
    }

    fun up() {
        setFlipPosition(UP_POSITION)
    }

    fun right() {
        task = Tasks.KICK_RIGHT
        val thread = Thread(this)
        thread.start()
    }

    fun left() {
        task = Tasks.KICK_LEFT
        val thread = Thread(this)
        thread.start()
    }

    private fun middle() {
        setKickerPosition(A_LITTLE_OFF_FROM_THE_MIDDLE_POSITION)
    }

    private fun setKickerPosition(position: Double) {
        kickerServo.position = position
    }

    private fun setFlipPosition(position: Double) {
        rightFlipServo.position = position
        leftFlipServo.position = position
        balanceServo.position = position
    }

    override fun toString(): String =
        "Kicker Position: ${if(kickerPosition==REVEALED_POSITION) "Up" else if(kickerPosition==STOWED_POSITION) "Stowed" else "Something else???"} \n" + 
        "Deploying Position: ${if(isOut) "Out" else "In"}"


    override fun run() {
        when(task) {
            Tasks.REVEAL -> {
                setFlipPosition(REVEALED_POSITION)
                sleep(250)
                setKickerPosition(A_LITTLE_OFF_FROM_THE_MIDDLE_POSITION)
                sleep(750)
                setKickerPosition(MIDDLE_POSITION)
                task = Tasks.NONE
            }
            Tasks.KICK_RIGHT -> {
                setKickerPosition(RIGHT_POSITION)
                sleep(600)
                middle()
                task = Tasks.NONE
            }
            Tasks.KICK_LEFT -> {
                setKickerPosition(LEFT_POSITION)
                sleep(600)
                middle()
                task = Tasks.NONE
            }
        }
    }

}