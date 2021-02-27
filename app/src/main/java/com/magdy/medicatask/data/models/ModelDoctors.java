package com.magdy.medicatask.data.models;

import java.util.List;

public class ModelDoctors {




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


            private Integer id;
            private String doctor_id;
            private Integer branch_id;
            private String name;
            private String description;
            private String specialty_description;
            private String degree;
            private String image;
            private Integer rate;
            private String max_price;
            private String min_price;
            private Integer institution_id;
            private Integer member_id;
            private Object preBooking;

            public Integer getId() {
                return id;
            }

            public String getDoctor_id() {
                return doctor_id;
            }

            public Integer getBranch_id() {
                return branch_id;
            }

            public String getName() {
                return name;
            }

            public String getDescription() {
                return description;
            }

            public String getSpecialty_description() {
                return specialty_description;
            }

            public String getDegree() {
                return degree;
            }

            public String getImage() {
                return image;
            }

            public Integer getRate() {
                return rate;
            }

            public String getMax_price() {
                return max_price;
            }

            public String getMin_price() {
                return min_price;
            }

            public Integer getInstitution_id() {
                return institution_id;
            }

            public Integer getMember_id() {
                return member_id;
            }

            public Object getPreBooking() {
                return preBooking;
            }
        }
    }
}
