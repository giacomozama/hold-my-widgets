package me.zama.holdmywidgets.model.compass

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.core.content.getSystemService
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlin.math.atan2
import kotlin.math.sqrt


class CompassLiveProviderImpl(
    private val context: Context
) : CompassLiveProvider {

    private fun calculateHeading(
        accelerometerReading: FloatArray,
        magnetometerReading: FloatArray
    ): Float {
        var (ax, ay, az) = accelerometerReading
        val (ex, ey, ez) = magnetometerReading

        var hx = ey * az - ez * ay
        var hy = ez * ax - ex * az
        var hz = ex * ay - ey * ax

        val invH = 1.0f / sqrt(hx * hx + hy * hy + hz * hz)
        hx *= invH
        hy *= invH
        hz *= invH

        val invA = 1.0f / sqrt(ax * ax + ay * ay + az * az)
        ax *= invA
        ay *= invA
        az *= invA

        return atan2(hy, az * hx - ax * hz)
    }

    private fun convertRadtoDeg(rad: Float) = (rad / Math.PI).toFloat() * 180

    private fun map180to360(angle: Float) = (angle + 360) % 360

    private fun lowPassFilter(input: FloatArray, output: FloatArray) {
        for (i in input.indices) {
            output[i] = output[i] + 0.15f * (input[i] - output[i])
        }
    }

    override fun getLiveDegreesNorth(): Flow<Float> {
        val sensorManager = context.getSystemService<SensorManager>()!!
        return callbackFlow {
            val accelerometerReading = FloatArray(3)
            val magnetometerReading = FloatArray(3)

            val callback = object : SensorEventListener {
                override fun onSensorChanged(event: SensorEvent) {
                    if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
                        lowPassFilter(event.values.copyOf(), accelerometerReading)
                    } else if (event.sensor.type == Sensor.TYPE_MAGNETIC_FIELD) {
                        lowPassFilter(event.values.copyOf(), magnetometerReading)
                    }
                    trySend(map180to360(convertRadtoDeg(calculateHeading(accelerometerReading, magnetometerReading))))
                }

                override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
                }
            }

            val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
            if (accelerometer != null) {
                sensorManager.registerListener(
                    callback,
                    accelerometer,
                    SensorManager.SENSOR_DELAY_NORMAL,
                    SensorManager.SENSOR_DELAY_NORMAL
                )
            }

            val magneticField = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
            if (magneticField != null) {
                sensorManager.registerListener(
                    callback,
                    magneticField,
                    SensorManager.SENSOR_DELAY_NORMAL,
                    SensorManager.SENSOR_DELAY_NORMAL
                )
            }

            awaitClose {
                sensorManager.unregisterListener(callback)
            }
        }
    }
}