package app.dz.prizes.adapter


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import app.dz.prizes.R
import app.dz.prizes.model.SubscriptionList
import app.dz.prizes.retrofit2.model.Subscription


class SubscriptionAdapter(var items: List<Subscription> /*, private val itemClick: (Subscription) -> Unit*/) :
        RecyclerView.Adapter<SubscriptionAdapter.ViewHolder>() {

    //private var mItems: SubscriptionList = items//mutableListOf<Subscription>()
    private val mOnClickListener: View.OnClickListener

    init {
        //mItems = items

        mOnClickListener = View.OnClickListener { v ->
            notifyDataSetChanged()
        }

    }

    fun set(list: SubscriptionList) {
        //items.clear()
        //items.addAll(list)
        items = list
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscriptionAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_common, parent, false)
        return ViewHolder(v /*, itemClick*/)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(items.get(position))
    }

    //override fun getItemCount() = items.size


    override fun getItemCount(): Int {
        return items.size
    }


    /*
    class ViewHolder(view: View, private val itemClick: (Subscription) -> Unit)
        : RecyclerView.ViewHolder(view) {

        fun bindData(item: Subscription) {
            with(item) {
                //Picasso.with(itemView.ctx).load(iconUrl).into(itemView.icon)
                //itemView.date.text = date.toDateString()

                itemView.description.text = description
                //itemView.maxTemperature.text = "${high}º"
                itemView.minTemperature.text = "${low}º"
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }
    */


    //1
    class ViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        companion object {
            //5
            //private val PHOTO_KEY = "PHOTO"
        }

        //2
        private var view: View = v
        private var item: Subscription? = null

        //3
        init {
            v.setOnClickListener(this)
        }

        //4
        override fun onClick(v: View) {
            val context = itemView.context
            /*
            val showPhotoIntent = Intent(context, PhotoActivity::class.java)
            showPhotoIntent.putExtra(PHOTO_KEY, item)
            context.startActivity(showPhotoIntent)
            */
        }

        fun bindData(item: Subscription) {
            this.item = item
            //Picasso.with(view.context).load(item.url).into(view.itemImage)

            val tvTitle = view.findViewById<View>(R.id.tvTitle) as TextView
            tvTitle?.text = item.title

            val tvDescription = view.findViewById<View>(R.id.tvDescription) as TextView
            tvDescription?.text = item.description
        }
    }


}


