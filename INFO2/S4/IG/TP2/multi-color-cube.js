"use strict"; // good practice - see https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Strict_mode
////////////////////////////////////////////////////////////////////////////////
/*global THREE, window, document, $*/
var camera, scene, renderer;
var cameraControls;
var clock = new THREE.Clock();
var ambientLight, light;

function drawCube0(x1, y1, z1, x2, y2, z2) {
	scene = new THREE.Scene();
	// CUBE MESH
	var material, geometry, mesh;
	material = new THREE.MeshBasicMaterial( { vertexColors: THREE.VertexColors } );
	geometry = new THREE.Geometry();
	geometry.vertices.push( new THREE.Vector3( x1, y1, z1 ) ); //vertex 0
	geometry.vertices.push( new THREE.Vector3( x2, y1, z1 ) ); //vertex 1
	geometry.vertices.push( new THREE.Vector3( x1, y2, z1 ) ); //vertex 2
  geometry.vertices.push( new THREE.Vector3( x2, y2, z1 ) ); //vertex 3
	geometry.vertices.push( new THREE.Vector3( x1, y2, z2 ) ); //vertex 4
  geometry.vertices.push( new THREE.Vector3( x2, y2, z2 ) ); //vertex 5
  geometry.vertices.push( new THREE.Vector3( x1, y1, z2 ) ); //vertex 6
	geometry.vertices.push( new THREE.Vector3( x2, y1, z2 ) ); //vertex 7
	var triangle0 = new THREE.Face3( 2, 1, 0);
  var triangle1 = new THREE.Face3( 2, 3, 1);
  var triangle2 = new THREE.Face3( 3, 2, 4);
  var triangle3 = new THREE.Face3( 3, 4, 5);
  var triangle4 = new THREE.Face3( 2, 0, 6);
  var triangle5 = new THREE.Face3( 2, 6, 4);
  var triangle6 = new THREE.Face3( 0, 1, 7);
  var triangle7 = new THREE.Face3( 0, 7, 6);
  var triangle8 = new THREE.Face3( 1, 3, 5);
  var triangle9 = new THREE.Face3( 1, 5, 7);
  var triangle10 = new THREE.Face3( 6, 7, 5);
  var triangle11 = new THREE.Face3( 6, 5, 4);
	triangle0.vertexColors[0] = new THREE.Color(0x000000);
	triangle0.vertexColors[1] = new THREE.Color(0x000000);
	triangle0.vertexColors[2] = new THREE.Color(0x000000);
  triangle1.vertexColors[0] = new THREE.Color(0x000000);
	triangle1.vertexColors[1] = new THREE.Color(0x000000);
	triangle1.vertexColors[2] = new THREE.Color(0x000000);
  triangle2.vertexColors[0] = new THREE.Color(0xff0000);
	triangle2.vertexColors[1] = new THREE.Color(0xff0000);
	triangle2.vertexColors[2] = new THREE.Color(0xff0000);
  triangle3.vertexColors[0] = new THREE.Color(0xff0000);
	triangle3.vertexColors[1] = new THREE.Color(0xff0000);
	triangle3.vertexColors[2] = new THREE.Color(0xff0000);
  triangle4.vertexColors[0] = new THREE.Color(0x00ff00);
	triangle4.vertexColors[1] = new THREE.Color(0x00ff00);
	triangle4.vertexColors[2] = new THREE.Color(0x00ff00);
  triangle5.vertexColors[0] = new THREE.Color(0x00ff00);
	triangle5.vertexColors[1] = new THREE.Color(0x00ff00);
	triangle5.vertexColors[2] = new THREE.Color(0x00ff00);
  triangle6.vertexColors[0] = new THREE.Color(0x0000ff);
	triangle6.vertexColors[1] = new THREE.Color(0x0000ff);
	triangle6.vertexColors[2] = new THREE.Color(0x0000ff);
  triangle7.vertexColors[0] = new THREE.Color(0x0000ff);
	triangle7.vertexColors[1] = new THREE.Color(0x0000ff);
	triangle7.vertexColors[2] = new THREE.Color(0x0000ff);
  triangle8.vertexColors[0] = new THREE.Color(0xffff00);
	triangle8.vertexColors[1] = new THREE.Color(0xffff00);
	triangle8.vertexColors[2] = new THREE.Color(0xffff00);
  triangle9.vertexColors[0] = new THREE.Color(0xffff00);
	triangle9.vertexColors[1] = new THREE.Color(0xffff00);
	triangle9.vertexColors[2] = new THREE.Color(0xffff00);
  triangle10.vertexColors[0] = new THREE.Color(0x00ffff);
	triangle10.vertexColors[1] = new THREE.Color(0x00ffff);
	triangle10.vertexColors[2] = new THREE.Color(0x00ffff);
  triangle11.vertexColors[0] = new THREE.Color(0x00ffff);
	triangle11.vertexColors[1] = new THREE.Color(0x00ffff);
	triangle11.vertexColors[2] = new THREE.Color(0x00ffff);
  geometry.faces.push( triangle0 );
	geometry.faces.push( triangle1 );
  geometry.faces.push( triangle2 ); //triangle
  geometry.faces.push( triangle3 ); //triangle
  geometry.faces.push( triangle4 ); //triangle
  geometry.faces.push( triangle5 ); //triangle
  geometry.faces.push( triangle6 ); //triangle
  geometry.faces.push( triangle7 ); //triangle
  geometry.faces.push( triangle8 ); //triangle
  geometry.faces.push( triangle9 ); //triangle
  geometry.faces.push( triangle10 ); //triangle
  geometry.faces.push( triangle11 ); //triangle
	mesh = new THREE.Mesh( geometry, material );
	scene.add( mesh );
}

