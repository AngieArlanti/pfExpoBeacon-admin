import React, { Component } from 'react';
import AdminDataService from '../service/AdminDataService'

class ListStandsComponent extends Component {

    constructor(props) {
        super(props)
        this.state = {
            stands: [],
            message: null
        }
        this.refreshStands= this.refreshStands.bind(this)
    }

    componentDidMount() {
        this.refreshStands();
    }

    refreshStands() {
        AdminDataService.retrieveAllStands()
            .then(
                response => {
                    console.log(response);
                    this.setState({ stands: response.data })
                }
            )
    }

    render() {
        return (
            <div className="container">
                <h3>All Stands</h3>
                <div className="container">
                    <table className="table">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Description</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.stands.map(
                                    stand =>
                                        <tr key={stand.id}>
                                            <td>{stand.id}</td>
                                            <td>{stand.title}</td>
                                        </tr>
                                )
                            }
                        </tbody>
                    </table>
                </div>
            </div>
        )
    }
}

export default ListStandsComponent