"use strict"; // good practice - see https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Strict_mode
////////////////////////////////////////////////////////////////////////////////
// Particle System
////////////////////////////////////////////////////////////////////////////////
/*global THREE, document, window, $*/


var camera, scene, renderer;
var cameraControls;

var clock = new THREE.Clock();

function fillScene() {
	scene = new THREE.Scene();

	var geometry = new THREE.Geometry();

	// Student: rewrite the following vertex generation code so that
	// vertices are generated every 100 units:
	// -1000,-1000,-1000 to 1000,1000,1000, e.g.
	// at -1000,-1000,-1000, -900,-1000,-1000,
	// and so on, for the 21*21*21 = 9261 points.

    var disk = new THREE.TextureLoader().load( 'disc.png' );
	var material = new THREE.SpriteMaterial( { map: disk} );
	material.color.setHSL( 0.9, 0.2, 0.6 )
	for ( var i = 0; i < 8000; i ++ ) {
		var particles = new THREE.Sprite( material ); 
		var vertex = new THREE.Vector3();
		// accept the point only if it's in the sphere
		do {
			vertex.x = 2000 * Math.random() - 1000;
			vertex.y = 2000 * Math.random() - 1000;
			vertex.z = 2000 * Math.random() - 1000;
		} while ( vertex.length() > 1000 );
		particles.scale.set(35, 35, 35);
		particles.position.x=vertex.x;
		particles.position.y=vertex.y;
		particles.position.z=vertex.z;
		scene.add( particles);
	}
}

function init() {
	var canvasWidth = 846;
	var canvasHeight = 494;
	// For grading the window is fixed in size; here's general code:
	//var canvasWidth = window.innerWidth;
	//var canvasHeight = window.innerHeight;
	var canvasRatio = canvasWidth / canvasHeight;

	// RENDERER
	renderer = new THREE.WebGLRenderer( { antialias: true } );
	renderer = new THREE.WebGLRenderer( { clearAlpha: 1 } );
	renderer.gammaInput = true;
	renderer.gammaOutput = true;
	renderer.setSize(canvasWidth, canvasHeight);
	renderer.setClearColor( 0xAAAAAA, 1.0 );

	// CAMERA
	camera = new THREE.PerspectiveCamera( 55, canvasRatio, 2, 8000 );
	camera.position.set( 10, 5, 15 );
	// CONTROLS
	cameraControls = new THREE.OrbitControls(camera, renderer.domElement);
	cameraControls.target.set(0,0,0);

}

function addToDOM() {
	var container = document.getElementById('webGL');
	var canvas = container.getElementsByTagName('canvas');
	if (canvas.length>0) {
		container.removeChild(canvas[0]);
	}
	container.appendChild( renderer.domElement );
}

function animate() {
	window.requestAnimationFrame(animate);
	render();
}

function render() {
	var delta = clock.getDelta();
	cameraControls.update(delta);
	renderer.render(scene, camera);
}

try {
	init();
	fillScene();
	addToDOM();
	animate();
} catch(e) {
	var errorReport = "Your program encountered an unrecoverable error, can not draw on canvas. Error was:<br/><br/>";
}