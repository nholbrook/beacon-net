import React from "react";
import { Button, Form, InputGroup } from "react-bootstrap";
import LoaderButton from './LoaderButton';
import Auth from '@aws-amplify/auth';
import { SignIn } from "aws-amplify-react";

export class CustomSignIn extends SignIn {
  constructor(props) {
    super(props);
    this._validAuthStates = ["signIn", "signedOut", "signedUp"];
  }

  handleChange = event => {
    this.setState({
      [event.target.id]: event.target.value
    });
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

  showComponent(theme) {
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
                                <Form.Group controlId="username" bssize="large" style={{width: '100%'}}>
                                  <Form.Label>Username</Form.Label>
                                  <InputGroup>
                                    <InputGroup.Prepend>
                                      <InputGroup.Text id="inputGroupPrepend"><i className="icon-user"></i></InputGroup.Text>
                                    </InputGroup.Prepend>
                                    <Form.Control
                                      autoFocus
                                      type="text"
                                      key="username"
                                      name="username"
                                      onChange={this.handleInputChange}
                                    />
                                  </InputGroup>
                                </Form.Group>
                            </div>
                            <div className="input-group mb-4">
                                <Form.Group controlId="password" bssize="large" style={{width: '100%'}}>
                                  <Form.Label>Password</Form.Label>
                                  <InputGroup>
                                    <InputGroup.Prepend>
                                      <InputGroup.Text id="inputGroupPrepend"><i className="icon-lock"></i></InputGroup.Text>
                                    </InputGroup.Prepend>
                                    <Form.Control
                                      autoFocus
                                      type="password"
                                      key="password"
                                      name="password"
                                      onChange={this.handleInputChange}
                                    />
                                  </InputGroup>
                                </Form.Group>
                            </div>
                            <div className="row">
                                <div className="col-12">
                                  <LoaderButton block type="submit" isLoading={this.state.isLoading} text="Login" loadingText="Logging in…" onClick={() => super.signIn()}/>
                                </div>
                                <div className="col-12 text-center">
                                    <button className="btn btn-link px-0" type="button" onClick={() => super.changeState("forgotPassword")}>Forgot password?</button>
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
                          <a href="register"><button className="btn btn-primary active mt-3" type="button" onClick={() => super.changeState("signUp")}>Register Now!</button></a>
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
