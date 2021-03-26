import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import CodeMirror from '@uiw/react-codemirror';
import 'codemirror/keymap/sublime';
import 'codemirror/theme/monokai.css';

class App extends Component {
  state = {
    isLoading: true,
    vertex: []
  };

  async componentDidMount() {
    const response = await fetch('http://localhost:8080/vertexshaders');
    const body = await response.json();
    this.setState({ vertexList: body, isLoading: false });
  }

  render() {
    const { isLoading, vertexList } = this.state;
    const code = 'float a = 0.0f;';

    if (isLoading) {
      return <p>Loading...</p>
    }

    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <div>
            <h2>Vertex List</h2>
            {vertexList.map(vertex =>
              <div key={vertex.id}>
                {vertex.source}
              </div>
            )}
          </div>
        </header>
        <CodeMirror
          value={code}
          options={{
            theme: 'monokai',
            keyMap: 'sublime',
            mode: 'c++',
          }}
        />
      </div>
    );
  }
}

export default App;
