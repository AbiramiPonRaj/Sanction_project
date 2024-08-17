// import React, { useState, useEffect } from 'react';
// import {
//   TextField, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow,
//   Grid, FormControl, InputLabel, Select, MenuItem, Button, Box, Dialog, DialogTitle,
//   DialogContent, Container, DialogActions, DialogContentText, TablePagination, IconButton,
// } from '@mui/material'


// import { Card } from 'react-bootstrap';



// import ClearIcon from '@mui/icons-material/Clear';

// import VisibilityIcon from '@mui/icons-material/Visibility';
// import VisibilityOffIcon from '@mui/icons-material/VisibilityOff'; import { SelectChangeEvent } from '@mui/material';

// import * as XLSX from 'xlsx';
// import { GetApp } from '@mui/icons-material';
// import { AiFillFileExcel, AiFillPrinter } from 'react-icons/ai';
// import CountryApiService from '../../../data/services/master/Country/country_api_service';
// import StateApiService from '../../../data/services/master/State/state_api_service';
// import statusApiService from '../../../data/services/master/status/status-api-service';
// import Header from '../../../layouts/header/header';
// import SearchList from '../PepSearchList';

// import { useSelector } from 'react-redux';

// import Level3RIF from './PepLevel3 RIF';
// import ViewService from '../../../data/services/viewservice/view_api_service';
// import { All } from '../../../data/services/viewservice/view_payload';
// import SearchApiService from '../../../data/services/pep_search/search-api-service';
// import HitdatalifecycleApiService from '../../../data/services/pep_search/hitdatalifecycle/hitdatalifecycle-api-service';
// import HitdataApiService from '../../../data/services/pep_search/hitdata/hitdata-api-service';
// import PendingAlertApiService from '../../../data/services/pep_search/PendingAlert/pendingalert-api-service';
// import HitcaseApiService from '../../../data/services/pep_search/hitcase/hitcase-api-service';

// interface Type {
//   id: string;
//   type: string;
// }
// interface State {
//   id: string;
//   countryId: string;
//   name: string;
// }
// interface Country {
//   id: string;
//   name: string;
// }
// interface PindingcasesPayload {
//   id: string;
//   id1: string;
//   criminalId: string;
//   name: string;
//   matchingScore: string;
//   remark: string;
// }
// // In 'SearchList.tsx'
// // In 'Search.tsx'
// // In 'Search.tsx'
// export interface Employee {
//   id: number;
//   name: string;
//   dob: string;
//   title: string;
//   lastName: string;
//   state: string;
//   dist: string;
//   address: string;
//   designation: string;
//   ministry: string;
//   placeOfBirth: string;
//   coName: string; // Add the missing properties 'coName' and 'department'
//   department: string;
//   // Add any other missing properties as needed
// }
// interface Hitdata {
//   id: string;
//   name: string;
//   display: string;
//   criminalId: string;
//   searchId: string;
//   matchingScore: string
//   statusNowId: string;
//   cycleId: string;
// }

// interface Status {
//   id: string;
//   name: string;
//   // Add other properties if necessary
// }
// interface PendingAlert {
//   id: string;
//   id1: string;
//   criminalId: string;
//   name: string;
//   matchingScore: string;
//   remark: string;
// }
// const Level3Search = () => {
//   const [selectedCountry, setSelectedCountry] = useState('');
//   const [countries, setCountries] = useState<Country[]>([]);
//   const [selectedType, setSelectedType] = useState('');
//   const [types, setTypes] = useState<All[]>([]);
//   const [name, setName] = useState('');
//   const [identity, setIdentity] = useState('');
//   const [page, setPage] = useState(0);
//   const [rowsPerPage, setRowsPerPage] = useState(10);
//   const [serialNumber, setSerialNumber] = useState(1);
//   const [searchResults, setSearchResults] = useState<any[]>([]);
//   const [selectedEmployee, setSelectedEmployee] = useState<Employee | null>(null); // State to store the selected employee
//   const [isDialogOpen, setIsDialogOpen] = useState(false); // State 
//   const countryService = new CountryApiService(); // Create an instance of CountryApiService
//   // const [selectedAction, setSelectedAction] = useState<string>('');
//   const [selectedSerialNumber, setSelectedSerialNumber] = useState<number | null>(null);
//   const [selectedSearchId, setSelectedSearchId] = useState<number | null>(null);
//   const [Pendingcases, setPendingcases] = useState<PindingcasesPayload[]>([]);
//   const [selectedState, setSelectedState] = useState('');
//   const [pendingAlert, setPendingAlert] = useState<PendingAlert[]>([]);
//   const [states, setStates] = useState<State[]>([]);

//   const [selectedActionTag, setSelectedActionTag] = useState<string | null>(null); // State to store the selected action for the selected row

//   const [isRemarksDialogOpen, setIsRemarksDialogOpen] = useState(false);
//   const [selectedStatus, setSelectedStatus] = useState('');
//   const [remarks, setRemarks] = useState('');
//   const authApiService = new SearchApiService();

//   const status = new statusApiService();
//   const hitdatalifecycleApiService = new HitdatalifecycleApiService();
//   const stateApiService = new StateApiService();
//   const hitdataApiService = new HitdataApiService();
//   const [statusData, setStatusData] = useState<any[]>([]);
//   const [selectedStatuses, setSelectedStatuses] = useState<string[]>([]);
//   const [selectedRow, setSelectedRow] = useState<number | null>(null); // Initialize with null or -1 if no row is selected
//   const [remarksData, setRemarksData] = useState<{ [key: number]: string }>({});
//   const [selectedActions, setSelectedActions] = useState<{ [key: number]: string }>({});
//   const [remarksAndActions, setRemarksAndActions] = useState<{ action: string; remarks: string }[]>([]);
//   const [isSearchTableVisible, setIsSearchTableVisible] = useState(false);
//   const authService = new StateApiService();
//   const [searchInput, setSearchInput] = useState('');
//   const [selectedAction, setSelectedAction] = useState<string[]>([]);
//   const pendingalerService = new PendingAlertApiService();
//   const [showTable, setShowTable] = useState(false);
//   const [rowStatuses, setRowStatuses] = useState<{ [key: number]: { status: string; remarks: string } }>({});
//   const [showSearchTable, setShowSearchTable] = useState(false);
//   const [showPendingAlertTable, setShowPendingAlertTable] = useState(false);


