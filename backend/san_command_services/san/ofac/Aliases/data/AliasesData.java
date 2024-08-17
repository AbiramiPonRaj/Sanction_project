package com.ponsun.san.ofac.Aliases.data;
import lombok.Data;

@Data
public class AliasesData {
    private String AliasesType;
    private String Category;
    private String AliasesName;


    public AliasesData(String AliasesType, String Category, String AliasesName ) {
        this.AliasesType = AliasesType;
        this.Category = Category;
        this.AliasesName = AliasesName;

    }
    public static AliasesData newInstance(String AliasesType, String Category, String AliasesName){
        return new AliasesData(AliasesType,Category,AliasesName);
    }
}
