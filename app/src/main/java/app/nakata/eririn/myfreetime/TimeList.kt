package app.nakata.eririn.myfreetime

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.timelist.*

class TimeList : AppCompatActivity() {


    val timeData:List<TimeData> = listOf(
        TimeData("6:00",""),
        TimeData("7:00",""),
        TimeData("8:00",""),
        TimeData("9:00",""),
        TimeData("10:00",""),
        TimeData("11:00",""),
        TimeData("12:00",""),
        TimeData("13:00",""),
        TimeData("14:00",""),
        TimeData("15:00",""),
        TimeData("16:00",""),
        TimeData("17:00",""),
        TimeData("18:00",""),
        TimeData("19:00",""),
        TimeData("20:00",""),
        TimeData("21:00",""),
        TimeData("22:00",""),
        TimeData("23:00",""),
        TimeData("24:00","")
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.timelist)

        //アダプター
        val adapter = RecyclerViewAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        adapter.addAll(timeData)

        //ページ遷移
        val timer = Intent(this,TimerActivity::class.java)

        calculateButton.setOnClickListener {
            startActivity(timer)
        }

        //ローカル
        val dataStore: SharedPreferences = getSharedPreferences("DataStore", Context.MODE_PRIVATE)

        calculateButton.setOnClickListener {
            val freetime = adapter.returntimes()

            val editor = dataStore.edit()
            editor.putInt("Input",freetime)

            editor.apply()

        }

    }


}

