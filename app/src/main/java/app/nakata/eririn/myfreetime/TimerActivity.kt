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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        val dataStore: SharedPreferences = getSharedPreferences("DataStore", Context.MODE_PRIVATE)

        //残り時間のセット
        val timertime = dataStore.getInt("Input",18)
        val second : Int = timertime*60*60
        //最初のタイマー時間表示
        val hour : Int = second/3600
        val minutes : Int = second/60%60
        val calculatesecond : Int = second%60
        secondText.text = ("$hour:$minutes:$calculatesecond")

        //タイマーをセット
        val timer : CountDownTimer = object : CountDownTimer(second.toLong()*10000,1000){
            override fun onFinish() {
                imageView.setImageResource(R.drawable.flower5)
                secondText.text = "タイムオーバー"
            }
            //カウントダウンごとの処理
            override fun onTick(millisUntilFinished: Long){

                var remainTime :Int = second - 1
                dataStore.getLong("remain",18)


                var hour : Int = remainTime/3600
                var minutes : Int = remainTime/60%60
                var calculatesecond : Int = remainTime%60

                secondText.text = ("$hour:$minutes:$calculatesecond")
            }

        }
        //タイマー開始
        startButton.setOnClickListener {
            timer.start()
            //タイマーボタンを見えなくする
            startButton.isVisible = false
            stopButton.isVisible = true
        }

        stopButton.setOnClickListener {
            timer.cancel()
            startButton.isVisible = true
            stopButton.isVisible = false

            //dataStore.getLong("remain",18)

            //保存する
            val carculateTime : Long = timertime.toLong()

            val editor = dataStore.edit()
            editor.putLong("remain",carculateTime)

            editor.apply()
        }
        //ページ遷移
        val timelist = Intent(this,TimeList::class.java)

        returnButton.setOnClickListener {
            startActivity(timelist)
        }


        //アラーム機能
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar.add(Calendar.HOUR,1)

        val intent = Intent(this,AlarmBroadcastReceiver::class.java)
        val pending = PendingIntent.getBroadcast(this,0,intent,0)

        var finish :AlarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        finish.setExact(AlarmManager.RTC_WAKEUP,calendar.timeInMillis,pending)




    }


}

class AlarmBroadcastReceiver() : BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context,"Received",Toast.LENGTH_LONG).show()
    }
}

