package arivista.lib.dragdrop

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.Context
import android.graphics.Canvas
import android.graphics.Point
import android.util.AttributeSet
import android.view.DragEvent
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.*


class CustomView : LinearLayout, View.OnDragListener {
    var options = ArrayList<Optionmodel>()
    var imageResource: Int? = null
    var optionscopy = ArrayList<Optionmodel>()
    var image: ImageView? = null
    var context1: Context? = null
    var scalevalue: Double? = null
    var optionlistView: ListView? = null
    var submit: Button? = null
    var reset: Button? = null
    var reveal: Button? = null
    var mainholder: AbsoluteLayout? = null
    private var optionmodel: Optionmodel? = null


    constructor(context: Context) : super(context) {
        context1 = context
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        context1 = context
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        context1 = context
        init()
    }

    private fun init() {
        View.inflate(context, R.layout.dragframe, this)

        optionlistView = findViewById<ListView>(R.id.list)
        mainholder = findViewById(R.id.c)
        image = findViewById(R.id.image)
        submit = findViewById(R.id.btnSubmit)
        reset = findViewById(R.id.btnReset)
        reveal = findViewById(R.id.btnReveal)
        submit!!.setOnClickListener {
            submit!!.isEnabled = false
            reveal!!.isEnabled = true
            reset!!.isEnabled = true

            submit()
        }

        reset!!.setOnClickListener {
            submit!!.isEnabled = false
            reveal!!.isEnabled = false
            reset!!.isEnabled = false

            reset()
        }
        reveal!!.setOnClickListener {
            submit!!.isEnabled = false
            reveal!!.isEnabled = false
            reset!!.isEnabled = true
            reveal()
        }
    }

    fun setValue(optionslist: ArrayList<Optionmodel>) {
        options = optionslist
        optionlistView!!.adapter = OptionAdapter(optionslist)
        optionlistView!!.onItemLongClickListener = AdapterView.OnItemLongClickListener { parent, view, position, id ->
            val vh = view.tag as OptionAdapter.ViewHolder
            val touchedX = (vh.lastTouchedX + 0.5f).toInt()
            val touchedY = (vh.lastTouchedY + 0.5f).toInt()
            this.optionmodel = optionslist.get(position)
            view.startDrag(null, object : View.DragShadowBuilder(view) {
                override fun onProvideShadowMetrics(shadowSize: Point, shadowTouchPoint: Point) {
                    super.onProvideShadowMetrics(shadowSize, shadowTouchPoint)
                    shadowTouchPoint.x = touchedX
                    shadowTouchPoint.y = touchedY
                }

                override fun onDrawShadow(canvas: Canvas) {
                    super.onDrawShadow(canvas)
                }
            }, view, 0)
            false
        }
        optionlistView!!.setOnDragListener { v, event ->
            if (event.action == DragEvent.ACTION_DROP) {
                val view = event.localState as View
                view.visibility = View.VISIBLE
            }
            true
        }
    }

    fun setImage(image: Int) {
        imageResource = image

    }

    @SuppressLint("InflateParams", "ClickableViewAccessibility")
    fun setInput(value: ArrayList<Optionmodel>) {
        setValue(value)
        val observer = mainholder!!.getViewTreeObserver()
        observer.addOnGlobalLayoutListener {
            val headerLayoutWidth = mainholder!!.getWidth()
            val r: Double
            val a = 500
            val b = 500
            val p: Double
            r = (a / b).toDouble()
            p = (height / a).toDouble()
            val height = (headerLayoutWidth * r).toInt()
            scalevalue = p
            val param1 = AbsoluteLayout.LayoutParams(headerLayoutWidth, height, 0, 0)
            image!!.layoutParams = param1
            image!!.setBackgroundResource(this.imageResource!!)
        }
        for ((i, obj) in value.withIndex()) {
            val view = LayoutInflater.from(context).inflate(R.layout.draggablelayout, null, false)
            val contactUs = view.findViewById<TextView>(R.id.option)
            view.setBackgroundResource(R.drawable.rect_bg)
            contactUs.tag = obj.name
            view.setOnDragListener(this)
            val param = AbsoluteLayout.LayoutParams(obj.width!!, obj.height!!, obj.x!!, obj.y!!)
            view.setLayoutParams(param)
            if (mainholder != null) {
                mainholder!!.addView(view)
            }
            contactUs.setOnTouchListener(ChoiceTouchListener(i))
        }

    }

