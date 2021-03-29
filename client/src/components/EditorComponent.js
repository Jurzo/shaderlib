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
        <div style={{height: '400px', marginBottom: '60px' ,marginTop: '20px'}}>
            <h2 style={{ textAlign: 'left' }}>{type}</h2>
            <CodeMirror
                value={props.source}
                onChange={(editor)=>setSource(editor.getValue())}
                options={{
                    theme: 'monokai',
                    keyMap: 'sublime',
                    mode: 'c++',
                }}
            />
            <button class="updateButton" onClick={() => props.setSource(source)}>update</button>
        </div>
    );

}

export default EditorComponent;