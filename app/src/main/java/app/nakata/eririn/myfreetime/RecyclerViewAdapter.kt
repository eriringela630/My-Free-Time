package app.nakata.eririn.myfreetime

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.*

 class RecyclerViewAdapter(private val context: Context):
     RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val time: TextView = view.findViewById(R.id.timeTextView)
        val schedule: EditText = view.findViewById(R.id.scheduleTextView)
    }

     val items:MutableList<TimeData> = mutableListOf()
     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
         val view =
             LayoutInflater.from(context).inflate(R.layout.item_time_data_cell,parent,false)
         return ViewHolder(view)
     }

     override fun getItemCount(): Int {
        return items.size
     }

     override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         val item = items[position]
         holder.time.text = item.time
         holder.schedule.setText(item.schedule)
         holder.schedule.doAfterTextChanged {
             if (it?.isNotEmpty() == true) {
                 item.schedule = it.toString()
             }
         }
     }

     fun returntimes() {

         var number = 0

         for(item in items) {
             if(item.schedule.isEmpty()){
                 number += 1
             }
         }
     }

     fun addAll(items:List<TimeData>){
         this.items.addAll(items)
         notifyDataSetChanged()
     }



 }