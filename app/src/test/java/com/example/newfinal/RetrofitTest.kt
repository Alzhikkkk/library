package com.example.newfinal

import com.example.newfinal.api.RetrofitInstance
import com.example.newfinal.utils.Constant
import com.example.newfinal.utils.Constant.Companion.BASE_URL
import org.junit.Test
import retrofit2.Retrofit

class RetrofitClientTest {
    @Test
    fun testRetrofitInstance() {
        //Get an instance of Retrofit
        val instance: Retrofit = RetrofitInstance.retrofit
        //Assert that, Retrofit's base url matches to our BASE_URL
        assert(instance.baseUrl().url().toString() == Constant.BASE_URL)
    }
}