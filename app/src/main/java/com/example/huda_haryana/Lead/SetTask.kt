package com.example.huda_haryana.Lead

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.huda_haryana.R
import com.example.huda_haryana.databinding.ActivitySetTaskBinding
import com.google.firebase.database.FirebaseDatabase
import com.kunzisoft.switchdatetime.SwitchDateTimeDialogFragment
import java.text.SimpleDateFormat
import java.util.*

var d: Long? = null
lateinit var desctxt: String
lateinit var notificationManager: NotificationManager
lateinit var pendingIntent: PendingIntent
lateinit var notificationChannel: NotificationChannel
lateinit var builder: Notification.Builder
private val channelID = "1"
private val desc = "Set Reminder"

class SetTask : AppCompatActivity() {
    lateinit var binding: ActivitySetTaskBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_set_task)
        val datetime = SwitchDateTimeDialogFragment.newInstance("Set Reminder", "SET", "CANCEL")
        datetime.startAtCalendarView()
        datetime.setHighlightAMPMSelection(false)
        datetime.set24HoursMode(false)
        val id=intent.getStringExtra("id")
        val name=intent.getStringExtra("name")
        datetime.simpleDateMonthAndDayFormat = SimpleDateFormat("MMM dd", java.util.Locale.getDefault())
        binding.selectTimeSetTask.setOnClickListener {
            datetime.show(supportFragmentManager, "Set")
        }
        binding.settaskToolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val mref = FirebaseDatabase.getInstance().getReference("leads").child(id!!).child("Alarm")
        val mref2=FirebaseDatabase.getInstance().getReference("Tasks")
        datetime.setOnButtonClickListener(object : SwitchDateTimeDialogFragment.OnButtonClickListener {
            override fun onPositiveButtonClick(date: Date?) {
                Toast.makeText(this@SetTask, date.toString(), Toast.LENGTH_SHORT).show()
                d = date?.time
                val dateFormat=SimpleDateFormat("hh:mm a dd-MMM")
                val time=dateFormat.format(Date(d!!))
                binding.dateTime.setText(time)
                val intent = Intent(this@SetTask, AddTask::class.java)
                pendingIntent = PendingIntent.getActivity(this@SetTask, 0, intent, 0)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    notificationChannel = NotificationChannel(channelID, desc, NotificationManager.IMPORTANCE_HIGH)
                    notificationChannel.enableLights(true)
                    notificationChannel.lightColor = Color.GREEN
                    notificationChannel.enableVibration(true)
                    notificationManager.createNotificationChannel(notificationChannel)
                    builder = Notification.Builder(this@SetTask, channelID)
                            .setContentTitle("Reminder")
                            .setSmallIcon(R.drawable.lead_icon)
                            .setContentIntent(pendingIntent)
                } else {
                    builder = Notification.Builder(this@SetTask)
                            .setContentTitle("Reminder")
                            .setSmallIcon(R.drawable.lead_profile)
                            .setContentIntent(pendingIntent)
                            .setLargeIcon(BitmapFactory.decodeResource(this@SetTask.resources,R.drawable.lead_profile))
                }

            }

            override fun onNegativeButtonClick(date: Date?) {

            }

        })
        binding.setalarmSettask.setOnClickListener {
            desctxt = binding.descriptionSettask.text.toString()
            builder.setContentText(desctxt)
            val data = AlarmData(desctxt, d.toString(),name!!)
            val key2=mref2.push().key
            val key=mref.push().key
            mref2.child(key2!!).setValue(data)
            mref.child(key!!).setValue(data)
            setalarm(d)
        }
    }

    fun setalarm(d: Long?) {
        val alarmanager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AddAlarm::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
        alarmanager.setExact(AlarmManager.RTC_WAKEUP, d!!, pendingIntent)
        Toast.makeText(this, "Alarm Set", Toast.LENGTH_SHORT).show()
    }
}
