import React from 'react';
import logo from './logo.svg';
import './App.css';
import { createTheme, MantineProvider, Slider } from '@mantine/core';
import '@mantine/core/styles.css';
import '@mantine/carousel/styles.css';
import HomePage from './Pages/HomePage';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Footer from './Footer/Footer';
import FindJobsPage from './Pages/FindJobsPage';
import Header from './Header/Header';
import '@fontsource/poppins';


function App() {
  const theme = createTheme({
    colors:{
      'bright-sun': ['#fffbeb','#fff3c6','#ffe588','#ffd149','#ffbd20','#f99b07','#dd7302','#b75006','#943c0c','#7a330d','#461902',
      ],
      'mine-shaft': ['#f6f6f6','#e7e7e7','#d1d1d1','#b0b0b0','#888888','#6d6d6d','#5d5d5d','#4f4f4f','#454545','#3d3d3d','#2d2d2d',
      ],},
      fontFamily: "Poppins, sans-serif",
    
  })
  return (
    <MantineProvider theme={theme}>
      <BrowserRouter>
      <Header/>
      <Routes>
        <Route path='/find-jobs' element={<FindJobsPage/>}/>
        <Route path='*' element={<HomePage/>} />
        
      </Routes>
      <Footer/>
      </BrowserRouter>
    </MantineProvider>
    
  );
}

export default App;
