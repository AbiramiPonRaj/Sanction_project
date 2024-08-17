

import React from 'react';
import Container from 'react-bootstrap/Container';
import Header from '../../layouts/header/header';
import { Box, Grid, Avatar, Typography } from '@mui/material';
import sanctionImage from '../../../src/assets/sanction.png';
import cmsImage from '../../../src/assets/cms.png';
import pepImage from '../../../src/assets/pep.jpg';
import aaml from '../../../src/assets/aaml.png';
import bank from '../../../src/assets/bank.jpg';
import { useNavigate } from 'react-router-dom';
import { Card } from 'react-bootstrap';

const Dashboard = () => {

    const navigate = useNavigate();

    const handlepepClick = () => {
        navigate('/PepLevel1FirstReview');
    };


    const handlecmsClick = () => {
        navigate('/CmsLevel1FirstReview');
    };

    const handlesanctionClick = () => {
        navigate('/SanLevel1FirstReview');
    };
    const handleamlClick = () => {
        navigate('/Amldashboard');
    };

    const handlebtmsClick = () => {
        navigate('/Alertview');
    };

    const handlesearchClick = () => {
        navigate('/Result');
    };

    return (
        <>
            <Box sx={{ display: 'flex' }}>
                <Header />
                <Box component="main" sx={{ flexGrow: 1, p: 3 }}>
                    <Container style={{ maxWidth: 'none', backgroundColor: 'white' }}>
                        <Box m={2}>
                            <h4>DASHBOARD</h4>
                            <div className="d-flex justify-content-center">
                                <div>
                                    <div className="card-body">
                                        <Grid container spacing={3} style={{ margin: '20px' }}>


                                            <Grid item sm={4} >
                                                <Card style={{ padding: '9%', boxShadow: 'rgb(0 0 0 / 28%) 0px 4px 8px', alignItems: 'center', width: '275px' }}>
                                                    <Avatar alt="PEP" src={pepImage} style={{ width: '100px', height: '100px', cursor: 'pointer' }} onClick={handlepepClick} />
                                                    <Typography onClick={handlepepClick} style={{ cursor: 'pointer' }}>PEP</Typography>
                                                </Card>
                                            </Grid>


                                            <Grid item sm={4} >
                                                <Card style={{ padding: '9%', boxShadow: 'rgb(0 0 0 / 28%) 0px 4px 8px', alignItems: 'center', width: '275px' }}>

                                                    <Avatar alt="CMS" src={cmsImage} style={{ width: '100px', height: '100px', cursor: 'pointer' }} onClick={handlecmsClick} />
                                                    <Typography onClick={handlecmsClick} style={{ cursor: 'pointer' }}>CMS</Typography>
                                                </Card>
                                            </Grid>

                                            <Grid item sm={4} >
                                                <Card style={{ padding: '9%', boxShadow: 'rgb(0 0 0 / 28%) 0px 4px 8px', alignItems: 'center', width: '275px' }}>

                                                    <Avatar alt="Sanction" src={sanctionImage} style={{ width: '100px', height: '100px', cursor: 'pointer' }} onClick={handlesanctionClick} />
                                                    <Typography onClick={handlesanctionClick} style={{ cursor: 'pointer' }} >Sanction</Typography>
                                                </Card>
                                            </Grid>
                                        </Grid>
                                        <br></br>
                                        <Grid container spacing={3} style={{ margin: '20px' }}>
                                            <Grid item xs={12} sm={4}  >
                                                <Card style={{ padding: '9%', boxShadow: 'rgb(0 0 0 / 28%) 0px 4px 8px', alignItems: 'center', width: '275px' }}>

                                                    <Avatar alt="AML" src={aaml} onClick={handleamlClick}  style={{ width: '100px', height: '100px', cursor: 'pointer' }}/>
                                                    <Typography onClick={handleamlClick} style={{ cursor: 'pointer' }}>AML</Typography>
                                                </Card>
                                            </Grid>
                                            <Grid item xs={12} sm={4} >
                                                <Card style={{ padding: '9%', boxShadow: 'rgb(0 0 0 / 28%) 0px 4px 8px', alignItems: 'center', width: '275px' }}>

                                                    <Avatar alt="BTMS" src={bank} style={{ width: '100px', height: '100px', cursor: 'pointer' }} onClick={handlebtmsClick}/>
                                                    <Typography onClick={handlebtmsClick} style={{ cursor: 'pointer' }}>BTMS</Typography>
                                                </Card>
                                            </Grid>
                                            <Grid item xs={12} sm={4} >
                                                <Card style={{ padding: '9%', boxShadow: 'rgb(0 0 0 / 28%) 0px 4px 8px', alignItems: 'center', width: '275px' }}>

                                                    <Avatar alt="Negative Search" src={bank}style={{ width: '100px', height: '100px', cursor: 'pointer' }} onClick={handlesearchClick}/>
                                                    <Typography  onClick={handlesearchClick} style={{ cursor: 'pointer' }}>Negative Search</Typography>
                                                </Card>
                                            </Grid>

                                        </Grid>
                                    </div>
                                </div>
                            </div>
                        </Box>
                    </Container>
                </Box>
            </Box>
        </>
    );
}

export default Dashboard;
