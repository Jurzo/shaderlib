import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router';
import CanvasComponent from './CanvasComponent';
import EditorComponent from './EditorComponent';

const ShaderLibComponent = (props) => {
    const [vertex, setVertex] = useState("");
    const [fragment, setFragment] = useState("");
    const [name, setName] = useState("");
    const params = useParams();

    useEffect(() => {
        for (const shader of props.shaderList) {
            if (shader.id === parseInt(params.id)) {
                setVertex(shader.vsource.source);
                setFragment(shader.fsource.source);
                setName(shader.name);
            }
        }
    }, []);

    return (
        <div style={{width: props.resolution.width + 'px', margin:'auto'}}>
            <h1 style={{textAlign:'center'}}>{name}</h1>
            <CanvasComponent
                resolution={props.resolution}
                vertex={vertex}
                fragment={fragment}
            />
            <EditorComponent
                source={fragment}
                setSource={setFragment}
                type={"fragment shader"}
            />
            <EditorComponent
                source={vertex}
                setSource={setVertex}
                type={"vertex shader"}
            />
        </div>
    );

}

export default ShaderLibComponent;