//   const hitcaseApiService = new HitcaseApiService();
//   const userDetails = useSelector((state: any) => state.loginReducer);
//   const userFirstName = userDetails.userData?.firstName;
//   const loginDetails = userDetails.loginDetails;
//   const userId = loginDetails.uid;
//   const handleReset = () => {
//     setName('');
//     setIdentity('');
//     setSelectedCountry('');
//     setSelectedState('');
//     setSelectedType('');
//     fetchStates();
//   };
//   const fetchStates = async () => {
//     try {
//       const States = await authService.getStateDataByCountryId();
//       setStates(States);
//     } catch (error) {
//       console.error("Error fetching states:", error);
//     }
//   };

//   const handleCountryChange = async (data: any) => {
//     setSelectedCountry(data);
//     try {
//       let states = await stateApiService.getStateDataByCountryId(); // Call the method on the instance
//       console.log(states);
//     } catch (error) {
//       // Handle errors, e.g., display an error messagesetRemarksAndActions
//     }
//   }
//   function getCountryNameById(countryId: string) {
//     const country = countries.find((c) => c.id === countryId);
//     return country ? country.name : '';
//   }


//   useEffect(() => {
//     fetchCountries();
//     fetchSearchResults();
//     fetchtypes();
//     fetchStatus();
//     fetchStates();
//     const savedActions = JSON.parse(localStorage.getItem('savedActions') || '{}');
//     setSelectedActions(savedActions);
//   }, [page, rowsPerPage]);
//   const fetchCountries = async () => {
//     try {
//       const Countries = await countryService.getCountryOptions();
//       setCountries(Countries);
//       setSerialNumber(page * rowsPerPage + 1);
//     } catch (error) {
//       console.error("Error fetching Country:", error);
//     }
//   };
//   const viewservice = new ViewService();
//   const fetchtypes = async () => {
//     try {
//       const types = await viewservice.getAll(); // Call getType from TypeApiService
//       setTypes(types);
//     } catch (error) {
//       console.error("Error fetching types:", error);
//     }
//   };

//   const fetchSearchResults = async () => {
//     try {
//       // Fetch search results from the server, e.g., using your API service
//       const results = await authApiService.getSearchs();
//       setSearchResults(results); // Update the searchResults state with the fetched data
//       setSerialNumber((page * rowsPerPage) + 1); // Update selectedSerialNumber based on the current page and rowsPerPage
//     } catch (error) {
//       console.error("Error fetching search results:", error);
//     }
//   };

//   const fetchStatus = async () => {
//     try {
//       const statuses: Status[] = await status.getStatus(); // Specify the type as Status[]

//       // Filter the statuses to keep only "close" and "Escalation" (matching the actual case)
//       const filteredStatuses = statuses.filter((status: Status) => {
//         return status.name === "close" || status.name === "Escalation";
//       });

//       console.log(filteredStatuses); // Add this line to check the filtered statuses
//       setStatusData(filteredStatuses); // Update the statusData state with the filtered results
//     } catch (error) {
//       console.error("Error fetching statuses:", error);
//     }
//   };

//   const handleStateChange = async (data: any) => {
//     setSelectedState(data);

//   }

//   const handleTypeChange = async (data: any) => {
//     setSelectedType(data);

//   }
//   const handleSubmit = async (e: React.FormEvent) => {
//     e.preventDefault();

//     try {
//       // const payload: SearchPayload = {
//       //   name: name,
//       //   typeId: selectedType,
//       //   countryId: selectedCountry,
//       //   identity: identity,
//       //   stateId: selectedState,
//       //   uid:userId

//       // };

//       // const response = await authApiService.CreateSearch(payload); // Call CreateSearch from SearchApiService
//       // setIsSearchTableVisible(true);

//       // setName('');
//       // setSelectedCountry('');
//       // setSelectedState('');
//       fetchSearchResults()
//       setIsSearchTableVisible(true);

//       // showSuccessMessage('Search added successfully.');
//     } catch (error) {
//       console.error("Error adding Search:", error);
//     }
//   };


//   const handlePageChange = (event: React.MouseEvent<HTMLButtonElement> | null, newPage: number) => {
//     setPage(newPage);
//     setSerialNumber(newPage * rowsPerPage + 1);
//   };

//   const handleRowsPerPageChange = (event: React.ChangeEvent<HTMLInputElement>) => {
//     const newRowsPerPage = parseInt(event.target.value, 10);
//     const newPage = selectedSerialNumber !== null ? Math.floor(selectedSerialNumber / newRowsPerPage) : 0;
//     setRowsPerPage(newRowsPerPage);
//     setPage(newPage);
//     setSerialNumber(newPage * newRowsPerPage + 1);
//   };

//   const startIndex = page * rowsPerPage;
//   const endIndex = startIndex + rowsPerPage;

//   const handleRowClick = (employee: Employee, index: number) => {
//     const currentIndex = page * rowsPerPage + index; // Calculate the current index
//     setSelectedEmployee(employee);
//     setSelectedRow(currentIndex); // Set the selected row index
//     setIsDialogOpen(true);
//   };



//   const handleCloseRemarksDialog = () => {
//     setIsRemarksDialogOpen(false);
//     setSelectedStatus('');
//     setRemarks('');
//   };

//   const handleStatusChange = (event: SelectChangeEvent<string>) => {
//     setSelectedStatus(event.target.value);
//   };

//   const handleRemarksChange = (event: React.ChangeEvent<HTMLInputElement>) => {
//     setRemarks(event.target.value);
//   };

//   const handleIconClick = (index: number) => {
//     const currentIndex = page * rowsPerPage + index;
//     const existingAction = selectedActions[currentIndex] || '';
//     const existingRemarks = remarksAndActions[currentIndex]?.remarks || '';

//     setSelectedStatus(existingAction);
//     setRemarks(existingRemarks);
//     setSelectedRow(currentIndex);
//     setIsRemarksDialogOpen(true);

//   };

//   const getStatusName = (action: string) => {
//     const status = statusData.find((status) => status.id === action);

//     if (status) {
//       // Define a mapping from status ID to CSS class
//       const statusClassMap: { [key: string]: string } = {
//         '1': 'green-text', // Assuming '1' corresponds to 'Closed'
//         '2': 'red-text',   // Assuming '2' corresponds to 'Escalation'
//         '3': 'yellow-text', // Assuming '3' corresponds to 'Request For Information'
//       };

//       const statusClass = statusClassMap[status.id];

//       if (statusClass) {
//         return (
//           <span className={statusClass}>
//             {status.name}
//           </span>
//         );
//       } else {
//         // If the status ID doesn't match any of the defined classes, return the status name as is
//         return status.name;
//       }
//     } else {
//       return ''; // Handle cases where the status is not found
//     }
//   };

