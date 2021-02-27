package com.magdy.medicatask.data.models;


import java.util.List;


public class SpecialiseModel {


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
            private String title;
            private String image;
            private String app_icon;

            public Integer getId() {
                return id;
            }

            public String getTitle() {
                return title;
            }

            public String getImage() {
                return image;
            }

            public String getApp_icon() {
                return app_icon;
            }
        }
    }
}
