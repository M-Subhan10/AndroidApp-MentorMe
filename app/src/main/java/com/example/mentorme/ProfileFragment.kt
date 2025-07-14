package com.example.mentorme

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mentorme.databinding.FragmentProfileBinding
import com.github.dhaval2404.imagepicker.ImagePicker

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val REQUEST_COVER_IMAGE = 101
    private val REQUEST_PROFILE_IMAGE = 102

    private lateinit var sharedPref: SharedPreferences

    private lateinit var dataList: ArrayList<DataClass_Card>
    private lateinit var nameList: Array<String>
    private lateinit var roleList: Array<String>
    private lateinit var priceList: Array<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        sharedPref = requireContext().getSharedPreferences("ProfilePrefs", Context.MODE_PRIVATE)

        loadSavedImages()
        setupRecyclerView()

        binding.coverFab.setOnClickListener {
            ImagePicker.with(this)
                .crop()
                .compress(1024)
                .maxResultSize(1080, 1080)
                .start(REQUEST_COVER_IMAGE)
        }

        binding.profileFab.setOnClickListener {
            ImagePicker.with(this)
                .crop()
                .compress(1024)
                .maxResultSize(1080, 1080)
                .start(REQUEST_PROFILE_IMAGE)
        }

        return binding.root
    }

    private fun loadSavedImages() {
        val coverUriStr = sharedPref.getString("cover_image_uri", null)
        val profileUriStr = sharedPref.getString("profile_image_uri", null)

        if (coverUriStr != null) {
            binding.coverImg.setImageURI(Uri.parse(coverUriStr))
        }
        if (profileUriStr != null) {
            binding.profileImg.setImageURI(Uri.parse(profileUriStr))
        }
    }

    private fun setupRecyclerView() {
        nameList = arrayOf("M.Subhan", "Ali Ahson", "Abdullah", "Faiq")
        roleList = arrayOf("Android Dev", "AI Eng", "Frontend Dev", "DevOps Eng")
        priceList = arrayOf("$1500", "$1200", "$1800", "$1500")

        dataList = arrayListOf()

        for (i in nameList.indices) {
            val data = DataClass_Card(nameList[i], roleList[i], priceList[i])
            dataList.add(data)
        }

        val adapter = Card_Adapter(dataList)

        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerview.setHasFixedSize(true)
        binding.recyclerview.adapter = adapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && data != null) {
            val uri: Uri = data.data!!

            when (requestCode) {
                REQUEST_COVER_IMAGE -> {
                    binding.coverImg.setImageURI(uri)
                    sharedPref.edit().putString("cover_image_uri", uri.toString()).apply()
                }

                REQUEST_PROFILE_IMAGE -> {
                    binding.profileImg.setImageURI(uri)
                    sharedPref.edit().putString("profile_image_uri", uri.toString()).apply()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