function drawCube1(x1, y1, z1, x2, y2, z2) {
	scene = new THREE.Scene();
	// CUBE MESH
	var material, geometry, mesh;
	material = new THREE.MeshLambertMaterial( { vertexColors: THREE.VertexColors } );
	geometry = new THREE.Geometry();
	geometry.vertices.push( new THREE.Vector3( x1, y1, z1 ) ); //vertex 0
	geometry.vertices.push( new THREE.Vector3( x2, y1, z1 ) ); //vertex 1
	geometry.vertices.push( new THREE.Vector3( x1, y2, z1 ) ); //vertex 2
  geometry.vertices.push( new THREE.Vector3( x2, y2, z1 ) ); //vertex 3
	geometry.vertices.push( new THREE.Vector3( x1, y2, z2 ) ); //vertex 4
  geometry.vertices.push( new THREE.Vector3( x2, y2, z2 ) ); //vertex 5
  geometry.vertices.push( new THREE.Vector3( x1, y1, z2 ) ); //vertex 6
	geometry.vertices.push( new THREE.Vector3( x2, y1, z2 ) ); //vertex 7
	var triangle0 = new THREE.Face3( 2, 1, 0);
  var triangle1 = new THREE.Face3( 2, 3, 1);
  var triangle2 = new THREE.Face3( 3, 2, 4);
  var triangle3 = new THREE.Face3( 3, 4, 5);
  var triangle4 = new THREE.Face3( 2, 0, 6);
  var triangle5 = new THREE.Face3( 2, 6, 4);
  var triangle6 = new THREE.Face3( 0, 1, 7);
  var triangle7 = new THREE.Face3( 0, 7, 6);
  var triangle8 = new THREE.Face3( 1, 3, 5);
  var triangle9 = new THREE.Face3( 1, 5, 7);
  var triangle10 = new THREE.Face3( 6, 7, 5);
  var triangle11 = new THREE.Face3( 6, 5, 4);
	triangle0.vertexColors[0] = new THREE.Color(0x000000);
	triangle0.vertexColors[1] = new THREE.Color(0x000000);
	triangle0.vertexColors[2] = new THREE.Color(0x000000);
  triangle1.vertexColors[0] = new THREE.Color(0x000000);
	triangle1.vertexColors[1] = new THREE.Color(0x000000);
	triangle1.vertexColors[2] = new THREE.Color(0x000000);
  triangle2.vertexColors[0] = new THREE.Color(0xff0000);
	triangle2.vertexColors[1] = new THREE.Color(0xff0000);
	triangle2.vertexColors[2] = new THREE.Color(0xff0000);
  triangle3.vertexColors[0] = new THREE.Color(0xff0000);
	triangle3.vertexColors[1] = new THREE.Color(0xff0000);
	triangle3.vertexColors[2] = new THREE.Color(0xff0000);
  triangle4.vertexColors[0] = new THREE.Color(0x00ff00);
	triangle4.vertexColors[1] = new THREE.Color(0x00ff00);
	triangle4.vertexColors[2] = new THREE.Color(0x00ff00);
  triangle5.vertexColors[0] = new THREE.Color(0x00ff00);
	triangle5.vertexColors[1] = new THREE.Color(0x00ff00);
	triangle5.vertexColors[2] = new THREE.Color(0x00ff00);
  triangle6.vertexColors[0] = new THREE.Color(0x0000ff);
	triangle6.vertexColors[1] = new THREE.Color(0x0000ff);
	triangle6.vertexColors[2] = new THREE.Color(0x0000ff);
  triangle7.vertexColors[0] = new THREE.Color(0x0000ff);
	triangle7.vertexColors[1] = new THREE.Color(0x0000ff);
	triangle7.vertexColors[2] = new THREE.Color(0x0000ff);
  triangle8.vertexColors[0] = new THREE.Color(0xffff00);
	triangle8.vertexColors[1] = new THREE.Color(0xffff00);
	triangle8.vertexColors[2] = new THREE.Color(0xffff00);
  triangle9.vertexColors[0] = new THREE.Color(0xffff00);
	triangle9.vertexColors[1] = new THREE.Color(0xffff00);
	triangle9.vertexColors[2] = new THREE.Color(0xffff00);
  triangle10.vertexColors[0] = new THREE.Color(0x00ffff);
	triangle10.vertexColors[1] = new THREE.Color(0x00ffff);
	triangle10.vertexColors[2] = new THREE.Color(0x00ffff);
  triangle11.vertexColors[0] = new THREE.Color(0x00ffff);
	triangle11.vertexColors[1] = new THREE.Color(0x00ffff);
	triangle11.vertexColors[2] = new THREE.Color(0x00ffff);
  geometry.faces.push( triangle0 );
	geometry.faces.push( triangle1 );
  geometry.faces.push( triangle2 ); //triangle
  geometry.faces.push( triangle3 ); //triangle
  geometry.faces.push( triangle4 ); //triangle
  geometry.faces.push( triangle5 ); //triangle
  geometry.faces.push( triangle6 ); //triangle
  geometry.faces.push( triangle7 ); //triangle
  geometry.faces.push( triangle8 ); //triangle
  geometry.faces.push( triangle9 ); //triangle
  geometry.faces.push( triangle10 ); //triangle
  geometry.faces.push( triangle11 ); //triangle
	geometry.computeVertexNormals();
	mesh = new THREE.Mesh( geometry, material );
	scene.add( mesh );
  // LIGHTS
	scene.add( ambientLight );
	scene.add( light );
}

