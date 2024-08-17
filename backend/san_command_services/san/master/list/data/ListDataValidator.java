package com.ponsun.san.master.list.data;




import com.ponsun.san.infrastructure.exceptions.EQAS_SAN_ApplicationException;

import com.ponsun.san.master.list.request.CreateListRequest;
import com.ponsun.san.master.list.request.UpdateListRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ListDataValidator {
    public void validateSaveListData(final CreateListRequest request){
        if(request.getText() == null || request.getText().equals("")){
            throw new EQAS_SAN_ApplicationException("Text parameter required");
        }
    }
    public void validateUpdateListData(final UpdateListRequest request){
        if(request.getText() == null || request.getText().equals("")){
            throw new EQAS_SAN_ApplicationException("Text parameter required");
        }
    }
}
