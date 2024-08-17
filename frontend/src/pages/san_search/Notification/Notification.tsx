import React, { useEffect, useRef, useState } from 'react';
import {

  Table,
  TableBody,
  TableContainer,
  TableHead,
  TableRow,
  TableCell,
  IconButton
} from '@mui/material';
import { Box, TextField, Button, Grid, InputLabel, FormControl, Select, MenuItem, Paper, Typography, Dialog, DialogTitle, DialogContent, DialogActions, DialogContentText } from '@mui/material';

import { Card } from 'react-bootstrap';
import SearchApiService from '../../../data/services/san_search/search-api-service';
import { useLocation, useNavigate, useParams } from 'react-router-dom';
import Header from '../../../layouts/header/header';
import { Country, List, Program, All, Customer, CustomerRequest, Address, IdentificationData, AliasesData, PlaceOfBirthData, NationalityData, Birthdate, DetailsData, SearchDTO, RecordDTO, logicalIdentification, logicaAddress, LogicalDetails, Logicalcitiy, LogicalBirthDetails, LogicalAKADetails, GroupAliases, GroupIdentification, CityDetails, UnDetails, UnAliases, UnDesignationDetails } from '../../../data/services/san_search/viewpage/view_payload';
import ViewService from '../../../data/services/san_search/viewpage/view_api_service';
import { Timeline, TimelineItem, TimelineContent, TimelineDot, TimelineSeparator, TimelineConnector } from '@mui/lab';
import { useSelector } from 'react-redux';
import HitdatalifecycleApiService from '../../../data/services/san_search/hitdatalifecycle/hitdatalifecycle-api-service';
import HitcaseApiService from '../../../data/services/san_search/hitcase/hitcase-api-service';
import { SelectChangeEvent } from '@mui/material/Select';
import PrintIcon from '@mui/icons-material/Print';
import VisibilityIcon from '@mui/icons-material/Visibility';
import VisibilityOffIcon from '@mui/icons-material/VisibilityOff';
import ClearIcon from '@mui/icons-material/Clear';
import { useReactToPrint } from 'react-to-print'; // Example import, adjust based on your actual library
import * as XLSX from 'xlsx';
import FileDownloadIcon from '@mui/icons-material/FileDownload';


interface Notification {
  id: number;
  name: string;
  createdAt: string;
  matchingScore: number;
}

interface Levelpending {
  name: string;
  matching_score: number;
  hitName: string;
  hitScore: number;
  hitId: string;
  recId: number;
  searchId: string;
  lifcycleSearchId: string;
  fileType: number;
}
interface Status {
  id: string;
  name: string;
  // Add other properties if necessary
}

