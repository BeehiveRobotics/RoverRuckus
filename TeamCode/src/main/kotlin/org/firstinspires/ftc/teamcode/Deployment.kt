package org.firstinspires.ftc.teamcode

import org.BeehiveRobotics.Library.Util.BROpMode
import org.BeehiveRobotics.Library.Systems.RobotSystem
import org.BeehiveRobotics.Library.Servos.Servo as BRServo
import com.qualcomm.robotcore.hardware.Servo
import org.BeehiveRobotics.Library.Sensors.REVColorSensor

class Deployment(private val opMode: BROpMode): RobotSystem(opMode), Runnable {
    val topKicker = BRServo(opMode, "dtks")
    val bottomKicker = BRServo(opMode, "dbks")
    private val rightFlipServo = BRServo(opMode, "drfs")
    private val leftFlipServo = BRServo(opMode, "dlfs")
    private val balanceServo = BRServo(opMode, "dbs")
    val topCS = REVColorSensor(opMode, "dtcs")
    val bottomCS = REVColorSensor(opMode, "dbcs")
    private val REVEALED_POSITION = 0.15
    private val STOWED_POSITION = 1.0
    private val UP_POSITION = 0.8
    private val RIGHT_POSITION = 0.75
    private val MIDDLE_POSITION = 0.5
    private val LEFT_POSITION = 0.25
    private val A_LITTLE_OFF_FROM_THE_MIDDLE_POSITION = 0.51
    private val OUT_OF_THE_WAY_FOR_LANDING_POSITION = 0.7
    private var isUp = false
    private val kickerPosition = A_LITTLE_OFF_FROM_THE_MIDDLE_POSITION
    private val KICK_DELAY_TIME = 500L
    private val COLOR_SENSOR_THRESHOLD = 0.35
    enum class Tasks {
        KICK_RIGHT_RIGHT, KICK_LEFT_LEFT, KICK_LEFT_RIGHT, KICK_RIGHT_LEFT, REVEAL, PURGE, KNOCK, NONE
    }
    enum class KickDirection {
        RIGHT, LEFT, UNKNOWN
    }
    private var task = Tasks.NONE
    internal var flipPosition: Double = STOWED_POSITION
        internal set(value) {
            rightFlipServo.position = value
            leftFlipServo.position = value
            balanceServo.position = value
            field = value
        }
    /*This is only for testing servo positions */
    internal var kickersPosition: Double = MIDDLE_POSITION
        internal set(value) {
            topKicker.position = value
            bottomKicker.position = value
            field = value
        }



    override fun init() {
        rightFlipServo.direction = Servo.Direction.REVERSE
        balanceServo.direction = Servo.Direction.REVERSE
        balanceServo.speed = 0.002
    }
    
    fun reveal() {
        task = Tasks.REVEAL
        val thread = Thread(this)
        thread.start()
    }

    fun stow() {
        isUp = false
        setFlipPosition(STOWED_POSITION)
    }

    fun toggleOut() {
        if(isUp) {
            stow()
        } else {
            reveal()
        }
    }
    fun knock() {
        task = Tasks.KNOCK
        val thread = Thread(this)
        thread.start()

    }
    
    fun up() {
        setFlipPosition(UP_POSITION)
    }

    fun kickRightRight() {
        task = Tasks.KICK_RIGHT_RIGHT
        val thread = Thread(this)
        thread.start()
    }

    fun kickLeftLeft() {
        task = Tasks.KICK_LEFT_LEFT
        val thread = Thread(this)
        thread.start()
    }
    
    fun kickRightLeft() {
        task = Tasks.KICK_RIGHT_LEFT
        val thread = Thread(this)
        thread.start()
    }

    fun kickLeftRight() {
        task = Tasks.KICK_LEFT_RIGHT
        val thread = Thread(this)
        thread.start()
    }
    
    fun purge() {
        task = Tasks.PURGE
        val thread = Thread(this)
        thread.start()
    }

    private fun middle() {
        setKickerPositions(MIDDLE_POSITION, MIDDLE_POSITION)
    }

    private fun setKickerPositions(topPosition: Double, bottomPosition: Double) {
        topKicker.position = topPosition
        bottomKicker.position = bottomPosition
    }

