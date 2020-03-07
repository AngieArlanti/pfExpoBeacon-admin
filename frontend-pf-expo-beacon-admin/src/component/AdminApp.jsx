  
import React, { Component } from 'react';
import ListStandsComponent from './ListStandsComponent';
import AddStandComponent from './AddStandComponent';
import StandComponent from './StandComponent';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import Header from "./Headers/Header.js";

class AdminApp extends Component {
    render() {
        return (
            <Router>
            <>
                <h1>Admin Application</h1>
                {/* <Header /> */}
                <Switch>
                    <Route path="/" exact component={ListStandsComponent} />
                    <Route path="/stands" exact component={ListStandsComponent} />
                    <Route path="/stands/add" component={AddStandComponent} />
                    <Route path="/stands/:id" component={StandComponent} />
                </Switch>
            </>
            </Router>
        )
    }
}
export default AdminApp