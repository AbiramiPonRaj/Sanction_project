package com.ponsun.san.searchLifcycle.insert.services;

import com.ponsun.san.common.entity.Status;
import com.ponsun.san.infrastructure.exceptions.EQAS_SAN_ApplicationException;
import com.ponsun.san.infrastructure.utils.Response;
import com.ponsun.san.searchLifcycle.PendingCase.data.PendingCaseData;
import com.ponsun.san.searchLifcycle.hitcase.request.CreateHitCaseRequest;
import com.ponsun.san.searchLifcycle.hitcase.services.HitCaseWritePlatformService;
import com.ponsun.san.searchLifcycle.hitcaselifecycle.request.CreateHitCaseLifeCycleRequest;
import com.ponsun.san.searchLifcycle.hitcaselifecycle.services.HitCaseLifeCycleWritePlatformService;
import com.ponsun.san.searchLifcycle.hitrecord.domain.HitRecordRepository;
import com.ponsun.san.searchLifcycle.hitrecordlifecycle.domain.HitRecordLifecycle;
import com.ponsun.san.searchLifcycle.hitrecordlifecycle.domain.HitRecordlifecycleRepository;
import com.ponsun.san.searchLifcycle.hitrecordlifecycle.request.CreateHitRecordLifecycleRequest;
import com.ponsun.san.searchLifcycle.hitrecordlifecycle.services.HitRecordLifecycleWritePlatformService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class InsertWritePlatformServiceImpl implements InsertWritePlatformService{
    private final HitCaseWritePlatformService hitCaseWritePlatformService;
    private final HitRecordlifecycleRepository hitRecordlifecycleRepository;
    private final HitRecordRepository hitRecordRepository;

    private  final HitRecordLifecycleWritePlatformService hitRecordLifecycleWritePlatformService;
    private final HitCaseLifeCycleWritePlatformService hitCaseLifeCycleWritePlatformService;


    //L1 and L2 CLS  ==> ie. createHitdataLifecycle
    //public Response createHitdataLifecycle(CreateHitdataLifecycle createHitdataLifecycle)
    @Override
    @Transactional
    //L1 and L2 ==>Esc  ie HitCase and HitDataLifecycle
    public Response CreateHitCase_HitrecordLifeCycle(CreateHitCaseRequest createHitCaseRequest){
        try {
            Response response = this.hitCaseWritePlatformService.CreateHitcase(createHitCaseRequest);
            Integer ids = (Integer) response.getId();

            updateValidFlag(createHitCaseRequest.getSearchId(),createHitCaseRequest.getCriminalId(),createHitCaseRequest.getHitId());
            //update is valid in hitdata
            //System.out.println("AA  : "+createHitCaseRequest.getHitId());
            //updateHitDataStatus(createHitCaseRequest.getHitId());
            //HitData hitData = hitDataReadPlatformService.fetchAllHitId(createHitCaseRequest.getCriminalId(),createHitCaseRequest.getSearchId());
            HitRecordLifecycle createHitDataDet = HitRecordLifecycle.converter(ids,createHitCaseRequest);
            createHitDataDet.setStatus(Status.ACTIVE);
            createHitDataDet.setValid(true);
            createHitDataDet.setHitdataId(createHitCaseRequest.getHitId());
            //this.hitDataWritePlatformService.blockHitData(createHitCaseRequest.getHitId());
            //final Response response1 = this.hitdataLifecycleWritePlatformService.l2_createHitdataLifecycle(createHitDataDet);
            this.hitRecordlifecycleRepository.saveAndFlush(createHitDataDet);
            return response;
        }catch (DataIntegrityViolationException e){
            throw new EQAS_SAN_ApplicationException(e.getMessage());
        }
    }
    @Override
    @Transactional
    //L3 and L4 Case LifeCycle (CLS,ESC,RFI)
    public Response insertPendingCaseData(PendingCaseData pendingCaseData) {
        ModelMapper modelMapper = new ModelMapper();
        // Convert the source DTO to the target DTO
        CreateHitRecordLifecycleRequest hitdataLifecycle = modelMapper.map(pendingCaseData, CreateHitRecordLifecycleRequest.class);
        CreateHitCaseLifeCycleRequest hitCaseLifeCycleRequest = modelMapper.map(pendingCaseData, CreateHitCaseLifeCycleRequest.class);
//        System.out.println("A"+hitdataLifecycle);
//        System.out.println("B"+hitCaseLifeCycleRequest);
        updateValidFlag(pendingCaseData.getSearchId(),pendingCaseData.getCriminalId(),pendingCaseData.getHitId());
        //update is valid in hitdata
        hitRecordLifecycleWritePlatformService.createHitdataLifecycle(hitdataLifecycle);
        Response response= this.hitCaseLifeCycleWritePlatformService.createHitCaseLifeCycle(hitCaseLifeCycleRequest);
        return response;
    }
    @Override
    @Transactional
    public void updateValidFlag(Integer searchId, Integer criminalId,Integer hitId) {
        boolean valid = false;
        this.hitRecordlifecycleRepository.updateValidBySearchIdAndCriminalId(searchId, criminalId,valid);
        this.hitRecordRepository.updateValidityById(valid,hitId);
    }
    @Override
    @Transactional
    public void updateHitDataStatus(Integer hitId) {
        boolean valid = false;
        this.hitRecordRepository.updateValidityById(valid,hitId);
    }
}
