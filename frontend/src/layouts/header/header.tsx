

// import React, { useEffect, useState } from 'react';
// import { styled, useTheme, Theme, CSSObject } from '@mui/material/styles';
// import Box from '@mui/material/Box';
// import MuiDrawer from '@mui/material/Drawer';
// import MuiAppBar, { AppBarProps as MuiAppBarProps } from '@mui/material/AppBar';
// import Toolbar from '@mui/material/Toolbar';
// import List from '@mui/material/List';
// import CssBaseline from '@mui/material/CssBaseline';
// import Typography from '@mui/material/Typography';
// import Divider from '@mui/material/Divider';
// import IconButton from '@mui/material/IconButton';
// import MenuIcon from '@mui/icons-material/Menu';
// import ChevronLeftIcon from '@mui/icons-material/ChevronLeft';
// import ChevronRightIcon from '@mui/icons-material/ChevronRight';
// import ListItem from '@mui/material/ListItem';
// import ListItemButton from '@mui/material/ListItemButton';
// import ListItemIcon from '@mui/material/ListItemIcon';
// import ListItemText from '@mui/material/ListItemText';
// import Navigation from './navigation';
// import { useLocation, useNavigate } from 'react-router-dom';
// import DashboardIcon from '@mui/icons-material/Dashboard';
// import Tooltip from '@mui/material/Tooltip';
// import fraud2 from '../../../src/assets/fraud2.png';
// import Report from '../../../src/assets/Report.png';



// const drawerWidth = 240;

// const openedMixin = (theme: Theme): CSSObject => ({
//   width: drawerWidth,
//   transition: theme.transitions.create('width', {
//     easing: theme.transitions.easing.sharp,
//     duration: theme.transitions.duration.enteringScreen,
//   }),
//   overflowX: 'hidden',
// });

// const closedMixin = (theme: Theme): CSSObject => ({
//   transition: theme.transitions.create('width', {
//     easing: theme.transitions.easing.sharp,
//     duration: theme.transitions.duration.leavingScreen,
//   }),
//   overflowX: 'hidden',
//   width: `calc(${theme.spacing(7)} + 1px)`,
//   [theme.breakpoints.up('sm')]: {
//     width: `calc(${theme.spacing(8)} + 1px)`,
//   },
// });

// const DrawerHeader = styled('div')(({ theme }) => ({
//   display: 'flex',
//   alignItems: 'center',
//   justifyContent: 'flex-end',
//   padding: theme.spacing(0, 1),
//   ...theme.mixins.toolbar,
// }));

// interface AppBarProps extends MuiAppBarProps {
//   open?: boolean;
// }

// const AppBar = styled(MuiAppBar, {
//   shouldForwardProp: (prop) => prop !== 'open',
// })<AppBarProps>(({ theme, open }) => ({
//   zIndex: theme.zIndex.drawer + 1,
//   transition: theme.transitions.create(['width', 'margin'], {
//     easing: theme.transitions.easing.sharp,
//     duration: theme.transitions.duration.leavingScreen,
//   }),
//   ...(open && {
//     marginLeft: drawerWidth,
//     width: `calc(100% - ${drawerWidth}px)`,
//     transition: theme.transitions.create(['width', 'margin'], {
//       easing: theme.transitions.easing.sharp,
//       duration: theme.transitions.duration.enteringScreen,
//     }),
//   }),
// }));

// const Drawer = styled(MuiDrawer, { shouldForwardProp: (prop) => prop !== 'open' })(
//   ({ theme, open }) => ({
//     width: drawerWidth,
//     flexShrink: 0,
//     whiteSpace: 'nowrap',
//     boxSizing: 'border-box',
//     ...(open && {
//       ...openedMixin(theme),
//       '& .MuiDrawer-paper': openedMixin(theme),
//     }),
//     ...(!open && {
//       ...closedMixin(theme),
//       '& .MuiDrawer-paper': closedMixin(theme),
//     }),
//   }),
// );

// const Sidebar = () => {
//   const navigate = useNavigate();
//   const location = useLocation();
//   const theme = useTheme();
//   const [open, setOpen] = useState(true);
//   const [showReport, setShowReport] = useState(false);

//   useEffect(() => {
//     const accessPermissionData = location.state?.accessPermissionData;

//   }, [location.state,]);

//   const handleDrawerOpen = () => {
//     setOpen(true);
//   };

//   const handleDrawerClose = () => {
//     setOpen(false);
//   };

//   const handleDashboardClick = () => {
//     navigate('/dashboard');
//     if (open) {
//       setOpen(false);
//     }
//   };

