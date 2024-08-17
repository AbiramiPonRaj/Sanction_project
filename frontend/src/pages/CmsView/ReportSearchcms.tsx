// // import React, { ReactNode, useState } from 'react';
// // import 'bootstrap/dist/css/bootstrap.min.css';
// // import { Container, Row, Col, Form, Button, Card } from 'react-bootstrap';
// // import DatePicker from 'react-datepicker';
// // import 'react-datepicker/dist/react-datepicker.css';
// // import { CardContent } from '@mui/material';
// // import Header from '../../../layouts/header/header';
// // import CmsSearchApiService from '../../../data/services/CmsSearch/cmsSearch-api-service';


// // interface CmsSearchData {
// //     searchDtos: SearchDtos[];
// // };

// // interface SearchDtos {
// //     id: number;
// //     searchId: number;
// //     name: string;
// //     matchingScore: number;
// //     fromDate: string;
// //     toDate: string;
// // };

// // interface SearchDetailsDataList {
// //     id: number;
// //     searchId: number;
// //     name: string;
// //     searchingScore: number;
// //     typeId: number;
// //     uid: number;
// //     fromDate: string;
// //     toDate: string;
// // };


// // function CmsSearch() {
// //     const calculateWeekRange = (date: Date): [Date, Date] => {
// //         const currentDate = new Date(date);
// //         const dayOfWeek = currentDate.getDay();

// //         const startDate = new Date(currentDate);
// //         startDate.setDate(currentDate.getDate() - dayOfWeek);

// //         const endDate = new Date(currentDate);
// //         endDate.setDate(currentDate.getDate() + (6 - dayOfWeek));

// //         return [startDate, endDate];
// //     };

// //     const [selectedOption, setSelectedOption] = useState<string>('daily');
// //     const [currentDate, setCurrentDate] = useState<Date>(new Date());
// //     const [startDate, setStartDate] = useState<Date>(new Date());
// //     const [endDate, setEndDate] = useState<Date>(new Date());
// //     const [highlightedWeek, setHighlightedWeek] = useState(calculateWeekRange(new Date()));
// //     const [searchPerformed, setSearchPerformed] = useState(false);
// // const [data, setData] = useState<CmsSearchData[]>([]);



// //     const handleOptionChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
// //         setSelectedOption(event.target.value);
// //         setCurrentDate(new Date());
// //         setStartDate(new Date());
// //         setEndDate(new Date());
// //     };

// //     const formatDate = (date: Date) => {
// //         const year = date.getFullYear();
// //         const month = date.getMonth();
// //         const day = date.getDate();
// //         return new Date(year, month, day);
// //     };

// //     function convert(str: string | number | Date) {
// //         const date = new Date(str);
// //         const year = date.getFullYear();
// //         const month = String(date.getMonth() + 1).padStart(2, '0');
// //         const day = String(date.getDate()).padStart(2, '0');
// //         return `${year}-${month}-${day}`;
// //     }
// //     const handleSearch = () => {
// //         const formattedStartDate = convert(startDate);
// //         const formattedEndDate = convert(endDate);

// //         console.log('Start Date:', formattedStartDate);
// //         console.log('End Date:', formattedEndDate);

// //         const startDateAsDate = new Date(formattedStartDate);
// //         const endDateAsDate = new Date(formattedEndDate);

// //         const apiService = new CmsSearchApiService();
// //         apiService.getCustomDate(startDateAsDate, endDateAsDate)
// //             .then((fetchedData: CmsSearchData[] | undefined) => {
// //                 if (fetchedData && Array.isArray(fetchedData)) {
// //                     setSearchPerformed(true);
// //                     const transformedData: CmsSearchData[] = fetchedData.map(entry => ({
// //                         searchDtos: entry.searchDtos ? entry.searchDtos.map(dto => ({
// //                             id: 0,
// //                             searchId: 0,
// //                             name: dto.name,
// //                             matchingScore: dto.matchingScore,
// //                             fromDate: dto.fromDate,
// //                             toDate: dto.toDate,
// //                         })) : [],
// //                     }));
// //                     console.log('transformedData:', transformedData);
// //                     setData(transformedData);
// //                 } else {
// //                     console.error('API response is empty or not in the expected format.');
// //                 }
// //             })
// //             .catch((error: { response: { status: any; data: any; }; request: any; message: any; }) => {
// //                 console.error('API request error:', error);
// //                 if (error.response) {
// //                     console.error('Request failed with status code:', error.response.status);
// //                     console.error('Response data:', error.response.data);
// //                 } else if (error.request) {
// //                     console.error('No response received. Request made but no response.');
// //                 } else {
// //                     console.error('Error setting up the request:', error.message);
// //                 }
// //             });
// //     };

