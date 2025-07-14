package com.example.mentorme

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.SearchView
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

        // Remove underline from SearchView
        val searchPlateId = searchView.context.resources.getIdentifier("android:id/search_plate", null, null)
        val searchPlate = searchView.findViewById<View>(searchPlateId)
        searchPlate?.setBackgroundColor(Color.TRANSPARENT)

        return view
    }
}