    //submit fun
    fun submit() {
        if (mainholder != null) {

            for (pos in 0 until mainholder!!.childCount) {
                try {
                    val view = mainholder!!.getChildAt(pos)


                    val dropView = view.findViewById(R.id.option) as TextView
                    val tag1a = dropView.text.toString()
                    val tag2 = dropView.tag.toString()
                    if (tag2 == tag1a) {
                        dropView.setBackgroundResource(R.color.highlight)
                    } else {
                        dropView.setBackgroundResource(R.color.orange)
                    }
                } catch (e: Exception) {
                }
            }
        }
    }

    //reset fun
    fun reset() {
        for (i in 0 until mainholder!!.getChildCount()) {
            try {
                val childAt = mainholder!!.getChildAt(i)
                val dropTarget = childAt.findViewById(R.id.option) as TextView
                dropTarget.setBackgroundColor(resources
                        .getColor(android.R.color.transparent))
                dropTarget.setTextColor(
                        resources.getColor(android.R.color.white))
                optionscopy.add(Optionmodel(dropTarget.text.toString(), 0, 0, 0, 0))
                dropTarget.text = ""
            } catch (e: Exception) {

            }
        }
        setValue(optionscopy)
    }

    //reveal fun
    fun reveal() {
        val j = 0
        options.clear()
        for (i in j until mainholder!!.childCount) {
            try {
                val childAt = mainholder!!.getChildAt(i)
                val dropTarget = childAt.findViewById(R.id.option) as TextView
                dropTarget.text = (dropTarget.tag.toString())
                dropTarget.setBackgroundColor(resources.getColor(R.color.ddreveal))
            } catch (e: Exception) {
            }
        }
    }

    //TouchListener
    internal inner class ChoiceTouchListener(private val pos: Int) : View.OnTouchListener {
        @SuppressLint("NewApi", "ClickableViewAccessibility")
        override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
            if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                val dropTarget = view.findViewById(R.id.option) as TextView
                optionmodel?.name = dropTarget.text.toString()
                val data = ClipData.newPlainText(optionmodel?.name, "")
                var srcview: View? = null
                try {
                    if (mainholder != null) {
                        for (i in 0..mainholder!!.getChildCount()) {
                            val src = mainholder!!.getChildAt(i)

                            if (src.getTag().toString() == pos.toString() && src.getTag() != null) {
                                srcview = src
                                break
                            }
                        }
                    }
                    val shadowBuilder = View.DragShadowBuilder(srcview)
                    srcview!!.startDrag(data, shadowBuilder, srcview, 0)
                } catch (e: Exception) {
                }

                return true
            } else {
                return false

            }
        }
    }


    //Drag and Drop action
    @SuppressLint("PrivateResource")
    override fun onDrag(v: View, event: DragEvent): Boolean {
        val action = event.action
        when (action) {
            DragEvent.ACTION_DRAG_STARTED -> {
            }
            DragEvent.ACTION_DRAG_ENTERED -> {
                (v.findViewById(R.id.option) as TextView)
                        .setBackgroundColor(
                                resources
                                        .getColor(R.color.colorAccent))
                (v.findViewById(R.id.option) as TextView)
                        .setTextColor(
                                resources
                                        .getColor(R.color.black))
                v.invalidate()
            }
            DragEvent.ACTION_DRAG_EXITED -> {

                if ((v.findViewById(R.id.option) as TextView).text.toString().isEmpty()) {

                    (v.findViewById(R.id.option) as TextView)
                            .setBackgroundColor(
                                    resources
                                            .getColor(android.R.color.transparent))
                } else {
                    (v.findViewById(R.id.option) as TextView)
                            .setBackgroundColor(
                                    resources
                                            .getColor(R.color.highlighted_text_material_dark))
                }

                v.visibility = View.VISIBLE
            }
            DragEvent.ACTION_DROP -> {
                val dropTarget = v.findViewById(R.id.option) as TextView
                val text = dropTarget.text
                if (text != "") options.add(Optionmodel(dropTarget.text.toString(), 0, 0, 0, 0))
                dropTarget.text = optionmodel!!.name
                options.remove(optionmodel!!)
                if (optionlistView != null) {
                    optionlistView!!.adapter = OptionAdapter(options)

                }
                (v.findViewById(R.id.option) as TextView)
                        .setBackgroundColor(
                                resources
                                        .getColor(R.color.highlighted_text_material_dark))
                (v.findViewById(R.id.option) as TextView)
                        .setTextColor(
                                resources
                                        .getColor(R.color.primary_text_default_material_light))
                (v.findViewById(R.id.option) as TextView).text = (optionmodel!!.name)
                (v.findViewById(R.id.option) as TextView).visibility = View.VISIBLE
                if (options.size == 0) {
                    submit!!.isEnabled = true
                } else {
                    submit!!.isEnabled = false
                }

            }
            DragEvent.ACTION_DRAG_ENDED ->
                v.invalidate()
            else -> {
            }
        }
        return true
    }

}