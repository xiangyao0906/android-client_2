package com.xinly.dendrobe.util

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.NumberFormat
import kotlin.math.roundToInt

/**
 * Created by zm on 2019-07-03.
 */
object BigDecimalUtils {
    val MONEY_POINT = 2 // 货币保留两位小数

    /**
     * 格式化精度
     *
     * @param v
     * @param scale
     * 小数位数
     * @return double
     */
    fun formatString(v: Double, scale: Int): String {
        if (scale < 0) {
            throw IllegalArgumentException(
                "The   scale   must   be   a   positive   integer   or   zero"
            )
        }
        if (scale == 0) {
            return DecimalFormat("0").format(v)
        }
        var formatStr = "0."
        for (i in 0 until scale) {
            formatStr += "0"
        }
        return DecimalFormat(formatStr).format(v)
    }

    /**
     * 格式化精度
     *
     * @param v
     * @param point
     * 小数位数
     * @return double
     */
    @JvmStatic
    fun format(v: Double, point: Int): String {
        val b = BigDecimal(v)
        return b.setScale(point, BigDecimal.ROUND_HALF_UP).toPlainString()
    }

    /**
     *
     * @param v
     * @param point
     * @return
     */
    fun formatRoundUp(v: Double, point: Int): Double {
        val nf = NumberFormat.getInstance()
        nf.roundingMode = RoundingMode.HALF_UP//设置四舍五入
        nf.minimumFractionDigits = point//设置最小保留几位小数
        nf.maximumFractionDigits = point//设置最大保留几位小数
        return java.lang.Double.valueOf(nf.format(v))
    }

    /**
     * 格式化金额。带千位符
     *
     * @param v
     * @return
     */
    @JvmStatic
    fun moneyFormat(v: Double): String {
        val formater = DecimalFormat()
        formater.maximumFractionDigits = 2
        formater.groupingSize = 3
        formater.roundingMode = RoundingMode.FLOOR
        return formater.format(v)
    }

    /**
     * 带小数的显示小数。不带小数的显示整数
     * @param d
     * @return
     */
    fun doubleTrans(d: Double): String {
        return if ((d).roundToInt() - d == 0.0) {
            d.toLong().toString()
        } else d.toString()
    }

    /**
     * BigDecimal 相加
     *
     * @param v1
     * @param v2
     * @return double
     */
    fun add(v1: Double, v2: Double): Double {
        val n1 = BigDecimal(v1.toString())
        val n2 = BigDecimal(v2.toString())
        return n1.add(n2).toDouble()
    }

    /**
     * BigDecimal 相减
     *
     * @param v1
     * @param v2
     * @return double
     */
    fun subtract(v1: Double, v2: Double): Double {
        val n1 = BigDecimal(v1.toString())
        val n2 = BigDecimal(v2.toString())
        return n1.subtract(n2).toDouble()
    }

    /**
     * BigDecimal 相乘
     *
     * @param v1
     * @param v2
     * @return double
     */
    fun multiply(v1: Double, v2: Double): Double {
        val n1 = BigDecimal(v1.toString())
        val n2 = BigDecimal(v2.toString())
        return n1.multiply(n2).toDouble()
    }

    /**
     * BigDecimal 相除
     *
     * @param v1
     * @param v2
     * @return double
     */
    fun divide(v1: Double, v2: Double): Double {
        val n1 = BigDecimal(v1.toString())
        val n2 = BigDecimal(v2.toString())
        return n1.divide(n2, 10, BigDecimal.ROUND_HALF_UP).toDouble()
    }

    /**
     * 比较大小 小于0：v1 < v2 大于0：v1 > v2 等于0：v1 = v2
     *
     * @param v1
     * @param v2
     * @return
     */
    fun compare(v1: Double, v2: Double): Int {
        val n1 = BigDecimal(v1.toString())
        val n2 = BigDecimal(v2.toString())
        return n1.compareTo(n2)
    }
}