package org.example.mscompte.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor
@NoArgsConstructor
public class FollowingRequest {
    private String idCompteFollowing;
    private boolean following;
}
