package com.example.brownbox.matchschedule.view.matchSearch

import com.example.brownbox.matchschedule.api.ApiRepository
import com.example.brownbox.matchschedule.api.TheSportDBApi
import com.example.brownbox.matchschedule.model.MatchSearchItemResponse
import com.example.brownbox.matchschedule.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MatchSearchPresenter(private val view: MatchSearchView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson, private val context:
                           CoroutineContextProvider = CoroutineContextProvider()){

    fun getEventSearch(keyword: String?) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getEventSearch(keyword)).await(),
                MatchSearchItemResponse::class.java
            )

            try{
                view.showEventList(data.event)
            }catch (e:Exception){
                view.emptyState()
            }
            view.hideLoading()
        }
    }
}