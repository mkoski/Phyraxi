(function() {
	$.getJSON(starmap_url, function(json) {
		var starmap = json;
		var container = document.getElementById('map_container');
		var WIDTH = window.innerWidth, HEIGHT = window.innerHeight;
		var radius = 10, segments = 32, rings = 6;
		var scene = new THREE.Scene();
		var VIEW_ANGLE = 45, ASPECT = WIDTH / HEIGHT, NEAR = 0.1, FAR = 20000;
		var camera = new THREE.PerspectiveCamera(VIEW_ANGLE, ASPECT, NEAR, FAR);
		camera.position.z = 150;
		var renderer = new THREE.WebGLRenderer();
		renderer.setSize(WIDTH, HEIGHT);
		
		for ( var i in starmap.stars) {
			var x = starmap.stars[i]['coordinates']['x'];
			var y = starmap.stars[i]['coordinates']['y'];
			var z = starmap.stars[i]['coordinates']['z'];
			console.log("Loop.");
			var sphereMaterial = new THREE.MeshLambertMaterial({
				color : spectral_types[starmap.stars[i]['type'].charAt(0)]
			});
			var sphere = new THREE.Mesh(new THREE.SphereGeometry(radius,
					segments, segments), new THREE.MeshPhongMaterial({
				map : THREE.ImageUtils
						.loadTexture('img/'
								+ spectral_types_jpg[starmap.stars[i]['type']
										.charAt(0)]),
			}));
			// var light = new THREE.PointLight(0xffffff, 0.6, 0);
			var light = new THREE.DirectionalLight(0xffffff, 0.5);
			light.position.set(x, y, z);
			scene.add(light);
			sphere.position.set(x, y, z);		
			scene.add(sphere)
		}
		
		var starfield = createStars(10000, 64);
		scene.add(starfield);
		var controls = new THREE.TrackballControls(camera);
		// var controls = new THREE.OrbitControls(camera,renderer.domElement);
		container.appendChild(renderer.domElement);
		render();
		
		function render() {
			controls.update();
			requestAnimationFrame(render);
			renderer.render(scene, camera);
		}

		function createStars(radius, segments) {
			return new THREE.Mesh(new THREE.SphereGeometry(radius, segments,
					segments), new THREE.MeshBasicMaterial({
				map : THREE.ImageUtils.loadTexture('img/mw4000.png'),
				side : THREE.BackSide
			}));
		}
	});
}());