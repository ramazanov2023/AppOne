package com.example.appone.ui.watchlater

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.appone.R

class SudokuBoardView(context: Context, attr: AttributeSet) : View(context, attr) {

    private var amountCellsInRow = 9
    private var sqrtSize = 3
    private var cellSize = 0F

    private var selectCol = 6
    private var selectRow = 7
    private var listener: SudokuBoardView.OnTouchListener? = null

    private var sudokuNumbers: List<Cell>? = null

    private val filledCell = Paint().apply {
        color = resources.getColor(R.color.selected_cell)
//        color = Color.parseColor("#4CAF50")
        style = Paint.Style.FILL
    }

    private val linkedCells = Paint().apply {
        color = resources.getColor(R.color.link_cells)
//        color = Color.parseColor("#4CAF50")
        style = Paint.Style.FILL
    }


    private val boldLine = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.STROKE
        strokeWidth = 4F
    }

    private val thinLine = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.STROKE
        strokeWidth = 2F
    }

    private val number = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.FILL_AND_STROKE
        textSize = 72F
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val sizePixels = Math.min(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(sizePixels, sizePixels)
    }

    override fun onDraw(canvas: Canvas?) {
        cellSize = (width / amountCellsInRow).toFloat()

        fillCell(canvas)
        drawLines(canvas)
        drawText(canvas)
    }

    fun addSudokuNumbers(sudokuNumbers: List<Cell>) {
        this.sudokuNumbers = sudokuNumbers
        invalidate()
    }

    private fun fillCell(canvas: Canvas?) {
        for (r in 0..amountCellsInRow) {
            for (c in 0..amountCellsInRow) {
                if (r == selectCol && c == selectRow) {
                    canvas?.drawRect(
                        r * cellSize,
                        c * cellSize,
                        (r + 1) * cellSize,
                        (c + 1) * cellSize,
                        filledCell
                    )
                } else if (r == selectCol || c == selectRow) {
                    canvas?.drawRect(
                        r * cellSize,
                        c * cellSize,
                        (r + 1) * cellSize,
                        (c + 1) * cellSize,
                        linkedCells
                    )
                } else if (r / sqrtSize == selectCol / sqrtSize && c / sqrtSize == selectRow / sqrtSize) {
                    canvas?.drawRect(
                        r * cellSize,
                        c * cellSize,
                        (r + 1) * cellSize,
                        (c + 1) * cellSize,
                        linkedCells
                    )
                }
            }
        }
    }

    private fun drawText(canvas: Canvas?) {
        sudokuNumbers?.forEach {
            val valueCell = it.value.toString()
            val col = it.col
            val row = it.row

            val textBounds = Rect()

            number.getTextBounds(valueCell, 0, valueCell.length, textBounds)

            val numberWidth = number.measureText(valueCell)

            val numberHeight = textBounds.height()

            canvas?.drawText(
                valueCell,
                ((col * cellSize) + cellSize / 2 - numberWidth / 2).toFloat(),
                ((row * cellSize) - cellSize / 2 + numberHeight / 2).toFloat(),
                number
            )
        }
    }

    private fun drawLines(canvas: Canvas?) {
        canvas?.drawRect(0F, 0F, width.toFloat(), height.toFloat(), boldLine)

        for (i in 1 until amountCellsInRow) {
            val line = when (i % sqrtSize) {
                0 -> boldLine
                else -> thinLine
            }

            canvas?.drawLine(i * cellSize, 0F, i * cellSize, height.toFloat(), line)
            canvas?.drawLine(0F, i * cellSize, width.toFloat(), i * cellSize, line)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                handleClickCell(event.x, event.y)
                true
            }
            else -> false
        }
    }

    private fun handleClickCell(x: Float, y: Float) {
        selectCol = (x / cellSize).toInt()
        selectRow = (y / cellSize).toInt()
        listener?.onTouchCell(selectRow, selectCol)
    }

    fun updateSelectedCell(cell: Pair<Int, Int>?) {
        cell?.let {
            selectCol = cell.second
            selectRow = cell.first
            invalidate()
        }
    }

    fun registerOnTouchListener(listener: OnTouchListener) {
        this.listener = listener
    }

    interface OnTouchListener {
        fun onTouchCell(selectRow: Int, selectCol: Int)
    }
}