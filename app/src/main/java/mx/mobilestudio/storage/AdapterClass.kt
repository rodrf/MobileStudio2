package mx.mobilestudio.storage


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_task.view.*
import mx.mobilestudio.storage.roomdatabase.EntityTODO
import kotlin.properties.Delegates

class AdapterClass : RecyclerView.Adapter<AdapterClass.ViewHolder>() {

    var dataList: List<EntityTODO> by Delegates.observable(emptyList()) { _, _, _ -> notifyDataSetChanged() }

    private lateinit var clickListener: (Int, EntityTODO) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false))


    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(dataList[position])
        holder.itemView.setOnClickListener {
            clickListener(position, dataList[position])
        }

    }

    fun setListener(listener: (Int, EntityTODO) -> Unit) {
        clickListener = listener
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(data: EntityTODO) {
            itemView.tvItemTitle?.text = data.title
            itemView.tvItemID?.text = "${data.id}"
        }
    }
}