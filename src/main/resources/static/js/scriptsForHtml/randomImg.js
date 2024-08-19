var images = [
    '/img/blog-img/1.jpg',
    '/img/bg-img/breadcumb2.jpg',
    '/img/bg-img/breadcumb3.jpg'
];

var recipeFrames = document.getElementsByClassName('recipe-frame');
for (var i = 0; i < recipeFrames.length; i++) {
    var randomIndex = Math.floor(Math.random() * images.length);
    var selectedImage = images.splice(randomIndex, 1)[0];
    recipeFrames[i].style.backgroundImage = 'url("' + selectedImage + '")';
}
