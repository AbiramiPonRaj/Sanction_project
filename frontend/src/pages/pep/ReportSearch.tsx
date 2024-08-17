


// import React, { useState } from 'react';
// import 'bootstrap/dist/css/bootstrap.min.css';
// import { Container, Row, Col, Form, Button, Card, Modal, Table } from 'react-bootstrap';
// import DatePicker from 'react-datepicker';
// import 'react-datepicker/dist/react-datepicker.css';
// import { CardContent, Box } from '@mui/material';
// import Header from '../../layouts/header/header';
// import PepSearchApiService from '../../data/services/pep/PepSearch/pepSearch-api-service';

// export interface PepSearchData {
//     searchDtos: SearchDto[];
// }

// export interface SearchDto {
//     name: string;
//     searchingScore: number | null;
//     uid: number;
//     fromDate: string;
//     toDate: string;
//     searchDetailsData: SearchDetailsData[];
// }

// export interface SearchDetailsData {
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

//     const handleRowClick = (searchDetails: SearchDetailsData[]) => {
//         setSelectedSearchDetails(searchDetails);
//         setShowModal(true);
//     };

//     const handleCloseModal = () => {
//         setShowModal(false);
//         setSelectedSearchDetails([]);
//     };

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
//                         <h4 style={{ marginTop: '6%', marginLeft: '2%' }}>Data Entry</h4>
//                         <Card border='10px' style={{ margin: '2%' }}>
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
//                                         <p>No data available</p>
//                                     )}
//                                     {data.length > 0 && (
//                                         <div className="table-wrapper">
//                                             <table className="table report-table table-fixed-header">
//                                                 <thead>
//                                                     <tr>
//                                                         <th>Sl no</th>
//                                                         <th>Name</th>
//                                                         <th>Searching Score</th>
//                                                     </tr>
//                                                 </thead>
//                                                 <tbody>
//                                                     {(() => {
//                                                         let serialNumber = 1;
//                                                         return data.flatMap((item: PepSearchData) =>
//                                                             item.searchDtos.map((dto: SearchDto) => (
//                                                                 <tr key={dto.uid} onClick={() => handleRowClick(dto.searchDetailsData)}>
//                                                                     <td>{serialNumber++}</td>
//                                                                     <td>{dto.name}</td>
//                                                                     <td>{dto.searchingScore}</td>
//                                                                 </tr>
//                                                             ))
//                                                         );
//                                                     })()}
//                                                 </tbody>
//                                             </table>
//                                         </div>
//                                     )}
//                                 </CardContent>
//                             </Container>
//                         </Card>
//                     </Box>
//                 </Box>
//             </Box>

//             <Modal show={showModal} onHide={handleCloseModal}>
//                 <Modal.Header closeButton>
//                     <Modal.Title>Search Details</Modal.Title>
//                 </Modal.Header>
//                 <Modal.Body>
//                     {selectedSearchDetails.length > 0 ? (
//                         <Table striped bordered hover>
//                             <thead>
//                                 <tr>
//                                     <th>Search ID</th>
//                                     <th>Name</th>
//                                     <th>Matching Score</th>
//                                     <th>UID</th>
//                                     <th>From Date</th>
//                                     <th>To Date</th>
//                                 </tr>
//                             </thead>
//                             <tbody>
//                                 {selectedSearchDetails.map((detail, index) => (
//                                     <tr key={index}>
//                                         <td>{detail.searchId}</td>
//                                         <td>{detail.name}</td>
//                                         <td>{detail.matchingScore}</td>
//                                         <td>{detail.uid}</td>
//                                         <td>{detail.fromDate}</td>
//                                         <td>{detail.toDate}</td>
//                                     </tr>
//                                 ))}
//                             </tbody>
//                         </Table>
//                     ) : (
//                         <p>No details available</p>
//                     )}
//                 </Modal.Body>
//                 <Modal.Footer>
//                     <Button variant="secondary" onClick={handleCloseModal}>
//                         Close
//                     </Button>
//                 </Modal.Footer>
//             </Modal>
//         </>
//     );
// }

// export default ReportSearch;

import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Container, Row, Col, Form, Button, Card } from 'react-bootstrap';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import { CardContent, Box, Modal, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Typography, Paper, DialogTitle, DialogContent, DialogActions } from '@mui/material';
import Header from '../../layouts/header/header';
import PepSearchApiService from '../../data/services/pep/PepSearch/pepSearch-api-service';
import Dialog from '@mui/material/Dialog';
export interface PepSearchData {
    searchDtos: SearchDto[];
}