//   const handleRemarksSubmit = async () => {
//     try {
//       if (selectedRow !== null && selectedRow >= 0) {
//         const updatedRemarksAndActions = [...remarksAndActions];
//         updatedRemarksAndActions[selectedRow] = { action: selectedStatus, remarks };

//         //searchResults.filter();
//         //alert(selectedRow);
//         // Update the state with the new remarks and actions
//         setRemarksAndActions(updatedRemarksAndActions);

//         const selectedSearchResult = searchResults[selectedRow];
//         //alert(selectedSearchResult);

//         if (selectedSearchResult) {
//           const hitdatalifecyclePayload = {
//             searchId: selectedSearchResult.searchId,
//             criminalId: selectedSearchResult.criminalId,
//             statusId: selectedStatus,
//             remark: remarks,
//             hitId: selectedSearchResult.hitId,
//             levelId: '1',
//             caseId: '0',
//             uid: userId
//           };
//           const hitcasePayload = {
//             //id:'null',    
//             display: '-', // Replace with actual value for 'display'
//             searchId: selectedSearchResult.searchId,
//             hitId: selectedSearchResult.hitId,
//             criminalId: selectedSearchResult.criminalId,
//             levelId: '1', // Replace with the appropriate level ID
//             statusNowId: selectedStatus,
//             cycleId: '1', // Replace with actual value for 'cycleId'
//             remark: remarks,
//             uid: userId
//             //valid:'1'


//           };

//           console.log("hitdatalifecycle Payload:", hitdatalifecyclePayload);
//           console.log("hitCasePayload:", hitcasePayload);

//           if (parseInt(selectedStatus) == 1) {// Insert into hitdatalifecycle table
//             await hitdatalifecycleApiService.CreateHitdatalifecycle(hitdatalifecyclePayload);
//           }
//           if (parseInt(selectedStatus) == 2) { // Insert into hitcase table
//             alert(hitcasePayload.criminalId);

//             await hitcaseApiService.CreateHitCaseInsert(hitcasePayload);
//           }
//         }

//         // ... (remaining code)
//         // setSelectedActionTag(selectedStatus);

//         // Update the selectedActions state
//         setSelectedActions({
//           ...selectedActions,
//           [selectedRow]: selectedStatus,
//         });

//         // // Save the action in local storage (if needed)
//         // const savedActions = { ...selectedActions };
//         // savedActions[selectedRow] = selectedStatus;
//       }


//       setIsRemarksDialogOpen(false);
//     } catch (error) {
//       console.error("Error submitting remarks:", error);
//     }
//   };





//   const filterResults = () => {
//     return searchResults.filter((result) =>
//       (
//         (typeof result.criminalName === 'string' && result.criminalName.toLowerCase().includes(searchInput.toLowerCase())) ||
//         (typeof result.dob === 'string' && result.dob.toLowerCase().includes(searchInput.toLowerCase())) ||
//         (typeof result.address === 'string' && result.address.toLowerCase().includes(searchInput.toLowerCase())) ||
//         (typeof result.countryId === 'string' && result.countryId.toLowerCase().includes(searchInput.toLowerCase())) ||
//         (typeof result.nameScore === 'string' && result.nameScore.toLowerCase().includes(searchInput.toLowerCase()))
//       ) ||
//       (
//         (typeof result.criminalName === 'number' && result.criminalName === parseInt(searchInput, 10)) ||
//         (typeof result.dob === 'number' && result.dob === parseInt(searchInput, 10)) ||
//         (typeof result.address === 'number' && result.address === parseInt(searchInput, 10)) ||
//         (typeof result.countryId === 'number' && result.countryId === parseInt(searchInput, 10)) ||
//         (typeof result.nameScore === 'number' && result.nameScore === parseInt(searchInput, 10))
//       )
//     );
//   };
//   const handleExportToExcel = () => {
//     // Create a new workbook
//     const workbook = XLSX.utils.book_new();

//     // Convert all table data to an Excel sheet
//     const allSheetData = searchResults.map((result, index) => {
//       return [
//         index + 1, // Index should start from 1
//         result.criminalName,
//         result.dob,
//         result.address,
//         result.countryId,
//         result.nameScore,
//       ];
//     });

//     const sheet = XLSX.utils.aoa_to_sheet([
//       ['S.No', 'Name', 'DOB', 'Address', 'Country', 'Name Score'], // Header row
//       ...allSheetData, // Data rows
//     ]);

//     // Add the sheet to the workbook
//     XLSX.utils.book_append_sheet(workbook, sheet, 'SearchResults');

//     // Create a blob from the workbook to download
//     XLSX.writeFile(workbook, 'search_results.xlsx');
//   };

//   const handleSearchInputKeyPress = (e: React.KeyboardEvent<HTMLInputElement>) => {
//     if (e.key === 'Enter') {
//       handleSubmit(e);
//     }
//   };
//   const handlePendingAlertButtonClick = () => {
//     // Call the function to load the pending alert details
//     handlePendingAlertClick();

//     // Now, open the Levelcasedetails dialog
//     setIsLevelcasedetailsOpen(true);
//   };
//   const [isLevel2casedetailsOpen, setIsLevelcasedetailsOpen] = useState(false);

//   const handlePendingAlertClick = async () => {
//     try {
//       let pendingAlertData = await pendingalerService.getpendingalertdetails();
//       console.log(pendingAlertData); // Log the data
//       setPendingAlert(pendingAlertData);
//       setIsLevelcasedetailsOpen(true); // Open the dialog
//     } catch (error) {
//       // Handle any errors
//     }
//   };




//   return (
//     <>
//       <Box sx={{ display: 'flex' }}>
//         <Header />
//         <Box component="main" sx={{ flexGrow: 1, p: 3 }}>
//           <Box m={4}>
//             <Container style={{ maxWidth: 'none', backgroundColor: 'white', padding: "30px", margin: "10px" }}>
//               <Box m={4}>
//                 <div className="d-flex justify-content-center">
//                   <div className="card" style={{ boxShadow: '1px 1px 1px grey', width: '100%' }}>
//                     <div className="card-body">

//                       <div className="nav gap-2 p-3 small shadow-sm">
//                         <Grid container spacing={3} alignItems="center" justifyContent="center">

//                           <Grid item xs={12} sm={2}>
//                             <TextField
//                               label="Name"
//                               value={name}
//                               onChange={e => setName(e.target.value)}
//                               name="name"
//                               variant="outlined"
//                               className="text-field"
//                               margin="dense"
//                               size="small"
//                               fullWidth
//                             />
//                           </Grid>



