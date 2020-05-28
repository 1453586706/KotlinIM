package com.huluobo.lc.kotlinim.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.huluobo.lc.kotlinim.R
import org.jetbrains.anko.sp

/**
 * @author Lc
 * @description:
 * @date :2020/5/28 11:29
 */
class SlideBar(context: Context?, attrs: AttributeSet? = null) : View(context, attrs) {
    var sectionHeight = 0f

    var paint = Paint()

    var textBaseline = 0f

    companion object {
        private val SECTIONS = arrayOf(
            "A",
            "B",
            "C",
            "D",
            "E",
            "F",
            "G",
            "H",
            "I",
            "J",
            "K",
            "L",
            "M",
            "N",
            "O",
            "P",
            "Q",
            "R",
            "S",
            "T",
            "U",
            "V",
            "W",
            "X",
            "Y",
            "Z"
        )
    }

    init {
        paint.apply {
            color = resources.getColor(R.color.qq_section_text_color)
            textSize = sp(12).toFloat()
            textAlign = Paint.Align.CENTER
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        //计算每个字符分配的高度
        sectionHeight = h * 1.0f / SECTIONS.size
        val fontMetrics = paint.fontMetrics
        //计算绘制文本的高度
        val textHeight = fontMetrics.descent - fontMetrics.ascent//descent代表文字的底部线,ascent代表的是顶部线
        textBaseline = sectionHeight / 2 + (textHeight / 2 + fontMetrics.descent)
    }

    override fun onDraw(canvas: Canvas?) {
        //绘制所有的字母
        val x = width * 1.0f / 2//绘制字符的初始位置
        var baseline = textBaseline
        SECTIONS.forEach {
            canvas?.drawText(it, x, baseline, paint)
            //更新Y,绘制下一个
            baseline += sectionHeight
        }
    }
}