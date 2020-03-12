import React, { Component } from "react";
// reactstrap components
import {
    Button,
    Card,
    CardHeader,
    CardBody,
    FormGroup,
    Form,
    Input,
    Row,
    Col,
    InputGroup,
    InputGroupAddon
  } from "reactstrap";

class AddStandComponent extends Component {

    constructor(props) {
        super(props)
        this.createStandClicked = this.createStandClicked.bind(this)
    }

    createStandClicked() {
        this.props.history.push(`/stands/add`)
    }

    render() {

        return (
            <div className="container">
                <Card className="bg-secondary shadow">
                    <CardHeader className="bg-white border-0">
                        <Row className="align-items-center">
                            <Col xs="8">
                                <h3 className="mb-0">Add Stand</h3>
                            </Col>
                        </Row>
                    </CardHeader>
                    <CardBody>
                        <Form>
                            <FormGroup>
                                <label className="form-control-label" htmlFor="input-title"> 
                                    Title
                                </label>
                                <Input
                                className="form-control-alternative"
                                id="input-title"
                                placeholder="Title"
                                type="text"
                                />
                            </FormGroup>
                            <FormGroup>
                                <label className="form-control-label" htmlFor="input-beacon"> 
                                    Beacon
                                </label>
                                <Input
                                className="form-control-alternative"
                                id="input-beacon"
                                placeholder="Beacon"
                                type="select"
                                >
                                    <option>1</option>
                                    <option>2</option>
                                    <option>3</option>
                                    <option>4</option>
                                    <option>5</option>
                                </Input>
                            </FormGroup>
                            <FormGroup>
                                <label className="form-control-label" htmlFor="input-short-description"> 
                                    Short description
                                </label>
                                <Input
                                className="form-control-alternative"
                                id="input-short-description"
                                placeholder="Short description"
                                type="text"
                                />
                            </FormGroup>
                            <FormGroup>
                                <label className="form-control-label" htmlFor="input-description"> 
                                    Description
                                </label>
                                <Input
                                className="form-control-alternative"
                                id="input-description"
                                placeholder="Description"
                                type="textarea"
                                />
                            </FormGroup>
                            <FormGroup>
                                <label className="form-control-label" htmlFor="input-cover"> 
                                    Cover
                                </label>
                                <Input
                                className="form-control-alternative"
                                id="input-cover"
                                placeholder="Cover"
                                type="url"
                                />
                            </FormGroup>
                            {/* <FormGroup>
                                <label className="form-control-label" htmlFor="input-pictures"> 
                                    Pictures
                                </label>
                                <InputGroup>
                                    <InputGroupAddon addonType="append"><Button>Add picture</Button></InputGroupAddon>
                                    <Input />
                                </InputGroup>
                            </FormGroup> */}
                        </Form>
                        <Row className="mt-3">
                            <Col className="text-right" xs="12">
                                <Button color="primary" type="button" 
                                    onClick={this.createStandClicked}>
                                    Add
                                </Button>
                            </Col>
                        </Row>
                    </CardBody>
                </Card>
            </div>
        )
    }
} 

export default AddStandComponent