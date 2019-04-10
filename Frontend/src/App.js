import React from 'react';
import Auth from '@aws-amplify/auth';
import { withAuthenticator, SignIn, SignUp, Authenticator } from 'aws-amplify-react';
import './App.css';
import Routes from './Routes';
import { library } from '@fortawesome/fontawesome-svg-core'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faSync } from '@fortawesome/free-solid-svg-icons'

library.add(faSync);

class App extends React.Component {
  constructor(props, context) {
    super(props, context);
  }

  render() {
    if (this.props.authState == "signedIn") {
      return (
        <Routes />
      );
    } else {
      return null;
    }
  }
}

export default withAuthenticator(App);
