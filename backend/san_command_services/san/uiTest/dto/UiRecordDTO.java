package com.ponsun.san.uiTest.dto;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UiRecordDTO {
    private String name;
    private String jarow;
    private String fuzzy_Wuzzytoken_sort_ratio;
    private String set;
    private String sort;
    private String cosine_Similarity;
    private String double_Metaphone;
    private String soundex_val;
    private String double_Metaphone_jw;
    private String n_Gram;
    public UiRecordDTO(String name,String jarow, String fuzzy_Wuzzytoken_sort_ratio, String set, String sort, String cosine_Similarity, String double_Metaphone, String soundex_val, String double_Metaphone_jw, String n_Gram) {
        this.name=name;
        this.jarow = jarow;
        this.fuzzy_Wuzzytoken_sort_ratio = fuzzy_Wuzzytoken_sort_ratio;
        this.set = set;
        this.sort = sort;
        this.cosine_Similarity = cosine_Similarity;
        this.double_Metaphone = double_Metaphone;
        this.soundex_val = soundex_val;
        this.double_Metaphone_jw = double_Metaphone_jw;
        this.n_Gram = n_Gram;
    }
    public static UiRecordDTO newInstance(String name,String jarow, String fuzzy_Wuzzytoken_sort_ratio, String set, String sort, String cosine_Similarity, String double_Metaphone, String soundex_val, String double_Metaphone_jw, String n_Gram) {
        return new UiRecordDTO(name, jarow, fuzzy_Wuzzytoken_sort_ratio, set, sort, cosine_Similarity, double_Metaphone, soundex_val,double_Metaphone_jw,n_Gram);
    }
}
