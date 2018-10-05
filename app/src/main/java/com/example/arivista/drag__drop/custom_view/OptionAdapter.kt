package arivista.lib.dragdrop

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class OptionAdapter(private val mItems: ArrayList<Optionmodel>) : BaseAdapter(), View.OnTouchListener {
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val vh: ViewHolder
        if (convertView == null) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.listview_item, parent,
                    false)
            view.setOnTouchListener(this)
            vh = ViewHolder(view)
            view.tag = vh
        } else {
            view = convertView
            vh = view.tag as ViewHolder
        }
        vh.text.text = mItems.get(position).name.toString()
        return view
    }

    override fun getItemViewType(position: Int): Int {
        return 0
    }

    override fun getCount(): Int {
        return mItems.size
    }

    override fun getItem(position: Int): Any {
        return mItems[position]
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View, event: MotionEvent): Boolean {
        val vh = v.tag as ViewHolder
        vh.lastTouchedX = event.x
        vh.lastTouchedY = event.y
        return false
    }

    class ViewHolder(v: View) {
        var text: TextView
        var lastTouchedX: Float = 0.toFloat()
        var lastTouchedY: Float = 0.toFloat()

        init {
            text = v.findViewById(R.id.label) as TextView
        }
    }
}
