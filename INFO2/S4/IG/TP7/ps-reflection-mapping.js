"use strict"; // good practice - see https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Strict_mode
////////////////////////////////////////////////////////////////////////////////
// Add a reflection map
////////////////////////////////////////////////////////////////////////////////
/*global THREE, requestAnimationFrame, $ */


var camera, scene, renderer;
var cameraControls;
var clock = new THREE.Clock();
var teapotSize = 400;

function fillScene() {
	scene = new THREE.Scene();

	// LIGHTS
	scene.add( new THREE.AmbientLight( 0x333333 ) );

	var light = new THREE.DirectionalLight( 0xFFFFFF, 0.9 );
	light.position.set( -1300, 700, 1240 );

	scene.add( light );

	light = new THREE.DirectionalLight( 0xFFFFFF, 0.7 );
	light.position.set( 1000, -500, -1200 );

	scene.add( light );

	scene.background = new THREE.CubeTextureLoader()
	.setPath( 'textures/skybox/' )
	.load( ['px.jpg', 'nx.jpg',
			'py.jpg', 'ny.jpg',
			'pz.jpg', 'nz.jpg' ]);



	var teapotMaterial = new THREE.MeshPhongMaterial(
		{ color: 0x770000, specular:0xffaaaa } );

	var teapot = new THREE.Mesh(
		new THREE.TeapotGeometry( teapotSize,
			8, true, true, true, true, true ),
		teapotMaterial );

	scene.add( teapot );
}

function init() {
	var canvasWidth = 846;
	var canvasHeight = 494;
	// For grading the window is fixed in size; here's general code:
	//var canvasWidth = window.innerWidth;
	//var canvasHeight = window.innerHeight;

	// CAMERA
	camera = new THREE.PerspectiveCamera( 45, canvasWidth/ canvasHeight, 100, 20000 );
	camera.position.set( -222, 494, 1746 );

	// RENDERER
	renderer = new THREE.WebGLRenderer( { antialias: true } );
	renderer.setSize( canvasWidth, canvasHeight );
	renderer.setClearColor( 0xAAAAAA, 1.0 );
	renderer.gammaInput = true;
	renderer.gammaOutput = true;

	// CONTROLS

	cameraControls = new THREE.OrbitControls( camera, renderer.domElement );
	cameraControls.target.set(0, -160, 0);

}

// EVENT HANDLERS

function addToDOM() {
	var container = document.getElementById( 'webGL');
	var canvas = container.getElementsByTagName('canvas');
	if (canvas.length>0) {
		container.removeChild(canvas[0]);
	}
	container.appendChild( renderer.domElement );
}

function animate() {
	requestAnimationFrame( animate );
	render();
}

function render() {
	var delta = clock.getDelta();
	cameraControls.update( delta );

	renderer.render( scene, camera );
}

try {
	init();
	fillScene();
	addToDOM();
	animate();
} catch(e) {

}
