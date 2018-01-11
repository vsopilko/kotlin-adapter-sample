package app.dz.prizes.retrofit2


class RetrofitProvider {

    fun api(): RetrofitAPI {
        return RetrofitAPI(RetrofitService.Factory.create())
    }
}