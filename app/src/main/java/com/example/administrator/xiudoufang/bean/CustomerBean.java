package com.example.administrator.xiudoufang.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class CustomerBean {

    /**
     * status : 1
     * customerlist : [{"c_id":"13549","customerno":"A000046","customername":"刘涛","quancheng":"刘涛","dengji":"五级","dengji_value":"5","dianid":"45","debt":"-2011.00","yue_amt":"0.00","country":"0","quyu":"","quyuno":"","xinyongedu":"0.00","chaoguoxinyongedushishifouyunxukaidan":"1","lianxiren":[{"lianxiren":"18505295858"},{"lianxiren":"刘涛"}],"qq":[{"qq":"18505295858"}],"weixinhao":[{"weixinhao":"18505295858"}],"dianhua":[{"dianhua":"18505295858"}],"telephone":[{"telephone":"18505295858"}],"fahuodizhi":[{"fahuodizhi":"闵行区老北翟路4855号"}],"shouhuodizhi":[{"shouhuodizhi":"18505295858"}],"freight":[{"freight":"18505295858"}]}]
     */

    private String status;
    private List<CustomerlistBean> customerlist;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<CustomerlistBean> getCustomerlist() {
        return customerlist;
    }

    public void setCustomerlist(List<CustomerlistBean> customerlist) {
        this.customerlist = customerlist;
    }

    public static class CustomerlistBean implements Parcelable {
        /**
         * c_id : 13549
         * customerno : A000046
         * customername : 刘涛
         * quancheng : 刘涛
         * dengji : 五级
         * dengji_value : 5
         * dianid : 45
         * debt : -2011.00
         * yue_amt : 0.00
         * country : 0
         * quyu : 
         * quyuno : 
         * xinyongedu : 0.00
         * chaoguoxinyongedushishifouyunxukaidan : 1
         * lianxiren : [{"lianxiren":"18505295858"},{"lianxiren":"刘涛"}]
         * qq : [{"qq":"18505295858"}]
         * weixinhao : [{"weixinhao":"18505295858"}]
         * dianhua : [{"dianhua":"18505295858"}]
         * telephone : [{"telephone":"18505295858"}]
         * fahuodizhi : [{"fahuodizhi":"闵行区老北翟路4855号"}]
         * shouhuodizhi : [{"shouhuodizhi":"18505295858"}]
         * freight : [{"freight":"18505295858"}]
         */

        private String c_id;
        private String customerno;
        private String customername;
        private String quancheng;
        private String dengji;
        private String dengji_value;
        private String dianid;
        private String debt;
        private String yue_amt;
        private String country;
        private String quyu;
        private String quyuno;
        private String xinyongedu;
        private String chaoguoxinyongedushishifouyunxukaidan;
        private List<LianxirenBean> lianxiren;
        private List<QqBean> qq;
        private List<WeixinhaoBean> weixinhao;
        private List<DianhuaBean> dianhua;
        private List<TelephoneBean> telephone;
        private List<FahuodizhiBean> fahuodizhi;
        private List<ShouhuodizhiBean> shouhuodizhi;
        private List<FreightBean> freight;

        public String getC_id() {
            return c_id;
        }

        public void setC_id(String c_id) {
            this.c_id = c_id;
        }

        public String getCustomerno() {
            return customerno;
        }

        public void setCustomerno(String customerno) {
            this.customerno = customerno;
        }

        public String getCustomername() {
            return customername;
        }

        public void setCustomername(String customername) {
            this.customername = customername;
        }

        public String getQuancheng() {
            return quancheng;
        }

        public void setQuancheng(String quancheng) {
            this.quancheng = quancheng;
        }

        public String getDengji() {
            return dengji;
        }

        public void setDengji(String dengji) {
            this.dengji = dengji;
        }

        public String getDengji_value() {
            return dengji_value;
        }

        public void setDengji_value(String dengji_value) {
            this.dengji_value = dengji_value;
        }

        public String getDianid() {
            return dianid;
        }

        public void setDianid(String dianid) {
            this.dianid = dianid;
        }

        public String getDebt() {
            return debt;
        }

        public void setDebt(String debt) {
            this.debt = debt;
        }

        public String getYue_amt() {
            return yue_amt;
        }

        public void setYue_amt(String yue_amt) {
            this.yue_amt = yue_amt;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getQuyu() {
            return quyu;
        }

        public void setQuyu(String quyu) {
            this.quyu = quyu;
        }

        public String getQuyuno() {
            return quyuno;
        }

        public void setQuyuno(String quyuno) {
            this.quyuno = quyuno;
        }

        public String getXinyongedu() {
            return xinyongedu;
        }

        public void setXinyongedu(String xinyongedu) {
            this.xinyongedu = xinyongedu;
        }

        public String getChaoguoxinyongedushishifouyunxukaidan() {
            return chaoguoxinyongedushishifouyunxukaidan;
        }

        public void setChaoguoxinyongedushishifouyunxukaidan(String chaoguoxinyongedushishifouyunxukaidan) {
            this.chaoguoxinyongedushishifouyunxukaidan = chaoguoxinyongedushishifouyunxukaidan;
        }

        public List<LianxirenBean> getLianxiren() {
            return lianxiren;
        }

        public void setLianxiren(List<LianxirenBean> lianxiren) {
            this.lianxiren = lianxiren;
        }

        public List<QqBean> getQq() {
            return qq;
        }

        public void setQq(List<QqBean> qq) {
            this.qq = qq;
        }

        public List<WeixinhaoBean> getWeixinhao() {
            return weixinhao;
        }

        public void setWeixinhao(List<WeixinhaoBean> weixinhao) {
            this.weixinhao = weixinhao;
        }

        public List<DianhuaBean> getDianhua() {
            return dianhua;
        }

        public void setDianhua(List<DianhuaBean> dianhua) {
            this.dianhua = dianhua;
        }

        public List<TelephoneBean> getTelephone() {
            return telephone;
        }

        public void setTelephone(List<TelephoneBean> telephone) {
            this.telephone = telephone;
        }

        public List<FahuodizhiBean> getFahuodizhi() {
            return fahuodizhi;
        }

        public void setFahuodizhi(List<FahuodizhiBean> fahuodizhi) {
            this.fahuodizhi = fahuodizhi;
        }

        public List<ShouhuodizhiBean> getShouhuodizhi() {
            return shouhuodizhi;
        }

        public void setShouhuodizhi(List<ShouhuodizhiBean> shouhuodizhi) {
            this.shouhuodizhi = shouhuodizhi;
        }

        public List<FreightBean> getFreight() {
            return freight;
        }

        public void setFreight(List<FreightBean> freight) {
            this.freight = freight;
        }

        public static class LianxirenBean implements Parcelable {
            /**
             * lianxiren : 18505295858
             */


            private String lianxiren;

            public String getLianxiren() {
                return lianxiren;
            }

            public void setLianxiren(String lianxiren) {
                this.lianxiren = lianxiren;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.lianxiren);
            }

            public LianxirenBean() {
            }

            protected LianxirenBean(Parcel in) {
                this.lianxiren = in.readString();
            }

            public static final Creator<LianxirenBean> CREATOR = new Creator<LianxirenBean>() {
                @Override
                public LianxirenBean createFromParcel(Parcel source) {
                    return new LianxirenBean(source);
                }

                @Override
                public LianxirenBean[] newArray(int size) {
                    return new LianxirenBean[size];
                }
            };
        }

        public static class QqBean implements Parcelable {
            /**
             * qq : 18505295858
             */

            private String qq;

            public String getQq() {
                return qq;
            }

            public void setQq(String qq) {
                this.qq = qq;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.qq);
            }

            public QqBean() {
            }

            protected QqBean(Parcel in) {
                this.qq = in.readString();
            }

            public static final Creator<QqBean> CREATOR = new Creator<QqBean>() {
                @Override
                public QqBean createFromParcel(Parcel source) {
                    return new QqBean(source);
                }

                @Override
                public QqBean[] newArray(int size) {
                    return new QqBean[size];
                }
            };
        }

        public static class WeixinhaoBean implements Parcelable {
            /**
             * weixinhao : 18505295858
             */

            private String weixinhao;

            public String getWeixinhao() {
                return weixinhao;
            }

            public void setWeixinhao(String weixinhao) {
                this.weixinhao = weixinhao;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.weixinhao);
            }

            public WeixinhaoBean() {
            }

            protected WeixinhaoBean(Parcel in) {
                this.weixinhao = in.readString();
            }

            public static final Creator<WeixinhaoBean> CREATOR = new Creator<WeixinhaoBean>() {
                @Override
                public WeixinhaoBean createFromParcel(Parcel source) {
                    return new WeixinhaoBean(source);
                }

                @Override
                public WeixinhaoBean[] newArray(int size) {
                    return new WeixinhaoBean[size];
                }
            };
        }

        public static class DianhuaBean implements Parcelable {
            /**
             * dianhua : 18505295858
             */

            private String dianhua;

            public String getDianhua() {
                return dianhua;
            }

            public void setDianhua(String dianhua) {
                this.dianhua = dianhua;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.dianhua);
            }

            public DianhuaBean() {
            }

            protected DianhuaBean(Parcel in) {
                this.dianhua = in.readString();
            }

            public static final Creator<DianhuaBean> CREATOR = new Creator<DianhuaBean>() {
                @Override
                public DianhuaBean createFromParcel(Parcel source) {
                    return new DianhuaBean(source);
                }

                @Override
                public DianhuaBean[] newArray(int size) {
                    return new DianhuaBean[size];
                }
            };
        }

        public static class TelephoneBean implements Parcelable {
            /**
             * telephone : 18505295858
             */

            private String telephone;

            public String getTelephone() {
                return telephone;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.telephone);
            }

            public TelephoneBean() {
            }

            protected TelephoneBean(Parcel in) {
                this.telephone = in.readString();
            }

            public static final Creator<TelephoneBean> CREATOR = new Creator<TelephoneBean>() {
                @Override
                public TelephoneBean createFromParcel(Parcel source) {
                    return new TelephoneBean(source);
                }

                @Override
                public TelephoneBean[] newArray(int size) {
                    return new TelephoneBean[size];
                }
            };
        }

        public static class FahuodizhiBean implements Parcelable {
            /**
             * fahuodizhi : 闵行区老北翟路4855号
             */

            private String fahuodizhi;

            public String getFahuodizhi() {
                return fahuodizhi;
            }

            public void setFahuodizhi(String fahuodizhi) {
                this.fahuodizhi = fahuodizhi;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.fahuodizhi);
            }

            public FahuodizhiBean() {
            }

            protected FahuodizhiBean(Parcel in) {
                this.fahuodizhi = in.readString();
            }

            public static final Creator<FahuodizhiBean> CREATOR = new Creator<FahuodizhiBean>() {
                @Override
                public FahuodizhiBean createFromParcel(Parcel source) {
                    return new FahuodizhiBean(source);
                }

                @Override
                public FahuodizhiBean[] newArray(int size) {
                    return new FahuodizhiBean[size];
                }
            };
        }

        public static class ShouhuodizhiBean implements Parcelable {
            /**
             * shouhuodizhi : 18505295858
             */

            private String shouhuodizhi;

            public String getShouhuodizhi() {
                return shouhuodizhi;
            }

            public void setShouhuodizhi(String shouhuodizhi) {
                this.shouhuodizhi = shouhuodizhi;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.shouhuodizhi);
            }

            public ShouhuodizhiBean() {
            }

            protected ShouhuodizhiBean(Parcel in) {
                this.shouhuodizhi = in.readString();
            }

            public static final Creator<ShouhuodizhiBean> CREATOR = new Creator<ShouhuodizhiBean>() {
                @Override
                public ShouhuodizhiBean createFromParcel(Parcel source) {
                    return new ShouhuodizhiBean(source);
                }

                @Override
                public ShouhuodizhiBean[] newArray(int size) {
                    return new ShouhuodizhiBean[size];
                }
            };
        }

        public static class FreightBean implements Parcelable {
            /**
             * freight : 18505295858
             */

            private String freight;

            public String getFreight() {
                return freight;
            }

            public void setFreight(String freight) {
                this.freight = freight;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.freight);
            }

            public FreightBean() {
            }

            protected FreightBean(Parcel in) {
                this.freight = in.readString();
            }

            public static final Creator<FreightBean> CREATOR = new Creator<FreightBean>() {
                @Override
                public FreightBean createFromParcel(Parcel source) {
                    return new FreightBean(source);
                }

                @Override
                public FreightBean[] newArray(int size) {
                    return new FreightBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.c_id);
            dest.writeString(this.customerno);
            dest.writeString(this.customername);
            dest.writeString(this.quancheng);
            dest.writeString(this.dengji);
            dest.writeString(this.dengji_value);
            dest.writeString(this.dianid);
            dest.writeString(this.debt);
            dest.writeString(this.yue_amt);
            dest.writeString(this.country);
            dest.writeString(this.quyu);
            dest.writeString(this.quyuno);
            dest.writeString(this.xinyongedu);
            dest.writeString(this.chaoguoxinyongedushishifouyunxukaidan);
            dest.writeList(this.lianxiren);
            dest.writeList(this.qq);
            dest.writeList(this.weixinhao);
            dest.writeList(this.dianhua);
            dest.writeList(this.telephone);
            dest.writeList(this.fahuodizhi);
            dest.writeList(this.shouhuodizhi);
            dest.writeList(this.freight);
        }

        public CustomerlistBean() {
        }

        protected CustomerlistBean(Parcel in) {
            this.c_id = in.readString();
            this.customerno = in.readString();
            this.customername = in.readString();
            this.quancheng = in.readString();
            this.dengji = in.readString();
            this.dengji_value = in.readString();
            this.dianid = in.readString();
            this.debt = in.readString();
            this.yue_amt = in.readString();
            this.country = in.readString();
            this.quyu = in.readString();
            this.quyuno = in.readString();
            this.xinyongedu = in.readString();
            this.chaoguoxinyongedushishifouyunxukaidan = in.readString();
            this.lianxiren = new ArrayList<LianxirenBean>();
            in.readList(this.lianxiren, LianxirenBean.class.getClassLoader());
            this.qq = new ArrayList<QqBean>();
            in.readList(this.qq, QqBean.class.getClassLoader());
            this.weixinhao = new ArrayList<WeixinhaoBean>();
            in.readList(this.weixinhao, WeixinhaoBean.class.getClassLoader());
            this.dianhua = new ArrayList<DianhuaBean>();
            in.readList(this.dianhua, DianhuaBean.class.getClassLoader());
            this.telephone = new ArrayList<TelephoneBean>();
            in.readList(this.telephone, TelephoneBean.class.getClassLoader());
            this.fahuodizhi = new ArrayList<FahuodizhiBean>();
            in.readList(this.fahuodizhi, FahuodizhiBean.class.getClassLoader());
            this.shouhuodizhi = new ArrayList<ShouhuodizhiBean>();
            in.readList(this.shouhuodizhi, ShouhuodizhiBean.class.getClassLoader());
            this.freight = new ArrayList<FreightBean>();
            in.readList(this.freight, FreightBean.class.getClassLoader());
        }

        public static final Creator<CustomerlistBean> CREATOR = new Creator<CustomerlistBean>() {
            @Override
            public CustomerlistBean createFromParcel(Parcel source) {
                return new CustomerlistBean(source);
            }

            @Override
            public CustomerlistBean[] newArray(int size) {
                return new CustomerlistBean[size];
            }
        };
    }
}
