import React, { Component, Fragment } from 'react';
import { Image, Table, Navbar, NavItem, Nav, Form, FormControl, Button, Modal, ButtonToolbar, ToggleButtonGroup, ToggleButton } from 'react-bootstrap';
import { LinkContainer } from 'react-router-bootstrap';
import axios from 'axios';
import Auth from '@aws-amplify/auth';
import L, { HeatmapOverlay } from 'leaflet';
import { Map, Marker, Popup, TileLayer } from 'react-leaflet';
import HeatmapLayer from '../HeatmapLayer';
import './Home.css';
var dateFormat = require('dateformat');

export default class Home extends Component {
  constructor(props, context) {
    super(props, context);

    this.handleShow = this.handleShow.bind(this);
    this.handleClose = this.handleClose.bind(this);

    this.state = {
      show: false,
      beacons: [],
      summaries: [],
      username: ""
    };

  }

  loadData() {
    Auth.currentAuthenticatedUser({}).then(user => {
      this.setState({username: user.username});

      axios.get('http://beaconnet.co/beacons?accountId=' + this.state.username)
        .then(response => {
          this.setState({beacons: response.data });
      });

      axios.get('http://beaconnet.co/summaries?accountId=' + this.state.username)
        .then(response => {
          var json = response.data;
          this.setState({summaries: response.data });
          console.log(this.state.summaries);

          const script = document.createElement("script");

          script.src = '../heatmap.js';
          script.async = true;

          document.body.appendChild(script);
      })
    });
  }

  componentDidMount() {
    this.loadData();
    //this.loadHeatmap();
  }

  handleClose() {
    this.setState({ show: false });
  }

  handleShow() {
    this.setState({ show: true });
  }

  filterDataDay() {
    var now = new Date();
    var endTime = dateFormat(now, 'yyyy-mm-dd&20HH:MM:ss.L');
    var start = new Date();
    start.setHours(now.getHours()-1);
    var startTime = dateFormat(start, 'yyyy-mm-dd&20HH:MM:ss.L');
    axios.get('http://beaconnet.co/summaries?accountId=' + this.state.username + '&startTime=' + startTime + '&endTime=' + endTime)
      .then(response => {
        if (response.data != null) {
          this.setState({summaries: response.data });
          console.log(this.state.summaries);
        }
    });
  }

  logout() {
    Auth.signOut()
    .then(data => console.log(data))
    .catch(err => console.log(err));
  }

  loadHeatmap() {
    console.log("TESTING");

    var json = this.state.summaries;

    var points = [];
    var max = 0;

    for(var i = 0; i < json.length; i++) {
        var obj = json[i];
        max = Math.max(max, obj.count);
        var point = {
            x: parseInt(obj.y, 10) * -1,
            y: parseInt(obj.x, 10),
            value: parseInt(obj.count, 10)
        };
        points.push(point);
    }

    var data = {
      max: max,
      data: points
    };

    console.log(data);

    var baseLayer = L.tileLayer.zoomify('https://s3.amazonaws.com/beacon-net/bsb1/', {
        width: 3180,
        height: 2064,
        tolerance: 0.8
    });

    var cfg = {
        radius: 10,
        maxOpacity: .8,
        scaleRadius: true,
        useLocalExtrema: true,
        latField: 'x',
        lngField: 'y',
        valueField: 'value'
    };

    var heatmapLayer = new HeatmapOverlay(cfg);

    var map = new L.Map('photo', {
      crs: L.CRS.Simple,
      center: [1590, 1032],
      zoom: 4,
      layers: [baseLayer, heatmapLayer]
    });

    heatmapLayer.setData(data);
  }

