package app.dz.prizes.retrofit2

import app.dz.prizes.model.SubscriptionList
import app.dz.prizes.retrofit2.model.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit




interface RetrofitService {

    companion object Factory {
        //  http://vo-hub.com:8888/api/prizes/v1/
        val BASE_URL = "http://vo-hub.com:8888/"
        val ENDPOINT = BASE_URL + "api/prizes/v1/"

        fun create(): RetrofitService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(ENDPOINT)
                    .build()

            return retrofit.create<RetrofitService>(RetrofitService::class.java)
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    //  http://vo-hub.com:8888/api/prizes/v1/subscriptions/
    /*
    @get:GET("subscriptions/")
    val subscriptions: Call<SubscriptionList>
    */


    /*
    @GET("subscriptions/")
    fun search(@retrofit2.http.Query("q") query: String,
               @retrofit2.http.Query("page") page: Int = 1,
               @retrofit2.http.Query("per_page") perPage: Int = 20): io.reactivex.Observable<SubscriptionList>
    */

    @GET("subscriptions/")
    fun subscriptions(): io.reactivex.Observable<SubscriptionResponse>

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // User API
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @POST("sign_in/")
    fun registration(@Body param: RegParam): Call<RegResponse>

    @POST("token/")
    fun getToken(@Body param: TokenParam): Call<TokenResponse>

    @POST("login/")
    fun login(@Body param: LoginParam): Call<LoginResponse>




    //    @GET("user/")
    //    Call<User> getUser();
    //
    //    // change profile page data
    //    // Update current user profile data
    //    // https://gist.github.com/DeV1doR/ca99bd6193dfa37856eaeb2a32596a70#api-for-change-profile-page-data
    //    // PUT | PATCH
    //    @PUT("user/")
    //    Call<User> setUser(@Body UserParams userParams);
    //
    //    @PUT("user/")
    //    @Multipart
    //    Call<User> setUserPhoto(@Part MultipartBody.Part imageFile);
    //
    //
    //    //// Content: text.video lessons,feed,needs,ads,thanks //////////////////////////////////////////////////////////
    //
    //    @GET("feeds/")
    //    Call<ContentList> getFeed(@Query("limit") int limit, @Query("offset") int offset);
    //
    //    @GET("feeds/")
    //    Call<ContentList> getContent(@Query("feed_type") int feedType,
    //                                 @Query("limit") int limit, @Query("offset") int offset);
    //
    //    @GET("feeds/{feed_id}/")
    //    Call<ContentModel> getContentById(@Path("feed_id") int feedId);
    //
    //    // Favourite lessons
    //
    //    @GET("ftexts/")
    //    Call<ContentList> getFavouriteTextLessons(@Query("limit") int limit, @Query("offset") int offset);
    //
    //    @GET("fvideos/")
    //    Call<ContentList> getFavouriteVideoLessons(@Query("limit") int limit, @Query("offset") int offset);
    //
    //    //Set lessons completed
    //    @POST("text-lessons/{id}/views/")
    //    Call<Void> setTextLessonCompleted(@Path("id") int id);
    //
    //    @POST("video-lessons/{id}/views/")
    //    Call<Void> setVideoLessonCompleted(@Path("id") int id);
    //
    //    //// Needs //////////////////////////////////////////////////////////
    //
    //    @GET("my_needs/")
    //    Call<ContentList> getUserNeeds(@Query("limit") int limit, @Query("offset") int offset);
    //
    //    @POST("need/")
    //    Call<BaseContentResponse> sendNeed(@Body HashMap<String, String> need);
    //
    //    //// Thanks //////////////////////////////////////////////////////////
    //
    //    @GET("my_thanks/")
    //    Call<ContentList> getUserThanks(@Query("limit") int limit, @Query("offset") int offset);
    //
    //    @POST("thank/")
    //    Call<BaseContentResponse> sendThank(@Body HashMap<String, String> thank);
    //
    //    //// Pictures //////////////////////////////////////////////////////////
    //
    //    @POST("picture/create/")
    //    @Multipart
    //    Call<Void> sendPicture(@Part MultipartBody.Part imageFile);
    //
    //    @POST("picture/create/")
    //    @Multipart
    //    Call<Void> sendPictureWithText(@Part MultipartBody.Part imageFile,
    //                                   @Part("text") RequestBody description);
    //
    //    //// Likes //////////////////////////////////////////////////////////
    //
    //    @POST("likes/")
    //    Call<Void> postLike(@Body HashMap<String, Integer> body);
    //
    //    @DELETE("likes/{feed_id}/")
    //    Call<Void> deleteLike(@Path("feed_id") int feedId);
    //
    //    //// Statistics //////////////////////////////////////////////////////////
    //
    //    @POST("common_user/v1/trial/login-stat/")
    //    Call<Void> updateLaunchingStatistic(@Body Map<String, String> deviceInfo);
    //
    //    //// Bonuses /////////////////////////////////////////////////////////
    //
    //    @GET("purchase/")
    //    Call<UserPurchaseList> getUserPurchases();
    //
    //    @POST("purchase/")
    //    Call<Void> sendBonus(@Body Map<String, String> body);
    //
    //    //// Partners book ////////////////////////////////////////////////////
    //    @POST("common_user/v1/publication/")
    //    Call<Void> sendComment(@Body Comment comment);
    //
    //    @GET("common_user/v1/publication/")
    //    Call<CommentsList> getPartnerComments(@Header("Accept-Language") String language, @Query("offset") int offset, @Query("limit") int limit);
    //
    //    //// Firebae //////////////////////////////////////////////////////////
    //
    //    @POST("notification/v1/device/")
    //    Call<Void> sendRegistrationToServer(@Body Map<String, String> bodyInfo);
    //
    //    @POST("notification/send/")
    //    Call<Void> sendLikeNotification(@Body LikeNotification likeNotification);

}

