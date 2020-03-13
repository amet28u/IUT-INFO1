"use strict"; // good practice - see https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Strict_mode
////////////////////////////////////////////////////////////////////////////////
// Make a swivel and tilt light
////////////////////////////////////////////////////////////////////////////////
/*global THREE, Coordinates, document, dat, window, $*/

var camera, scene, renderer, stats;
var cameraControls, effectController;
var clock = new THREE.Clock();

var light;
var geo, material;
var speed, geometry;

function fillScene() {
	// SCENE
	scene = new THREE.Scene();
	scene.fog = new THREE.Fog( 0xAAAAAA, 2000, 4000 );

	// LIGHTS
	/*scene.add( new THREE.AmbientLight( 0x222222 ) );
	light = new THREE.DirectionalLight( 0xFFFFFF, 1.5 );
	light.position.set( 1, 1, 0 );
	scene.add( light );*/

	for (var i = 0; i < 59; i++){
		var colorR = Math.random();
		var colorG = Math.random();
		var colorB = Math.random();
		var colorRGB = new THREE.Color(colorR, colorG, colorB);
		var material1 = new THREE.MeshPhongMaterial({color: colorRGB, emissive: colorRGB});
		var geo1 = new THREE.Mesh(new THREE.SphereGeometry(0.5, 32, 32), material1);
		var posX = Math.random()*20-10;
		var posY = Math.random()*20-10;
		var posZ = Math.random()*20-10;
		geo1.position.set(posX, posY, posZ);
		scene.add(geo1);
		var light1 = new THREE.PointLight(colorRGB, 1);
		light1.position.set(posX, posY, posZ);
		scene.add(light1);
	}

	// MATERIALS
	material = new THREE.MeshPhongMaterial({color:0xFFFFFF});

	// GEOMETRIES
	geometry = [];
	for (var i = 0; i < 10; i++){
		geo = new THREE.Mesh( new THREE.SphereGeometry(2, 32, 32), material);
		geo.position.set(Math.random()*20-10, Math.random()*20-10, Math.random()*20-10);
		geometry.push(geo);
		scene.add(geo);
	}

	// SPEEDS
	speed = [
		new THREE.Vector3(Math.random()/5, Math.random()/5, Math.random()/5),
		new THREE.Vector3(Math.random()/5, Math.random()/5, Math.random()/5),
		new THREE.Vector3(Math.random()/5, Math.random()/5, Math.random()/5),
		new THREE.Vector3(Math.random()/5, Math.random()/5, Math.random()/5),
		new THREE.Vector3(Math.random()/5, Math.random()/5, Math.random()/5),
		new THREE.Vector3(Math.random()/5, Math.random()/5, Math.random()/5),
		new THREE.Vector3(Math.random()/5, Math.random()/5, Math.random()/5),
		new THREE.Vector3(Math.random()/5, Math.random()/5, Math.random()/5),
		new THREE.Vector3(Math.random()/5, Math.random()/5, Math.random()/5),
		new THREE.Vector3(Math.random()/5, Math.random()/5, Math.random()/5)
	];
}

function init() {
	var canvasWidth = 846;
	var canvasHeight = 494;
	// For grading the window is fixed in size; here's general code:
	//var canvasWidth = window.innerWidth;
	//var canvasHeight = window.innerHeight;

	// RENDERER
	renderer = new THREE.WebGLRenderer( { antialias: true } );
	renderer.gammaInput = true;
	renderer.gammaOutput = true;
	renderer.setSize(canvasWidth, canvasHeight);
	renderer.setClearColor( 0x000000, 1.0 );

	// CAMERA
	camera = new THREE.PerspectiveCamera( 35, canvasWidth/ canvasHeight, 1, 4000 );
	camera.position.set( -50, 0, -50 );

	// CONTROLS
	cameraControls = new THREE.OrbitControls(camera, renderer.domElement);
	cameraControls.target.set(0,0,0);

	stats = new Stats();
	stats.showPanel( 0 ); // 0: fps, 1: ms, 2: mb, 3+: custom
	document.body.appendChild( stats.dom );

}

function drawHelpers() {
	//Coordinates.drawGround({size:10000});
	//Coordinates.drawGrid({size:10000,scale:0.01});
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
	for (var i = 0; i < 10; i++){
		geometry[i].position.set(geometry[i].position.x+speed[i].x,
			geometry[i].position.y+speed[i].y,
			geometry[i].position.z+speed[i].z);
		if (geometry[i].position.x > 10 || geometry[i].position.x < -10)
			speed[i].x = -speed[i].x;
		if (geometry[i].position.y > 10 || geometry[i].position.y < -10)
			speed[i].y = -speed[i].y;
		if (geometry[i].position.z > 10 || geometry[i].position.z < -10)
			speed[i].z = -speed[i].z;
	}
	render();
	stats.update();
}

function render() {
	var delta = clock.getDelta();
	cameraControls.update(delta);
	renderer.render(scene, camera);
}

function setupGui() {
}

try {
	init();
	fillScene();
	setupGui();
	drawHelpers();
	addToDOM();
	animate();
} catch(e) {
	var errorReport = "Your program encountered an unrecoverable error, can not draw on canvas. Error was:<br/><br/>";
}
