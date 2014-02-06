///////////////////
// Configuration //
///////////////////

// Star radius
var radius_star = 15;
var radius_halo = 10;

// Scaling coefficient
var map_scaling = 0.1;
// Initial camera position
var camera_position = 20000;

// Starmap options
var starmap_url = "http://localhost:8080/resources/starmap/random/50";
// var background = "galaxy_starfield_darker.png";
var background = "mw4000_darker.png";

// RGB colors for star types
var spectral_types = {
        O : 0x0000ff, // blue
        B : 0x00ffff, // blue-white
        A : 0xffffff, // white
        F : 0xffff99, // yellow-white
        G : 0xffff00, // yellow
        K : 0xff9933, // orange
        M : 0xff0000 //red
      };

// Colored star textures
var spectral_types_jpg = {
        O : "star_blue.jpg", // blue
        B : "star_blue-white.jpg", // blue-white
        A : "star_white.jpg", // white
        F : "star_yellow-white.jpg", // yellow-white
        G : "star_yellow.jpg", // yellow
        K : "star_orange.jpg", // orange
        M : "star_red.jpg" //red
      };

// 2D images for simple stars
var spectral_types_png = {
    O : "particle_blue.png", // blue
    B : "particle_blue-white.png", // blue-white
    A : "particle_white.png", // white
    F : "particle_yellow-white.png", // yellow-white
    G : "particle_yellow.png", // yellow
    K : "particle_orange.png", // orange
    M : "particle_red.png" //red
  };

//toggle full-screen 
THREEx.FullScreen
		.bindKey({charCode : 'f'.charCodeAt(0)
		});
