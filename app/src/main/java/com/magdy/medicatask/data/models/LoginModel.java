package com.magdy.medicatask.data.models;

import java.util.List;


public class LoginModel {




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

        private Integer id;
        private String name;
        private String last_name;
        private String email;
        private String mobile;
        private Integer mobile_active;
        private String device_id;
        private Integer device_type;
        private Integer is_active;
        private Object mobile_code;
        private String authorization;
        private Integer email_active;
        private String email_code;
        private String address;
        private String image;
        private Object file;
        private String share_medical_history;
        private String social_login;
        private String invite_code;
        private Object created_by;
        private Object updated_by;
        private Object invited_by;
        private String created_at;
        private String updated_at;
        private Object deleted_at;
        private List<AddresssBean> addresss;
        private List<CardsBean> cards;

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getLast_name() {
            return last_name;
        }

        public String getEmail() {
            return email;
        }

        public String getMobile() {
            return mobile;
        }

        public Integer getMobile_active() {
            return mobile_active;
        }

        public String getDevice_id() {
            return device_id;
        }

        public Integer getDevice_type() {
            return device_type;
        }

        public Integer getIs_active() {
            return is_active;
        }

        public Object getMobile_code() {
            return mobile_code;
        }

        public String getAuthorization() {
            return authorization;
        }

        public Integer getEmail_active() {
            return email_active;
        }

        public String getEmail_code() {
            return email_code;
        }

        public String getAddress() {
            return address;
        }

        public String getImage() {
            return image;
        }

        public Object getFile() {
            return file;
        }

        public String getShare_medical_history() {
            return share_medical_history;
        }

        public String getSocial_login() {
            return social_login;
        }

        public String getInvite_code() {
            return invite_code;
        }

        public Object getCreated_by() {
            return created_by;
        }

        public Object getUpdated_by() {
            return updated_by;
        }

        public Object getInvited_by() {
            return invited_by;
        }

        public String getCreated_at() {
            return created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public Object getDeleted_at() {
            return deleted_at;
        }

        public List<AddresssBean> getAddresss() {
            return addresss;
        }

        public List<CardsBean> getCards() {
            return cards;
        }

        public static class AddresssBean {
            /**
             * id : 65
             * lat : 30.015745
             * lng : 31.282179
             * title : ندي
             * phone : null
             * address : 355 ميدان النافورة المقطم
             * governorate_id : null
             * city_id : 11
             * district_id : null
             * client_id : 166
             * created_at : 2020-04-05 05:08:42
             * updated_at : 2020-10-06 08:58:41
             */

            private Integer id;
            private String lat;
            private String lng;
            private String title;
            private Object phone;
            private String address;
            private Object governorate_id;
            private Integer city_id;
            private Object district_id;
            private Integer client_id;
            private String created_at;
            private String updated_at;

            public Integer getId() {
                return id;
            }

            public String getLat() {
                return lat;
            }

            public String getLng() {
                return lng;
            }

            public String getTitle() {
                return title;
            }

            public Object getPhone() {
                return phone;
            }

            public String getAddress() {
                return address;
            }

            public Object getGovernorate_id() {
                return governorate_id;
            }

            public Integer getCity_id() {
                return city_id;
            }

            public Object getDistrict_id() {
                return district_id;
            }

            public Integer getClient_id() {
                return client_id;
            }

            public String getCreated_at() {
                return created_at;
            }

            public String getUpdated_at() {
                return updated_at;
            }
        }


        public static class CardsBean {
            /**
             * id : 232
             * email : null
             * mobile : 01156161776
             * code : 0000391421041900
             * exp_at : 2020-04-21 02:33:50
             * company_user_key : 01156161776
             * status : pending
             * card_version : 1
             * client_id : 166
             * company_id : null
             * created_at : 2019-04-21 00:33:50
             * updated_at : 2020-02-04 13:04:22
             */

            private Integer id;
            private Object email;
            private String mobile;
            private String code;
            private String exp_at;
            private String company_user_key;
            private String status;
            private String card_version;
            private Integer client_id;
            private Object company_id;
            private String created_at;
            private String updated_at;

            public Integer getId() {
                return id;
            }

            public Object getEmail() {
                return email;
            }

            public String getMobile() {
                return mobile;
            }

            public String getCode() {
                return code;
            }

            public String getExp_at() {
                return exp_at;
            }

            public String getCompany_user_key() {
                return company_user_key;
            }

            public String getStatus() {
                return status;
            }

            public String getCard_version() {
                return card_version;
            }

            public Integer getClient_id() {
                return client_id;
            }

            public Object getCompany_id() {
                return company_id;
            }

            public String getCreated_at() {
                return created_at;
            }

            public String getUpdated_at() {
                return updated_at;
            }
        }
    }
}
