package com.example.huda_haryana.fragments_bottom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.huda_haryana.Lead.AlarmAdapter
import com.example.huda_haryana.Lead.AlarmData
import com.example.huda_haryana.R
import com.example.huda_haryana.databinding.FragmentTasksBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.*

class TaskFragment : Fragment() {
    lateinit var binding: FragmentTasksBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_tasks,container,false)
        binding.taskFragRecyler.layoutManager=LinearLayoutManager(context)
        val mref=FirebaseDatabase.getInstance().getReference("Tasks")
        mref.addValueEventListener(object :ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){
                    val list= mutableListOf<AlarmData>()
                    for(i in p0.children){
                        val data=i.getValue(AlarmData::class.java)
                        list.add(data!!)
                    }
                    list.reverse()
                    val list2= mutableListOf<AlarmData>()
                    val curr=Date().time
                    for(i in list){
                        if(i.date.toLong()>curr){
                            list2.add(i)
                        }
                    }
                    binding.noTaskTxt.visibility=View.GONE
                    binding.taskFragRecyler.visibility=View.VISIBLE
                    binding.taskFragRecyler.adapter=AlarmAdapter(list2,curr)
                }
            }

        })
        return binding.root
    }

}
