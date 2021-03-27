import React, { useEffect, useRef, useState } from 'react';
import { useFrame } from 'react-three-fiber';
import * as THREE from 'three';

const ShaderComponent = (props) => {
    const mesh = useRef();
    const geometry = new THREE.PlaneBufferGeometry( 2, 2 );

    const fragment = props.fragment;

    const vertex = props.vertex;
    
    const uniforms = {
        u_time: { type: "f", value: 1.0 },
        u_resolution: { type: "v2", value: new THREE.Vector2(props.resolution.width, props.resolution.height)},
        u_mouse: { type: "v2", value: new THREE.Vector2() }
    };

    
    const material = new THREE.ShaderMaterial( {
        uniforms: uniforms,
        vertexShader: vertex,
        fragmentShader: fragment
    });
    
    useFrame(() => {
        mesh.current.material.uniforms.u_time.value += 0.01;
    })

    return (
        <mesh
        ref={mesh}
        geometry={geometry}
        material={material}
        ></mesh>
    )
}

export default ShaderComponent;