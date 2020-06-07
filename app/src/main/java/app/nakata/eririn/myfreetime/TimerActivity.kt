package app.nakata.eririn.myfreetime

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_timer.*
import java.util.*

class TimerActivity : AppCompatActivity() {

    //タイマー
    val second = 10
    val timer :CountDownTimer = object : CountDownTimer(10000,1000){
        override fun onFinish() {
            imageView.setImageResource(R.drawable.flower5)
            secondText.text = "タイムオーバー"
        }

        override fun onTick(millisUntilFinished: Long) {
            TODO("Not yet implemented")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        val timelist = Intent(this,TimeList::class.java)

        returnButton.setOnClickListener {
            startActivity(timelist)
        }

        timerButton.setOnClickListener{
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = System.currentTimeMillis()
            calendar.add(Calendar.HOUR,1)

            val intent = Intent(this,AlarmBroadcastReceiver::class.java)
            val pending = PendingIntent.getBroadcast(this,0,intent,0)

            var finish :AlarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
            finish.setExact(AlarmManager.RTC_WAKEUP,calendar.timeInMillis,pending)
        }

        //残り時間の表示
        secondText.text = second.toString()

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

