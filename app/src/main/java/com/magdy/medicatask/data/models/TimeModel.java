package com.magdy.medicatask.data.models;

import java.util.List;


public class TimeModel {


    /**
     * code : 100
     * message : Data fetched successfully
     * item : {"data":[{"time":"06:30 PM","date":"28-12-2020","available":1,"day_name":"Wednesday","price":"450.00"},{"time":"07:00 PM","date":"28-12-2020","available":1,"day_name":"Wednesday","price":"450.00"},{"time":"07:30 PM","date":"28-12-2020","available":1,"day_name":"Wednesday","price":"450.00"},{"time":"08:00 PM","date":"28-12-2020","available":1,"day_name":"Wednesday","price":"450.00"},{"time":"08:30 PM","date":"28-12-2020","available":1,"day_name":"Wednesday","price":"450.00"},{"time":"09:00 PM","date":"28-12-2020","available":1,"day_name":"Wednesday","price":"450.00"}]}
     */

    private Integer code;
    private String message;
    private ItemBean item;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public ItemBean getItem() {
        return item;
    }

    public static class ItemBean {
        private List<DataBean> data;

        public List<DataBean> getData() {
            return data;
        }

        public static class DataBean {
            /**
             * time : 06:30 PM
             * date : 28-12-2020
             * available : 1
             * day_name : Wednesday
             * price : 450.00
             */

            private String time;
            private String date;
            private Integer available;
            private String day_name;
            private String price;

            public String getTime() {
                return time;
            }

            public String getDate() {
                return date;
            }

            public Integer getAvailable() {
                return available;
            }

            public String getDay_name() {
                return day_name;
            }

            public String getPrice() {
                return price;
            }
        }
    }
}
