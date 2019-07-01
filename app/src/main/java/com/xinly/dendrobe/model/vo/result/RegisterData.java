package com.xinly.dendrobe.model.vo.result;

/**
 * Created by zm on 2019-07-01.
 */
public class RegisterData {

    private MemberBean member;
    private String token;

    public MemberBean getMember() {
        return member;
    }

    public void setMember(MemberBean member) {
        this.member = member;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class MemberBean {
        /**
         * code : 102554
         */

        private int code;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }
}
