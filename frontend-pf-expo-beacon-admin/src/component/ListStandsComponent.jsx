import React, { Component } from 'react';
import AdminDataService from '../service/AdminDataService'
// reactstrap components
import {
  Card,
  CardHeader,
  Table,
  Row,
  Button,
  UncontrolledTooltip
} from "reactstrap";

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
                <Card className="shadow">
                <CardHeader className="border-0">
                  <Row className="align-items-center">
                    <div className="col">
                      <h3 className="mb-0">All Stands</h3>
                    </div>
                    {this.state.message && <div class="alert alert-success">{this.state.message}</div>}
                    <div className="col text-right">
                       <Button
                        color="primary"
                        onClick={this.addStandClicked}
                        size="sm"
                      >
                        Add Stand
                      </Button> 
                    </div>
                  </Row>
                </CardHeader>
                <Table className="align-items-center table-flush" responsive>
                  <thead className="thead-light">
                    <tr>
                      <th scope="col"></th>
                      <th scope="col">Title</th>
                      <th scope="col">Description</th>
                      <th scope="col">Actions</th>
                      <th scope="col">Pictures</th>
                    </tr>
                  </thead>
                  <tbody>
                        {
                            this.state.stands.map(
                                stand =>
                                    <tr key={stand.id}>
                                      {/* TODO (ma 2020-03-07) Ajustar el tamaño de la imagen */}
                                        <th><img 
                                        src={stand.cover} 
                                        height="150px" width="250px"
                                        /></th>
                                        <td>{stand.title}</td>
                                        <td>{stand.short_description}</td>
                                        <td>
                                            {/* <button className="btn btn-outline-secondary" onClick={() => this.editStandClicked(stand.id)}>Edit</button> */}
                                            <button className="btn btn-outline-danger" onClick={() => this.deleteStandClicked(stand.id)}>Delete</button>
                                        </td>
                                        <td>
                                          <div className="avatar-group">
                                            {/* stand.pictures.map(
                                              picture => 
                                                <a
                                                className="avatar avatar-sm"
                                                onClick={e => e.preventDefault()}
                                              >
                                                <img
                                                  alt="..."
                                                  className="rounded-circle"
                                                  src={picture}
                                                />
                                              </a>
                                              ) */}
                                              {/* <a
                                                className="avatar avatar-sm"
                                                href={stand.cover}
                                              >
                                                <img
                                                  alt="..."
                                                  className="rounded-circle"
                                                  src={stand.cover}
                                                />
                                              </a> */}
                                          </div>
                                        </td>
                                    </tr>
                            )
                        }
                    </tbody>
                </Table>
              </Card>
            </div>

        )
    }
}

export default ListStandsComponent