interface DisabledIcons {
  [key: string]: boolean;
}
const NotificationComponent: React.FC = () => {
  const userDetails = useSelector((state: any) => state.loginReducer);
  const userFirstName = userDetails.userData?.firstName;
  const loginDetails = userDetails.loginDetails;

  const location = useLocation();
  const { id } = useParams();
  const navigate = useNavigate();
  const cardRef = useRef<HTMLDivElement | null>(null);
  const [notifications, setNotifications] = useState<Notification[]>([]);
  const [selectedNotification, setSelectedNotification] = useState<number | null>(null);

  const [levelpending, setLevelpending] = useState<Levelpending[]>([]);
  const [error, setError] = useState<string | null>(null);
  const [formData, setFormData] = useState<RecordDTO>({
    ids: 0,
    name: '',
    address: '',
    program: '',
    entityType: '',
    list: '',
    score: 0,
    fileType: 0,
    fileList: '',
    criminalId: '',
    searchId: '',
    hitId: '',
    nationality: '',
    citizenship: '',
    passport: '',
    Country: '',
  });

  const [countryData, setcountryData] = useState<Customer>({
    id: 0,
    city: '',
    State: '',
  });

  const [RecordType, setRecordType] = useState<All[]>([
  ]);

  const viewservice = new ViewService();
  const [selectedRecordType, setSelectedRecordType] = useState(0);
  const [Program, setProgram] = useState<Program[]>([]);
  const [selectedProgram, setSelectedProgram] = useState<string>('');
  const [List, setList] = useState<List[]>([]);
  const [selectedList, setSelectedList] = useState(0);
  const [country, setCountry] = useState<Country[]>([]);
  const [selectedCountry, setSelectedCountry] = useState(0);
  const [filteredData, setFilteredData] = useState<RecordDTO[]>([]);
  const [filteredDatas, setFilteredDatas] = useState<Levelpending[]>([]);

  const [searchError, setSearchError] = useState<boolean>(false);
  const [sliderValue, setSliderValue] = useState<number>(100);
  const [data, setData] = useState<RecordDTO[]>([
  ]);
  const [address, setaddress] = useState<Address[]>([
  ]);
  const [identification, setIdentification] = useState<IdentificationData[]>([
  ]);
  const [aliases, setAliases] = useState<AliasesData[]>([
  ]);
  const [details, setdetails] = useState<DetailsData[]>([
  ]);
  const [sortedColumn, setSortedColumn] = useState<string>('');
  const [sortDirection, setSortDirection] = useState<'asc' | 'desc'>('asc');
  const [showModal, setShowModal] = useState(false);
  const [showModallogical, setShowModallogical] = useState(false);
  const [showModalgroup, setShowModalgroup] = useState(false);
  const [showModalun, setShowModalun] = useState(false);

  const [selectedSearchDetails, setSelectedSearchDetails] = useState<string>(''); // Initialize with an appropriate default value
  const [logicaldetails, setLogicaldetails] = useState<LogicalDetails[]>([
  ]);
  const [logicalcitiy, setLogicalcitiy] = useState<Logicalcitiy[]>([
  ]);
  const [logicalBirthDetails, setLogicalBirthDetails] = useState<LogicalBirthDetails[]>([
  ]);
  const [logicalidentification, setLogicalIdentification] = useState<logicalIdentification[]>([
  ]);
  const [logicalAddress, setLogicalAddress] = useState<logicaAddress[]>([
  ]);
  const [logicalAka, setLogicalAka] = useState<LogicalAKADetails[]>([
  ]);
  const [Groupaliases, setGroupaliases] = useState<GroupAliases[]>([
  ]);
  const [CityDetails, setCityDetails] = useState<CityDetails[]>([]);
  const [groupidentification, setGroupIdentification] = useState<GroupIdentification[]>([
  ]);
  const [UnDetails, setUnDetails] = useState<UnDetails[]>([]);
  const [Unaliases, setUnaliases] = useState<UnAliases[]>([
  ]);
  const [UnDesignationDetails, setUnDesignationDetails] = useState<UnDesignationDetails[]>([
  ]);
  const [statusData, setStatusData] = useState<Status[]>([]);
  const [selectedStatuses, setSelectedStatuses] = useState<string[]>([]);

  const [dialogOpen, setDialogOpen] = useState<boolean>(false);
  // const [selectedActions, setSelectedActions] = useState<{ [key: number]: string }>({});
  // const [remarksAndActions, setRemarksAndActions] = useState<{ action: string; remarks: string }[]>([]);

  // const [selectedStatus, setSelectedStatus] = useState<string>('');
  // const [selectedAction, setSelectedAction] = useState<string | null>(null);
  // const [selectedCourierTracker, setSelectedCourierTracker] = useState<any | null>(null); // State to store the selected courier tracker
  // const [isRemarksDialogOpen, setIsRemarksDialogOpen] = useState(false);
  // const [remarks, setRemarks] = useState('');
  // const [selectedRow, setSelectedRow] = useState<number | null>(null); // Initialize with null or -1 if no row is selected
  // const [searchResults, setSearchResults] = useState<any[]>([]);
  // const [page, setPage] = useState(0);
  // const [rowsPerPage, setRowsPerPage] = useState(10);
  const [selectedActions, setSelectedActions] = useState<{ [key: string]: string }>({});
  const [remarksAndActions, setRemarksAndActions] = useState<{ [key: string]: { action: string; remarks: string } }>({});

  const [selectedStatus, setSelectedStatus] = useState<string>('');
  const [selectedAction, setSelectedAction] = useState<string | null>(null);
  const [selectedCourierTracker, setSelectedCourierTracker] = useState<any | null>(null); // State to store the selected courier tracker
  const [isRemarksDialogOpen, setIsRemarksDialogOpen] = useState(false);
  const [remarks, setRemarks] = useState('');
  const [selectedRow, setSelectedRow] = useState<string | null>(null); // Initialize with null
  const [searchResults, setSearchResults] = useState<any[]>([]);
  const [page, setPage] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(10);


  useEffect(() => {
    fetchNotifications();
    fetchCountry();
    fetchList();
    fetchProgram();
    fetchAll();
    fetchAddresses();
    fetchIdentification();
    fetchAliases();
    fetchDetails();
    fetchiden();
    fetchaddress();
    fetchadetails();
    fetchacitiy();
    fetchBirthDetails();
    fetchAka();
    fetchaliase();
    fetchanationalid();
    fetchCityDetails();
    fetchUnDetails();
    fetchunaliase();
    fetchundesingation();
    fetchStatus();

  }, [id]);
  useEffect(() => {
    const handleKeyDown = (event: { key: any; }) => {
        if (!cardRef.current) return;
        const { key } = event;
        const element = cardRef.current;
        if (key === 'ArrowUp') {
            element.scrollTop -= 50;
        } else if (key === 'ArrowDown') {
            element.scrollTop += 50;
        }
    };
    document.addEventListener('keydown', handleKeyDown);
    return () => {
        document.removeEventListener('keydown', handleKeyDown);
    };
}, []);


  const fetchNotifications = async () => {
    try {
      const notifications = await authApiService.getSearch();
      setNotifications(notifications);
    } catch (error) {
      console.error("Error fetching notifications:", error);
    }
  };

  const fetchLevelpending = async (id: number) => {
    try {
      const levelpending = await authApiService.getLevelpending(id);
      setLevelpending(levelpending);
    } catch (error) {
      console.error("Error fetching the details:", error);
      setError("Error fetching the details");
    }
  };

  const handleNotificationClick = async (id: number) => {
    if (selectedNotification === id) {
      setSelectedNotification(null); // Close the table if the same notification is clicked
    } else {
      setSelectedNotification(id); // Open the table for the clicked notification
      await fetchLevelpending(id); // Fetch the levelpending data
    }
  };
  const fetchCountry = async () => {
    try {
      const countryData = await viewservice.getCountryList();
      setCountry(countryData);
    } catch (error) {
      console.error("Error fetching country list:", error);
    }
  };

  const fetchList = async () => {
    try {
      const ListData = await viewservice.getList();
      setList(ListData);
    } catch (error) {
      console.error("Error fetching country list:", error);
    }
  };

  const fetchProgram = async () => {
    try {
      const ProgramData = await viewservice.getProgram();
      setProgram(ProgramData);
    } catch (error) {
      console.error("Error fetching country list:", error);
    }
  };

  const fetchAll = async () => {
    try {
      const AllData = await viewservice.getAll();
      setRecordType(AllData);
    } catch (error) {
      console.error("Error fetching country list:", error);
    }
  };

  const fetchAddresses = async () => {
    try {
      const Address = await viewservice.getAddresses(id);
      setaddress(Address);
    } catch (error) {
      console.error("Error fetching country list:", error);
    }
  };

  const fetchAliases = async () => {
    try {
      const aliases = await viewservice.getAliases(id);
      setAliases(aliases);
    } catch (error) {
      console.error("Error fetching aliases list:", error);
    }
  };

  const fetchIdentification = async () => {
    try {
      const identification = await viewservice.getIdentification(id);
      setIdentification(identification);
    } catch (error) {
      console.error("Error fetching country list:", error);
    }
  };

  const fetchDetails = async () => {
    try {
      const details = await viewservice.getDetails(id);
      setdetails(details);
    } catch (error) {
      console.error("Error fetching the details:", error);
    }
  };
  const fetchiden = async () => {
    try {
      const Address = await viewservice.getLogicalIdentification(id);
      setLogicalIdentification(Address);
    } catch (error) {
      console.error("Error fetching country list:", error);
    }
  };
  const fetchaddress = async () => {
    try {
      const Address = await viewservice.getLogicalAddress(id);
      setLogicalAddress(Address);
    } catch (error) {
      console.error("Error fetching country list:", error);
    }
  };
  const fetchadetails = async () => {
    try {
      const Address = await viewservice.getLogicaldetails(id);
      setLogicaldetails(Address);
    } catch (error) {
      console.error("Error fetching country list:", error);
    }
  };
  const fetchacitiy = async () => {
    try {
      const Address = await viewservice.getLogicalcity(id);
      setLogicalcitiy(Address);
    } catch (error) {
      console.error("Error fetching country list:", error);
    }
  };
  const fetchBirthDetails = async () => {
    try {
      const Address = await viewservice.getLogicalBirthDetails(id);
      setLogicalBirthDetails(Address);
    } catch (error) {
      console.error("Error fetching country list:", error);
    }
  };
  const fetchAka = async () => {
    try {
      const Address = await viewservice.getLogicalAka(id);
      setLogicalAka(Address);
    } catch (error) {
      console.error("Error fetching country list:", error);
    }
  };

  const fetchaliase = async () => {
    try {
      const Groupaliases = await viewservice.getGroupAliases(id);
      setGroupaliases(Groupaliases);
    } catch (error) {
      console.error("Error fetching country list:", error);
    }
  };
  const fetchCityDetails = async () => {
    try {
      const CityDetails = await viewservice.getGroupCityDetails(id);
      setCityDetails(CityDetails);
    } catch (error) {
      console.error("Error fetching country list:", error);
    }
  };
  const fetchanationalid = async () => {
    try {
      const groupidentification = await viewservice.getGroupIdentification(id);
      setGroupIdentification(groupidentification);
    } catch (error) {
      console.error("Error fetching country list:", error);
    }
  };
  const fetchUnDetails = async () => {
    try {
      const UnDetails = await viewservice.getUnDetails(id);
      setUnDetails([UnDetails]);
      console.log('UnDetails:', UnDetails); // Log data to check structure
    } catch (error) {
      console.error("Error fetching country list:", error);
    }
  };
  const fetchunaliase = async () => {
    try {
      const Unaliases = await viewservice.getUnAliases(id);
      setUnaliases(Unaliases);
    } catch (error) {
      console.error("Error fetching country list:", error);
    }
  };

  const fetchundesingation = async () => {
    try {
      const UnDesignationDetails = await viewservice.getUnDesignationDetailss(id);
      setUnDesignationDetails(UnDesignationDetails);
    } catch (error) {
      console.error("Error fetching country list:", error);
    }
  };

  const handleRecordTypeChange = (event: SelectChangeEvent<number>) => {
    const value = event.target.value;
    setSelectedRecordType(typeof value === 'string' ? parseInt(value) : value);
  };

  const handleListChange = (event: SelectChangeEvent<number>) => {
    const value = event.target.value;
    setSelectedList(typeof value === 'string' ? parseInt(value) : value);
  };

  const handleCountryChange = (event: SelectChangeEvent<number>) => {
    const value = event.target.value;
    setSelectedCountry(typeof value === 'string' ? parseInt(value) : value);
  };
  const tableRef = useRef<HTMLDivElement>(null); // Assuming tableRef is used to reference a <div> in your JSX

  // const handlePrint = () => {
  //   try {
  //     if (tableRef.current) {
  //       const content = tableRef.current.innerHTML;
  //       const printWindow = window.open('', '_blank');
  //       if (printWindow) {
  //         printWindow.document.open();
  //         printWindow.document.write(`
  //           <html>
  //             <head>
  //               <title>Print Document</title>
  //               <style>
  //                 @media print {
  //                   body {
  //                     padding: 1rem;
  //                   }
  //                 }
  //               </style>
  //             </head>
  //             <body>
  //               ${content}
  //             </body>
  //           </html>
  //         `);
  //         printWindow.document.close();
  //         printWindow.print();
  //       } else {
  //         console.error("Failed to open print window.");
  //       }
  //     } else {
  //       console.error("Table container reference (tableRef) not found.");
  //     }
  //   } catch (error) {
  //     console.error("Error handling print:", error);
  //   }
  // };
  const myRef = useRef(null);


  //   const handlePrint = useReactToPrint({
  //     content: () => myRef.current
  //   });
  const handlePrint = useReactToPrint({
    content: () => myRef.current,
    pageStyle: `
    @page {
      margin-left: 20mm; /* Adjust this value as per your requirement */
    }
    body {
      margin: 0;
    }
  `,
  });

  const exportToExcel = () => {
    try {
      const dataForExport = notifications.length > 0 ? notifications.map((row) => ({
        // Id: row.,
        Name: row.name,
        MatchingScore: row.matchingScore,
        CreatedAt: row.createdAt,
      

      })) : [{ Message: "Your search has not returned any results." }];

      const workbook = XLSX.utils.book_new();
      const worksheet = XLSX.utils.json_to_sheet(dataForExport);
      XLSX.utils.book_append_sheet(workbook, worksheet, "Lookup Results");
      XLSX.writeFile(workbook, "lookup_results.xlsx");
    } catch (error) {
      console.error("Error exporting data to Excel:", error);
    }
  };



  const [loading, setLoading] = useState(false);


  const handleTableRowClick = async (id: number, fileType: number, index: number, searchId: string, recId: string) => {

    console.log('Clicked row id:', id); // Check if id is correctly logged
    // const key = `${searchId}-${recId}-${index}`;
    // if (disabledIcons[key]) {
    //   return; // Exit the function if the span is disabled
    // }
    if (fileType === 1) {
      setShowModal(true);
      console.log("Clicked icon at row:", index);
      console.log("Search ID:", searchId);
      console.log("Record ID:", recId);

      const currentIndex = `${searchId}-${recId}-${index}`;
      const existingAction = selectedActions[currentIndex] || '';
      const existingRemarks = remarksAndActions[currentIndex]?.remarks || '';

      setSelectedStatus(existingAction);
      setRemarks(existingRemarks);
      setSelectedRow(currentIndex);
      // setIsRemarksDialogOpen(true);

      try {
        setLoading(true);


        const detailsData = await viewservice.getDetails(id);
        setdetails(detailsData);

        const identificationData = await viewservice.getIdentification(id);
        setIdentification(identificationData);

        const aliasesData = await viewservice.getAliases(id);
        setAliases(aliasesData);

        const addressData = await viewservice.getAddresses(id);
        setaddress(addressData);


      } catch (error) {
        console.error("Error fetching details:", error);
      }

      setLoading(false);
    } else if (fileType === 2) {
      setShowModallogical(true);
      console.log("Clicked icon at row:", index);
      console.log("Search ID:", searchId);
      console.log("Record ID:", recId);

      const currentIndex = `${searchId}-${recId}-${index}`;
      const existingAction = selectedActions[currentIndex] || '';
      const existingRemarks = remarksAndActions[currentIndex]?.remarks || '';

      setSelectedStatus(existingAction);
      setRemarks(existingRemarks);
      setSelectedRow(currentIndex);



      try {
        setLoading(true);

        // Fetch detailed information based on selected ID
        const logicalidentification = await viewservice.getLogicalIdentification(id);
        setLogicalIdentification(logicalidentification);

        const logicalAddress = await viewservice.getLogicalAddress(id);
        setLogicalAddress(logicalAddress);

        const logicaldetails = await viewservice.getLogicaldetails(id);
        setLogicaldetails(logicaldetails);

        const logicalcitiy = await viewservice.getLogicalcity(id);
        setLogicalcitiy(logicalcitiy);
        const logicalBirthDetails = await viewservice.getLogicalBirthDetails(id);
        setLogicalBirthDetails(logicalBirthDetails);
        const logicalAka = await viewservice.getLogicalAka(id);
        setLogicalAka(logicalAka);

      } catch (error) {
        console.error("Error fetching details:", error);
      }
      setLoading(false);

    } else if (fileType === 3) {

      setShowModalgroup(true);
      console.log("Clicked icon at row:", index);
      console.log("Search ID:", searchId);
      console.log("Record ID:", recId);

      const currentIndex = `${searchId}-${recId}-${index}`;
      const existingAction = selectedActions[currentIndex] || '';
      const existingRemarks = remarksAndActions[currentIndex]?.remarks || '';

      setSelectedStatus(existingAction);
      setRemarks(existingRemarks);
      setSelectedRow(currentIndex);

      try {
        setLoading(true);
        // Fetch detailed information based on selected ID
        const Groupaliases = await viewservice.getGroupAliases(id);
        setGroupaliases(Groupaliases);

        const CityDetails = await viewservice.getGroupCityDetails(id);
        setCityDetails(CityDetails);
        const groupidentification = await viewservice.getGroupIdentification(id);
        setGroupIdentification(groupidentification);

      } catch (error) {
        console.error("Error fetching details:", error);
      }
      setLoading(false);
    }
    else if (fileType === 4) {

      setShowModalun(true);
      console.log("Clicked icon at row:", index);
      console.log("Search ID:", searchId);
      console.log("Record ID:", recId);

      const currentIndex = `${searchId}-${recId}-${index}`;
      const existingAction = selectedActions[currentIndex] || '';
      const existingRemarks = remarksAndActions[currentIndex]?.remarks || '';

      setSelectedStatus(existingAction);
      setRemarks(existingRemarks);
      setSelectedRow(currentIndex);


      try {
        setLoading(true);
        // Fetch detailed information based on selected ID
        const UnDetails = await viewservice.getUnDetails(id);
        setUnDetails([UnDetails]);

        const Unaliases = await viewservice.getUnAliases(id);
        setUnaliases(Unaliases);
        const UnDesignationDetails = await viewservice.getUnDesignationDetailss(id);
        setUnDesignationDetails(UnDesignationDetails);

      } catch (error) {
        console.error("Error fetching details:", error);
      }
    }
    setLoading(false);
    // setDisabledIcons((prev) => ({
    //   ...prev,
    //   [key]: true,
    // }));
  }



  const handleCloseModal = () => {
    setShowModal(false);

  };
  const handleCloseModallogical = () => {
    setShowModallogical(false);

  };
  const handleCloseModalgroup = () => {
    setShowModalgroup(false);

  };
  const handleCloseModalun = () => {
    setShowModalun(false);

  };
  const fetchStatus = async () => {
    try {
      const statuses: Status[] = await authApiService.getStatus(); // Specify the type as Status[]

      // Filter the statuses to keep only "close" and "Escalation" (matching the actual case)
      const filteredStatuses = statuses.filter((status: Status) => {
        return status.name === "close" || status.name === "Escalation";
      });

      console.log(filteredStatuses); // Add this line to check the filtered statuses
      setStatusData(filteredStatuses); // Update the statusData state with the filtered results
    } catch (error) {
      console.error("Error fetching statuses:", error);
    }
  };
  const handleCloseRemarksDialog = () => {
    console.log('Closing remarks dialog.');

    setIsRemarksDialogOpen(false);
    setSelectedAction(null);
    setRemarks('');
  };


  const handleStatusChange = (event: SelectChangeEvent<string>) => {
    setSelectedStatus(event.target.value);
  };

  const handleRemarksChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setRemarks(event.target.value);
  };

  // const handleIconClick = (index: number, searchId: string, recId: string) => {
  //   console.log("Clicked icon at row:", index);
  //   console.log("Search ID:", searchId);
  //   console.log("Record ID:", recId);

  //   const currentIndex = page + index;
  //   const existingAction = selectedActions[currentIndex] || '';
  //   const existingRemarks = remarksAndActions[currentIndex]?.remarks || '';

  //   setSelectedStatus(existingAction);
  //   setRemarks(existingRemarks);
  //   setSelectedRow(currentIndex);
  //   setIsRemarksDialogOpen(true);
  // };
  const [disabledIcons, setDisabledIcons] = useState<DisabledIcons>({});

  const handleIconClick = (index: number, searchId: string, recId: string) => {
    console.log("Clicked icon at row:", index);
    console.log("Search ID:", searchId);
    console.log("Record ID:", recId);

    const currentIndex = `${searchId}-${recId}-${index}`;
    const existingAction = selectedActions[currentIndex] || '';
    const existingRemarks = remarksAndActions[currentIndex]?.remarks || '';

    setSelectedStatus(existingAction);
    setRemarks(existingRemarks);
    setSelectedRow(currentIndex);
    setIsRemarksDialogOpen(true);
  };



  const getStatusName = (action: string) => {
    const status = statusData.find((status) => status.id === action);

    if (status) {
      // Define a mapping from status ID to CSS class
      const statusClassMap: { [key: string]: string } = {
        '1': 'green-text', // Assuming '1' corresponds to 'Closed'
        '2': 'red-text',   // Assuming '2' corresponds to 'Escalation'
        '3': 'yellow-text', // Assuming '3' corresponds to 'Request For Information'
      };

      const statusClass = statusClassMap[status.id];

      if (statusClass) {
        return (
          <span className={statusClass}>
            {status.name}
          </span>
        );
      } else {
        // If the status ID doesn't match any of the defined classes, return the status name as is
        return status.name;
      }
    } else {
      return ''; // Handle cases where the status is not found
    }
  };

  const handleRemarksSubmit = async () => {
    try {
      if (selectedRow !== null && levelpending.some(record => `${record.searchId}-${record.recId}-${levelpending.indexOf(record)}` === selectedRow)) {
        const updatedRemarksAndActions = { ...remarksAndActions };
        updatedRemarksAndActions[selectedRow] = { action: selectedStatus, remarks };

        setRemarksAndActions(updatedRemarksAndActions);

        const selectedSearchResult = levelpending.find(record => `${record.searchId}-${record.recId}-${levelpending.indexOf(record)}` === selectedRow);

        if (!selectedSearchResult) {
          console.error("Selected search result is undefined");
          return;
        }

        const hitdatalifecyclePayload = {
          searchId: selectedSearchResult.searchId,
          criminalId: selectedSearchResult.recId.toString(),
          statusId: selectedStatus,
          remark: remarks,
          hitId: selectedSearchResult.hitId,
          levelId: '1',
          caseId: '0',
          uid: loginDetails.id,
        };

        const hitcasePayload = {
          display: '-',
          searchId: selectedSearchResult.searchId,
          hitId: selectedSearchResult.hitId,
          criminalId: selectedSearchResult.recId.toString(),
          levelId: '1',
          statusNowId: selectedStatus,
          cycleId: '1',
          remark: remarks,
          uid: loginDetails.id,
        };

        console.log("hitdatalifecycle Payload:", hitdatalifecyclePayload);
        console.log("hitCasePayload:", hitcasePayload);

        if (parseInt(selectedStatus) == 1) {
          await hitdatalifecycleApiService.CreateHitdatalifecycle(hitdatalifecyclePayload);
        } else if (parseInt(selectedStatus) == 2) {
          await hitcaseApiService.CreateHitCaseInsert(hitcasePayload);
        }

        setSelectedActions({
          ...selectedActions,
          [selectedRow]: selectedStatus,
        });

        setDisabledIcons({
          ...disabledIcons,
          [selectedRow]: true,
        });

        setIsRemarksDialogOpen(false);
      } else {
        console.error("Selected row is null, invalid, or out of bounds");
      }
    } catch (error) {
      console.error("Error submitting remarks:", error);
    }
    handleCloseModal();
    handleCloseModallogical();
    handleCloseModalgroup();
    handleCloseModalun();
  };
  const authApiService = new SearchApiService();
  const hitdatalifecycleApiService = new HitdatalifecycleApiService();
  const hitcaseApiService = new HitcaseApiService();

  return (
    <>

      <Box sx={{ display: 'flex' }}>
        <Header />
        <Box component="main" sx={{ flexGrow: 1, p: 3 }}>
          <Box m={2} style={{ marginTop: '5%' }}>
            <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginLeft: '95%' }}>

              <IconButton

                color="primary"

                onClick={exportToExcel}
                style={{ minWidth: 'unset', padding: '2px' }}
              >
                <FileDownloadIcon />
              </IconButton>

            </div>
            <Card style={{ padding: '1%', boxShadow: 'rgb(0 0 0 / 28%) 0px 4px 8px', width: '100%' }}>
              {notifications && notifications.length > 0 ? (
                <>
                  <TableContainer style={{ maxHeight: '600px', overflow: 'auto' }}>
                    <Table size="small" aria-label="a dense table" style={{ margin: '0 auto' }}>
                      <TableHead>
                        <TableRow>
                          <TableCell style={{ position: 'sticky', top: 0, backgroundColor: 'white' }}>S.No</TableCell>
                          <TableCell style={{ position: 'sticky', top: 0, backgroundColor: 'white' }}>Name</TableCell>
                          <TableCell style={{ position: 'sticky', top: 0, backgroundColor: 'white' }}>MatchingScore</TableCell>
                          <TableCell style={{ position: 'sticky', top: 0, backgroundColor: 'white' }}>Created At</TableCell>

                        </TableRow>
                      </TableHead>
                      <TableBody>
                        {notifications.map((notification, index) => (
                          <React.Fragment key={notification.id}>
                            <TableRow>
                            <TableCell>{index + 1}</TableCell> 
                            <TableCell>
                                <span style={{ cursor: 'pointer', color: 'blue', textDecoration: 'underline' }} onClick={() => handleNotificationClick(notification.id)}>{notification.name}</span>
                              </TableCell>
                              <TableCell>{(notification.matchingScore)}</TableCell>

                              <TableCell>{new Date(notification.createdAt).toLocaleDateString()}</TableCell>
                            </TableRow>
                            {selectedNotification === notification.id && (
                              <TableRow >
                                <TableCell colSpan={2}>
                                  <div style={{ display: 'flex', justifyContent: 'center', marginLeft: '20%' }}>
                                    {error ? (
                                      <Typography variant="body2" color="error">{error}</Typography>
                                    ) : (
                                      levelpending.length > 0 ? (
                                        <Card style={{ padding: '1%', boxShadow: 'rgb(0 0 0 / 28%) 0px 4px 8px', width: '100%' }}>

                                          <TableContainer style={{ maxHeight: '400px', overflow: 'auto' }}>
                                            <Table size="small" aria-label="a dense table" style={{ margin: '0 auto' }}>
                                              <TableHead>
                                                <TableRow>
                                                  <TableCell style={{ position: 'sticky', top: 0, backgroundColor: 'white' }}>Hit Name</TableCell>
                                                  <TableCell style={{ position: 'sticky', top: 0, backgroundColor: 'white' }}>Hit Score</TableCell>
                                                  <TableCell style={{ position: 'sticky', top: 0, backgroundColor: 'white' }}>Action</TableCell>
                                                </TableRow>
                                              </TableHead>
                                              <TableBody>
                                                {levelpending.map((record, index) => {
                                                  const currentIndex = `${record.searchId}-${record.recId}-${index}`;
                                                  const selectedAction = selectedActions[currentIndex];
                                                  return (
                                                    <React.Fragment key={record.recId}>
                                                      <TableRow>

                                                        <TableCell>
                                                          <button
                                                            style={{
                                                              // cursor: 'pointer',
                                                              color: 'blue',
                                                              textDecoration: 'underline',
                                                              border: '0px solid blue',  // Example border style
                                                              backgroundColor: 'white'
                                                            }}
                                                            onClick={() =>
                                                              handleTableRowClick(
                                                                record.recId,
                                                                record.fileType,
                                                                index,
                                                                record.searchId.toString(),
                                                                record.recId.toString()
                                                              )
                                                            }
                                                            disabled={disabledIcons[`${record.searchId}-${record.recId}-${index}`]}

                                                          >
                                                            {record.hitName}
                                                          </button>
                                                        </TableCell>


                                                        <TableCell>{record.hitScore}</TableCell>
                                                        <TableCell>
                                                          <IconButton onClick={() => handleIconClick(index, record.searchId, record.recId.toString())} style={{ padding: '1px 1px' }}
                                                            disabled={disabledIcons[`${record.searchId}-${record.recId}-${index}`]}
                                                          >
                                                            {selectedAction ? (
                                                              <VisibilityOffIcon style={{ color: 'red' }} />
                                                            ) : (
                                                              <VisibilityIcon style={{ color: 'green' }} />
                                                            )}
                                                          </IconButton>
                                                          {selectedAction && <span>{getStatusName(selectedAction)}</span>}
                                                        </TableCell>
                                                      </TableRow>
                                                    </React.Fragment>
                                                  );
                                                })}
                                              </TableBody>
                                            </Table>
                                          </TableContainer>
                                        </Card>
                                      ) : (
                                        <Typography variant="body2">No Pending data available</Typography>
                                      )
                                    )}
                                  </div>
                                </TableCell>
                              </TableRow>
                            )}
                          </React.Fragment>
                        ))}
                      </TableBody>
                    </Table>
                  </TableContainer>
                </>
              ) : (
                <Typography variant="body1">No notifications available</Typography>
              )}
            </Card>

          </Box>
        </Box>
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
        {/* {selectedCourierTracker && (

          <Timeline position="left" style={{ marginRight: '50%' }}>
            <TimelineItem>
              <TimelineContent>{selectedCourierTracker.name}</TimelineContent>

              <TimelineSeparator>
                <TimelineDot style={{ background: 'blue' }} />
                <TimelineConnector style={{ background: 'blue' }} />
              </TimelineSeparator>
              <TimelineContent color="text.secondary">Name</TimelineContent>
            </TimelineItem>
            <TimelineItem>
              <TimelineContent>{selectedCourierTracker.matchingScore}</TimelineContent>

              <TimelineSeparator>
                <TimelineDot style={{ background: 'blue' }} />
                <TimelineConnector style={{ background: 'blue' }} />
              </TimelineSeparator>
              <TimelineContent color="text.secondary">Matching Score</TimelineContent>
            </TimelineItem>
            <TimelineItem>
              <TimelineContent>{selectedCourierTracker.hitId}</TimelineContent>
              <TimelineSeparator>
                <TimelineDot style={{ background: 'blue' }} />
                <TimelineConnector style={{ background: 'blue' }} />
              </TimelineSeparator>
              <TimelineContent color="text.secondary">hitId</TimelineContent>
            </TimelineItem>
            <TimelineItem>
              <TimelineContent>{selectedCourierTracker.searchId}</TimelineContent>
              <TimelineSeparator>
                <TimelineDot style={{ background: 'blue' }} />
                <TimelineConnector style={{ background: 'blue' }} />
              </TimelineSeparator>
              <TimelineContent color="text.secondary">searchId</TimelineContent>
            </TimelineItem>
            <TimelineItem>
              <TimelineContent>{selectedCourierTracker.remark}</TimelineContent>
              <TimelineSeparator >
                <TimelineDot style={{ backgroundColor: 'green' }} />
              </TimelineSeparator>
              <TimelineContent color="text.secondary">Remark</TimelineContent>
            </TimelineItem>
          </Timeline>
        )} */}

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

          {selectedStatus && (
            <Button onClick={handleRemarksSubmit} color="primary">
              Submit
            </Button>
          )}
        </DialogActions>
      </Dialog>
      <Dialog open={showModal} onClose={handleCloseModal} fullWidth
        maxWidth="lg">

        <DialogContent >
          {loading ? (
            <p>Loading...</p>
          ) : (
            <>
              <Box m={2} >
                <div style={{ display: 'flex', justifyContent: 'flex-end', alignItems: 'center' }}>
                  {/* <h4>DETAILS:</h4> */}

                  <IconButton
                    color="primary"
                    onClick={handlePrint}
                    className="print-hide"
                    style={{ minWidth: 'unset', padding: '2px' }}
                  >
                    <PrintIcon />
                  </IconButton>
                </div>
                <Card ref={cardRef} style={{ padding: '1%', boxShadow: 'rgb(0 0 0 / 28%) 0px 4px 8px', width: '100%', overflowY: 'auto', maxHeight: '500px' }}>

                  <div className="card-body" >

                    <div ref={myRef}>
                      <h5>DETAILS:</h5>
                      <Card style={{ padding: '1%', boxShadow: 'rgb(0 0 0 / 28%) 0px 4px 8px', width: '100%' }}>


                        <Grid container spacing={2} justifyContent="space-between">

                          <Grid item xs={3}>
                            {details.slice(0, Math.ceil(details.length / 3)).map((details, index) => (
                              <p key={index}><b>{details.heading} {details.heading.includes(':') ? '' : ':'}</b> {details.val}</p>
                            ))}
                          </Grid>
                          {/* Middle */}
                          <Grid item xs={3}>
                            {details.slice(Math.ceil(details.length / 3), Math.ceil(2 * details.length / 3)).map((details, index) => (
                              <p key={index}><b>{details.heading} {details.heading.includes(':') ? '' : ':'}</b> {details.val}</p>
                            ))}
                          </Grid>
                          {/* Right side */}
                          <Grid item xs={3}>
                            {details.slice(Math.ceil(2 * details.length / 3)).map((details, index) => (
                              <p key={index}><b>{details.heading} {details.heading.includes(':') ? '' : ':'}</b> {details.val}</p>
                            ))}
                          </Grid>
                        </Grid>
                      </Card>
                      <br />
                      {identification.length > 0 && (
                        <>
                          <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }} >
                            <h4>IDENTIFICATIONS:</h4>
                          </div>
                          <Card style={{ padding: '1%', boxShadow: 'rgb(0 0 0 / 28%) 0px 4px 8px', width: '100%' }}>
                            <Grid item xs={12}>
                              <TableContainer>
                                <Table size="small" aria-label="a dense table" style={{ margin: '0 auto' }}>
                                  <TableHead>
                                    <TableRow>
                                      <TableCell>Type</TableCell>
                                      <TableCell>ID</TableCell>

                                      <TableCell>Country</TableCell>
                                      <TableCell>Issue Date </TableCell>
                                      {/* <TableCell>Expire Date</TableCell> */}
                                    </TableRow>
                                  </TableHead>
                                  <TableBody>
                                    {identification.map((identification, index) => (
                                      <TableRow key={identification.type + identification.country + identification.issue_Date} style={{ background: index % 2 === 0 ? 'white' : 'whitesmoke' }}>
                                        <TableCell>{identification.type}</TableCell>
                                        <TableCell>{identification.ids}</TableCell>
                                        <TableCell>{identification.country}</TableCell>
                                        <TableCell>
                                          {identification.issue_Date !== '0000-00-00' ? identification.issue_Date : ''}
                                        </TableCell>
                                      </TableRow>
                                    ))}
                                  </TableBody>
                                </Table>
                              </TableContainer>
                            </Grid>
                          </Card>
                          <br />
                        </>
                      )}
                      {aliases.filter(alias => alias.aliasesType !== "Name").length > 0 && (
                        <>
                          <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
                            <h4>ALIASES:</h4>
                          </div>
                          <Card style={{ padding: '1%', boxShadow: 'rgb(0 0 0 / 28%) 0px 4px 8px', width: '100%' }}>
                            <Grid item xs={12}>
                              <TableContainer>
                                <Table size="small" aria-label="a dense table" style={{ margin: '0 auto' }}>
                                  <TableHead>
                                    <TableRow>
                                      <TableCell>Type</TableCell>
                                      <TableCell>Category</TableCell>
                                      <TableCell>Name</TableCell>
                                    </TableRow>
                                  </TableHead>
                                  <TableBody>
                                    {aliases.filter(alias => alias.aliasesType !== "Name").map((alias, index) => (
                                      <TableRow key={index} style={{ background: index % 2 === 0 ? 'white' : 'whitesmoke' }}>
                                        <TableCell>{alias.aliasesType}</TableCell>
                                        <TableCell>{alias.category}</TableCell>
                                        <TableCell>{alias.aliasesName}</TableCell>
                                      </TableRow>
                                    ))}
                                  </TableBody>
                                </Table>
                              </TableContainer>

                            </Grid>
                          </Card>
                          <br />
                        </>
                      )}
                      {address.length > 0 && (
                        <>
                          <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
                            <h4>Addresses:</h4>
                          </div>
                          <Card style={{ padding: '1%', boxShadow: 'rgb(0 0 0 / 28%) 0px 4px 8px', width: '100%' }}>
                            <Grid item xs={12}>
                              <TableContainer>
                                <Table size="small" aria-label="a dense table" style={{ margin: '0 auto' }}>
                                  <TableHead>
                                    <TableRow>
                                      <TableCell>Region</TableCell>
                                      <TableCell>Address1</TableCell>
                                      <TableCell>Address2</TableCell>
                                      <TableCell>Address3</TableCell>
                                      <TableCell>City</TableCell>
                                      <TableCell>Province</TableCell>
                                      <TableCell>Postal Code</TableCell>
                                      <TableCell>Country </TableCell>
                                    </TableRow>
                                  </TableHead>
                                  <TableBody>
                                    {address.map((addres, index) => (
                                      <TableRow key={index} style={{ background: index % 2 === 0 ? 'white' : 'whitesmoke' }}>
                                        <TableCell>{addres.region}</TableCell>
                                        <TableCell>{addres.address1}</TableCell>
                                        <TableCell>{addres.address2}</TableCell>
                                        <TableCell>{addres.address3}</TableCell>
                                        <TableCell>{addres.city}</TableCell>
                                        <TableCell>{addres.province}</TableCell>
                                        <TableCell>{addres.postal}</TableCell>
                                        <TableCell>{addres.countryName}</TableCell>
                                      </TableRow>
                                    ))}
                                  </TableBody>
                                </Table>
                              </TableContainer>
                            </Grid>
                          </Card>
                        </>
                      )}
                      <br></br>
                      <div>Enter Remarks</div>
                      <div style={{ textAlign: 'center' }}>
                        Select a status and enter remarks for this employee.
                      </div>
                      <div>
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
                            <div style={{ textAlign: 'center' }}>
                              Enter your remarks for this action.
                            </div>

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
                      </div>
                    </div>

                    <div style={{ display: 'flex', justifyContent: 'flex-end' }}>
                      <DialogActions>
                        <Button variant="contained" onClick={handleCloseModal}>Close</Button>
                        {selectedStatus && (
                          <Button variant="contained" onClick={handleRemarksSubmit} color="primary">
                            Submit
                          </Button>
                        )}
                      </DialogActions>

                    </div>
                  </div>

                </Card>
              </Box>
              {/* </Box>

              </Box> */}

            </>
          )}

        </DialogContent>

      </Dialog>

      <Dialog open={showModallogical} onClose={handleCloseModallogical} fullWidth
        maxWidth="lg">
        {/* <DialogTitle>Search Details</DialogTitle> */}
        <DialogContent >
          {loading ? (
            <p>Loading...</p>
          ) : (
            <>
              {/* <Box sx={{ display: 'flex' }}>
                <Header />
                <Box component="main" sx={{ flexGrow: 1, p: 3 }}> */}
              <Box m={2} >
                <div style={{ display: 'flex', justifyContent: 'flex-end', alignItems: 'center' }}>

                  <IconButton

                    color="primary"

                    onClick={handlePrint}
                    style={{ minWidth: 'unset', padding: '2px' }}
                  >
                    <PrintIcon />
                  </IconButton>
                </div>
                <Card ref={cardRef} style={{ padding: '1%', boxShadow: 'rgb(0 0 0 / 28%) 0px 4px 8px', width: '100%', overflowY: 'auto', maxHeight: '500px' }}>
                <div className="card-body">

                    <div ref={myRef}>
                      <h5>DETAILS:</h5>
                      <Card style={{ padding: '1%', boxShadow: 'rgb(0 0 0 / 28%) 0px 4px 8px', width: '100%' }}>
                        <Grid container spacing={2} justifyContent="space-between">
                          {logicaldetails.length > 0 ? (
                            logicaldetails.map((detail, index) => (
                              <React.Fragment key={index}>
                                <Grid item xs={4}>
                                  <Typography><b>First Name</b>: {detail.naal_firstname}</Typography>
                                </Grid>
                                <Grid item xs={4}>
                                  <Typography><b>Middle Name</b>: {detail.naal_middlename}</Typography>
                                </Grid>
                                <Grid item xs={4}>
                                  <Typography><b>Last Name</b>: {detail.naal_lastname}</Typography>
                                </Grid>
                              </React.Fragment>
                            ))
                          ) : (
                            <Grid item xs={12}>
                              <Typography>No details available</Typography>
                            </Grid>
                          )}

                          {logicalBirthDetails.length > 0 ? (
                            logicalBirthDetails.map((detail, index) => (
                              <React.Fragment key={index}>
                                <Grid item xs={4}>
                                  <Typography><b>Birth Country</b>: {detail.birt_country}</Typography>
                                </Grid>
                                <Grid item xs={4}>
                                  <Typography><b>Birth Place</b>: {detail.birt_plcae}</Typography>
                                </Grid>
                                <Grid item xs={4}>
                                  <Typography><b>Birth Date</b>: {detail.birt_date}</Typography>
                                </Grid>
                              </React.Fragment>
                            ))
                          ) : (
                            <Grid item xs={12}>
                              <Typography>No details available</Typography>
                            </Grid>
                          )}
                          {logicalcitiy.length > 0 ? (
                            logicalcitiy.map((detail, index) => (
                              <React.Fragment key={index}>
                                <Grid item xs={4}>
                                  <Typography><b>City Country</b>: {detail.citi_country}</Typography>
                                </Grid>

                              </React.Fragment>
                            ))
                          ) : (
                            <Grid item xs={12}>
                              <Typography>No details available</Typography>
                            </Grid>
                          )}
                        </Grid>
                      </Card>
                      <br />
                      {logicalidentification.length > 0 && (
                        <>
                          <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
                            <h4>IDENTIFICATIONS:</h4>
                          </div>
                          <Card style={{ padding: '1%', boxShadow: 'rgb(0 0 0 / 28%) 0px 4px 8px', width: '100%' }}>
                            <Grid item xs={12}>
                              <TableContainer component={Paper}>
                                <Table size="small" aria-label="a dense table" style={{ margin: '0 auto' }}>
                                  <TableHead>
                                    <TableRow>
                                      <TableCell>Identification Leba publication date</TableCell>
                                      <TableCell>Entity logical id Identification</TableCell>
                                      <TableCell>Identification leba numtitle</TableCell>
                                      <TableCell>Identification</TableCell>
                                      <TableCell>Identification</TableCell>

                                    </TableRow>
                                  </TableHead>
                                  <TableBody>
                                    {logicalidentification.map((id, index) => (
                                      <TableRow key={index} style={{ background: index % 2 === 0 ? 'white' : 'whitesmoke' }}>
                                        <TableCell>{id.entity_logical_id_Iden !== 0 ? id.entity_logical_id_Iden : null}</TableCell>
                                        {/* <TableCell>{id.entity_logical_id_Iden}</TableCell> */}
                                        <TableCell>{id.iden_Leba_publication_date}</TableCell>
                                        <TableCell>{id.iden_country}</TableCell>
                                        <TableCell>{id.iden_leba_numtitle}</TableCell>
                                        <TableCell>{id.iden_number}</TableCell>

                                      </TableRow>
                                    ))}
                                  </TableBody>
                                </Table>
                              </TableContainer>
                            </Grid>
                          </Card>


                        </>
                      )}
                      {/* <br /> */}
                      {logicalAddress.length > 0 && (
                        <>
                          <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
                            <h4>Addresses:</h4>
                          </div>
                          <Card style={{ padding: '1%', boxShadow: 'rgb(0 0 0 / 28%) 0px 4px 8px', width: '100%' }}>
                            <Grid item xs={12}>
                              <TableContainer component={Paper}>
                                <Table size="small" aria-label="a dense table" style={{ margin: '0 auto' }}>
                                  <TableHead>
                                    <TableRow>

                                      {/* <TableCell>entity_logical_id_Address </TableCell> */}
                                      <TableCell>Address_number </TableCell>
                                      <TableCell>Address_street </TableCell>
                                      <TableCell>Address_zipcode </TableCell>
                                      <TableCell>Address_city </TableCell>
                                      <TableCell>Address_country</TableCell>
                                      <TableCell>Address_other </TableCell>

                                    </TableRow>
                                  </TableHead>
                                  <TableBody>
                                    {logicalAddress.map((addr, index) => (
                                      <TableRow key={index} style={{ background: index % 2 === 0 ? 'white' : 'whitesmoke' }}>

                                        {/* <TableCell>{addr.entity_logical_id_Addr}</TableCell> */}
                                        <TableCell>{addr.addr_number}</TableCell>
                                        <TableCell>{addr.addr_street}</TableCell>
                                        <TableCell>{addr.addr_zipcod}</TableCell>
                                        <TableCell>{addr.addr_city}</TableCell>
                                        <TableCell>{addr.addr_country}</TableCell>
                                        <TableCell>{addr.addr_other}</TableCell>
                                      </TableRow>
                                    ))}
                                  </TableBody>
                                </Table>
                              </TableContainer>
                            </Grid>
                          </Card>

                        </>
                      )}
                      {/* <br /> */}
                      {logicalAka.length > 0 && (
                        <>
                          <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
                            <h4>ALIASES:</h4>
                          </div>
                          <Card style={{ padding: '1%', boxShadow: 'rgb(0 0 0 / 28%) 0px 4px 8px', width: '100%' }}>
                            <Grid item xs={12}>
                              <TableContainer component={Paper}>
                                <Table size="small" aria-label="a dense table" style={{ margin: '0 auto' }}>
                                  <TableHead>
                                    <TableRow>

                                      {/* <TableCell>entity_logical_id_Address </TableCell> */}
                                      <TableCell>Name </TableCell>


                                    </TableRow>
                                  </TableHead>
                                  <TableBody>
                                    {logicalAka.map((addr, index) => (
                                      <TableRow key={index} style={{ background: index % 2 === 0 ? 'white' : 'whitesmoke' }}>

                                        {/* <TableCell>{addr.entity_logical_id_Addr}</TableCell> */}
                                        <TableCell>{addr.name}</TableCell>

                                      </TableRow>
                                    ))}
                                  </TableBody>
                                </Table>
                              </TableContainer>
                            </Grid>
                          </Card>

                        </>
                      )}

                    </div>
                    <br></br>
                    <div>Enter Remarks</div>
                    <div style={{ textAlign: 'center' }}>
                      Select a status and enter remarks for this employee.
                    </div>
                    <div>
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
                          <div style={{ textAlign: 'center' }}>
                            Enter your remarks for this action.
                          </div>

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
                    </div>
                  </div>

                  <div style={{ display: 'flex', justifyContent: 'flex-end' }}>
                    <DialogActions>
                      <Button variant="contained" onClick={handleCloseModallogical}>Close</Button>
                      {selectedStatus && (
                        <Button variant="contained" onClick={handleRemarksSubmit} color="primary">
                          Submit
                        </Button>
                      )}
                    </DialogActions>
                  </div>
                </Card>
              </Box>
              {/* </Box>

              </Box> */}
            </>
          )}

        </DialogContent>

      </Dialog>
      <Dialog open={showModalgroup} onClose={handleCloseModalgroup} fullWidth
        maxWidth="lg">
        {/* <DialogTitle>Search Details</DialogTitle> */}
        <DialogContent >
          {loading ? (
            <p>Loading...</p>
          ) : (
            <>

              {/* <Box sx={{ display: 'flex' }}>
                <Header />
                <Box component="main" sx={{ flexGrow: 1, p: 3 }}> */}

              <Box m={2} >
                <div style={{ display: 'flex', justifyContent: 'flex-end', alignItems: 'center' }}>

                  <IconButton

                    color="primary"

                    onClick={handlePrint}
                    style={{ minWidth: 'unset', padding: '2px' }}
                  >
                    <PrintIcon />
                  </IconButton>
                </div>
                <Card ref={cardRef} style={{ padding: '1%', boxShadow: 'rgb(0 0 0 / 28%) 0px 4px 8px', width: '100%', overflowY: 'auto', maxHeight: '500px' }}>
                <div className="card-body">

                    <div ref={myRef}>
                      <Typography variant="h5">DETAILS:</Typography>
                      <Card style={{ padding: '1%', boxShadow: 'rgb(0 0 0 / 28%) 0px 4px 8px', width: '100%' }}>
                        {CityDetails.length > 0 && (
                          <>

                            <br />


                            {CityDetails.map((detail, index) => (
                              <Grid container spacing={2} justifyContent="space-between">
                                <React.Fragment key={index}>
                                  <Grid item xs={4}>
                                    <Typography><b>Name</b> : {detail.name}</Typography>
                                  </Grid>
                                  <Grid item xs={4}>
                                    <Typography><b>Place Of Birth</b>: {detail.place_of_Birth}</Typography>
                                  </Grid>
                                  <Grid item xs={4}>
                                    <Typography><b>DOB</b>: {detail.dob}</Typography>
                                  </Grid>
                                  <Grid item xs={3}>
                                    <Typography><b>Group Type</b>: {detail.group_Type}</Typography>
                                  </Grid>
                                  <Grid item xs={3}>
                                    <Typography><b>Citizenship</b>: {detail.citizenship}</Typography>
                                  </Grid>
                                </React.Fragment>
                              </Grid>
                            ))}

                          </>
                        )}
                      </Card>
                      <br />
                      {groupidentification.length > 0 && (
                        <>
                          <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
                            <Typography variant="h5">IDENTIFICATIONS:</Typography>
                          </div>
                          <Card style={{ padding: '1%', boxShadow: 'rgb(0 0 0 / 28%) 0px 4px 8px', width: '100%' }}>
                            <Grid item xs={12}>
                              <TableContainer component={Paper}>
                                <Table size="small" aria-label="a dense table" style={{ margin: '0 auto' }}>
                                  <TableHead>
                                    <TableRow>
                                      <TableCell>Identity</TableCell>
                                      <TableCell>Number</TableCell>

                                      <TableCell>det</TableCell>

                                    </TableRow>
                                  </TableHead>
                                  <TableBody>
                                    {groupidentification.map((id, index) => (
                                      <TableRow key={index} style={{ background: index % 2 === 0 ? 'white' : 'whitesmoke' }}>
                                        <TableCell>{id.identity}</TableCell>
                                        <TableCell>{id.number}</TableCell>
                                        <TableCell>{id.det}</TableCell>

                                      </TableRow>
                                    ))}
                                  </TableBody>
                                </Table>
                              </TableContainer>
                            </Grid>
                          </Card>
                          <br />
                        </>
                      )}
                      <br />

                      {Groupaliases.length > 0 && (
                        <>
                          <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
                            <h4>ALIASES:</h4>
                          </div>
                          <Card style={{ padding: '1%', boxShadow: 'rgb(0 0 0 / 28%) 0px 4px 8px', width: '100%' }}>
                            <Grid item xs={12}>
                              <TableContainer component={Paper}>
                                <Table size="small" aria-label="a dense table" style={{ margin: '0 auto' }}>
                                  <TableHead>
                                    <TableRow>
                                      <TableCell>Type</TableCell>
                                      <TableCell>Quality</TableCell>

                                      <TableCell>Name</TableCell>


                                    </TableRow>
                                  </TableHead>
                                  <TableBody>
                                    {Groupaliases.map((id, index) => (
                                      <TableRow key={index} style={{ background: index % 2 === 0 ? 'white' : 'whitesmoke' }}>
                                        <TableCell>{id.alias_Type}</TableCell>
                                        <TableCell>{id.alias_Quality}</TableCell>
                                        <TableCell>{id.name}</TableCell>


                                      </TableRow>
                                    ))}
                                  </TableBody>
                                </Table>
                              </TableContainer>
                            </Grid>
                          </Card>
                          <br />
                        </>
                      )}

                    </div>
                    <br></br>
                    <div>Enter Remarks</div>
                    <div style={{ textAlign: 'center' }}>
                      Select a status and enter remarks for this employee.
                    </div>
                    <div>
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
                          <div style={{ textAlign: 'center' }}>
                            Enter your remarks for this action.
                          </div>

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
                    </div>
                  </div>

                  <div style={{ display: 'flex', justifyContent: 'flex-end' }}>
                    <DialogActions>
                      <Button variant="contained" onClick={handleCloseModalgroup}>Close</Button>
                      {selectedStatus && (
                        <Button variant="contained" onClick={handleRemarksSubmit} color="primary">
                          Submit
                        </Button>
                      )}
                    </DialogActions>
                  </div>
                </Card>
              </Box>
              {/* </Box>
              </Box> */}
            </>)}

        </DialogContent>

      </Dialog>
      <Dialog open={showModalun} onClose={handleCloseModalun} fullWidth
        maxWidth="lg">
        {/* <DialogTitle>Search Details</DialogTitle> */}
        <DialogContent >
          {loading ? (
            <p>Loading...</p>
          ) : (
            <>

              {/* <Box sx={{ display: 'flex' }}>
                <Header />
                <Box component="main" sx={{ flexGrow: 1, p: 3 }}> */}

              <Box m={2} >
                <div style={{ display: 'flex', justifyContent: 'flex-end', alignItems: 'center' }}>

                  <IconButton

                    color="primary"

                    onClick={handlePrint}
                    style={{ minWidth: 'unset', padding: '2px' }}
                  >
                    <PrintIcon />
                  </IconButton>
                </div>
                <Card ref={cardRef} style={{ padding: '1%', boxShadow: 'rgb(0 0 0 / 28%) 0px 4px 8px', width: '100%', overflowY: 'auto', maxHeight: '500px' }}>

                  <div className="card-body">

                    <div ref={myRef}>

                      <Typography variant="h5">DETAILS:</Typography>
                      <Card style={{ padding: '1%', boxShadow: 'rgb(0 0 0 / 28%) 0px 4px 8px', width: '100%' }}>
                        {UnDetails.length > 0 ? (
                          UnDetails.map((detail, index) => (
                            <React.Fragment key={index}>
                              <Grid container spacing={2} justifyContent="space-between">

                                <Grid item xs={3}>
                                  <Typography><b>First Name</b>: {detail.firstName}</Typography>
                                </Grid>

                                <Grid item xs={3}>
                                  <Typography><b>Sec Name</b>: {detail.secName}</Typography>
                                </Grid>

                                <Grid item xs={3}>
                                  <Typography><b>Third Name</b>: {detail.thirdName}</Typography>
                                </Grid>

                                <Grid item xs={3}>
                                  <Typography><b>List</b>: {detail._list}</Typography>
                                </Grid>

                                <Grid item xs={3}>
                                  <Typography><b>Birth Place</b>: {detail.birthPlace}</Typography>
                                </Grid>

                                <Grid item xs={3}>
                                  <Typography><b>Birth Type</b>: {detail.birthType}</Typography>
                                </Grid>

                                <Grid item xs={3}>
                                  <Typography><b>Citizenship</b>: {detail.citizenship}</Typography>
                                </Grid>

                                <Grid item xs={3}>
                                  <Typography><b>DOB</b>: {detail.dob}</Typography>
                                </Grid>

                                <Grid item xs={3}>
                                  <Typography><b>Gender</b>: {detail.gender}</Typography>
                                </Grid>

                                <Grid item xs={3}>
                                  <Typography><b>Data ID</b>: {detail.dataid}</Typography>
                                </Grid>

                                <Grid item xs={3}>
                                  <Typography><b>Nationality</b>: {detail.nationality}</Typography>
                                </Grid>

                                <Grid item xs={3}>
                                  <Typography><b>Remarks</b>: {detail.remarks}</Typography>
                                </Grid>

                              </Grid>
                            </React.Fragment>
                          ))
                        ) : (
                          <Typography>No details available</Typography>
                        )}
                      </Card>


                      <br />
                      {Unaliases.length > 0 && (
                        <>
                          <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
                            <h4>ALIASES:</h4>
                          </div>
                          <Card style={{ padding: '1%', boxShadow: 'rgb(0 0 0 / 28%) 0px 4px 8px', width: '100%' }}>
                            <Grid item xs={12}>
                              <TableContainer component={Paper}>
                                <Table size="small" aria-label="a dense table" style={{ margin: '0 auto' }}>
                                  <TableHead>
                                    <TableRow>
                                      <TableCell>Type</TableCell>
                                      <TableCell>Quality</TableCell>

                                      <TableCell>Name</TableCell>


                                    </TableRow>
                                  </TableHead>
                                  <TableBody>
                                    {Unaliases.map((id, index) => (
                                      <TableRow key={index} style={{ background: index % 2 === 0 ? 'white' : 'whitesmoke' }}>
                                        <TableCell>{id._Type}</TableCell>
                                        <TableCell>{id.quality}</TableCell>
                                        <TableCell>{id.name}</TableCell>


                                      </TableRow>
                                    ))}
                                  </TableBody>
                                </Table>
                              </TableContainer>
                            </Grid>
                          </Card>
                          <br />
                        </>
                      )}
                      <br />
                      {UnDesignationDetails.length > 0 && (
                        <>
                          <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
                            <h4>ALIASES:</h4>
                          </div>
                          <Card style={{ padding: '1%', boxShadow: 'rgb(0 0 0 / 28%) 0px 4px 8px', width: '100%' }}>
                            <Grid item xs={12}>
                              <TableContainer component={Paper}>
                                <Table size="small" aria-label="a dense table" style={{ margin: '0 auto' }}>
                                  <TableHead>
                                    <TableRow>
                                      <TableCell>Identity</TableCell>



                                    </TableRow>
                                  </TableHead>
                                  <TableBody>
                                    {UnDesignationDetails.map((id, index) => (
                                      <TableRow key={index} style={{ background: index % 2 === 0 ? 'white' : 'whitesmoke' }}>
                                        <TableCell>{id.identity}</TableCell>



                                      </TableRow>
                                    ))}
                                  </TableBody>
                                </Table>
                              </TableContainer>
                            </Grid>
                          </Card>
                          <br />

                        </>
                      )}

                    </div>
                    <br></br>
                    <div>Enter Remarks</div>
                    <div style={{ textAlign: 'center' }}>
                      Select a status and enter remarks for this employee.
                    </div>
                    <div>
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
                          <div style={{ textAlign: 'center' }}>
                            Enter your remarks for this action.
                          </div>

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
                    </div>
                  </div>

                  <div style={{ display: 'flex', justifyContent: 'flex-end' }}>
                    <DialogActions>
                      <Button variant="contained" onClick={handleCloseModalun}>Close</Button>
                      {selectedStatus && (
                        <Button variant="contained" onClick={handleRemarksSubmit} color="primary">
                          Submit
                        </Button>
                      )}
                    </DialogActions>
                  </div>
                </Card>
              </Box>
              {/* </Box>
              </Box> */}
            </>
          )}

        </DialogContent>

      </Dialog>

    </>
  );
};

export default NotificationComponent;
