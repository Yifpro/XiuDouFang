package com.example.administrator.xiudoufang.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class SubjectBean {


    /**
     * status : 1
     * messagestr : null
     * accounttypes : [{"accountid":"1","account_name":"营业收入","show_name":"营业收入(收款)","moren":"1","direction":"1"},{"accountid":"2","account_name":"营业外收入","show_name":"营业外收入(收款)","moren":"0","direction":"1"},{"accountid":"3","account_name":"营业外支出","show_name":"营业外支出(付款)","moren":"0","direction":"-1"},{"accountid":"4","account_name":"营业支出","show_name":"营业支出(付款)","moren":"0","direction":"-1"},{"accountid":"5","account_name":"应付账款","show_name":"应付账款","moren":"0","direction":"-1"},{"accountid":"6","account_name":"应收账款","show_name":"应收账款","moren":"0","direction":"1"},{"accountid":"7","account_name":"管理费用","show_name":"管理费用支出","moren":"0","direction":"0"},{"accountid":"8","account_name":"银行存款","show_name":"银行存款","moren":"0","direction":"0"},{"accountid":"9","account_name":"财务费用","show_name":"财务费用","moren":"0","direction":"0"},{"accountid":"67","account_name":"采购支出","show_name":"采购支出","moren":"0","direction":"-1"},{"accountid":"68","account_name":"坏账","show_name":"坏账","moren":"0","direction":"0"},{"accountid":"10","account_name":"期初余额","show_name":"期初余额","moren":"0","direction":"0"},{"accountid":"11","account_name":"测试","show_name":"测试2","moren":"0","direction":"1"},{"accountid":"79","account_name":"销售费用","show_name":"销售费用","moren":"0","direction":"0"}]
     */

    private String status;
    private Object messagestr;
    private List<AccounttypesBean> accounttypes;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getMessagestr() {
        return messagestr;
    }

    public void setMessagestr(Object messagestr) {
        this.messagestr = messagestr;
    }

    public List<AccounttypesBean> getAccounttypes() {
        return accounttypes;
    }

    public void setAccounttypes(List<AccounttypesBean> accounttypes) {
        this.accounttypes = accounttypes;
    }

    public static class AccounttypesBean implements Parcelable {
        /**
         * accountid : 1
         * account_name : 营业收入
         * show_name : 营业收入(收款)
         * moren : 1
         * direction : 1
         */

        private String accountid;
        private String account_name;
        private String show_name;
        private String moren;
        private String direction;



        public String getAccountid() {
            return accountid;
        }

        public void setAccountid(String accountid) {
            this.accountid = accountid;
        }

        public String getAccount_name() {
            return account_name;
        }

        public void setAccount_name(String account_name) {
            this.account_name = account_name;
        }

        public String getShow_name() {
            return show_name;
        }

        public void setShow_name(String show_name) {
            this.show_name = show_name;
        }

        public String getMoren() {
            return moren;
        }

        public void setMoren(String moren) {
            this.moren = moren;
        }

        public String getDirection() {
            return direction;
        }

        public void setDirection(String direction) {
            this.direction = direction;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.accountid);
            dest.writeString(this.account_name);
            dest.writeString(this.show_name);
            dest.writeString(this.moren);
            dest.writeString(this.direction);
        }

        public AccounttypesBean() {
        }

        protected AccounttypesBean(Parcel in) {
            this.accountid = in.readString();
            this.account_name = in.readString();
            this.show_name = in.readString();
            this.moren = in.readString();
            this.direction = in.readString();
        }

        public static final Creator<AccounttypesBean> CREATOR = new Creator<AccounttypesBean>() {
            @Override
            public AccounttypesBean createFromParcel(Parcel source) {
                return new AccounttypesBean(source);
            }

            @Override
            public AccounttypesBean[] newArray(int size) {
                return new AccounttypesBean[size];
            }
        };
    }
}
