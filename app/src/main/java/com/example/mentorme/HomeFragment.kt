package com.example.mentorme

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerView2: RecyclerView
    private lateinit var recyclerView3: RecyclerView
    private lateinit var dataList: ArrayList<DataClass_Card>
    private lateinit var nameList: Array<String>
    private lateinit var roleList: Array<String>
    private lateinit var priceList: Array<String>

    private lateinit var auth: FirebaseAuth
    private lateinit var usernameText: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Firebase init
        auth = FirebaseAuth.getInstance()

        val notificationBtn = view.findViewById<ImageView>(R.id.notification_btn)
        notificationBtn.setOnClickListener {
            startActivity(Intent(requireContext(), Notifications::class.java))
        }


        // Greeting name TextView
        usernameText = view.findViewById(R.id.user_name)
        loadUserName()

        // RecyclerViews setup
        recyclerView = view.findViewById(R.id.recyclerview)
        recyclerView2 = view.findViewById(R.id.recyclerview2)
        recyclerView3 = view.findViewById(R.id.recyclerview3)

        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView2.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView3.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        recyclerView.setHasFixedSize(true)
        recyclerView2.setHasFixedSize(true)
        recyclerView3.setHasFixedSize(true)

        dataList = arrayListOf()

        // Sample data
        nameList = arrayOf("M.Subhan", "Ali Ahson", "Abdullah", "Faiq", "Ali Hamza", "Shuja", "Haadi", "Siddique")
        roleList = arrayOf(
            "Android Dev @ Blah", "AI Eng @ Quest", "Frontend Dev @ G-8",
            "DevOps Eng @ I-10", "Java Dev @ Vlomatica", "AI Eng @ Fiverr",
            "Cloud Eng @ Remote", "DevOps Eng @ MTBC"
        )
        priceList = arrayOf(
            "$1500/Session", "$1200/Session", "$1800/Session", "$1500/Session",
            "$1200/Session", "$1800/Session", "$1500/Session", "$1200/Session"
        )

        getData()

        return view
    }

    private fun getData() {
        for (i in nameList.indices) {
            val dataClass = DataClass_Card(nameList[i], roleList[i], priceList[i])
            dataList.add(dataClass)
        }

        val adapter = Card_Adapter(dataList)
        recyclerView.adapter = adapter
        recyclerView2.adapter = adapter
        recyclerView3.adapter = adapter
    }

    private fun loadUserName() {
        val user: FirebaseUser? = auth.currentUser
        val nameFromFirebase = user?.displayName

        usernameText.text = if (!nameFromFirebase.isNullOrEmpty()) {
            "$nameFromFirebase"
        } else {
            "User"
        }
    }
}
