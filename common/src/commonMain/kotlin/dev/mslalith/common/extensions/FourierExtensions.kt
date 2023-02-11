package dev.mslalith.common.extensions

import androidx.compose.ui.geometry.Offset
import dev.mslalith.common.shared.ComplexNumber
import dev.mslalith.common.shared.FourierItem
import dev.mslalith.common.utils.TWO_PI
import kotlin.math.cos
import kotlin.math.sin

fun discreteFourierTransformOf(
    series: List<Offset>,
): List<FourierItem> {
    // Formula reference: https://wikimedia.org/api/rest_v1/media/math/render/svg/9b1598508ebb847e6c726d5b741ae2363d84f616
    val N = series.size
    return List(series.size) {
        val k = it + 1
        var sum = ComplexNumber(real = 0f, imaginary = 0f)
        series.indices.forEach { n ->
            val theta = TWO_PI * k * n / N
            sum += ComplexNumber.from(series[n]) * ComplexNumber(real = cos(-theta), imaginary = sin(-theta))
        }
        sum /= N

        FourierItem(
            complexNumber = sum,
            frequency = k,
        )
    }
}
