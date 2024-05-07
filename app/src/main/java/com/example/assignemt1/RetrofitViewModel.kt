package com.example.assignemt1

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignemt1.data.SearchResponse
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Response


class RetrofitViewModel: ViewModel() {

    private val repository = ItemsRepository()
    val retrofitResponse: MutableState <SearchResponse> =
        mutableStateOf(SearchResponse())
    fun getResponse(keyword:String) {
        viewModelScope.launch  {
            try {
                val responseReturned = repository.getResponse(keyword)
                retrofitResponse.value = responseReturned

            } catch (e: Exception) {
                Log.i("Error ", "Response failed")
            }
        }
    }
}