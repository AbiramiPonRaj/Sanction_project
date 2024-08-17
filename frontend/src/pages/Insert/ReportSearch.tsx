// import React, { useState } from 'react';
// import 'bootstrap/dist/css/bootstrap.min.css';
// import { Container, Row, Col, Form, Button, Card } from 'react-bootstrap';
// import DatePicker from 'react-datepicker';
// import 'react-datepicker/dist/react-datepicker.css';
// import { CardContent, Box, Modal, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Typography, Paper, DialogTitle, DialogContent, DialogActions } from '@mui/material';
// import Header from '../../layouts/header/header';
// import PepSearchApiService from '../../data/services/pep/PepSearch/pepSearch-api-service';
// import Dialog from '@mui/material/Dialog';
// export interface PepSearchData {
//     searchDtos: SearchDto[];
// }

// export interface SearchDto {
//     id: number;
//     name: string;
//     searchingScore: number | null;
//     uid: number;
//     fromDate: string;
//     toDate: string;
//     searchDetailsData: SearchDetailsData[];
// }

// export interface SearchDetailsData {
//     id: number;
//     searchId: number;
//     name: string;
//     matchingScore: number;
//     uid: number;
//     fromDate: string;
//     toDate: string;
// }

// function ReportSearch() {
//     const calculateWeekRange = (date: Date): [Date, Date] => {
//         const currentDate = new Date(date);
//         const dayOfWeek = currentDate.getDay();

//         const startDate = new Date(currentDate);
//         startDate.setDate(currentDate.getDate() - dayOfWeek);

//         const endDate = new Date(currentDate);
//         endDate.setDate(currentDate.getDate() + (6 - dayOfWeek));

//         return [startDate, endDate];
//     };
//     const [serialNumber, setSerialNumber] = useState(1);

//     const [selectedOption, setSelectedOption] = useState<string>('daily');
//     const [startDate, setStartDate] = useState<Date>(new Date());
//     const [endDate, setEndDate] = useState<Date>(new Date());
//     const [highlightedWeek, setHighlightedWeek] = useState(calculateWeekRange(new Date()));
//     const [searchPerformed, setSearchPerformed] = useState(false);
//     const [data, setData] = useState<PepSearchData[]>([]);
//     const [showModal, setShowModal] = useState(false);
//     const [selectedSearchDetails, setSelectedSearchDetails] = useState<SearchDetailsData[]>([]);

//     const handleSearch = () => {
//         const apiService = new PepSearchApiService();
//         apiService.getCustomDate(startDate, endDate)
//             .then((fetchedData: PepSearchData[]) => {
//                 setSearchPerformed(true);

//                 // Transform the fetched data
//                 const transformedData: PepSearchData[] = fetchedData.map(entry => {
//                     let searchDtos: SearchDto[] = [];
//                     if (Array.isArray(entry.searchDtos)) {
//                         searchDtos = entry.searchDtos;
//                     } else if (entry.searchDtos && typeof entry.searchDtos === 'object') {
//                         searchDtos = [entry.searchDtos as SearchDto];
//                     }

//                     const transformedEntry = {
//                         searchDtos: searchDtos.map(dto => ({
//                             ...dto,
//                             searchDetailsData: Array.isArray(dto.searchDetailsData) ? dto.searchDetailsData : []
//                         }))
//                     };
//                     return transformedEntry;
//                 });

//                 setData(transformedData);
//             })
//             .catch((error: any) => {
//                 console.error('API request error:', error);
//             });
//     };

//     const handleStartChange = (date: Date) => {
//         const newStartDate = new Date(date);
//         const [weekStart, weekEnd] = calculateWeekRange(newStartDate);
//         setHighlightedWeek([weekStart, weekEnd]);
//         setStartDate(newStartDate);
//     };

//     const handleEndChange = (date: Date) => {
//         const newEndDate = new Date(date);
//         const [weekStart, weekEnd] = calculateWeekRange(newEndDate);
//         setHighlightedWeek([weekStart, weekEnd]);
//         setEndDate(newEndDate);
//     };

// const handleRowClick = (searchDetails: SearchDetailsData[]) => {
//     setSelectedSearchDetails(searchDetails);

//     setShowModal(true);
// };

