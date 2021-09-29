package br.com.nandoligeiro.samplearch.presentation.custom

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.MotionEvent
import android.widget.LinearLayout
import android.widget.TextView
import br.com.nandoligeiro.samplearch.R
import br.com.nandoligeiro.samplearch.databinding.CustomButtonBinding


class CustomButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = com.google.android.material.R.attr.editTextStyle
) : LinearLayout(context, attrs, defStyleAttr) {

    private var onClickListener: OnClickListener? = null
    private var icon: TextView
    private var title: TextView

    private val binding = CustomButtonBinding.inflate(
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
        .also { addView(it.root) }

    init {
        icon = binding.tvIcon
        title = binding.tvTitle
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.CustomButton)

        setBackgroundColor(resources.getColor(R.color.design_default_color_primary))
        title.setTextColor(resources.getColor(R.color.colorAccent))

        icon.text = attributes.getString(R.styleable.CustomButton_textIcon)
        title.text = attributes.getString(R.styleable.CustomButton_title)
        attributes.recycle()
    }

    override fun dispatchKeyEvent(event: KeyEvent): Boolean {

        if (event.action == KeyEvent.ACTION_UP && event.keyCode == KeyEvent.KEYCODE_DPAD_CENTER || event.keyCode == KeyEvent.KEYCODE_ENTER) {
            onClickListener?.onClick(this)
        }
        return super.dispatchKeyEvent(event)
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        isPressed = when (ev.action) {
            MotionEvent.ACTION_DOWN -> { true }
            MotionEvent.ACTION_UP -> {
                onClickListener?.onClick(this)
                false
            }
            else -> { false }
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun setOnClickListener(l: OnClickListener?) {
        onClickListener = l
    }
}