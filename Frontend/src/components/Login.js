import React from 'react';
import { Button, Form } from 'react-bootstrap';
import Auth from '@aws-amplify/auth';
import './Login.css';
import LoaderButton from './LoaderButton';

import awsconfig from '../aws-exports';

Auth.configure(awsconfig);

export default class Login extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      isLoading: false,
      username: "",
      password: "",
      newPassword: "",
      confirmNewPassword: "",
      email: "",
      phoneNumber: "",
      challenge: "",
      loginException: "",
      user: null
    };
  }

  validateForm() {
    return this.state.username.length > 0 && this.state.password.length > 0;
  }

  validateNewPassword() {
    return this.state.newPassword.length > 0 && this.state.confirmNewPassword.length > 0;
  }

  handleChange = event => {
    this.setState({
      [event.target.id]: event.target.value
    });
  }

  handleSubmit = async event => {
    event.preventDefault();
    this.setState({ isLoading: true });
    try {
        this.state.user = await Auth.signIn(this.state.username, this.state.password);
        if (this.state.user.challengeName === 'NEW_PASSWORD_REQUIRED') {
          this.setState({ challenge: this.state.user.challengeName, loginException: "", isLoading: false });
        } else {
          this.props.userHasAuthenticated(true);
          this.props.history.push("/");
        }
    } catch (err) {
      this.setState({ isLoading: false });
      if (err.code === 'UserNotConfirmedException') {
        this.setState({ loginException: err.code });
      } else if (err.code === 'PasswordResetRequiredException') {
        this.setState({ loginException: err.code });
      } else if (err.code === 'NotAuthorizedException') {
        this.setState({ loginException: "Incorrect username or password." });
      } else if (err.code === 'UserNotFoundException') {
        this.setState({ loginException: "Incorrect username or password." });
      } else {
        this.setState({ loginException: err.code });
      }
    }
  }

  newPassword = async event => {
    event.preventDefault();
    this.setState({ isLoading: true });
    const { requiredAttributes } = this.state.user.challengeParam;
    if (this.state.newPassword == this.state.confirmNewPassword) {
      try {
        const loggedUser = await Auth.completeNewPassword(
          this.state.user,
          this.state.newPassword
        );
        this.props.userHasAuthenticated(true);
        this.props.history.push("/");
      }
      catch (err) {
        this.setState({ isLoading: false });
        if (err.code === 'InvalidPasswordException') {
          this.setState({ loginException: "Invalid password. Password must be 8 characters and contain a number, uppercase letter, lowercase letter, and a special character." });
        } else {
          this.setState({ loginException: err.code });
          console.log(err);
        }
      }
    } else {
      this.setState({ loginException: "Both passwords do not match.", isLoading: false });
    }
  }

  render() {
    return (
      <div className="Login d-flex flex-column justify-content-center">
        { this.state.challenge === 'NEW_PASSWORD_REQUIRED'
          ?
          <div></div>
          :
          <div id="background" className="app flex-row align-items-center">
          	<div className="container">
          		<div className="row justify-content-center">
          			<div className="col-md-8">
          				<div className="card-group">
          					<div className="card p-4">
          						<div className="card-body">
                        <Form onSubmit={this.handleSubmit}>
                            <h1>Login</h1>
                            <p className="text-muted">Sign In to your account</p>
                            <div className="input-group mb-3">
                                <div className="input-group-prepend">
                                    <span className="input-group-text">
                                        <i className="icon-user"></i>
                                    </span>
                                </div>
                                <input className="form-control" type="text" name="username" placeholder="Username"></input>
                            </div>
                            <div className="input-group mb-4">
                                <div className="input-group-prepend">
                                    <span className="input-group-text">
                                        <i className="icon-lock"></i>
                                    </span>
                                </div>
                                <input className="form-control" type="password" name="password" placeholder="Password"></input>
                            </div>
                            <div className="row">
                                <div className="col-6">
                                  <LoaderButton block disabled={!this.validateForm()} type="submit" isLoading={this.state.isLoading} text="Login" loadingText="Logging in…" />
                                </div>
                                <div className="col-6 text-right">
                                    <button className="btn btn-link px-0" type="button">Forgot password?</button>
                                </div>
                            </div>
                            <p className="text-center text-danger">{this.state.loginException}</p>
                        </Form>
                      </div>
                    </div>
                    <div className="card text-white bg-primary py-5 d-md-down-none" style={{width: "44%"}}>
                      <div className="card-body text-center">
                        <div>
                          <h2>Sign up</h2>
                          <a href="register"><button className="btn btn-primary active mt-3" type="button">Register Now!</button></a>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          /*<Form onSubmit={this.newPassword}>
            <div className="d-flex justify-content-center">
              <img alt="AutoClose Logo" id="logo" src="logo.svg"/>
            </div>
            <h4 className="text-center mb-4">Please enter a new password.</h4>
            <Form.Group controlId="newPassword">
              <Form.Control autoFocus type="password" placeholder="New Password" value={this.state.newPassword} onChange={this.handleChange} />
            </Form.Group>

            <Form.Group controlId="confirmNewPassword">
              <Form.Control type="password" placeholder="Confirm New Password" value={this.state.confirmNewPassword} onChange={this.handleChange}/>
            </Form.Group>
            <p className="text-center text-danger">{this.state.loginException}</p>
            <LoaderButton block disabled={!this.validateNewPassword()} type="submit" isLoading={this.state.isLoading} text="Login" loadingText="Logging in…" />
          </Form>
          : <Form onSubmit={this.handleSubmit}>
            <div className="d-flex justify-content-center">
              <img alt="AutoClose Logo" id="logo" src="logo.svg"/>
            </div>
            <h4 className="text-center mb-4">Please log into your account.</h4>
            <Form.Group controlId="username">
              <Form.Control autoFocus type="text" placeholder="Email" value={this.state.username} onChange={this.handleChange} />
            </Form.Group>
            <Form.Group controlId="password">
              <Form.Control type="password" placeholder="Password" value={this.state.password} onChange={this.handleChange}/>
            </Form.Group>
            <p className="text-center text-danger">{this.state.loginException}</p>
            <LoaderButton block disabled={!this.validateForm()} type="submit" isLoading={this.state.isLoading} text="Login" loadingText="Logging in…" />
          </Form>*/
        }
      </div>
    );
  }
}
