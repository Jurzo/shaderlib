import React, { useState, useEffect } from 'react';
import CodeMirror from '@uiw/react-codemirror';
import 'codemirror/keymap/sublime';
import 'codemirror/theme/monokai.css';

const EditorComponent = (props) => {
    const [source, setSource] = useState("");
    const [type, setType] = useState("None");

    useEffect(() => {
        setType(props.type);
    }, []);

    return (
        <div style={{height: '400px', marginBottom: '40px'}}>
            <h2 style={{ textAlign: 'center' }}>{type}</h2>
            <CodeMirror
                value={props.source}
                onChange={(editor)=>setSource(editor.getValue())}
                options={{
                    theme: 'monokai',
                    keyMap: 'sublime',
                    mode: 'c++',
                }}
            />
            <button style={{float:'left'}} onClick={() => props.setSource(source)}>update</button>
        </div>
    );

}

export default EditorComponent;