//                           <Grid item xs={12} sm={2}>
//                             <FormControl fullWidth variant="outlined" margin="dense" className="text-field">
//                               <InputLabel htmlFor="type">Type</InputLabel>
//                               <Select
//                                 label="Type"
//                                 value={selectedType}
//                                 onChange={(e) => handleTypeChange(e.target.value)}
//                                 name="type"
//                                 variant="outlined"
//                                 size="small"
//                                 required
//                               >
//                                 {types.map(type => (
//                                   <MenuItem key={type.partyTypeID} value={parseInt(type.partyTypeID)}>
//                                     {type.type_text}
//                                   </MenuItem>
//                                 ))}
//                               </Select>
//                             </FormControl>
//                           </Grid>
//                           <Grid item xs={12} sm={2}>
//                             <FormControl fullWidth variant="outlined" margin="dense" className="text-field">
//                               <InputLabel htmlFor="country">Country</InputLabel>
//                               <Select
//                                 label="Country"
//                                 value={selectedCountry}
//                                 onChange={(e) => handleCountryChange(e.target.value)}
//                                 name="country"
//                                 variant="outlined"
//                                 className="text-field"
//                                 size="small"
//                                 required
//                               >
//                                 {countries.map((country) => (
//                                   <MenuItem key={country.id} value={country.id}> {/* Use country.name as the value */}
//                                     {country.name}
//                                   </MenuItem>
//                                 ))}
//                               </Select>
//                             </FormControl>


//                           </Grid>
//                           <Grid item xs={12} sm={2}>
//                             <FormControl fullWidth variant="outlined" margin="dense" className="text-field">
//                               <InputLabel htmlFor="country">State</InputLabel>
//                               <Select
//                                 label="State"
//                                 value={selectedState}
//                                 onChange={(e) => handleStateChange(e.target.value)}

//                                 name="State"
//                                 variant="outlined"
//                                 className="text-field"
//                                 size="small"
//                                 required
//                               >
//                                 {states.map((states) => (
//                                   <MenuItem key={states.id} value={states.id}> {/* Use country.name as the value */}
//                                     {states.name}
//                                   </MenuItem>
//                                 ))}

//                               </Select>
//                             </FormControl>
//                           </Grid>
//                           <Grid item xs={12} sm={2}>
//                             <button type="button" className="btn btn-outline-primary" onClick={handleSubmit}>
//                               SEARCH
//                             </button>
//                           </Grid>
//                           <Grid item xs={12} sm={2}>

//                             <button
//                               type="button" className="btn btn-outline-primary"
//                               onClick={handlePendingAlertButtonClick}
//                             >
//                               Case/RIF
//                             </button>
//                           </Grid>
//                         </Grid>
//                       </div>

//                     </div>
//                   </div>

//                 </div>


//                 {isSearchTableVisible && (
//                   <div>
//                     <h3>Level 3 Search</h3>
//                     <div style={{ display: 'flex', justifyContent: 'flex-end' }}>
//                       <TablePagination
//                         rowsPerPageOptions={[10, 20, 50, 100]}
//                         component="div"
//                         count={searchResults.length}
//                         page={page}
//                         onPageChange={handlePageChange}
//                         rowsPerPage={rowsPerPage}
//                         onRowsPerPageChange={handleRowsPerPageChange} // Attach the event handler here
//                       // style={{ marginLeft: "500px" }}
//                       />

//                       <div style={{ display: 'flex', justifyContent: 'flex-end' }}>

//                         <TextField
//                           label="Open Search"
//                           value={searchInput}
//                           onChange={(e) => setSearchInput(e.target.value)}
//                           name="Search"
//                           variant="outlined"
//                           className="text-field"
//                           margin="dense"
//                           size="small"
//                           fullWidth
//                         />

//                       </div>

//                       <IconButton onClick={handleExportToExcel} color="primary">
//                         <AiFillFileExcel />
//                       </IconButton>

//                     </div>
//                     <div className="d-flex justify-content-center">

//                       <div className="card" style={{ boxShadow: '1px 1px 1px grey', width: '100%' }}>
//                         <div className="card-body">
//                           <Grid container spacing={1} alignItems="center" justifyContent="center">

//                             <TableContainer component={Card} sx={{ maxHeight: 440 }} style={{ width: "85%", margin: "20px" }}>
//                               <Table size="small" aria-label="a dense table" style={{ margin: '0 auto' }}>
//                                 <TableHead sx={{ backgroundColor: '#cccdd1' }}>
//                                   <TableRow className="tableHeading">
//                                     <TableCell>S.No</TableCell>
//                                     <TableCell>Name</TableCell>
//                                     <TableCell>DOB</TableCell>
//                                     <TableCell>Address</TableCell>
//                                     <TableCell>Country</TableCell>
//                                     <TableCell>Name Score</TableCell>
//                                     <TableCell>Action</TableCell>
//                                   </TableRow>
//                                 </TableHead>
//                                 <TableBody>
//                                   {filterResults().slice(startIndex, endIndex).map((result, index) => {
//                                     const currentIndex = page * rowsPerPage + index;
//                                     const selectedAction = selectedActions[currentIndex] || '';


//                                     return (
//                                       <React.Fragment key={result.id}>
//                                         <TableRow
//                                         >
//                                           <TableCell>{index + serialNumber}</TableCell>

//                                           <TableCell>
//                                             <button
//                                               onClick={() => handleRowClick(result, index)}
//                                               style={{ cursor: 'pointer', background: 'none', border: 'none' }}
//                                             >
//                                               {result.name}
//                                             </button>
//                                           </TableCell>
//                                           <TableCell>
//                                             <button
//                                               onClick={() => handleRowClick(result, index)}
//                                               style={{ cursor: 'pointer', background: 'none', border: 'none' }}
//                                             >
//                                               {result.dob}
//                                             </button>
//                                           </TableCell>
//                                           <TableCell>
//                                             <button
//                                               onClick={() => handleRowClick(result, index)}
//                                               style={{ cursor: 'pointer', background: 'none', border: 'none' }}
//                                             >
//                                               {result.address}
//                                             </button>
//                                           </TableCell>
//                                           <TableCell>
//                                             <button
//                                               onClick={() => handleRowClick(result, index)}
//                                               style={{ cursor: 'pointer', background: 'none', border: 'none' }}
//                                             >
//                                               {result.country}
//                                             </button>
//                                           </TableCell>
//                                           <TableCell>
//                                             <button
//                                               onClick={() => handleRowClick(result, index)}
//                                               style={{ cursor: 'pointer', background: 'none', border: 'none' }}
//                                             >
//                                               {result.matchScore}
//                                             </button>
//                                           </TableCell>
//                                           <TableCell>
//                                             <IconButton onClick={() => handleIconClick(index)} style={{ padding: '1px 1px' }}>
//                                               {selectedAction ? (
//                                                 <VisibilityOffIcon style={{ color: 'red' }} /> // Red color for 'VisibilityOffIcon'
//                                               ) : (
//                                                 <VisibilityIcon style={{ color: 'green' }} /> // Green color for 'VisibilityIcon'
//                                               )}
//                                             </IconButton>