// const handleCloseModal = () => {
//     setShowModal(false);
//     setSelectedSearchDetails([]);
// };

//     return (
//         <>
//             <style>
//                 {`
//                     .table-wrapper {
//                         max-height: 400px; /* Adjust the height as needed */
//                         overflow-y: auto;
//                         position: relative;
//                     }

//                     .table-fixed-header thead {
//                         position: sticky;
//                         top: 0;
//                         background: white;
//                         z-index: 1;
//                     }

//                     .table-fixed-header th {
//                         background: white;
//                     }
//                 `}
//             </style>
//             <Box sx={{ display: 'flex' }}>
//                 <Header />
//                 <Box component="main" sx={{ flexGrow: 1, p: 3 }}>
//                     <Box m={2} style={{ marginTop: '5%' }}>
//                         <Typography variant="h4" style={{ marginTop: '6%', marginLeft: '2%' }}>Data Entry</Typography>
//                         <Card style={{ margin: '2%' }}>
//                             <Container className="alertreport-container">
//                                 <CardContent>
//                                     <Form>
//                                         <Row>
//                                             <Col xs={4}>
//                                                 <Form.Group>
//                                                     <Row>
//                                                         <Col>
//                                                             <Form.Label>Start Date:</Form.Label>
//                                                             <DatePicker
//                                                                 selected={startDate}
//                                                                 onChange={handleStartChange}
//                                                                 dateFormat="MMMM d, yyyy"
//                                                                 className="form-control"
//                                                                 disabledKeyboardNavigation
//                                                             />
//                                                         </Col>
//                                                         <Col>
//                                                             <Form.Label>End Date:</Form.Label>
//                                                             <DatePicker
//                                                                 selected={endDate}
//                                                                 onChange={handleEndChange}
//                                                                 dateFormat="MMMM d, yyyy"
//                                                                 className="form-control"
//                                                                 disabledKeyboardNavigation
//                                                             />
//                                                         </Col>
//                                                     </Row>
//                                                 </Form.Group>
//                                             </Col>
//                                             <Col xs={4}>
//                                                 <Button variant="primary" style={{ marginTop: '8%' }} onClick={handleSearch}>
//                                                     Apply Dates
//                                                 </Button>
//                                             </Col>
//                                         </Row>
//                                     </Form>
//                                     <br></br>
//                                     {data.length === 0 && searchPerformed && (
//                                         <Typography>No data available</Typography>
//                                     )}
// {data.length > 0 && (
//     <div className="table-wrapper">
//         <TableContainer style={{ maxHeight: '400px', overflow: 'auto' }}>
//             <Table size="small" aria-label="a dense table" style={{ margin: '0 auto' }}>
//                 <TableHead>
//                     <TableRow>
//                         <TableCell style={{ position: 'sticky', top: 0, backgroundColor: 'white' }}>Sl no</TableCell>
//                         <TableCell style={{ position: 'sticky', top: 0, backgroundColor: 'white', }}>Name</TableCell>
//                         <TableCell style={{ position: 'sticky', top: 0, backgroundColor: 'white' }}>Searching Score</TableCell>
//                     </TableRow>
//                 </TableHead>
//                 <TableBody>
//                     {(() => {
//                         let serialNumber = 0; // Initialize serial number counter
//                         return data.flatMap((item: PepSearchData) =>
//                             item.searchDtos.map((dto: SearchDto) => {
//                                 serialNumber++; // Increment serial number for each row
//                                 return (
//                                     <TableRow key={dto.uid} onClick={() => handleRowClick(dto.searchDetailsData)}>
//                                         <TableCell>{serialNumber}</TableCell> {/* Display serial number */}
//                                         <TableCell style={{ cursor: 'pointer' }}>{dto.name}</TableCell>
//                                         <TableCell>{dto.searchingScore !== null ? dto.searchingScore : "null"}</TableCell> {/* Conditional rendering */}
//                                     </TableRow>
//                                 );
//                             })
//                         );
//                     })()}

//                 </TableBody>
//             </Table>
//         </TableContainer>
//     </div>
// )}
//                                 </CardContent>
//                             </Container>
//                         </Card>
//                     </Box>
//                 </Box>
//             </Box>