// //     const handleStartChange = (date: Date) => {
// //         let newStartDate = new Date(date);
// //         if (selectedOption === 'weekly') {
// //             const [weekStart, weekEnd] = calculateWeekRange(newStartDate);
// //             setHighlightedWeek([weekStart, weekEnd]);
// //         } else if (selectedOption === 'monthly') {
// //             const firstDayOfMonth = new Date(newStartDate.getFullYear(), newStartDate.getMonth(), 1);
// //             const lastDayOfMonth = new Date(newStartDate.getFullYear(), newStartDate.getMonth() + 1, 0);
// //             setHighlightedWeek([firstDayOfMonth, lastDayOfMonth]);
// //         }
// //         setCurrentDate(newStartDate);
// //         setStartDate(newStartDate);
// //     };

// //     const handleEndChange = (date: Date) => {
// //         let newEndDate = new Date(date);
// //         if (selectedOption === 'weekly') {
// //             const [weekStart, weekEnd] = calculateWeekRange(newEndDate);
// //             setHighlightedWeek([weekStart, weekEnd]);
// //         } else if (selectedOption === 'monthly') {
// //             const firstDayOfMonth = new Date(newEndDate.getFullYear(), newEndDate.getMonth(), 1);
// //             const lastDayOfMonth = new Date(newEndDate.getFullYear(), newEndDate.getMonth() + 1, 0);
// //             setHighlightedWeek([firstDayOfMonth, lastDayOfMonth]);
// //         }
// //         setEndDate(newEndDate);
// //     };

// //     const getDisabledDates = (): [Date | null, Date | null] => {
// //         const today = new Date();
// //         let minDate: Date | null = new Date(today);
// //         let maxDate: Date | null = new Date(today);

// //         return [minDate, maxDate];
// //     };

// //     const disabledDates = getDisabledDates();

// //     return (
// //         <>
// //             <h4 style={{ marginTop: '6%', marginLeft: '2%' }}>Data Entry</h4>
// //             <Header />
// //             <Card border='10px' style={{ margin: '2%' }}>
// //                 <Container className="alertreport-container">
// //                     <CardContent>
// //                         <Form>
// //                             <Row>
// //                                 <Col xs={4}>
// //                                     <Form.Group>
// //                                         <Row>
// //                                             <Col>
// //                                                 <Form.Label>Start Date:</Form.Label>
// //                                                 <DatePicker
// //                                                     selected={startDate}
// //                                                     onChange={handleStartChange}
// //                                                     dateFormat="MMMM d, yyyy"
// //                                                     className="form-control"
// //                                                     disabledKeyboardNavigation
// //                                                     minDate={selectedOption === 'custom' ? null : new Date(1900, 0, 1)}
// //                                                     maxDate={selectedOption === 'custom' ? null : new Date(2100, 11, 31)}
// //                                                     highlightDates={highlightedWeek}
// //                                                 />
// //                                             </Col>
// //                                             <Col>
// //                                                 <Form.Label>End Date:</Form.Label>
// //                                                 <DatePicker
// //                                                     selected={endDate}
// //                                                     onChange={handleEndChange}
// //                                                     dateFormat="MMMM d, yyyy"
// //                                                     className="form-control"
// //                                                     disabledKeyboardNavigation
// //                                                     minDate={selectedOption === 'custom' ? null : new Date(1900, 0, 1)}
// //                                                     maxDate={selectedOption === 'custom' ? null : new Date(2100, 11, 31)}
// //                                                     highlightDates={highlightedWeek}
// //                                                 />
// //                                             </Col>
// //                                         </Row>
// //                                     </Form.Group>
// //                                 </Col>
// //                                 <Col xs={4}>
// //                                     <Button variant="primary" style={{ marginTop: '8%' }} onClick={handleSearch}>
// //                                         Apply Dates
// //                                     </Button>
// //                                 </Col>
// //                             </Row>
// //                         </Form>
// //                         <div className="current-date"></div>
// //                         {searchPerformed && (
// //                             <>
// //                                 {data.length === 0 ? (
// //                                     <p>No data available</p>
// //                                 ) : (
// //                                     <table className="table report-table">
// //                                         <thead>
// //                                             <tr>
// //                                                 <th>Sl no</th>
// //                                                 <th>Name</th>
// //                                                 <th>Matching Score</th>
// //                                             </tr>
// //                                         </thead>
// //                                         <tbody>
// //                                             {data.map((item: CmsSearchData, index: number) => (
// //                                                 item.searchDtos.map((searchItem: SearchDtos, innerIndex: number) => (
// //                                                     <tr key={`${index}-${innerIndex}`}>
// //                                                         <td>{innerIndex + 1}</td>
// //                                                         <td>{searchItem?.name}</td>
// //                                                         <td>{searchItem?.matchingScore}</td>
// //                                                     </tr>
// //                                                 ))
// //                                             ))}
// //                                         </tbody>
// //                                     </table>
// //                                 )}
// //                             </>
// //                         )}