export interface SearchDto {
    id: number;
    name: string;
    searchingScore: number | null;
    uid: number;
    fromDate: string;
    toDate: string;
    searchDetailsData: SearchDetailsData[];
}

export interface SearchDetailsData {
    id: number;
    searchId: number;
    name: string;
    matchingScore: number;
    uid: number;
    fromDate: string;
    toDate: string;
}

function ReportSearch() {
    const calculateWeekRange = (date: Date): [Date, Date] => {
        const currentDate = new Date(date);
        const dayOfWeek = currentDate.getDay();

        const startDate = new Date(currentDate);
        startDate.setDate(currentDate.getDate() - dayOfWeek);

        const endDate = new Date(currentDate);
        endDate.setDate(currentDate.getDate() + (6 - dayOfWeek));

        return [startDate, endDate];
    };
    const [serialNumber, setSerialNumber] = useState(1);

    const [selectedOption, setSelectedOption] = useState<string>('daily');
    const [startDate, setStartDate] = useState<Date>(new Date());
    const [endDate, setEndDate] = useState<Date>(new Date());
    const [highlightedWeek, setHighlightedWeek] = useState(calculateWeekRange(new Date()));
    const [searchPerformed, setSearchPerformed] = useState(false);
    const [data, setData] = useState<PepSearchData[]>([]);
    const [showModal, setShowModal] = useState(false);
    const [selectedSearchDetails, setSelectedSearchDetails] = useState<SearchDetailsData[]>([]);

    const handleSearch = () => {
        const apiService = new PepSearchApiService();
        apiService.getCustomDate(startDate, endDate)
            .then((fetchedData: PepSearchData[]) => {
                setSearchPerformed(true);

                // Transform the fetched data
                const transformedData: PepSearchData[] = fetchedData.map(entry => {
                    let searchDtos: SearchDto[] = [];
                    if (Array.isArray(entry.searchDtos)) {
                        searchDtos = entry.searchDtos;
                    } else if (entry.searchDtos && typeof entry.searchDtos === 'object') {
                        searchDtos = [entry.searchDtos as SearchDto];
                    }

                    const transformedEntry = {
                        searchDtos: searchDtos.map(dto => ({
                            ...dto,
                            searchDetailsData: Array.isArray(dto.searchDetailsData) ? dto.searchDetailsData : []
                        }))
                    };
                    return transformedEntry;
                });

                setData(transformedData);
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

    const handleRowClick = (searchDetails: SearchDetailsData[]) => {
        setSelectedSearchDetails(searchDetails);

        setShowModal(true);
    };

    const handleCloseModal = () => {
        setShowModal(false);
        setSelectedSearchDetails([]);
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
                        <Typography variant="h4" style={{ marginTop: '6%', marginLeft: '2%' }}>Data Entry</Typography>
                        <Card style={{ margin: '2%' }}>
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
                                    <br></br>
                                    {data.length === 0 && searchPerformed && (
                                        <Typography>No data available</Typography>
                                    )}
                                    {data.length > 0 && (
                                        <div className="table-wrapper">
                                            <TableContainer style={{ maxHeight: '400px', overflow: 'auto' }}>
                                                <Table size="small" aria-label="a dense table" style={{ margin: '0 auto' }}>
                                                    <TableHead>
                                                        <TableRow>
                                                            <TableCell style={{ position: 'sticky', top: 0, backgroundColor: 'white' }}>Sl no</TableCell>
                                                            <TableCell style={{ position: 'sticky', top: 0, backgroundColor: 'white', }}>Name</TableCell>
                                                            <TableCell style={{ position: 'sticky', top: 0, backgroundColor: 'white' }}>Searching Score</TableCell>
                                                        </TableRow>
                                                    </TableHead>
                                                    <TableBody>
                                                        {(() => {
                                                            let serialNumber = 0; // Initialize serial number counter
                                                            return data.flatMap((item: PepSearchData) =>
                                                                item.searchDtos.map((dto: SearchDto) => {
                                                                    serialNumber++; // Increment serial number for each row
                                                                    return (
                                                                        <TableRow key={dto.uid} onClick={() => handleRowClick(dto.searchDetailsData)}>
                                                                            <TableCell>{serialNumber}</TableCell> {/* Display serial number */}
                                                                            <TableCell style={{ cursor: 'pointer' }}>{dto.name}</TableCell>
                                                                            <TableCell>{dto.searchingScore !== null ? dto.searchingScore : "null"}</TableCell> {/* Conditional rendering */}
                                                                        </TableRow>
                                                                    );
                                                                })
                                                            );
                                                        })()}

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

export default ReportSearch;


