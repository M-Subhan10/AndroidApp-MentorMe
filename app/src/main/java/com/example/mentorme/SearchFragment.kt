package com.example.mentorme

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment

class SearchFragment : Fragment() {

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        // Access the SearchView
        val searchView = view.findViewById<SearchView>(R.id.searchView)
        // Remove underline of the SearchView
        val searchPlateId = searchView.context.resources
            .getIdentifier("android:id/search_plate", null, null)
        val searchPlate = searchView.findViewById<View>(searchPlateId)
        searchPlate?.setBackgroundColor(Color.TRANSPARENT)




        val recent1 = view.findViewById<LinearLayout>(R.id.recent1)
        val recent2 = view.findViewById<LinearLayout>(R.id.recent2)
        val recent3 = view.findViewById<LinearLayout>(R.id.recent3)

        val cross1 = view.findViewById<ImageView>(R.id.cross1)
        val cross2 = view.findViewById<ImageView>(R.id.cross2)
        val cross3 = view.findViewById<ImageView>(R.id.cross3)

        cross1.setOnClickListener {
            recent1.visibility = View.GONE
        }

        cross2.setOnClickListener {
            recent2.visibility = View.GONE
        }

        cross3.setOnClickListener {
            recent3.visibility = View.GONE
        }


        return view
    }
}