function drawCubes(x1, y1, z1, x2, y2, z2){
	scene = new THREE.Scene();
	for (var i = 0; i < 1000; i++){
		var x = Math.random()*100-50;
		var y = Math.random()*100-50;
		var z = Math.random()*100-50;
		if (x*x + y*y + z*z < 1000){
			var material = new THREE.MeshLambertMaterial({color:0xa6b3d8, opacity: 1-(x*x + y*y + z*z)/1000, transparent: true});
			var geometry = new THREE.Mesh( new THREE.CubeGeometry(5, 5, 5), material);
			geometry.position.set(x, y, z);
			scene.add(geometry);
		}
	}
	// LIGHTS
	scene.add( ambientLight );
	scene.add( light );
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
	renderer.gammaInput = true;
	renderer.gammaOutput = true;
	renderer.setSize(canvasWidth, canvasHeight);
	renderer.setClearColor( 0xAAAAAA, 1.0 );

  // LIGHTS
	ambientLight = new THREE.AmbientLight( 0x222222, 0.6 );
	light = new THREE.DirectionalLight( 0xFFFFFF );
	light.position.set( -1, -1, -1 );

	// CAMERA
	camera = new THREE.PerspectiveCamera( 55, canvasRatio, 1, 4000 );
	camera.position.set( 100, 150, 130 );

	// CONTROLS
	cameraControls = new THREE.OrbitControls(camera, renderer.domElement);
	cameraControls.target.set(0,0,0);

}

function addToDOM() {
	var container = document.getElementById('container5');
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
	drawCubes();
	addToDOM();
	animate();
} catch(e) {
	var errorReport = "Your program encountered an unrecoverable error, can not draw on canvas. Error was:<br/><br/>";
	$('#container').append(errorReport+e);
}
