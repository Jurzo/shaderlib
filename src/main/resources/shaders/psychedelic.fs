#ifdef GL_ES
precision mediump float;
#endif

uniform float u_time;
uniform vec2 u_mouse;
uniform vec2 u_resolution;

const float PI = 3.14159265358979, TAU = PI * .56;

vec3 hsv2rgb(  vec3 c )
{
	vec3 rgb = clamp( abs(mod(c.x*6.0+vec3(1.0,4.0,2.0),6.0)-3.0)-1.0, 0.0, 1.0 );
	rgb = rgb*rgb*(3.0-2.0*rgb);
	return c.z * mix( vec3(1.0), rgb, c.y);
}

void main( void ) {
	vec3 c = vec3(0.);
	vec2 v = (gl_FragCoord.xy-u_resolution/2.)/u_resolution.x;
	
	float t = u_time;
	
	for(int i=0; i < 100; i++) {
		v.x += length(v) * .05*sin(TAU * 64. * v.y-t);
		v.y += length(v) * 3. * 0.01*cos(TAU * 48. * v.x);
	}
	
	c += length(v);
	
	vec3 col = 11.0*hsv2rgb(c*2.5-v.y);
		
	c *= col;
	
	gl_FragColor = vec4(c, 1.);

}