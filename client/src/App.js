import React, { Component } from 'react';
import 'codemirror/keymap/sublime';
import 'codemirror/theme/monokai.css';
import './App.css';
import AuthenticationService from './components/service/AuthenticationService';
import axios from 'axios';
import { BrowserRouter as Router, Link, Redirect, Route } from "react-router-dom";

import ShaderLibComponent from './components/ShaderLibComponent';
import ShaderList from './components/ShaderList';
import Login from './components/Login';

const API_URL = "http://localhost:8080"

class App extends Component {
  state = {
    isLoading: true,
    refreshPage: this.refreshPage,
    refresh: false,
    shaderList: []
  }

  refreshPage = () => {
    this.setState({
      refresh: true
    })
  }

  async componentDidMount() {
    const response = await fetch('http://localhost:8080/shaders');
    const body = await response.json();
    this.setState({ shaderList: body, isLoading: false });
    if (AuthenticationService.isUserLoggedIn()) {
      AuthenticationService.reloadInterceptors();
    }
  }

  render() {
    const refreshPage = this.refreshPage;
    let { isLoading, shaderList } = this.state;

    if (isLoading) {
      return <p>Loading...</p>
    }

    const logout = () => {
      if (AuthenticationService.isUserLoggedIn()) {
        const postData = {
          requestCode: 0
        };

        const axiosConfig = AuthenticationService.getAxiosConfig();
        console.log(axiosConfig);

        const url = API_URL + '/user/signout/';

        axios.post(url,
          postData,
          axiosConfig
        )
          .then(response => {
            AuthenticationService.logout();
            refreshPage();
          })
          .catch(error => {
            console.log(error);
          });
      }
    }

    return (

      <div class="parent">
        <Router>
          <div class="div1">
            <nav className="navbar">
              {AuthenticationService.isUserLoggedIn()
                ? <button className="myButton" onClick={logout} >logout</button>
                : <Link to={{
                  pathname: '/login',
                  toggle: refreshPage
                }}><button className="myButton">login</button></Link>}
            </nav>
          </div>
          <div class="div2">
            <Route path="/login" component={Login} />
            <Route
              exact path="/"
              render={(props) => <ShaderList shaderList={shaderList} />}
            />
            <Route
              path="/shader/:id"
              render={(props) => <ShaderLibComponent shaderList={shaderList} resolution={{ width: 1000, height: 1000 }} />} />
          </div>
        </Router>

      </div>
    );
  }
}

export default App;
