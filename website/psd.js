const { promises } = require("fs");
var fs = require("fs");

async function writeIt() {
    await promises.mkdir("pizza_shanty");
    // Currently produces HTML file but it is not valid sinc there is nothing in
    // it. Figure out how to make HTML file and insert the links for CSS and JS
    // files.
    await promises.writeFile("pizza_shanty/index.html", "", function (err) {
        if (err) throw err;
        console.log("HTML file was created successfully.");
    });
    await promises.mkdir("pizza_shanty/scripts");
    await promises.writeFile("pizza_shanty/scripts/pizza_shanty.js", "", function (err) {
        if (err) throw err;
        console.log("JS file was created successfully.");
    });
    await promises.mkdir("pizza_shanty/styles");
    await promises.writeFile("pizza_shanty/styles/pizza_shanty.css", "", function (err) {
        if (err) throw err;
        console.log("CSS file was created successfully.");
    });
    await promises.mkdir("pizza_shanty/images");
    await promises.writeFile("pizza_shanty/images/pizza_image1.png", "", function (err) {
        if (err) throw err;
        console.log("Image 1 was created.");
    });
    await promises.writeFile("pizza_shanty/images/pizza_image2.png", "", function (err) {
        if (err) throw err;
        console.log("Image 2 was created.");
    });
}

writeIt()
