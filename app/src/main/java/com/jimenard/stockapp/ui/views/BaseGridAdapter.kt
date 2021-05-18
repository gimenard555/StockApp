package com.jimenard.stockapp.ui.views

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.jimenard.stockapp.ui.util.AdapterObject

class BaseGridAdapter<T>(
    private val context: Context,
    var items: List<T>,
    private val adapterObject: AdapterObject<T>,
    private val notifier: (View, T, Int) -> Unit
) : BaseAdapter() {

    private var holderViews = ArrayList<View>()
    override fun getCount() = this.items.size
    override fun getItem(position: Int) = this.items[position]
    override fun getItemId(position: Int) = 0L

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {
        val inflater = context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val holder = inflater.inflate(adapterObject.layoutToCharge, viewGroup, false)
        this.holderViews.add(holder)
        var current: View? = null
        if (position < this.holderViews.size) {
            current =
                this.adapterObject.chargeView(items[position], this.holderViews[position], position)
        }
        current?.setOnClickListener {
            notifier.invoke(it, items[position], position)
        }
        return current ?: view!!
    }

    /**
     * Metodo para hacer la seleccion desde exterior
     */
    fun selectCurrentPosition(value: T) {
        val newPosition = this.items.indexOf(value)
        if (newPosition != -1 && newPosition < this.holderViews.size) {
            adapterObject.changeViewState(
                holderViews[newPosition],
                holderViews[newPosition],
                items[newPosition]
            )
        }
    }
}