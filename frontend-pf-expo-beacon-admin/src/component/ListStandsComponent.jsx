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
        this.deleteStandClicked = this.deleteStandClicked.bind(this)
        this.addStandClicked = this.addStandClicked.bind(this)
        this.editStandClicked = this.editStandClicked.bind(this)
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

    deleteStandClicked(id) {
        AdminDataService.deleteStand(id)
            .then(
                response => {
                    this.setState({ message: `Delete of stand ${id} Successful` })
                    this.refreshStands()
                }
            )

    }

    addStandClicked() {
        this.props.history.push(`/stands/add`)
    }

    editStandClicked(id) {
        this.props.history.push(`/stands/${id}`)
    }

    render() {
        return (
            <div className="container">
                <h3>All Stands</h3>
                {this.state.message && <div class="alert alert-success">{this.state.message}</div>}
                <div className="container">
                    {/* <div className="float-right">
                         <button className="btn btn-success" onClick={this.addStandClicked}>Add Stand</button>
                     </div> */}
                    <table className="table">
                        <thead>
                            <tr>
                                <th></th>
                                <th>Title</th>
                                <th>Description</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.stands.map(
                                    stand =>
                                        <tr key={stand.id}>
                                            <th>{stand.cover}</th>
                                            <td>{stand.title}</td>
                                            <td>{stand.short_description}</td>
                                            <td>
                                                {/* <button className="btn btn-outline-secondary" onClick={() => this.editStandClicked(stand.id)}>Edit</button> */}
                                                <button className="btn btn-outline-danger" onClick={() => this.deleteStandClicked(stand.id)}>Delete</button>
                                            </td>
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