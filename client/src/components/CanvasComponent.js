import React from 'react';
import { Canvas} from 'react-three-fiber';
import ShaderComponent from './ShaderComponent';

const CanvasComponent = (props) => {
    const style = {width: props.resolution.width + "px", height: props.resolution.height + "px"};
    return (
        <div style={{...style, cursor: 'pointer'}}>
            <Canvas resize={{scroll: false}}
            onClick={() => {
                window.location.href='http://localhost:3000/shader/' + props.id;
            }}>
                <ShaderComponent resolution = {props.resolution} vertex = {props.vertex} fragment = {props.fragment}/>
            </Canvas>
        </div>
    )
}

export default CanvasComponent;