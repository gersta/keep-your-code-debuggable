package com.twodigits.debuggable.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@Builder
@NoArgsConstructor // seems to be required for jackson to write model to JSON file
@AllArgsConstructor // seems to be required for the @Builder if @NoArgsConstructor is also set
public class CommunityMember {

    @Id @GeneratedValue
    private String id;
    private String firstname;
    private String lastname;
    private String entryDate;
    private String fieldOfExpertise;

}
