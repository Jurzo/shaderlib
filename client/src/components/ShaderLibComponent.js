import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router';
import CanvasComponent from './CanvasComponent';
import EditorComponent from './EditorComponent';
import AuthenticationService from './service/AuthenticationService'

const ShaderLibComponent = (props) => {
    const [vertex, setVertex] = useState("");
    const [vertexId, setVertexId] = useState("");
    const [fragment, setFragment] = useState("");
    const [fragmentId, setFragmentId] = useState("");
    const [name, setName] = useState("");
    const [id, setId] = useState("");
    const params = useParams();

    const upload = () => {
        const vertexData = {
            id: vertexId,
            source: vertex
        }
        const fragmentData = {
            id: fragmentId,
            source: fragment
        }
        const shaderData = {
            id: id,
            name: name,
            fsource: fragmentData,
            vsource: vertexData
        }
        AuthenticationService.postData(vertexData, 'vertexshader').then(response => {
            console.log(response.data);

            AuthenticationService.postData(fragmentData, 'fragmentshader').then(response => {
                console.log(response.data);

                AuthenticationService.postData(shaderData, 'shader').then(response => {
                    console.log(response.data);
                })
                .catch(error => {
                    console.log(error);
                  });
            })
            .catch(error => {
                console.log(error);
              });
        })
        .catch(error => {
            console.log(error);
          });
    }

    useEffect(() => {
        for (const shader of props.shaderList) {
            if (shader.id === parseInt(params.id)) {
                setVertex(shader.vsource.source);
                setVertexId(shader.vsource.id);
                setFragment(shader.fsource.source);
                setFragmentId(shader.fsource.id);
                setName(shader.name);
                setId(params.id);
            }
        }
    }, []);

    return (
        <div class="shader-lib-component">
            <h1 style={{textAlign:'left'}}>{name}</h1>
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
            <button class="myButton" onClick={() => upload()}>save</button>
        </div>
    );

}

export default ShaderLibComponent;