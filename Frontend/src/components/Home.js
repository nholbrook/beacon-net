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
              /*{this.props.isAuthenticated
                ? <NavItem onClick={this.props.handleLogout}>Logout</NavItem>
                : <Fragment>
                    <LinkContainer to="/signup">
                      <NavItem>Signup</NavItem>
                    </LinkContainer>
                    <LinkContainer to="/login">
                      <NavItem>Login</NavItem>
                    </LinkContainer>
                  </Fragment>
              }*/
              <header class="app-header navbar">
                <a class="navbar-brand" href="#">
                <h4 class="logo mb-0">BEACON NET</h4>

                  <img class="navbar-brand-minimized" src="img/brand/sygnet.svg" width="30" height="30" alt="CoreUI Logo"></img>
                </a>

                <ul class="nav navbar-nav ml-auto">
                  <li class="nav-item d-md-down-none">
                    <a class="nav-link" href="#">
                    </a>
                  </li>
                  <li class="nav-item dropdown">
                    <a class="nav-link" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
                      <img class="img-avatar" src="icons/avatar.png" alt="admin@bootstrapmaster.com"></img>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right">
                      <div class="dropdown-header text-center">
                        <strong>Account</strong>
                      </div>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="login.html">
                      <i class="fa fa-lock"></i><span class="cui-account-logout" aria-hidden="true"></span> Logout
                    </a>
                  </div>
                </li>
              </ul>

            </header>
            <div class="app-body">
              <div class="sidebar">
                  <nav class="sidebar-nav">
                      <ul class="nav">
                          <li class="nav-item">
                              <a class="nav-link active" href="/">
                                  <i class="nav-icon icon-speedometer"></i> Dashboard
                              </a>
                          </li>
                          <li class="nav-title">Map Management</li>
                          <li class="nav-item">
                              <a class="nav-link" href="#"><span class="cui-map" aria-hidden="true"></span> 2nd Level Concourse</a>
                          </li>
                          <li class="nav-item">
                              <a class="nav-link" href="#"><span class="cui-map" aria-hidden="true"></span> Map 2</a>
                          </li>
                          <li class="nav-title">Beacon Management</li>
                          <li class="nav-item">
                            <a class="nav-link" href="place-beacon"><span class="cui-location-pin" aria-hidden="true"></span> Place and rename beacons</a>
                          </li>
                          <li class="nav-item">
                            <a class="nav-link" href="beacon-connection"><span class="cui-rss" aria-hidden="true"></span> View beacon connection status</a>
                          </li>
                          <li class="nav-item">
                            <a class="nav-link" href="upload-map"><span class="cui-cloud-upload" aria-hidden="true"></span> Upload a map</a>
                          </li>
                          <li class="nav-title">Other</li>
                          <li class="nav-item">
                              <a class="nav-link" href="/raw"><i class="nav-icon icon-graph"></i> View Raw Data</a>
                          </li>
                      </ul>
                  </nav>
                <button class="sidebar-minimizer brand-minimizer" type="button"></button>
              </div>
              <main class="main">
                <ol class="breadcrumb">
                  <li class="breadcrumb-item">Home</li>
                  <li class="breadcrumb-item active">Dashboard</li>
                  <li class="breadcrumb-menu d-md-down-none">
                    <div class="btn-group" role="group" aria-label="Button group"></div>
                  </li>
                </ol>
                <div class="container-fluid">
                  <div class="animated fadeIn">

                    <div class="card">
                      <div class="card-body">
                        <div class="row">
                          <div class="col-sm-5">
                            <h4 class="card-title mb-0">Traffic</h4>
                            <div class="small text-muted"><span class="cui-calendar" aria-hidden="true"></span> Date</div>
                            <div class="small text-muted"><span class="cui-map" aria-hidden="true"></span> Map: Map name</div>
                            <div class="small text-muted" id="filterdisplaytitle"><strong>Filter:</strong> None</div>
                          </div>
                          <div class="col-sm-7 d-none d-md-block">

                            <div class="btn btn-primary float-right" data-toggle="modal" data-target="#filterModal">
                              <span class="cui-chevron-bottom" aria-hidden="true"></span> Filter
                            </div>
                            <div class="btn-group btn-group-toggle float-right mr-3" data-toggle="buttons">
                              <label class="btn btn-outline-secondary">
                                <input id="option1" type="radio" name="options" autocomplete="off"> Day</input>
                              </label>
                              <label class="btn btn-outline-secondary active">
                                <input id="option2" type="radio" name="options" autocomplete="off" checked=""> Month</input>
                              </label>
                              <label class="btn btn-outline-secondary">
                                <input id="option3" type="radio" name="options" autocomplete="off"> Year</input>
                              </label>
                            </div>
                          </div>
                        </div>
                        <div class="chart-wrapper">
                            <div id="photo" style="height: 500px; width: 100%"></div>
                        </div>
                      </div>
                      <div class="card-footer">
                        <div class="row text-center">
                          <div class="col-sm-12 col-md mb-sm-2 mb-0">
                            <div class="text-muted">Congested</div>
                            <strong>80%</strong>
                            <div class="progress progress-xs mt-2">
                              <div class="progress-bar bg-congested" role="progressbar" style="width: 100%" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                          </div>
                          <div class="col-sm-12 col-md mb-sm-2 mb-0">
                            <div class="text-muted">Very Heavy</div>
                            <strong>70%</strong>
                            <div class="progress progress-xs mt-2">
                              <div class="progress-bar bg-heavy" role="progressbar" style="width: 100%" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                          </div>
                          <div class="col-sm-12 col-md mb-sm-2 mb-0">
                            <div class="text-muted">Heavy</div>
                            <strong>60%</strong>
                            <div class="progress progress-xs mt-2">
                              <div class="progress-bar bg-avg-heavy" role="progressbar" style="width: 100%" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                          </div>
                          <div class="col-sm-12 col-md mb-sm-2 mb-0">
                            <div class="text-muted">Average</div>
                            <strong>15%</strong>
                            <div class="progress progress-xs mt-2">
                              <div class="progress-bar bg-avg" role="progressbar" style="width: 100%" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                          </div>
                          <div class="col-sm-12 col-md mb-sm-2 mb-0">
                            <div class="text-muted">Light</div>
                            <strong>10%</strong>
                            <div class="progress progress-xs mt-2">
                              <div class="progress-bar bg-li" role="progressbar" style="width: 100%" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                          </div>
                          <div class="col-sm-12 col-md mb-sm-2 mb-0">
                            <div class="text-muted">Very Light</div>
                            <strong>5%</strong>
                            <div class="progress progress-xs mt-2">
                              <div class="progress-bar bg-vlight" role="progressbar" style="width: 100%" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                          </div>
                          <div class="col-sm-12 col-md mb-sm-2 mb-0">
                            <div class="text-muted">Empty</div>
                            <strong>0.5%</strong>
                            <div class="progress progress-xs mt-2">
                              <div class="progress-bar bg-empty" role="progressbar" style="width: 100%" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </main>
              <div class="modal fade" id="filterModal" tabindex="-1" role="dialog" aria-labelledby="filtereModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                  <div class="modal-content">
                    <div class="modal-header">
                      <h5 class="modal-title" id="filtereModalLabel">Filter map locations</h5>
                      <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                      </button>
                    </div>
                    <div class="modal-body">
                      <div class="row">
                        <div class="col-md-6">
                          <form>
                            <p><label><input class="filtercheckbox" type="checkbox" value="Beacon A" name="locationA">Beacon A</input></label></p>
                            <p><label><input class="filtercheckbox" type="checkbox" value="Beacon B" name="locationA">Beacon B</input></label></p>
                            <p><label><input class="filtercheckbox" type="checkbox" value="Beacon C" name="locationA">Beacon C</input></label></p>
                          </form>
                        </div>
                      </div>
                    </div>
                    <div class="modal-footer">
                      <button type="button" class="btn btn-primary" id="filterbtnapply"><span class="cui-check" aria-hidden="true"></span> Apply filter</button>
                      <button type="button" class="btn btn-secondary" id="filterbtnclear">Clear filter</button>
                      <button type="button" class="btn btn-secondary" data-dismiss="modal"><span class="cui-ban" aria-hidden="true"></span> Close</button>
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
