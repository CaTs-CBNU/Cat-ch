package com.cbnu.cats.ui.spen

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PointF
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.drawable.BitmapDrawable
import android.os.Environment
import android.util.DisplayMetrics
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import com.cbnu.cats.R
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.Stack
import kotlin.math.abs


class SPenCanvas(context: Context) : View(context) {
    init {
        // 아래와 같이 지정해주지 않으면 지우개모드에서 이상한 검정색 원이 생긴다.
        // 참고로 기본 동작은 LAYER_TYPE_SOFTWARE 인데 자세한 내용은 찾아봐야한다.
        setLayerType(FrameLayout.LAYER_TYPE_HARDWARE, null)
    }

    companion object {
        private const val ERASER_SIZE = 20F
        private const val TOUCH_TOLERANCE = 2f

        // event.buttonState == MotionEvent.BUTTON_STYLUS_PRIMARY 일 때 각 액션이 아래로 치환되어 내려온다.
        // 이유는 모르겠다.
        private const val SPEN_ACTION_DOWN = 211
        private const val SPEN_ACTION_UP = 212
        private const val SPEN_ACTION_MOVE = 213
    }

    private var strokePoint = PointF(0F, 0F)

    private val strokePath = Path()

    private val prevStack = Stack<Bitmap>()
    private val nextStack = Stack<Bitmap>()
    private var drawMode = true

    // 드로잉 펜 설정
    private val strokePaint = Paint().apply {
        isAntiAlias = true
        isDither = true
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
        strokeWidth = convertDpToPixel(2F)
    }

    // 지우개 모드일 때 지워지는 영역을 표시하기 위한 설정
    private val eraserCirclePaint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
//        color = Color.BLACK
        strokeWidth = convertDpToPixel(1F)
    }

    // 지우개 설정
    private val eraserPaint = Paint().apply {
        isAntiAlias = true
        xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
    }

    private var isMoving = false
    private var isErasing = false

    private var lastEraserPositionX = 0F
    private var lastEraserPositionY = 0F

    private var scribeCanvasBitmap: Bitmap? = null

    private lateinit var scribeCanvas: Canvas

    private var canvasWidth = -1
    private var canvasHeight = -1

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        canvasWidth = w
        canvasHeight = h
//        // Load the image from resources
//        val resources = context.resources
//        val originalBitmap = BitmapFactory.decodeResource(resources, R.drawable.output)
//
//        // Calculate the scaling factors to fit the image to the canvas
//        val scaleX = canvasWidth.toFloat() / originalBitmap.width
//        val scaleY = canvasHeight.toFloat() / originalBitmap.height
//
//        // Create a Matrix to apply the scaling factors
//        val matrix = Matrix().apply {
//            postScale(scaleX, scaleY)
//        }
//        // Create a scaled Bitmap with the new dimensions
//        val scaledBitmap = Bitmap.createBitmap(originalBitmap, 0, 0, originalBitmap.width, originalBitmap.height, matrix, true)

        // Create a Bitmap object with the desired size
        scribeCanvasBitmap = Bitmap.createBitmap(canvasWidth, canvasHeight, Bitmap.Config.RGB_565)
        // Create a Canvas object with the Bitmap
        scribeCanvasBitmap?.let {
            scribeCanvas = Canvas(it)
        }

        // Draw the scaled image on the Canvas
