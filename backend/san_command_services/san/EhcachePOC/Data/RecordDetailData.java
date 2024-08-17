package com.ponsun.san.EhcachePOC.Data;
import lombok.Data;

    @Data
    public class RecordDetailData {
    private Integer id;
    private String heading;
    private String val;
    public RecordDetailData(Integer id, String heading, String val) {
    this.id = id;
    this.heading = heading;
    this.val = val;
    }
    public static RecordDetailData newInstance(Integer id, String heading, String val){
    return new RecordDetailData(id,heading,val);
    }
}
