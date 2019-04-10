import React from 'react';
import App from './App';
import Home from './components/Home';
import Login from './components/Login';
import { BrowserRouter as Router, Route, Link, Switch } from 'react-router-dom';

export default class Routes extends React.Component {
  render() {
    return (
      <Router>
          <Route exact path="/" component={Home} />
          <Route path="/login" component={Login} />
      </Router>
    )
  }
}
