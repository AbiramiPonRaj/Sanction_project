package com.ponsun.san.ofac.Count.service;

import com.ponsun.san.dto.RecordDTO;
import com.ponsun.san.ofac.Count.data.SanctionDetailData;
import com.ponsun.san.searchLifcycle.hitrecord.domain.HitRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecordReadServiceImpl implements RecordReadService{
    private final SanctionDetailReadService sanctionDetailReadService;
    private String nationality;
    private String Citizenship_Country;
    private String futureAddress;
    private String EntityType;
    private String passport;
    private String program;
    public void setFutureAsync(Future<String> futureStr, Consumer<String> callback) {
        CompletableFuture.supplyAsync(() -> {
            try {
                return futureStr.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return null;
            }
        }).thenAccept(callback).exceptionally(ex -> {
            ex.printStackTrace();
            // Handle the error appropriately
            return null;
        });
    }

    public void updateRecordDTO(RecordDetails recordDetails, Integer id, RecordDTO target) throws ExecutionException, InterruptedException {
//        System.out.println("AA :"+recordDetails.getValue(id));
        CompletableFuture<HashMap<String, String>> mapRetFuture = recordDetails.getValue(id);

//        CompletableFuture<HashMap<String, String>> mapRetFuture = recordDetails.getValue(id);
        HashMap<String, String> mapRet = mapRetFuture.get();  // This will block until the Future completes

        // Now you can retrieve a value from mapRet
        String nationality          = "";
        String Citizenship_Country  = "";
        String futureAddress        = "";
        String EntityType           = "";
        String passport             = "";
        String program              = "";
        if (mapRet.containsKey("Nationality Country"))
            nationality = mapRet.get("Nationality Country");
        if (mapRet.containsKey("Citizenship Country"))
            Citizenship_Country = mapRet.get("Citizenship Country");
        if (mapRet.containsKey("futureAddress"))
            futureAddress = mapRet.get("futureAddress");
        if (mapRet.containsKey("EntityType"))
            EntityType = mapRet.get("EntityType");
        if (mapRet.containsKey("Identifications"))
            passport = mapRet.get("Identifications");
        if (mapRet.containsKey("program"))
            program = mapRet.get("program");

        //List<RecordDetailData> recode= ofacSearch.getDataByKeyId(id);

        setStringAsync(nationality,target::setNationality);
        setStringAsync(Citizenship_Country,target::setCitizenship);
        setStringAsync(futureAddress,target::setAddress);
        setStringAsync(EntityType,target::setEntityType);
        setStringAsync(passport,target::setPassport);
        setStringAsync(program,target::setProgram);


//        setFutureAsync(passport, recordDTO::setPassport);
    }

/*
    public List<RecordDTO> updateRecordDTO(RecordDetails recordDetails, List<HitRecord> source, List<RecordDTO> targetRecordDTO) throws ExecutionException, InterruptedException {
        try {

            List<CompletableFuture<RecordDTO>> futures = source.stream()
                    .map(hitSource -> {
                        Integer hitId=  hitSource.getId();
                        Integer id = hitSource.getCriminalId();
                        Integer type = hitSource.getFileType();
                        System.out.println("File type: " + type); // Debug statement

                        switch (type) {
                            case 1:
                                // Retrieve the future for the map
                                CompletableFuture<HashMap<String, String>> mapFuture = recordDetails.getValue(id);

                                return mapFuture.thenApply(mapRet -> {
                                    RecordDTO recordDTO = new RecordDTO();

                                    // Extract values from map
                                    String nationality = mapRet.getOrDefault("Nationality Country", "");
                                    String Citizenship_Country = mapRet.getOrDefault("Citizenship Country", "");
                                    String futureAddress = mapRet.getOrDefault("futureAddress", "");
                                    String EntityType = mapRet.getOrDefault("EntityType", "");
                                    String passport = mapRet.getOrDefault("Identifications", "");
                                    String program = mapRet.getOrDefault("program", "");

                                    // Set values asynchronously
                                    recordDTO.setHitId(hitId);
                                    setStringAsync(nationality, recordDTO::setNationality);
                                    setStringAsync(Citizenship_Country, recordDTO::setCitizenship);
                                    setStringAsync(futureAddress, recordDTO::setAddress);
                                    setStringAsync(EntityType, recordDTO::setEntityType);
                                    setStringAsync(passport, recordDTO::setPassport);
                                    setStringAsync(program, recordDTO::setProgram);

                                    return recordDTO;
                                });
//                            case 2:
//                                //return handleSanctionData(sanctionDetailReadService.fetchEuSanctionData(id));
//                            System.out.println("2:"+handleSanctionData(sanctionDetailReadService.fetchEuSanctionData(id)));
////                                break;
//                            case 3:
//                                //return handleSanctionData(sanctionDetailReadService.fetchUkSanctionData(id));
//                            System.out.println("3 : "+handleSanctionData(sanctionDetailReadService.fetchUkSanctionData(id)));
////                                break;
//                            case 4:
//                                //return handleSanctionData(sanctionDetailReadService.fetchUnSanctionData(id));
//                            System.out.println("4 : "+handleSanctionData(sanctionDetailReadService.fetchUnSanctionData(id)));
////                                break;
////                            default:
////                                return throw new IllegalArgumentException("Unknown type: " + type);
                        }

                        ///
                        return null;
                    })
                    .collect(Collectors.toList());
//
            // Wait for all futures to complete and collect results
            List<RecordDTO> results = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                    .thenApply(v -> futures.stream()
                            .map(CompletableFuture::join)
                            .collect(Collectors.toList())
                    ).get();

            // Add results to target list
            targetRecordDTO.addAll(results);
            //return targetRecordDTO;
            System.out.println("targetRecordDTO : "+targetRecordDTO);
        }   catch (InterruptedException | ExecutionException e) {
        // Handle exceptions appropriately
        e.printStackTrace(); // Logging the exception or handle it based on your application needs
        Thread.currentThread().interrupt(); // Restore the interrupted status
    }
        return targetRecordDTO;
    }
*/
//    private void handleSanctionData(List<SanctionDetailData> data) {
//        if (!data.isEmpty()) {
//            SanctionDetailData target = data.get(0);
//            futureAddress = target.getAddr_country();
//            nationality = target.getNationality_country();
//            Citizenship_Country = target.getCiti_country();
//            EntityType = target.getGroup_Type();
//            passport = target.getIden_country();
//            // program = target.getBirt_country(); // Uncomment if needed
//        }
//    }
    private CompletableFuture<RecordDTO> handleSanctionData(List<SanctionDetailData> sanctionDataList) {
        return CompletableFuture.supplyAsync(() ->
                sanctionDataList.stream()
                        .map(this::processSanctionDetailData) // Process the data
                        .findFirst() // Get the first available processed data
                        .map(this::convertToRecordDTO) // Convert to RecordDTO
                        .orElse(null) // Handle the case where no data is available
        );
    }

    // Helper method to convert SanctionDetailData to RecordDTO if needed
    private RecordDTO convertToRecordDTO(SanctionDetailData sanctionDetailData) {
        // Implement this method to convert SanctionDetailData to RecordDTO if necessary
        // This is a placeholder example
        if (sanctionDetailData == null) {
            return null;
        }
        RecordDTO recordDTO = new RecordDTO();


        // Set fields of recordDTO based on sanctionDetailData
        recordDTO.setEntityType(sanctionDetailData.getGroup_Type());
        recordDTO.setNationality(sanctionDetailData.getNationality_country());
        recordDTO.setCitizenship(sanctionDetailData.getCiti_country());
//        recordDTO.setsanctionDetailData.getBirt_country();
//        sanctionDetailData.getIden_country();
//        sanctionDetailData.getAddr_country();
//        recordDTO.setId(sanctionDetailData.getId());

        // Set fields of recordDTO based on sanctionDetailData
        return recordDTO;
    }

    // Example method to process SanctionDetailData
    private SanctionDetailData processSanctionDetailData(SanctionDetailData sanctionDetailData) {
        // Implement this method to process SanctionDetailData
        // This is a placeholder example


        return sanctionDetailData;
    }
    private void setStringAsync(String str, Consumer<String> setStr) {
        CompletableFuture.supplyAsync(() -> {
            return str;
        }).thenAccept(setStr).exceptionally(ex -> {
            ex.printStackTrace();
            // Handle the error appropriately
            return null;
        });
    }

}


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//    private final SanctionDetailReadService sanctionDetailReadService;
//    private String nationality;
//    private String Citizenship_Country;
//    private String futureAddress;
//    private String EntityType;
//    private String passport;
//    private String program;
//    public void updateRecordDTO(RecordDetails recordDetails, RecordDTO source, RecordDTO target) throws ExecutionException, InterruptedException {
//        log.debug(source+"  : RecordDTO source");
//        Integer id  = source.getIds();
//        Integer type = source.getFileType();
//        System.out.println("File type: " + type); // Debug statement
//
//
//        switch (type) {
//            case 1:
//                System.out.println("Case 1 executed");
////            Future<HashMap<String, String>> mapRetFuture = recordDetails.getValue(id);
////            HashMap<String, String> mapRet = mapRetFuture.get();
//                handleMapData(recordDetails.getValue(id).get());
//                break;
//
//            case 2:
//                System.out.println("Case 2 executed");
//                handleSanctionData(sanctionDetailReadService.fetchEuSanctionData(id));
//                break;
//            case 3:
//                handleSanctionData(sanctionDetailReadService.fetchUkSanctionData(id));
//                break;
//            case 4:
//                handleSanctionData(sanctionDetailReadService.fetchUnSanctionData(id));
//                break;
//            default:
//                throw new IllegalArgumentException("Unknown type: " + type);
//
//        }
//
//        setStringAsync(nationality, target::setNationality);
//        setStringAsync(Citizenship_Country, target::setCitizenship);
//        setStringAsync(futureAddress, target::setAddress);
//        setStringAsync(EntityType, target::setEntityType);
//        setStringAsync(passport, target::setPassport);
//        setStringAsync(program, target::setProgram);
//
//
////        setFutureAsync(passport, recordDTO::setPassport);
//
//    }
//    private void handleMapData(HashMap<String, String> mapRet) {
//        nationality = mapRet.getOrDefault("Nationality Country", nationality);
//        Citizenship_Country = mapRet.getOrDefault("Citizenship Country", Citizenship_Country);
//        futureAddress = mapRet.getOrDefault("futureAddress", futureAddress);
//        EntityType = mapRet.getOrDefault("EntityType", EntityType);
//        passport = mapRet.getOrDefault("Identifications", passport);
//        program = mapRet.getOrDefault("program", program);
//    }
//    private void handleSanctionData(List<SanctionDetailData> data) {
//        if (!data.isEmpty()) {
//            SanctionDetailData target = data.get(0);
//            futureAddress = target.getAddr_country();
//            nationality = target.getNationality_country();
//            Citizenship_Country = target.getCiti_country();
//            EntityType = target.getGroup_Type();
//            passport = target.getIden_country();
//            // program = target.getBirt_country(); // Uncomment if needed
//        }
//    }
//    private void setStringAsync(String str, Consumer<String> setStr) {
//        CompletableFuture.supplyAsync(() -> {
//            return str;
//        }).thenAccept(setStr).exceptionally(ex -> {
//            ex.printStackTrace();
//            // Handle the error appropriately
//            return null;
//        });
//    }
//}