// //                     </CardContent>
// //                 </Container>
// //             </Card>
// //         </>
// //     );
// // }
// // export default CmsSearch;


// import React, { useState } from 'react';
// import 'bootstrap/dist/css/bootstrap.min.css';
// import { Container, Row, Col, Form, Button, Card } from 'react-bootstrap';
// import DatePicker from 'react-datepicker';
// import 'react-datepicker/dist/react-datepicker.css';
// import { CardContent } from '@mui/material'; // Import CardContent
// import Header from '../../../layouts/header/header';
// import CmsSearchApiService from '../../../data/services/CmsSearch/cmsSearch-api-service';
// import { CmsSearchData } from '../../../data/services/CmsSearch/cmsSearch-payload';
// import {  Box } from '@mui/material';
// function CmsSearch() {
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
//     const [highlightedWeek, setHighlightedWeek] = useState<[Date, Date]>(calculateWeekRange(new Date()));
//     const [searchPerformed, setSearchPerformed] = useState(false);
//     const [searchData, setSearchData] = useState<CmsSearchData[]>([]);
   
   
//     const handleSearch = () => {
//         const apiService = new CmsSearchApiService();
//         const formattedStartDate = startDate;
//         const formattedEndDate = endDate;
    
//         apiService.getCustomDate(formattedStartDate, formattedEndDate)
//             .then((fetchedData: CmsSearchData[] | undefined) => {
//                 if (fetchedData && Array.isArray(fetchedData)) {
//                     setSearchPerformed(true);
//                     setSearchData(fetchedData);
//                 } else {
//                     console.error('API response is empty or not in the expected format.');
//                 }
//             })
//             .catch((error: any) => {
//                 console.error('API request error:', error);
//             });
//     };
    

//     const handleStartChange = (date: Date) => {
//         const [weekStart, weekEnd] = calculateWeekRange(date);
//         setHighlightedWeek([weekStart, weekEnd]);
        
//         setStartDate(date);
//     };

//     const handleEndChange = (date: Date) => {
//         const [weekStart, weekEnd] = calculateWeekRange(date);
//         setHighlightedWeek([weekStart, weekEnd]);
//         setEndDate(date);
//     };

//     return (
//         <>
//             <h4 style={{ marginTop: '6%', marginLeft: '2%' }}>Data Entry</h4>
//             <Box sx={{ display: 'flex' }}>
//             <Header />
//             <Box component="main" sx={{ flexGrow: 1, p: 3 }}>

//             <Card border='10px' style={{ margin: '2%' }}>
//                 <Container className="alertreport-container">
//                     <CardContent> {/* Use CardContent */}
//                         <Form>
//                             <Row>
//                                 <Col xs={4}>
//                                     <Form.Group>
//                                         <Row>
//                                             <Col>
//                                                 <Form.Label>Start Date:</Form.Label>
//                                                 <DatePicker
//                                                     selected={startDate}
//                                                     onChange={handleStartChange}
//                                                     dateFormat="MMMM d, yyyy"
//                                                     className="form-control"
//                                                     highlightDates={highlightedWeek}
//                                                 />
//                                             </Col>
//                                             <Col>
//                                                 <Form.Label>End Date:</Form.Label>
//                                                 <DatePicker
//                                                     selected={endDate}
//                                                     onChange={handleEndChange}
//                                                     dateFormat="MMMM d, yyyy"
//                                                     className="form-control"
//                                                     highlightDates={highlightedWeek}
//                                                 />
//                                             </Col>
//                                         </Row>
//                                     </Form.Group>
//                                 </Col>
//                                 <Col xs={4}>
//                                     <Button variant="primary" style={{ marginTop: '8%' }} onClick={handleSearch}>
//                                         Apply Dates
//                                     </Button>
//                                 </Col>
//                             </Row>
//                         </Form>
//                         <div className="current-date"></div>
//                         {searchPerformed && (
//                             <>
//                                 {searchData.length === 0 ? (
//                                     <p>No data available</p>
//                                 ) : (
//                                     <table className="table report-table">
//                                         <thead>
//                                             <tr>
//                                                 <th>Sl no</th>
//                                                 <th>Name</th>
//                                                 <th>Matching Score</th>
//                                             </tr>
//                                         </thead>
//                                         <tbody>
//                                             {searchData.map((item, index) => (
//                                                 item.searchDtos.map((searchItem, innerIndex) => (
//                                                     <tr key={`${index}-${innerIndex}`}>
//                                                         <td>{innerIndex + 1}</td>
//                                                         <td>{searchItem.name}</td>
//                                                         <td>{searchItem.searchingScore}</td>
//                                                     </tr>
//                                                 ))
//                                             ))}
//                                         </tbody>
//                                     </table>
//                                 )}
//                             </>
//                         )}

