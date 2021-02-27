package com.magdy.medicatask.network;

import com.magdy.medicatask.data.models.BranchesModel;
import com.magdy.medicatask.data.models.ModelAppointments;
import com.magdy.medicatask.data.models.ModelDoctors;
import com.magdy.medicatask.data.models.ReservationModel;
import com.magdy.medicatask.data.models.SpecialiseModel;
import com.magdy.medicatask.data.models.LoginModel;
import com.magdy.medicatask.data.models.TimeModel;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiCalls {


    @FormUrlEncoded
    @POST("auth-client/login")
    Single<LoginModel> login(@Field("mobile") String phone, @Field("password") String password);

    @GET("services")
    Single<BranchesModel> getBranches(@Query("lat") String lat,
                                      @Query("lng") String lng,
                                      @Query("type") int type,
                                      @Query("page") int page
    );

    @GET("institutions/specialties")
    Single<SpecialiseModel> getSpecialises(@Query("branch_id") int idBranch,
                                           @Query("id") int idInstitution);

    @GET("institutions/doctors")
    Single<ModelDoctors> getDoctors(@Query("branch_id") int branchId,
                                    @Query("specialty_id") int specialtId,
                                    @Query("id") int id);

    @GET("institutions/appointments")
    Single<ModelAppointments> getAppointment(@Query("branch_id") int idBranch,
                                             @Query("doctor_id") int doctorId,
                                             @Query("type") String type);

    @GET("institutions/appointments/time")
    Single<TimeModel> getTime(@Query("branch_id") int idBranch,
                              @Query("doctor_id") int doctorId,
                              @Query("type") String type,
                              @Query("id")  int institutionId ,
                              @Query("day_number") int dayNumber,
                              @Query("date") String date);

    @FormUrlEncoded
    @POST("client/reservation")
    Single<ReservationModel> makeReservation (@Field("institution_branch_id") int branchId,
                                              @Field("institution_id") int institutionId,
                                              @Field("date") String date,
                                              @Field("doctor_id") int doctorId,
                                              @Field("timefrom") String time,
                                              @Field("type") String type);



}
