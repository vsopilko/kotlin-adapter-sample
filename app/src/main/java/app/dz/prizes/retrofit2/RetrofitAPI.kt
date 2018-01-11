package app.dz.prizes.retrofit2

import app.dz.prizes.retrofit2.model.SubscriptionResponse

class RetrofitAPI(val apiService: RetrofitService) {


    /*
    fun searchUsers(location: String, language: String): io.reactivex.Observable<Result> {
        return apiService.search(query = "location:$location language:$language")
    }
    */


    fun getSubscriptions(): io.reactivex.Observable<SubscriptionResponse> {
        return apiService.subscriptions()
    }
}