  render() {
    const { beacons } = this.state;
    const { summaries } = this.state;
    return (
      <div className="Home">
        <header className="app-header navbar">
          <a className="navbar-brand pl-3" href="#">
            <img className="img-fluid" src="icons/beaconnet.png" alt="CoreUI Logo"></img>
          </a>
          <Form inline>
            Welcome&nbsp;<b>{this.state.username}</b>!
            <Button type="submit" className="m-3" onClick={this.logout}>Logout</Button>
          </Form>
      </header>
      <div className="app-body">
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
                      <Button className="float-right" variant="primary" onClick={this.handleShow}>
                        Filter
                      </Button>
                      <ButtonToolbar className="float-right">
                        <ToggleButtonGroup type="radio" name="options" defaultValue={2}>
                          <ToggleButton value={1} onClick={this.filterDataDay}>Day</ToggleButton>
                          <ToggleButton value={2} onClick={this.filterDataMonth}>Month</ToggleButton>
                          <ToggleButton value={3} onClick={this.filterDataYear}>Year</ToggleButton>
                        </ToggleButtonGroup>
                      </ButtonToolbar>
                    </div>
                  </div>
                  <div className="chart-wrapper">
                      <div className="d-flex justify-content-center" id="photo" style={{height: '500px', width: '100%'}}>
                        <Image src="https://s3.amazonaws.com/beacon-net-frontend-dev/icons/map.PNG" className="img-fluid"></Image>
                      </div>
                  </div>
                </div>
                <div className="card-footer">
                  <div className="row text-center">
                    <div className="col-sm-12 col-md mb-sm-2 mb-0">
                      <div className="text-muted">Congested</div>
                      <strong>80%</strong>
                      <div className="progress progress-xs mt-2">
                        <div className="progress-bar bg-congested" role="progressbar" style={{width: '100%'}} aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"></div>
                      </div>
                    </div>
                    <div className="col-sm-12 col-md mb-sm-2 mb-0">
                      <div className="text-muted">Very Heavy</div>
                      <strong>70%</strong>
                      <div className="progress progress-xs mt-2">
                        <div className="progress-bar bg-heavy" role="progressbar" style={{width: '100%'}} aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"></div>
                      </div>
                    </div>
                    <div className="col-sm-12 col-md mb-sm-2 mb-0">
                      <div className="text-muted">Heavy</div>
                      <strong>60%</strong>
                      <div className="progress progress-xs mt-2">
                        <div className="progress-bar bg-avg-heavy" role="progressbar" style={{width: '100%'}} aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"></div>
                      </div>
                    </div>
                    <div className="col-sm-12 col-md mb-sm-2 mb-0">
                      <div className="text-muted">Average</div>
                      <strong>15%</strong>
                      <div className="progress progress-xs mt-2">
                        <div className="progress-bar bg-avg" role="progressbar" style={{width: '100%'}} aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"></div>
                      </div>
                    </div>
                    <div className="col-sm-12 col-md mb-sm-2 mb-0">
                      <div className="text-muted">Light</div>
                      <strong>10%</strong>
                      <div className="progress progress-xs mt-2">
                        <div className="progress-bar bg-li" role="progressbar" style={{width: '100%'}} aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"></div>
                      </div>
                    </div>
                    <div className="col-sm-12 col-md mb-sm-2 mb-0">
                      <div className="text-muted">Very Light</div>
                      <strong>5%</strong>
                      <div className="progress progress-xs mt-2">
                        <div className="progress-bar bg-vlight" role="progressbar" style={{width: '100%'}} aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"></div>
                      </div>
                    </div>
                    <div className="col-sm-12 col-md mb-sm-2 mb-0">
                      <div className="text-muted">Empty</div>
                      <strong>0.5%</strong>
                      <div className="progress progress-xs mt-2">
                        <div className="progress-bar bg-empty" role="progressbar" style={{width: '100%'}} aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"></div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>

              <div className="card">
                <div className="card-body">
                  <div className="row">
                    <div className="col-sm-5">
                      <h4 className="card-title mb-0">Beacon Connection Status</h4>
                      <div className="small text-muted"><span className="cui-calendar" aria-hidden="true"></span> Place and Rename Beacons</div>
                    </div>
                    <div className="col-sm-7 d-none d-md-block">
                      <Button className="float-right" variant="primary">
                        Add New Beacon
                      </Button>
                    </div>
                    <div className="p-3 w-100">
                    <Table striped bordered hover>
                      <thead>
                        <tr>
                          <th>Beacon ID</th>
                          <th>Name</th>
                          <th>Last Seen</th>
                          <th>Map ID</th>
                          <th>X</th>
                          <th>Y</th>
                        </tr>
                      </thead>
                      <tbody>
                      {beacons.map(beacon =>
                        <tr key={beacon.beaconId}>
                          <th>{beacon.beaconId}</th>
                          <th>{beacon.name}</th>
                          <th>{beacon.lastTime}</th>
                          <th>{beacon.mapId}</th>
                          <th>{beacon.x}</th>
                          <th>{beacon.y}</th>
                        </tr>
                      )}
                      </tbody>
                  </Table>
                  </div>
                </div>
              </div>
              </div>

              <div className="card">
                <div className="card-body">
                  <div className="row">
                    <div className="col-sm-5">
                      <h4 className="card-title mb-0">Raw Status Data</h4>
                    </div>
                    <div className="p-3 w-100">
                    <Table striped bordered hover>
                      <thead>
                        <tr>
                          <th>Beacon ID</th>
                          <th>Timestamp</th>
                          <th>Count</th>
                        </tr>
                      </thead>
                      <tbody>
                      {summaries.map(summary =>
                        <tr key={summary.summaryId}>
                          <th>{summary.beaconId}</th>
                          <th>{summary.summaryTime}</th>
                          <th>{summary.count}</th>
                        </tr>
                      )}
                      </tbody>
                  </Table>
                  <nav aria-label="Page navigation example">
                  <ul className="pagination justify-content-center">
                  <li className="page-item disabled">
                  <a className="page-link" href="#" tabIndex="-1">Previous</a>
                  </li>
                  <li className="page-item">
                  <a className="page-link" href="#">1</a>
                  </li>
                  <li className="page-item">
                  <a className="page-link" href="#">2</a>
                  </li>
                  <li className="page-item">
                  <a className="page-link" href="#">3</a>
                  </li>
                  <li className="page-item">
                  <a className="page-link" href="#">Next</a>
                  </li>
                  </ul>
                  </nav>
                  </div>
                </div>
              </div>
              </div>
            </div>
          </div>
        </main>
        <Modal show={this.state.show} onHide={this.handleClose}>
          <Modal.Header closeButton>
            <Modal.Title>Filter Map Locations</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <Form>
              <Form.Group controlId="formBasicEmail">
                <Form.Check inline type="checkbox" label="Beacon A" />
                <Form.Check inline type="checkbox" label="Beacon B" />
                <Form.Check inline type="checkbox" label="Beacon C" />
              </Form.Group>
            </Form>
          </Modal.Body>
          <Modal.Footer>
            <Button variant="secondary" onClick={this.handleClose}>
              Close
            </Button>
            <Button variant="secondary" onClick={this.clearFilter}>
              Clear Filter
            </Button>
            <Button variant="primary" onClick={this.handleClose}>
              Apply Filter
            </Button>
          </Modal.Footer>
        </Modal>
      </div>
      <script src="http://cdn.leafletjs.com/leaflet-0.5.1/leaflet.js"></script>
      <script src="%PUBLIC_URL%/js/heatmap.min.js"></script>
      <script src="%PUBLIC_URL%/js/leaflet-heatmap.min.js"></script>
      <script src="%PUBLIC_URL%/js/L.TileLayer.Zoomify.js"></script>
      </div>
    );
  }
}
