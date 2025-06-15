import React from 'react';
import logo from './logo.svg';
import './App.css';
import { createTheme, Divider, MantineProvider, Slider } from '@mantine/core';
import '@mantine/core/styles.css';
import '@mantine/carousel/styles.css';
import '@mantine/tiptap/styles.css';
import '@mantine/dates/styles.css';
import HomePage from './Pages/HomePage';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Footer from './Footer/Footer';
<<<<<<< Updated upstream
=======
import FindJobsPage from './Pages/FindJobsPage';
import Header from './Header/Header';
import '@fontsource/poppins';
import FindTalentPage from './Pages/FindTalentPage';
import TalentProfilePage from './Pages/TalentProfilePage';
import PostJobPage from './Pages/PostJobPage';
import JobHistoryPage from './Pages/JobHistoryPage';
import ApplyJobPage from './Pages/ApplyJobPage';
import CompanyPage from './Pages/CompanyPage';
import PostedJobPage from './Pages/PostedJobPage';
import SignUpPage from './Pages/SignUpPage';
import ProfilePage from './Pages/ProfilePage';

>>>>>>> Stashed changes

function App() {
  const theme = createTheme({
    colors:{
      'bright-sun': ['#fffbeb','#fff3c6','#ffe588','#ffd149','#ffbd20','#f99b07','#dd7302','#b75006','#943c0c','#7a330d','#461902',
      ],
      'mine-shaft': ['#f6f6f6','#e7e7e7','#d1d1d1','#b0b0b0','#888888','#6d6d6d','#5d5d5d','#4f4f4f','#454545','#3d3d3d','#2d2d2d',
<<<<<<< Updated upstream
      ],
    }
=======
      ],},
      fontFamily: "Poppins, sans-serif",
      primaryColor:'brightSun',
      primaryShade:4,
    
>>>>>>> Stashed changes
  })
  return (
    <MantineProvider theme={theme}>
      <BrowserRouter>
<<<<<<< Updated upstream
      <Routes>
=======
      <Header/>
      <Divider size="xs" mx="md" />
      <Routes>
        <Route path='/find-jobs' element={<FindJobsPage/>}/>
        <Route path='/find-talent' element={<FindTalentPage/>}/>
        <Route path='/jobs' element={<JobHistoryPage/>}/>
        <Route path='/apply-jobs' element={<ApplyJobPage/>}/>
        <Route path='/posted-jobs' element={<PostedJobPage/>}/>
        <Route path='/job-history' element={<JobHistoryPage/>}/>
        <Route path='/company' element={<CompanyPage/>}/>
        <Route path='/post-job' element={<PostJobPage/>}/>
        <Route path='/signup' element={<SignUpPage/>}/>
        <Route path='/login' element={<SignUpPage/>}/>
        <Route path='/talent-profile' element={<TalentProfilePage/>}/>
        <Route path='/profile' element={<ProfilePage/>}/>
>>>>>>> Stashed changes
        <Route path='*' element={<HomePage/>} />
      </Routes>
      <Footer/>
      </BrowserRouter>
    </MantineProvider>
    
  );
}

export default App;