    fun setFlipPosition(position: Double) {
        rightFlipServo.position = position
        leftFlipServo.position = position
        balanceServo.position = position
    }
    //Fix toString()
    override fun toString(): String =
        "Kicker Position: ${if(kickerPosition==REVEALED_POSITION) "Up" else if(kickerPosition==STOWED_POSITION) "Stowed" else "Something else???"} \n" + 
        "Deploying Position: ${if(isUp) "Out" else "In"}"


    override fun run() {
        when(task) {
            Tasks.REVEAL -> {
                setFlipPosition(REVEALED_POSITION)
                balanceServo.position = 0.7
                sleep(550)
                balanceServo.position = 0.0
                isUp = true
                task = Tasks.NONE
            }
            Tasks.KICK_RIGHT_RIGHT -> {
                if(isUp) {
                    setKickerPositions(RIGHT_POSITION, RIGHT_POSITION)
                    sleep(KICK_DELAY_TIME)
                    middle()
                }
                task = Tasks.NONE
            }
            Tasks.KICK_LEFT_LEFT -> {
                if(isUp) {
                    setKickerPositions(LEFT_POSITION, LEFT_POSITION)
                    sleep(KICK_DELAY_TIME)
                    middle()
                }
                task = Tasks.NONE
            }
            Tasks.KICK_LEFT_RIGHT -> {
                if(isUp) {
                    setKickerPositions(LEFT_POSITION, RIGHT_POSITION)
                    sleep(KICK_DELAY_TIME)
                    middle()
                }
                task = Tasks.NONE
            }
            Tasks.KICK_RIGHT_LEFT -> {
                if(isUp) {
                    setKickerPositions(RIGHT_POSITION, LEFT_POSITION)
                    sleep(KICK_DELAY_TIME)
                    middle()
                }
                task = Tasks.NONE
            } 
            Tasks.PURGE -> {
                if(isUp) {
                    setKickerPositions(RIGHT_POSITION, RIGHT_POSITION)
                    sleep(KICK_DELAY_TIME)
                    setKickerPositions(LEFT_POSITION, LEFT_POSITION)
                    sleep(KICK_DELAY_TIME)
                    middle()
                }
            }
            Tasks.KNOCK -> {
                if(isUp) {
                    val topCSRGB = topCS.RGB()
                    val bottomCSRGB = bottomCS.RGB()
                    var retries = 0
                    var topKickDirection = KickDirection.UNKNOWN
                    var topNumber = 0.0f
                    while(retries < 10 && topKickDirection == KickDirection.UNKNOWN) {
                        topNumber = (topCSRGB[0]-topCSRGB[2]).toFloat()/topCSRGB.max()!!.toFloat()
                        topKickDirection = if(topNumber<0.3 && topNumber>-0.3) KickDirection.LEFT else if(topNumber>0.3 && topNumber<0.9) KickDirection.RIGHT else KickDirection.UNKNOWN
                        sleep(1000L/60L)
                        retries++
                    }
                    retries = 0
                    var bottomKickDirection = KickDirection.UNKNOWN
                    var bottomNumber = 0.0f
                    while(retries < 10 && bottomKickDirection == KickDirection.UNKNOWN) {
                        bottomNumber = (bottomCSRGB[0]-bottomCSRGB[2]).toFloat()/bottomCSRGB.max()!!.toFloat()
                        bottomKickDirection = if(bottomNumber<0.3 && bottomNumber>-0.3) KickDirection.LEFT else if(bottomNumber>0.3 && bottomNumber<0.9) KickDirection.RIGHT else KickDirection.UNKNOWN
                        sleep(1000L/60L)
                        retries++
                    }
                    when(topKickDirection) {
                        KickDirection.LEFT -> {
                            when(bottomKickDirection) {
                                KickDirection.LEFT -> kickLeftLeft()
                                KickDirection.RIGHT -> kickLeftRight()
                            }
                        }
                        KickDirection.RIGHT -> {
                            when(bottomKickDirection) {
                                KickDirection.LEFT -> kickRightLeft()
                                KickDirection.RIGHT -> kickRightRight()
                            }
                        }
                    }
                    opMode.dashboard.addData("Top CS RGB", topCSRGB)
                    opMode.dashboard.addData("Top CS calc#", topNumber)
                    opMode.dashboard.addData("Bottom CS RGB", bottomCSRGB)
                    opMode.dashboard.addData("Bottom CS calc#", bottomNumber)
                    opMode.dashboard.update()
                    task = Tasks.NONE
                }
            }
        }
        return;
    }

}