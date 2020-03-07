import React, { Component } from 'react';
import './App.css';
import AdminApp from './component/AdminApp';

import "./assets/plugins/nucleo/css/nucleo.css";
import "@fortawesome/fontawesome-free/css/all.min.css";
import "./assets/scss/argon-dashboard-react.scss";

class App extends Component {
  render() {
    return (
      <div className="container">
        <AdminApp />
      </div>
    );
  }
}

export default App;