//             <Dialog open={showModal} onClose={handleCloseModal} fullWidth
//                 maxWidth="md">
//                 <DialogTitle>Search Details</DialogTitle>
//                 <DialogContent >
//                     {selectedSearchDetails.length > 0 ? (
//                         <TableContainer style={{ maxHeight: '400px', overflow: 'auto' }}>
//                             <Table size="small" aria-label="a dense table" style={{ margin: '0 auto' }}>
//                                 <TableHead>
//                                     <TableRow>
//                                         <TableCell style={{ position: 'sticky', top: 0, backgroundColor: 'white' }}>Search ID</TableCell>
//                                         <TableCell style={{ position: 'sticky', top: 0, backgroundColor: 'white' }}>Name</TableCell>
//                                         <TableCell style={{ position: 'sticky', top: 0, backgroundColor: 'white' }}>Matching Score</TableCell>

//                                     </TableRow>
//                                 </TableHead>
//                                 <TableBody>
//                                     {selectedSearchDetails.map((detail, index) => (
//                                         <TableRow key={index}>
//                                             <TableCell>{detail.searchId}</TableCell>
//                                             <TableCell>{detail.name}</TableCell>
//                                             <TableCell>{detail.matchingScore}</TableCell>

//                                         </TableRow>
//                                     ))}
//                                 </TableBody>
//                             </Table>
//                         </TableContainer>
//                     ) : (
//                         <p>No details available</p>
//                     )}
//                 </DialogContent>
//                 <DialogActions>
//                     <Button variant="contained" onClick={handleCloseModal}>Close</Button>
//                 </DialogActions>
//             </Dialog>


//         </>
//     );
// }

// export default ReportSearch;

import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Container, Row, Col, Form, Button, Card } from 'react-bootstrap';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import { CardContent, Box, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Typography, Paper, DialogTitle, DialogContent, DialogActions } from '@mui/material';
import Header from '../../layouts/header/header';
import ViewService from '../../data/services/viewpage/view_api_service';
import Dialog from '@mui/material/Dialog';

export interface SanctionSearchData {
    searchData: SearchData[];
}

export interface SearchData {
    name: string;
    matchingScore: number | null;
    uid: number;
    typeId: number;
    listId: number;
    stateId: number;
    countryId: number;
    levelId: number;
    fromDate: string;
    toDate: string;
    hitRecordData: HitRecordData[];
}

export interface HitRecordData {
    searchId: number;
    name: string;
    matchingScore: number;
    uid: number;
    criminalId: number;
    display: string;
    statusNowId: number;
    cycleId: number;
    fromDate: string;
    toDate: string;
}

