#ifdef GL_ES
precision mediump float;
#endif

uniform float u_time;
uniform vec2 u_mouse;
uniform vec2 u_resolution;

#define PI 3.14159265359
#define DEPTH 16
#define FBASE 2.0
#define BASE 2
#define BASE_SQURED 4
#define BASE_QUBED 8
#define BASE_4 8
#define time u_time
#define mouse u_mouse
#define resolution u_resolution

float sum_arr(float arr[BASE_QUBED])
{
	float sum = 0.0;
	
	for (int i = 0; i < BASE_QUBED; i++)
		sum += arr[i];
	
	return sum;
}

vec2 get_stat(vec4 v)
{
	float x = v.x;
	float y = v.y;
	float z = v.z;
	float t = v.w;
	
	float r[BASE_QUBED];
	float g[BASE_QUBED];
	
	for (int n = 0; n < DEPTH; n++)
	{	
		float a = mod(x, FBASE);
		float b = mod(y, FBASE);
		float c = mod(z, FBASE);
		// float d = mod(t, FBASE);
		
		// 2D
		// float i = a + (b * FBASE);
		// 3D
		float i = a + (b * FBASE) + (c * FBASE * FBASE);
		// 4D
		// float i = a + (b * FBASE) + (c * FBASE * FBASE) + (d * FBASE * FBASE * FBASE);
		
		if (i == 0.0)
			r[0] = 1.0;
		if (i == 1.0)
			r[1] = 1.0;
		if (i == 2.0)
			r[2] = 1.0;
		if (i == 3.0)
			r[3] = 1.0;
		if (i == 4.0)
			r[4] = 1.0;
		if (i == 5.0)
			r[5] = 1.0;
		if (i == 6.0)
			r[6] = 1.0;
		if (i == 7.0)
			r[7] = 1.0;
				
		if (i == 8.0)
			g[0] = 1.0;
		if (i == 9.0)
			g[1] = 1.0;		
		if (i == 10.0)
			g[2] = 1.0;
		if (i == 11.0)
			g[3] = 1.0;
		if (i == 12.0)
			g[4] = 1.0;
		if (i == 13.0)
			g[5] = 1.0;
		if (i == 14.0)
			g[6] = 1.0;
		if (i == 15.0)
			g[7] = 1.0;				
		
		x = floor(x / FBASE);
		y = floor(y / FBASE);
		z = floor(z / FBASE);
		t = floor(t / FBASE);
	}
	
	float red = sum_arr(r) / (FBASE * FBASE * FBASE);
	float green = sum_arr(g) / (FBASE * FBASE * FBASE);
	
	return vec2(red, red);
}

void main( void ) {

	vec2 position = ( gl_FragCoord.xy / resolution.xy ) + mouse / 4.0;
		
	vec2 s = get_stat(vec4(gl_FragCoord.x, gl_FragCoord.y, mouse.x * resolution.x, mouse.y * resolution.y));

	gl_FragColor = vec4(s.x, s.y, 0.3, 1.0);

}