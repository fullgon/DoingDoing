import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import SignIn from './sign/SignIn';
import SignUp from './sign/SignUp';
import FindPassword from './sign/FindPassword'
import ToDoMain from './mainToDo/ToDoMain';
import ReactModal from 'react-modal'

import ModalsProvider from "./modals/ModalsProvider"

ReactModal.setAppElement('#root');

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <BrowserRouter>
    <Routes>
      <Route path="/" element={<App/>} />
      <Route path="auth/signIn" element={<SignIn/>} />
      <Route path='auth/signUp' element={<SignUp/>}/>
      <Route path='auth/findPassword' element={<FindPassword/>}/>
      <Route path='schedule' element={<ModalsProvider><ToDoMain/></ModalsProvider>}/>
    </Routes>
  </BrowserRouter>,
  root
);
