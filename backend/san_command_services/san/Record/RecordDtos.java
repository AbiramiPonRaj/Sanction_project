package com.ponsun.san.Record;
import com.ponsun.san.searchLifcycle.search.data.SearchDto;
import lombok.Data;

@Data
public class RecordDtos {
    private SearchDto searchData;
    public RecordDtos(SearchDto searchdto) {
        this.searchData = searchdto;
    }

    public RecordDtos() {

    }

    public static RecordDtos newInstance(SearchDto searchdto){
        return new RecordDtos(searchdto);
    }
}

