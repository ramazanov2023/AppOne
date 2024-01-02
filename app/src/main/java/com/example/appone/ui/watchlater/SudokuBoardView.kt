package com.example.appone.ui.watchlater

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.example.appone.R

class SudokuBoardView(context: Context, attr: AttributeSet) : View(context, attr) {

    private var numErrors:Int = 0
    private var openedCells:Int = 0
    private var amountCellsInRow = 9
    private var sqrtSize = 3
    private var cellSize = 0F

    private var indexCell:Int = 0
    private var cell:Cell? = null

    private var selectCol = 6
    private var selectRow = 7
    private var listener: SudokuBoardView.OnTouchListener? = null
    private var finishListener: OnFinishListener? = null

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
        color = resources.getColor(R.color.light_line)
        style = Paint.Style.STROKE
        strokeWidth = 2F
    }

    private val number = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.FILL_AND_STROKE
        textSize = 72F
    }

    private val wrongNumber = Paint().apply {
        color = Color.RED
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

        fillCell2(canvas)
        drawLines(canvas)
        drawText(canvas)
        if(numErrors>2){
            Log.e("dfgg","1")
            finishListener?.onFinish(openedCells,numErrors,false)
            reset()
        }
        if(openedCells == HIDE_CELLS){
            Log.e("dfgg","2")
            finishListener?.onFinish(openedCells,numErrors,true)
            reset()
        }
    }

    private fun reset(){
        numErrors = 0
        openedCells = 0
    }

    private fun finishGame(win: Boolean) {
        TODO("Not yet implemented")
    }

    fun addSudokuNumbers(sudokuNumbers: List<Cell>) {

        this.sudokuNumbers = sudokuNumbers
        invalidate()
    }

    private fun fillCell2(canvas: Canvas?){

        sudokuNumbers?.let {
            val selectedCell = it[indexCell]
            for(i in it){
                if (selectedCell.value == i.value && !i.hide && !selectedCell.hide && !selectedCell.wrong ) {
                    drawCell(canvas, i,filledCell)
                }else if(i.id == indexCell){
                    drawCell(canvas, i,filledCell)
                }else if(i.row == selectedCell.row || i.col == selectedCell.col){
                    drawCell(canvas, i,linkedCells)
                }else if(i.col/sqrtSize == selectedCell.col/sqrtSize && i.row/sqrtSize == selectedCell.row/sqrtSize){
                    drawCell(canvas, i,linkedCells)
                }
            }
        }
    }

    private fun drawCell(canvas: Canvas?, i: Cell, cells: Paint) {
        val col = i.col
        val row = i.row
        canvas?.drawRect(
            col * cellSize,
            row * cellSize,
            (col + 1) * cellSize,
            (row + 1) * cellSize,
            cells
        )
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
//        sudokuNumbers?.forEach {
//            Log.e("forfor","${it.row}   ${it.col}   ${it.id}    ${it.value}")
//        }

        sudokuNumbers?.forEach {

            if(!it.hide) {
                var valueCell: String?
                var num: Paint?
                val col = it.col
                val row = it.row
                val textBounds = Rect()

                if (it.wrong){
                    Log.e("insins", "6.1  -  wrong")
                    num = wrongNumber
                    valueCell = it.wrong_number.toString()
                }else{
                    Log.e("insins", "6.2  -  number")
                    num = number
                    valueCell = it.value.toString()
                }

                Log.e("forfor","${it.id}    $valueCell    ${it.value}")

                num.getTextBounds(valueCell, 0, valueCell.length, textBounds)

                val numberWidth = num.measureText(valueCell)

                val numberHeight = textBounds.height()



                canvas?.drawText(
                    valueCell,
                    ((col * cellSize) + cellSize / 2 - numberWidth / 2).toFloat(),
                    ((row * cellSize) + cellSize / 2 + numberHeight / 2).toFloat(),
                    num
                )
            }
        }
    }
    private fun drawText2(canvas: Canvas?) {
        sudokuNumbers?.forEach {
            var valueCell: String?
            var num: Paint?
            val col = it.col
            val row = it.row
            val textBounds = Rect()

            if (it.wrong){
                Log.e("insins", "6.1  -  wrong")
                num = wrongNumber
                valueCell = it.wrong_number.toString()
            }else{

            }

            if(!it.hide) {

                Log.e("insins", "6.2  -  number")
                num = number
                valueCell = it.value.toString()

                num.getTextBounds(valueCell, 0, valueCell.length, textBounds)

                val numberWidth = num.measureText(valueCell)

                val numberHeight = textBounds.height()



                canvas?.drawText(
                    valueCell,
                    ((col * cellSize) + cellSize / 2 - numberWidth / 2).toFloat(),
                    ((row * cellSize) - cellSize / 2 + numberHeight / 2).toFloat(),
                    num
                )
            }
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
        Log.e("insins", "7x  - selectCol = $selectCol  selectRow = $selectRow")
//        indexCell = ((selectCol+1) * (selectRow+1))-1
//        indexCell = ((selectRow+1)*9) - (9-selectCol)
//        for(i in sudokuNumbers!!){
//            if(selectCol == i.col && selectRow == i.row){
//                cell = i
//            }
//        }
        listener?.onTouchCell(selectRow, selectCol)
    }

    fun updateSelectedCell(cell: Pair<Int, Int>?) {
        cell?.let {
            selectRow = cell.first
            selectCol = cell.second
            Log.e("insins", "7x2  - selectCol = $selectCol  selectRow = $selectRow")
            indexCell = ((selectRow+1)*9) - (9-selectCol)
            Log.e("insins", "7x3  - indexCell = $indexCell")
            invalidate()
        }
    }

    fun registerOnTouchListener(listener: OnTouchListener,finishListener: OnFinishListener) {
        this.listener = listener
        this.finishListener = finishListener
    }

    fun handleInputNumber(number: Int?) {
        number?.let {
            Log.e("insins", "3  -  $number")
            Log.e("insins", "3.1  -  $indexCell")
            val cell = sudokuNumbers!![indexCell]
            if(cell.wrong){
                onHideNumber2(number)
            }else{
                if(cell.hide){
                    Log.e("insins", "4  -  ${cell.hide}")
//                onHideNumber(cell,number)
                    onHideNumber2(number)
                }else{

                }
            }
        }
    }

    private fun onHideNumber2(number: Int) {
        sudokuNumbers?.let {
            if(sudokuNumbers!![indexCell].value == number){
                Log.e("insins", "5.1  -  true")
                sudokuNumbers!![indexCell].wrong = false
                openedCells++
//                sudokuNumbers!![indexCell].hide = true
            }else{
                Log.e("insins", "5.2  -  false")
                sudokuNumbers!![indexCell].wrong = true
                sudokuNumbers!![indexCell].wrong_number = number
                numErrors++
            }
            sudokuNumbers!![indexCell].hide = false
            invalidate()
        }
    }

    interface OnTouchListener {
        fun onTouchCell(selectRow: Int, selectCol: Int)
    }

    interface OnFinishListener {
        fun onFinish(openedSells: Int, errors: Int, win:Boolean)
    }


}