//                                             {selectedAction && (
//                                               <span>{getStatusName(selectedAction)}</span>
//                                             )}
//                                           </TableCell>

//                                         </TableRow>

//                                       </React.Fragment>
//                                     );
//                                   })}
//                                 </TableBody>

//                               </Table>
//                             </TableContainer>

//                           </Grid>
//                         </div>
//                       </div>
//                     </div>
//                   </div>
//                 )}

//                 <div>



//                 </div>


//               </Box>
//             </Container >
//           </Box >
//         </Box>
//       </Box>
//       <Dialog
//         open={isRemarksDialogOpen}
//         onClose={handleCloseRemarksDialog}
//         fullWidth
//         maxWidth="md"
//       >
//         <DialogActions>
//           <Button onClick={handleCloseRemarksDialog} color="primary">

//             <ClearIcon />
//           </Button>
//         </DialogActions>
//         <DialogTitle>Enter Remarks</DialogTitle>
//         <DialogContentText style={{ textAlign: 'center' }}>
//           Select a status and enter remarks for this employee.
//         </DialogContentText>
//         <DialogContent>
//           <Grid container alignItems="center" justifyContent="center">
//             <Grid item xs={12} sm={3}>
//               <FormControl fullWidth variant="outlined" margin="dense">
//                 <InputLabel>Status</InputLabel>
//                 <Select
//                   label="Status"
//                   value={selectedStatus}
//                   onChange={handleStatusChange}
//                 >
//                   <MenuItem value="">Select Status</MenuItem>
//                   {statusData.map((status) => (
//                     <MenuItem key={status.id} value={status.id}>
//                       {status.name}
//                     </MenuItem>
//                   ))}
//                 </Select>
//               </FormControl>

//             </Grid>
//           </Grid>
//           {selectedStatus && (
//             <div>
//               <DialogContentText style={{ textAlign: 'center' }}>
//                 Enter your remarks for this action.
//               </DialogContentText>

//               <Grid container alignItems="center" justifyContent="center">
//                 <Grid item xs={12} sm={8}>
//                   <TextField
//                     autoFocus
//                     margin="dense"
//                     id="outlined-multiline-static"
//                     label="Remarks"
//                     type="text"
//                     fullWidth
//                     multiline
//                     rows={4}
//                     value={remarks}
//                     defaultValue="Default Value"
//                     onChange={handleRemarksChange}
//                   />

//                 </Grid>
//               </Grid>

//             </div>
//           )}
//         </DialogContent>
//         <DialogActions>


//           <Button onClick={handleRemarksSubmit} color="primary">
//             Submit
//           </Button>
//         </DialogActions>
//       </Dialog>
//       <Dialog open={isDialogOpen} fullWidth maxWidth="xl">
//         <DialogActions>
//           <Button
//             onClick={() => setIsDialogOpen(false)}
//             color="primary"
//           >
//             <ClearIcon />
//           </Button>
//         </DialogActions>
//         <DialogTitle>Employee Details</DialogTitle>
//         <DialogContent>
//           {selectedEmployee && <SearchList employee={selectedEmployee} />}
//         </DialogContent>
//       </Dialog>

//       {isLevel2casedetailsOpen && (
//         <Level3RIF
//           isLevel2casedetailsOpen={isLevel2casedetailsOpen}
//           setIsLevelcasedetailsOpen={setIsLevelcasedetailsOpen}
//           handleLevelDetailsDialogOpen={() => setIsLevelcasedetailsOpen(false)}
//         />
//       )}

//     </>
//   );
// }

// export default Level3Search;




import React, { useState, useEffect } from 'react';
import {
    TextField, Table, TableBody, TableCell, TableContainer, TableHead, TableRow,
    Grid, FormControl, InputLabel, Select, MenuItem, Button, Box, Dialog, DialogTitle,
    DialogContent, Container, DialogActions, DialogContentText, TablePagination, IconButton,
} from '@mui/material'

import { Card } from 'react-bootstrap';

import ClearIcon from '@mui/icons-material/Clear';

import VisibilityIcon from '@mui/icons-material/Visibility';
import VisibilityOffIcon from '@mui/icons-material/VisibilityOff'; import { SelectChangeEvent } from '@mui/material';



import { start } from 'repl';
import classNames from 'classnames';
import CountryApiService from '../../../data/services/master/Country/country_api_service';

import Header from '../../../layouts/header/header';
import SearchList from '../PepSearchList';
import statusApiService from '../../../data/services/master/status/status-api-service';
import StateApiService from '../../../data/services/master/State/state_api_service';
import { useSelector } from 'react-redux';
import SearchApiService from '../../../data/services/pep_search/search-api-service';
import PendingcasesApiService from '../../../data/services/pep_search/pendingcases/pending-api-service';
import HitcaseApiService from '../../../data/services/pep_search/hitcase/hitcase-api-service';
import RIFApiService from '../../../data/services/pep_search/rif/rif-api-service';

interface Pendingcase {
    searchId: string;
    criminalId: string;
    hitId: string;
    criminalName: string;
    matchingScore: string;
    remark: string;
    levelId: string;
    statusId: string;
    caseId: string;
}
interface Status {
    id: string;
    name: string;
    // Add other properties if necessary
}
interface RIF {
    caseId: string;
    criminalId: string;
    hitId: string;
    levelId: string;
    searchId: string;
    statusId: string;
    matchScore: string;
    country: string;
    state: string;
    dob: string;
    remark: string;
    uid: string;
    criminalName: string;
}

