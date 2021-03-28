import React, { useState, useEffect } from 'react';
import CodeMirror from '@uiw/react-codemirror';
import 'codemirror/keymap/sublime';
import 'codemirror/theme/monokai.css';

const EditorComponent = (props) => {
    const [source, setSource] = useState("");
    const [type, setType] = useState("None");

    useEffect(() => {
        setSource(props.source);
        setType(props.type);
    }, [])

    return (
        <div style={{height: '400px', marginBottom: '40px'}}>
            <h2 style={{ textAlign: 'center' }}>{type}</h2>
            <CodeMirror
                value={source}
                options={{
                    theme: 'monokai',
                    keyMap: 'sublime',
                    mode: 'c++',
                }}
            />
        </div>
    );

}

export default EditorComponent;