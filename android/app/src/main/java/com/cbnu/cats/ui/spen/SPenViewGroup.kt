package com.cbnu.cats.ui.spen

import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.SeekBar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cbnu.cats.R
import com.cbnu.cats.ui.Template
import com.cbnu.cats.ui.TemplateAdapter
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog

class SPenViewGroup(context: Context) : LinearLayout(context) {
    private val sPenCanvas: SPenCanvas
    private val strokeWidthSeekBar: SeekBar
    private val btn_container: LinearLayout

    init {
        orientation = VERTICAL
        sPenCanvas = SPenCanvas(context)
//        val backgroundImage: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.avatar_0)
        // TEST용 이미지 세팅
//        sPenCanvas.changeBackgroundImage(backgroundImage)

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
                setOnClickListener { /* Handle click */
                    sPenCanvas.setEraser() }
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
                setOnClickListener {view ->
                    showPopupWindow(view)
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

    private fun showPopupWindow(anchorView: View) {
        // 팝업 윈도우 레이아웃을 인플레이트
        val popupView = LayoutInflater.from(context).inflate(R.layout.popup_window, null)

        // 팝업 윈도우 생성
        val popupWindow = PopupWindow(popupView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true)
        popupWindow.setBackgroundDrawable(ColorDrawable()) // 팝업 윈도우의 배경을 설정

        // 템플릿 RecyclerView 설정
        val recyclerView = popupView.findViewById<RecyclerView>(R.id.rv_templates)
        recyclerView.layoutManager = GridLayoutManager(context, 3)
        val templates = getTemplates() // 템플릿 목록을 가져오는 함수
        recyclerView.adapter = TemplateAdapter(templates) { template ->
            val resources = context.resources
            val bitmap = BitmapFactory.decodeResource(resources, template.imageResId)
            if (bitmap != null) {
                sPenCanvas.changeBackgroundImage(template.imageResId)
            } else {
                Log.e("SPenViewGroup", "Failed to decode resource: ${template.imageResId}")
            }
            popupWindow.dismiss()
        }

        // 팝업 윈도우를 앵커 뷰 아래에 표시
        popupWindow.showAsDropDown(anchorView, 0, 0)
    }

    private fun getTemplates(): List<Template> {
        // 템플릿 목록을 반환하는 함수
        return listOf(
            Template("Template 1", R.drawable.default_background),
            Template("Template 2", R.drawable.avatar_1),
            Template("Template 3", R.drawable.avatar_10),
        )
    }
}