const Level3RIF= () => {


    const [selectedCountry, setSelectedCountry] = useState('');

    const [selectedState, setSelectedState] = useState('');
    const [selectedType, setSelectedType] = useState('');

    const [name, setName] = useState('');
    const [identity, setIdentity] = useState('');
    const [page, setPage] = useState(0);
    const [rowsPerPage, setRowsPerPage] = useState(5);
    const [serialNumber, setSerialNumber] = useState(1);
    const [searchResults, setSearchResults] = useState<any[]>([]);
    // const [selectedEmployee, setSelectedEmployee] = useState<Employee | null>(null); // State to store the selected employee
    const [isDialogOpen, setIsDialogOpen] = useState(false); // State 
    const countryService = new CountryApiService(); // Create an instance of CountryApiService
    const [isRemarksDialogOpen, setIsRemarksDialogOpen] = useState(false);
    const [isRemarksDialogOpenRif, setIsRemarksDialogOpenRif] = useState(false);
    const [selectedStatus, setSelectedStatus] = useState('');
    const [remarks, setRemarks] = useState('');
    const authApiService = new SearchApiService();
    const status = new statusApiService();
    const stateApiService = new StateApiService();
    const [statusData, setStatusData] = useState<any[]>([]);
    const [showTable, setShowTable] = useState(false);
    const [pendingcase, setPendingcase] = useState<Pendingcase[]>([]);
    const [pendingRif, setPendingRif] = useState<RIF[]>([]);

    const authService = new PendingcasesApiService();
    const [selectedRow, setSelectedRow] = useState<number | null>(null);
    const [rowStatuses, setRowStatuses] = useState<{ [key: number]: { status: string; remarks: string } }>({});
    const [selectedStatusId, setSelectedStatusId] = useState<number | null>(null);
    const [selectedActions, setSelectedActions] = useState<{ [key: number]: string }>({});
    const [remarksAndActions, setRemarksAndActions] = useState<{ action: string; remarks: string }[]>([]);
    const [selectedActionTag, setSelectedActionTag] = useState<string | null>(null);
    const [selectedAction, setSelectedAction] = useState<string | null>(null);
    const [showPendingAlertTable, setShowPendingAlertTable] = useState(false);
    const [showRIFTable, setShowRIFTable] = useState(false);
    const hitcaseApiService = new HitcaseApiService();
    const [selectedSerialNumber, setSelectedSerialNumber] = useState<number | null>(null);
    const rifService = new RIFApiService();

    const [showPendingCaseTable, setShowPendingCaseTable] = useState(false);
    const [showPendingRIFTable, setShowPendingRIFTable] = useState(false);
    const userDetails = useSelector((state: any) => state.loginReducer);
    const userFirstName = userDetails.userData?.firstName;
    const loginDetails = userDetails.loginDetails;
    const userId = loginDetails.uid;
    useEffect(() => {
        fetchStatus();

        // const savedActions = JSON.parse(localStorage.getItem('savedActions') || '{}');
        // setSelectedActions(savedActions);
    }, [page, rowsPerPage]);



    const handlePendingAlertClick = async () => {
        try {
            // Call the API to get pending cases
            let results = await authService.getPendingcaseRIF();

            // Update the state to show the pending case table
            setShowPendingCaseTable(true);
            setShowPendingRIFTable(false);

            // Set the pending cases data to be displayed in the table
            setPendingcase(results);
            setSearchResults(results);
        } catch (error) {
            // Handle the error as needed
        }
    };
    const handlePendingRIFClick = async () => {
        try {
            let results = await rifService.getpendingRIF();
            setPendingRif(results);
            setSearchResults(results);
            setShowPendingRIFTable(true);
            setShowPendingCaseTable(false);


        } catch (error) {

        }
    };
    const fetchStatus = async () => {
        try {
            const filteredStatuses = await status.getStatus(); // Call getType from TypeApiService
            setStatusData(filteredStatuses)
        } catch (error) {
            console.error("Error fetching types:", error);
        }
    };







    const handlePageChange = (event: React.MouseEvent<HTMLButtonElement> | null, newPage: number) => {
        setPage(newPage);
        setSerialNumber(newPage * rowsPerPage + 1);
    };

    const handleRowsPerPageChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const newRowsPerPage = parseInt(event.target.value, 10);
        const newPage = selectedSerialNumber !== null ? Math.floor(selectedSerialNumber / newRowsPerPage) : 0;
        setRowsPerPage(newRowsPerPage);
        setPage(newPage);
        setSerialNumber(newPage * newRowsPerPage + 1);
    };

    const startIndex = page * rowsPerPage;
    const endIndex = startIndex + rowsPerPage;

    const handleCloseRemarksDialog = () => {
        setIsRemarksDialogOpen(false);
        setSelectedStatus('');
        setRemarks('');
    };
    const handleCloseRemarksDialogRif = () => {
        setIsRemarksDialogOpen(false);
        setSelectedStatus('');
        setRemarks('');
    };

    const handleStatusChange = (event: SelectChangeEvent<string>) => {
        setSelectedStatus(event.target.value);
    };
    const handleStatusChangeRif = (event: SelectChangeEvent<string>) => {
        setSelectedStatus(event.target.value);
    };
    const handleRemarksChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setRemarks(event.target.value);
    };

    const handleRemarksChangeRif = (event: React.ChangeEvent<HTMLInputElement>) => {
        setRemarks(event.target.value);
    };
    const handleIconClick = (index: number) => {
        // alert(index);
        const currentIndex = page * rowsPerPage + index;
        const existingAction = selectedActions[currentIndex] || '';
        const existingRemarks = remarksAndActions[currentIndex]?.remarks || '';

        setSelectedStatus(existingAction);
        setRemarks(existingRemarks);
        setSelectedRow(currentIndex);
        setIsRemarksDialogOpen(true);
    };


    const handleRemarksSubmit = async () => {

        try {
          if (selectedRow !== null && selectedRow >= 0) {
            const updatedRemarksAndActions = [...remarksAndActions];
            updatedRemarksAndActions[selectedRow] = { action: selectedStatus, remarks };
    
            setRemarksAndActions(updatedRemarksAndActions);
    
            const selectedSearchResult = searchResults[selectedRow];
    
            if (selectedSearchResult) {
            //   alert(selectedSearchResult.searchId + '' + selectedSearchResult.criminalId + '' + selectedSearchResult.hitId);
              const PindingcasesPayload = {
                searchId: selectedSearchResult.searchId,
                criminalId: selectedSearchResult.criminalId,
                statusId: selectedStatus,
                remark: remarks,
                hitId: selectedSearchResult.hitId,
                levelId: '4',
                caseId: selectedSearchResult.caseId,
                uid: '1', // Ensure 'userId' or a valid user ID is used here
                criminalName: '',
                matchingScore: '0' // You can set an appropriate value for matchingScore
              };
              // Assuming CreateCaseLifeCycleImplInsert is accessible from this scope
    
              await authService.CreateCaseLifeCycleImplInsert(PindingcasesPayload);
              // Handle the response if needed
            }
          }
    
          setIsRemarksDialogOpen(false);
        } catch (error) {
          console.error('Error submitting remarks:', error);
          // Handle any other errors that might occur
        }
      }

    function getStatusColor(status: string) {
        switch (status) {
            case 'Close':
                return 'red';
            case 'Escalation':
                return 'green';
            case 'Request for information':
                return 'yellow';
            default:
                return 'white';
        }
    }

    // const handleRemarksSubmitRif = async () => {
    //     try {
    //         if (selectedRow !== null && selectedRow >= 0) {
    //             const updatedRemarksAndActions = [...remarksAndActions];
    //             updatedRemarksAndActions[selectedRow] = { action: selectedStatus, remarks };

    //             setRemarksAndActions(updatedRemarksAndActions);

    //             const selectedSearchResult = searchResults[selectedRow];

    //             if (selectedSearchResult) {
    //                 //   alert(selectedSearchResult.searchId+''+selectedSearchResult.criminalId+''+selectedSearchResult.hitId);
    //                 const PindingcasesPayload = {
    //                     searchId: selectedSearchResult.searchId,
    //                     criminalId: selectedSearchResult.criminalId,
    //                     // statusId: '2',
    //                     statusId: selectedStatus,
    //                     remark: remarks,
    //                     hitdataId: selectedSearchResult.hitId,
    //                     levelId: '4',
    //                     caseId: selectedSearchResult.caseId,
    //                     uid: '1', // Ensure 'userId' or a valid user ID is used here
    //                     criminalName: '',
    //                     matchingScore: '0' // You can set an appropriate value for matchingScore
    //                 };
    //                 // Assuming CreateCaseLifeCycleImplInsert is accessible from this scope

    //                 await authService.CreateCaseLifeCycleImplInsert(PindingcasesPayload);
    //                 // Handle the response if needed
    //             }
    //         }

    //         setIsRemarksDialogOpen(false);
    //     } catch (error) {
    //         console.error('Error submitting remarks:', error);
    //         // Handle any other errors that might occur
    //     }
    // }




    return (
        <>
            <Header />
            <Box m={4}>
                <Container style={{ maxWidth: 'none', backgroundColor: 'white', padding: "30px", margin: "10px" }}>
                    <Box m={4}>
                        {/* <Dialog open={isLevel2casedetailsOpen} fullWidth maxWidth="xl">
                            <DialogActions>
                                <Button onClick={() => setIsLevelcasedetailsOpen(false)} color="primary">
                                    <ClearIcon />
                                </Button>
                            </DialogActions> */}
                            <div className="d-flex justify-content-center" >
                                <div className="card" style={{ boxShadow: '1px 1px 1px grey', width: '100%', height: "100%" }}>
                                    <div className="card-body">


                                        <Grid container spacing={3} alignItems="center" justifyContent="center">

                                            <Grid item xs={12} sm={2}>
                                                <Button variant="contained" color="secondary" onClick={handlePendingAlertClick}>
                                                    Pending Case
                                                </Button>
                                            </Grid>



                                            <Grid item xs={12} sm={2}>
                                                <Button variant="contained" color="secondary" onClick={handlePendingRIFClick}>
                                                    Pending RIF
                                                </Button>
                                            </Grid>

                                        </Grid>
                                    </div>
                                </div>
                            </div>



                            {/* {isSearchTableVisible && ( */}
                            <div>



                                <div>
                                    <Grid container spacing={1} alignItems="center" justifyContent="center">
                                        {showPendingCaseTable && (

                                            <TableContainer component={Card} style={{ width: "85%", margin: "20px" }}>

                                                <Table size="small" aria-label="a dense table" style={{ margin: '0 auto' }}>
                                                    <TableHead sx={{ backgroundColor: '#cccdd1' }}>
                                                        <TableRow className="tableHeading">
                                                            <TableCell>S.No</TableCell>
                                                            <TableCell>Name</TableCell>
                                                            <TableCell>Matching Score</TableCell>
                                                            <TableCell>Remark</TableCell>
                                                            <TableCell>Action</TableCell>
                                                        </TableRow>
                                                    </TableHead>
                                                    <TableBody>
                                                        {pendingcase.slice(startIndex, endIndex).map((alert, index) => (

                                                            <TableRow

                                                            >
                                                                <TableCell>{index + 1}</TableCell>
                                                                <TableCell>{alert.criminalName}</TableCell>
                                                                <TableCell>{alert.matchingScore}</TableCell>
                                                                <TableCell>{alert.remark}</TableCell>
                                                                <TableCell>
                                                                    <IconButton onClick={() => handleIconClick(index)} style={{ padding: '1px 1px' }}>
                                                                        {selectedAction ? (
                                                                            <VisibilityOffIcon style={{ color: 'red' }} /> // Red color for 'VisibilityOffIcon'
                                                                        ) : (
                                                                            <VisibilityIcon style={{ color: 'green' }} /> // Green color for 'VisibilityIcon'
                                                                        )}
                                                                    </IconButton>
                                                                    {/* <IconButton onClick={() => handleIconClick(index)} style={{ padding: '1px 1px' }}>
                                                                        {selectedAction ? <VisibilityIcon /> : null}
                                                                    </IconButton> */}

                                                                    {/* {selectedAction && (
                                        <span>{getStatusName(selectedAction)}</span>
                                      )} */}
                                                                </TableCell>
                                                            </TableRow>
                                                        ))}
                                                    </TableBody>

                                                </Table>
                                                <div>
                                                    <TablePagination
                                                        rowsPerPageOptions={[5, 10, 20]}
                                                        component="div"
                                                        count={pendingcase.length}
                                                        page={page}
                                                        onPageChange={handlePageChange}
                                                        rowsPerPage={rowsPerPage}
                                                        onRowsPerPageChange={handleRowsPerPageChange}
                                                        style={{ marginLeft: "500px" }}
                                                    />
                                                </div>
                                            </TableContainer>
                                        )}


                                    </Grid>





                                </div>
                                <div>
                                    <Grid container spacing={1} alignItems="center" justifyContent="center">
                                        {showPendingRIFTable && (
                                            <TableContainer component={Card} style={{ width: "85%", margin: "20px" }}>
                                                <Table size="small" aria-label="a dense table" style={{ margin: '0 auto' }}>
                                                    <TableHead sx={{ backgroundColor: '#cccdd1' }}>
                                                        <TableRow className="tableHeading">
                                                            <TableCell>S.No</TableCell>
                                                            <TableCell>Name</TableCell>
                                                            <TableCell>Matching Score</TableCell>
                                                            <TableCell>Remark</TableCell>
                                                            <TableCell>Action</TableCell>
                                                        </TableRow>
                                                    </TableHead>
                                                    <TableBody>
                                                        {pendingRif.slice(startIndex, endIndex).map((alert, index) => (

                                                            <TableRow

                                                            >
                                                                <TableCell>{index + 1}</TableCell>
                                                                <TableCell>{alert.criminalName}</TableCell>
                                                                <TableCell>{alert.matchScore}</TableCell>
                                                                <TableCell>{alert.remark}</TableCell>
                                                                <TableCell>
                                                                    <IconButton onClick={() => handleIconClick(index)} style={{ padding: '1px 1px' }}>
                                                                        {selectedAction ? (
                                                                            <VisibilityOffIcon style={{ color: 'red' }} /> // Red color for 'VisibilityOffIcon'
                                                                        ) : (
                                                                            <VisibilityIcon style={{ color: 'green' }} /> // Green color for 'VisibilityIcon'
                                                                        )}
                                                                    </IconButton>
                                                                </TableCell>
                                                            </TableRow>
                                                        ))}
                                                    </TableBody>

                                                </Table>
                                                <div>
                                                    <TablePagination
                                                        rowsPerPageOptions={[5, 10, 20]}
                                                        component="div"
                                                        count={pendingRif.length}
                                                        page={page}
                                                        onPageChange={handlePageChange}
                                                        rowsPerPage={rowsPerPage}
                                                        onRowsPerPageChange={handleRowsPerPageChange}
                                                        style={{ marginLeft: "500px" }}
                                                    />
                                                </div>
                                            </TableContainer>
                                        )}


                                    </Grid>





                                </div>
                            </div>
                        {/* </Dialog> */}

                    </Box>
                </Container >

            </Box >
            <Dialog
                open={isRemarksDialogOpen}
                onClose={handleCloseRemarksDialog}
                fullWidth
                maxWidth="md"
            >
                <DialogActions>
                    <Button onClick={handleCloseRemarksDialog} color="primary">

                        <ClearIcon />
                    </Button>
                </DialogActions>
                <DialogTitle>Enter Remarks</DialogTitle>
                <DialogContentText style={{ textAlign: 'center' }}>
                    Select a status and enter remarks for this employee.
                </DialogContentText>
                <DialogContent>
                    <Grid container alignItems="center" justifyContent="center">
                        <Grid item xs={12} sm={3}>
                            <FormControl fullWidth variant="outlined" margin="dense">
                                <InputLabel>Status</InputLabel>
                                <Select
                                    label="Status"
                                    value={selectedStatus}
                                    onChange={handleStatusChange}
                                >
                                    <MenuItem value="">Select Status</MenuItem>
                                    {statusData.map((status) => (
                                        <MenuItem key={status.id} value={status.id}>
                                            {status.name}
                                        </MenuItem>
                                    ))}
                                </Select>
                            </FormControl>

                        </Grid>
                    </Grid>
                    {selectedStatus && (
                        <div>
                            <DialogContentText style={{ textAlign: 'center' }}>
                                Enter your remarks for this action.
                            </DialogContentText>

                            <Grid container alignItems="center" justifyContent="center">
                                <Grid item xs={12} sm={8}>
                                    <TextField
                                        autoFocus
                                        margin="dense"
                                        id="outlined-multiline-static"
                                        label="Remarks"
                                        type="text"
                                        fullWidth
                                        multiline
                                        rows={4}
                                        value={remarks}
                                        defaultValue="Default Value"
                                        onChange={handleRemarksChange}
                                    />

                                </Grid>
                            </Grid>

                        </div>
                    )}
                </DialogContent>
                <DialogActions>


                    <Button onClick={handleRemarksSubmit} color="primary">
                        Submit
                    </Button>
                </DialogActions>
            </Dialog>
            {/* <Dialog
                open={isRemarksDialogOpenRif}
                onClose={handleCloseRemarksDialogRif}
                fullWidth
                maxWidth="md"
            >
                <DialogActions>
                    <Button onClick={handleCloseRemarksDialogRif} color="primary">

                        <ClearIcon />
                    </Button>
                </DialogActions>
                <DialogTitle>Enter Remarks</DialogTitle>
                <DialogContentText style={{ textAlign: 'center' }}>
                    Select a status and enter remarks for this employee.
                </DialogContentText>
                <DialogContent>
                    <Grid container alignItems="center" justifyContent="center">
                        <Grid item xs={12} sm={3}>
                            <FormControl fullWidth variant="outlined" margin="dense">
                                <InputLabel>Status</InputLabel>
                                <Select
                                    label="Status"
                                    value={selectedStatus}
                                    onChange={handleStatusChangeRif}
                                >
                                    <MenuItem value="">Select Status</MenuItem>
                                    {statusData.map((status) => (
                                        <MenuItem key={status.id} value={status.id}>
                                            {status.name}
                                        </MenuItem>
                                    ))}
                                </Select>
                            </FormControl>

                        </Grid>
                    </Grid>
                    {selectedStatus && (
                        <div>
                            <DialogContentText style={{ textAlign: 'center' }}>
                                Enter your remarks for this action.
                            </DialogContentText>

                            <Grid container alignItems="center" justifyContent="center">
                                <Grid item xs={12} sm={8}>
                                    <TextField
                                        autoFocus
                                        margin="dense"
                                        id="outlined-multiline-static"
                                        label="Remarks"
                                        type="text"
                                        fullWidth
                                        multiline
                                        rows={4}
                                        value={remarks}
                                        defaultValue="Default Value"
                                        onChange={handleRemarksChangeRif}
                                    />

                                </Grid>
                            </Grid>

                        </div>
                    )}
                </DialogContent>
                <DialogActions>


                    <Button onClick={handleRemarksSubmitRif} color="primary">
                        Submit
                    </Button>
                </DialogActions>
            </Dialog> */}
            {/* <Dialog open={isDialogOpen} fullWidth maxWidth="xl">
                <DialogActions>
                    <Button
                        onClick={() => setIsDialogOpen(false)}
                        color="primary"
                    >
                        <ClearIcon />
                    </Button>
                </DialogActions>
                <DialogTitle>Employee Details</DialogTitle>
                <DialogContent>
                    {selectedEmployee && <SearchList employee={selectedEmployee} />}
                </DialogContent>
            </Dialog> */}

        </>
    );
}

export default Level3RIF;