//   const renderHeadings = () => {

//     if (location.pathname === '/Amldetails') {
//       return (
//         <>

//           <List>
//             <Typography variant="h6" component="h6" style={{ color: '#1976d2' }}>
//               AML
//             </Typography>
//             <ListItemButton sx={{ height: '40px' }} onClick={() => navigate('/Amldetails')}>
//               <ListItemText primary="Aml to Teams" sx={{ opacity: open ? 1 : 0 }} />
//             </ListItemButton>

//             <ListItemButton sx={{ height: '40px' }} onClick={() => navigate('/QcViewaml')}>
//               <ListItemText primary="Branch To Aml" sx={{ opacity: open ? 1 : 0 }} />
//             </ListItemButton>
//             <ListItemButton sx={{ height: '40px' }} onClick={() => navigate('/AmlDecision')}>
//               <ListItemText primary="Aml Decision" sx={{ opacity: open ? 1 : 0 }} />
//             </ListItemButton>
//           </List>
//         </>
//       );
//     } else if (location.pathname === '/PepSearch') {
//       return (
//         <>

//           <List>
//             <Typography variant="h6" component="h6" style={{ color: '#1976d2' }}>
//               PEP
//             </Typography>
//             <ListItemButton sx={{ height: '40px' }} onClick={() => navigate('/ReportSearch')}>
//             <ListItemIcon
//                 sx={{
//                   minWidth: 0,
//                   mr: open ? 3 : 'auto',
//                   justifyContent: 'center',
//                 }}
//               >
//                 <Tooltip title="Report" placement="right" arrow>

//                   <img src={Report} alt="Default Preview" style={{ maxHeight: '27px', maxWidth: '300px' }} />
//                 </Tooltip>
//               </ListItemIcon>
//               <ListItemText primary="Report" sx={{ opacity: open ? 1 : 0 }} />
//             </ListItemButton>

//           </List>
//         </>
//       );
//     }
//     else if (location.pathname === '/Fraud') {
//       return (
//         <>
//           <List>
//             <Typography variant="h6" component="h6" style={{ color: '#1976d2' }}>
//               Fraud CounterFeit Alert
//             </Typography>
//             <ListItemButton sx={{ height: '40px' }} onClick={() => navigate('/Fraud')}>
//               <ListItemIcon
//                 sx={{
//                   minWidth: 0,
//                   mr: open ? 3 : 'auto',
//                   justifyContent: 'center',
//                 }}
//               >
//                 <Tooltip title="Fraud" placement="right" arrow>

//                   <img src={fraud2} alt="Default Preview" style={{ maxHeight: '27px', maxWidth: '300px' }} />
//                 </Tooltip>
//               </ListItemIcon>

//               <ListItemText primary="Fraud" sx={{ opacity: open ? 1 : 0 }} />
//             </ListItemButton>
//           </List>
//         </>
//       );  
//     }else if (location.pathname === '/AmlScam') {
//       return (
//         <>
//           <List>
//             <Typography variant="h6" component="h6" style={{ color: '#1976d2' }}>
//               Scam
//             </Typography>
//             <ListItemButton sx={{ Height: '40px' }} onClick={() => navigate('/AmlScam')}>
//               <ListItemText primary="Branch Info To Aml Team" sx={{ opacity: open ? 1 : 0 }} />
//             </ListItemButton>
//             <ListItemButton sx={{ Height: '40px' }} onClick={() => navigate('/QcViewaml')}>
//               <ListItemText primary="Aml Decision" sx={{ opacity: open ? 1 : 0 }} />
//             </ListItemButton>
//           </List>
//         </>
//       )} 
//       else if (location.pathname === '/CounterfeitDetails') {
//         return (
//           <>
//             <List>
//               <Typography variant="h6" component="h6" style={{ color: '#1976d2' }}>
//               CounterFeit
//               </Typography>
//               <ListItemButton sx={{ Height: '40px' }} onClick={() => navigate('/CounterfeitDetails')}>
//                 <ListItemText primary="Branch Info To Aml Team" sx={{ opacity: open ? 1 : 0 }} />
//               </ListItemButton>
//               <ListItemButton sx={{ Height: '40px' }} onClick={() => navigate('/QcViewaml')}>
//                 <ListItemText primary="Aml Decision" sx={{ opacity: open ? 1 : 0 }} />
//               </ListItemButton>
//             </List>
//           </>
//         )}
//         else if (location.pathname === '/SearchCms') {
//           return (
//             <>

