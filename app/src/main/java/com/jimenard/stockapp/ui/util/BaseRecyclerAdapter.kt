package com.jimenard.stockapp.ui.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Adaptador generico para todos los recicler view utlizados en la aplicacion
 */
class BaseRecyclerAdapter<T>(
    var items: List<T>,
    private val adapterObject: AdapterObject<T>,
    private val notifier: (View, T, Int) -> Unit,
    private val changePosition: Boolean = false
) : RecyclerView.Adapter<BaseRecyclerAdapter<T>.BaseRecyclerViewHolder>() {

    private var holders: ArrayList<BaseRecyclerViewHolder> = ArrayList()
    var selectedPosition: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): BaseRecyclerViewHolder {
        val holder = BaseRecyclerViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(this.adapterObject.layoutToCharge, parent, false)
        )
        this.holders.add(holder)
        return holder
    }

    override fun getItemCount() = this.items.size

    override fun onBindViewHolder(holder: BaseRecyclerViewHolder, position: Int) {
        holder.setParams(this.items[position])
    }

    /**
     * Se actuliza la informacion de la lista que se carga
     * @param itemsUpdated nueva lista a actualizar
     */
    fun setDataList(itemsUpdated: List<T>) {
        this.items = itemsUpdated
        this.notifyDataSetChanged()
    }

    /**
     * Se valida posicion seleccionada
     * @param position posicion seleccionada que llega desde interfaz
     */
    fun setCurrentPosition(position: Int) {
        if (position < this.holders.size) {
            this.holders[position].changeState(position)
        }
    }

    inner class BaseRecyclerViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        /**
         * Método encargado de recibir los parámetros cuando se renderiza la vista
         * @param itemRecyclerView item con la informacion para actualizar la vista
         */
        fun setParams(itemRecyclerView: T) {
            val view = adapterObject.chargeView(itemRecyclerView, this.view, this.adapterPosition)
            view.setOnClickListener {
                notifier.invoke(view, itemRecyclerView, adapterPosition)
                if (changePosition) {
                    changeState(this.adapterPosition)
                }
            }
            adapterObject.notifyView?.setOnClickListener {
                notifier.invoke(it, itemRecyclerView, -1)
                if (changePosition) {
                    changeState(this.adapterPosition)
                }
            }
        }

        /**
         * Se notifica para que el item cambie de estado
         * @param position posicion del item que se quiere cambiar de estado
         */
        fun changeState(position: Int) {
            if (selectedPosition != -1) {
                adapterObject.changeViewState(
                    holders[selectedPosition].view,
                    holders[position].view,
                    items[position]
                )
            } else {
                adapterObject.changeViewState(
                    holders[position].view,
                    holders[position].view,
                    items[position]
                )
            }
            selectedPosition = if (selectedPosition == position) -1 else position
        }
    }
}