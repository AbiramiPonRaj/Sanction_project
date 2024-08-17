

import React, { useState, useEffect, useRef } from 'react';
import { Box, TextField, Button, Grid, InputLabel, FormControl, Select, MenuItem, Paper, Typography, Dialog, DialogTitle, DialogContent, DialogActions, DialogContentText } from '@mui/material';
import { Card } from 'react-bootstrap';
import { SelectChangeEvent } from '@mui/material/Select';
import { Table, TableContainer, TableHead, TableRow, TableCell, TableBody, IconButton } from '@mui/material';
import Header from '../../layouts/header/header';
import PrintIcon from '@mui/icons-material/Print';
import { Slider } from '@mui/material';
import { useLocation, useNavigate, useParams } from 'react-router-dom';
import ClearIcon from '@mui/icons-material/Clear';
import { Link } from 'react-router-dom';
import * as XLSX from 'xlsx';
import FileDownloadIcon from '@mui/icons-material/FileDownload';
import ViewService from '../../data/services/san_search/viewpage/view_api_service';
import { Country, List, Program, All, Customer, CustomerRequest, Address, IdentificationData, AliasesData, PlaceOfBirthData, NationalityData, Birthdate, DetailsData, SearchDTO, RecordDTO, logicalIdentification, logicaAddress, LogicalDetails, Logicalcitiy, LogicalBirthDetails, LogicalAKADetails, GroupAliases, GroupIdentification, CityDetails, UnDetails, UnAliases, UnDesignationDetails } from '../../data/services/san_search/viewpage/view_payload';
import './Details.css';
import VisibilityIcon from '@mui/icons-material/Visibility';
import VisibilityOffIcon from '@mui/icons-material/VisibilityOff';
import SearchApiService from '../../data/services/san_search/search-api-service';
import { Timeline, TimelineItem, TimelineContent, TimelineDot, TimelineSeparator, TimelineConnector } from '@mui/lab';
import { useSelector } from 'react-redux';
import HitdatalifecycleApiService from '../../data/services/san_search/hitdatalifecycle/hitdatalifecycle-api-service';
import HitcaseApiService from '../../data/services/san_search/hitcase/hitcase-api-service';
import { useReactToPrint } from 'react-to-print';


