const censoredWords = ["sad", "mad", "bad"];
const customCensoredWords = [];
function censor(inStr) {  
  for (idx in censoredWords) {    
      inStr = inStr.replace(censoredWords[idx], "***"); 
  }
  for (idx in customCensoredWords) {    
       inStr = inStr.replace(customCensoredWords[idx], "***"); 
  }
  return inStr; 
}
function addCensoredWord(word) {  
  customCensoredWords.push(word);
}
function getCensoredWords() { 
  return censoredWords.concat(customCensoredWords);
}
//Make functions public.  Very important.
exports.censor = censor;
exports.addCensoredWord = addCensoredWord;
exports.getCensoredWords = getCensoredWords;

