import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router';
import CanvasComponent from './CanvasComponent';
import EditorComponent from './EditorComponent';
import AuthenticationService from './service/AuthenticationService';

const ShaderLibComponent = (props) => {
    const [vertex, setVertex] = useState("");
    const [vertexId, setVertexId] = useState("");
    const [fragment, setFragment] = useState("");
    const [fragmentId, setFragmentId] = useState("");
    const [name, setName] = useState("");
    const [id, setId] = useState("");
    const params = useParams();

    const update = () => {
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
        post(shaderData);
    }

    const post = (shaderData) => {
        AuthenticationService.postData(shaderData.vsource, 'vertexshader').then(response => {
            console.log(response.data);

            AuthenticationService.postData(shaderData.fsource, 'fragmentshader').then(response => {
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
        if (params.index >= 0) {
            const shader = props.shaderList[params.index];
            setVertex(shader.vsource.source);
            setVertexId(shader.vsource.id);
            setFragment(shader.fsource.source);
            setFragmentId(shader.fsource.id);
            setName(shader.name);
            setId(shader.id);
        } else {
            AuthenticationService.getData("/newshader")
                .then(response => response.json())
                .then(response => {
                    const shader = response;
                    setVertex(shader.vsource.source);
                    setVertexId(shader.vsource.id);
                    setFragment(shader.fsource.source);
                    setFragmentId(shader.fsource.id);
                    setId(); // to not overwrite old shader
                    setName(shader.name);
                })
                .catch(error => {
                    console.log(error)
                });
        }
    }, [params.index]);

    return (
        <div class="shader-lib-component">
            <table style={{ margin: 'auto' }}>
                <tr>
                    <td>
                        <h1 style={{ textAlign: 'left' }}>{name}</h1>
                    </td>
                    <td>
                        {AuthenticationService.isAdmin() ? <button class="updateButton" onClick={() => update()}>save</button> : null}
                    </td>
                </tr>
            </table>
            <CanvasComponent
                resolution={props.resolution}
                vertex={vertex}
                fragment={fragment}
            />
            <EditorComponent
                source={fragment}
                setSource={(setFragment)}
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