//                     </CardContent>
//                 </Container>
//             </Card>

//             </Box>
           

//             </Box>
            
//         </>
//     );
// }
// export default CmsSearch;
import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Container, Row, Col, Form, Button, Card } from 'react-bootstrap';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import { CardContent, Box } from '@mui/material';
import CmsSearchApiService from '../../data/services/CmsSearch/cmsSearch-api-service';
import { CmsSearchData } from '../../data/services/CmsSearch/cmsSearch-payload';
import Header from '../../layouts/header/header';

export interface PepSearchData {
    searchDtos: SearchDto[];
}

export interface SearchDto {
    name: string;
    searchingScore: number | null;
    uid: number;
    fromDate: string;
    toDate: string;
    searchDetailsDataList: SearchDetailsDataList[];
}

export interface SearchDetailsDataList {
    searchId: number;
    name: string;
    searchingScore: number;
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

    const [selectedOption, setSelectedOption] = useState<string>('daily');
    const [startDate, setStartDate] = useState<Date>(new Date());
    const [endDate, setEndDate] = useState<Date>(new Date());
    const [highlightedWeek, setHighlightedWeek] = useState(calculateWeekRange(new Date()));
    const [searchPerformed, setSearchPerformed] = useState(false);
    const [data, setData] = useState<PepSearchData[]>([]);

    const handleSearch = () => {
        const apiService = new CmsSearchApiService();
        apiService.getCustomDate(startDate, endDate)
            .then((fetchedData: CmsSearchData[]) => {
                setSearchPerformed(true);

                // Transform the fetched data
                const transformedData: CmsSearchData[] = fetchedData.map(entry => {
                    console.log('entry:', entry);

                    let searchDtos: SearchDto[] = [];
                    if (Array.isArray(entry.searchDtos)) {
                        searchDtos = entry.searchDtos;
                    } else if (entry.searchDtos && typeof entry.searchDtos === 'object') {
                        searchDtos = [entry.searchDtos as SearchDto];
                    }

                    const transformedEntry = {
                        searchDtos: searchDtos.map(dto => {
                            console.log('dto:', dto);
                            return {
                                ...dto,
                                searchDetailsData: Array.isArray(dto.searchDetailsDataList) ? dto.searchDetailsDataList : []
                            };
                        })
                    };
                    console.log('transformedEntry:', transformedEntry);
                    return transformedEntry;
                });

                // Log the transformed data to ensure it's correct
                console.log('transformedData:', transformedData);

                // Set the transformed data to the state variable
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
                                    <br></br>
                                    {data.length === 0 && searchPerformed && (
                                        <p>No data available</p>
                                    )}
                                    {data.length > 0 && (
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
                                                    {data.map((item: CmsSearchData, index: number) => (
                                                        <React.Fragment key={index}>
                                                            {item.searchDtos.map((dto: SearchDto, dtoIndex: number) => (
                                                                <React.Fragment key={`${index}-${dtoIndex}`}>
                                                                    {dto.searchDetailsDataList && dto.searchDetailsDataList.length > 0 ? (
                                                                        dto.searchDetailsDataList.map((searchItem: SearchDetailsDataList, innerIndex: number) => (
                                                                            <tr key={`${index}-${dtoIndex}-${innerIndex}`}>
                                                                                <td>{innerIndex + 1}</td>
                                                                                <td>{searchItem.name}</td>
                                                                                <td>{searchItem.searchingScore}</td>
                                                                            </tr>
                                                                        ))
                                                                    ) : (
                                                                        <tr>
                                                                            <td colSpan={3}>No data available</td>
                                                                        </tr>
                                                                    )}
                                                                </React.Fragment>
                                                            ))}
                                                        </React.Fragment>
                                                    ))}
                                                </tbody>
                                            </table>
                                        </div>
                                    )}
                                </CardContent>
                            </Container>
                        </Card>
                    </Box>
                </Box>
            </Box>
        </>
    );
}

export default ReportSearch;


