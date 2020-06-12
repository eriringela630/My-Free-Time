package app.nakata.eririn.myfreetime

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_timer.*
import java.util.*

class TimerActivity : AppCompatActivity() {

    val dataStore: SharedPreferences = getSharedPreferences("DataStore", Context.MODE_PRIVATE)

    //残り時間のセット
    var timertime: Int = dataStore.getInt("Input",18)
    var minutes : Int = timertime*60

    //タイマーをセット
    val timer : CountDownTimer = object : CountDownTimer(timertime.toLong()*10,timertime.toLong()){
        override fun onFinish() {
            imageView.setImageResource(R.drawable.flower5)
            secondText.text = "タイムオーバー"
        }
        //カウントダウンごとの処理
        override fun onTick(millisUntilFinished: Long){
            minutes -= 1
            secondText.text = minutes.toString()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        //タイマー時間表示
        secondText.text = minutes.toString()

        //ページ遷移
        val timelist = Intent(this,TimeList::class.java)

        returnButton.setOnClickListener {
            startActivity(timelist)
        }

        //タイマーボタン
        startButton.setOnClickListener{
            //アラーム機能
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = System.currentTimeMillis()
            calendar.add(Calendar.HOUR,1)

            val intent = Intent(this,AlarmBroadcastReceiver::class.java)
            val pending = PendingIntent.getBroadcast(this,0,intent,0)

            var finish :AlarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
            finish.setExact(AlarmManager.RTC_WAKEUP,calendar.timeInMillis,pending)

            //タイマーボタンを見えなくする
            startButton.isVisible = false
            //タイマー開始
            timer.start()
        }



        startButton.setOnClickListener {
            startButton.text = "ストップ"
        }

    }


}

class AlarmBroadcastReceiver() : BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context,"Received",Toast.LENGTH_LONG).show()
    }
}

