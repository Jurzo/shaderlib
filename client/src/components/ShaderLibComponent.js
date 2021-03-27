import React, { useState, useEffect } from 'react';
import CanvasComponent from './CanvasComponent';
import EditorComponent from './EditorComponent';

const ShaderLibComponent = (props) => {
    return (
        <div style={{width: props.resolution.width, margin:'auto'}}>
            <h1 style={{textAlign:'center'}}>{props.shader.name}</h1>
            <CanvasComponent
                resolution={props.resolution}
                vertex={props.shader.vsource.source}
                fragment={props.shader.fsource.source}
            />
            <EditorComponent
                source={props.shader.fsource.source}
                type={"fragment shader"}
            />
            {/* <EditorComponent
                source={props.shader.vsource.source}
                type={"vertex shader"}
            /> */}
        </div>
    );

}

export default ShaderLibComponent;