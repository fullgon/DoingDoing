import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import { BrowserRouter, Route, Routes } from 'react-router-dom';

import Invo from "./routes/invo"
import Expen from "./routes/expen"

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <BrowserRouter>
    <Routes>
      <Route path="/" element={<App/>} />
      <Route path="invo" element={<Invo/>} />
      <Route path="expen" element={<Expen/>} />
    </Routes>
  </BrowserRouter>,
  root
);
