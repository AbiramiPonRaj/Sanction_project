


package com.ponsun.san.ofac.liststatus.data;
import lombok.Data;

@Data

public class ListStatusData {
    private String liststatus;


    public ListStatusData(String liststatus) {
        this.liststatus = liststatus;
    }

    public static ListStatusData newInstance(String liststatus) {
        return new ListStatusData(liststatus);
    }
}