function SanctionSearch() {
    const calculateWeekRange = (date: Date): [Date, Date] => {
        const currentDate = new Date(date);
        const dayOfWeek = currentDate.getDay();

        const startDate = new Date(currentDate);
        startDate.setDate(currentDate.getDate() - dayOfWeek);

        const endDate = new Date(currentDate);
        endDate.setDate(currentDate.getDate() + (6 - dayOfWeek));

        return [startDate, endDate];
    };

    const [selectedOption, setSelectedOption] = useState<string>('daily');
    const [startDate, setStartDate] = useState<Date>(new Date());
    const [endDate, setEndDate] = useState<Date>(new Date());
    const [highlightedWeek, setHighlightedWeek] = useState(calculateWeekRange(new Date()));
    const [searchPerformed, setSearchPerformed] = useState(false);
    const [data, setData] = useState<SanctionSearchData[]>([]);
    const [showModal, setShowModal] = useState(false);
    const [selectedSearchDetails, setSelectedSearchDetails] = useState<HitRecordData[]>([]);
    const handleRowClick = (searchDetails: HitRecordData[]) => {
        setSelectedSearchDetails(searchDetails);

        setShowModal(true);
    };
    const handleCloseModal = () => {
        setShowModal(false);
        setSelectedSearchDetails([]);
    };
    const handleSearch = () => {
        const apiService = new ViewService();
        apiService.getCustomDate(startDate, endDate)
            .then((fetchedData: SanctionSearchData[]) => {
                setSearchPerformed(true);

                // Normalize the fetched data
                const normalizedData: SanctionSearchData[] = fetchedData.map(entry => {
                    let searchDataArray: SearchData[] = [];

                    if (Array.isArray(entry.searchData)) {
                        searchDataArray = entry.searchData;
                    } else if (typeof entry.searchData === 'object') {
                        searchDataArray = [entry.searchData as SearchData];
                    }

                    searchDataArray = searchDataArray.map(dto => ({
                        ...dto,
                        hitRecordData: Array.isArray(dto.hitRecordData) ? dto.hitRecordData : []
                    }));

                    return { searchData: searchDataArray };
                });

                // Log the normalized data to ensure it's correct
                console.log('normalizedData:', normalizedData);

                // Set the normalized data to the state variable
                setData(normalizedData);
            })
            .catch((error: any) => {
                console.error('API request error:', error);
            });
    };

    const handleStartChange = (date: Date) => {
        const newStartDate = new Date(date);
        const [weekStart, weekEnd] = calculateWeekRange(newStartDate);
        setHighlightedWeek([weekStart, weekEnd]);
        setStartDate(newStartDate);
    };

    const handleEndChange = (date: Date) => {
        const newEndDate = new Date(date);
        const [weekStart, weekEnd] = calculateWeekRange(newEndDate);
        setHighlightedWeek([weekStart, weekEnd]);
        setEndDate(newEndDate);
    };

    return (
        <>
            <style>
                {`
                    .table-wrapper {
                        max-height: 400px; /* Adjust the height as needed */
                        overflow-y: auto;
                        position: relative;
                    }

                    .table-fixed-header thead {
                        position: sticky;
                        top: 0;
                        background: white;
                        z-index: 1;
                    }

                    .table-fixed-header th {
                        background: white;
                    }
                `}
            </style>
            <Box sx={{ display: 'flex' }}>
                <Header />
                <Box component="main" sx={{ flexGrow: 1, p: 3 }}>
                    <Box m={2} style={{ marginTop: '5%' }}>
                        <h4 style={{ marginTop: '6%', marginLeft: '2%' }}>Data Entry</h4>
                        <Card border='10px' style={{ margin: '2%' }}>
                            <Container className="alertreport-container">
                                <CardContent>
                                    <Form>
                                        <Row>
                                            <Col xs={4}>
                                                <Form.Group>
                                                    <Row>
                                                        <Col>
                                                            <Form.Label>Start Date:</Form.Label>
                                                            <DatePicker
                                                                selected={startDate}
                                                                onChange={handleStartChange}
                                                                dateFormat="MMMM d, yyyy"
                                                                className="form-control"
                                                                disabledKeyboardNavigation
                                                            />
                                                        </Col>
                                                        <Col>
                                                            <Form.Label>End Date:</Form.Label>
                                                            <DatePicker
                                                                selected={endDate}
                                                                onChange={handleEndChange}
                                                                dateFormat="MMMM d, yyyy"
                                                                className="form-control"
                                                                disabledKeyboardNavigation
                                                            />
                                                        </Col>
                                                    </Row>
                                                </Form.Group>
                                            </Col>
                                            <Col xs={4}>
                                                <Button variant="primary" style={{ marginTop: '8%' }} onClick={handleSearch}>
                                                    Apply Dates
                                                </Button>
                                            </Col>
                                        </Row>
                                    </Form>
                                    <br />
                                    {data.length === 0 && searchPerformed && (
                                        <p>No data available</p>
                                    )}

                                    {/* {data.length > 0 && (
                                        <div className="table-wrapper">
                                            <table className="table report-table table-fixed-header">
                                                <thead>
                                                    <tr>
                                                        <th>Sl no</th>
                                                        <th>Name</th>
                                                        <th>Matching Score</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    {data.map((item: SanctionSearchData, index: number) => (
                                                        <React.Fragment key={index}>
                                                            {item.searchData.map((dto: SearchData, dtoIndex: number) => (
                                                                <React.Fragment key={`${index}-${dtoIndex}`}>
                                                                    {dto.hitRecordData.map((searchItem: HitRecordData, innerIndex: number) => (
                                                                        <tr key={`${index}-${dtoIndex}-${innerIndex}`}>
                                                                            <td>{innerIndex + 1}</td>
                                                                            <td>{searchItem.name}</td>
                                                                            <td>{searchItem.matchingScore}</td>
                                                                        </tr>
                                                                    ))}
                                                                    {dto.hitRecordData.length === 0 && (
                                                                        <tr>
                                                                            <td>1</td>
                                                                            <td>{dto.name}</td>
                                                                            <td>{dto.matchingScore}</td>
                                                                        </tr>
                                                                    )}
                                                                </React.Fragment>
                                                            ))}
                                                        </React.Fragment>
                                                    ))}
                                                </tbody>
                                            </table>
                                        </div>
                                    )} */}
                                    {data.length > 0 && (
                                        <div className="table-wrapper">
                                            <TableContainer style={{ maxHeight: '400px', overflow: 'auto' }}>
                                                <Table size="small" aria-label="a dense table" style={{ margin: '0 auto' }}>
                                                    <TableHead>
                                                        <TableRow>
                                                            <TableCell style={{ position: 'sticky', top: 0, backgroundColor: 'white' }}>Sl no</TableCell>
                                                            <TableCell style={{ position: 'sticky', top: 0, backgroundColor: 'white', }}>Name</TableCell>
                                                            <TableCell style={{ position: 'sticky', top: 0, backgroundColor: 'white' }}>Matching Score</TableCell>
                                                        </TableRow>
                                                    </TableHead>
                                                    <TableBody>
                                                        {data.map((item: SanctionSearchData, index: number) => (
                                                            <React.Fragment key={index}>
                                                                {item.searchData.map((dto: SearchData, dtoIndex: number) => (
                                                                    <React.Fragment key={`${index}-${dtoIndex}`}>
                                                                        {dto.hitRecordData.map((searchItem: HitRecordData, innerIndex: number) => (
                                                                            <TableRow key={`${index}-${dtoIndex}-${innerIndex}`} onClick={() => handleRowClick(dto.hitRecordData)}>
                                                                                <TableCell>{innerIndex + 1}</TableCell>
                                                                                <TableCell style={{ cursor: 'pointer' }}>{searchItem.name}</TableCell>
                                                                                <TableCell>{searchItem.matchingScore}</TableCell>
                                                                            </TableRow>
                                                                        ))}
                                                                        {dto.hitRecordData.length === 0 && (
                                                                            <TableRow>
                                                                                <TableCell>1</TableCell>
                                                                                <TableCell>{dto.name}</TableCell>
                                                                                <TableCell>{dto.matchingScore}</TableCell>
                                                                            </TableRow>
                                                                        )}
                                                                    </React.Fragment>
                                                                ))}
                                                            </React.Fragment>
                                                        ))}
                                                    </TableBody>
                                                </Table>
                                            </TableContainer>
                                        </div>
                                    )}


                                </CardContent>
                            </Container>
                        </Card>
                    </Box>
                </Box>
            </Box>
            <Dialog open={showModal} onClose={handleCloseModal} fullWidth
                maxWidth="md">
                <DialogTitle>Search Details</DialogTitle>
                <DialogContent >
                    {selectedSearchDetails.length > 0 ? (
                        <TableContainer style={{ maxHeight: '400px', overflow: 'auto' }}>
                            <Table size="small" aria-label="a dense table" style={{ margin: '0 auto' }}>
                                <TableHead>
                                    <TableRow>
                                        <TableCell style={{ position: 'sticky', top: 0, backgroundColor: 'white' }}>Search ID</TableCell>
                                        <TableCell style={{ position: 'sticky', top: 0, backgroundColor: 'white' }}>Name</TableCell>
                                        <TableCell style={{ position: 'sticky', top: 0, backgroundColor: 'white' }}>Matching Score</TableCell>

                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    {selectedSearchDetails.map((detail, index) => (
                                        <TableRow key={index}>
                                            <TableCell>{detail.searchId}</TableCell>
                                            <TableCell>{detail.name}</TableCell>
                                            <TableCell>{detail.matchingScore}</TableCell>

                                        </TableRow>
                                    ))}
                                </TableBody>
                            </Table>
                        </TableContainer>
                    ) : (
                        <p>No details available</p>
                    )}
                </DialogContent>
                <DialogActions>
                    <Button variant="contained" onClick={handleCloseModal}>Close</Button>
                </DialogActions>
            </Dialog>
        </>
    );
}

export default SanctionSearch;

