package com.magdy.medicatask.data.models;

import java.util.List;


public class ModelAppointments {


    /**
     * code : 100
     * message : Data fetched successfully
     * item : {"data":[{"price":"450.00","time_from":"06:30 PM","time_to":"09:30 PM","day_number":3,"dates":{"data":[{"date":"01/03/2021"},{"date":"08/03/2021"},{"date":"15/03/2021"},{"date":"22/03/2021"},{"date":"29/03/2021"},{"date":"05/04/2021"},{"date":"12/04/2021"},{"date":"19/04/2021"}]}},{"price":"450.00","time_from":"06:30 PM","time_to":"09:30 PM","day_number":5,"dates":{"data":[{"date":"03/03/2021"},{"date":"10/03/2021"},{"date":"17/03/2021"},{"date":"24/03/2021"},{"date":"31/03/2021"},{"date":"07/04/2021"},{"date":"14/04/2021"},{"date":"21/04/2021"}]}}]}
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
             * price : 450.00
             * time_from : 06:30 PM
             * time_to : 09:30 PM
             * day_number : 3
             * dates : {"data":[{"date":"01/03/2021"},{"date":"08/03/2021"},{"date":"15/03/2021"},{"date":"22/03/2021"},{"date":"29/03/2021"},{"date":"05/04/2021"},{"date":"12/04/2021"},{"date":"19/04/2021"}]}
             */

            private String price;
            private String time_from;
            private String time_to;
            private Integer day_number;
            private DatesBean dates;

            public String getPrice() {
                return price;
            }

            public String getTime_from() {
                return time_from;
            }

            public String getTime_to() {
                return time_to;
            }

            public Integer getDay_number() {
                return day_number;
            }

            public DatesBean getDates() {
                return dates;
            }

            public static class DatesBean {
                private List<DataBean2> data;

                public List<DataBean2> getData() {
                    return data;
                }

                public static class DataBean2 {
                    /**
                     * date : 01/03/2021
                     */

                    private String date;

                    public String getDate() {
                        return date;
                    }
                }
            }
        }
    }
}