//               <List>
//                 <Typography variant="h6" component="h6" style={{ color: '#1976d2' }}>
//                 CMS
//                 </Typography>
//                 <ListItemButton sx={{ height: '40px' }} onClick={() => navigate('/ReportSearchcms')}>
//                 <ListItemIcon
//                     sx={{
//                       minWidth: 0,
//                       mr: open ? 3 : 'auto',
//                       justifyContent: 'center',
//                     }}
//                   >
//                     <Tooltip title="Report" placement="right" arrow>

//                       <img src={Report} alt="Default Preview" style={{ maxHeight: '27px', maxWidth: '300px' }} />
//                     </Tooltip>
//                   </ListItemIcon>
//                   <ListItemText primary="Report" sx={{ opacity: open ? 1 : 0 }} />
//                 </ListItemButton>

//               </List>
//             </>
//           );
//         }else {
//       return null;
//     }
//   };



//   return (
//     <Box sx={{ display: 'flex' }}>
//       <CssBaseline />
//       <AppBar open={open}>
//         <Toolbar>
//           <Tooltip title="Menu">
//             <IconButton
//               color="inherit"
//               aria-label="open drawer"
//               onClick={handleDrawerOpen}
//               edge="start"
//               sx={{
//                 marginRight: 5,
//                 ...(open && { display: 'none' }),
//               }}
//             >
//               <MenuIcon />
//             </IconButton>
//           </Tooltip>
//           <Navigation />
//         </Toolbar>
//       </AppBar>
//       <Drawer variant="permanent" open={open}>
//         <DrawerHeader>
//           <Typography variant="h4" component="h4" style={{ color: '#1976d2' }}>
//             PONSUN
//           </Typography>
//           <IconButton onClick={handleDrawerClose}>
//             {open ? <ChevronRightIcon /> : <ChevronLeftIcon />}
//           </IconButton>
//         </DrawerHeader>
//         <Divider />

//         {renderHeadings()}
//         <Divider />
//       </Drawer>
//     </Box>
//   );
// }

// export default Sidebar;
import React, { useEffect, useState } from 'react';
import { styled, useTheme, Theme, CSSObject } from '@mui/material/styles';
import Box from '@mui/material/Box';
import MuiDrawer from '@mui/material/Drawer';
import MuiAppBar, { AppBarProps as MuiAppBarProps } from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import List from '@mui/material/List';
import CssBaseline from '@mui/material/CssBaseline';
import Typography from '@mui/material/Typography';
import Divider from '@mui/material/Divider';
import IconButton from '@mui/material/IconButton';
import MenuIcon from '@mui/icons-material/Menu';
import ChevronLeftIcon from '@mui/icons-material/ChevronLeft';
import ChevronRightIcon from '@mui/icons-material/ChevronRight';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import Navigation from './navigation';
import { useLocation, useNavigate } from 'react-router-dom';
import Tooltip from '@mui/material/Tooltip';
import DashboardIcon from '@mui/icons-material/Dashboard';
import fraud2 from '../../../src/assets/fraud2.png';
import Report from '../../../src/assets/Report.png';
import satateImage from '../../../src/assets/state.png';
import taskImage from '../../../src/assets/Task.png';
import Branch from '../../../src/assets/Branch.png';
import decision from '../../../src/assets/decision.png';
import home from '../../../src/assets/home.png';
import Search1 from '../../../src/assets/Search1.png'
import pending from '../../../src/assets/pending.png'
import PendingActionsIcon from '@mui/icons-material/PendingActions';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';


const drawerWidth = 240;

const openedMixin = (theme: Theme): CSSObject => ({
  width: drawerWidth,
  transition: theme.transitions.create('width', {
    easing: theme.transitions.easing.sharp,
    duration: theme.transitions.duration.enteringScreen,
  }),
  overflowX: 'hidden',
});

const closedMixin = (theme: Theme): CSSObject => ({
  transition: theme.transitions.create('width', {
    easing: theme.transitions.easing.sharp,
    duration: theme.transitions.duration.leavingScreen,
  }),
  overflowX: 'hidden',
  width: `calc(${theme.spacing(7)} + 1px)`,
  [theme.breakpoints.up('sm')]: {
    width: `calc(${theme.spacing(8)} + 1px)`,
  },
});

const DrawerHeader = styled('div')(({ theme }) => ({
  display: 'flex',
  alignItems: 'center',
  justifyContent: 'flex-end',
  padding: theme.spacing(0, 1),
  ...theme.mixins.toolbar,
}));

interface AppBarProps extends MuiAppBarProps {
  open?: boolean;
}

