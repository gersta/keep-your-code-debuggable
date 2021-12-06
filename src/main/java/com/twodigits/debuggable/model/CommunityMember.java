package com.twodigits.debuggable.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class CommunityMember {

    @Id @GeneratedValue
    private String id;
    private String firstname;
    private String lastname;
    private String entryDate;
    private String fieldOfExpertise;

}
