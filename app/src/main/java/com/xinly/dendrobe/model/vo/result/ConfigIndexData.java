package com.xinly.dendrobe.model.vo.result;

import com.xinly.dendrobe.model.vo.bean.UserBean;

import java.util.ArrayList;

/**
 * Created by zm on 2019-07-02.
 */
public class ConfigIndexData {

    private ArrayList<SlideBean> slide;
    private ArrayList<MenuBean> menu;
    private ArrayList<NoticeBean> notice;
    private UserBean member;

    public UserBean getMember() {
        return member;
    }

    public void setMember(UserBean member) {
        this.member = member;
    }

    public ArrayList<SlideBean> getSlide() {
        return slide;
    }

    public void setSlide(ArrayList<SlideBean> slide) {
        this.slide = slide;
    }

    public ArrayList<MenuBean> getMenu() {
        return menu;
    }

    public void setMenu(ArrayList<MenuBean> menu) {
        this.menu = menu;
    }

    public ArrayList<NoticeBean> getNotice() {
        return notice;
    }

    public void setNotice(ArrayList<NoticeBean> notice) {
        this.notice = notice;
    }

    public static class SlideBean {

        private String title;
        private String type;
        private String tag;
        private String image;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }

    public static class MenuBean {
        private String name;
        private String title;
        private String tag;
        private String icon;
        private boolean status;
        private String alert;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public String getAlert() {
            return alert;
        }

        public void setAlert(String alert) {
            this.alert = alert;
        }
    }

    public static class NoticeBean {
        /**
         * title : 这是一条正在测试的公告，内容可能会很长，需要做滚动播放还是什么的，你们看着办吧，是的，就是这样，叼的一批
         * id : 1
         */

        private String title;
        private int id;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
