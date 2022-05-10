import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import SignIn from './signIn/SignIn';
import SignUp from './signUp/SignUp';
import ToDoMain from './mainToDo/ToDoMain';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <BrowserRouter>
    <Routes>
      <Route path="/" element={<SignIn/>} />
      <Route path='signUp' element={<SignUp/>}/>
      <Route path='main' element={<ToDoMain/>}/>
    </Routes>
  </BrowserRouter>,
  root
);
