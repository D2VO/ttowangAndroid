package com.app.ttowang.ttowang.Main.Home.stamp.ChangeCoupon;

/**
 * Created by srpgs2 on 2016-08-25.
 */
public class ChangeCouponItem {

    private String CouponName;
    private String CouponCode;
    private String BusinessId;
    private String StampNeed;


    public void setCouponName(String couponName) {
        CouponName = couponName ;
    }
    public void setCouponCode(String couponCode) {
        CouponCode = couponCode;
    }
    public void setBusinessId(String businessId) {
        BusinessId = businessId;
    }
    public void setStampNeed(String stampNeed) {
        StampNeed = stampNeed;
    }



    public String getCouponName() {
        return this.CouponName;
    }
    public String getCouponCode() {
        return this.CouponCode ;
    }
    public String getBusinessId() {
        return this.BusinessId ;
    }
    public String getStampNeed() {
        return this.StampNeed ;
    }

}
