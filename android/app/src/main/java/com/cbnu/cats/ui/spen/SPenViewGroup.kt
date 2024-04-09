package com.cbnu.cats.ui.spen

import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.Gravity
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.SeekBar
import com.cbnu.cats.R
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog


class SPenViewGroup(context: Context) : LinearLayout(context) {
    private val sPenCanvas: SPenCanvas
    private val strokeWidthSeekBar: SeekBar
    private val btn_container: LinearLayout

    init {
        orientation = VERTICAL
        sPenCanvas = SPenCanvas(context)

        btn_container = LinearLayout(context).apply {
            orientation = HORIZONTAL
            setBackgroundColor(Color.LTGRAY)
            addView(ImageButton(context).apply {
                setImageResource(R.drawable.ic_pen) // Set your drawable resource
                setBackgroundResource(R.drawable.ic_color) // Set a circular background
                setOnClickListener {
                    // Check if the context is an Activity
                    val activity = context as? Activity
                    if (activity != null) {
                        // Create and show the MaterialColorPickerDialog
                        MaterialColorPickerDialog
                            .Builder(activity)
                            .setTitle("색상 선택")
                            .setColorListener { color, colorHex ->
                                // Handle Color Selection
                                sPenCanvas.setStrokeColor(color)
                            }
                            .setPositiveButton("확인")
                            .setNegativeButton("취소")
                            .show()
                    }
                }
                layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
                    gravity = Gravity.CENTER
                }
            })
            addView(ImageButton(context).apply {
                setImageResource(R.drawable.ic_eraser) // Set your drawable resource
                setBackgroundResource(R.drawable.ic_color) // Set a circular background
                setOnClickListener { /* Handle click */ }
                layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
                    gravity = Gravity.CENTER
                }
            })
            addView(ImageButton(context).apply {
                setImageResource(R.drawable.ic_undo) // Set your drawable resource
                setBackgroundResource(R.drawable.ic_color) // Set a circular background
                setOnClickListener {
                    sPenCanvas.prevPath()
                }
                layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
                    gravity = Gravity.CENTER
                }
            })
            addView(ImageButton(context).apply {
                setImageResource(R.drawable.ic_redo) // Set your drawable resource
                setBackgroundResource(R.drawable.ic_color) // Set a circular background
                setOnClickListener {
                    sPenCanvas.nextPath()
                }
                layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
                    gravity = Gravity.CENTER
                }
            })
            addView(ImageButton(context).apply {
                setImageResource(R.drawable.ic_refresh) // Set your drawable resource
                setBackgroundResource(R.drawable.ic_color) // Set a circular background
                setOnClickListener {
                    sPenCanvas.clearCanvas()
                }
                layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
                    gravity = Gravity.CENTER
                }
            })
            addView(ImageButton(context).apply {
                setImageResource(R.drawable.ic_note) // Set your drawable resource
                setBackgroundResource(R.drawable.ic_color) // Set a circular background
                setOnClickListener {
                    // TODO: 이미지 선택할 수 있도록 하기.
                }
                layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
                    gravity = Gravity.CENTER
                }
            })

            strokeWidthSeekBar = SeekBar(context).apply {
                setBackgroundColor(Color.LTGRAY)
                progressTintList = ColorStateList.valueOf(Color.BLACK)
                max = 20 // Maximum thickness
                progress = 2 // Initial thickness
                setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                        sPenCanvas.setStrokeWidth(progress.toFloat())
                    }

                    override fun onStartTrackingTouch(seekBar: SeekBar?) {}

                    override fun onStopTrackingTouch(seekBar: SeekBar?) {}
                })
                val layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, convertDpToPx(50))
                layoutParams.topMargin = convertDpToPx(10)
                layoutParams.bottomMargin = convertDpToPx(10)
                this.layoutParams = layoutParams
            }
            addView(strokeWidthSeekBar)
        }
        btn_container.setPadding(0,50,0,0)
        addView(btn_container)
        addView(sPenCanvas, LayoutParams(LayoutParams.MATCH_PARENT, 0, 1f))
    }

    private fun convertDpToPx(dp: Int): Int {
        val density = context.resources.displayMetrics.density
        return (dp * density).toInt()
    }
}