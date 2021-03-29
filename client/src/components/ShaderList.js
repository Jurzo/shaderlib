import CanvasComponent from './CanvasComponent'

const ShaderList = (props) => {
    const size = { width: 300, height: 300 };

    return (
        <div style={{margin: 'auto', width:'50%'}}>
            {props.shaderList.map(shader =>
                <div key={shader.id} style={{float:'left'}}>
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