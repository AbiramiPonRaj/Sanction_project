package com.ponsun.san.adminconfiguration.adminuserrights.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

@Data
@JsonDeserialize
public class AdminUserRightsDTO {
        private String id;
        private String uid;
        private String modId;
        private String modDetId;
        private String entUid;
        private String dt;
        private Boolean valid;


        public AdminUserRightsDTO(final String id,final String uid, final Integer modId, final Integer modDetId, final Integer entUid, final String dt, final Boolean valid) {
                this.id =id;
                this.uid = uid;
                this.modId = String.valueOf(modId);
                this.modDetId = String.valueOf(modDetId);
                this.entUid = String.valueOf(entUid);
                this.dt = dt;
                this.valid = Boolean.valueOf(String.valueOf(valid));
        }

        public static AdminUserRightsDTO newInstance(final String id,final String uid, final Integer modId, final Integer modDetId, final Integer entUid, final String dt, final Boolean valid) {
                return new AdminUserRightsDTO(id, uid, modId, modDetId, entUid, dt, valid);
        }
}
//        public AdminUserRightsDTO(String uid, Integer modId, Integer modDetId, Integer entUid, String dt, Boolean valid) {
//
//                this.uid = uid;
//                this.modId = String.valueOf(modId);
//                this.modDetId = String.valueOf(modDetId);
//                this.entUid = String.valueOf(entUid);
//                this.dt = dt;
//                this.valid = String.valueOf(valid);
//        }
//
//        public static AdminUserRightsDTO newInstance(String uid, Integer modId, Integer modDetId, Integer entUid, String dt, Boolean valid) {
//                return new AdminUserRightsDTO(uid, modId, modDetId, entUid, dt, valid);
//        }
//}
//
//