const AppBar = styled(MuiAppBar, {
  shouldForwardProp: (prop) => prop !== 'open',
})<AppBarProps>(({ theme, open }) => ({
  zIndex: theme.zIndex.drawer + 1,
  transition: theme.transitions.create(['width', 'margin'], {
    easing: theme.transitions.easing.sharp,
    duration: theme.transitions.duration.leavingScreen,
  }),
  ...(open && {
    marginLeft: drawerWidth,
    width: `calc(100% - ${drawerWidth}px)`,
    transition: theme.transitions.create(['width', 'margin'], {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.enteringScreen,
    }),
  }),
}));

const Drawer = styled(MuiDrawer, { shouldForwardProp: (prop) => prop !== 'open' })(
  ({ theme, open }) => ({
    width: drawerWidth,
    flexShrink: 0,
    whiteSpace: 'nowrap',
    boxSizing: 'border-box',
    ...(open && {
      ...openedMixin(theme),
      '& .MuiDrawer-paper': openedMixin(theme),
    }),
    ...(!open && {
      ...closedMixin(theme),
      '& .MuiDrawer-paper': closedMixin(theme),
    }),
  }),
);

const Sidebar = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const theme = useTheme();
  const [open, setOpen] = useState(true);

  useEffect(() => {
    const accessPermissionData = location.state?.accessPermissionData;
  }, [location.state]);

  const handleDrawerOpen = () => {
    setOpen(true);
  };

  const handleDrawerClose = () => {
    setOpen(false);
  };

  const handleDashboardClick = () => {
    navigate('/dashboard');
    if (open) {
      setOpen(false);
    }
  };
  const handleLevel2SecReviewClick = () => {
    navigate('/SanLevel1secReview');
  };
  const handlefirstLevelpendingReviewClick = () => {
    navigate('/Notification');
  };
  const handleLevel2SearchClick = () => {
    navigate('/SanLevel2Search');
  };
  const handleLevel3SearchClick = () => {
    navigate('/SanLevel3Search');
  };
  const handleSearchClick = () => {
    navigate('/Search');
  };
  const handlePepLevel2SecReviewClick = () => {
    navigate('/PepLevel1secReview');
  };
  const handlePepLevel2SearchClick = () => {
    navigate('/PepLevel2Search');
  };
  const handlePepLevel3SearchClick = () => {
    navigate('/PepLevel3Search');
  };
  const handleCmsLevel2SecReviewClick = () => {
    navigate('/CmsLevel1secReview');
  };
  const handleCmsLevel2SearchClick = () => {
    navigate('/CmsLevel2Search');
  };
  const handleCmsLevel3SearchClick = () => {
    navigate('/CmsLevel3Search');
  };
  const [currentSection, setCurrentSection] = useState(location.pathname);

  const handleItemClick = (path: any) => {
    navigate(path);
    setCurrentSection(path);
    setOpen(true);
  };

  const renderHeadings = () => {
    switch (currentSection) {
      case '/Aml':
      case '/Amldetails':
      case '/QcViewdecision':
      case '/Amlteamview/${complaintId}/${uid}':
        // case '/Amltemasdashboard':
        return (
          <>
            <List>
              <Typography variant="h6" component="h6" style={{ color: '#1976d2' }}>
                AML
              </Typography>
              <ListItemButton sx={{ height: '40px' }} onClick={() => handleItemClick('/Amldetails')}>
                <ListItemIcon sx={{ minWidth: 0, mr: open ? 3 : 'auto', justifyContent: 'center' }}>
                  <Tooltip title="Aml Qry" placement="right" arrow>
                    <img src={Branch} alt="Default Preview" style={{ maxHeight: '27px', maxWidth: '300px' }} />
                  </Tooltip>
                </ListItemIcon>
                <ListItemText primary="Aml Qry" sx={{ opacity: open ? 1 : 0 }} />
              </ListItemButton>
              <ListItemButton sx={{ height: '40px' }} onClick={() => handleItemClick('/QcViewdecision')}>
                <ListItemIcon sx={{ minWidth: 0, mr: open ? 3 : 'auto', justifyContent: 'center' }}>
                  <Tooltip title="Decision" placement="right" arrow>
                    <img src={decision} alt="Default Preview" style={{ maxHeight: '27px', maxWidth: '300px' }} />
                  </Tooltip>
                </ListItemIcon>
                <ListItemText primary="Decision" sx={{ opacity: open ? 1 : 0 }} />
              </ListItemButton>
              {/* <ListItemButton sx={{ height: '40px' }} onClick={() => handleItemClick('/Amltemasdashboard')}>
              <ListItemIcon sx={{ minWidth: 0, mr: open ? 3 : 'auto', justifyContent: 'center' }}>
                <Tooltip title="Aml Home" placement="right" arrow>
                  <img src={home} alt="Default Preview" style={{ maxHeight: '27px', maxWidth: '300px' }} />
                </Tooltip>
              </ListItemIcon>
              <ListItemText primary="Aml Home" sx={{ opacity: open ? 1 : 0 }} />
            </ListItemButton> */}
            </List>
            <List>
              <ListItemButton sx={{ height: '40px' }} onClick={() => handleItemClick('/Amltemasdashboard')}>
                <ListItemIcon sx={{ minWidth: 0, mr: open ? 3 : 'auto', justifyContent: 'center' }}>
                  <Tooltip title="Aml Home" placement="right" arrow>
                    <img src={home} alt="Default Preview" style={{ maxHeight: '27px', maxWidth: '300px' }} />
                  </Tooltip>
                </ListItemIcon>
                <ListItemText primary="Aml Home" sx={{ opacity: open ? 1 : 0 }} />
              </ListItemButton>
            </List>
          </>

        );
      case '/QcViewaml':
      case '/QcViewaml':
        // case '/Amltemasdashboard':

        return (
          <>
            <List>
              <Typography variant="h6" component="h6" style={{ color: '#1976d2' }}>
                Branch
              </Typography>
              <ListItemButton sx={{ height: '40px' }} onClick={() => handleItemClick('/QcViewaml')}>
                <ListItemIcon sx={{ minWidth: 0, mr: open ? 3 : 'auto', justifyContent: 'center' }}>
                  <Tooltip title="Branch To Aml" placement="right" arrow>
                    <img src={Branch} alt="Default Preview" style={{ maxHeight: '27px', maxWidth: '300px' }} />
                  </Tooltip>
                </ListItemIcon>
                <ListItemText primary="Branch To Aml" sx={{ opacity: open ? 1 : 0 }} />
              </ListItemButton>

            </List>
            <List>
              <ListItemButton sx={{ height: '40px' }} onClick={() => handleItemClick('/Amltemasdashboard')}>
                <ListItemIcon sx={{ minWidth: 0, mr: open ? 3 : 'auto', justifyContent: 'center' }}>
                  <Tooltip title="Aml Home" placement="right" arrow>
                    <img src={home} alt="Default Preview" style={{ maxHeight: '27px', maxWidth: '300px' }} />
                  </Tooltip>
                </ListItemIcon>
                <ListItemText primary="Aml Home" sx={{ opacity: open ? 1 : 0 }} />
              </ListItemButton>
            </List>
          </>
        );


      case '/ReportSearch':
      case '/PepLevel1FirstReview':
      case '/PepLevel1secReview':
      case '/PepLevel2Search':
      case '/PepLevel3Search':
        return (
          <List>
            <Typography variant="h6" component="h6" style={{ color: '#1976d2' }}>
              PEP
            </Typography>

            <ListItemButton sx={{ height: '40px' }} onClick={() => navigate('/PepLevel1FirstReview')}>
              <ListItemIcon
                sx={{
                  minWidth: 0,
                  mr: open ? 3 : 'auto',
                  justifyContent: 'center',
                }}
              >
                <Tooltip title="Level1FirstReview" placement="right" arrow>
                  <img src={Search1} alt="Default Preview" style={{ maxHeight: '27px', maxWidth: '300px' }} />
                </Tooltip>
              </ListItemIcon>
              <ListItemText primary="Level1FirstReview" sx={{ opacity: open ? 1 : 0 }} />
            </ListItemButton>
            <ListItemButton sx={{ height: '40px' }} onClick={handlePepLevel2SecReviewClick}>
              <ListItemIcon
                sx={{
                  minWidth: 0,
                  mr: open ? 3 : 'auto',
                  justifyContent: 'center',
                }}
              >
                <Tooltip title="Level2 Sec Review" placement="right" arrow>
                  <img src={Search1} alt="Default Preview" style={{ maxHeight: '27px', maxWidth: '300px' }} />
                </Tooltip>
              </ListItemIcon>
              <ListItemText primary="Level2 Sec Review" sx={{ opacity: open ? 1 : 0 }} />
            </ListItemButton>
            <ListItemButton sx={{ height: '40px' }} onClick={handlePepLevel2SearchClick}>
              <ListItemIcon
                sx={{
                  minWidth: 0,
                  mr: open ? 3 : 'auto',
                  justifyContent: 'center',
                }}
              >
                <Tooltip title="Level2 Search" placement="right" arrow>
                  <img src={Search1} alt="Default Preview" style={{ maxHeight: '27px', maxWidth: '300px' }} />
                </Tooltip>
              </ListItemIcon>
              <ListItemText primary="Level2 Search" sx={{ opacity: open ? 1 : 0 }} />
            </ListItemButton>
            <ListItemButton sx={{ height: '40px' }} onClick={handlePepLevel3SearchClick}>
              <ListItemIcon
                sx={{
                  minWidth: 0,
                  mr: open ? 3 : 'auto',
                  justifyContent: 'center',
                }}
              >
                <Tooltip title="Level3 Search" placement="right" arrow>
                  <img src={Search1} alt="Default Preview" style={{ maxHeight: '27px', maxWidth: '300px' }} />
                </Tooltip>
              </ListItemIcon>
              <ListItemText primary="Level3 Search" sx={{ opacity: open ? 1 : 0 }} />
            </ListItemButton>
            <ListItemButton sx={{ height: '40px' }} onClick={() => navigate('/ReportSearch')}>
              <ListItemIcon
                sx={{
                  minWidth: 0,
                  mr: open ? 3 : 'auto',
                  justifyContent: 'center',
                }}
              >
                <Tooltip title="Report" placement="right" arrow>
                  <img src={Report} alt="Default Preview" style={{ maxHeight: '27px', maxWidth: '300px' }} />
                </Tooltip>
              </ListItemIcon>
              <ListItemText primary="Report" sx={{ opacity: open ? 1 : 0 }} />
            </ListItemButton>
          </List>
        );
      case '/Fraud':
        return (
          <List>
            <Typography variant="h6" component="h6" style={{ color: '#1976d2' }}>
              Fraud CounterFeit Alert
            </Typography>
            <ListItemButton sx={{ height: '40px' }} onClick={() => navigate('/Fraud')}>
              <ListItemIcon
                sx={{
                  minWidth: 0,
                  mr: open ? 3 : 'auto',
                  justifyContent: 'center',
                }}
              >
                <Tooltip title="Fraud" placement="right" arrow>
                  <img src={fraud2} alt="Default Preview" style={{ maxHeight: '27px', maxWidth: '300px' }} />
                </Tooltip>
              </ListItemIcon>
              <ListItemText primary="Fraud" sx={{ opacity: open ? 1 : 0 }} />
            </ListItemButton>
          </List>
        );
      case '/AmlScam':
      case '/QcViewaml':
        return (
          <List>
            <Typography variant="h6" component="h6" style={{ color: '#1976d2' }}>
              Scam
            </Typography>
            <ListItemButton sx={{ height: '40px' }} onClick={() => navigate('/AmlScam')}>
              <ListItemText primary="Branch Info To Aml Team" sx={{ opacity: open ? 1 : 0 }} />
            </ListItemButton>
            <ListItemButton sx={{ height: '40px' }} onClick={() => navigate('/QcViewaml')}>
              <ListItemText primary="Aml Decision" sx={{ opacity: open ? 1 : 0 }} />
            </ListItemButton>
          </List>
        );
      case '/CounterfeitDetails':
      case '/QcViewaml':
        return (
          <List>
            <Typography variant="h6" component="h6" style={{ color: '#1976d2' }}>
              CounterFeit
            </Typography>
            <ListItemButton sx={{ height: '40px' }} onClick={() => navigate('/CounterfeitDetails')}>
              <ListItemText primary="Branch Info To Aml Team" sx={{ opacity: open ? 1 : 0 }} />
            </ListItemButton>
            <ListItemButton sx={{ height: '40px' }} onClick={() => navigate('/QcViewaml')}>
              <ListItemText primary="Aml Decision" sx={{ opacity: open ? 1 : 0 }} />
            </ListItemButton>
          </List>
        );
      case '/CmsLevel1FirstReview':
      case '/ReportSearchcms':
      case '/CmsLevel1secReview':
      case '/CmsLevel2Search':
      case '/CmsLevel3Search':
        return (
          <List>
            <Typography variant="h6" component="h6" style={{ color: '#1976d2' }}>
              CMS
            </Typography>

            <ListItemButton sx={{ height: '40px' }} onClick={() => navigate('/CmsLevel1FirstReview')}>
              <ListItemIcon
                sx={{
                  minWidth: 0,
                  mr: open ? 3 : 'auto',
                  justifyContent: 'center',
                }}
              >
                <Tooltip title="Level1 First Review" placement="right" arrow>
                  <img src={Search1} alt="Default Preview" style={{ maxHeight: '27px', maxWidth: '300px' }} />
                </Tooltip>
              </ListItemIcon>
              <ListItemText primary="Level1 First Review" sx={{ opacity: open ? 1 : 0 }} />
            </ListItemButton>
            <ListItemButton sx={{ height: '40px' }} onClick={handleCmsLevel2SecReviewClick}>
              <ListItemIcon
                sx={{
                  minWidth: 0,
                  mr: open ? 3 : 'auto',
                  justifyContent: 'center',
                }}
              >
                <Tooltip title="Level2 Sec Review" placement="right" arrow>
                  <img src={Search1} alt="Default Preview" style={{ maxHeight: '27px', maxWidth: '300px' }} />
                </Tooltip>
              </ListItemIcon>
              <ListItemText primary="Level2 Sec Review" sx={{ opacity: open ? 1 : 0 }} />
            </ListItemButton>
            <ListItemButton sx={{ height: '40px' }} onClick={handleCmsLevel2SearchClick}>
              <ListItemIcon
                sx={{
                  minWidth: 0,
                  mr: open ? 3 : 'auto',
                  justifyContent: 'center',
                }}
              >
                <Tooltip title="Level2 Search" placement="right" arrow>
                  <img src={Search1} alt="Default Preview" style={{ maxHeight: '27px', maxWidth: '300px' }} />
                </Tooltip>
              </ListItemIcon>
              <ListItemText primary="Level2 Search" sx={{ opacity: open ? 1 : 0 }} />
            </ListItemButton>
            <ListItemButton sx={{ height: '40px' }} onClick={handleCmsLevel3SearchClick}>
              <ListItemIcon
                sx={{
                  minWidth: 0,
                  mr: open ? 3 : 'auto',
                  justifyContent: 'center',
                }}
              >
                <Tooltip title="Level3 Search" placement="right" arrow>
                  <img src={Search1} alt="Default Preview" style={{ maxHeight: '27px', maxWidth: '300px' }} />
                </Tooltip>
              </ListItemIcon>
              <ListItemText primary="Level3 Search" sx={{ opacity: open ? 1 : 0 }} />
            </ListItemButton>
            <ListItemButton sx={{ height: '40px' }} onClick={() => navigate('/ReportSearchcms')}>
              <ListItemIcon
                sx={{
                  minWidth: 0,
                  mr: open ? 3 : 'auto',
                  justifyContent: 'center',
                }}
              >
                <Tooltip title="Report" placement="right" arrow>
                  <img src={Report} alt="Default Preview" style={{ maxHeight: '27px', maxWidth: '300px' }} />
                </Tooltip>
              </ListItemIcon>
              <ListItemText primary="Report" sx={{ opacity: open ? 1 : 0 }} />
            </ListItemButton>

          </List>
        );
      case '/SanLevel1FirstReview':
      case '/SanctionSearch':
      case '/SanLevel1secReview':
      case '/SanLevel2Search':
      case '/SanLevel3Search':
      case '/Notification':
      case '/Search':

        return (
          <List>
            <Typography variant="h6" component="h6" style={{ color: '#1976d2' }}>
              SAN
            </Typography>

            <ListItemButton sx={{ height: '40px' }} onClick={() => navigate('/SanLevel1FirstReview')}>
              <ListItemIcon
                sx={{
                  minWidth: 0,
                  mr: open ? 3 : 'auto',
                  justifyContent: 'center',
                }}
              >
                <Tooltip title="Level1 First Review" placement="right" arrow>
                  <img src={Search1} alt="Default Preview" style={{ maxHeight: '27px', maxWidth: '300px' }} />
                </Tooltip>
              </ListItemIcon>
              <ListItemText primary="Level 1 First Review" sx={{ opacity: open ? 1 : 0 }} />
            </ListItemButton>
            <ListItemButton sx={{ height: '40px' }} onClick={handlefirstLevelpendingReviewClick}>
              <ListItemIcon
                sx={{
                  minWidth: 0,
                  mr: open ? 3 : 'auto',
                  justifyContent: 'center',
                }}
              >
                <Tooltip title="First Level Pending" placement="right" arrow>
                  <img src={pending} alt="Default Preview" style={{ maxHeight: '27px', maxWidth: '300px' }} />
                </Tooltip>
              </ListItemIcon>
              <ListItemText primary="First Level Pending" sx={{ opacity: open ? 1 : 0 }} />
            </ListItemButton>
            <ListItemButton sx={{ height: '40px' }} onClick={handleLevel2SecReviewClick}>
              <ListItemIcon
                sx={{
                  minWidth: 0,
                  mr: open ? 3 : 'auto',
                  justifyContent: 'center',
                }}
              >
                <Tooltip title="Level2 Sec Review" placement="right" arrow>
                  <img src={Search1} alt="Default Preview" style={{ maxHeight: '27px', maxWidth: '300px' }} />
                </Tooltip>
              </ListItemIcon>
              <ListItemText primary="Level 1 Sec Review" sx={{ opacity: open ? 1 : 0 }} />
            </ListItemButton>
            <ListItemButton sx={{ height: '40px' }} onClick={handleLevel2SearchClick}>
              <ListItemIcon
                sx={{
                  minWidth: 0,
                  mr: open ? 3 : 'auto',
                  justifyContent: 'center',
                }}
              >
                <Tooltip title="Level2 Search" placement="right" arrow>
                  <img src={Search1} alt="Default Preview" style={{ maxHeight: '27px', maxWidth: '300px' }} />
                </Tooltip>
              </ListItemIcon>
              <ListItemText primary="Level 2 Search" sx={{ opacity: open ? 1 : 0 }} />
            </ListItemButton>
            <ListItemButton sx={{ height: '40px' }} onClick={handleLevel3SearchClick}>
              <ListItemIcon
                sx={{
                  minWidth: 0,
                  mr: open ? 3 : 'auto',
                  justifyContent: 'center',
                }}
              >
                <Tooltip title="Level 3 Search" placement="right" arrow>
                  <img src={Search1} alt="Default Preview" style={{ maxHeight: '27px', maxWidth: '300px' }} />
                </Tooltip>
              </ListItemIcon>
              <ListItemText primary="Level3 Search" sx={{ opacity: open ? 1 : 0 }} />
            </ListItemButton>
            <ListItemButton sx={{ height: '40px' }} onClick={() => navigate('/SanctionSearch')}>
              <ListItemIcon
                sx={{
                  minWidth: 0,
                  mr: open ? 3 : 'auto',
                  justifyContent: 'center',
                }}
              >
                <Tooltip title="Report" placement="right" arrow>
                  <img src={Report} alt="Default Preview" style={{ maxHeight: '27px', maxWidth: '300px' }} />
                </Tooltip>
              </ListItemIcon>
              <ListItemText primary="Report" sx={{ opacity: open ? 1 : 0 }} />
            </ListItemButton>
            <ListItemButton sx={{ height: '40px' }} onClick={handleSearchClick}>
              <ListItemIcon
                sx={{
                  minWidth: 0,
                  mr: open ? 3 : 'auto',
                  justifyContent: 'center',
                }}
              >
                <Tooltip title="UI Testing Search" placement="right" arrow>
                <AccountCircleIcon />
                </Tooltip>
              </ListItemIcon>
              <ListItemText primary="UI Testing Search" sx={{ opacity: open ? 1 : 0 }} />
            </ListItemButton>


            {/* <ListItemButton disablePadding sx={{ display: 'block' }} onClick={handleSearchClick}>
              <Tooltip title="UI Testing Search" placement="right" arrow>
                <ListItemButton
                  sx={{
                    minHeight: 48,
                    justifyContent: open ? 'initial' : 'center',
                    px: 2.5,
                  }}
                >
                  <ListItemIcon
                    sx={{
                      minWidth: 0,
                      mr: open ? 3 : 'auto',
                      justifyContent: 'center',
                    }}
                  >
                    <AccountCircleIcon />
                  </ListItemIcon>
                  <ListItemText primary=" UI Testing Search" sx={{ opacity: open ? 1 : 0 }} />
                </ListItemButton>
              </Tooltip>
            </ListItemButton> */}


          </List>
        );
      default:
        return null;
    }
  };

  return (
    <Box sx={{ display: 'flex' }}>
      <CssBaseline />
      <AppBar open={open}>
        <Toolbar>
          <Tooltip title="Menu">
            <IconButton
              color="inherit"
              aria-label="open drawer"
              onClick={handleDrawerOpen}
              edge="start"
              sx={{
                marginRight: 5,
                ...(open && { display: 'none' }),
              }}
            >
              <MenuIcon />
            </IconButton>
          </Tooltip>
          <Navigation />
        </Toolbar>
      </AppBar>
      <Drawer variant="permanent" open={open}>
        <DrawerHeader>
          <Typography variant="h4" component="h4" style={{ color: '#1976d2' }}>
            PONSUN
          </Typography>
          <IconButton onClick={handleDrawerClose}>
            {open ? <ChevronRightIcon /> : <ChevronLeftIcon />}
          </IconButton>
        </DrawerHeader>
        <Divider />
        {renderHeadings()}
        <Divider />
      </Drawer>
    </Box>
  );
}

export default Sidebar;