interface Status {
    id: string;
    name: string;
    // Add other properties if necessary
}
interface DisabledIcons {
    [key: string]: boolean;
}
function Details() {
    const userDetails = useSelector((state: any) => state.loginReducer);
    const userFirstName = userDetails.userData?.firstName;
    const loginDetails = userDetails.loginDetails;
    const userId = loginDetails.uid;

    const location = useLocation();
    const { id, ids } = useParams();
    const navigate = useNavigate();
    const cardRef = useRef<HTMLDivElement | null>(null);

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


    }, [ids]);

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

    const handleKeyPress = (e: { key: string }) => {
        if (e.key === 'Enter') {
            handleSearch();
        }
    };

    const handleSort = (columnName: string) => {
        if (columnName === sortedColumn) {
            setSortDirection(sortDirection === 'asc' ? 'desc' : 'asc');
        } else {
            setSortedColumn(columnName);
            setSortDirection('asc');
        }
    };

    const sortedData = (filteredData ?? []).length > 0 ? filteredData.sort((a, b) => {
        const valueA = a[sortedColumn as keyof RecordDTO];
        const valueB = b[sortedColumn as keyof RecordDTO];

        if (sortedColumn === 'score') {
            // Convert to numbers for numerical comparison
            const numA = Number(valueA);
            const numB = Number(valueB);
            return sortDirection === 'asc' ? numA - numB : numB - numA;
        } else {
            // Handle other columns as strings
            const strA = String(valueA);
            const strB = String(valueB);
            return sortDirection === 'asc' ? strA.localeCompare(strB) : strB.localeCompare(strA);
        }
    }) : [];

    const [loading, setLoading] = useState<boolean>(false);
    const handleSliderChange = (e: any, newValue: number | number[]) => {
        setSliderValue(typeof newValue === 'number' ? newValue : 50); // Ensure newValue is always a number
    };

    const handleTextFieldChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        let value = parseInt(e.target.value, 10);
        if (isNaN(value)) {
            value = 0; // Set default value if parsing fails
        }
        setSliderValue(value);
    };

    const handleBlur = () => {
        if (sliderValue < 50) {
            setSliderValue(50); // Clamp value to minimum 50
        } else if (sliderValue > 100) {
            setSliderValue(100); // Clamp value to maximum 100
        }
    };

    const handleSearch = async () => {
        try {
            setLoading(true);
            setFilteredData([]);
            if (!formData.name && !formData.address && sliderValue === 100) {
                setSearchError(true);

                setLoading(false);
                return;
            }
            const searchDTO: SearchDTO = {
                name: formData.name,
                matching_score: sliderValue,
                listID: selectedList,
                partySubTypeID: selectedRecordType,
                countryId: selectedCountry,
                // accountNum:'',

            };
            const filtered = await viewservice.getRecordsCount(searchDTO);
            console.log('Filtered Data:', filtered);
            if (Array.isArray(filtered) && filtered.length > 0) {
                setFilteredData(filtered);
                setSearchError(false);
            } else {
                setSearchError(true);
                setFilteredData([]);
            }
            console.log('setFilteredData:', setFilteredData);
        } catch (error) {
            console.error("Error fetching lookup data:", error);
            setSearchError(true);
            setFilteredData([]);
        } finally {
            setLoading(false);
        }
    };

    const handleReset = () => {
        setFormData({
            ids: 0, name: '', address: '', entityType: '', program: '', list: '', score: 0, fileType: 0, fileList: '', searchId: '', hitId: '', criminalId: '', nationality: '',
            citizenship: '',
            passport: '',
            Country: ''
        });
        setFilteredData([]);
        setSliderValue(100);
        setSearchError(false);
    };

    // const handlePrint = () => {
    //     window.print();
    // };

    // const tableRef = useRef<HTMLDivElement>(null); // Specify the type of tableRef

    // const handlePrint = () => {
    //     try {
    //         const table = tableRef.current; // Assuming you have a ref named tableRef
    //         if (table) {
    //             const printContents = table.innerHTML;
    //             const originalContents = document.body.innerHTML;

    //             document.body.innerHTML = printContents;
    //             window.print();

    //             document.body.innerHTML = originalContents; // Restore original content
    //         } else {
    //             console.error("Table container reference (tableRef) not found.");
    //         }
    //     } catch (error) {
    //         console.error("Error handling print:", error);
    //     }
    // };
    // const handlePrint = () => {
    //     try {
    //         const table = tableRef.current;
    //         if (table) {
    //             // Hide non-printable content
    //             const originalDisplayStyle = table.style.display;
    //             table.style.display = 'block'; // Ensure table is visible for printing
    //             const printContents = table.innerHTML;
    //             table.style.display = originalDisplayStyle; // Restore original display style

    //             // Create a new window to print
    //             const printWindow = window.open('', '_blank');
    //             if (printWindow) {
    //                 printWindow.document.open();
    //                 printWindow.document.write(`
    //                     <html>
    //                         <head>
    //                             <style>
    //                                 @media print {
    //                                     body * {
    //                                         visibility: hidden;
    //                                     }
    //                                     #print-content, #print-content * {
    //                                         visibility: visible;
    //                                     }
    //                                 }
    //                             </style>
    //                         </head>
    //                         <body>
    //                             <div id="print-content">
    //                                 ${printContents}
    //                             </div>
    //                         </body>
    //                     </html>
    //                 `);
    //                 printWindow.document.close();
    //                 printWindow.focus();
    //                 printWindow.print();
    //                 printWindow.close();
    //             } else {
    //                 throw new Error('Failed to open print window.');
    //             }
    //         } else {
    //             console.error("Table container reference (tableRef) not found.");
    //         }
    //     } catch (error) {
    //         console.error("Error handling print:", error);
    //     }
    // };
    const myRef = useRef(null);
    const Ref = useRef(null);

    //   const handlePrint = useReactToPrint({
    //     content: () => myRef.current
    //   });
    const handlePrint = useReactToPrint({
        content: () => Ref.current,
        pageStyle: `
          @page {
            margin-left: 20mm; /* Adjust this value as per your requirement */
          }
          body {
            margin: 0;
          }
          .table-container {
            overflow: visible !important; /* Ensure table content is fully visible when printing */
            max-height: none !important;
          }
          table {
            margin: 0 auto; /* Center the table */
            width: 100%;
          }
          th, td {
            border: 1px solid #ddd; /* Add borders to table cells for clarity */
          }
        `,
    });

    const handlePrinted = useReactToPrint({
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
            const dataForExport = filteredData.length > 0 ? filteredData.map((row) => ({
                FileList: row.fileList,
                Name: row.name,
                Address: row.address,
                Type: row.entityType,
                Program: row.program,
                List: row.list,
                Score: row.score

            })) : [{ Message: "Your search has not returned any results." }];

            const workbook = XLSX.utils.book_new();
            const worksheet = XLSX.utils.json_to_sheet(dataForExport);
            XLSX.utils.book_append_sheet(workbook, worksheet, "Lookup Results");
            XLSX.writeFile(workbook, "lookup_results.xlsx");
        } catch (error) {
            console.error("Error exporting data to Excel:", error);
        }
    };

    const allValues = RecordType.map((item) => item.type_text).join(", ");


    const handleTableRowClick = async (ids: number, fileType: number, index: number, searchId: string) => {
        const id = String(ids);
        console.log('Clicked row id:', id); // Check if id is correctly logged

        if (fileType === 1) {
            setShowModal(true);
            console.log("Clicked icon at row:", index);
            console.log("Search ID:", searchId);
            console.log("Record ID:", ids);

            const currentIndex = `${searchId}-${ids}-${index}`;
            const existingAction = selectedActions[currentIndex] || '';
            const existingRemarks = remarksAndActions[currentIndex]?.remarks || '';

            setSelectedStatus(existingAction);
            setRemarks(existingRemarks);
            setSelectedRow(currentIndex);

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
            console.log("Record ID:", ids);

            const currentIndex = `${searchId}-${ids}-${index}`;
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
            console.log("Record ID:", ids);

            const currentIndex = `${searchId}-${ids}-${index}`;
            const existingAction = selectedActions[currentIndex] || '';
            const existingRemarks = remarksAndActions[currentIndex]?.remarks || '';

            setSelectedStatus(existingAction);
            setRemarks(existingRemarks);
            setSelectedRow(currentIndex)

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
            console.log("Record ID:", ids);

            const currentIndex = `${searchId}-${ids}-${index}`;
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
    const authApiService = new SearchApiService();
    const hitdatalifecycleApiService = new HitdatalifecycleApiService();
    const hitcaseApiService = new HitcaseApiService();
    const fetchStatus = async () => {
        try {
            const statuses: Status[] = await authApiService.getStatus();

            const filteredStatuses = statuses.filter((status: Status) => {
                return status.name === "close" || status.name === "Escalation";
            });

            console.log(filteredStatuses);
            setStatusData(filteredStatuses);
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
    const [disabledIcons, setDisabledIcons] = useState<DisabledIcons>({});


    const handleIconClick = (index: number, searchId: string, ids: string) => {
        console.log("Clicked icon at row:", index);
        console.log("Search ID:", searchId);
        console.log("ids:", ids);

        const currentIndex = `${searchId}-${ids}-${index}`;
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

                return status.name;
            }
        } else {
            return '';
        }
    };

    const handleRemarksSubmit = async () => {
        try {
            if (selectedRow !== null && filteredData.some(row => `${row.searchId}-${row.ids}-${filteredData.indexOf(row)}` === selectedRow)) {
                const updatedRemarksAndActions = { ...remarksAndActions };
                updatedRemarksAndActions[selectedRow] = { action: selectedStatus, remarks };

                setRemarksAndActions(updatedRemarksAndActions);

                const selectedSearchResult = filteredData.find(row => `${row.searchId}-${row.ids}-${filteredData.indexOf(row)}` === selectedRow);
                console.log("selectedRow:", selectedRow);
                console.log("filteredData:", filteredData);

                if (!selectedSearchResult) {
                    console.error("Selected search result is undefined");
                    return;
                }

                const hitdatalifecyclePayload = {
                    searchId: selectedSearchResult.searchId,
                    criminalId: selectedSearchResult.ids.toString(),
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
                    criminalId: selectedSearchResult.ids.toString(),
                    levelId: '1',
                    statusNowId: selectedStatus,
                    cycleId: '1',
                    remark: remarks,
                    uid: loginDetails.id,
                };

                console.log("hitdatalifecycle Payload:", hitdatalifecyclePayload);
                console.log("hitCasePayload:", hitcasePayload);

                if (parseInt(selectedStatus) === 1) {
                    await hitdatalifecycleApiService.CreateHitdatalifecycle(hitdatalifecyclePayload);
                } else if (parseInt(selectedStatus) === 2) {
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
                console.error("Selected row is null or invalid");
            }
        } catch (error) {
            console.error("Error submitting remarks:", error);
        }
        handleCloseModal();
        handleCloseModallogical();
        handleCloseModalgroup();
        handleCloseModalun();
    };

    return (
        <>
            <Box sx={{ display: 'flex' }}>
                <Header />

                <Box component="main" sx={{ flexGrow: 1, p: 3 }}>
                    <Box m={2} style={{ marginTop: '5%' }}>
                        <Card className='card' style={{ boxShadow: 'rgb(0 0 0 / 28%) 0px 4px 8px', width: '100%' }}>

                            <div className="card-body" >
                                <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginLeft: '95%' }}>
                                    {/* <h4>LOOK UP</h4> */}
                                    <IconButton

                                        color="primary"

                                        onClick={handlePrint}
                                        style={{ minWidth: 'unset', padding: '2px' }}
                                    >
                                        <PrintIcon />
                                    </IconButton>
                                    <IconButton

                                        color="primary"

                                        onClick={exportToExcel}
                                        style={{ minWidth: 'unset', padding: '2px' }}
                                    >
                                        <FileDownloadIcon />
                                    </IconButton>

                                </div>


                                <Card className='card' style={{ padding: '1%', boxShadow: 'rgb(0 0 0 / 28%) 0px 4px 8px', width: '100%' }}>
                                    <div style={{ display:'flex',gap:'2%'}}>
                                   
                                            <FormControl style={{ width: '50%' }}>
                                                <InputLabel htmlFor="record-type">Type</InputLabel>
                                                <Select
                                                    label="Type"
                                                    size='small'
                                                    variant="outlined"
                                                    value={selectedRecordType}
                                                    onChange={handleRecordTypeChange}
                                                >
                                                    <MenuItem value={0}>All</MenuItem>
                                                    {RecordType.map((item) => (
                                                        <MenuItem key={item.partyTypeID} value={parseInt(item.partyTypeID)}>
                                                            {item.type_text}
                                                        </MenuItem>
                                                    ))}
                                                </Select>
                                            </FormControl>
                                       
                                      
                                            <TextField
                                                style={{ width: '50%' }}
                                                label="Account Number"
                                                id="Account Number"
                                                size='small'
                                                variant="outlined"
                                                type="text"
                                                name="name"
                                                autoComplete="off"
                                           
                                            />
                                    

                                    
                                            <TextField
                                                style={{ width: '50%' }}
                                                label="Name"
                                                id="Name"
                                                size='small'
                                                variant="outlined"
                                                type="text"
                                                name="name"
                                                autoComplete="off"
                                                value={formData.name}
                                                onChange={(e) => setFormData({ ...formData, name: e.target.value })}
                                            />
                                       
                              
                                            {List.length > 0 && (
                                                <FormControl style={{ width: '50%' }}>
                                                    <InputLabel htmlFor="record-type"  >List</InputLabel>
                                                    <Select 
                                                        label="List"
                                                        size='small'
                                                        variant="outlined"
                                                        value={selectedList}
                                                        onChange={handleListChange}
                                                    >
                                                        <MenuItem value={0}>All</MenuItem>
                                                        {List.map((item) => (
                                                            <MenuItem key={item.primaryKey} value={item.primaryKey}>
                                                                {item.text}
                                                            </MenuItem>
                                                        ))}
                                                    </Select>
                                                </FormControl>
                                            )}
                                        
                                            {country.length > 0 && (
                                                <FormControl style={{ width: '50%' }}>
                                                    <InputLabel htmlFor="record-type">Country</InputLabel>
                                                    <Select
                                                        label="Country"
                                                        size='small'
                                                        variant="outlined"
                                                        value={selectedCountry}
                                                        onChange={handleCountryChange}
                                                    >
                                                        <MenuItem value={0}>All</MenuItem>
                                                        {country.map((item) => (
                                                            <MenuItem key={item.primaryKey} value={item.primaryKey}>
                                                                {item.text}
                                                            </MenuItem>
                                                        ))}
                                                    </Select>
                                                </FormControl>
                                            )}
                                        
                                     
                                            <Slider
                                                style={{ width: '40%' }}
                                                value={sliderValue}
                                                onChange={handleSliderChange}
                                                aria-labelledby="input-slider"
                                                min={50}
                                                max={100}
                                            />
                                       
                                            <TextField
                                                style={{ width: '20%' }}
                                                value={sliderValue}
                                                size="small"
                                                id="max-score"
                                                label="max-score"
                                                variant="outlined"
                                                name="maxScore"
                                                autoComplete="off"
                                                type="text"
                                                onChange={handleTextFieldChange}
                                                onBlur={handleBlur}
                                            />
                                   
                                        {/* <Grid item xs={2}>
                                            <FormControl style={{ width: '100%' }}>
                                                <InputLabel htmlFor="record-type">Type</InputLabel>
                                                <Select
                                                    label="Type"
                                                    size='small'
                                                    variant="outlined"
                                                    value={selectedRecordType}
                                                    onChange={handleRecordTypeChange}
                                                >
                                                    <MenuItem value={0}>All</MenuItem>
                                                    {RecordType.map((item) => (
                                                        <MenuItem key={item.partyTypeID} value={parseInt(item.partyTypeID)}>
                                                            {item.type_text}
                                                        </MenuItem>
                                                    ))}
                                                </Select>
                                            </FormControl>
                                        </Grid>
                                        <Grid item xs={2}>
                                            <TextField
                                                style={{ width: '100%' }}
                                                label="Account Number"
                                                id="Account Number"
                                                size='small'
                                                variant="outlined"
                                                type="text"
                                                name="name"
                                                autoComplete="off"
                                           
                                            />
                                        </Grid>

                                        <Grid item xs={2}>
                                            <TextField
                                                style={{ width: '100%' }}
                                                label="Name"
                                                id="Name"
                                                size='small'
                                                variant="outlined"
                                                type="text"
                                                name="name"
                                                autoComplete="off"
                                                value={formData.name}
                                                onChange={(e) => setFormData({ ...formData, name: e.target.value })}
                                            />
                                        </Grid>
                                        <Grid item xs={2}>
                                            {List.length > 0 && (
                                                <FormControl style={{ width: '100%' }}>
                                                    <InputLabel htmlFor="record-type">List</InputLabel>
                                                    <Select
                                                        label="List"
                                                        size='small'
                                                        variant="outlined"
                                                        value={selectedList}
                                                        onChange={handleListChange}
                                                    >
                                                        <MenuItem value={0}>All</MenuItem>
                                                        {List.map((item) => (
                                                            <MenuItem key={item.primaryKey} value={item.primaryKey}>
                                                                {item.text}
                                                            </MenuItem>
                                                        ))}
                                                    </Select>
                                                </FormControl>
                                            )}
                                        </Grid>
                                        <Grid item xs={2}>
                                            {country.length > 0 && (
                                                <FormControl style={{ width: '100%' }}>
                                                    <InputLabel htmlFor="record-type">Country</InputLabel>
                                                    <Select
                                                        label="Country"
                                                        size='small'
                                                        variant="outlined"
                                                        value={selectedCountry}
                                                        onChange={handleCountryChange}
                                                    >
                                                        <MenuItem value={0}>All</MenuItem>
                                                        {country.map((item) => (
                                                            <MenuItem key={item.primaryKey} value={item.primaryKey}>
                                                                {item.text}
                                                            </MenuItem>
                                                        ))}
                                                    </Select>
                                                </FormControl>
                                            )}
                                        </Grid>
                                        <Grid item xs={2}>
                                            <Slider
                                                style={{ width: '57%' }}
                                                value={sliderValue}
                                                onChange={handleSliderChange}
                                                aria-labelledby="input-slider"
                                                min={50}
                                                max={100}
                                            />
                                        </Grid>
                                        <Grid item xs={2}>
                                            <TextField
                                                style={{ width: '55px' }}
                                                value={sliderValue}
                                                size="small"
                                                id="max-score"
                                                label="max-score"
                                                variant="outlined"
                                                name="maxScore"
                                                autoComplete="off"
                                                type="text"
                                                onChange={handleTextFieldChange}
                                                onBlur={handleBlur}
                                            />
                                        </Grid> */}
                                 
                                    
                                    <div style={{ display: 'flex', justifyContent: 'center', gap: '3%' }} >
                                        {/* <Grid item xs={4}> */}
                                        {/* <Grid container spacing={4}> */}
                                        <div >

                                            <Button variant="contained" onClick={handleSearch} onKeyPress={handleKeyPress} >Search</Button>
                                        </div>
                                        <div >

                                            <Button variant="contained" onClick={handleReset}>Reset</Button>
                                        </div>
                                    </div>
                                    </div>
                                    {/* </Grid> */}
                                    {/* </Grid> */}
                                </Card>
                                <br />
                                <div >
                                    <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
                                        <h6>LOOKUP RESULTS ({filteredData.length})</h6>
                                    </div>

                                    <Card className='card' style={{ padding: '1%', boxShadow: 'rgb(0 0 0 / 28%) 0px 4px 8px', width: '100%' }}>
                                        <Grid item xs={12}>
                                            <div ref={Ref}>
                                                <TableContainer className="table-container" style={{ maxHeight: '400px', overflow: 'auto' }}>
                                                    <Table size="small" aria-label="a dense table" style={{ margin: '0 auto' }}>
                                                        <TableHead className="sticky-headers">
                                                            <TableRow>
                                                                <TableCell style={{ position: 'sticky', top: 0, backgroundColor: 'white', width: '10%' }} onClick={() => handleSort('fileList')}>
                                                                    File List {sortedColumn === 'fileList' && (sortDirection === 'asc' ? '↑' : '↓')}
                                                                </TableCell>
                                                                <TableCell style={{ position: 'sticky', top: 0, backgroundColor: 'white' }} onClick={() => handleSort('name')}>
                                                                    Name {sortedColumn === 'name' && (sortDirection === 'asc' ? '↑' : '↓')}
                                                                </TableCell>
                                                                <TableCell style={{ position: 'sticky', top: 0, backgroundColor: 'white', }} onClick={() => handleSort('typeId')}>
                                                                    Type {sortedColumn === 'typeId' && (sortDirection === 'asc' ? '↑' : '↓')}
                                                                </TableCell>
                                                                <TableCell style={{ position: 'sticky', top: 0, backgroundColor: 'white' }} onClick={() => handleSort('nationality')}>
                                                                    Nationality {sortedColumn === 'typeId' && (sortDirection === 'asc' ? '↑' : '↓')}
                                                                </TableCell>
                                                                <TableCell style={{ position: 'sticky', top: 0, backgroundColor: 'white' }} onClick={() => handleSort('citizenship')}>
                                                                    Citizenship {sortedColumn === 'typeId' && (sortDirection === 'asc' ? '↑' : '↓')}
                                                                </TableCell>
                                                                <TableCell style={{ position: 'sticky', top: 0, backgroundColor: 'white' }} onClick={() => handleSort('passport')}>
                                                                    Passport {sortedColumn === 'typeId' && (sortDirection === 'asc' ? '↑' : '↓')}
                                                                </TableCell>
                                                                <TableCell style={{ position: 'sticky', top: 0, backgroundColor: 'white' }} onClick={() => handleSort('typeId')}>
                                                                Address {sortedColumn === 'typeId' && (sortDirection === 'asc' ? '↑' : '↓')}
                                                                </TableCell>
                                                                <TableCell style={{ position: 'sticky', top: 0, backgroundColor: 'white' }} onClick={() => handleSort('score')}>
                                                                    Score {sortedColumn === 'score' && (sortDirection === 'asc' ? '↑' : '↓')}
                                                                </TableCell>
                                                                <TableCell style={{ position: 'sticky', top: 0, backgroundColor: 'white' }}>Action</TableCell>
                                                            </TableRow>
                                                        </TableHead>
                                                        <TableBody>
                                                            {loading ? (
                                                                <TableRow>
                                                                    <TableCell colSpan={8} align="center">
                                                                        <Typography variant="body1">Loading...</Typography>
                                                                    </TableCell>
                                                                </TableRow>
                                                            ) : filteredData.length > 0 ? (
                                                                filteredData.map((row, index) => {
                                                                    const currentIndex = `${row.searchId}-${row.ids}-${index}`;
                                                                    const selectedAction = selectedActions[currentIndex] || '';

                                                                    return (
                                                                        <TableRow key={row.ids}>
                                                                            <TableCell>{row.fileList}</TableCell>
                                                                            <TableCell 
                                                                          >
                                                                                <button
                                                                                    style={{
                                                                                        color: 'blue',
                                                                                        textDecoration: 'underline',
                                                                                        border: '0px solid blue',
                                                                                        backgroundColor: 'white'
                                                                                    }}
                                                                                    onClick={() => handleTableRowClick(row.ids, row.fileType, index, row.searchId.toString())}
                                                                                    disabled={disabledIcons[`${row.searchId}-${row.ids}-${index}`]}
                                                                                >
                                                                                    {row.name}
                                                                                </button>
                                                                            </TableCell>

                                                                            <TableCell  style={{ wordBreak: 'break-word', whiteSpace: 'normal', overflowWrap: 'break-word' ,}}>{row.entityType}</TableCell>
                                                                            <TableCell style={{ wordBreak: 'break-word', whiteSpace: 'normal', overflowWrap: 'break-word', }}>{row.nationality}</TableCell>
                                                                            <TableCell style={{ wordBreak: 'break-word', whiteSpace: 'normal', overflowWrap: 'break-word' }}>{row.citizenship}</TableCell>
                                                                            <TableCell style={{ wordBreak: 'break-word', whiteSpace: 'normal', overflowWrap: 'break-word'}}>{row.passport}</TableCell>
                                                                            <TableCell style={{ wordBreak: 'break-word', whiteSpace: 'normal', overflowWrap: 'break-word' }}>{row.address}</TableCell>
                                                                            <TableCell style={{ wordBreak: 'break-word', whiteSpace: 'normal', overflowWrap: 'break-word' }}>{row.score}</TableCell>
                                                                            <TableCell>
                                                                                <IconButton onClick={() => handleIconClick(index, row.searchId, row.ids.toString())} style={{ padding: '1px 1px' }}
                                                                                    disabled={disabledIcons[`${row.searchId}-${row.ids}-${index}`]}
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
                                                                    );
                                                                })
                                                            ) : (
                                                                searchError && (
                                                                    <TableRow>
                                                                        <TableCell colSpan={8} align="center">
                                                                            <Typography variant="body2" color="error" style={{ textAlign: 'center', marginTop: '10px' }}>
                                                                                {filteredData.length === 0 ? "Your search has not returned any results." : "At least one search parameter is required."}
                                                                            </Typography>
                                                                        </TableCell>
                                                                    </TableRow>
                                                                )
                                                            )}
                                                        </TableBody>
                                                    </Table>
                                                </TableContainer>
                                            </div>

                                        </Grid>
                                    </Card>
                                </div>
                            </div>
                        </Card>
                    </Box>
                </Box>
            </Box>
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
                {selectedCourierTracker && (

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
                                {/* <TimelineConnector style={{ backgroundColor: 'green' }} /> */}
                            </TimelineSeparator>
                            <TimelineContent color="text.secondary">Remark</TimelineContent>
                        </TimelineItem>
                    </Timeline>
                )}

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
                            {/* <Box sx={{ display: 'flex' }}>
                        <Header />


                        <Box component="main" sx={{ flexGrow: 1, p: 3 }}> */}

                            <Box m={2} >
                                <div style={{ display: 'flex', justifyContent: 'flex-end', alignItems: 'center' }}>

                                    {/* <Button variant="contained" color="primary" onClick={handlePrint}>
                                               <PrintIcon />
                                           </Button> */}
                                    <IconButton

                                        color="primary"

                                        onClick={handlePrinted}
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
                                                    <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
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
                                                                            {/* <TableCell>ID</TableCell> */}
                                                                            <TableCell>Country</TableCell>
                                                                            <TableCell>Issue Date </TableCell>
                                                                            <TableCell>Expire Date</TableCell>
                                                                        </TableRow>
                                                                    </TableHead>
                                                                    <TableBody>
                                                                        {identification.map((identification, index) => (
                                                                            <TableRow key={identification.type + identification.country + identification.issue_Date} style={{ background: index % 2 === 0 ? 'white' : 'whitesmoke' }}>
                                                                                <TableCell>{identification.type}</TableCell>
                                                                                <TableCell>{identification.ids}</TableCell>
                                                                                <TableCell>{identification.country}</TableCell>
                                                                                {/* <TableCell>
                                                                                    {identification.issue_Date !== '0000-00-00' ? identification.issue_Date : ''}
                                                                                </TableCell> */}
                                                                                 <TableCell>{identification.dateClarification === "Issue Date" ? identification.issue_Date : ''}
                                                                            </TableCell>
                                                                            <TableCell>
                                                                                {identification.dateClarification === "Expiration Date" ? identification.issue_Date : ''}
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
                            <Box m={2} style={{ marginTop: '5%' }}>
                                <div style={{ display: 'flex', justifyContent: 'flex-end', alignItems: 'center' }}>

                                    <IconButton
                                        color="primary"
                                        onClick={handlePrinted}
                                        style={{ minWidth: 'unset', padding: '2px' }}
                                        className="non-printable" // Add this class to hide during printing
                                    >
                                        <PrintIcon />
                                    </IconButton>

                                </div>
                                <Card ref={cardRef} style={{ padding: '1%', boxShadow: 'rgb(0 0 0 / 28%) 0px 4px 8px', width: '100%', overflowY: 'auto', maxHeight: '500px' }}>
                                    <div className="card-body">

                                        <br />
                                        <div ref={myRef}>
                                            <h4>DETAILS:</h4>
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
                                                    <br />
                                                </>
                                            )}
                                            <br />
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
                                                                            <TableCell>Address Number </TableCell>
                                                                            <TableCell>Address Street </TableCell>
                                                                            <TableCell>Address Zipcode </TableCell>
                                                                            <TableCell>Address City </TableCell>
                                                                            <TableCell>Address Country</TableCell>
                                                                            <TableCell>Address Other </TableCell>

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
                                                    <br />
                                                </>
                                            )}
                                            <br />
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
                                                    <br />
                                                </>
                                            )}
                                            <br />
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

                                        onClick={handlePrinted}
                                        style={{ minWidth: 'unset', padding: '2px' }}
                                    >
                                        <PrintIcon />
                                    </IconButton>
                                </div>
                                {/* <Card style={{ padding: '1%', boxShadow: 'rgb(0 0 0 / 28%) 0px 4px 8px', width: '100%' }}> */}
                                <Card ref={cardRef} style={{ padding: '1%', boxShadow: 'rgb(0 0 0 / 28%) 0px 4px 8px', width: '100%', overflowY: 'auto', maxHeight: '500px' }}>
                                    <div className="card-body">

                                        <br />
                                        <div ref={myRef}>
                                            <Typography variant="h5">DETAILS:</Typography>
                                            <Card style={{ padding: '1%', boxShadow: 'rgb(0 0 0 / 28%) 0px 4px 8px', width: '100%' }}>
                                                {CityDetails.length > 0 && (
                                                    <>

                                                        {CityDetails.map((detail, index) => (
                                                            <Grid container spacing={2} justifyContent="space-between">
                                                                <React.Fragment key={index}>
                                                                    <Grid item xs={4}>
                                                                        <Typography><b>Name</b> : {detail.name}</Typography>
                                                                    </Grid>
                                                                    <Grid item xs={4}>
                                                                        <Typography><b>Place of Birth</b>: {detail.place_of_Birth}</Typography>
                                                                    </Grid>
                                                                    <Grid item xs={4}>
                                                                        <Typography><b>Date of Birth</b>: {detail.dob}</Typography>
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
                        </>
                    )}

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

                                        onClick={handlePrinted}
                                        style={{ minWidth: 'unset', padding: '2px' }}
                                    >
                                        <PrintIcon />
                                    </IconButton>
                                </div>
                                <Card ref={cardRef} style={{ padding: '1%', boxShadow: 'rgb(0 0 0 / 28%) 0px 4px 8px', width: '100%', overflowY: 'auto', maxHeight: '500px' }}>
                                    <div className="card-body">

                                        <br />
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
                                                                    <Typography><b>Date of Birth</b>: {detail.dob}</Typography>
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
    )
}

export default Details;