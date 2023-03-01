package com.example.gettingstarted.fragmentLifecycle

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gettingstarted.R

class CatFragment : Fragment() {

    override fun onStart() {
        super.onStart()
        Log.d(this.toString(), "onStart for cat fragment called..")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(this.toString(), "Cat Fragment onCreate...")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(this.toString(), "onCreateView for cat fragment called..")
        return inflater.inflate(R.layout.fragment_cat, container, false)
    }

    override fun onStop() {
        super.onStop()
        Log.d(this.toString(), "onStop for cat fragment called..")
    }

    override fun onPause() {
        super.onPause()
        Log.d(this.toString(), "onPause for cat fragment called..")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(this.toString(), "onDestroyView for cat fragment called..")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(this.toString(), "onDestroy for cat fragment called..")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(this.toString(), "onDetach for cat fragment called..")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(this.toString(), "onAttach for cat fragment called..")
    }

    override fun onResume() {
        super.onResume()
        Log.d(this.toString(), "onResume for cat fragment called..")
    }

}