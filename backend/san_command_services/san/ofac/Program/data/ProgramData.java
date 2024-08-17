
package com.ponsun.san.ofac.Program.data;
import lombok.Data;

@Data

public class ProgramData {
    private String Program;


    public ProgramData(String Program) {
        this.Program = Program;
    }

    public static ProgramData newInstance(String Program) {
        return new ProgramData(Program);
    }
}
