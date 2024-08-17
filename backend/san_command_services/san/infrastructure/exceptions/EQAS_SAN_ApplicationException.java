package com.ponsun.san.infrastructure.exceptions;

public class EQAS_SAN_ApplicationException extends AbstractPlatformException{

    public EQAS_SAN_ApplicationException(String message){
        super("error.msg.generic",message);
    }


}
