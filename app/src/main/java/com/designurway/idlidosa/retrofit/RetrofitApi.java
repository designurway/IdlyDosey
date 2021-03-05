package com.designurway.idlidosa.retrofit;


import com.designurway.idlidosa.model.AddressModel;
import com.designurway.idlidosa.model.DashComboDataModel;
import com.designurway.idlidosa.model.ErrorMessageModel;
import com.designurway.idlidosa.model.GetNotificationResponse;
import com.designurway.idlidosa.model.GetOrderHistoryResponseModel;
import com.designurway.idlidosa.model.GetTotalAmountModel;
import com.designurway.idlidosa.model.LoginModel;
import com.designurway.idlidosa.model.MenuDataModel;
import com.designurway.idlidosa.model.MenuDetailsDataModel;
import com.designurway.idlidosa.model.NotificationListModel;
import com.designurway.idlidosa.model.OrderStatusModel;
import com.designurway.idlidosa.model.PaymentModel;
import com.designurway.idlidosa.model.PaymentSucessfulModel;
import com.designurway.idlidosa.model.ProfileDataModel;
import com.designurway.idlidosa.model.ProfileModel;
import com.designurway.idlidosa.model.ReceivedComboDataModel;
import com.designurway.idlidosa.model.RegisterDataModel;
import com.designurway.idlidosa.model.StatusAndMessageModel;
import com.designurway.idlidosa.model.StatusMessageModel;
import com.designurway.idlidosa.model.StatusOTPModel;
import com.designurway.idlidosa.model.TrackOrderListModel;
import com.designurway.idlidosa.model.TrackOrderModel;
import com.designurway.idlidosa.model.ViewCartModelResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitApi {

    @FormUrlEncoded
    @POST("varify_customer_phone_otp.php")
    Call<LoginModel> verifyPhoneAndOTP(
            @Field("phone") String phone,
            @Field("otp") String otp,
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("customer_registration.php")
    Call<RegisterDataModel> customerRegister(
            @Field("email") String email,
            @Field("phone") String phone,
            @Field("password") String password,
            @Field("referral_code") String refCode
    );

    @GET("varify_customer_phone.php")
    Call<ErrorMessageModel> verifyCustomerPhone(
            @Query("phone") String phone);

    @GET("get_customer_otp.php")
    Call<StatusOTPModel> getOtpForPhone(
            @Query("phone") String phone);


    @FormUrlEncoded
    @POST("set_customer_password.php")
    Call<ErrorMessageModel> setCustomerForgotPwd(
            @Field("phone") String phone,
            @Field("password") String password
    );

    @GET("get_dashboard.php")
    Call<MenuDataModel> setFeatured();

    @GET("get_product_detail.php")
    Call<MenuDetailsDataModel> setMenuDetails(
            @Query("product_id") String product_id);

    @FormUrlEncoded
    @POST("post_order_quantity.php")
    Call<ErrorMessageModel> postCartQuantity(
            @Field("customer_id") String customer_id,
            @Field("product_id") String product_id,
            @Field("order_id") String order_id,
            @Field("amount") String amount,
            @Field("quantity") String quantity
    );

    @FormUrlEncoded
    @POST("post_customer_cart_item.php")
    Call<StatusAndMessageModel> AddtoCart(
            @Field("customer_id") String customer_id,
            @Field("product_id") String product_id,
            @Field("amount") String amount,
            @Field("quantity") String quantity
    );


    @GET("get_customer_order_history.php")
    Call<GetOrderHistoryResponseModel> GetOrderHistory(
            @Query("customer_id") String customer_id);

    @GET("get_menu_detail.php")
    Call<MenuDetailsDataModel> GetProduct(
            @Query("menu_id") String menu_id);

    @GET("track_order_status.php")
    Call<TrackOrderModel> GetOrderStatus(
            @Query("customer_id") String customer_id,
            @Query("order_id") String order_id);

    @FormUrlEncoded
    @POST("profile_post.php")
    Call<ProfileModel> postProfile(
            @Field("name") String name,
            @Field("email") String email,
            @Field("phone") String phone,
            @Field("home_address") String address
    );

    @FormUrlEncoded
    @POST("customer_profile_image_post.php")
    Call<ErrorMessageModel> postImage(

            @Field("phone") String phone,
            @Field("logo_name") String name,
            @Field("profile_image") String image
    );

    @GET("get_profile_details.php")
    Call<ProfileDataModel> getProfileDetails(
            @Query("phone") String phone
    );


    @GET("customer_change_password.php")
    Call<StatusAndMessageModel> changePwd(
            @Query("phone") String phone,
            @Query("password") String password,
            @Query("new_pwd") String newPwd,
            @Query("conf_pwd") String confPwd
    );

    @FormUrlEncoded
    @POST("post_customer_address.php")
    Call<StatusAndMessageModel> postCustomerAddress(

            @Field("phone") String phone,
            @Field("building") String building,
            @Field("street") String street,
            @Field("address_type") String addressType
    );

    @GET("get_cart_item.php")
    Call<ViewCartModelResponse> GetCartItem(
            @Query("customer_id") String customer_id);


    @FormUrlEncoded
    @POST("confirm_order.php")
    Call<ErrorMessageModel> ConformOrder(
            @Field("customer_id") String customer_id,
            @Field("total_amount") String total_amount,
            @Field("order_id") String order_id

    );

    @FormUrlEncoded
    @POST("post_customer_cart_item.php")
    Call<StatusAndMessageModel> AddtoCart(
            @Field("customer_id") String customer_id,
            @Field("product_id") String product_id,
            @Field("amount") String amount,
            @Field("order_id") String order_id,
            @Field("quantity") String quantity
    );

    @GET("get_dashboard_combo.php")
    Call<DashComboDataModel> getComboPacks();

    @GET("check_referral_code.php")
    Call<StatusAndMessageModel> updateReferralCode(
            @Query("referral_code") String referralCode,
            @Query("referred_to") String referredTo
    );

    @GET("get_combo_product.php")
    Call<MenuDetailsDataModel> setComboDetails(
            @Query("combo_id") String comboId
    );


    @GET("index.php")
    Call<GetNotificationResponse> getNotification(
            @Query("title") String title,
            @Query("body") String body
    );

    @GET("delete_cart_item.php")
    Call<ErrorMessageModel> deleteCartItem(
            @Query("customer_id") String customerId,
            @Query("order_id") String orderId,
            @Query("product_id") String productId
    );

    @GET("get_bulk_order_details.php")
    Call<MenuDataModel> GetDashboard(
            @Query("product_type") String title,
            @Query("customer_id") String customer_id
    );

    @FormUrlEncoded
    @POST("post_medicine_order.php")
    Call<StatusAndMessageModel> PostMedicineOrder(
            @Field("order_id") String order_id,
            @Field("customer_id") String customer_id,
            @Field("quantity") String quantity,
            @Field("medicine_name") String medicine_name
    );

    @FormUrlEncoded
    @POST("update_combo_details.php")
    Call<ErrorMessageModel> updateComboWonDetails(
            @Field("order_id") String order_id,
            @Field("customer_id") String customer_id,
            @Field("total_amount") String totalAmount

    );

    @FormUrlEncoded
    @POST("post_medicine_image.php")
    Call<StatusAndMessageModel> PostMedicineImage(
            @Field("customer_id") String customer_id,
            @Field("order_id") String order_id,
            @Field("image_name") String image_name,
            @Field("medicine_image") String medicine_image
    );

    @GET("combo_view.php")
    Call<ReceivedComboDataModel> getComboWonDetails(
            @Query("referral_code") String referralCode
    );

    @FormUrlEncoded
    @POST("post_won_combo.php")
    Call<ErrorMessageModel> postComboWonDetails(
            @Field("customer_id") String customer_id,
            @Field("order_id") String order_id,
            @Field("delivery_address") String deliveryAddress,
            @Field("product_id") String product_id,
            @Field("combo_id") String comboId
    );

    @GET("get_customer_address.php")
    Call<AddressModel> getAddress(
            @Query("phone") String phone
    );


    @GET("track_order.php")
    Call<TrackOrderListModel> getOnGoingOrder(
            @Query("customer_id") String customer_id
    );

    @GET("view_notifications.php")
    Call<NotificationListModel> getNotificationList(
            @Query("customer_id") String customer_id
    );

    @FormUrlEncoded
    @POST("post_transaction_details.php")
    Call<PaymentSucessfulModel> postPaymentDetails(
            @Field("customer_id") String customer_id,
            @Field("bank_name") String bank_name,
            @Field("bank_transaction_id") String bank_transaction_id,
            @Field("currency_type") String currency_type,
            @Field("gateway") String gateway,
            @Field("mid") String mid,
            @Field("order_id") String order_id,
            @Field("payment_mode") String payment_mode,
            @Field("resp_code") String resp_code,
            @Field("resp_message") String resp_message,
            @Field("status") String status,
            @Field("transaction_amount") String transaction_amount,
            @Field("transaction_date") String transaction_date,
            @Field("transaction_id") String transaction_id
    );

    @FormUrlEncoded
    @POST("order_status.php")
    Call<OrderStatusModel> postOrderDetails(
            @Field("customer_id") String customer_id,
            @Field("order_id") String order_id,
            @Field("total_amount") String total_amount,
            @Field("delivery_address") String delivery_address
    );

    @GET("get_total_cart_item_amount.php")
    Call<GetTotalAmountModel> getAmount(
            @Query("customer_id") String customer_id
    );

    @FormUrlEncoded
    @POST("edit_address.php")
    Call<StatusAndMessageModel> EditAddress(

            @Field("customer_id") String customer_id,
            @Field("building") String building,
            @Field("street") String street,
            @Field("address_type") String address_type

    );

    @GET("fetch_customer_order_amount.php")
    Call<PaymentModel> payment(
            @Query("customer_id") String customer_id,
            @Query("order_id") String order_id
    );

    @GET("fetch_image_for_payment.php")
    Call<StatusMessageModel> image(
            @Query("phone") String phone
    );


    @GET("fetch_customer_notifications.php")
    Call<NotificationListModel> getNotificationforCustomer(
            @Query("customer_id") String customer_id
    );

    @GET("count_unread_notifications.php")
    Call<StatusAndMessageModel> getNotificationCount(
            @Query("customer_id") String customer_id
    );

    @FormUrlEncoded
    @POST("update_unread_notification.php")
    Call<StatusAndMessageModel> updateUnreadRead(
            @Field("order_id") String order_id

    );

     @FormUrlEncoded
    @POST("delete_notification.php")
    Call<StatusAndMessageModel> deleteNotification(
            @Field("order_id") String order_id,
            @Field("customer_id") String customer_id

    );



}
