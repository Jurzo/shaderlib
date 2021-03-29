import CanvasComponent from './CanvasComponent'

const ShaderList = (props) => {
    const size = { width: 300, height: 300 };

    return (
        <div>
            {props.shaderList.map(shader =>
                <div key={shader.id} style={{cursor: 'pointer'}} onClick={() => {
                    window.location.href='http://localhost:3000/shader/' + shader.id;
                }}>
                    <CanvasComponent
                        resolution={size}
                        vertex={shader.vsource.source}
                        fragment={shader.fsource.source}
                        id={shader.id}
                    />
                </div>
            )}
        </div>
    );
}

export default ShaderList;