//        scribeCanvas.drawBitmap(scaledBitmap, 0f, 0f, null)
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        // 그린 path 를 비트맵 객체에 중간 저장, 다시 캔버스에 그리는 부분이라고 보면 된다.
        scribeCanvasBitmap?.let { canvas.drawBitmap(it, 0F, 0F, strokePaint) }
        canvas.drawPath(strokePath, if (isErasing) eraserPaint else strokePaint)

        if (isErasing && isMoving) {
            canvas.drawCircle(lastEraserPositionX, lastEraserPositionY, ERASER_SIZE, eraserCirclePaint)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event == null) return false

        // 스타일러스 펜 터치가 아닐 경우 터치 이벤트를 처리하지 않는다.
        // 아마도 멀티 터치일 경우 무조건 0으로 하면 안될 것
        if (event.getToolType(0) != MotionEvent.TOOL_TYPE_STYLUS) return false

        // 터치 이벤트의 x,y 좌표를 가져옴
        val touchX = event.x
        val touchY = event.y

        if (event.buttonState == MotionEvent.BUTTON_STYLUS_PRIMARY) {
            // 화면에서 손을 떼지 않는 이상 드로잉/지우개 모드 전환을 하지 않을 것(대부분 앱이 그렇게 동작하길래..)
            if (!isMoving) {
                isErasing = !isErasing
            }
        }

        when (event.action) {
            // 스타일러스 펜이 화면에 닿았을 때의 동작을 처리함
            MotionEvent.ACTION_DOWN, SPEN_ACTION_DOWN -> {
                isMoving = true

                // 그리기 경로를 리셋하고, 그리기 시작점을 터치 좌표로 이동시킴
                strokePath.reset()
                strokePath.moveTo(touchX, touchY)



                if (isErasing) {
                    scribeCanvas.drawCircle(touchX, touchY, ERASER_SIZE, eraserPaint)

                    lastEraserPositionX = touchX
                    lastEraserPositionY = touchY
                } else {
                    strokePoint = PointF(touchX, touchY)
                }

                invalidate()
            }

            MotionEvent.ACTION_MOVE, SPEN_ACTION_MOVE -> {
                if (isErasing) {
                    strokePath.addCircle(touchX, touchY, ERASER_SIZE, Path.Direction.CW)

                    lastEraserPositionX = touchX
                    lastEraserPositionY = touchY
                } else {
                    val dx = abs(touchX - strokePoint.x)
                    val dy = abs(touchY - strokePoint.y)

                    if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
                        strokePath.quadTo(
                            strokePoint.x,
                            strokePoint.y,
                            (touchX + strokePoint.x) / 2,
                            (touchY + strokePoint.y) / 2
                        )

                        strokePoint = PointF(touchX, touchY)
                    }
                }

                invalidate()
            }
            // 스타일러스 펜이 화면에서 떨어졌을 때의 동작을 처리함
            MotionEvent.ACTION_UP, SPEN_ACTION_UP -> {
                scribeCanvasBitmap?.let { prevStack.push(it.copy(it.config , true)) }


                if (isErasing) {
                    scribeCanvas.drawPath(strokePath, eraserPaint)
                } else {
                    scribeCanvas.drawPath(strokePath, strokePaint)
                }

                isMoving = false
                isErasing = false

                lastEraserPositionX = 0F
                lastEraserPositionY = 0F

                strokePath.reset()

                invalidate()
            }
        }

        return true
    }

    fun setStrokeWidth(width: Float) {
        strokePaint.strokeWidth = convertDpToPixel(width)
        invalidate() // Apply the changed line thickness by requesting a redraw
    }

    fun setStrokeColor(color: Int) {
        strokePaint.color = color
        invalidate() // Apply the changed color by requesting a redraw
    }
    fun clearCanvas() {
        scribeCanvas.drawColor(0, PorterDuff.Mode.CLEAR)
        invalidate()
    }
    private fun convertDpToPixel(dp: Float): Float {
        return if (context != null) {
            val resources = context.resources
            val metrics = resources.displayMetrics

            dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
        } else {
            val metrics = Resources.getSystem().displayMetrics

            dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
        }
    }

    fun prevPath() {
        if (prevStack.isNotEmpty()) {
            scribeCanvasBitmap?.let { nextStack.push(it.copy(it.config, true)) }


            // Restore the canvas to the state before the last path was drawn
            scribeCanvasBitmap = prevStack.pop()
            scribeCanvas = Canvas(scribeCanvasBitmap!!)
            invalidate()
        }
    }
    fun nextPath() {
        if (nextStack.isNotEmpty()) {
            // Save the current state of the canvas before redoing
            scribeCanvasBitmap?.let { prevStack.push(it.copy(it.config, true)) }


            // Restore the canvas to the state before the undo operation
            scribeCanvasBitmap = nextStack.pop()
            scribeCanvas = Canvas(scribeCanvasBitmap!!)
            invalidate()
        }
    }
//    fun changeBackgroundColor(color: Int) {
//        eraserPaint.color = color
//        setBackgroundColor(color)
//    }

    fun changeBackgroundImage(resourceId: Int) {
        // Load the image from resources
        val resources = context.resources
        val originalBitmap = BitmapFactory.decodeResource(resources, resourceId)

        if (originalBitmap != null) {
            // Calculate the scaling factors to fit the image to the canvas
            val scaleX = canvasWidth.toFloat() / originalBitmap.width
            val scaleY = canvasHeight.toFloat() / originalBitmap.height

            // Create a Matrix to apply the scaling factors
            val matrix = Matrix().apply {
                postScale(scaleX, scaleY)
            }
            // Create a scaled Bitmap with the new dimensions
            val scaledBitmap = Bitmap.createBitmap(originalBitmap, 0, 0, originalBitmap.width, originalBitmap.height, matrix, true)

            // Create a Bitmap object with the desired size
            scribeCanvasBitmap = Bitmap.createBitmap(canvasWidth, canvasHeight, Bitmap.Config.ARGB_8888)
            // Create a Canvas object with the Bitmap
            scribeCanvasBitmap?.let {
                scribeCanvas = Canvas(it)
            }

            // Draw the scaled image on the Canvas
            scribeCanvas?.drawBitmap(scaledBitmap, 0f, 0f, null)

            // Set the new background image
//            background = scaledBitmap
            invalidate()
        } else {
            // Handle null bitmap (optional)
            android.util.Log.e("SPenCanvas", "Failed to decode resource: $resourceId")
        }
    }
    // 비트맵을 PNG 파일로 저장하는 함수
//    fun saveBitmapToFile(bitmap: Bitmap, format: Bitmap.CompressFormat, filename: String): File? {
//        val directory = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
//        val file = File(directory, filename)
//        var fos: FileOutputStream? = null
//        try {
//            fos = FileOutputStream(file)
//            bitmap.compress(format, 100, fos)
//            fos.close()
//        } catch (e: IOException) {
//            e.printStackTrace()
//            return null
//        }
//        return file
//    }
    fun setEraser() {
        drawMode = false
    }
}