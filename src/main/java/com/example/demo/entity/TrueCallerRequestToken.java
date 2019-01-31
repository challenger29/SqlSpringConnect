package com.example.demo.entity;

import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name= "true_caller_authorization")
public class TrueCallerRequestToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    Integer id;

    @Column(name ="my_number")
    String myNumber;

    @Column(name ="phone_number")
    String phoneNo;

    @Column(name ="register_id")
    String registerId;

    @Column(name ="is_active")
    boolean isActive;

    @LastModifiedDate
    @Column(name ="last_used")
    Date lastBlocked;

    public TrueCallerRequestToken() {
    }

    public TrueCallerRequestToken(String myNumber, String phoneNo, String registerId, boolean isActive, Date lastBlocked) {
        this.myNumber = myNumber;
        this.phoneNo = phoneNo;
        this.registerId = registerId;
        this.isActive = isActive;
        this.lastBlocked = lastBlocked;
    }

    public String getMyNumber() {
        return myNumber;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getRegisterId() {
        return registerId;
    }

    public boolean isActive() {
        return isActive;
    }

    public Date getLastBlocked() {
        return lastBlocked;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setLastBlocked(Date lastBlocked) {
        this.lastBlocked = lastBlocked;
    }

    @Override
    public String toString() {
        return "TrueCallerRequestToken{" +
                "id=" + id +
                ", myNumber='" + myNumber + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", registerId='" + registerId + '\'' +
                ", isActive=" + isActive +
                ", last blocked=" + lastBlocked.toString() +
                '}';
    }
}
