import React, { Component, Fragment } from 'react';
import { Table, Navbar, NavItem, Nav, Form, FormControl, Button } from 'react-bootstrap';
import { LinkContainer } from 'react-router-bootstrap';

import './Home.css';

export default class Home extends Component {
  render() {
    return (
      <div className="Home">
      <Navbar bg="light" expand="lg">
        <div className="container">
          <Navbar.Brand href="#home">AutoClose</Navbar.Brand>
          <Navbar.Toggle aria-controls="basic-navbar-nav" />
          <Navbar.Collapse id="basic-navbar-nav">
            <Nav className="mr-auto">
              <header className="app-header navbar">
                <a className="navbar-brand" href="#">
                <h4 className="logo mb-0">BEACON NET</h4>

                  <img className="navbar-brand-minimized" src="img/brand/sygnet.svg" width="30" height="30" alt="CoreUI Logo"></img>
                </a>

                <ul className="nav navbar-nav ml-auto">
                  <li className="nav-item d-md-down-none">
                    <a className="nav-link" href="#">
                    </a>
                  </li>
                  <li className="nav-item dropdown">
                    <a className="nav-link" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
                      <img className="img-avatar" src="icons/avatar.png" alt="admin@bootstrapmaster.com"></img>
                    </a>
                    <div className="dropdown-menu dropdown-menu-right">
                      <div className="dropdown-header text-center">
                        <strong>Account</strong>
                      </div>
                    <div className="dropdown-divider"></div>
                    <a className="dropdown-item" href="login.html">
                      <i className="fa fa-lock"></i><span className="cui-account-logout" aria-hidden="true"></span> Logout
                    </a>
                  </div>
                </li>
              </ul>

            </header>
            <div className="app-body">
              <div className="sidebar">
                  <nav className="sidebar-nav">
                      <ul className="nav">
                          <li className="nav-item">
                              <a className="nav-link active" href="/">
                                  <i className="nav-icon icon-speedometer"></i> Dashboard
                              </a>
                          </li>
                          <li className="nav-title">Map Management</li>
                          <li className="nav-item">
                              <a className="nav-link" href="#"><span className="cui-map" aria-hidden="true"></span> 2nd Level Concourse</a>
                          </li>
                          <li className="nav-item">
                              <a className="nav-link" href="#"><span className="cui-map" aria-hidden="true"></span> Map 2</a>
                          </li>
                          <li className="nav-title">Beacon Management</li>
                          <li className="nav-item">
                            <a className="nav-link" href="place-beacon"><span className="cui-location-pin" aria-hidden="true"></span> Place and rename beacons</a>
                          </li>
                          <li className="nav-item">
                            <a className="nav-link" href="beacon-connection"><span className="cui-rss" aria-hidden="true"></span> View beacon connection status</a>
                          </li>
                          <li className="nav-item">
                            <a className="nav-link" href="upload-map"><span className="cui-cloud-upload" aria-hidden="true"></span> Upload a map</a>
                          </li>
                          <li className="nav-title">Other</li>
                          <li className="nav-item">
                              <a className="nav-link" href="/raw"><i className="nav-icon icon-graph"></i> View Raw Data</a>
                          </li>
                      </ul>
                  </nav>
                <button className="sidebar-minimizer brand-minimizer" type="button"></button>
              </div>
              <main className="main">
                <ol className="breadcrumb">
                  <li className="breadcrumb-item">Home</li>
                  <li className="breadcrumb-item active">Dashboard</li>
                  <li className="breadcrumb-menu d-md-down-none">
                    <div className="btn-group" role="group" aria-label="Button group"></div>
                  </li>
                </ol>
                <div className="container-fluid">
                  <div className="animated fadeIn">

                    <div className="card">
                      <div className="card-body">
                        <div className="row">
                          <div className="col-sm-5">
                            <h4 className="card-title mb-0">Traffic</h4>
                            <div className="small text-muted"><span className="cui-calendar" aria-hidden="true"></span> Date</div>
                            <div className="small text-muted"><span className="cui-map" aria-hidden="true"></span> Map: Map name</div>
                            <div className="small text-muted" id="filterdisplaytitle"><strong>Filter:</strong> None</div>
                          </div>
                          <div className="col-sm-7 d-none d-md-block">

                            <div className="btn btn-primary float-right" data-toggle="modal" data-target="#filterModal">
                              <span className="cui-chevron-bottom" aria-hidden="true"></span> Filter
                            </div>
                            <div className="btn-group btn-group-toggle float-right mr-3" data-toggle="buttons">
                              <label className="btn btn-outline-secondary">
                                <input id="option1" type="radio" name="options" autoComplete="off"> Day</input>
                              </label>
                              <label className="btn btn-outline-secondary active">
                                <input id="option2" type="radio" name="options" autoComplete="off" defaultChecked> Month</input>
                              </label>
                              <label className="btn btn-outline-secondary">
                                <input id="option3" type="radio" name="options" autoComplete="off"> Year</input>
                              </label>
                            </div>
                          </div>
                        </div>
                        <div className="chart-wrapper">
                            <div id="photo" style="height: 500px; width: 100%"></div>
                        </div>
                      </div>
                      <div className="card-footer">
                        <div className="row text-center">
                          <div className="col-sm-12 col-md mb-sm-2 mb-0">
                            <div className="text-muted">Congested</div>
                            <strong>80%</strong>
                            <div className="progress progress-xs mt-2">
                              <div className="progress-bar bg-congested" role="progressbar" style="width: 100%" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                          </div>
                          <div className="col-sm-12 col-md mb-sm-2 mb-0">
                            <div className="text-muted">Very Heavy</div>
                            <strong>70%</strong>
                            <div className="progress progress-xs mt-2">
                              <div className="progress-bar bg-heavy" role="progressbar" style="width: 100%" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                          </div>
                          <div className="col-sm-12 col-md mb-sm-2 mb-0">
                            <div className="text-muted">Heavy</div>
                            <strong>60%</strong>
                            <div className="progress progress-xs mt-2">
                              <div className="progress-bar bg-avg-heavy" role="progressbar" style="width: 100%" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                          </div>
                          <div className="col-sm-12 col-md mb-sm-2 mb-0">
                            <div className="text-muted">Average</div>
                            <strong>15%</strong>
                            <div className="progress progress-xs mt-2">
                              <div className="progress-bar bg-avg" role="progressbar" style="width: 100%" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                          </div>
                          <div className="col-sm-12 col-md mb-sm-2 mb-0">
                            <div className="text-muted">Light</div>
                            <strong>10%</strong>
                            <div className="progress progress-xs mt-2">
                              <div className="progress-bar bg-li" role="progressbar" style="width: 100%" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                          </div>
                          <div className="col-sm-12 col-md mb-sm-2 mb-0">
                            <div className="text-muted">Very Light</div>
                            <strong>5%</strong>
                            <div className="progress progress-xs mt-2">
                              <div className="progress-bar bg-vlight" role="progressbar" style="width: 100%" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                          </div>
                          <div className="col-sm-12 col-md mb-sm-2 mb-0">
                            <div className="text-muted">Empty</div>
                            <strong>0.5%</strong>
                            <div className="progress progress-xs mt-2">
                              <div className="progress-bar bg-empty" role="progressbar" style="width: 100%" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </main>
              <div className="modal fade" id="filterModal" tabindex="-1" role="dialog" aria-labelledby="filtereModalLabel" aria-hidden="true">
                <div className="modal-dialog" role="document">
                  <div className="modal-content">
                    <div className="modal-header">
                      <h5 className="modal-title" id="filtereModalLabel">Filter map locations</h5>
                      <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                      </button>
                    </div>
                    <div className="modal-body">
                      <div className="row">
                        <div className="col-md-6">
                          <form>
                            <p><label><input className="filtercheckbox" type="checkbox" value="Beacon A" name="locationA">Beacon A</input></label></p>
                            <p><label><input className="filtercheckbox" type="checkbox" value="Beacon B" name="locationA">Beacon B</input></label></p>
                            <p><label><input className="filtercheckbox" type="checkbox" value="Beacon C" name="locationA">Beacon C</input></label></p>
                          </form>
                        </div>
                      </div>
                    </div>
                    <div className="modal-footer">
                      <button type="button" className="btn btn-primary" id="filterbtnapply"><span className="cui-check" aria-hidden="true"></span> Apply filter</button>
                      <button type="button" className="btn btn-secondary" id="filterbtnclear">Clear filter</button>
                      <button type="button" className="btn btn-secondary" data-dismiss="modal"><span className="cui-ban" aria-hidden="true"></span> Close</button>
                    </div>
                  </div>
                </div>
              </div>
              </div>
            </Nav>
            <Form inline>
              <FormControl type="text" placeholder="Search" className="mr-sm-2" />
              <Button variant="outline-success">Search</Button>
            </Form>
          </Navbar.Collapse>
        </div>
      </Navbar>
      <div className="container pt-5">
      <Table striped bordered hover size="sm">
        <thead>
          <tr>
            <th>#</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Zipcode</th>
            <th>Phone Number</th>
            <th>Email Address</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>1</td>
            <td>Kenneth</td>
            <td>Parker</td>
            <td>60607</td>
            <td>312-421-2164</td>
            <td>KennethCParker@teleworm.us</td>
          </tr>
          <tr>
            <td>2</td>
            <td>Joseph</td>
            <td>Hill</td>
            <td>27313</td>
            <td>336-674-7948</td>
            <td>JosephEHill@armyspy.com</td>
          </tr>
          <tr>
            <td>3</td>
            <td>Neil</td>
            <td>Johnson</td>
            <td>79852</td>
            <td>432-371-6116</td>
            <td>NeilLJohnson@armyspy.com</td>
          </tr>
        </tbody>
      </Table>
      </div>
      </div>
    );
  }
}
