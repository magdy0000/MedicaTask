package com.magdy.medicatask.data;

import java.io.IOException;
import java.net.SocketTimeoutException;

import retrofit2.HttpException;

public class NetworkState  {

    private Status status ;
    private String massage ;
    private Object data  ;
  public enum  Status {
        RUNNING, FAILED , SUCCESS
    }

    public NetworkState (Status status , String massage  ,Object data ){
      this.data = data ;
      this.status = status ;
      this.massage = massage ;


    }
    public NetworkState (Status status , String massage   ){

        this.status = status ;
        this.massage = massage ;
    }


   public static NetworkState loading (){

      return new NetworkState(Status.RUNNING , "loading");

   }

   public static NetworkState loaded(Object data){

      return new NetworkState( Status.SUCCESS, "success", data);

   }
    public static NetworkState error(String massage){

      return new  NetworkState(Status.FAILED , massage);
    }
    public static NetworkState error(Throwable throwable){

       if (throwable instanceof IOException){
           return new NetworkState(Status.FAILED , "No Connection ");
       }else if (throwable instanceof SocketTimeoutException){

           return new   NetworkState( Status.FAILED, "Bad Connection");
       }else if (throwable instanceof HttpException){
           return new NetworkState( Status.FAILED, "Server Error");
       }else {
        return new  NetworkState( Status.FAILED, "Failed");
       }

    }

    public Status getStatus() {
        return status;
    }

    public String getMassage() {
        return massage;
    }

    public Object getData() {
        return data;
    }
}

