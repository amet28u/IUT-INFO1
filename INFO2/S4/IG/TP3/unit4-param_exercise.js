"use strict"; // good practice - see https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Strict_mode
////////////////////////////////////////////////////////////////////////////////
// Flower petal improvement: scale and re-orient the petals to look better
////////////////////////////////////////////////////////////////////////////////
/*global THREE, Coordinates, document, window, dat, $*/

var camera, scene, renderer;
var cameraControls, effectController;
var sphereTab = [];
var speedTab = [];
var clock = new THREE.Clock();
var gridX = true;
var gridY = false;
var gridZ = false;
var axes = true;
var ground = true;

function fillScene() {
	scene = new THREE.Scene();
	scene.fog = new THREE.Fog( 0x808080, 2000, 4000 );

	// LIGHTS
	var ambientLight = new THREE.AmbientLight( 0x222222 );

	var light = new THREE.DirectionalLight( 0xFFFFFF, 1.0 );
	light.position.set( 200, 400, 500 );

	var light2 = new THREE.DirectionalLight( 0xFFFFFF, 1.0 );
	light2.position.set( -500, 250, -200 );

	scene.add(ambientLight);
	scene.add(light);
	scene.add(light2);
	for (var i = 0; i < 5; i++){
		var dim = Math.random()*5; //dim des obj
		var colorR = Math.random();
		var colorG = Math.random();
		var colorB = Math.random();
		var posX = Math.random()*20-10;
		var posY = Math.random()*20-10;
		var posZ = Math.random()*20-10;
		var speed = Math.random()*3;
		speedTab[i] = Math.floor(speed)+1;
		var material = new THREE.MeshPhongMaterial();
		material.color.setRGB(colorR, colorG, colorB);
		var geometry = new THREE.Mesh( new THREE.SphereGeometry(Math.floor(dim)+1, 32, 32), material);
		geometry.position.set(Math.floor(posX)+1, 30, Math.floor(posZ)+1);
		sphereTab[i] = geometry;
		scene.add(geometry);
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
	renderer = new THREE.WebGLRenderer( { antialias: false } );
	renderer.gammaInput = true;
	renderer.gammaOutput = true;
	renderer.setSize(canvasWidth, canvasHeight);
	renderer.setClearColor( 0xAAAAAA, 1.0 );

	// CAMERA
	camera = new THREE.PerspectiveCamera( 38, canvasRatio, 1, 10000 );
	// CONTROLS
	cameraControls = new THREE.OrbitControls(camera, renderer.domElement);
	camera.position.set(200, 0, 200);
	cameraControls.target.set(0, 0, 0);
	fillScene();

}

function addToDOM() {
	var container = document.getElementById('container');
	var canvas = container.getElementsByTagName('canvas');
	if (canvas.length>0) {
		container.removeChild(canvas[0]);
	}
	container.appendChild( renderer.domElement );
}

function drawHelpers() {
	/*if (ground) {
		Coordinates.drawGround({size:10000});
	}*/
	if (gridX) {
		Coordinates.drawGrid({size:10000,scale:0.01});
	}
	if (gridY) {
		Coordinates.drawGrid({size:10000,scale:0.01, orientation:"y"});
	}
	if (gridZ) {
		Coordinates.drawGrid({size:10000,scale:0.01, orientation:"z"});
	}
	/*if (axes) {
		Coordinates.drawAllAxes({axisLength:200,axisRadius:1,axisTess:50});
	}*/
}

function animate() {

	for (var i = 0; i < 5; i++){
		sphereTab[i].position.y = sphereTab[i].position.y-speedTab[i];
		if (sphereTab[i].position.y <= -30){
			sphereTab[i].position.y = 30;
		}
		if (sphereTab[i].position.y > 0){
			sphereTab[i].scale.set(sphereTab[i].scale.x+0.1, 1, sphereTab[i].scale.z+0.1);
		}
		else{
			sphereTab[i].scale.set(sphereTab[i].scale.x-0.1, 1, sphereTab[i].scale.z-0.1);
		}

	}

	window.requestAnimationFrame(animate);
	render();
}

function render() {
	var delta = clock.getDelta();
	cameraControls.update(delta);

	if ( effectController.newGridX !== gridX || effectController.newGridY !== gridY || effectController.newGridZ !== gridZ || effectController.newGround !== ground || effectController.newAxes !== axes)
	{
		gridX = effectController.newGridX;
		gridY = effectController.newGridY;
		gridZ = effectController.newGridZ;
		ground = effectController.newGround;
		axes = effectController.newAxes;

		fillScene();
		drawHelpers();
	}

	renderer.render(scene, camera);
}



function setupGui() {

	effectController = {

		newGridX: gridX,
		newGridY: gridY,
		newGridZ: gridZ,
		newGround: ground,
		newAxes: axes

	};

	var gui = new dat.GUI();
	var h = gui.addFolder("Grid display");
	h.add( effectController, "newGridX").name("Show XZ grid");
	h.add( effectController, "newGridY" ).name("Show YZ grid");
	h.add( effectController, "newGridZ" ).name("Show XY grid");
	h.add( effectController, "newGround" ).name("Show ground");
	h.add( effectController, "newAxes" ).name("Show axes");

}


// this is the main action sequence
try {
	init();
	fillScene();
	drawHelpers();
	addToDOM();
	setupGui();
	animate();
} catch(e) {
	var errorReport = "Your program encountered an unrecoverable error, can not draw on canvas. Error was:<br/><br/>";
	$('#container').append(errorReport+e);
}
