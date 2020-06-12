package app.nakata.eririn.myfreetime

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_timer.*
import java.util.*

class TimerActivity : AppCompatActivity() {
    //データストアから値を読み出す
    //val
    //val intValue = Input.getInt

    //タイマー
   // val second = intValue
    //val timer : CountDownTimer = object : CountDownTimer(intValue.toLong()*10, intValue.toLong()){
        //タイマー残り時間マックス
        //fun max(){
            //if (){
                //imageView.setImageResource(R.drawable.flower)
           // }

       // }
        //タイマー残り時間ハーフ
        //fun half(){
            //if(){
               // imageView.setImageResource(R.drawable.flower3)
           // }

        //}
        //タイマー終了時
        //override fun onFinish() {
            //imageView.setImageResource(R.drawable.flower5)
           // secondText.text = "タイムオーバー"
        //}

       // override fun onTick(millisUntilFinished: Long) {
           // TODO("Not yet implemented")
       // }
  //  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        val dataStore: SharedPreferences = getSharedPreferences("DataStore", Context.MODE_PRIVATE)
        val timertime: Int = dataStore.getInt("Input",18)

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
        }

        //残り時間の表示
        //secondText.text = second.toString()

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

