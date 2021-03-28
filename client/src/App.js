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
  }

  render() {
    const refreshPage = this.refreshPage;
    var { isLoading, shaderList } = this.state;

    if (isLoading) {
      return <p>Loading...</p>
    }

    const logout = () => {
      if (!AuthenticationService.isUserLoggedIn()) {
        return;
      }
      let postData = {
        requestCode: 0
      };

      let axiosConfig = AuthenticationService.getAxiosConfig();

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

    return (
      <div className="App">
        <Router>
          {AuthenticationService.isUserLoggedIn()
            ? <button onClick={logout} >logout</button>
            : <Link to={{
              pathname: 'login',
              toggle: refreshPage
            }}><button>login</button></Link>}
          <Route
            exact path="/"
            render={(props) => <ShaderList shaderList={shaderList} />}
          />
          <Route path="/login" component={Login} />
        </Router>
      </div>
    );
  }
}

export default App;
