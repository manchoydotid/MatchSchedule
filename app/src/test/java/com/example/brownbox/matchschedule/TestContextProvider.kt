package com.example.brownbox.matchschedule

import com.example.brownbox.matchschedule.util.CoroutineContextProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlin.coroutines.CoroutineContext

class TestContextProvider : CoroutineContextProvider(){
    @ExperimentalCoroutinesApi
    override  val main: CoroutineContext = Dispatchers.Unconfined
}