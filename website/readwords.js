const censor = require("censorify");
console.log(censor.getCensoredWords());
console.log("Uncensored: Some very sad, bad, gloomy, and mad text.");
console.log(censor.censor("Censored1: Some very sad, bad, gloomy, and mad text."));
console.log("Adding gloomy");
censor.addCensoredWord("gloomy");
console.log(censor.censor("Censored2: Some very sad, bad, gloomy, and mad text."));
console.log(censor.getCensoredWords());

