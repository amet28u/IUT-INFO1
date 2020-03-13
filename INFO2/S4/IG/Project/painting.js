"use strict";

var camera, scene, renderer;
var cameraControls, effectController;
var clock = new THREE.Clock();
var gridX = false;
var gridY = false;
var gridZ = false;
var axes = false;
var ground = true;

function painting(){
  var sphereMaterial = new THREE.MeshLambertMaterial({color:0xEE3A2F});
  var cubeMaterial = new THREE.MeshLambertMaterial({color:0xcbd85b});
  var table = new THREE.Mesh(new THREE.CubeGeometry(100, 25, 50), cubeMaterial);
  table.position.x = 0;
  table.position.y = 13;
  table.position.z = 0;
  scene.add(table);
  var points = [];
for ( var i = 0; i < 10; i ++ ) {
	points.push( new THREE.Vector2( Math.sin( i * 0.2 ) * 10 + 5, ( i - 5 ) * 2 ) );
}
var geometry = new THREE.LatheGeometry( points );
var material = new THREE.MeshBasicMaterial( { color: 0xffffff } );
var lathe = new THREE.Mesh( geometry, material );
lathe.position.y = 25;
scene.add( lathe );
  /*
  var verticesOfCube = [
    -1,-1,-1,    1,-1,-1,    1, 1,-1,    -1, 1,-1,
    -1,-1, 1,    1,-1, 1,    1, 1, 1,    -1, 1, 1,
];

var indicesOfFaces = [
    2,1,0,    0,3,2,
    0,4,7,    7,3,0,
    0,1,5,    5,4,0,
    1,2,6,    6,5,1,
    2,3,7,    7,6,2,
    4,5,6,    6,7,4
];
  var geometry = new THREE.PolyhedronGeometry( verticesOfCube, indicesOfFaces, 6, 2 );
  geometry.position.x = 0;
  geometry.position.y = 0;
  geometry.position.z = 0;
  var geo = new THREE.Mesh(geometry, cubeMaterial);*/
  //scene.add(geo);
  //scene.add(geometry);
}

function createCup(){
  var cylinderMaterial = new THREE.MeshLambertMaterial({color:0xA49F97});
  var base = new THREE.Mesh(new THREE.CylinderGeometry(100, ))
}

function init(){
  var canvasWidth = window.innerWidth;
  var canvasHeight = window.innerHeight;
  var canvasRatio = canvasWidth / canvasHeight;
  renderer = new THREE.WebGLRenderer({antialias:true});
	renderer.gammaInput = true;
	renderer.gammaOutput = true;
	renderer.setSize(canvasWidth, canvasHeight);
	renderer.setClearColor(0xdc98dd, 1.0);
  camera = new THREE.PerspectiveCamera(45, canvasRatio, 1, 40000);
	camera.position.set(-700, 500, -600);
  cameraControls = new THREE.OrbitControls(camera, renderer.domElement);
	cameraControls.target.set(0,600,0);
  fillScene();
}

function addToDOM(){
  var container = document.getElementById('painting');
	var canvas = container.getElementsByTagName('canvas');
	if (canvas.length>0){
		container.removeChild(canvas[0]);
	}
	container.appendChild(renderer.domElement);
}

function fillScene() {
	scene = new THREE.Scene();
	//scene.fog = new THREE.Fog( 0x808080, 3000, 6000 );
	var ambientLight = new THREE.AmbientLight( 0x222222 );
	var light = new THREE.DirectionalLight( 0xFFFFFF, 1.0 );
	light.position.set( 200, 400, 500 );
	var light2 = new THREE.DirectionalLight( 0xFFFFFF, 1.0 );
	light2.position.set( -400, 200, -300 );
	scene.add(ambientLight);
	scene.add(light);
	scene.add(light2);
	if (ground) {
		Coordinates.drawGround({size:1000});
	}
	if (gridX) {
		Coordinates.drawGrid({size:1000,scale:0.01});
	}
	if (gridY) {
		Coordinates.drawGrid({size:1000,scale:0.01, orientation:"y"});
	}
	if (gridZ) {
		Coordinates.drawGrid({size:1000,scale:0.01, orientation:"z"});
	}
	if (axes) {
		Coordinates.drawAllAxes({axisLength:300,axisRadius:2,axisTess:50});
	}
  painting();
  createCup();
}

function animate() {
	window.requestAnimationFrame(animate);
	render();
}

function render() {
	var delta = clock.getDelta();
	cameraControls.update(delta);
	if (effectController.newGridX !== gridX || effectController.newGridY !== gridY
     || effectController.newGridZ !== gridZ || effectController.newGround !== ground
      || effectController.newAxes !== axes){
		gridX = effectController.newGridX;
		gridY = effectController.newGridY;
		gridZ = effectController.newGridZ;
		ground = effectController.newGround;
		axes = effectController.newAxes;
		fillScene();
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
	gui.add(effectController, "newGridX").name("Show XZ grid");
	gui.add( effectController, "newGridY" ).name("Show YZ grid");
	gui.add( effectController, "newGridZ" ).name("Show XY grid");
	gui.add( effectController, "newGround" ).name("Show ground");
	gui.add( effectController, "newAxes" ).name("Show axes");
}
try{
	init();
	setupGui();
	addToDOM();
	animate();
}
catch(e){
	var errorReport = "Your program encountered an unrecoverable error, can not draw on canvas. Error was:<br/><br/>";
	$('#container').append(errorReport+e);
}
