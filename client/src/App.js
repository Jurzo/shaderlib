import React, { Component } from 'react';
import CodeMirror from '@uiw/react-codemirror';
import 'codemirror/keymap/sublime';
import 'codemirror/theme/monokai.css';

import CanvasComponent from './components/CanvasComponent';
import EditorComponent from './components/EditorComponent';
import ShaderLibComponent from './components/ShaderLibComponent';

class App extends Component {
  state = {
    isLoading: true,
    shaderList: []
  };

  async componentDidMount() {
    const response = await fetch('http://localhost:8080/shaders');
    const body = await response.json();
    this.setState({ shaderList: body, isLoading: false });
  }

  render() {
    var { isLoading, shaderList} = this.state;

    const fragment =
      "uniform vec2 u_resolution;\n" +
      "uniform float u_time;\n" +
      "void main() \n" +
      "    vec2 st = gl_FragCoord.xy/u_resolution.xy;\n" +
      "    gl_FragColor=vec4(st.x,st.y,0.0,1.0);\n" +
      "}";

    if (isLoading) {
      return <p>Loading...</p>
    }

    return (
      <div className="App">
        <header className="App-header">
          <div>
            {shaderList.map(shader =>
              <div key={shader.id}>
                <ShaderLibComponent 
                  shader = {shader}
                  resolution = {{ width: 800, height: 800 }}
                />
              </div>
            )}
          </div>
        </header>
      </div>
    );
  }
}

export default App;
