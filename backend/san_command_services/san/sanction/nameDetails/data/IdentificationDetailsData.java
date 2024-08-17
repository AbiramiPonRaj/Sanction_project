package com.ponsun.san.sanction.nameDetails.data;

import lombok.Data;

@Data
public class IdentificationDetailsData {
    private String Iden_leba_numtitle;
    private String Iden_number;
    private String Iden_country;
    private String Iden_Leba_publication_date;
    private Integer Entity_logical_id_Iden;

    public IdentificationDetailsData(String Iden_leba_numtitle, String Iden_number, String Iden_country, String Iden_Leba_publication_date, Integer Entity_logical_id_Iden) {
        this.Iden_leba_numtitle = Iden_leba_numtitle;
        this.Iden_number = Iden_number;
        this.Iden_country = Iden_country;
        this.Iden_Leba_publication_date = Iden_Leba_publication_date;
        this.Entity_logical_id_Iden = Entity_logical_id_Iden;
    }

    public static IdentificationDetailsData newInstance(String Iden_leba_numtitle, String Iden_number, String Iden_country, String Iden_Leba_publication_date, Integer Entity_logical_id_Iden) {
        return new IdentificationDetailsData(Iden_leba_numtitle, Iden_number, Iden_country, Iden_Leba_publication_date, Entity_logical_id_Iden);
    }
}
