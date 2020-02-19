import React, { Component } from 'react';
import ListStandsComponent from './ListStandsComponent';

class AdminApp extends Component {
    render() {
        return (<>
              <h1>Admin Application</h1>
              <ListStandsComponent />
            </>
        )
    }
}

export default AdminApp