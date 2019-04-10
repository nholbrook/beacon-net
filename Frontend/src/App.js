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
  constructor(props) {
    super(props);

    this.state = {
      isAuthenticated: false
    };
  }

  async componentDidMount() {
    try {
      await Auth.currentSession();
      this.userHasAuthenticated(true);
    }
    catch(e) {
      if (e !== 'No current user') {
        alert(e);
      }
    }

    this.setState({ isAuthenticating: false });
  }

  handleLogout = async event =>  {
    try {
      await Auth.signOut();
      this.userHasAuthenticated(false);
    }
    catch(e) {
      alert(e);
    }
  }

  userHasAuthenticated = authenticated => {
    this.setState({ isAuthenticated: authenticated });
  }

  render() {
    const childProps = {
      isAuthenticated: this.state.isAuthenticated,
      userHasAuthenticated: this.userHasAuthenticated,
      handleLogout: this.handleLogout
    };

    return (
      !this.state.isAuthenticating &&
      <Routes />
    );
  }
}

export default withAuthenticator(App, true);
