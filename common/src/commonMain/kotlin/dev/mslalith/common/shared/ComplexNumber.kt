package dev.mslalith.common.shared

import androidx.compose.ui.geometry.Offset
import kotlin.math.atan2
import kotlin.math.sqrt

data class ComplexNumber(
    val real: Float,
    val imaginary: Float
) {

    companion object {
        fun from(offset: Offset) = ComplexNumber(real = offset.x, imaginary = offset.y)
    }

    val amplitude: Float
        get() = sqrt((real * real) + (imaginary * imaginary))

    val phase: Float
        get() = atan2(imaginary, real)

    operator fun plus(other: ComplexNumber) = ComplexNumber(
        real = real + other.real,
        imaginary = imaginary + other.imaginary
    )

    operator fun times(other: ComplexNumber) = ComplexNumber(
        real = (real * other.real) - (imaginary * other.imaginary),
        imaginary = (real * other.imaginary) + (imaginary * other.real)
    )

    fun reciprocal(): ComplexNumber {
        val scale = real * real + imaginary * imaginary
        return ComplexNumber(real = real / scale, imaginary = -imaginary / scale)
    }

    operator fun div(other: Int) = ComplexNumber(
        real = real / other,
        imaginary = imaginary / other
    )

    operator fun div(other: ComplexNumber) = times(other.reciprocal())
}
